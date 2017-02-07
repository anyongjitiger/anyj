package com.emep.zaixian.security.component;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * @ClassName: ShiroSessionRepository
 * @Description: 统一的shiro_session的方法接口，使用不同的方式缓存session数据，可以通过实现该接口来达到目的
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:17:54
 * 
 */
public interface ShiroSessionRepository {

	/** 
	* @Title: saveSession 
	* @Description: 保存session 
	* @param @param session    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void saveSession(Session session);

	/** 
	* @Title: deleteSession 
	* @Description: 删除session 
	* @param @param sessionId    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void deleteSession(Serializable sessionId);

	/** 
	* @Title: getSession 
	* @Description: 获得指定的session 
	* @param @param sessionId
	* @param @return    设定文件 
	* @return Session    返回类型 
	* @throws 
	*/
	Session getSession(Serializable sessionId);

	/** 
	* @Title: getAllSessions 
	* @Description: 获得缓存的所有的session
	* @param @return    设定文件 
	* @return Collection<Session>    返回类型 
	* @throws 
	*/
	Collection<Session> getAllSessions();
}