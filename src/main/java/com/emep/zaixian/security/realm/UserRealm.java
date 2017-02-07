package com.emep.zaixian.security.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.emep.zaixian.model.User;
import com.emep.zaixian.service.UserFacade;

/**
 * @ClassName: UserRealm
 * @Description: 用户在登录前获得用户的资源权限、密码信息等
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:39:52
 * 
 */
public class UserRealm extends AuthorizingRealm {
	@Resource
	private UserFacade userFacade;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(// 获得用户的角色id，用户的权限信息
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		/* 静态数据，等待数据库完成后接入正式数据 开始 */
		/*Set<String> set1 = new HashSet<String>();
		set1.add("11111111111111111111");
		Set<String> permissions = new HashSet<String>();
		permissions.add("index:view");// 用户的权限信息格式为 xxxxx:xxxxx
		 静态数据，等待数据库完成后接入正式数据 结束 
		authorizationInfo.setRoles(set1);// 设置用户的角色
		authorizationInfo.setStringPermissions(permissions);// 设置用户的权限*/
		authorizationInfo.setRoles(userFacade.findRoles(username));
        authorizationInfo.setStringPermissions(userFacade.findPermissions(username));
		return authorizationInfo;
	}

	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userFacade.findByUsername(username);
		System.out.println(user.toString());
		// if ("0".equals(user.getStatus())) {
		// throw new LockedAccountException();// 帐号锁定
		// } 这里我注释了，只是测试，正式环境开启
		/*ByteSource bytes = ByteSource.Util.bytes(user.getUserName()
				+ user.getSalt());
		*//**
		 * 获得用户的相关密码信息后交给RetryLimitHashedCredentialsMatcher来处理
		 * 
		 * 
		 * @see RetryLimitHashedCredentialsMatcher
		 * 
		 * *//*
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(), user.getPassword(), bytes, getName());*/
		if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
		return authenticationInfo;
	}
}
