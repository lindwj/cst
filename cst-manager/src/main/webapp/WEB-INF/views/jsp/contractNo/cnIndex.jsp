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

<title>${webTitle }</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/contractNumber.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
	
	
	<script type="text/javascript">
function sbm(){
	
	$("#shareForm").submit();
}


function del(obj){
	$('#black1').fadeIn();
	//确定事件
	$("#surebtn").click(function(){
		$("#contractNoId").attr("value",$(obj).attr("id"));
		$("#shareForm").attr("action", "<%=path%>/contractNo/contractNoDelete.do");
		$("#shareForm").submit();
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#black1').fadeOut();
    });
}


function edit(obj){
	
	$("#contractNoId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/contractNo/contractNoAddEditIni.do");
	$("#shareForm").submit();
}

</script>
	
</head>
<body>

<div class="black" id="black1">

    <div class="fadebox">
        <div class="ordertitle">
            <h1>确认删除？</h1>
        </div>

        <div class="orderbtn">
            <input id="surebtn" class="btn-default" type="button" value="确定">
            <input id="canclebtn" class="btn-default" type="button" value="取消">
        </div>
    </div>
</div>

<div class="container">

        <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        <div class="content">


<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/contractNo/contractNoListPage.do">
            <div class="content-title">合同编号管理</div>

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

                <button type="button" class="btn-default" onclick="sbm()" id="searchbtn">搜索</button>

                <button type="button" class="btn-default" id="addbtn" onclick="javascript:location='<%=path%>/contractNo/contractNoAdd.do'">添加+</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">省份</th>
                            <th class="col2">城市</th>
                            <th class="col3">县区</th>
                            <th class="col4">店</th>
                            <th class="col5">合同编号数量</th>
                            <th class="col6">操作</th>
                        </tr>

                        </thead>
                        <tbody>
<input type="hidden" name="contractNoId" id="contractNoId" />
									<c:forEach var="item" items="${contractNoList}">
                            <tr>
                            
                            <td id="y1" class="col1">${item.provincestr}</td>
											<td id="y2" class="col2">${item.citystr}</td>
											<td id="y3" class="col3">${item.districtstr}</td>
											<td id="y4" class="col4">${item.shopIdstr}</td>
											<td id="y5" class="col5">${item.contractNoAmt}</td>
											
                                <td id="y10" class="col6"><input id="${item.contractNoId}" onclick="del(this)" class='delete' type='button' value='删除'><input class='modify' id="${item.contractNoId}" onclick="edit(this)" type='button' value='修改' ></td>
                            </tr>

</c:forEach>

                        </tbody>

<%@include file="../include/innerNavigation.jsp"%>
                    </table>
                


            </div>

</form>

        </div>

</div>

<script type="text/javascript">


var p='${contractNo.province}';
var c='${contractNo.city}';
var d='${contractNo.district}';
var s='${contractNo.shopId}';


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