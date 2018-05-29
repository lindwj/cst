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

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
	
<title>${webTitle }</title>

<script type="text/javascript">


function sbm(){
	//$('#file').empty();
	//$('#fileQueue').empty();
	$("#shareForm").attr("action", "<%=path%>/product/saveProduct.do");
	$("#shareForm").submit();
}


function del(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/product/delProduct.do");
	$("#shareForm").submit();
}


function editPic(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/productPic/productPicAddEditIni.do");
	$("#shareForm").submit();
}


function editArt(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/productAttribute/productAttributeAddEditIni.do");
	$("#shareForm").submit();
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
					action="<%=path%>/product/productForPicListPage.do">

					<div id="err" style="color: red">
					</div>
					<div class="cat_table">
						<h1>商品列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>图片</th>
										<th>名称</th>
										<th>价格</th>
										<th>原价</th>
										<th>编号</th>
										<th>类型</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" name="productUuid" id="productUuid" />
									<c:forEach var="item" items="${productList}">
										<tr>
											<td><img src="${item.pic}" width="50px" height="50px"/></td>
											<td>${item.name}</td>
											<td>${item.price}</td>
											<td>${item.costPrice}</td>
											<td>${item.code}</td>
											<td>${item.typeIdStr}</td>
											<td><c:if test="${item.status == -1}">已删除</c:if>
											<c:if test="${item.status == 0}">草稿</c:if>
											<c:if test="${item.status == 1}">已上架</c:if>
											<c:if test="${item.status == 2}">已下架</c:if></td>
											<td>
												<a href="#" id="${item.productUuid}" onclick="editPic(this)"><i class="page_icon detail_icon"></i>编辑图组</a>
												<a href="#" id="${item.productUuid}" onclick="editArt(this)"><i class="page_icon detail_icon"></i>编辑属性</a>
												</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

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
