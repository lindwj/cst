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
	$("#shareForm").attr("action", "<%=path%>/productCfg/saveProductCfg.do");
	$("#shareForm").submit();
}


function del(obj){
	
	$("#productCfgId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/productCfg/delProductCfg.do");
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
					action="<%=path%>/productCfg/productCfgListPage.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">商品uuid 不能为空，排序大于0</c:if>
					</div>
					<div class="cat_table">
						<h1>新增-预订热门推荐</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										
										<th>商品uuid</th>
										<th>排序</th>
										<th>类型</th>
									</tr>
								</thead>
								<input type="hidden" name="dowhat" value="add" />
								<tbody>
									<tr>
										<td><input type="text" name="productUuid" id="productUuid" />
										<input type="hidden" name="productCfgId" id="productCfgId" />
											</td>
										<td><input type="text" name="sort" id="sort" value="0"/>
										</td>
										<td><select name="type" id="seltypeId"
										>
												<c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}">${item.parameterName}</option>
												</c:forEach>
										</select></td>
										
									</tr>
									<tr>
									<td></td>
									<td></td>
									<td></td>
												</tr>
								</tbody>
							</table>
							<div>
									<a href="#" onclick="sbm()"><i
												class="page_icon detail_icon"></i><h1 style="color:blue"> 保存</h1></a>
									</div>
						</div>
						
					</div>
					<div class="cat_table">
						<h1>已添加列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>图片</th>
										<th>名称</th>
										<th>类型</th>
										<th>排序</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${productCfgList}">
										<tr>
											<td><img src="<%=path%>/resource/upload/product/${item.pic}" width="50px" height="50px"/></td>
											<td>${item.name}</td>
											<td>${item.typeStr}</td>
											<td>${item.sort}</td>
											<td><c:if test="${item.state == -1}">已删除</c:if>
											<c:if test="${item.state == 0}">草稿</c:if>
											<c:if test="${item.state == 1}">已上架</c:if>
											<c:if test="${item.state == 2}">已下架</c:if></td>
											<td><a href="#" id="${item.productCfgId}" onclick="del(this)" ><i class="page_icon detail_icon"></i>删除</a>
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
