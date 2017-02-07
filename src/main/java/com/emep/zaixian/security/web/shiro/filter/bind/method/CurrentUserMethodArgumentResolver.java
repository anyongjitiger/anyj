package com.emep.zaixian.security.web.shiro.filter.bind.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.emep.zaixian.security.web.shiro.filter.SysUserFilter;
import com.emep.zaixian.security.web.shiro.filter.bind.annotation.CurrentUser;

/**
 * @ClassName: CurrentUserMethodArgumentResolver
 * @Description: 根据注解来给有注解的参数赋值，其中参数已经绑定在user上
 * @see SysUserFilter
 * @author com_emep_mpc
 * @date 2016年8月29日 下午2:49:34
 * 
 */
public class CurrentUserMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() {
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {// 判断参数是否有注解，有注解才绑定参数
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter
				.getParameterAnnotation(CurrentUser.class);
		return webRequest.getAttribute(currentUserAnnotation.value(),
				NativeWebRequest.SCOPE_REQUEST);// 返回绑定的值
	}
}
