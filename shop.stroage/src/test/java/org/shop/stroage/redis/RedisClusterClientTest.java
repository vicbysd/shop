package org.shop.stroage.redis;

import org.junit.Test;

/**
 * Redis Cluster Test
 * @author VIC
 *
 */
public class RedisClusterClientTest {
	
	@Test
	public void test(){
		RedisClusterClient.set("currentUser", "join");
		String value = RedisClusterClient.get("currentUser");
		System.out.println("value: " + value);
	}

}
