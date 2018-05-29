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
					action="<%=path%>/stock/stockAddEdit.do">

					<div id="err" style="color: red">
					<c:if test="${errcode == 100 }">库存已存在 </c:if>
						<c:if test="${errcode == 101 }">至少一行 库存增量  不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>库存列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>图片</th>
										<th>名称</th>
										<th>前台库存</th>
										<th>前台库存增量</th>
										<th>店库存</th>
										<th>店库存增量</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody>
								
								<%int i=0; %>
									<c:forEach var="item" items="${stock.stockList}">
										<tr>
											<td>
											<input type="hidden" name ="stockList[<%=i%>].productUuid" id="productUuid" value="${item.productUuid}"/>
											<input type="hidden" name ="stockList[<%=i%>].stockId" id="stockId" value="${item.stockId}"/>
											<input type="hidden" name ="stockList[<%=i%>].shopId" id="shopId" value="${stock.shopId}"/>
											<input type="hidden" name ="stockList[<%=i%>].updateByAdm" id="updateByAdm" value="${stock.updateByAdm}"/>
											<img src="${item.pic}" width="50px" height="50px"/>
											</td>
											<td>
											${item.name}
											</td>
											<td>
											${item.showCapacity - item.sellCapacity}
											</td>
											
											<td><input type="text" name="stockList[<%=i%>].showCapacity" id="showCapacity" value="0"/></td>
											
											<td>
											${item.shopCapacity - item.sellCapacity}
											</td>
											
											<td><input type="text" name="stockList[<%=i%>].shopCapacity" id="shopCapacity" value="0"/></td>
											
											<td><select name="stockList[<%=i%>].status" id="selstatus">
												<option value="-1" <c:if test="${item.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${item.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${item.status == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${item.status == 2}">selected="selected" </c:if> >已下架</option>
										</select></td>
										</tr>
										<%i++; %>
									</c:forEach>
									
									
								</tbody>
							</table>
							
							<div><a href="#" id="" onclick="sbm()"><i class="page_icon detail_icon"></i><h1>批量保存</h1></a></div>

<%@include file="../include/innerNavigation.jsp"%>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

</body>
</html>
