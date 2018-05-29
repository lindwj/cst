<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../include/tag.jsp"%>
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
	<link rel="stylesheet" type="text/css"
	href="<%=path%>/resource/uploadify/uploadify.css">	
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

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
	
	
<title>${webTitle }</title>

<script type="text/javascript">

	function sbm() {
		$("#shareForm").submit();
	}
</script>
</head>

<body>
	<div class="container">
		<%@ include file="../include/top.jsp"%>
		<!--main-->
		<div class="cat_main">

			<%@ include file="../include/left.jsp"%>
			<div class="cat_m_right">
				<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/preOrders/preOrdersAddEdit.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == '100' }">合同编号已存在 </c:if>
						<c:if test="${errcode == '101' }">每项必填，合同编号不能超过分配数量</c:if>
					</div>
					<div class="cat_table">
						<h1>预订订单</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>商品</th>
										<th>消费者电商账号</th>
										<th>收货人</th>
										<th>打款账户</th>
										<th>收货人地址</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="hidden" name="preOrdersId" id="preOrdersId"
											value="${preOrders.preOrdersId }" /> <input type="hidden" name="dowhat"
											value="edit" />
											
											<select name="productUuid" id="productUuid"
										>
												<c:forEach var="item" items="${productList}">
												<option value="${item.productUuid}"  <c:if test="${preOrders.productUuid == item.productUuid}">selected="selected" </c:if>>${item.name}</option>
												</c:forEach>
										</select>
											</td>
											
											<td><input type="text" name="userPhone" id="userPhone"
											value="${preOrders.userPhone }" /></td>
											<td><input type="text" name="receiveName" id="receiveName"
											value="${preOrders.receiveName }" /></td>
											<td><input type="text" name="receiveAccount" id="receiveAccount"
											value="${preOrders.receiveAccount }" /></td>
											<td><input type="text" name="receiveAddress" id="receiveAddress"
											value="${preOrders.receiveAddress }" /></td>

									</tr>
								</tbody>
								
								
								
								<thead>
									<tr>
									<th>收货人手机</th>
										<th>配送周期</th>
										<th>贵宾专员</th>
										<th>合同编号（已分配:${contractNoAmt}个）</th>

									</tr>
								</thead>
								<tbody>
									<tr>
											
											<td><input type="text" name="receiveMobile" id="receiveMobile"
											value="${preOrders.receiveMobile }" /></td>
											<td>每<input type="text" name="receivePeriod" id="receivePeriod"
											value="${preOrders.receivePeriod }" />个月一次</td>
											<td><select name="saleManager" id="saleManager"
										>
												<c:forEach var="item" items="${managerList}">
												<option value="${item.managerId}"  <c:if test="${preOrders.saleManager == item.managerId}">selected="selected" </c:if>>${item.name}</option>
												</c:forEach>
										</select></td>
											<td><input type="text" name="contractNo" id="contractNo"
											value="${preOrders.contractNo }" /></td>
									</tr>
								</tbody>
							</table>
							
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

</body>
</html>
