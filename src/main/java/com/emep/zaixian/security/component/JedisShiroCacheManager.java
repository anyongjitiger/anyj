package com.emep.zaixian.security.component;

import org.apache.shiro.cache.Cache;

/** 
* @ClassName: JedisShiroCacheManager 
* @Description: redis管理shiro缓存的相关方法 
* @author com_emep_mpc
* @date 2016年8月29日 下午2:26:00 
*  
*/
public class JedisShiroCacheManager implements ShiroCacheManager {

	private RedisManager redisManager;

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		return new JedisShiroCache<K, V>(redisManager, name);
	}

	@Override
	public void destroy() {
		redisManager.init();
		redisManager.flushDB();
	}

}
