package com.emep.zaixian.security.component;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * @ClassName: CustomShiroCacheManager
 * @Description:自定义的shiroCacheManager
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:28:29
 * 
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {

	private ShiroCacheManager shrioCacheManager;

	public ShiroCacheManager getShrioCacheManager() {
		return shrioCacheManager;
	}

	public void setShrioCacheManager(ShiroCacheManager shrioCacheManager) {
		this.shrioCacheManager = shrioCacheManager;
	}

	@Override
	public void destroy() throws Exception {
		getShrioCacheManager().destroy();
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return getShrioCacheManager().getCache(name);
	}

}
