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
		$("#shuidianId").attr("value",$(obj).attr("id"));
		$("#shareForm").attr("action", "<%=path%>/shuidian/shuidianDelete.do");
		$("#shareForm").submit();
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#black1').fadeOut();
    });
}

//修改专营店
function edit(obj){
	$("#shuidianId").attr("value",$(obj).attr("id"));
	$("#shareForm").attr("action", "<%=path%>/shuidian/shuidianAddEditIni.do");
	$("#shareForm").submit();
}


function bind(obj){
	
	$("#shuidianId").attr("value",$(obj).attr("id"));
	
	
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
					action="<%=path%>/shuidian/shuidianListPage.do">


        <div class="content">

            <div class="content-title">水电录入管理</div>

            <div class="search">

                <label class="search-label">分公司：</label>
                <select  id="selprovince"  name="fengsid" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select>

                <label class="search-label1">项目：</label>
                <select id="selcity" name="xmid" class="input-default"
											onchange="onSelectChange(this,'seldistrict');">
												<option value="">请选择</option>
										</select>


                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>

                <button type="button" class="btn-default" id="addbtn" onclick="javascript:location='<%=path%>/shuidian/shuidianAdd.do'">添加+</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">分公司</th>
                            <th class="col2">项目</th>
                            <th class="col1">年</th>
                            <th class="col2">月</th>
                            <th class="col3">水费</th>
                            <th class="col4">水量</th>
                            <th class="col5">电费</th>
                            <th class="col6">电量</th>
                            <th class="col7">备注</th>
                            <th class="col8">操作</th>
                        </tr>

                        </thead>
                        <tbody>

                            
                            <input type="hidden" name="shuidianId" id="shuidianId" />
									<c:forEach var="item" items="${shuidianList}">
										<tr>
											<td id="y1" class="col1">${item.fengsname}</td>
											<td id="y2" class="col2">${item.xmname}</td>
											<td id="y1" class="col1">${item.nian}</td>
											<td id="y2" class="col2">${item.yue}</td>
											<td id="y3" class="col3">${item.shuifei}</td>
											<td id="y4" class="col4">${item.shuiliang}</td>
											<td id="y5" class="col5">${item.dianfei}</td>
											<td id="y7" class="col6">${item.dianliang}</td>
											<td id="y9" class="col7">${item.note}</td>
											<td id="y10" class="col8">
											<input class='delete' id="${item.shuidianId}" onclick="del(this)" type='button' value='删除'>
											<input class='modify' type='button' id="${item.shuidianId}" onclick="edit(this)" value='修改' >
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

var p='${shuidian.fengsid}';
var c='${shuidian.xmid}';
var d='0';

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