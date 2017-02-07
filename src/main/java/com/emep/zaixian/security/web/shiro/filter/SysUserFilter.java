package com.emep.zaixian.security.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.emep.zaixian.security.Constants;
import com.emep.zaixian.service.UserFacade;

/** 
* @ClassName: SysUserFilter 
* @Description: 把用户的信息绑定到 user中去 
* @author com_emep_mpc
* @date 2016年8月29日 下午2:50:46 
*  
*/
public class SysUserFilter extends PathMatchingFilter {
	@Resource
	private UserFacade userFacade;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
//		request.setAttribute(Constants.CURRENT_USER,userFacade.getByUserName(username));
		request.setAttribute(Constants.CURRENT_USER, userFacade.findByUsername(username));
		return true;
	}
}
