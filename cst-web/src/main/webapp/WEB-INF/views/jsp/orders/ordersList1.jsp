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

<link rel="stylesheet" href="<%=path%>/resource/css/tradeList.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/trade.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>

<script type="text/javascript">

function chk(obj){
	
	$("#status").val($(obj).attr("st"));
	$("#shareForm").submit();
}

</script>

</head>
<body>

<body>
	<%@include file="../../../../resource/include/header.jsp"%>

<form name="shareForm" id="shareForm" method="post"
		action="<%=path%>/orders/ordersListPage.do">
		<input type="hidden" name="status" id="status" value="${orders.status}" />
<div class="w960 prod_all">
    <ul id="head-menu" class="sub_nav">
        <li  <c:if test="${orders.status == 100}">class="on"</c:if> ><a href="javascript:void(0);" st="100" onclick="chk(this);">全部订单</a></li>
        <li  <c:if test="${orders.status == 9}"> class="on"</c:if>><a href="javascript:void(0);" st="9" onclick="chk(this);">待付款 <span class="orange_color"></span></a>
        </li>
        <li  <c:if test="${orders.status == 1}">class="on"</c:if>><a href="javascript:void(0);" st="1" onclick="chk(this);">已付款（配货中） <span class="orange_color"></span></a>
        </li>
        <li  <c:if test="${orders.status == 2}">class="on"</c:if>><a href="javascript:void(0);" st="2" onclick="chk(this);">已签收<span class="orange_color"></span></a>
        </li>
    </ul>

    <div id="tradeEmptyDiv" class="empty_div order_no_div" style="height: 60px; padding-top: 40px;display: none;">
        <dl>
            <dt><i class="am-icon-file-text-o am-icon-lg" style="font-size: 150%;"></i></dt>
            <dd>还没有订单，列表为空<br>马上 <a href="/index">去购物！</a></dd>
        </dl>
    </div>

    <table class="bought_table" id="sectionDiv" style="">
        <colgroup>
            <col class="baobei">
            <col class="price">
            <col class="quantity">
            <col class="item_operate">
            <col class="amount">
            <col class="trade_status">
            <col class="trade_operate">
        </colgroup>
        <thead>
        <tr class="col_name">
            <th class="baobei">商品信息</th>
            <th class="price"></th>
            <th class="quantity">单价（元）</th>
            <th class="item_operate">数量</th>
            <th class="amount">实付款</th>
            <th class="trade_status">订单状态</th>
            <th class="trade-operate">操作</th>
        </tr>
        </thead>
        
        <tbody class="success_order">
        <tr class="sep_row">
            <td colspan="7"></td>
        </tr>
        <tr class="sep_row">
            <td colspan="7"></td>
        </tr>
        <tr class="tishi">
            <td colspan="7"></td>
        </tr>
        
        
        <c:forEach var="item" items="${ordersList}">
        <tr class="order_hd split_order">
            <td class="first">
                <span class="dealtime">${item.createDatestr}</span>
            </td>
            <td class="column" colspan="3"><span class="number last-item">订单号：<em>${item.code}</em></span></td>
            
            <td class="last" colspan="3">由&nbsp;${item.shopName}&nbsp;配送 &nbsp;&nbsp;  <c:if test="${item.status == 9}">
            <a class="payment"  href="<%=path%>/orders/ordersDelete.do?code=${item.code}">取消订单</a>
                                                                                         </c:if></td>
            
        </tr>
        <c:if test="${! item.productUuid.equals('0')}">
        <tr class="order_bd">
            <td class="baobei">
                <a class="pic" href="<%=path%>/product/productDetail.do?productUuid=${item.productUuid}"><img
                        src="<%=path%>/resource/upload/product/${item.pic}"></a>
                <a href="<%=path%>/product/productDetail.do?productUuid=${item.productUuid}"><p>${item.subject}</p></a>
            </td>
            <td class="price"><a href="#"></a></td>
            <td class="quantity">￥${item.price}</td>
            <td class="item_operate">${item.capacity}</td>
            <td  class="amount"><span class="orange_color">￥${item.totalFromBdh}</span></td>
            <td  class="trade_status">
     			<c:if test="${item.status == 100}">全部订单</c:if>
     			<c:if test="${item.status == 9}">待付款</c:if>
     			<c:if test="${item.status == 1}">已付款（配货中）</c:if>
     			<c:if test="${item.status == 2}">已签收</c:if>
     			<c:if test="${item.status == -1}">已取消</c:if>
            </td>
            <td class="price">
                <c:if test="${item.status == 9}">
                <a class="payment go_pay" target="_blank"
                   href="<%=path%>/orders/ordersPay.do?code=${item.code}">去支付</a>
                   </c:if>
                <a class="payment" href="<%=path%>/orders/ordersDetail.do?code=${item.code}">去查看</a>
            </td>
        </tr>
        </c:if>
        
        <c:if test="${item.productUuid.equals('0')}">
        <c:forEach var="ordersDetail" items="${item.ordersDetailList}">
        <tr class="order_bd">
            <td class="baobei">
                <a class="pic" href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}"><img
                        src="<%=path%>/resource/upload/product/${ordersDetail.pic}"></a>
                <a href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}"><p>${ordersDetail.productName}</p></a>
            </td>
            <td class="price"><a href="#"></a></td>
            <td class="quantity">￥${ordersDetail.price}</td>
            <td class="item_operate">${ordersDetail.capacity}</td>
            
            <td  class="amount"><span class="orange_color">￥${ordersDetail.price*ordersDetail.capacity}</span></td>
            <td  class="trade_status">
     			
            </td>
            <td class="price">
                
            </td>
        </tr>
        </c:forEach>
        
        <tr class="order_bd">
        <td class="baobei">
            </td>
            <td class="price"><a href="#"></a></td>
            <td class="quantity"></td>
            <td class="item_operate">合计：</td>
        
            <td  class="amount"><span class="orange_color">￥${item.totalFromBdh}</span></td>
            <td  class="trade_status">
     			<c:if test="${item.status == 100}">全部订单</c:if>
     			<c:if test="${item.status == 9}">待付款</c:if>
     			<c:if test="${item.status == 1}">已付款（配货中）</c:if>
     			<c:if test="${item.status == 2}">已签收</c:if>
     			<c:if test="${item.status == -1}">已取消</c:if>
            </td>
            <td class="price">
                <c:if test="${item.status == 9}">
                <a class="payment go_pay" target="_blank"
                   href="<%=path%>/orders/ordersPay.do?code=${item.code}">去支付</a>
                   </c:if>
                <a class="payment" href="<%=path%>/orders/ordersDetail.do?code=${item.code}">去查看</a>
            </td>
        </tr>
        </c:if>
        
        </c:forEach>
        
        
        </tbody>
    </table>

<%@include file="../../../../resource/include/innerNavigation.jsp"%>
</div>
</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>

	
	<script>
		$(function() {
			Trade.init();
		});
	</script>
</body>
</html>
