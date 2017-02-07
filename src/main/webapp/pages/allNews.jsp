<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
	<link rel="stylesheet" href="<c:url value='/resources/css/zhaohe.css'/>"/></link>
	<script src="<c:url value='/resources/lib/jquery-2.1.4.min.js' />"></script>
	<script src="<c:url value='/static/angular/angular.min.js' />"></script>
    <script src="<c:url value='/static/js/service/allArticle_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/allArticle_controller.js' />"></script>
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
	</style>
</head>
<body ng-app="articleApp" style='background: url(${pageContext.request.contextPath}/resources/img/bgPic.jpg) repeat-x;background-attachment:fixed;'>
    <div class='container' ng-controller="allArticleController">
    	<p style="font-size:28px;text-align:center;margin-top:20px;margin-bottom:20px;padding-top:20px;">${article.title}</p>
    	<ul class="newslist">
            <li ng-repeat="new in news">
                <span style="padding-left:20px;padding-right:20px">{{new.date}}</span>
                <div style="cursor: pointer;padding-left:20px;padding-right:20px" ng-click="openUrl(new)">{{new.title}}</div>
            </li>
        </ul>
    </div>

</body>
</html>