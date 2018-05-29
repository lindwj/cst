<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../include/tag.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <!--<link rel="icon" href="/static/images/favicon.ico?v=1" type="image/x-icon"/>-->



 <title>${webTitle }</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/reserveSearch.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/DatePicker/WdatePicker.js"></script>



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

       <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        <div class="content">
        
        <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/preOrders/preOrdersListPageSel.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == '100' }">合同编号已存在 </c:if>
						<c:if test="${errcode == '101' }">每项必填，合同编号不能超过分配数量</c:if>
					</div>

            <div class="content-title">订单管理</div>

            <div class="search">

                <label class="search-label">省份：</label>
                <select  id="selprovince" name="province" class="input-default"
											onchange="onSelectChange(0,this,'selcity');"></select>

                <label class="search-label1">城市：</label>
                <select id="selcity" name="city" class="input-default"
											onchange="onSelectChange(0,this,'seldistrict');">
												<option value="">请选择</option>
										</select>

                <label class="search-label1">县区：</label>
                 <select name="district" id="seldistrict" class="input-default"
										onchange="onSelectChange(2,this,'selshopId');">
												<option value="">请选择</option>
										</select>
                <label class="search-label1">专营店：</label>
                <select name="shopId" id="selshopId" class="input-default"
										>
												<option value="">请选择</option>
										</select>


                <label class="search-label1">开始日期：</label>
                <input type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="beginTime" value="${preOrders.beginTime}" id="beginTime" />


                <label class="search-label1">开始日期：</label>
                <input type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="endTime" value="${preOrders.endTime}" id="endTime" />

                <button type="button" class="btn-default" onclick="sel()" id="searchbtn">搜索</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">名称</th>
                            <th class="col2">合同编号</th>
                            <th class="col3">申请人</th>
                            <th class="col4">申请人电话</th>
                            <th class="col5">收货人</th>
                            <th class="col6">收货人电话</th>
                            <th class="col7">贵宾专员</th>
                            <th class="col8">状态</th>
                            <th class="col9">操作</th>
                            <!--<th class="col7">配送周期</th>-->
                            <!--<th class="col8">贵宾专员</th>-->
                            <!--<th class="col9">合同编号</th>-->
                            <!--<th class="col10">申请姓名</th>-->
                            <!--<th class="col11">申请电话</th>-->
                            <!--<th class="col12">状态</th>-->
                            <!--<th class="col13">操作</th>-->
                            <!--<th class="col8">状态</th>-->
                            <!--<th class="text-right">数据修改</th>-->
                        </tr>

                        </thead>
                        <tbody>

<input type="hidden" name="preOrdersId" id="preOrdersId" />
<c:forEach var="item" items="${preOrdersList}">
                        <tr>
                            <td id="y1" class="col1">${item.productName}</td>
                            <td id="y2" class="col2">${item.contractNo}</td>
                            <td id="y3" class="col3">${item.applyName}</td>
                            <td id="y4" class="col4">${item.applyPhone}</td>
                            <td id="y5" class="col5">${item.receiveName}</td>
                            <td id="y6" class="col6">${item.receiveMobile}</td>
                            <td id="y7" class="col7">${item.managerName}</td>
                            <td id="y8" class="col8"><c:if test="${item.status == 0}">待处理</c:if>
											<c:if test="${item.status == 1}">已签约</c:if></td>
                            <td id="y10" class="col9"><input class='delete' type='button' id="${item.preOrdersId}"  onclick="edit(this)" value='查看'></td>
                        </tr>
                        </c:forEach>
                        


                        </tbody>

                    </table>
                    <%@include file="../include/innerNavigation.jsp"%>

                


            </div>

</form>

        </div>

</div>
<script type="text/javascript">


var p='${preOrders.province}';
var c='${preOrders.city}';
var d='${preOrders.district}';
var s='${preOrders.shopId}';


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
						if(arr[o].id==p){
							op.setAttribute("selected", 'selected');
						}
						op
								.appendChild(document
										.createTextNode(arr[o].province));
					} else if (toSelId == 'selcity') {
						if(arr[o].id==c){
							op.setAttribute("selected", 'selected');
						}
						op.appendChild(document.createTextNode(arr[o].city));
					} else if (toSelId == 'seldistrict') {
						if(arr[o].id==d){
							op.setAttribute("selected", 'selected');
						}
						op
								.appendChild(document
										.createTextNode(arr[o].district));
					}else if (toSelId == 'selshopId') {
						
						if(arr[o].id==s){
							op.setAttribute("selected", 'selected');
						}
						op
						.appendChild(document
								.createTextNode(arr[o].street));
					}
					
					
					obj.appendChild(op);
				}
			}
		}
		setSelect(0,'1', 'selprovince');
		
		if(p.length!=0){
			setSelect(0,p, 'selcity');
			setSelect(0,c, 'seldistrict');
			setSelect(2,d, 'selshopId');
			}
	</script>
</body>
</html>