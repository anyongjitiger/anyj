<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <title>博锋达科技（大连）有限公司</title>
    <link rel="stylesheet" href="<c:url value='/static/css/web.css' />"/></link>
    <link rel="Stylesheet" href="<c:url value='/static/css/smoothDivScroll.css' />"/></link>
    <script src="<c:url value='/static/lib/jquery-2.1.4.min.js' />"></script>
    <script src="<c:url value='/static/lib/easySlider.js' />"></script>
    <script src="<c:url value='/static/lib/jquery.ui.widget.js' />"></script>
    <script src="<c:url value='/static/lib/jquery.smoothDivScroll-1.0-min.js' />"></script>
    <script src="<c:url value='/static/lib/angular/angular.min.js' />"></script>
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/service/article_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/article_controller.js' />"></script>
</head>
<body ng-app="myApp">
<script>
    $(function () {
        $("#slider").easySlider({
            slideSpeed: 500,
            paginationSpacing: "15px",
            paginationDiameter: "12px",
            paginationPositionFromBottom: "20px",
            slidesClass: ".slides",
            controlsClass: ".controls",
            paginationClass: ".pagination"
        });
        $("div#makeMeScrollable").smoothDivScroll({
            autoScroll: "always",
            autoScrollDirection: "backandforth",
            autoScrollStep: 1,
            autoScrollInterval: 15,
            startAtElementId: "startAtMe",
            visibleHotSpots: "always"
        });
    });
</script>
<div class="top">
    <div class="left"><a href="/"></a></div>
</div>
<div class="header">
    <div class="headerinner">
        <ul class="headernav">
            <li><a href="/">首页</a></li>
            <li><a href="#">公司简介</a></li>
            <li><a href="#">产品展示</a></li>
            <li><a href="#">联系方式</a></li>
            <li><a href="#">关于我们</a></li>
        </ul>
    </div>
</div>
<div id="slider">
    <ul class="slides clearfix">
        <li><img class="responsive" src="<c:url value='/static/img/9.jpg' />"></li>
        <li><img class="responsive" src="<c:url value='/static/img/6.jpg' />"></li>
        <li><img class="responsive" src="<c:url value='/static/img/10.jpg' />"></li>
        <li><img class="responsive" src="<c:url value='/static/img/11.jpg' />"></li>
    </ul>
    <ul class="controls">
        <li><img src="<c:url value='/static/img/prev.png' />" alt="previous"></li>
        <li><img src="<c:url value='/static/img/next.png' />" alt="next"></li>
    </ul>
    <ul class="pagination">
        <li class="active"></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>

<div ng-controller="ArticleController as ctrl">
    <div class="mainbox">
        <div class="box1">
            <div class="title"><a
                    href="http://localhost:8083/test/User">公司简介<span>&nbsp;/&nbsp;About Us</span></a></div>
            <div class="conbox">
                　　博锋达科技（大连）有限公司成立于2015年，是大连市认定的高新技术企业，公司通过了ISO9001质量管理体系的认证，并于2015年通过ISO14001环境管理体...
                <a href="/guanyuzhaohe/gongsijianjie/index.html">详细>></a>
            </div>
        </div>
        <div class="box2">
            <div class="title">
                <a href="/Article/allNews" class="more" >more</a>
                <a href="/zixunzhongxin/index.html">企业动态<span>&nbsp;/&nbsp;Group Dynamics</span></a>
            </div>
            <div class="conbox">
                <ul class="pictxt_left">
                    <li>
                        <img src="<c:url value='/static/img/4.jpg' />" width="85" height="58"/>
                        <h3><a href="/zixunzhongxin/qiyedongtai/2016/1008/244.html">博锋达科技诚邀合作</a></h3>
                        <span>2016年11月20日公司安排明总</span>
                    </li>
                </ul>
                <div class="clr"></div>
                <ul class="newslist">
                    <li ng-repeat="new in news">
                        <span>{{new.date}}</span>
                        <div style="cursor: pointer" ng-click="openUrl(new)">{{new.title}}</div>
                        <!--<a href="{{'/bfd/user/getQYDT?title='+new.title}}">{{new.title}}</a>-->
                    </li>
                </ul>
            </div>
        </div>
        <div class="box3">
            <div class="title"><a href="/gongchenganli/index.html" class="more">more</a><a
                    href="/gongchenganli/index.html">工程案例<span>&nbsp;/&nbsp;Projects</span></a></div>
            <div class="conbox">
                <ul class="newslist">
                    <li><a href="/gongchenganli/kongdiaotongfenggongcheng/2012/1101/10.html">车间通风工程</a></li>
                    <li><a href="/gongchenganli/huanbaoxitonggongcheng/2014/0126/125.html">净化除尘系统</a></li>
                    <li><a href="/gongchenganli/huanbaoxitonggongcheng/2014/0126/127.html">自动控制工程</a></li>
                    <li><a href="/gongchenganli/huanbaoxitonggongcheng/2012/1217/75.html">净化系统工程</a></li>
                    <li><a href="/gongchenganli/xitongjiejuefangan/2013/0503/115.html">节能系统方案</a></li>

                </ul>
            </div>
        </div>
        <div class="clr"></div>
    </div>
</div>
<div class='wrapper_container showbox'>
    <div class="title">
        <a href="">产品展示<span>&nbsp;/&nbsp;About Us</span></a>
    </div>
    <div id="makeMeScrollable">
        <div class="scrollingHotSpotLeft"></div>

        <div class="scrollingHotSpotRight"></div>
        <div class="scrollWrapper">
            <div class="scrollableArea">
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0128.jpg' />" alt="Demo image" width="200" height=200" border="0"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0129.jpg' />" alt="Demo image" width="200" height="300"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0130.jpg' />" alt="Demo image" width="200" height="300"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0131.jpg' />" alt="Demo image" width="200" height="300" id="startAtMe"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0132.jpg' />" alt="Demo image" width="200" height="300"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0133.jpg' />" alt="Demo image" width="200" height="300"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0134.jpg' />" alt="Demo image" width="200" height="300"/></a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0135.jpg' />" alt="Demo image" width="200" height="300"/> </a>
                <a href="#"><img src="<c:url value='/static/img/zjzl/DSC_0136.jpg' />" alt="Demo image" width="200" height="300"/> </a>
            </div>
        </div>
    </div>
</div>
<div class="foot"> 辽ICP备10000000号 版权所有：博锋达科技（大连）有限公司</div>
</body>
</html>