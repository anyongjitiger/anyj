<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value='/resources/css/zhaohe.css'/>"/></link>
	<script src="<c:url value='/resources/lib/jquery-2.1.4.min.js' />"></script>
	<script src="<c:url value='/static/angular/angular.min.js' />"></script>
    <script src="<c:url value='/static/js/service/allArticle_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/allArticle_controller.js' />"></script>
	<script>
    	var op = "${op}";
    </script>
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
	nav {
        position: relative;
        width:100%;
        height: 50px;
    }
    .pagination {
        right: 0px;
        position: absolute;
        top: -30px;
    }
    nav li {
        cursor: pointer;
    }
	</style>
</head>
<body ng-app="articleApp" style='background: url(${pageContext.request.contextPath}/resources/img/bgPic.jpg) repeat-x;background-attachment:fixed;'>
    <div class='container' ng-controller="allArticleController" ng-show="showAll">
    	<p style="font-size:28px;text-align:center;margin-top:20px;margin-bottom:20px;padding-top:20px;">${article.title}</p>
    	<ul class="newslist">
            <li ng-repeat="new in news">
                <span style="padding-left:20px;padding-right:20px">{{new.date}}</span>
                <div style="cursor: pointer;padding-left:20px;padding-right:20px" ng-click="openUrl(new)">{{new.title}}</div>
            </li>
        </ul>
    </div>
	<div class='container' ng-controller="editArticleController" ng-show="showEdit">
    	<table class="table table-bordered" style = 'margin-top:20px;'>
	        <tr>
	            <th>index</th>
	            <th>title</th>
	            <th>action</th>
	            <!-- <th ng-repeat="(x,y) in items[0]">{{ x }}</th> -->
	        </tr>
	        <tr ng-repeat="x in items">
	            <td>{{ $index + 1 }}</td>
	            <td ng-bind="x.title"></td>
	            <td><button ng-click="edit($index,x.id);">edit</button>
	            <button ng-click="delete($index,x.id);">delete</button>
	            </td>
	        </tr>
	    </table>
	    <nav>
	        <ul class="pagination">
	            <li>
	                <a ng-click="Previous()">
	                    <span>上一页</span>
	                </a>
	            </li>
	            <li ng-repeat="page in pageList" ng-class="{active: isActivePage(page)}" >
	                <a ng-click="selectPage(page)" >{{ page }}</a>
	            </li>
	            <li>
	                <a ng-click="Next()">
	                    <span>下一页</span>
	                </a>
	            </li>
	        </ul>
	    </nav>
	</div>
</body>
</html>