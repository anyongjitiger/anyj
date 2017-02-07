package com.emep.zaixian.security.component;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

/** 
* @ClassName: CustomShiroSessionDao 
* @Description: 自定义的shiro session dao 
* @author com_emep_mpc
* @date 2016年8月29日 下午2:29:18 
*  
*/
public class CustomShiroSessionDao extends AbstractSessionDAO {

	private ShiroSessionRepository shiroSessionRepository;

	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	public void setShiroSessionRepository(
			ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}

	@Override
	public void delete(Session session) {
		if (session == null) {
			System.out.println("错误");
			return;
		}
		Serializable id = session.getId();
		if (id != null)
			getShiroSessionRepository().deleteSession(id);

	}

	@Override
	public Collection<Session> getActiveSessions() {
		return getShiroSessionRepository().getAllSessions();
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		getShiroSessionRepository().saveSession(session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		getShiroSessionRepository().saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return getShiroSessionRepository().getSession(sessionId);
	}

}
