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

	
<title>${webTitle }</title>

<script type="text/javascript">


	function sbm() {
		$("#shareForm").submit();
	}
	
	
	function del(obj){
		
		$("#productAttributeIdfordel").attr("value",$(obj).attr("id"));
		
		
		$("#shareForm").attr("action", "<%=path%>/productAttribute/delProductAtr.do");
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
					action="<%=path%>/productAttribute/saveProductAtr.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">已存在 </c:if>
						<c:if test="${errcode == 101 }">名称 属性值  单位  排序   不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>属性列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>名称</th>
										<th>属性值</th>
										<th>单位</th>
										<th>排序</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" name="productUuid" id="productUuid" value="${productAttribute.productUuid}" />
								<input type="hidden" name="productAttributeId" id="productAttributeIdfordel" value="" />
								<input type="hidden" name="dowhat" id="dowhat" value="${productAttribute.dowhat}" />
								
								<c:if test="${productAttribute.dowhat == 'add'}">
								<c:forEach var="s"  begin="0" end="4">

								<tr>
											<td><input type="text" name="productAttributeList[${s}].name" id="" value=""/></td>
											<td>
											<input type="text" name="productAttributeList[${s}].value" id="" value=""/>
											<input type="hidden" name="productAttributeList[${s}].productUuid" id="" value="${productAttribute.productUuid}"/>
											 </td>
											<td><input type="text" name="productAttributeList[${s}].unit" id="" value=""/></td>
											<td><input type="text" name="productAttributeList[${s}].sort" id="" value="0"/></td>
											<td><select name="productAttributeList[${s}].status" id="selstatus">
												<option value="-1"  >已删除</option>
												<option value="0"  >草稿</option>
												<option value="1" selected="selected"  >已上架</option>
												<option value="2"  >已下架</option>
										</select></td>
										<td>
												</td>
										</tr>
										
								</c:forEach>
								
								</c:if>
								
								<c:if test="${productAttribute.dowhat == 'edit'}">
								<%int i=0; %>
									<c:forEach var="item" items="${productAttribute.productAttributeList}">
										<tr>
											<td><input type="text" name="productAttributeList[<%=i%>].name" id="name" value="${item.name}"/></td>
											<td>
											<input type="hidden" name="productAttributeList[<%=i%>].productAttributeId" id="" value="${item.productAttributeId}"/>
											<input type="text" name="productAttributeList[<%=i%>].value" id="" value="${item.value}"/>
											</td>
											<td><input type="text" name="productAttributeList[<%=i%>].unit" id="unit" value="${item.unit}"/></td>
											<td><input type="text" name="productAttributeList[<%=i%>].sort" id="sort" value="${item.sort}"/></td>
											<td><select name="productAttributeList[<%=i%>].status" id="selstatus">
												<option value="-1" <c:if test="${item.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${item.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${item.status == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${item.status == 2}">selected="selected" </c:if> >已下架</option>
										</select></td>
										<td>
												<a href="#" id="${item.productAttributeId}" onclick="del(this)"><i class="page_icon detail_icon"></i>删除</a>
												</td>
										</tr>
										<%i++; %>
									</c:forEach>
									
									
									<% if(i<4){ %>
									<c:forEach var="s"  begin="<%=i %>" end="4">

								<tr>
											<td><input type="text" name="productAttributeList[${s}].name" id="" value=""/></td>
											<td>
											<input type="text" name="productAttributeList[${s}].value" id="" value=""/>
											<input type="hidden" name="productAttributeList[${s}].productUuid" id="" value="${productAttribute.productUuid}"/>
											 </td>
											<td><input type="text" name="productAttributeList[${s}].unit" id="" value=""/></td>
											<td><input type="text" name="productAttributeList[${s}].sort" id="" value="0"/></td>
											<td><select name="productAttributeList[${s}].status" id="selstatus">
												<option value="-1"  >已删除</option>
												<option value="0"  >草稿</option>
												<option value="1" selected="selected"  >已上架</option>
												<option value="2"  >已下架</option>
										</select></td>
										<td>
												</td>
										</tr>
										
								</c:forEach>
								<%} %>
									
									</c:if>
								</tbody>
							</table>
							
							<div><a href="#" id="" onclick="sbm()"><i class="page_icon detail_icon"></i><h1>批量保存</h1></a></div>


						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

</body>
</html>
