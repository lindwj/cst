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
	var str="";
	
	
	$("input[id*='chk']").each(function() {
		
		if ("checked" == $(this).attr("checked")) {
	    

		if(str==""){
			str=$(this).attr("fname");
		}else{
			str=str+","+$(this).attr("fname");
		}
		
		}
 });
		
	$("#files").attr("value",str);
	$("#shareForm").attr("action", "<%=path%>/batchDownload/execute.do");
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
					action="<%=path%>/indexBanner/indexBannerListPage.do">

<input type="hidden" name="files" id="files" />
        <div class="content">

            <div class="content-title">文件管理</div>

            <div class="search">

                <label class="search-label">分公司：</label>
                <select  id="selprovince"  name="sort" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select>



                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>

                <button type="button" class="btn-default" id="addbtn" onclick="edit(this)">打包下载</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">选择</th>
                            <th class="col1">分公司</th>
                            <th class="col2">文件名</th>
                            
                            <th class="col8">状态</th>
                        </tr>

                        </thead>
                        <tbody>

                            
									<c:forEach var="item" items="${indexBannerList}">
										<tr>
											<td id="y1" class="col1"><input type="checkbox" id="chk${item.indexBannerId}" fname="${item.bannerUrl}"></td>
											<td id="y1" class="col1">${item.productUrl}</td>
											<td id="y2" class="col2">${item.bannerUrl}</td>
											<td id="y1" class="col1" id="${item.indexBannerId}">已上传</td>
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