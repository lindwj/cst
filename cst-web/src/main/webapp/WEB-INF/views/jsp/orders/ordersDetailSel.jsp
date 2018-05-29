<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false" %>
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


  <link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
    <link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/components/header/footer.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/css/OrderDetail.css"/>

</head>
<body>

<body>
	<%@include file="../../../../resource/include/header.jsp"%>
<div class="content">


    <div class="order-info">

        <div class="info-title">
            <p>订单信息</p>
        </div>


        <div class="info-deli">

            <div>
                <span>订单状态：</span>
                <span style="font-size: 14px;font-weight: 700">
                <c:if test="${preOrders.status == 0}">
										待处理
										</c:if>
										
											<c:if test="${preOrders.status == 1}">
											已签约
											</c:if>
                </span>
            </div>
            <div>
                <span>收货地址：</span>
                <span>${preOrders.receiveAddress}</span>
            </div>
            <div>
                <span>合同编号：</span>
                <span>${preOrders.contractNo}</span>
            </div>
            <div>
                <span>送货店铺：</span>
                <span>${preOrders.shopName}</span>
            </div>

        </div>

    </div>	
	
	
	<div class="order-item">
        <!--标题-->
        <table class="item-table">
            <colgroup>
                <col class="title-col1">
                <col class="title-col2">
                <col class="title-col3">
                <col class="title-col4">
                <col class="title-col5">
                <col class="title-col6">
            </colgroup>
            <tbody>
            <tr>
                <th>商品</th>
                <th>收货人</th>
										<th>申请人</th>
										<th>贵宾专员</th>
										<th>状态</th>

            </tr>

            </tbody>
        </table>

        <!--订单内容-->
        <div class="item-content">

            <div class="item-title">

                <span class="item-date">${preOrders.updateTimeStr}</span>
                <span>合同编号：</span>
                <span>${preOrders.contractNo}</span>

            </div>

            <ul>
                <li class="td col1">
                    <div class="item-img"><a><img src="${preOrders.pic}"></a></div>
                    <div style="margin-left:90px;">
                        <p class="item-detail-title"><a href="javacript:void(0);"><span>${preOrders.productName}</span></a><p>
                    </div>
                </li>
                <li class="td col2"><p>${preOrders.receiveName}</p></li>
                <li class="td col3"><p>${preOrders.applyName}</p></li>
                <li class="td col4">
                    <div>
                        <strong>${preOrders.managerName}</strong>
                    </div>
                </li>

                <li class="td col5">
                    <div>
                        <p><span>
                          <c:if test="${preOrders.status == 0}">
										待处理
										</c:if>
										
											<c:if test="${preOrders.status == 1}">
											已签约
											</c:if>  
                        </span></p>
                    </div>
                </li>


                <div class="float-clear"></div>
            </ul>
            


        </div>



    </div>

    <div class="order-price">

    </div>

</div>



	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../../resource/include/footer.jsp"%>


</body>
</html>
