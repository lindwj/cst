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
	
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/DatePicker/WdatePicker.js"></script>
	
<title>${webTitle }</title>

<script type="text/javascript">


function sbm(){
	$("#shareForm").attr("action", "<%=path%>/preOrders/preOrdersAddEdit.do");
	$("#shareForm").submit();
}


function sel(){
	$("#shareForm").submit();
}


function del(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/product/delProduct.do");
	$("#shareForm").submit();
}


function edit(obj){
	
	$("#preOrdersId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/preOrders/preOrdersAddEditIniSel.do");
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
					action="<%=path%>/preOrders/preOrdersListPageSel.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == '100' }">合同编号已存在 </c:if>
						<c:if test="${errcode == '101' }">每项必填，合同编号不能超过分配数量</c:if>
					</div>
					
					
					<div class="cat_table">
						<h1>订单查询</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
									<th>省
											</th>
										<th>市
										</th>
										<th>县
										</th>
										<th>店
										</th>
									
									
										<th>开始日期</th>
										<th>结束日期</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										
										<td><select  id="selprovince" 
											onchange="onSelectChange(0,this,'selcity');"></select>
											</td>
										<td><select id="selcity" 
											onchange="onSelectChange(0,this,'seldistrict');">
												<option value="">请选择</option>
										</select>
										</td>
										<td><select  id="seldistrict"
										onchange="onSelectChange(2,this,'selshopId');">
												<option value="">请选择</option>
										</select>
										</td>
										<td><select name="shopId" id="selshopId"
										>
												<option value="">请选择</option>
										</select>
										</td>
										
										<td><input type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="beginTime" id="beginTime" />
										</td>
										<td><input type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="endTime" id="endTime" />
										</td>
										
											<td><a href="#" onclick="sel()"><i
												class="page_icon detail_icon"></i>查询</a></td>

									</tr>
								</tbody>
							</table>
						</div>

					</div>
					
					
					
					<div class="cat_table">
						<h1>已添加预订订单列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>名称</th>
										<th>合同编号</th>
										<th>申请人</th>
										<th>申请人电话</th>
										<th>收货人</th>
										<th>收货人电话</th>
										<th>贵宾专员</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" name="preOrdersId" id="preOrdersId" />
									<c:forEach var="item" items="${preOrdersList}">
										<tr>
											<td>${item.productName}</td>
											<td>${item.contractNo}</td>
											<td>${item.applyName}</td>
											<td>${item.applyPhone}</td>
											<td>${item.receiveName}</td>
											<td>${item.receiveMobile}</td>
											<td>${item.managerName}</td>
											<td>
											<c:if test="${item.status == 0}">待处理</c:if>
											<c:if test="${item.status == 1}">已签约</c:if>
											</td>
											<td>
												<a href="#" id="${item.preOrdersId}" onclick="edit(this)"><i class="page_icon detail_icon"></i>查看</a></td>
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
function onSelectChange(fromset,obj,toSelId){
 setSelect(fromset,obj.value,toSelId);
}
function setSelect(fromset,fromSelVal,toSelId){
 //alert(document.getElementById("province").selectedIndex);
 document.getElementById(toSelId).innerHTML="";
 jQuery.ajax({
  url: "<%=path%>/nation/getNation.do",
				cache : false,
				data : "parent=" + fromSelVal+"&isStreet="+fromset,//isStreet=2 标识不是查询街道 而是查询专营店
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
					}else if (toSelId == 'selshopId') {
						op
						.appendChild(document
								.createTextNode(arr[o].street));
					}
					
					
					obj.appendChild(op);
				}
			}
		}
		setSelect(0,'1', 'selprovince');
	</script>

</body>
</html>
