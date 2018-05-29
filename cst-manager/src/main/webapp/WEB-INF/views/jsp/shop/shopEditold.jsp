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
					action="<%=path%>/shop/saveShop.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == '100' }">专营店已存在 </c:if>
						<c:if test="${errcode == '101' }">省市县 名称 编码 地址不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>修改专营店</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>省份</th>
										<th>城市</th>
										<th>县区</th>
										<th>街道</th>
										<th>状态</th>
										<th>名称</th>
										<th>简称</th>
										<th>编号</th>
										<th>地址</th>
										<th>操作</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="hidden" name="shopId" id="shopId"
											value="${shop.shopId }" /> <input type="hidden" name="dowhat"
											value="edit" /> ${shop.provincestr}</td>
										<td>${shop.citystr}</td>
										<td>${shop.districtstr}</td>
										<td>${shop.streetstr}</td>
										<td>
										<select name="status" id="selstatus">
												<option value="-1" <c:if test="${shop.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${shop.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${shop.status == 1}">selected="selected" </c:if> >已激活</option>
												<option value="2" <c:if test="${shop.status == 2}">selected="selected" </c:if> >已注销</option>
										</select>
										</td>
										<td><input type="text" name="name" id="name"
											value="${shop.name }" /></td>
											<td><input type="text" name="nickName" id="nickName"
											value="${shop.nickName }" /></td>
										<td><input type="text" name="code" id="code"
											value="${shop.code }" /></td>
										<td><input type="text" name="address" id="address"
											value="${shop.address }" /></td>

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
