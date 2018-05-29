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

		$("#status").val($(obj).attr("st"));
		$("#shareForm").submit();
	}
	
	
	
	function yd(obj) {
		$("#shareForm").attr("action", "<%=path%>/preOrders/preOrdersListPageUser.do");
		$("#shareForm").submit();
	}
</script>

</head>
<body>
<body>
	<%@include file="../../../../resource/include/header.jsp"%>

	<form name="shareForm" id="shareForm" method="post"
		action="<%=path%>/orders/ordersListPage.do">
		<input type="hidden" name="status" id="status"
			value="${orders.status}" />

		<div class="content">
			<div class="panel">

				<div class="col-main">

					<div class="nav-status">

						<div class="item-status">
							<div <c:if test="${orders.status == 100}">class="selected"</c:if>
								st="100" onclick="chk(this);">
								<span>我的订单</span>
							</div>
							<span class="status-sep">|</span>
							<div <c:if test="${orders.status == 9}">class="selected"</c:if>
								st="9" onclick="chk(this);">
								<span>待付款</span>
							</div>
							<span class="status-sep">|</span>
							<div <c:if test="${orders.status == 1}">class="selected"</c:if>
								st="1" onclick="chk(this);">
								<span>待收货</span>
							</div>
							<span class="status-sep">|</span>
							<div <c:if test="${orders.status == 2}">class="selected"</c:if>
								st="2" onclick="chk(this);">
								<span>已签收</span>
							</div>
							<span class="status-sep">|</span>
							<div
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
								<th>单价(元)</th>
								<th>数量</th>
								<!--<th>商品操作</th>-->
								<th>实付款(元)</th>
								<th>交易状态</th>
								<th>交易操作</th>

							</tr>

						</tbody>
					</table>










					<c:forEach var="item" items="${ordersList}">
						<div class="item-content">

							<div class="item-title">

								<span class="item-date">${item.createDatestr}</span> <span>订单号：</span>
								<span>${item.code}</span>

							</div>

							<c:if test="${! item.productUuid.equals('0')}">
								<ul class="last-ul">

									<li class="td col1">
										<div class="item-img">
											<a
												href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${item.productUuid}"><img
												src="${item.pic}"></a>
										</div>
										<div style="margin-left: 90px;">
											<p class="item-detail-title">
												<a
													href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${item.productUuid}"><span>${item.subject}</span></a>
											<p>
										</div>
									</li>
									<li class="td col2"><p>${item.price}</p></li>
									<li class="td col3"><p>${item.capacity}</p></li>
									<li class="td col4">
										<div>
											<strong>${item.totalFromBdh}</strong> <br> <span
												style="color: #6c6c6c;">(含运费)</span>
										</div>

									</li>

									<li class="td col5">
										<div>
											<c:if test="${item.status == 9}">
												<p style="margin-bottom: 3px;">
													<span>待付款</span>
												</p>
											</c:if>
											<c:if test="${item.status == 1}">
												<p style="margin-bottom: 3px;">
													<span>待收货</span>
												</p>
											</c:if>
											<c:if test="${item.status == 2}">
												<p style="margin-bottom: 3px;">
													<span>已签收</span>
												</p>
											</c:if>
											<c:if test="${item.status == -1}">
												<p style="margin-bottom: 3px;">
													<span>交易关闭</span>
												</p>
											</c:if>

											<p style="margin-bottom: 3px;">
												<a href="<%=path%>/orders/ordersDetail.do?code=${item.code}">订单详情</a>
											</p>
										</div>
									</li>

									<li class="td col6">
										<div>
										
											<p style="margin-bottom: 3px;">
												<span> <c:if test="${item.status == 9}">
														<a target="_blank"
															href="<%=path%>/orders/ordersPay.do?code=${item.code}">去支付</a>
													</c:if>

												</span>
											</p>
										</div>
									</li>

<div class="float-clear"></div>
								</ul>
							</c:if>

							<%
								int i = 0;
							%>
							<c:if test="${item.productUuid.equals('0')}">
								<c:forEach var="ordersDetail" items="${item.ordersDetailList}">

									<ul <c:if test="i+1 == ${item.ordersDetailList.size()}" >class="last-ul"</c:if> >

										<li class="td col1">
											<div class="item-img">
												<a
													href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${ordersDetail.productUuid}"><img
													src="${ordersDetail.pic}"></a>
											</div>
											<div style="margin-left: 90px;">
												<p class="item-detail-title">
													<a
														href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${ordersDetail.productUuid}"><span>${ordersDetail.productName}</span></a>
												<p>
											</div>
										</li>
										<li class="td col2"><p>${ordersDetail.price}</p></li>
										<li class="td col3"><p>${ordersDetail.capacity}</p></li>
										<li class="td col4">
											<div>
												<%
													if (i == 0) {
												%>
												<strong>${item.totalFromBdh}</strong> <br> <span
													style="color: #6c6c6c;">(含运费)</span>
												<%
													}
												%>
											</div>

										</li>

										<li class="td col5">
											<div>
												<%
													if (i == 0) {
												%>
												<c:if test="${item.status == 9}">
													<p style="margin-bottom: 3px;">
														<span>待付款</span>
													</p>
												</c:if>
												<c:if test="${item.status == 1}">
													<p style="margin-bottom: 3px;">
														<span>待收货</span>
													</p>
												</c:if>
												<c:if test="${item.status == 2}">
													<p style="margin-bottom: 3px;">
														<span>已签收</span>
													</p>
												</c:if>
												<c:if test="${item.status == -1}">
													<p style="margin-bottom: 3px;">
														<span>交易关闭</span>
													</p>
												</c:if>

												<p style="margin-bottom: 3px;">
													<a
														href="<%=path%>/orders/ordersDetail.do?code=${item.code}">订单详情</a>
												</p>

												<%
													}
												%>
											</div>
										</li>

										<li class="td col6">
											<div>
												<p style="margin-bottom: 3px;">
													<span> <%
 	if (i == 0) {
 %> <c:if
															test="${item.status == 9}">
															<a class="payment go_pay" target="_blank"
																href="<%=path%>/orders/ordersPay.do?code=${item.code}">去支付</a>
														</c:if> <%
 	}
 %>
													</span>
												</p>
											</div>
										</li>
<div class="float-clear"></div>

									</ul>


									<%
										i++;
									%>
								</c:forEach>
							</c:if>


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
