<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
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
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="<%=path%>/resource/images/favicon.png">
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">

<link rel="stylesheet" href="<%=path%>/resource/css/cart.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/buy.js"></script>

<style type="text/css">
label.error, label.tip {
	color: red;
}
</style>

<script type="text/javascript">
function sbm(){
	
	var flag=0;
	
$("input[id$='productUuid']").each(function () {
		
		
		var productUuid = $(this).val();
		
		var showCapacity=$("#"+productUuid+"showCapacity").val();
		var capacity =$("#"+productUuid+"column_num").val();
		
		
		if(buy.isInteger(showCapacity) && buy.isInteger(capacity)){
			if(capacity>showCapacity){
				buy.popModal($("#"+productUuid+"productName").val()+"库存不足");
				flag=1;
				return;
			}
		}
		
		var shopId = $("#shopId").val();
		if(shopId==0){
				buy.popModal("此地址不支持配送，请选择其他地址！");
				flag=1;
				return;
		}
		
		
		
});

if(0==flag){

$("#buyForm").submit();

}
	
}

function setObj(data,obg,productUuid) {
	var arr = jsonParse(data);
	//有匹配的专营店时 才修改值
	if (arr != null &&arr.shopId > 0 ) {
		
		$("#"+productUuid+"showCapacity").val(arr.capacity);
		
		$("#shopName").val(arr.shopName);
		$("#shopId").val(arr.shopId);
		$("#shopmc").text("由  "+arr.shopName+"  配送");
		
		var name =	$(obg).find("option:selected").attr("tname");
		var p = $(obg).find("option:selected").attr("tprovincestr");
		var c =$(obg).find("option:selected").attr("tcitystr");
		var d =$(obg).find("option:selected").attr("tdistrictstr");
		var s =$(obg).find("option:selected").attr("tstreetstr");
		var m = $(obg).find("option:selected").attr("tmobile");
		var a = $(obg).find("option:selected").attr("taddress");
		
		
		$("#receiveName").val(name);
		$("#receiveAddress").val(p+c+d+s+a);
		$("#receiveMobile").val(m);
	}else{
		buy.popModal("该地址不支持配送，请选择其他地址！");
		$("#"+productUuid+"showCapacity").val(0);
		
	}
}

function chg(obg){
	
	var goodsAddressUuid = $(obg).find("option:selected").attr("value");
	
	
	$("input[id$='productUuid']").each(function () {
		
		
		var productUuid = $(this).val();
		
		jQuery.ajax({
			  url: "<%=path%>/orders/getShop.do",
							cache : false,
							data : "goodsAddressUuid=" + goodsAddressUuid+"&productUuid=" + productUuid,
							success : function(data) {
								
								setObj(data,obg,productUuid);
								
							}
						});
		
    });
	
	
}

</script>
</head>
<body>

	<%@include file="../../../../resource/include/header.jsp"%>

<form name="buyForm" id="buyForm" method="post"
		action="<%=path%>/orders/ordersAddEditDetail.do">
		<div id="err" style="color: red">
				<c:if test="${param.errcode == 103 }">
					<h3>库存不足</h3>
				</c:if>
				<c:if test="${param.errcode == 104 }">
					<h3>此地址不支持配送，请选择其他地址！</h3>
				</c:if>
				<c:if test="${errcode == 105 }">
					<h3>部分商品没有库存！</h3>
				</c:if>
			</div>
	<input id="shopId" name="shopId" type="hidden"
		value="${shopId}" />
		
	<input id="receiveName" name="receiveName" type="hidden"
		value="${goodsAddress.name}" />
		<input id="receiveAddress" name="receiveAddress" type="hidden"
		value="${goodsAddress.provincestr}${goodsAddress.citystr}${goodsAddress.districtstr}${goodsAddress.streetstr}${goodsAddress.address}" />
		<input id="receiveMobile" name="receiveMobile" type="hidden"
		value="${goodsAddress.mobile}" />
		<input id="totalFromBdh" name="totalFromBdh" type="hidden"
		value="${orders.totalFromBdh}" />
		
		<input id="shopName" name="shopName" type="hidden"
		value="${shopName}" />
		
		
		<div class="prod_title">
			<p class="w960">选择收货地址</p>
		</div>

		<div class="w960 check_address" id="has-adderss" style="">
		<select name="goodsAddressUuid" id="goodsAddressUuid" onchange="chg(this)">
				<c:forEach var="item" items="${goodsAddressList}">
				<option value="${item.goodsAddressUuid}" taddress="${goodsAddress.address}" tname="${item.name}" tprovincestr="${item.provincestr}" tcitystr="${item.citystr}" tdistrictstr="${item.districtstr}" tstreetstr="${item.streetstr}" tmobile="${item.mobile}"
				<c:if test="${1 == item.isDefault}">selected="selected" </c:if>>
				${item.name}&nbsp;&nbsp;&nbsp;&nbsp;${item.provincestr}&nbsp;&nbsp;${item.citystr}&nbsp;&nbsp;${item.districtstr}&nbsp;&nbsp;${item.streetstr}&nbsp;&nbsp;${item.mobile}
				</option>
				</c:forEach>
		</select>
		</div>



		<div class="cart_list" style="display: block;" id="cartContent">
			<div class="prod_title">
				<div class="w960">
					<ul class="tr_ul">
						<li class="column_1">&nbsp;</li>
						<li class="column_2">商品信息</li>
						<li class="column_3"></li>
						<li class="column_4">单价（元）</li>
						<li class="column_5">数量</li>
					</ul>
				</div>
			</div>
			<div class="w960 prod_all" id="orderList">
			
			<div class="shop_list">
            <p class="shop_name"><span>
                <img src="<%=path%>/resource/images/cart/postAddr.png"><span id="shopmc">由&nbsp;${shopName }&nbsp;配送</span></span>
            </p>

            <div class="checkbox">
				<%int i=0; %>
				<c:forEach var="ordersDetail" items="${orders.ordersDetailList}">

                <div class="prod_list ">
                    <ul class="tr_ul ">
                        <li class="column_1"><input id="${ordersDetail.productUuid}productUuid" name="ordersDetailList[<%=i %>].productUuid" type="hidden" value="${ordersDetail.productUuid}" />
                        <input id="pic" name="ordersDetailList[<%=i %>].pic" type="hidden" value="${ordersDetail.pic}" />
                        <input id="${ordersDetail.productUuid}productName" name="ordersDetailList[<%=i %>].productName" type="hidden" value="${ordersDetail.productName}" />
                        <input id="price" name="ordersDetailList[<%=i %>].price" type="hidden" value="${ordersDetail.price}" />
                        <input id="${ordersDetail.productUuid}showCapacity"  type="hidden" value="${ordersDetail.showCapacity}" />
                        <input name="ordersDetailList[<%=i %>].capacity"  type="hidden" value="${ordersDetail.capacity}" />
                        </li>
                        <li class="column_2"><a href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}">
                        <img src="<%=path%>/resource/upload/product/${ordersDetail.pic}" width="50px" height="50px"/>
                        </a>${ordersDetail.productName}
                        </li>
                        <li class="column_3"></li>
                        <li class="column_4">￥<span class="orange_color" id="showprice">${ordersDetail.price}</span></li>
                        <li class="column_5">
                            <div class="count_div">
                                <input type="text" value="${ordersDetail.capacity}" disabled="disabled" id="${ordersDetail.productUuid}column_num" name="ordersDetailList[<%=i %>].capacity" class="column_num">
                            </div>
                        </li>
                    </ul>
                </div>
                
                 <%i=i+1; %>
                </c:forEach>
            </div>
        </div>
			
			</div>
			<div class="w960">
				<div class="total_prod">
					<p>
						合计：￥<em class="orange_color" id="cart_realPrice">${orders.totalFromBdh}</em>(不含运费)
					</p>
					<a class="btn_total am-btn" href="javascript:void(0)" onclick="sbm()" id="checkBtn">结算</a>
				</div>

			</div>
		</div>
</form>
		<br /> <br /> <br /> <br />



	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>

		<script>
		$(function() {
			buy.init();
		});
	</script>
</body>
</html>
