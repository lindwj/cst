<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../resource/include/tag.jsp"%>

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

<link rel="stylesheet" href="<%=path%>/resource/css/checkout.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/checkout.js"></script>
<script type="text/javascript">
	function sbm() {

		$("#alipayto").submit();
	}
</script>
</head>
<body>
<body>
	<%@include file="../../../../resource/include/header.jsp"%>


	<div class="prod_title">
		<p class="w960">当前收货地址</p>
	</div>

	<div class="w960 check_address">
		<ul>
			<li class="on"><i></i><span>${orders.receiveAddress }&nbsp;&nbsp;${orders.receiveMobile }</span>
			</li>
		</ul>
	</div>

	<div class="w960 prod_all">
		<ul class="tr_ul tab_head">
			<li class="column_1">&nbsp;</li>
			<li class="column_2">商品信息</li>
			<li class="column_3"></li>
			<li class="column_4">单价（元）</li>
			<li class="column_5">数量</li>
			<!--<li class="column_6">操作</li>-->
		</ul>

		<div class="shop_list">
			<p class="shop_name ml0">
				<span><img src="<%=path%>/resource/images/cart/postAddr.png">
					由${orders.shopName }配送</span>
			</p>

			<div class="checkbox">
			<c:forEach var="ordersDetail" items="${orders.ordersDetailList}">
				<div class="prod_list ">
					<ul class="tr_ul ">
						<li class="column_1"></li>
						<li class="column_2"><a
							href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}">
								<img src="<%=path%>/resource/upload/product/${ordersDetail.pic}"
								width="50px" height="50px" />
						</a>${ordersDetail.productName}</li>
						<li class="column_3"></li>
						<li class="column_4"><span class="orange_color">￥${ordersDetail.price}</span></li>
						<li class="column_5">${ordersDetail.capacity}</li>
					</ul>
				</div>
				</c:forEach>
			</div>
		</div>


			<div class="infor_detail">
				<p>
					<span> <!-- 共 <em class="orange_color">4</em> 件商品， -->合 计
					</span>￥${orders.totalFromBdh }
				</p>

				<p>
					<span>配送费</span>￥0
				</p>

				<p>
					<span>支付方式</span><em class="orange_color">支付宝支付</em>
				</p>
			</div>

			<div style="background: none;" class="total_prod">
				<p>
					<!-- 共计 <em class="orange_color">4</em> 件商品 -->
					实付款：<em class="orange_color">￥<font class="sum_total">${orders.totalFromBdh }</font></em>
				</p>

				<form id="alipayto" name="alipayto"
					action="<%=path%>/resource/jsp/alipayb/alipayto.jsp" method="post">
					<input type="hidden" name="orderNo" value="${orders.code}" /> <input
						type="hidden" name="orderTatol" value="${orders.totalFromBdh}" />

					<!--<s:hidden name="royalty" value="george.mo@links-e.com^%{royalty}^分润"></s:hidden> <input type="hidden" name="seller_email" value="%{seller_email}"/> -->
					<input type="hidden" name="subject" value="合并支付" />

				</form>

				<a id="btn_check" class="btn_total" href="javascript:void(0)"
					onclick="sbm()">支付</a>
			</div>


	</div>

	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../../resource/include/footer.jsp"%>

	<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1"
		id="wait-pay-modal">



		<div class="am-modal-dialog">
			<div id="modal-title" class="am-modal-hd">
				下单成功
				<!-- <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a> -->
			</div>
			<!-- <div class="am-modal-bd">订单已生效</div> -->
			<div class="fashlog">
				<p id="afterPayForm" class="btn" style="">
					<button id="afterPayBtn" class="on goOrder">已完成支付</button>
					<button id="questionBtn" class="goOrder">支付遇到问题了</button>
				</p>
			</div>
			<br /> <br />
		</div>
	</div>

	<script>
		$(function() {
			Checkout.init();
		});
	</script>
</body>
</html>
