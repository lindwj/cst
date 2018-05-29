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
<link href="<%=path%>/resource/css/money.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function sbm(){
	$("#shareForm").submit();
}

function pass(obj){
	$(".ordertitle").find("h1").html("确认通过？");
	$('#hidebox').fadeIn();
	//确定事件
	$("#surebtn").click(function(){
		var form = document.createElement("form");  
		document.body.appendChild(form);  
		var input = document.createElement("input");  
		var input1 = document.createElement("input"); 
		var idval=$(obj).attr("data-id");
		var remark=$("#remark").attr("value");
		input.type = "text";  
		input.name = "ordergoodsId";  
		input.value =idval ;  
	    form.appendChild(input); 
	    input1.type = "text";  
	    input1.name = "accountantRemark";  
	    input1.value =remark ;  
	    form.appendChild(input1);  
	    form.method = "POST";  
	    form.action = "<%=path%>/Ordergoods/passMoneyList.do";  
	    form.submit();  
	    document.body.removeChild(form);  
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#hidebox').fadeOut();
    });
}

function refuse(obj){
	$(".ordertitle").find("h1").html("是否拒绝？");
	$('#hidebox').fadeIn();
	//确定事件
	$("#surebtn").click(function(){
		var form = document.createElement("form");  
		document.body.appendChild(form);  
		var input = document.createElement("input");  
		var input1 = document.createElement("input"); 
		var idval=$(obj).attr("data-id");
		var remark=$("#remark").attr("value");
		input.type = "text";  
		input.name = "ordergoodsId";  
		input.value =idval ;  
	    form.appendChild(input); 
	    input1.type = "text";  
	    input1.name = "accountantRemark";  
	    input1.value =remark ;  
	    form.appendChild(input1);  
	    form.method = "POST";  
	    form.action = "<%=path%>/Ordergoods/refuseMoneyList.do";  
	    form.submit();  
	    document.body.removeChild(form);  
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#hidebox').fadeOut();
    });
}
</script>
</head>
<body>
<div class="black" id="hidebox">
    <div class="fadebox">
        <div class="ordertitle">
            <h1></h1>
        </div>
        <div class="prodetail">

        <textarea name="accountantRemark" id="remark"></textarea>
        </div>
        <div class="clear"></div>

        <div class="orderbtn">
            <input id="surebtn" class="btn-default" type="button" value="确定">
            <input id="canclebtn" class="btn-default" type="button" value="取消">
        </div>
    </div>
</div>
<div class="container">

        <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        <div class="content">
        <form name="shareForm" id="shareForm" method="post" action="<%=path%>/Ordergoods/searchMoneyList.do">
            <div class="content-title">订单管理</div>

            <div class="search">

                <label class="search-label">订货日期：</label>
                <input type="text" class="input-default" onclick="WdatePicker({readOnly:true})" name="beginTime" value="${ordergoods.beginTime}" id="tel-name" />
                <label>至</label>
                <input type="text" class="input-default" onclick="WdatePicker({readOnly:true})" name="endTime" value="${ordergoods.endTime}" id="endTime" />

                <label class="search-label1">状态：</label>
                <select id="item-status" name="myStatus" class="select-default">
                    <option value="100"<c:if test="${ordergoods.myStatus == 100}">selected="selected" </c:if>>全部</option>
                    <option value="1"<c:if test="${ordergoods.myStatus == 1}">selected="selected" </c:if>>已提交</option>
                    <option value="4"<c:if test="${ordergoods.myStatus == 4}">selected="selected" </c:if>>库管审核通过</option>
                    <option value="6"<c:if test="${ordergoods.myStatus == 6}">selected="selected" </c:if>>订单审核员审核通过</option>
                    <option value="8"<c:if test="${ordergoods.myStatus == 8}">selected="selected" </c:if>>已发货</option>
                    <option value="10"<c:if test="${ordergoods.myStatus == 10}">selected="selected" </c:if>>订单完成</option>
                    <option value="-1"<c:if test="${ordergoods.myStatus < 0}">selected="selected" </c:if>>已拒绝</option>
                    <%-- <option value="-2"<c:if test="${ordergoods.myStatus == -2}">selected="selected" </c:if>>市场部审核已拒绝</option>
                    <option value="-4"<c:if test="${ordergoods.myStatus == -4}">selected="selected" </c:if>>产品中心审核已拒绝</option>
                    <option value="-6"<c:if test="${ordergoods.myStatus == -6}">selected="selected" </c:if>>财务部审核已拒绝</option> --%>
                </select>

                <label class="search-label">专营店：</label>
                <input name="shopName" type="text" class="input-default" value="${ordergoods.shopName}">

                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>
                <span id="errorm">请输入手机号、订单号或者订单状态,进行搜索!</span>

            </div>

            <div class="productorder">


                <table class="table1">
                    <tr>
                        <th class="col12">商品名称</th>
                        <th class="col13">订货价（元）</th>
                        <th class="col14">单位</th>
                        <th class="col15">申请数量</th>
                        <th class="col17">总金额</th>
                        <th class="col18">订单状态</th>
                        <th class="col19">操作</th>
                    </tr>
                </table>

				<c:forEach var="item" items="${ordergoodsList}">
                <table class='table2'>
                    <tr class='table2title'>
                        <td style="text-align: left" colspan='8'>专营店:${item.shopCode}&nbsp;&nbsp;${item.shopName}&nbsp;&nbsp;&nbsp;&nbsp;提交时间：${item.subDateStr}&nbsp;&nbsp;订单号：${item.code}</td>
                    </tr>
                    <c:forEach var="ntem" items="${item.ordergoodsProducts}"  varStatus="num">
                    <c:if test="${num.index==0}">
                    <tr>
                        <td class='col1'>${ntem.productName}</td>
                        <td class='col2'>${ntem.productPrice}</td>
                        <td class='col3' >
                        	<c:if test="${ntem.unit==1}">袋</c:if>
                        	<c:if test="${ntem.unit==2}">盒</c:if>
                        	<c:if test="${ntem.unit==3}">箱</c:if>
                        	<c:if test="${ntem.unit==4}">饼</c:if>
                        	<c:if test="${ntem.unit==5}">瓶</c:if>
                        </td>
                        <td class='col4'>${ntem.applyNum}</td>
                        <td class='col6' valign='top'>${item.totalPrice}<br>(不含运费)</td>
                        <td class='col7' valign='top'>
                        <c:if test="${item.myStatus==1}">已提交</c:if>
                        <c:if test="${item.myStatus==-2}">库管审核已拒绝</c:if>
                        <c:if test="${item.myStatus==4}">库管员审核通过</c:if>
                        <c:if test="${item.myStatus==6}">订单审核员审核通过</c:if>
                        <c:if test="${item.myStatus==-6}">订单审核员审核已拒绝</c:if>
                        <c:if test="${item.myStatus==8}">已发货</c:if>
                        <c:if test="${item.myStatus==10}">订单完成</c:if>
                        </td>
                        <td class='col8' valign='top'>
                        	<c:if test="${item.myStatus==4}">
                            	<input class='changebtn' type='button' value='通过' onclick="pass(this)" data-id="${item.ordergoodsId}">
                            	<input class='changebtn' type='button' value='拒绝' onclick="refuse(this)" data-id="${item.ordergoodsId}">
                            </c:if>
                         </td>
                    </tr>
                    </c:if>
                    <c:if test="${num.index!=0}">
                    <tr>
                        <td class='col1'>${ntem.productName}</td>
                        <td class='col2'>${ntem.productPrice}</td>
                        <td class='col3' >
                        	<c:if test="${ntem.unit==1}">袋</c:if>
                        	<c:if test="${ntem.unit==2}">盒</c:if>
                        	<c:if test="${ntem.unit==3}">箱</c:if>
                        	<c:if test="${ntem.unit==4}">饼</c:if>
                        	<c:if test="${ntem.unit==5}">瓶</c:if>
                        </td>
                        <td class='col4'>${ntem.applyNum}</td>
                        <td class='col6' valign='top'></td>
                        <td class='col7' valign='top'></td>
                        <td class='col8' valign='top'>
                        </td>
                    </tr>
                    </c:if>
                    </c:forEach>
                </table>
			</c:forEach>
                <table class="table3">
                <%@include file="../include/innerNavigation.jsp"%>
                </table>
            </div>
            </form>
        </div>

</div>
</body>
</html>