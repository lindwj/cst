<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../../../resource/include/tag.jsp"%>

<!DOCTYPE HTML>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${webTitle }</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<link rel="icon" type="image/png"
	href="<%=path%>/resource/images/favicon.png">
    <link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">
    <link rel="stylesheet" href="<%=path%>/resource/css/app.css">


<link rel="stylesheet" href="<%=path%>/resource/css/index.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>


</head>
<body>
	<%@include file="../../../resource/include/header.jsp"%>
	<div class="banner-container">
    <div data-am-widget="slider" class="am-slider am-slider-a5" data-am-slider='{directionNav:false}'>
        <ul class="am-slides">
            <li>
                <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg">
            </li>
            <li>
                <img src="http://s.amazeui.org/media/i/demos/bing-2.jpg">
            </li>
            <li>
                <img src="http://s.amazeui.org/media/i/demos/bing-3.jpg">
            </li>
            <li>
                <img src="http://s.amazeui.org/media/i/demos/bing-4.jpg">
            </li>
        </ul>
    </div>
</div>

<div class="w960">
    <div class="section_header">
        <h1>朋来先敬·寻味之旅</h1>
    </div>
    
    <div data-am-widget="titlebar"
         class="am-titlebar am-titlebar-default am-margin-zero am-titlebar-multi am-no-layout azshop-titlebar">
        <h2 class="am-titlebar-title">预定商品</h2>
    </div>
    <ul class="product_ul">
        <li>
            <div>
                <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2594">
                    <img src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">
                </a>
            </div>
            <!--onclick="addCart(2594,2805,1,this);"-->
            <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2594">
                <p>【江苏固城湖】</p>

                <p class="orange">固城湖大闸蟹</p>

                <p><span class="orange_color">￥149.00</span><s> ￥188.00</s></p>
            </a>
        </li>
        <li>
            <div>
                <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2574">
                    <img src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">
                </a>
            </div>
            <!--onclick="addCart(2574,2782,1,this);"-->
            <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2574">
                <p>【四川蒲江】</p>

                <p class="orange">蒲江红阳猕猴桃</p>

                <p><span class="orange_color">￥59.00</span><s> ￥79.00</s></p>
            </a>
        </li>
        <li>
            <div>
                <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2594">
                    <img src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">
                </a>
            </div>
            <!--onclick="addCart(2594,2805,1,this);"-->
            <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2594">
                <p>【江苏固城湖】</p>

                <p class="orange">固城湖大闸蟹</p>

                <p><span class="orange_color">￥149.00</span><s> ￥188.00</s></p>
            </a>
        </li>
        <li>
            <div>
                <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2574">
                    <img src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">
                </a>
            </div>
            <!--onclick="addCart(2574,2782,1,this);"-->
            <a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2574">
                <p>【四川蒲江】</p>

                <p class="orange">蒲江红阳猕猴桃</p>

                <p><span class="orange_color">￥59.00</span><s> ￥79.00</s></p>
            </a>
        </li>
    </ul>

<c:forEach var="item" items="${pList}">

<div data-am-widget="titlebar"
         class="am-titlebar am-titlebar-default am-margin-zero am-titlebar-multi am-no-layout azshop-titlebar">
        <h2 class="am-titlebar-title">${item.parameterName}</h2>
    </div>
    <ul class="product_ul">
    <c:forEach var="ptemp" items="${item.productList}">
    <li>
            <div>
                <a href="<%=path%>/product/productDetail.do?productUuid=${ptemp.productUuid}">
                    <img src="<%=path%>/resource/upload/product/${ptemp.pic}">
                </a>

            </div>
            <!--onclick="addCart(2594,2805,1,this);"-->
            <a href="<%=path%>/product/productDetail.do?productUuid=${ptemp.productUuid}">

                <p class="orange">${ptemp.name}</p>

                <p><span class="orange_color">￥${ptemp.price}</span><s> ￥${ptemp.costPrice}</s></p>
            </a>
        </li>
    </c:forEach>
    </ul>
</c:forEach>

</div>

	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../resource/include/toolbar.jsp"%>
	<%@include file="../../../resource/include/footer.jsp"%>

</body>
</html>
