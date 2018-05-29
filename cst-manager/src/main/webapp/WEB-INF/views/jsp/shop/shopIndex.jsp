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
    <link href="<%=path%>/resource/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resource/css/franchiseStore.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resource/common/iconfont.css"/>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>


<script type="text/javascript">
//搜索专营店
function sbm(){
	$("#shareForm").submit();
}

//删除专营店
function del(obj){
	$('#black1').fadeIn();
	//确定事件
	$("#surebtn").click(function(){
		$("#shopId").attr("value",$(obj).attr("id"));
		$("#shareForm").attr("action", "<%=path%>/shop/delShop.do");
		$("#shareForm").submit();
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#black1').fadeOut();
    });
}

//修改专营店
function edit(obj){
	$("#shopId").attr("value",$(obj).attr("id"));
	$("#shareForm").attr("action", "<%=path%>/shop/shopEditIni.do");
	$("#shareForm").submit();
}


function bind(obj){
	
	$("#shopId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/nation/getNationList.do");
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
        
        <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/shop/shopListPage.do">


        <div class="content">

            <div class="content-title">专营店管理</div>

            <div class="search">

                <label class="search-label">省份：</label>
                <select  id="selprovince"  name="province" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select>

                <label class="search-label1">城市：</label>
                <select id="selcity" name="city" class="input-default"
											onchange="onSelectChange(this,'seldistrict');">
												<option value="">请选择</option>
										</select>

                <label class="search-label1">县区：</label>
                <select name="district" id="seldistrict" class="input-default"
										onchange="onSelectChange(this,'selstreet');">
												<option value="">请选择</option>
										</select>

                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>

                <button type="button" class="btn-default" id="addbtn" onclick="javascript:location='<%=path%>/shop/shopAdd.do'">添加+</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">省份</th>
                            <th class="col2">城市</th>
                            <th class="col3">县区</th>
                            <th class="col4">街道</th>
                            <th class="col5">名称</th>
                            <th class="col6">编号</th>
                            <th class="col7">状态</th>
                            <th class="col8">操作</th>
                        </tr>

                        </thead>
                        <tbody>

                            
                            <input type="hidden" name="shopId" id="shopId" />
									<c:forEach var="item" items="${shopList}">
										<tr>
											<td id="y1" class="col1">${item.provincestr}</td>
											<td id="y2" class="col2">${item.citystr}</td>
											<td id="y3" class="col3">${item.districtstr}</td>
											<td id="y4" class="col4">${item.streetstr}</td>
											<td id="y5" class="col5">${item.name}</td>
											<td id="y7" class="col6">${item.code}</td>
											<td id="y9" class="col7"><c:if test="${item.status == -1}">已删除</c:if>
											<c:if test="${item.status == 0}">草稿</c:if>
											<c:if test="${item.status == 1}">已激活</c:if>
											<c:if test="${item.status == 2}">已注销</c:if></td>
											<td id="y10" class="col8">
											<input class='delete' id="${item.shopId}" onclick="del(this)" type='button' value='删除'>
											<input class='modify' type='button' id="${item.shopId}" onclick="edit(this)" value='修改' >
											<input class='bound' type='button' value='绑定街道' id="${item.shopId}" onclick="bind(this)">
												</td>
										</tr>
									</c:forEach>



                        </tbody>

                    </table>
                

<%@ include file="../include/innerNavigation.jsp"%>
            </div>



        </div>
        
        
</form>
</div>

<script type="text/javascript">

var p='${shop.province}';
var c='${shop.city}';
var d='${shop.district}';

//选择级联地址
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
				
				if (toSelId == 'selstreet') {
					$("#"+toSelId).hide();
				}
				
				
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
					}else if (toSelId == 'selstreet') {
						if(arr[o].id==s){
							op.setAttribute("selected", 'selected');
						}
						op
						.appendChild(document
								.createTextNode(arr[o].street));
					}
					obj.appendChild(op);
				}
			}else{
				if (toSelId == 'selstreet') {
					var obj = document.getElementById(toSelId);
					$("#"+toSelId).hide();
				}
			}
		}
		setSelect('1', 'selprovince');
		
		if(p.length!=0){
		setSelect(p, 'selcity');
		setSelect(c, 'seldistrict');
		}
		
		
		
	</script>

</body>
</html>