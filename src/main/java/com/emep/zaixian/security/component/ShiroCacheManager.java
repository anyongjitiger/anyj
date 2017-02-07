package com.emep.zaixian.security.component;

import org.apache.shiro.cache.Cache;

/**
 * @ClassName: ShiroCacheManager
 * @Description: shiro的缓存方法接口，定义了shiro中进行数据缓存的方法，要实现不同工具的缓存方式，可以通过实现该接口来达到目的
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:19:54
 * 
 */
public interface ShiroCacheManager {
	/** 
	* @Title: getCache 
	* @Description: 获得指定的缓存信息 
	* @param @param name
	* @param @return    设定文件 
	* @return Cache<K,V>    返回类型 
	* @throws 
	*/
	<K, V> Cache<K, V> getCache(String name);

	/** 
	* @Title: destroy 
	* @Description: 清除缓存
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void destroy();
}
