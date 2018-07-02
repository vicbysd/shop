package org.shop.stroage.redis;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * 缓存转JSON对象辅助类
 * @author VIC
 *
 */
public class CacheUtil {
	
	public static <T> String serializeJson(T t) {
		SerializeConfig sc = new SerializeConfig();
		sc.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		return JSON.toJSONString(t, sc, SerializerFeature.DisableCircularReferenceDetect);

	}

	public static <T> T deserializeJson(String strJson, Class<T> t) {
		return JSON.parseObject(strJson, t);
	}

	public static <T> List<T> deserializeJsonToList(String strJson, Class<T> t) {
		return JSON.parseArray(strJson, t);

	}
}
