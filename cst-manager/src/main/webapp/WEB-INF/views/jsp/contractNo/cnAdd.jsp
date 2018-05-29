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
<link href="<%=path%>/resource/css/contractDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript">
function sbm(){
	if($("#selprovince").val()==""){	
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else if($("#selcity").val()==""){
		$("#errorm5").fadeIn(300);
		$("#errorm5").fadeOut(5000);
	}else if($("#seldistrict").val()==""){
		$("#errorm6").fadeIn(300);
		$("#errorm6").fadeOut(5000);
	}else if($("#selshopId").val()==""){
		$("#errorm7").fadeIn(300);
		$("#errorm7").fadeOut(5000);
	}else if($("#contractNoAmt").val()==""){
		$("#errorm8").fadeIn(300);
		$("#errorm8").fadeOut(5000);
	}else{
		$("#shareForm").attr("action", "<%=path%>/contractNo/contractNoAddEdit.do");
		$("#shareForm").submit();
	}
}



</script>	
	
</head>
<body>



<div class="container">

 <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>
        <div class="content">

<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/contractNo/contractNoListPage.do">
					<div id="errorm4">请选择省</div>
        			<div id="errorm5">请选择市</div>
        			<div id="errorm6">请选择区</div>
        			<div id="errorm7">请选择专营店</div>
        			<div id="errorm8">请填写合同编号生成数量</div>
					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">专营店合同编号已存在 </c:if>
						<c:if test="${errcode == 101 }">省市县，专营店，合同编号数量（最大6位数）  不能为空</c:if>
					</div>
					
            <div class="content-title">新增合同编号</div>


            <div class="content-main">
                <form>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">省份</th>
                            <th class="col2">城市</th>
                            <th class="col3">县区</th>
                            <th class="col4">专营店</th>
                            <th class="col5">合同编号生成数量</th>
                            <th class="col6">操作</th>
                        </tr>

                        </thead>
                        <tbody>
<input type="hidden" name="dowhat" value="add" />
                            <tr>
                                <td class="col1">
                                    <select  id="selprovince" name="province" class="input-default"
											onchange="onSelectChange(0,this,'selcity');"></select>
                                </td>
                                <td class="col2">
                                    <select id="selcity" name="city" class="input-default"
											onchange="onSelectChange(0,this,'seldistrict');">
												<option value="">请选择</option>
										</select>
                                </td>
                                <td class="col3">
                                    <select name="district" id="seldistrict" class="input-default"
										onchange="onSelectChange(2,this,'selshopId');">
												<option value="">请选择</option>
										</select>
                                </td>
                                <td class="col4">
                                    <select name="shopId" id="selshopId" class="input-default"
										>
												<option value="">请选择</option>
										</select>
                                </td>
                                <td class="col5">
                                <input type="text" name="contractNoAmt"  id="contractNoAmt" />个
                                </td>
                                <td class="col6">
                                    <input class='modify' onclick="sbm()" type='button' value='保存'>
                                </td>

                            </tr>



                        </tbody>
                        
                        
                        <tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button type="button" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/contractNo/contractNoListPage.do'">返回上一级</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </tfoot>


                    </table>

                


            </div>

</form>

        </div>


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