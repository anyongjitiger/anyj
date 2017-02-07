<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
	<link rel="stylesheet" href="<c:url value='/resources/css/zhaohe.css'/>"/></link>
	<script src="<c:url value='/resources/lib/jquery-2.1.4.min.js' />"></script>
	<style type="text/css">
	#editor-trigger {
	height: 700px;
	}
	.container {
	width: 1024px;
	min-height:100%;
	margin: 0 auto;
	position: relative;
	background-color: whitesmoke;
	overflow-y:auto;
	}
	.jumpto div a{
		text-decoration:underline;
	}
	</style>
</head>
<body style='background: url(${pageContext.request.contextPath}/resources/img/bgPic.jpg) repeat-x;background-attachment:fixed;'>
    <div class='container'>
    	<p style="font-size:28px;text-align:center;margin-top:20px;margin-bottom:20px;padding-top:20px">${article.title}</p>
    	<div style = "font-size:16px;text-align:left;padding:20 30">${article.content}</div>
    	<div class='jumpto' style= "font-size:16px;padding: 0 30 20 30;">
	    	<div style="float:right">
		    	<c:choose>
				   <c:when test="${nextId==null}">
				   		<a href="#">没有下一篇</a>
				   </c:when>
				   <c:otherwise>
				   		<a href="/Article/article/${nextId}">下一篇</a>
				   </c:otherwise>
				</c:choose>
			</div>
			<div>
				<c:choose>
				   <c:when test="${prevId==null}">
				   		<a href="#">没有上一篇</a>
				   </c:when>
				   <c:otherwise>
				   		<a href="/Article/article/${prevId}">上一篇</a>
				   </c:otherwise>
				</c:choose>
			</div>
    	</div>
    </div>
</body>
</html>