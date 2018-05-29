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
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
    <link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
    <link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/components/header/footer.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/css/cartnew.css"/>
<script src="<%=path%>/resource/components/jquery/jquery-3.0.0.min.js"></script>

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

function delall(){
	
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
	
	$("#buyForm").attr("action", "<%=path%>/cart/cartDeleteAll.do");
	
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
		<div class="content">
		<div id="err" class="title-content" style="color: red">
				<c:if test="${param.errcode == 103 }">
					<h3>商品库存不足</h3>
				</c:if>
				<c:if test="${param.errcode == 104 }">
					<h3>您的默认地址附近没有匹配的店，不支持配送。</h3>
				</c:if>
			</div>
    <div class="cart-title">
        <div class="cart-title-left"> <span class="title-content">全部商品</span> </div>
        <div class="cart-sum">
            <span>已选商品（含运费）</span>
            <strong class="price">¥<em id="cart_realPrice2">0</em></strong>
            <a id="small-submit" onclick="sbm()" class="submit-btn submit-btn-disabled">结&nbsp;算</a>
        </div>
        <div class="wrap-line"></div>
    </div>


    <div class="cart-table-th">
        <div class="wp">
            <div class="th th-chk">
                <div id="select-all" class="select-all">
                    <div class="cart-checkbox">
                        <input id="selectAllChk" type="checkbox" onClick="javascript:Cart.checkAllHandler(this)" checked="checked">
                    </div>
                    &nbsp;&nbsp;全选
                </div>
            </div>
            <div class="th th-item"><div class="td-inner">商品信息</div></div>
            <div class="th th-info"><div class="td-inner">&nbsp;</div></div>
            <!--<div class="th th-spec"><div class="td-inner">规格</div></div>-->
            <div class="th th-price"><div class="td-inner">单价（元）</div></div>
            <div class="th th-amount"><div class="td-inner">数量</div></div>
            <div class="th th-sum"><div class="td-inner">金额</div></div>
            <div class="th th-op"><div class="td-inner">操作</div></div>
        </div>
    </div>


    <div class="order-list cart-main" id="order-list">
        <!--ajax获取购物车数据-->

        <div class="order-content">
        <%int i=0; %>
				<c:forEach var="product" items="${productList}">
            <ul class="item-content">
                <li class="td td-chk">
                    <div class="td-inner"><div class="cart-checkbox">
                    <input id="productUuid" name="ordersDetailList[<%=i %>].productUuid" type="hidden" value="${product.productUuid}" />
                        <input id="${product.productUuid}code" name="ordersDetailList[<%=i %>].code" type="hidden" value="1" />
                    <input id="${product.productUuid}chk" uuid="${product.productUuid}" onclick="javascript:Cart.amt()" type="checkbox" checked="checked">
                      </div>
                    </div>
                </li>
                <li class="td td-item">
                    <div class="td-inner">
                        <div class="item-pic">
                            <a href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${product.productUuid}" target="_blank">
                                <img src="${product.pic}">
                            </a>
                        </div>
                        <div class="item-info">
                            <div class="item-basic-info">
                                <a href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${product.productUuid}"><span class="item-title">${product.name}</span></a>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="td td-info">
                    <div class="item-props"></div>
                </li>
                <li class="td td-price">
                    <div class="td-inner">
                        <div class="price-content">
                            <em class="price-now" id="${product.productUuid}showprice">${product.price}</em>
                        </div>
                    </div>
                </li>
                <li class="td td-amount">
                    <div class="td-inner">
                        <div class="amount-wrapper">
                            <div class="item-amount">
                                <span class="minus"  data-minus="16059822" onclick="javascript:Cart.subWareBybutton('${product.productUuid}')">-</span>
                                <input type="text" value="${product.capacity}" oldvalue="${product.capacity}" onchange="javascript:Cart.chg('${product.productUuid}')" id="${product.productUuid}column_num" name="ordersDetailList[<%=i %>].capacity" class="text-amount"  autocomplete="off">
                                <span class="plus"  onclick="javascript:Cart.addWareBybutton('${product.productUuid}')" data-plus="16059822">+</span>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="td td-sum">
                    <div class="td-inner">
                        <em  id="${product.productUuid}sumprice">${product.price*product.capacity}</em>
                    </div>
                </li>
                <li class="td td-op"><div class="td-inner"><a href="<%=path%>/cart/cartDelete.do?productUuid=${product.productUuid}">删除</a></div></li>
            <div class="float-clear"></div>
            </ul>
            
             <%i=i+1; %>
                </c:forEach>
        </div>

    </div>

    <!--增加横线样式显示-->
    <div class="order-bottom content"></div>

    <div class="float-bar-holder">
        <div class="float-bar clearfix default" style="position: static;">
            <div class="float-bar-wrapper">

                <div class="select-all J_SelectAll">
                    <div class="cart-checkbox">
                        <input id="selectAllChk" type="checkbox" onClick="javascript:Cart.checkAllHandler(this)" checked="checked" >
                    </div>&nbsp;全选
                </div>

                <div class="operations">
                    <a href="javascript:void(0)" hidefocus="true" onclick="delall()" class="J_DeleteSelected">删除选中的商品</a>
                </div>

                <div class="float-bar-right">
                    <div class="price-sum">
                        <span class="txt">合计（含运费）：</span>
                        <strong class="price">¥<em id="cart_realPrice">0</em></strong>
                    </div>
                    <div class="btn-area">
                        <a href="javascript:void(0)" onclick="sbm()" id="a-sum-total" class="submit-btn submit-btn-disabled" >
                            <span>结&nbsp;算</span>
                            <b></b>
                        </a>
                    </div>
                </div>

            </div>
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
