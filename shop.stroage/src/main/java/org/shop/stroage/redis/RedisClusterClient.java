package org.shop.stroage.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;

import org.shop.stroage.config.NodeConfig;
import org.shop.util.LoggerUtil;
import org.shop.util.StringUtil;
import redis.clients.jedis.*;

/**
 * 
 * 
 * Redis 集群客户端
 * 
 * @author VIC
 *
 */
public class RedisClusterClient {

	private static LoggerUtil log = LoggerUtil.getLogger(RedisClusterClient.class);

	private RedisClusterClient() {
	}

	// ---------------------------------------------------------------cloud----------------------------------------------------------------//
	
	/**
	 * 获取当前运行环境
	 * @return
	 */
	private static String getRunModel() {
		String runModel = null;// NodeConfigService.getRunModel();
		log.debug("Current run model: " + runModel);
		return runModel;
	}

	private static JedisCluster jedisCluster;
	private static Integer REDIS_DEFAULT_PORT = 6379;

	private static JedisPoolConfig initPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大能够保持空闲状态的对象数
		jedisPoolConfig.setMaxIdle(1024);
		// 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setMaxWaitMillis(1000 * 100);

		// 在还会给pool时，是否提前进行validate操作
		jedisPoolConfig.setTestOnReturn(true);
		return jedisPoolConfig;
	}

	private static JedisCluster getJedisCluster() {
		if (jedisCluster == null) {
			Set<HostAndPort> jedisClusterNodes = Sets.newHashSet();
			List<NodeConfig> redusUrls = getNodeConfigs();
			// NodeConfigService.getNodeConfigs("redis.url",getRunModel());
			if (redusUrls != null && !redusUrls.isEmpty()) {
				for (NodeConfig nodeConfig : redusUrls) {
					String url = nodeConfig.getValue();
					if (url.contains(":")) {
						String ip = url.split(":")[0];
						String port = url.split(":")[1];
						if (StringUtil.isEmpty(port)) {
							port = REDIS_DEFAULT_PORT + "";
						}
						jedisClusterNodes.add(new HostAndPort(ip, Integer.valueOf(port)));
					} else {
						jedisClusterNodes.add(new HostAndPort(url, REDIS_DEFAULT_PORT));
					}
				}
			}
			jedisCluster = new JedisCluster(jedisClusterNodes, initPoolConfig());
		}
		return jedisCluster;
	}

	/**
	 * 获取redis集群节点配置方法
	 * @return
	 */
	private static List<NodeConfig> getNodeConfigs() {
		List<NodeConfig> nodeConfigs = Lists.newArrayList();
		// TODO 以下为本地Redis集群测试节点,生产环境中需要改为从数据库或配置文件中获取
		nodeConfigs.add(new NodeConfig("192.168.1.188:7000"));
		nodeConfigs.add(new NodeConfig("192.168.1.188:7001"));
		nodeConfigs.add(new NodeConfig("192.168.1.188:7002"));
		nodeConfigs.add(new NodeConfig("192.168.1.188:7003"));
		nodeConfigs.add(new NodeConfig("192.168.1.188:7004"));
		nodeConfigs.add(new NodeConfig("192.168.1.188:7005"));
		
		return nodeConfigs;
	}

	/**
	 * 根据key判断cahche中是否存在key
	 * 
	 * @param key
	 * @return
	 */
	public static Boolean exists(String key) {
		Boolean value = false;
		try {
			value = getJedisCluster().exists(key);
		} catch (Exception e) {
			return false;
		}
		return value;
	}

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;
		try {
			value = getJedisCluster().get(key);

		} catch (Exception e) {
			return "";
		}
		return value;
	}

	/**
	 * 向cache中添加数据
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		set(key, value, 0);
	}

	/**
	 * 向cache中添加数据
	 * 
	 * @param key
	 * @param value
	 * @param timeOut
	 *            cache超时时间单位S
	 */

	public static void set(String key, String value, int timeOut) {

		try {
			getJedisCluster().set(key, value);
			if (timeOut != 0)
				getJedisCluster().expire(key, timeOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * 向cache中添加一个对象
	 * 
	 * @param key
	 * @param value
	 * @param <T>
	 */
	public static <T> void set(String key, T value) {
		set(key, value, 0);
	}

	/**
	 * 向cache中添加一个对象
	 * 
	 * @param key
	 * @param value
	 * @param timeOut
	 * @param <T> 
	 * cache超时时间单位S
	 */
	public static <T> void set(String key, T value, int timeOut) {
		try {
			getJedisCluster().set(key, CacheUtil.serializeJson(value));
			if (timeOut != 0)
				getJedisCluster().expire(key, timeOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断member元素是否是集合key的成员。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public static Boolean sismember(String key, String member) {
		Boolean sIs = false;
		try {
			sIs = getJedisCluster().sismember(key, member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sIs;
	}

	public static void setSet(String key, String... member) {
		setSet(key, 0, member);
	}

	/**
	 * 向cache中add一个String集合(Set<String>)
	 * 
	 * @param key
	 * @param timeOut cache超时时间单位S
	 * @param member
	 */
	public static void setSet(String key, int timeOut, String... member) {
		try {
			getJedisCluster().sadd(key, member);
			if (timeOut != 0)
				getJedisCluster().expire(key, timeOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key从cache中获取一个Set<String>
	 * 
	 * @param key
	 * @return
	 */
	public static Set<String> getSet(String key) {
		Set<String> value = Sets.newHashSet();
		try {
			value = getJedisCluster().smembers(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return value;
	}

	/**
	 * 根据key从cache中获取一个java对象
	 * 
	 * @param key
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> T get(String key, Class<T> t) {
		String json = get(key);
		if (!StringUtil.isNotEmpty(json)) {
			return CacheUtil.deserializeJson(json, t);
		}
		return null;
	}

	/***
	 * key 不存在向cache写入value对象
	 * 
	 * @param key
	 * @param value
	 * @param timeOut 超时时间单位S
	 * @param <T>
	 */
	public static <T> void setIfNoExists(String key, T value, int timeOut) {
		try {
			long i = getJedisCluster().setnx(key, CacheUtil.serializeJson(value));
			if (timeOut != 0) {
				if (i == 1) {// 第一次写入
					getJedisCluster().expire(key, timeOut);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * key 不存在向cache写入long类型的value
	 * 
	 * @param key
	 * @param value
	 * @param timeOut 超时时间单位S
	 */
	public static void setIfNoExists(String key, long value, int timeOut) {

		try {
			long i = getJedisCluster().setnx(key, value + "");
			if (timeOut != 0) {
				if (i == 1) {// 第一次写入
					getJedisCluster().expire(key, timeOut);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数值以原子方式递增
	 * 
	 * @param key
	 * @return
	 */
	public static long increamentAndGet(String key) {

		try {
			return getJedisCluster().incr(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据key删除cache中的内容
	 * 
	 * @param key
	 */
	public static void del(String key) {
		try {
			getJedisCluster().del(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

