<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<link rel="icon" type="images/png"
	href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
<link rel="stylesheet"
	href="<%=path%>/resource/components/header/header.css" />
<link rel="stylesheet"
	href="<%=path%>/resource/components/header/footer.css" />
<link rel="stylesheet" href="<%=path%>/resource/css/ItemList.css" />

<script src="<%=path%>/resource/components/jquery/jquery-3.0.0.min.js"></script>
<script src="<%=path%>/resource/scripts/ItemList.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>

<script type="text/javascript">
	function chk(obj) {
		$("#shareForm").attr("action", "<%=path%>/orders/ordersListPage.do");
		$("#status").val($(obj).attr("st"));
		$("#shareForm").submit();
	}
	
	
	function yd(obj) {
		$("#shareForm").submit();
	}
</script>

</head>
<body>
<body>
	<%@include file="../../../../resource/include/header.jsp"%>

	<form name="shareForm" id="shareForm" method="post"
		action="<%=path%>/preOrders/preOrdersListPageUser.do">
		<input type="hidden" name="status" id="status"
			 />

		<div class="content">
			<div class="panel">

				<div class="col-main">

					<div class="nav-status">

						<div class="item-status">
							<div 
								st="100" onclick="chk(this);">
								<span>所有订单</span>
							</div>
							<span class="status-sep">|</span>
							<div 
								st="9" onclick="chk(this);">
								<span>待付款</span>
							</div>
							<span class="status-sep">|</span>
							<div
								st="1" onclick="chk(this);">
								<span>待收货</span>
							</div>
							<span class="status-sep">|</span>
							<div 
								st="2" onclick="chk(this);">
								<span>已签收</span>
							</div>
							<span class="status-sep">|</span>
							<div class="selected"
								st="11" onclick="yd(this);">
								<span>预定订单</span>
							</div>
						</div>

					</div>

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
										<th>操作</th>
								

							</tr>

						</tbody>
					</table>










					<c:forEach var="item" items="${preOrdersList}">
						<div class="item-content">

							<div class="item-title">

								<span class="item-date">${item.updateTimeStr}</span> <span>合同编号：</span>
								<span>${item.contractNo}</span>

							</div>

								<ul class="last-ul">

									<li class="td col1">
										<div class="item-img">
											<a
												href="javacript:void(0);"><img
												src="${item.pic}"></a>
										</div>
										<div style="margin-left: 90px;">
											<p class="item-detail-title">
												<span>${item.productName}</span>
											<p>
										</div>
									</li>
									<li class="td col2"><p>${item.receiveName}</p></li>
									<li class="td col3"><p>${item.applyName}</p></li>
									<li class="td col4">
									<p>${item.managerName}</p>

									</li>

									<li class="td col5">
										<div>
										
										<c:if test="${item.status == 0}">
										<p style="margin-bottom: 3px;">
													<span>待处理</span>
												</p>
										</c:if>
										
										
											<c:if test="${item.status == 1}">
											<p style="margin-bottom: 3px;">
													<span>已签约</span>
												</p>
											</c:if>
											
										</div>
									</li>

									<li class="td col6">
										<div>
										
											<p style="margin-bottom: 3px;">
												<span> 
												<a href="<%=path%>/preOrders/preOrdersDetail.do?preOrdersId=${item.preOrdersId}">订单详情</a>
												</span>
											</p>
										</div>
									</li>

<div class="float-clear"></div>
								</ul>

						</div>


					</c:forEach>


					<div class="float-clear"></div>


<%@include file="../../../../resource/include/innerNavigation.jsp"%>
				</div>



			</div>
			
		</div>









		
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../../../resource/include/footer.jsp"%>


</body>
</html>
