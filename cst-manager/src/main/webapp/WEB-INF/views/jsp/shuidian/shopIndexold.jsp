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
	
	$("#shareForm").attr("action", "<%=path%>/shop/saveShop.do");
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
					action="<%=path%>/shop/shopListPage.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">专营店已存在 </c:if>
						<c:if test="${errcode == 101 }">省市县街道，名称，编号 ,地址  不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>新增专营店</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>省份</th>
										<th>城市</th>
										<th>县区</th>
										<th>街道</th>
										<th>名称</th>
										<th>简称</th>
										<th>编号</th>
										<th>地址</th>
									</tr>
								</thead>
								<input type="hidden" name="dowhat" value="add" />
								<tbody>
									<tr>
										<td><select  id="selprovince" name="province"
											onchange="onSelectChange(this,'selcity');"></select>
											</td>
										<td><select id="selcity" name="city"
											onchange="onSelectChange(this,'seldistrict');">
												<option value="">请选择</option>
										</select>
										</td>
										<td><select name="district" id="seldistrict"
										onchange="onSelectChange(this,'selstreet');">
												<option value="">请选择</option>
										</select>
										</td>
										<td><select name="street" id="selstreet"
										>
												<option value="">请选择</option>
										</select>
										</td>
										<td><input type="text" name="name" id="name" /></td>
										<td><input type="text" name="nickName" id="nickName" /></td>
										<td><input type="text" name="code" id="code" /></td>
										<td><input type="text" name="address" id="address" /></td>
										
									</tr>
									<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td><a href="#" onclick="sbm()" ><i
												class="page_icon detail_icon"></i>保存</a></td>
												</tr>
								</tbody>
							</table>
						</div>
						
					</div>
					<div class="cat_table">
						<h1>已添加专营店列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>省份</th>
										<th>城市</th>
										<th>县区</th>
										<th>街道</th>
										<th>名称</th>
										<th>简称</th>
										<th>编号</th>
										<th>地址</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" name="shopId" id="shopId" />
									<c:forEach var="item" items="${shopList}">
										<tr>
											<td>${item.provincestr}</td>
											<td>${item.citystr}</td>
											<td>${item.districtstr}</td>
											<td>${item.streetstr}</td>
											<td>${item.name}</td>
											<td>${item.nickName}</td>
											<td>${item.code}</td>
											<td>${item.address}</td>
											<td><c:if test="${item.status == -1}">已删除</c:if>
											<c:if test="${item.status == 0}">草稿</c:if>
											<c:if test="${item.status == 1}">已激活</c:if>
											<c:if test="${item.status == 2}">已注销</c:if></td>
											<td><a href="#" id="${item.shopId}" onclick="del(this)" ><i class="page_icon detail_icon"></i>删除</a>
												<a href="#" id="${item.shopId}" onclick="edit(this)"><i class="page_icon detail_icon"></i>修改</a></td>
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

	<script type="text/javascript">
function onSelectChange(obj,toSelId){
 setSelect(obj.value,toSelId);
}
function setSelect(fromSelVal,toSelId){
 //alert(document.getElementById("province").selectedIndex);
 document.getElementById(toSelId).innerHTML="";
 jQuery.ajax({
  url: "<%=path%>/nation/getNation.do",
				cache : false,
				data : "parent=" + fromSelVal,
				success : function(data) {
					createSelectObj(data, toSelId);
				}
			});
		}
		function createSelectObj(data, toSelId) {
			var arr = jsonParse(data);
			if (arr != null && arr.length > 0) {
				var obj = document.getElementById(toSelId);
				obj.innerHTML = "";
				var nullOp = document.createElement("option");
				nullOp.setAttribute("value", "");
				nullOp.appendChild(document.createTextNode("请选择"));
				obj.appendChild(nullOp);
				for ( var o in arr) {
					var op = document.createElement("option");
					op.setAttribute("value", arr[o].id);
					//op.text=arr[o].name;//这一句在ie下不起作用，用下面这一句或者innerHTML
					if (toSelId == 'selprovince') {
						op
								.appendChild(document
										.createTextNode(arr[o].province));
					} else if (toSelId == 'selcity') {
						op.appendChild(document.createTextNode(arr[o].city));
					} else if (toSelId == 'seldistrict') {
						op
								.appendChild(document
										.createTextNode(arr[o].district));
					}else if (toSelId == 'selstreet') {
						op
						.appendChild(document
								.createTextNode(arr[o].street));
					}
					obj.appendChild(op);
				}
			}
		}
		setSelect('1', 'selprovince');
	</script>
</body>
</html>
