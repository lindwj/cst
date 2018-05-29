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

<link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
    <link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/components/header/footer.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/css/indexnew.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/components/iconfont/iconfont.css"/>
<script src="<%=path%>/resource/components/jquery/jquery-3.0.0.min.js"></script>
<script src="<%=path%>/resource/components/jquery/jquery.fly.min.js"></script>
<script src="<%=path%>/resource/components/jquery/unslider-min.js"></script>
<script src="<%=path%>/resource/scripts/indexnew.js"></script>

<script type="text/javascript">
function cart(obj){
	
	var capacity =1;
	var productUuid =$(obj).attr("v");
	
	jQuery.ajax({
		  url: "<%=path%>/cart/cartAddEdit.do",
						cache : false,
						data : "num=" + capacity+"&productUuid=" + productUuid,
						success : function(data) {
							if(1==data){
								mv(obj);
							}
							
						}
					});
}
</script>

</head>
<body>
	<%@include file="../../../resource/include/header.jsp"%>
	
	<!-- slider -->
<div class="banner">
    <div class="unslider">
        <ul>
        
        <li><a href="javascript:void(0)"><img src="<%=path%>/resource/images/index/1.jpg"></a></li>
            <li><a href="javascript:void(0)"><img src="<%=path%>/resource/images/index/2.jpg"></a></li>
            <li><a href="javascript:void(0)"><img src="<%=path%>/resource/images/index/3.jpg"></a></li>
                   <li><a href="javascript:void(0)"><img src="<%=path%>/resource/images/index/4.jpg"></a></li>
            <li><a href="javascript:void(0)"><img src="<%=path%>/resource/images/index/5.jpg"></a></li>
            <li><a href="javascript:void(0)"><img src="<%=path%>/resource/images/index/6.jpg"></a></li>
            
        <c:forEach var="indexBanner" items="${indexBannerList}">
        
        <li><a href="${indexBanner.productUrl}"><img src="<%=path%>/resource/upload/productIndex/${indexBanner.bannerUrl}"></a></li>
        </c:forEach>
        
        </ul>
    </div>
</div>


	

<div class="content">
    <div class="section_header">
        <h1></h1>
    </div>
<%@include file="../../../resource/include/toolbar.jsp"%>
    
    

<c:forEach var="item" items="${pList}">


<ul class="item" id="ls">

        <div class="titlebar">
            <h2>${item.parameterName}</h2>
        </div>
        
    <c:forEach var="ptemp" items="${item.productList}">
    
    <li id="${ptemp.productUuid}" <c:if test="${item.parameterId != 10}"> class="item-pic" </c:if>>
            <div>
                <a href="<%=path%>/product/<c:if test="${item.parameterId != 10}">productDetail</c:if><c:if test="${item.parameterId == 10}">preProductDetail</c:if>.do?productUuid=${ptemp.productUuid}" target="_blank"><img src="${ptemp.pic}"></a>
                <p class="orange">${ptemp.name}</p>
                <p><span class="orange_color">￥${ptemp.price}</span></p>
                <p class="bar_p"><a><img src="<%=path%>/resource/images/index/add_shop.png" onclick="cart(this)" class="add_shop" v="${ptemp.productUuid}" id="img_${ptemp.productUuid}"></a></p>
            </div>
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
	<%@include file="../../../resource/include/footer.jsp"%>
</body>
</html>
