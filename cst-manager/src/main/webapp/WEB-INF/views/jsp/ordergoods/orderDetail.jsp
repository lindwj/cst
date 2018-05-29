<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../include/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <!--<link rel="icon" href="/static/images/favicon.ico?v=1" type="image/x-icon"/>-->
    
    
<title>${webTitle }</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/orderDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
<script type="text/javascript">
function sbm(){
	$("#shareForm").submit();
}
function detail(obj){
	var form = document.createElement("form");  
	document.body.appendChild(form);  
	var input = document.createElement("input");  
	var idval=$(obj).attr("data-id");
	var remark=$("#remark").attr("value");
	input.type = "text";  
	input.name = "logisticsId";  
	input.value =idval ;  
	form.appendChild(input); 
	form.method = "POST";  
	form.action = "<%=path%>/Logistics/getSignDetail.do";  
	form.submit();  
	document.body.removeChild(form); 
}
$(function(){
	
})
</script>
</head>
<body>
<div class="container">
        <%@ include file="../include/top2.jsp"%>
		<%@ include file="../include/left2.jsp"%>
        <div class="content">
            <div class="content-title">订单详情</div>
            <div class="productorder">
<table class="table">
                    <th>订单信息</th>
                    <tr>
                        <td>
                            <div class="message">
                                <h1>收货人:</h1>
                                <p>${ordergoods.receiveMan}</p>
                            </div>
                            <input type="hidden" value="${ordergoods.code}" id="hiddencode"/>
                            <input class="order" type="hidden" value="${ordergoods.zipCode}"/>
                            <input class="order" type="hidden" value="${ordergoods.telPhone}"/>
                            <input class="order" type="hidden" value="${ordergoods.address}"/>
                            <input class="order" type="hidden" value="${ordergoods.storeRemark}"/>
                            <br>
                            <div class="message">
                                <h1>手机:</h1>
                                <p>${ordergoods.phone}</p>
                            </div>
                            <br>
                            <div class="message">
                                <h1>订单号:</h1>
                                <p>${ordergoods.code}</p>
                            </div>
                            <br>
                            <div class="message">
                                <h1>收货地址:</h1>
                                <p>${ordergoods.address}</p>
                            </div>
                            <br>
                            <div class="message">
                                <h1 style="height:78px;">审批:</h1>
                                <p>市场部：${ordergoods.marketRemark}&nbsp;&nbsp;${ordergoods.marketTimeStr}</p><br>
                                <p>产品中心：${ordergoods.pcenterRemark}&nbsp;&nbsp;${ordergoods.pcenterTimeStr}</p><br>
                                <p>财务部：${ordergoods.accountantRemark}&nbsp;&nbsp;${ordergoods.accountantTimeStr}</p><br>
                                <p>库管：${ordergoods.storeRemark}&nbsp;&nbsp;${ordergoods.storeTimeStr}</p>
                            </div>
                        </td>
                    </tr>
                </table>
                <table class="table1">
                    <tr>
                        <th class="col12">商品名称</th>
                        <th class="col13">订货价（元）</th>
                        <th class="col14">单位</th>
                        <th class="col15">发货数量</th>
                        <th class="col17">总金额</th>
                        <th class="col18">订单状态</th>
                    </tr>
                </table>
				<c:forEach var="item" items="${logistics}">
                <table class='table2'>
                    <tr class='table2title'>
                        <td style="text-align: left" colspan='8'>发货时间:${item.logisticsTimeStr}&nbsp;&nbsp;&nbsp;&nbsp;物流公司:${item.logisticsBuiness}&nbsp;&nbsp;&nbsp;&nbsp;物流单号:${item.logisticsCode}&nbsp;&nbsp;&nbsp;&nbsp;联系人:${item.linkMan}&nbsp;&nbsp;${item.logisticsPhone}
                            <c:if test="${item.logisticsStatus==1}">
                            	<button type="button" class="btn btn-default" data-id="${item.logisticsId}" onclick="detail(this)">签收详情</button>
                            </c:if>
                        </td>
                    </tr>
                    <c:forEach var="ntem" items="${item.logisticsProducts}" varStatus="num">
                    <tr>
                        <td class='col1'>${ntem.productName}</td>
                        <td class='col2'>${ntem.productPrice}</td>
                        <td class='col3'>
                        	<c:if test="${ntem.unit==1}">袋</c:if>
                        	<c:if test="${ntem.unit==2}">盒</c:if>
                        	<c:if test="${ntem.unit==3}">箱</c:if>
                        	<c:if test="${ntem.unit==4}">饼</c:if>
                        	<c:if test="${ntem.unit==5}">瓶</c:if>
                        </td>
                        <td class='col4'>${ntem.logisticsNum}</td>
                        <td class='col6' valign='top'>
                        <c:if test="${num.index==0}">${item.totalPrice}<br>(不含运费)
                        </c:if>
                        </td>
                        <td class='col7'>
                        	<c:if test="${num.index==0}">
                        	<c:if test="${item.logisticsStatus==-1}">未签收</c:if>
                        	<c:if test="${item.logisticsStatus==0}">已发货</c:if>
                        	<c:if test="${item.logisticsStatus==1}">已签收<br>运费:${item.sendMoney}元</c:if>
                        	<c:if test="${item.logisticsStatus==2}">物流完成<br>运费:${item.sendMoney}元</c:if>
                        	</c:if>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
				</c:forEach>

                <table class="table3">
                    <tr>
                        <td colspan="7">

                            <div class="pull-right page-box">
                                <div class="pagination-goto">
                                    <button type="button" class="btn btn-default" id="goBtn" onclick="javascript:history.back()">返回上一级</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
         </div>
   </div>
</div>
</body>
</html>