<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/web.css">
	<title>Insert title here</title>
</head>
<body
	style='background: url(${pageContext.request.contextPath}/static/img/bgPic.jpg) repeat-x'>
	<div style='width: 512px; margin: 100px auto; position: relative;'>
		<p style='padding: 76px 76px 0 76px; font-size: 18px; font-family: "SimHei", Arial;'>账户登录</p>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div style="padding-left: 75px; display: inline-block; width: 360px">
				<input type="text" name="name"
					style="width: 360px; height: 40px; margin-bottom: 15px; margin-top: 15px;"
					placeholder="用户名" value="<shiro:principal/>">
			</div>
			<div style="padding-left: 75px; display: inline-block; width: 360px">
				<input type="password" name="pwd"
					style="width: 360px; height: 40px;" placeholder="密码">
			</div>
			<input class="button blue-button"
				style="font-size: 14px; width: 67%; height: 40px; margin-top: 20px; margin-left: 86px; padding-top: 0.2em"
				type="submit" value='登录'>
		</form>
		${error}
	</div>
</body>
</html>