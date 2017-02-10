<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <title>博锋达科技（大连）有限公司</title>
    <link rel="stylesheet" href="<c:url value='/static/css/zhaohe.css' />"/></link>
    <link rel="stylesheet" href="<c:url value='/static/dist/css/wangEditor.min.css' />"></link>
    <style type="text/css">
        #editor-trigger {
            height: 700px;
        }
        .container {
            width: 1024px;
            margin: 0 auto;
            position: relative;
        }
        .popup{
        	font-size:28px;
        	background-color:#565656;
        	color:#fff;
        	position:absolute;
        	left:50%; 
			top:50%; 
        	width:200px;
        	height:70px;
        	text-align:center;
			padding-top:27px;
        	z-index:9;
        	display:none;
        }
    </style>
    <script src="<c:url value='/static/lib/jquery-2.1.4.min.js' />"></script>
    <script src="<c:url value='/static/dist/js/wangEditor.js' />"></script>
    <script>
    	var articleId = "${article.id}";
    </script>
    <script src="<c:url value='/static/js/addNews.js' />"></script>
</head>
<body>
<div class="top">
    <div class="left"><a href="../index.html"></a></div>
</div>
<div class="header">
    <div class="headerinner">
        <ul class="headernav">
            <li><a href="../index.html">首页</a></li>
            <li><a href="#">公司简介</a></li>
            <li><a href="#">产品展示</a></li>
            <li><a href="#">联系方式</a></li>
            <li><a href="#">关于我们</a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div style="line-height:30px;display: inline-block;margin: 10px 10px">标题</div>
    <div style="display: inline-block;width: 960px"><input id='biaoti' type="text" style="height:25px; width: 960px" value="${article.title}"/></div>
    <div id="editor-container" class="container">
        <div id="editor-trigger">
        	<c:choose>
			   <c:when test="${article==null}">
			   		<p>请输入内容</p>
			   </c:when>
			   <c:otherwise>
			   		${article.content}
			   </c:otherwise>
			</c:choose>
        	<!-- <p>请输入内容</p> -->
       	</div>
    </div>
    <div id="btn1" class="button blue-button" style="font-size:14px;width:48%;height:25px;margin-top: 10px;margin-left: 20px">保  存</div>
    <div id="btn2" class="button blue-button" style="font-size:14px;width:48%;height:25px;margin-top: 10px">清  空</div>
</div>
<div class="popup">保存成功</div>
</body>
</html>