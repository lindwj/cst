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
    <link href="<%=path%>/resource/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resource/css/franchiseDetail.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resource/common/iconfont.css"/>
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
	}else if($("#selstreet").val()==""){
		$("#errorm7").fadeIn(300);
		$("#errorm7").fadeOut(5000);
	}else if($("#name").val()==""){
		$("#errorm8").fadeIn(300);
		$("#errorm8").fadeOut(5000);
	}else if($("#nickName").val()==""){
		$("#errorm9").fadeIn(300);
		$("#errorm9").fadeOut(5000);
	}else if($("#code").val()==""){
		$("#errorm10").fadeIn(300);
		$("#errorm10").fadeOut(5000);
	}else if($("#address").val()==""){
		$("#errorm11").fadeIn(300);
		$("#errorm11").fadeOut(5000);
	}else{
		$("#shareForm").attr("action", "<%=path%>/shop/saveShop.do");
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
					action="<%=path%>/shop/shopListPage.do">
		<div id="errorm4">请选择省</div>
        <div id="errorm5">请选择市</div>
        <div id="errorm6">请选择区</div>
        <div id="errorm7">请选择街道</div>
        <div id="errorm8">请填写名称</div>
        <div id="errorm9">请填写简称</div>
        <div id="errorm10">请填写编号</div>
        <div id="errorm11">请填写地址</div>
					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">专营店已存在 </c:if>
						<c:if test="${errcode == 101 }">省市县街道，名称，编号 ,地址  不能为空</c:if>
					</div>

            <div class="content-title">新增专营店</div>


            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">省份</th>
                            <th class="col2">城市</th>
                            <th class="col3">县区</th>
                            <th class="col4">街道</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                            
                            <input type="hidden" name="dowhat" value="add" />
                            <tr>
                                <td class="col1">
                                    <select  id="selprovince" name="province" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select>
                                </td>
                                <td class="col2">
                                    <select id="selcity" name="city" class="input-default"
											onchange="onSelectChange(this,'seldistrict');">
												<option value="">请选择</option>
										</select>
                                </td>
                                <td class="col3">
                                    <select name="district" id="seldistrict" class="input-default"
										onchange="onSelectChange(this,'selstreet');">
												<option value="">请选择</option>
										</select>
                                </td>
                                <td class="col4">
                                   <select name="street" id="selstreet" class="input-default"
										>
												<option value="">请选择</option>
										</select>
                                </td>

                            </tr>



                        </tbody>


                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col5">名称</th>
                            <th class="col6">简称</th>
                            <th class="col7">编号</th>
                            <th class="col8">地址</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                <td class="col5">
                                    <input type="text" name="name" id="name" />
                                </td>
                                <td class="col6">
                                    <input type="text" name="nickName" id="nickName" />
                                </td>
                                <td class="col7">
                                   <input type="text" name="code" id="code" />
                                </td>
                                <td class="col8">
                                  <input type="text" name="address" id="address" />
                                </td>


                            </tr>



                        </tbody>

<tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button style="float:right;" type="button" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/shop/shopListPage.do'">返回上一级</button>
                                            <input class='samestyle' style="height:32px;margin:0 10px;" type='button' onclick="sbm()" value='保存'>
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