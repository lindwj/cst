<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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

<link rel="stylesheet" href="<%=path%>/resource/css/tradeDetail.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/tradeDetail.js"></script>


</head>
<body>

<body>
	<%@include file="../../../../resource/include/header.jsp"%>

<div class="w960">
    <table class="bought_table mt20">
        <colgroup>
            <col class="baobei">
            <col class="price">
            <col class="quantity">
            <col class="item_operate">
            <col class="amount">
            <col class="trade_status">
            <col class="trade_operate">
        </colgroup>
        <tbody class="success_order">
        <tr class="order_hd">
            <td class="first">
                <span title="2015-09-19 16:05:15" class="dealtime">${orders.createDatestr}</span>
            </td>
            <td class="column" colspan="3"><span class="number last-item">订单号：<em>${orders.code}</em></span></td>
            <td style="padding-right:20px;text-align:right;" class="last" colspan="3">
            <c:if test="${orders.status == 9}">
            <a class="orange_color"
                                                                                         href="<%=path%>/orders/ordersDelete.do?code=${orders.code}">取消订单</a>
                                                                                         </c:if>
            </td>
        </tr>
        <tr>
            <td class="order_infor" colspan="7">
                <dl>
                    <dt>
                        <!--<img src="/Public/Pc/images/icon/!.png"><p>订单状态：</p>-->
                        <i class="am-icon-warning am-icon-sm">
                            订单状态：
                        </i>
                    </dt>
                    <dd>
                    <c:if test="${orders.status == 9}">
                    <p>订单提交，<strong>待付款</strong> <a class="pay_btn" 
                                                        href="<%=path%>/orders/ordersPay.do?code=${orders.code}"
                                                        target="_blank">去支付</a> 支付方式：支付宝支付</p>

                        <p>请尽快付款，超时将取消订单</p>
                    </c:if>
     			<c:if test="${orders.status == 1}">已付款（配货中）</c:if>
     			<c:if test="${orders.status == 2}">已签收</c:if>
                <c:if test="${orders.status == -1}">已取消</c:if>    
                        
                    </dd>
                </dl>
            </td>
        </tr>
        </tbody>
    </table>


    <div class="order_detail_box">
        <div class="prod_title">
            <p class="w960">订单详情</p>
        </div>
        <div class="order_detail_div">
            <p class="infor_p"><span>收货地址：</span>${orders.receiveAddress }&nbsp;&nbsp;${orders.receiveMobile } </p>

            <ul class="tr_ul tab_head mt10">
                <li class="column_1">&nbsp;</li>
                <li class="column_2">商品信息</li>
                <li class="column_3"></li>
                <li class="column_4">单价（元）</li>
                <li class="column_5">数量</li>
                <li class="column_6">操作</li>
            </ul>
            <p class="shop_name ml0"><span><img src="<%=path%>/resource/images/cart/postAddr.png">由&nbsp;${orders.shopName}&nbsp;配送</span>
            </p>

            <div class="checkbox">
             <c:if test="${! orders.productUuid.equals('0')}">
                <div class="prod_list">
                    <ul class="tr_ul">
                        <li class="column_1">&nbsp;</li>
                        <li class="column_2"><a href="<%=path%>/product/productDetail.do?productUuid=${orders.productUuid}"><img
                                src="<%=path%>/resource/upload/product/${orders.pic}">${orders.subject}</a>
                        </li>
                        <li class="column_3"></li>
                        <li class="column_4"><span class="orange_color">￥${orders.price}</span></li>
                        <li class="column_5">${orders.capacity}</li>
                    </ul>
                </div>
                </c:if>
                
                <c:if test="${orders.productUuid.equals('0')}">
                <c:forEach var="ordersDetail" items="${orders.ordersDetailList}">
                <div class="prod_list">
                    <ul class="tr_ul">
                        <li class="column_1">&nbsp;</li>
                        <li class="column_2"><a href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}"><img
                                src="<%=path%>/resource/upload/product/${ordersDetail.pic}">${ordersDetail.productName}</a>
                        </li>
                        <li class="column_3"></li>
                        <li class="column_4"><span class="orange_color">￥${ordersDetail.price}</span></li>
                        <li class="column_5">${ordersDetail.capacity}</li>
                    </ul>
                </div>
                </c:forEach>
                </c:if>
            </div>
            <div class="infor_detail">
                <p><span>合   计</span><i class="am-icon-cny">${orders.totalFromBdh}</i></p>

                <p><span>配送费</span>￥0</p>

                <p><span>支付方式</span><em class="orange_color">支付宝支付</em></p>
            </div>
            <div style="background:none;" class="total_prod">
                <p>共计支付：<em class="am-icon-cny orange_color">${orders.totalFromBdh}</em></p>
            </div>


        </div>
        <!-- order_detail_div -->

    </div>
</div>


	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>


	<script>
		$(function() {
			TradeDetail.init();
		});
	</script>
</body>
</html>
