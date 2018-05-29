<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../include/tag.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link href="<%=path%>/resource/css/Pagestyle.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/resource/css/icon.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resource/css/dropkick.css">
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/toastr/toastr.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/global.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery.dropkick-min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/layer/layer.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
<title>${webTitle }</title>

<script type="text/javascript">
function sbm(){
	
	$("#shareForm").submit();
}


function del(obj){
	
	$("#shopId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/shop/delShop.do");
	$("#shareForm").submit();
}


function edit(obj){
	
	$("#shopId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/shop/shopEditIni.do");
		$("#shareForm").submit();
	}
	
	

function chg(obg){
	
	
	
	var code = $(obg).attr("code");
	
	var statusstr = $("#"+code+"status").find("option:selected").attr("value");
	
	var statustxt = $("#"+code+"status").find("option:selected").text();
	jQuery.ajax({
		  url: "<%=path%>/orders/setStatus.do",
						cache : false,
						data : "code=" + code+"&statusstr=" + statusstr,
						success : function(data) {
							if(0==data){
								popModal('保存成功');
								$("#"+code+"statustxt").html(statustxt);
								
							}
							
						}
					});
	
}
</script>
</head>

<body>
	<div class="container">
		<%@include file="../include/top.jsp"%>
		<!--main-->
		<div class="cat_main">

			<%@include file="../include/left.jsp"%>
			<div class="cat_m_right">
				<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/orders/ordersListPage.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">
						</c:if>
						<c:if test="${errcode == 101 }"></c:if>
					</div>
					<div class="cat_table">
						<h1>订单查询</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>订单号</th>
										<th>订单状态</th>
										<th>手机号</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" name="code" id="code" /></td>
										<td><select name="statusstr" id="statusstr">
												<option value="100">全部</option>
												<option value="9">待付款</option>
												<option value="1">已付款（配货中）</option>
												<option value="2">已签收</option>
										</select></td>
										<td><input type="text" name="receiveMobile"
											id="receiveMobile" /></td>
											<td><a href="#" onclick="sbm()"><i
												class="page_icon detail_icon"></i>查询</a></td>

									</tr>
								</tbody>
							</table>
						</div>

					</div>
					<div class="cat_table">
						<h1>订单列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>

										<th>商品信息</th>
										<th>订单号</th>
										<th>手机号</th>
										<th>单价（元）</th>
										<th>数量</th>
										<th>实付款</th>
										<th>订单状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${ordersList}">
									<c:if test="${! item.productUuid.equals('0')}">
										<tr >
											<td><a 
												href="<%=path%>/product/productDetail.do?productUuid=${item.productUuid}"><img
													src="${item.pic}"></a>
												<a
												href="<%=path%>/product/productDetail.do?productUuid=${item.productUuid}"><p>${item.subject}</p></a>
											</td>
											<td><a href="#">${item.code}</a></td>
											<td>${item.receiveMobile}</td>
											<td>￥${item.price}</td>
											<td>${item.capacity}</td>
											<td><span>￥${item.totalFromBdh}</span></td>
											<td id="${item.code}statustxt"><c:if test="${item.status == 100}">全部订单</c:if> <c:if
													test="${item.status == 9}">待付款</c:if> <c:if
													test="${item.status == 1}">已付款（配货中）</c:if> <c:if
													test="${item.status == 2}">已签收</c:if><c:if
													test="${item.status == -1}">已取消</c:if></td>
											<td><select  id="${item.code}status">
													<option value="1">已付款（配货中）</option>
													<option value="2">已签收</option>
											</select> <a href="#" code="${item.code}" onclick="chg(this)">保存</a></td>
										</tr>
										</c:if>
										<c:if test="${item.productUuid.equals('0')}">
										<tr >
											<td>合并支付</td>
											<td><a href="#">${item.code}</a></td>
											<td>${item.receiveMobile}</td>
											<td></td>
											<td></td>
											<td><span>￥${item.totalFromBdh}</span></td>
											<td id="${item.code}statustxt"><c:if test="${item.status == 100}">全部订单</c:if> <c:if
													test="${item.status == 9}">待付款</c:if> <c:if
													test="${item.status == 1}">已付款（配货中）</c:if> <c:if
													test="${item.status == 2}">已签收</c:if><c:if
													test="${item.status == -1}">已取消</c:if></td>
											<td><select  id="${item.code}status">
													<option value="1">已付款（配货中）</option>
													<option value="2">已签收</option>
											</select> <a href="#" code="${item.code}" onclick="chg(this)">保存</a></td>
										</tr>
        								<c:forEach var="ordersDetail" items="${item.ordersDetailList}">
        								<tr >
											<td><a 
												href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}"><img
													src="${ordersDetail.pic}"></a>
												<a
												href="<%=path%>/product/productDetail.do?productUuid=${ordersDetail.productUuid}"><p>${ordersDetail.productName}</p></a>
											</td>
											<td><a href="#"></a></td>
											<td></td>
											<td>￥${ordersDetail.price}</td>
											<td>${ordersDetail.capacity}</td>
											<td><span></span></td>
											<td id="${ordersDetail.code}statustxt"></td>
											<td></td>
										</tr>
        								</c:forEach>
        								</c:if>
									</c:forEach>
								</tbody>
							</table>

							<%@include file="../include/innerNavigation.jsp"%>
								<%@include file="../include/modal.jsp"%>

						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

	<script type="text/javascript">
		
	popModal = function(msg){
        $('#inok i').text(msg);
        $("#inok").show();
        setTimeout("$('#inok').fadeOut(1000)", 800);
    };
    
	</script>
</body>
</html>
