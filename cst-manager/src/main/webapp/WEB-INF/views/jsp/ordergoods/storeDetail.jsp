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
    
    
<title>${webTitle}</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/storeDeatil.css"
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
function sbmform(obj){
	var reg = /(1[3-9]\d{9}$)/;
	if($("#receiveMan").val()==""||$("#phone").val()==""||$("#address").val()==""){
		$("#errorm3").fadeIn(300);
		$("#errorm3").fadeOut(5000);
	}else if(!reg.test($("#phone").val())){
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else{
		$("#code").attr("value",$("#hiddencode").attr("value"));
		$("#form").attr("action", "<%=path%>/Ordergoods/updateOrder.do");
		$("#form").submit();
	}
}
function add(obj){
    var form = document.createElement("form");  
    document.body.appendChild(form);  
    var input = document.createElement("input");  
    var codeval=$(obj).attr("data-id");
    input.type = "text";  
    input.name = "code";  
    input.value =codeval ;  
    form.appendChild(input);  
    form.method = "POST";  
    form.action = "<%=path%>/Ordergoods/getLogistics.do";  
    form.submit();  
    document.body.removeChild(form);  
}
function del(obj){
	var form = document.createElement("form");  
    document.body.appendChild(form);  
    var input = document.createElement("input");  
    var input1 = document.createElement("input"); 
    var idval=$(obj).attr("data-id");
    var codeval =$("#hiddencode").attr("value");
    input.type = "text";  
    input.name = "logisticsId";  
    input.value =idval ;  
    form.appendChild(input);  
    input1.type = "text";  
    input1.name = "ordergoodsCode";  
    input1.value =codeval ;  
    form.appendChild(input1);  
    form.method = "POST";  
    form.action = "<%=path%>/Logistics/logisticsDel.do";  
    form.submit();  
    document.body.removeChild(form);  
}
$(function(){
	$("#canclebtn").click(function(){
		$("#black1").fadeOut();
	})
	$("#addressbtn").click(function(){
		$(".prodetail").eq(0).find("input").val($(".message").eq(0).find("p").text());
		$(".prodetail1").eq(0).find("input").val($(".message").eq(1).find("p").text());
		$(".prodetail").eq(1).find("input").val($(".order").eq(0).val());
		$(".prodetail1").eq(1).find("input").val($(".order").eq(1).val());
		$(".prodetail2").eq(0).find("textarea").val($(".order").eq(2).val());
		$(".prodetail2").eq(1).find("textarea").val($(".order").eq(3).val());
		$("#black1").fadeIn();
	})
})
</script>
</head>
<body>

<div class="black" id="black1">
	<form name="form" id="form" method ="post">
    <div class="fadebox">
        <div class="ordertitle">
            <h1>添加预定订单</h1>
        </div>
        <input type="hidden" name="code" id="code"/>
        <div class="prodetail" id="select3">
            <p>收货人名:</p>
            <input type="text" id="receiveMan" name="receiveMan" value="${ordergoods.receiveMan}">
        </div>
        <div class="prodetail1">
            <p>手机号码:</p>
            <input type="text" id="phone" name="phone" value="${ordergoods.phone}">
        </div>
        <div class="prodetail">
            <p>邮政编码:</p>
            <input type="text" name="zipCode" value="${ordergoods.zipCode}">
        </div>
        <div class="prodetail1">
            <p>电话号码:</p>
            <input type="text" name="telPhone" value="${ordergoods.telPhone}">
        </div>
        <div class="prodetail2">
            <p>收货地址:</p>
            <textarea name="address" id="address" value="${ordergoods.address}"></textarea>
        </div>
        <div class="prodetail2">
        <p>备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp; :</p>
        <%-- <input type="text" name="storeRemark" value="${ordergoods.storeRemark}"> --%>
        <textarea type="text" name="storeRemark" value="${ordergoods.storeRemark}"></textarea>
    </div>
        <div class="clear"></div>

        <div class="orderbtn">
            <input id="canclebtn" class="btn-default" type="button" value="取消">
            <input id="surebtn" class="btn-default" type="button" onclick="sbmform()" value="确定">
        </div>
    </div>
    </form>
</div>


<div class="container">

       <%@ include file="../include/top2.jsp"%>


        <%@ include file="../include/left2.jsp"%>

        <div class="content">
            <div class="content-title">物流维护</div>

            <div class="productorder">

                <table class="table">
                    <th>订单信息
                        <button type="button" class="btn btn-default" data-id="${ordergoods.code}" onclick="add(this)">物流增加</button>
                        <button style="margin-right: 10px;" type="button" class="btn btn-default" id="addressbtn">地址修改</button>
                    </th>
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
                        <th class="col13">申请数量</th>
                        <th class="col14">单位</th>
                        <th class="col15">已发货数量</th>
                    </tr>
                    <c:forEach var="item" items="${ordergoods.ordergoodsProducts}">
                    <tr>
                        <td class='col1'>${item.productName}</td>
                        <td class='col2'>${item.applyNum}</td>
                        <td class='col3'>
                        	<c:if test="${item.unit==1}">袋</c:if>
                        	<c:if test="${item.unit==2}">盒</c:if>
                        	<c:if test="${item.unit==3}">箱</c:if>
                        	<c:if test="${item.unit==4}">饼</c:if>
                        	<c:if test="${item.unit==5}">瓶</c:if>
                        </td>
                        <td class='col4' style=" border-bottom:1px solid #d2d7d9 ;">${item.num}</td>
                    </tr>
                    </c:forEach>
                </table>




                <div class="content-title">物流信息</div>
                <table class="table1">
                    <tr>
                        <th class="col12">商品名称</th>
                        <th class="col13">发货数量</th>
                        <th class="col14">单位</th>
                        <th class="col15">状态</th>
                    </tr>
                </table>

				<c:forEach var="item" items="${logistics}">
                <table class='table2'>
                    <tr class='table2title'>
                        <td style="text-align: left" colspan='8'>发货时间:${item.logisticsTimeStr}&nbsp;&nbsp;&nbsp;&nbsp;物流公司:${item.logisticsBuiness}&nbsp;&nbsp;&nbsp;&nbsp;物流单号:${item.logisticsCode}&nbsp;&nbsp;&nbsp;&nbsp;联系人:${item.linkMan}&nbsp;&nbsp;${item.logisticsPhone}
                            <button type="button" class="btn btn-default" data-id="${item.logisticsId}" onclick="del(this)">删除</button>
                        </td>
                    </tr>
                    <c:forEach var="ntem" items="${item.logisticsProducts}" varStatus="num">
                    <tr>
                        <td class='col1'>${ntem.productName}</td>
                        <td class='col2'>${ntem.logisticsNum}</td>
                        <td class='col3'>
                        	<c:if test="${ntem.unit==1}">袋</c:if>
                        	<c:if test="${ntem.unit==2}">盒</c:if>
                        	<c:if test="${ntem.unit==3}">箱</c:if>
                        	<c:if test="${ntem.unit==4}">饼</c:if>
                        	<c:if test="${ntem.unit==5}">瓶</c:if>
                        </td>
                        
                        <td class='col4'>
                        <c:if test="${num.index==0}">
                        	<c:if test="${item.logisticsStatus==-1}">未签收</c:if>
                        	<c:if test="${item.logisticsStatus==0}">已发货</c:if>
                        	<c:if test="${item.logisticsStatus==1}">已签收</c:if>
                        	<c:if test="${item.logisticsStatus==2}">物流完成</c:if>
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
                                    <button type="button" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/Ordergoods/storeListPage.do'">返回上一级</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="errorm3">收货人、手机号码、收货地址必填</div>
        <div id="errorm4">请输入正确的手机号码</div>
</div>
</body>
</html>