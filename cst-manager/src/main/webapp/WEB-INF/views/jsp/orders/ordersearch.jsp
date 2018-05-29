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
<link href="<%=path%>/resource/css/ordersearch.css"
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


</script>
</head>
<body>



<div class="container">

<%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>
        <div class="content">

            <div class="content-title">订单管理</div>
<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/orders/ordersForBdhListPage.do">
            <div class="search">

                <label class="search-label">手机号：</label>
                <input name="receiveMobile" class="input-default"
											id="receiveMobile" value="${orders.receiveMobile }" type="text" class="input-default">


                <label class="search-label1">订单号：</label>
                <input name="code" id="code" value="${orders.code }" type="text" class="input-default">

				<label class="search-label1">订单状态：</label>
				<select name="statusstr" id="statusstr" class="input-default" >
												<option value="100" <c:if test="${orders.status == 100}">selected="selected" </c:if>>全部</option>
												<option value="9" <c:if test="${orders.status == 9}">selected="selected" </c:if>>待付款</option>
												<option value="1" <c:if test="${orders.status == 1}">selected="selected" </c:if>>已付款（配货中）</option>
												<option value="2" <c:if test="${orders.status == 2}">selected="selected" </c:if>>已签收</option>
										</select>
                <br>
                <label class="search-label">省&nbsp;&nbsp;&nbsp;份：</label>
                <select style="width:150px;margin-top:20px;" id="selprovince" name="province" class="input-default"
											onchange="onSelectChange(0,this,'selcity');"></select>

                <label class="search-label1">城&nbsp;&nbsp;&nbsp;市：</label>
                <select style="width:150px;" id="selcity" name="city" class="input-default"
											onchange="onSelectChange(0,this,'seldistrict');">
												<option value="">请选择</option>
										</select>

                <label class="search-label1">县&nbsp;&nbsp;&nbsp;区&nbsp;&nbsp;&nbsp;：</label>
                 <select style="width:130px;" name="district" id="seldistrict" class="input-default"
										onchange="onSelectChange(2,this,'selshopId');">
												<option value="">请选择</option>
										</select>
                <label class="search-label1">专营店：</label>
                <select name="shopId" id="selshopId" class="input-default"
										>
												<option value="">请选择</option>
										</select>

                <button type="button" class="btn-default" onclick="sbm()" id="searchbtn">搜索</button>





            </div>

            <div class="content-main">
                    <table class="table1">
                        <tr>
                            <th class="col1"></th>
                            <th class="col2">商品</th>
                            <th class="col3">单价（元）</th>
                            <th class="col4">数量</th>
                            <th class="col5">收货信息</th>
                            <th class="col6">实付款（元）</th>
                            <th class="col7">服务费</th>
                            <th class="col8">备注</th>
                            <th class="col0">交易状态</th>
                        </tr>
                    </table>
                    
                    
                    
                    
                    <c:forEach var="item" items="${ordersList}">
									<c:if test="${! item.productUuid.equals('0')}">
									
									
									
									
									
									<table class='table2'>

                        <tr class='table2title'>
                            <td colspan='9'>${item.createDatestr}&nbsp;&nbsp;&nbsp;&nbsp;订单号：${item.code}&nbsp;&nbsp;&nbsp;&nbsp;交易号：<c:if test="${item.payType == 2}">${item.transactionId}</c:if><c:if test="${item.payType == 1}">${item.tradeNo}</c:if>&nbsp;&nbsp;&nbsp;&nbsp;支付方式：<c:if test="${item.payType == 2}">微信</c:if><c:if test="${item.payType == 1}">支付宝</c:if></td>
                        </tr>
                        <tr>
                            <td class='col9'>
                                <div class='tablepic'>
                                    <img
													src="${item.pic}">
                                </div>
                            </td>
                            <td class='col10'>${item.subject}</td>
                            <td class='col11' valign='top'>${item.price}</td>
                            <td class='col12' valign='top'>${item.capacity}</td>
                            <td class='col13' valign='top'>${item.receiveName}&nbsp;&nbsp;${item.receiveMobile}<br>${item.receiveAddress}</td>
                            <td class='col14' valign='top'>${item.totalFromBdh}</td>
                            <td class='col15' valign='top'>${item.serviceFee}</td>
                            <td class='col16' valign='top'>${item.note}</td>
                            <td class='col17' valign='top'><c:if test="${item.status == 100}">全部订单</c:if> <c:if
													test="${item.status == 9}">待付款</c:if> <c:if
													test="${item.status == 1}">已付款（配货中）</c:if> <c:if
													test="${item.status == 2}">已签收</c:if><c:if
													test="${item.status == -1}">已取消</c:if></td>
                        </tr>

                    </table>
									
										
										</c:if>
										<c:if test="${item.productUuid.equals('0')}">
										
										
										
										
										
										<table class='table2'>

                        <tr class='table2title'>
                            <td colspan='9'>${item.createDatestr}&nbsp;&nbsp;&nbsp;&nbsp;订单号：${item.code}&nbsp;&nbsp;&nbsp;&nbsp;交易号：<c:if test="${item.payType == 2}">${item.transactionId}</c:if><c:if test="${item.payType == 1}">${item.tradeNo}</c:if>&nbsp;&nbsp;&nbsp;&nbsp;支付方式：<c:if test="${item.payType == 2}">微信</c:if><c:if test="${item.payType == 1}">支付宝</c:if></td>
                        </tr>
                        
                        
                        <%int i=0; %>
                        <c:forEach var="ordersDetail" items="${item.ordersDetailList}">
                        <%if (i==0){ %>
                        
                        <tr>
                            <td class='col9'>
                                <div class='tablepic'>
                                   <img
													src="${ordersDetail.pic}">
                                </div>
                            </td>
                            <td class='col10'>${ordersDetail.productName}</td>
                            <td class='col11' valign='top'>${ordersDetail.price}</td>
                            <td class='col12' valign='top'>${ordersDetail.capacity}</td>
                            <td class='col13' valign='top'>${item.receiveName}&nbsp;&nbsp;${item.receiveMobile}<br>${item.receiveAddress}</td>
                            <td class='col14' valign='top'>${item.totalFromBdh}</td>
                            <td class='col15' valign='top'>${item.serviceFee}</td>
                            <td class='col16' valign='top'>${item.note}</td>
                            <td class='col17' valign='top'><c:if test="${item.status == 100}">全部订单</c:if> <c:if
													test="${item.status == 9}">待付款</c:if> <c:if
													test="${item.status == 1}">已付款（配货中）</c:if> <c:if
													test="${item.status == 2}">已签收</c:if><c:if
													test="${item.status == -1}">已取消</c:if></td>
                        </tr>
                        
                        
                        <%} else{ %>
                        
                        <tr>
                            <td class='col9'>
                                <div class='tablepic'>
                                     <img
													src="${ordersDetail.pic}">
                                </div>
                            </td>
                            <td class='col10'>${ordersDetail.productName}</td>
                            <td class='col11' valign='top'>${ordersDetail.price}</td>
                            <td class='col12' valign='top'>${ordersDetail.capacity}</td>
                            <td class='col13' valign='top'></td>
                            <td class='col14' valign='top'></td>
                            <td class='col15' valign='top'></td>
                            <td class='col16' valign='top'></td>
                            <td class='col17' valign='top'></td>
                        </tr>
                        
                        <%}
                      i++; %>
                        </c:forEach>

                    </table>
										
										
										
										
										
										
										
										
										
        								
        								</c:if>
									</c:forEach>
                    
                    

                    

					<%@include file="../include/innerNavigation.jsp"%>


            </div>

</form>

        </div>

</div>

<script type="text/javascript">


var p='${orders.province}';
var c='${orders.city}';
var d='${orders.district}';
var s='${orders.shopId}';


function onSelectChange(fromset,obj,toSelId){
 setSelect(fromset,obj.value,toSelId);
}
function setSelect(fromset,fromSelVal,toSelId){
 //alert(document.getElementById("province").selectedIndex);
 document.getElementById(toSelId).innerHTML="";
 jQuery.ajax({
  url: "<%=path%>/nation/getNation.do",
				cache : false,
				data : "parent=" + fromSelVal+"&isStreet="+fromset,//isStreet=2 标识不是查询街道 而是查询专营店
				success : function(data) {
					createSelectObj(data, toSelId);
				}
			});
		}
		function createSelectObj(data, toSelId) {
			var arr = jsonParse(data);
			if (arr != null && arr.length > 0) {
				var obj = document.getElementById(toSelId);
				obj.innerHTML = "";
				var nullOp = document.createElement("option");
				nullOp.setAttribute("value", "");
				nullOp.appendChild(document.createTextNode("请选择"));
				obj.appendChild(nullOp);
				for ( var o in arr) {
					var op = document.createElement("option");
					op.setAttribute("value", arr[o].id);
					//op.text=arr[o].name;//这一句在ie下不起作用，用下面这一句或者innerHTML
					if (toSelId == 'selprovince') {
						if(arr[o].id==p){
							op.setAttribute("selected", 'selected');
						}
						op
								.appendChild(document
										.createTextNode(arr[o].province));
					} else if (toSelId == 'selcity') {
						if(arr[o].id==c){
							op.setAttribute("selected", 'selected');
						}
						op.appendChild(document.createTextNode(arr[o].city));
					} else if (toSelId == 'seldistrict') {
						if(arr[o].id==d){
							op.setAttribute("selected", 'selected');
						}
						op
								.appendChild(document
										.createTextNode(arr[o].district));
					}else if (toSelId == 'selshopId') {
						
						if(arr[o].id==s){
							op.setAttribute("selected", 'selected');
						}
						op
						.appendChild(document
								.createTextNode(arr[o].street));
					}
					
					
					obj.appendChild(op);
				}
			}
		}
		setSelect(0,'1', 'selprovince');
		
		if(p.length!=0){
			setSelect(0,p, 'selcity');
			setSelect(0,c, 'seldistrict');
			setSelect(2,d, 'selshopId');
			}
	</script>
</body>
</html>