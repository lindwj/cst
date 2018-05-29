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
					action="<%=path%>/contractNo/contractNoAddEdit.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">专营店合同编号已存在 </c:if>
						<c:if test="${errcode == 101 }">省市县，专营店，合同编号数量（最大6位数）   不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>修改账号</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>省份</th>
										<th>城市</th>
										<th>县区</th>
										<th>专营店</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="hidden" name="contractNoId" id="contractNoId"
											value="${contractNo.contractNoId }" /> <input type="hidden"
											name="dowhat" value="edit" /> ${contractNo.provincestr}</td>
										<td>${contractNo.citystr}</td>
										<td>${contractNo.districtstr}</td>
										<td>${contractNo.shopIdstr}</td>

									</tr>
								</tbody>


								<thead>
									<tr>

										<th>合同编号生成数量</th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" name="contractNoAmt" id="contractNoAmt" value="${contractNo.contractNoAmt}"/>个</td>
										<td></td>
										<td></td>
										<td></td>

									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><a href="#" onclick="sbm()"><i
												class="page_icon detail_icon"></i>保存</a></td>
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
