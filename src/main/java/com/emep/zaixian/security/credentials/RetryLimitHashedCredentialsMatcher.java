package com.emep.zaixian.security.credentials;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.emep.zaixian.security.component.RedisManager;
import com.emep.zaixian.security.component.SerializeUtils;
import com.emep.zaixian.service.UserFacade;

/**
 * @ClassName: RetryLimitHashedCredentialsMatcher
 * @Description: 自定义的shiro登录验证方法，用户错误登录5次后，要在5分钟后重新登录
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:34:51
 * 
 */
public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
	@Resource
	private UserFacade userFacade;

	private RedisManager redisManager;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {// 进行登录验证
		String username = (String) token.getPrincipal();
		/* 记录用户的登录操作次数开始 */
		byte[] key = (username + "_retry_locked").getBytes();
		if (null == redisManager.get(key)) {
			redisManager
					.set(key, SerializeUtils.serialize(new Integer(0)), 300);
		}

		Integer retryCount = (Integer) SerializeUtils.deserialize(redisManager
				.get(key));
		retryCount++;
		redisManager.set(key, SerializeUtils.serialize(retryCount), 300);
		/* 记录用户的登录操作次数结束 */
		if (retryCount > 5) {// 如果错误登录次数超过5次，那么就要锁定用户5分钟，并抛出相应的异常
			throw new ExcessiveAttemptsException();
		}
		//进行用户登录信息的匹配操作
		boolean matches = super.doCredentialsMatch(token, info);

		if (matches)
			redisManager.del(key);//如果用户登录操作成功，那么清楚用户之前的错误记录

		return matches;
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
}
