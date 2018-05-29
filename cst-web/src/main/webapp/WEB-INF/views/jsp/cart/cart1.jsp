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
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="<%=path%>/resource/images/favicon.png">
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">

<link rel="stylesheet" href="<%=path%>/resource/css/cart.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/cart.js"></script>
<script type="text/javascript">
function sbm(){
	
	var flag=0;
	
	$("input[id$='chk']").each(function () {
		if($(this).is(":checked")){
			flag=1;
		}
	});
	
	if(0==flag){
		
		Cart.popModal("请选择商品！");
		return;
	}
	
	$("#buyForm").submit();
}

function cart(capacity,productUuid){
	
	if(capacity!=0){
	jQuery.ajax({
		  url: "<%=path%>/cart/cartAddEdit.do",
						cache : false,
						data : "num=" + capacity+"&productUuid=" + productUuid,
						success : function(data) {
							if(1==data){
							}
							
						}
					});
	}
}

</script>
</head>
<body>

	<%@include file="../../../../resource/include/header.jsp"%>

<form name="buyForm" id="buyForm" method="post"
		action="<%=path%>/orders/ordersAddEditDetailIni.do">
		<div class="w960 cart_empty_div" style="display: none;" id="emptyCart">
			<div class="empty_div" style="height: 160px;">
				<dl>
					<dt>
						<img src="<%=path%>/resource/images/cart/emptyCart.png">
					</dt>
					<dd>
						您的购物车内还没有商品，<br>马上 <a href="/index">去购物！</a>
					</dd>
				</dl>
			</div>
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
						<li class="column_6">操作</li>
					</ul>
				</div>
			</div>
			<div class="w960 prod_all" >
			
			<div class="shop_list">

            <div class="checkbox" id="orderList">
            <%int i=0; %>
				<c:forEach var="product" items="${productList}">
                <div class="prod_list ">
                    <ul class="tr_ul " >
                        <li class="column_1"><input id="${product.productUuid}chk" uuid="${product.productUuid}" onclick="javascript:Cart.amt()" type="checkbox" checked="checked">
                        <input id="productUuid" name="ordersDetailList[<%=i %>].productUuid" type="hidden" value="${product.productUuid}" />
                        <input id="${product.productUuid}code" name="ordersDetailList[<%=i %>].code" type="hidden" value="1" />
                        </li>
                        <li class="column_2"><a href="<%=path%>/product/productDetail.do?productUuid=${product.productUuid}">
                        <img src="<%=path%>/resource/upload/product/${product.pic}" width="50px" height="50px"/>
                        </a>${product.name}
                        </li>
                        <li class="column_3"></li>
                        <li class="column_4">￥<span class="orange_color" id="${product.productUuid}showprice">${product.price}</span></li>
                        <li class="column_5">
                            <div class="count_div">
                                <span class="sub_btn" onclick="javascript:Cart.subWareBybutton('${product.productUuid}')">-</span>
                                <input type="text" value="${product.capacity}" oldvalue="${product.capacity}" onchange="javascript:Cart.chg('${product.productUuid}')" id="${product.productUuid}column_num" name="ordersDetailList[<%=i %>].capacity" class="column_num">
                                <span class="add_btn" onclick="javascript:Cart.addWareBybutton('${product.productUuid}')">+</span>
                            </div>
                        </li>
                        <li class="column_6"><a class="orange_color delete_btn" href="<%=path%>/cart/cartDelete.do?productUuid=${product.productUuid}" >删除</a></li>
                    </ul>
                    
                </div>
                
                 <%i=i+1; %>
                </c:forEach>
                
               
            </div>
        </div>
			
			</div>
			<div class="w960">
				<div class="total_prod">
					<input type="checkbox" class="checkAll" id="checkIcon-1"
						onClick="javascript:Cart.checkAllHandler(this)" checked="checked"> 全选

					<p>
						合计：￥<em class="orange_color" id="cart_realPrice"></em>(不含运费)
					</p>
					<a class="btn_total am-btn" href="javascript:;" id="checkBtn" onclick="sbm()">购买</a>
				</div>

			</div>
		</div>

		<br /> <br /> <br /> <br />

</form>

	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>
		<script>
		$(function() {
			Cart.init();
		});
	</script>
</body>
</html>
