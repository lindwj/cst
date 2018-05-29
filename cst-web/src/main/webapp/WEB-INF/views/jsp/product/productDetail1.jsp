<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html class="no-js">
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${webTitle }</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<!-- <meta http-equiv="Cache-Control" content="no-siteapp" /> -->
<link rel="icon" type="image/png"
	href="<%=path%>/resource/images/favicon.png">
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">

<link rel="stylesheet" href="<%=path%>/resource/css/productDetail.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/parabola.js"></script>
<script src="<%=path%>/resource/scripts/productDetail.js"></script>

<script type="text/javascript">
function sbm(){
	var showCapacity=$("#showCapacity").val();
	var capacity =$("#column_num").val();
	
	if(ProductDetail.isInteger(showCapacity) && ProductDetail.isInteger(capacity)){
		if(capacity>showCapacity){
			ProductDetail.popModal("库存不足");
			return;
		}
	}
	
	
	$("#buyForm").submit();
}

function cart(obj){
	
	var showCapacity=$("#showCapacity").val();
	var capacity =$("#column_num").val();
	
	if(ProductDetail.isInteger(showCapacity) && ProductDetail.isInteger(capacity)){
		if(capacity>showCapacity){
			ProductDetail.popModal("库存不足");
			return;
		}
	}
	
	var productUuid =$("#productUuid").val();
	
	jQuery.ajax({
		  url: "<%=path%>/cart/cartAddEdit.do",
						cache : false,
						data : "num=" + capacity+"&productUuid=" + productUuid,
						success : function(data) {
							if(1==data){
								ProductDetail.moveIng(obj);
							}
							
						}
					});
}
</script>

</head>
<body>
	<%@include file="../../../../resource/include/header.jsp"%>

<form name="buyForm" id="buyForm" method="post"
		action="<%=path%>/orders/ordersAddEditIni.do">
		
	<input id="productUuid" name="productUuid" type="hidden"
		value="${product.productUuid}" />
	<input id="showCapacity"  type="hidden"
		value="${showCapacity }" />

	<div class="fly_item" id="flyItem">
		<a
			class="am-icon-btn am-icon-shopping-cart am-active amz-cart-wrapper"
			title="购物车" href="#" style="background-color: #c81623; color: #fff;"></a>
	</div>

	<p class="current_location w960">
		<!-- <a href="/">首页</a> &gt;
		<spa>蒲江红阳猕猴桃</spa> -->
	</p>

	<div class="focus_img clearfix w960">

		<!-- slider -->
		<div class="slider">
			<ul>
				<c:forEach items="${productPicList}" var="img">
					<li style="display: none;"><img
						src="<%=path%>/resource/upload/productPic/${img.picUrl}" /></li>
				</c:forEach>
			</ul>
			<ol>
				<c:forEach items="${productPicList}" var="img">
					<li class=""><img
						src="<%=path%>/resource/upload/productPic/${img.picUrl}" /></li>
				</c:forEach>
			</ol>
		</div>
		<!-- goods_detail -->
		<div class="goods_detail">
			<p class="tit">${product.name}</p>

			<div class="price">
				<span class="orange_color">商城价：<i>¥ <l id="price">${product.price }</l>
				</i></span> <em>市场价：￥ <l id="old_price">${product.costPrice }</l>
				</em>
			</div>

			<p class="mt20">选择规格：</p>

			<p class="btn mt10">
				<c:forEach items="${productAttributeList}" var="atr">
					<span class="active" spec_id="2782" old_price="" price="">${atr.name}:${atr.value}${atr.unit}</span>
				</c:forEach>

			</p>


			<shiro:guest>
				<p>
				
					<a href="<%=path%>/goodsAddress/goodsAddressListPage.do" style="font-weight: bold;">请维护收货地址。</a>
				</p>
			</shiro:guest>
			<shiro:authenticated>
				<c:choose>
					<c:when test="${shopName == null || shopName == ''}">
						<p>
							<a href="<%=path%>/goodsAddress/goodsAddressListPage.do" style="font-weight: bold;">请维护收货地址。</a>
						</p>
					</c:when>
					<c:otherwise>
						<p>
						<a href="<%=path%>/goodsAddress/goodsAddressListPage.do" style="font-weight: bold;">
						<img src="<%=path%>/resource/images/cart/postAddr.png">由&nbsp;${shopName}&nbsp;配送</a>
						</p>
						<p class="btn mt10">
							<span class="active">库存：${showCapacity}</span>
						</p>
					</c:otherwise>
				</c:choose>

			</shiro:authenticated>

			<div class="count_div count_div02">

				<span class="sub_btn"
					onclick="javascript:ProductDetail.subWareBybutton()">-</span> <input
					type="text" id="column_num" name="capacity" value="1"> <span
					class="add_btn"
					onclick="javascript:ProductDetail.addWareBybutton()">+</span>
				<l onclick="">
				<a href="javascript:void(0)" onclick="cart(this)" class="btn_totalnew">加入购物车</a></l>
				<l onclick="">
				<a href="javascript:void(0)" onclick="sbm()" class="btn_total">立即购买</a></l>
			</div>
		</div>

	</div>

</form>
	<div class="w960">
		<p class="hot_title">推荐商品</p>
		<ul id="hot_prod_list" class="hot_prod_list">
			<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2494">
					<img
					src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150813/55cc5ff316178.jpg">
					<p>百合干</p>
					<p>
						<span class="orange_color">￥46.90</span><s>￥56.00</s>
					</p>
			</a></li>
			<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2527">
					<img
					src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150815/55cf0b710fc83.jpg">
					<p>皖南一级笋干</p>
					<p>
						<span class="orange_color">￥58.00</span><s>￥79.00</s>
					</p>
			</a></li>
			<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2511">
					<img
					src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150813/55cc6f4820392.jpg">
					<p>有机糙米</p>
					<p>
						<span class="orange_color">￥8.80</span><s>￥12.90</s>
					</p>
			</a></li>
			<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2446">
					<img
					src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">
					<p>青海贵德土蜂蜜</p>
					<p>
						<span class="orange_color">￥128.00</span><s>￥158.00</s>
					</p>
			</a></li>
			<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2520">
					<img
					src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150813/55cc73356adf0.jpg">
					<p>有机玉米碴</p>
					<p>
						<span class="orange_color">￥15.80</span><s>￥19.90</s>
					</p>
			</a></li>
		</ul>
	</div>

	<div class="w960 clearfix">
		<div class="hot_sale">
			<h3>人气商品</h3>
			<ul id="recommandList" class="hot_prod_list">
				<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2541">
						<img
						src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">

						<p>咸鸭蛋（切开即食）（9月20日发货）</p>

						<p>
							<span class="orange_color">￥19.90</span><s>￥22.80</s>
						</p>
				</a></li>
				<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2529">
						<img
						src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">

						<p>土白菜干</p>

						<p>
							<span class="orange_color">￥25.80</span><s>￥32.00</s>
						</p>
				</a></li>
				<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2526">
						<img
						src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">

						<p>精品笋衣</p>

						<p>
							<span class="orange_color">￥15.90</span><s>￥19.90</s>
						</p>
				</a></li>
				<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2542">
						<img
						src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">

						<p>老冰糖</p>

						<p>
							<span class="orange_color">￥9.90</span><s>￥13.80</s>
						</p>
				</a></li>
				<li><a href="?m=Home&amp;c=Goods&amp;a=detail&amp;id=2477">
						<img
						src="http://oss-cn-beijing.aliyuncs.com/app-ydyw/uploads/20150812/55cac414c4c8f.jpg">

						<p>野地七彩花生</p>

						<p>
							<span class="orange_color">￥36.80</span><s>￥42.00</s>
						</p>
				</a></li>
			</ul>
		</div>
		<div class="spec">
			<p class="hot_title">
				<span class="active" onclick="show_con(1)" id="count">商品介绍</span>
			</p>

			<div id="cons">${product.description}</div>
			<div style="display: none;" id="check_cons"></div>
		</div>
	</div>

	<br>
	<br>
	<br>
	<br>
	<br>

	<%@include file="../../../../resource/include/toolbar.jsp"%>
	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>


	<script>
		$(function() {
			ProductDetail.init();
		});
	</script>
</body>
</html>
