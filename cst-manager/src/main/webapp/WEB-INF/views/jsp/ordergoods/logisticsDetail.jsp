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
    
    
<title>${webTitle }</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/logisticsDetail.css"
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
 	var length=$(".updateNum").length;
	var judge2=0;
	var hidjudge=0;
	var judge1=0;
	var ex = /^\d+$/;
	for(var i=0;i<length;i++){
		if($(".logisticsNum").eq(i).val()==""){
			judge2=0;
		}else{
			if(judge2==0){
				if($(".hidden").eq(i).attr("checked")=="checked"){
					if(($(".updateNum").eq(i).text()-$(".num").eq(i).text())<$(".logisticsNum").eq(i).val()){
						judge2=-1;
					}
				}
			}
		}
	} 
	for(var i=0;i<length;i++){
 		if(judge1==0){
 			if($(".hidden").eq(i).attr("checked")=="checked"){
				if(!ex.test($(".logisticsNum").eq(i).val())){
					judge1=-1;
				}
 			}
		}
	} 
	for(var i=0;i<length;i++){
		if(hidjudge==0){
			if($(".hidden").eq(i).attr("checked")=="checked"){
				hidjudge=-1;
			}
		}
	}
 	if($(".logisticsBuiness").val()==""||$(".logisticsCode").val()==""){
		$("#errorm3").fadeIn(300);
		$("#errorm3").fadeOut(5000);
	}else if(judge2==-1){
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else if(hidjudge==0){
		$("#errorm5").fadeIn(300);
		$("#errorm5").fadeOut(5000);
	}else if(judge1==-1){
		$("#errorm6").fadeIn(300);
		$("#errorm6").fadeOut(5000);
	}else{
		$("#shareForm").submit(); 
		}
 
}
function back(obj){
    var form = document.createElement("form");  
    document.body.appendChild(form);  
    var input = document.createElement("input");  
    var codeval=$(obj).attr("data-id");
    input.type = "text";  
    input.name = "code";  
    input.value =codeval ;  
    form.appendChild(input);  
    form.method = "POST";  
    form.action = "<%=path%>/Ordergoods/getOrderDetail.do";  
    form.submit();  
    document.body.removeChild(form);  
}
$(function(){
	$(".hiddenn").attr("value",0);
	var length=$(".updateNum").length;
	for(var i=0;i<length;i++){
		if($(".hidden").eq(i).attr("checked")=="checked"){
			$(".hiddenn").eq(i).attr("value",1);
		}
	}
	$(".hidden").click(function(){
		if($(this).attr("checked")=="checked"){
			$(this).parents("tr").find("input").eq(0).attr("value",1);
		}else{
			$(this).parents("tr").find("input").eq(0).attr("value",0);
		}
	});
})
</script>
</head>
<body>
<div class="container">
       <%@ include file="../include/top2.jsp"%>
        <%@ include file="../include/left2.jsp"%>
<div class="content">
<form name="shareForm" id="shareForm" method="post" action="<%=path%>/Logistics/savelogistics.do">
            <div class="content-title">物流维护</div>
            <div class="search">
            <input type="hidden" name="ordergoodsCode" value="${ordergoods.code}"/>
                <label class="search-label">物流公司:</label>
                <input id="tel-name" name="logisticsBuiness" type="text" class="input-default logisticsBuiness">
                <label>物流单号:</label>
                <input name="logisticsCode" type="text" class="input-default logisticsCode">
                <label>联系人:</label>
                <input name="linkMan" type="text" class="input-default">
                <label>电话号码:</label>
                <input name="logisticsPhone" type="text" class="input-default">
            </div>
            <div class="productorder">
                <table class="table1">
                    <tr>
                        <th class="col11"></th>
                        <th class="col12">商品名称</th>
                        <th class="col13">应发货数量</th>
                        <th class="col14">单位</th>
                        <th class="col15">已发货数量</th>
                        <th class="col16">本次货数量</th>
                    </tr>
                    <c:forEach var="item" varStatus="st" items="${ordergoods.ordergoodsProducts}">
                    <tr>
                    	<input type="hidden" name="logisticsProducts[${st.index}].hidden" class="hiddenn"/>
                        <td class='col1'><input type="checkbox" class="hidden"></td>
                        <input type="hidden" name="logisticsProducts[${st.index}].productUuid" value="${item.productUuid}"/>
                        <input type="hidden" name="logisticsProducts[${st.index}].unit" value="${item.unit}"/>
                        <input type="hidden" name="logisticsProducts[${st.index}].productPrice" value="${item.productPrice}"/>
                        <input type="hidden" name="logisticsProducts[${st.index}].totalNum" value="${item.num}"/>
                        <input type="hidden" name="logisticsProducts[${st.index}].applyNum" value="${item.applyNum}"/>
                        <td class='col2'>${item.productName}</td>
                        <td class='col3 updateNum'>${item.applyNum}</td>
                        <td class='col4'>
                        	<c:if test="${item.unit==1}">袋</c:if>
                        	<c:if test="${item.unit==2}">盒</c:if>
                        	<c:if test="${item.unit==3}">箱</c:if>
                        	<c:if test="${item.unit==4}">饼</c:if>
                        	<c:if test="${item.unit==5}">瓶</c:if>
                        </td>
                        <td class='col5 num' style=" border-bottom:1px solid #d2d7d9 ;">
                        <c:if test="${item.num==null}">0</c:if>
                        <c:if test="${item.num!=null}">${item.num}</c:if></td>
                        <td class='col6'><input type="text" name="logisticsProducts[${st.index}].logisticsNum" class="logisticsNum" value="${item.applyNum-item.num}"/></td>
                    </tr>
                    </c:forEach>
                </table>
                <table class="table3">
                    <tr>

                        <td colspan="7">

                            <div class="pull-right page-box">

                                <div class="pagination-goto">
                                    <button type="button" class="btn btn-default" id="goBtn" onclick="back(this)" data-id="${ordergoods.code}">返回上一级</button>
                                    <button style="margin-right:10px;" type="button" class="btn btn-default" id="saveBtn" onclick="sbm()">保存</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>


            </div>
			</form>
        </div>
        <div id="errorm3">物流公司、物流单号必填</div>
        <div id="errorm4">货数量填写太大</div>
        <div id="errorm5">至少选择一个商品</div>
        <div id="errorm6">货物数量为数字</div>
</div>
</body>
</html>