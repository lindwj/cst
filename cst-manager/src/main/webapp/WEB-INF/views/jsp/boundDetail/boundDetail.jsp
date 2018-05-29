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
    <link href="<%=path%>/resource/css/boundDetail.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resource/common/iconfont.css"/>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
	
<script src="<%=path%>/resource/scripts/boundDetail.js"></script>


<script type="text/javascript">
function sbm(){
	$("#shareForm").submit();
}


function save(){
	console.log($("#checkbox").attr("checked"));
	if($("#checkbox").attr("checked")==undefined){
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else{
		$("#shareForm").attr("action", "<%=path%>/bindStreet/bindStreetAddEdit.do");
		$("#shareForm").submit();
	}
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

        <%@ include file="../include/top2.jsp"%>


        <%@ include file="../include/left2.jsp"%>
        
        <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/nation/getNationList.do">
					<div id="errorm4">至少勾选一项</div>
					

        <div class="content">

            <div class="content-title">绑定街道</div>

<div id="err" style="color: red">
						<c:if test="${nation.code == 100 }">部分街道已经被绑定 </c:if>
						<c:if test="${nation.code == 101 }">至少勾选一个街道</c:if>
					</div>

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


            </div>

            <div class="showstreat">
                <h1>
                <c:forEach var="street" items="${bindList}">
                <span>${street.district}：</span>${street.street}&nbsp;&nbsp;&nbsp;。&nbsp;&nbsp;&nbsp;
                <input type="hidden" id="${street.parent}" value="${street.code}">
                
                </c:forEach>
                </h1>
            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1"><input id="checkall" onclick="chkall(this)" class="checkbox" type="checkbox">全选</th>
                            <th class="col2">省份</th>
                            <th class="col3">城市</th>
                            <th class="col4">县区</th>
                            <th class="col5">街道</th>
                        </tr>

                        </thead>
                        <tbody>
<input type="hidden" name="shopId" id="shopId" value="${nation.shopId}"/>
						<%int i=0; %>
						<c:forEach var="item" items="${naList}">
                            <tr>
                                <td class="col1">
                                    <input name="streetList[<%=i%>].street" value="${item.id}" class="checkbox" id="checkbox" type="checkbox">
                                </td>
                                <td class="col2">${item.province}</td>
                                <td class="col3">${item.city}</td>
                                <td class="col4">${item.district}</td>
                                <td class="col5">${item.street}</td>

                            </tr>

								<%i++; %>
						</c:forEach>

                        </tbody>


                        <tfoot>
                        <tr>

                            <td colspan="5">

                                <div class="pull-right page-box">
                                    <div class="pagination-goto">                                        
                                        <button type="button" style="float:right;" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/shop/shopListPage.do'">返回上一级</button>
                                        <button type="button" class="samestyle" style="height:32px;margin:0 10px;" id="saveBtn" onclick="save()">保存</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tfoot>

                    </table>

                


            </div>

        </div>
</form>

<c:if test="${naList != null && naList.size() > 0 }">
<script type="text/javascript">
chkBind('${nation.district}');
</script>
</c:if>

</div>
<script type="text/javascript">



var p='${nation.province}';
var c='${nation.city}';
var d='${nation.district}';

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