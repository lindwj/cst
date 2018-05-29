<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
    <link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/components/header/footer.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/css/ordersnew.css"/>


	
<script src="<%=path%>/resource/components/jquery/jquery-3.0.0.min.js"></script>
<script type="text/javascript">
	function sbm() {

		$("#alipayto").submit();
	}
</script>
</head>
<body style="display: none;">
	<%@include file="../../../../resource/include/header.jsp"%>


	<div class="content">
    <div class="address">

            <h3>确认收货地址
            </h3>
            
            
            <ul id="address-list" class="address-list">
            
            
            <li class="clearfix selected" id="sel_" data-addressid="">
                    <i class="marker iconfont"></i>
                    <span class="marker-tip">寄送至</span>
                    <div class="address-info">
                        <label for="addrId_" class="user-address">
                            <span>${orders.receiveAddress }</span>
                            （
                            <span>${orders.receiveName }</span>
                            &nbsp;收）
                            <span class="addr-person"><em>${orders.receiveMobile }</em></span>
                        </label>
                    </div>
                </li>
            
            
            </ul>
            
            
            
            
            
            
            
            <div class="buy-order-field">
        <h3>确认订单信息</h3>
        <div class="buy-th-line clearfix">
            <span class="buy-th-title">商品信息</span>
            <span class="buy-th-type">规格</span>
            <span class="buy-th-price">单价(元)</span>
            <span class="buy-th-quantity">数量</span>
            <span class="buy-th-total">小计(元)</span>
        </div>

        <div class="order clearfix">
            <div class="orderInfo blue-line">
                <div>
                    <span class="orderInfo-shoparea" id="shopmc">店铺：${orders.shopName }进行配送</span>
                </div>
            </div>

            <div id="item-list">
            
            <c:forEach var="ordersDetail" items="${orders.ordersDetailList}">
                <!--加载商品信息-->
                <div class="item clearfix">
                    <div class="itemInfo item-title">
                    
                    
                        <a href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${ordersDetail.productUuid}" class="itemInfo-link">
                            <span class="item-pic"><span>
                                <img class="itemInfo-pic" src="${ordersDetail.pic}">
                            </span>
                            </span>
                            <span class="itemInfo-title">${ordersDetail.productName}</span>
                        </a>
                    </div>
                    <div class="item-type">
                        <span class="itemInfo type"><em>&nbsp;&nbsp;</em></span>
                    </div>
                    <div class="item-price">
                        <span class="price"><em id="showprice">${ordersDetail.price}</em></span>
                    </div>
                    <div class="quantity item-quantity"><p>${ordersDetail.capacity}</p></div>
                    <div class="itemPay item-total"><p class="itemPay-price price"><em>${ordersDetail.price*ordersDetail.capacity}</em></p></div>
                </div>
                
                </c:forEach>
            </div>


            <!--div class="order-extra">
                <div class="order-user-info">
                    <div class="memo"><label>给卖家留言：</label>
                        <textarea  class="memo-input c2c-text-default memo-close" autocomplete="off" placeholder="选填：对本次交易的说明。" ></textarea>
                    </div>
                </div>

            -->
            <div class="orderPay blue-line clearfix">
                <p>商品合计(含运费)：
                    <span class="price">
                        <span>¥</span>
                        <em class="style-middle-bold-red">${orders.totalFromBdh}</em>
                    </span>
                </p>
            </div>

            <div></div>
            <div></div>

        </div>


    </div>

    <div class="buy-footer">

        <div class="order-go">
            <div class="address-confirm clearfix">
                <div class="box">
                    <div class="realPay" tabindex="0">
                        <em>实付款：</em>
                        <span class="price g_price "><span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">${orders.totalFromBdh}</em></span>
                    </div>
                    <div class="address">

                        <p class="buy-footer-address">
                            <span class="buy-line-title buy-line-title-type">寄送至：</span>
                            <span id="addr-bottom">${orders.receiveAddress }</span>
                        </p>
                        <p class="buy-footer-address">
                            <span class="buy-line-title" id="">收货人：</span>
                            <span id="person-bottom">${orders.receiveName } ${orders.receiveMobile }</span>
                        </p>
                    </div>

                </div>

                <div class="submitOrder">
                    <div class="go-btn-wrap">
                    
                    <form id="alipayto" name="alipayto"
					action="<%=path%>/resource/jsp/alipayb/alipayto.jsp" method="post">
					<input type="hidden" name="orderNo" value="${orders.code}" /> <input
						type="hidden" name="orderTatol" value="${orders.totalFromBdh}" />

					<input type="hidden" name="subject" value="${orders.ordersDetailList.get(0).productName} <c:if test="${orders.ordersDetailList.size()>1}">等多件</c:if>" />

				</form>

					
                        <a id="J_Go" href="javascript:void(0);" class="btn-go  " tabindex="0" onclick="sbm()">支付</a>
                    </div>
                </div>
                <!--<a href="" class="return-cart J_MakePoint" ><i class="iconfont icon-fanhui"></i>返回购物车</a>-->
            </div>
            <div class="clearfix"></div>


        </div>
    </div>

</div>




	</div>

	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../../resource/include/footer.jsp"%>

<script type="text/javascript">
sbm();
</script>
</body>
</html>
