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
		$("#shareForm").attr("action", "<%=path%>/shuidian/saveShuidian.do");
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
						<c:if test="${errcode == 100 }">已存在 </c:if>
						<c:if test="${errcode == 101 }">分公司，项目，水电信息  不能为空</c:if>
					</div>

            <div class="content-title">新增水电信息</div>


            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">分公司</th>
                            <th class="col2">项目</th>
                            <th class="col3">年</th>
                            <th class="col4">月</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                            
                            <input type="hidden" name="dowhat" value="add" />
                            <tr>
                                <td class="col1">
                                    <select  id="selprovince" name="fengsid" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select>
                                </td>
                                <td class="col2">
                                    <select id="selcity" name="xmid" class="input-default"
											onchange="onSelectChange(this,'seldistrict');">
												<option value="">请选择</option>
										</select>
                                </td>
                                <td class="col3">
                                    <select name="nian" id="" class="input-default"
										>
												<option value="2010">2010</option>
												<option value="2011">2011</option>
												<option value="2012">2012</option>
												<option value="2013">2013</option>
												<option value="2014">2014</option>
												<option value="2015">2015</option>
												<option value="2016">2016</option>
												<option value="2017">2017</option>
												<option value="2018">2018</option>
												<option value="2019">2019</option>
												<option value="2020">2020</option>
												<option value="2021">2021</option>
												<option value="2022">2022</option>
												<option value="2023">2023</option>
												<option value="2024">2024</option>
												<option value="2025">2025</option>
										</select>
                                </td>
                                <td class="col4">
                                   <select name="yue" id="" class="input-default"
										>
												<option value="01">01</option>
												<option value="02">02</option>
												<option value="03">03</option>
												<option value="04">04</option>
												<option value="05">05</option>
												<option value="06">06</option>
												<option value="07">07</option>
												<option value="08">08</option>
												<option value="09">09</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
										</select>
                                </td>

                            </tr>



                        </tbody>


                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col5">水费</th>
                            <th class="col6">水量</th>
                            <th class="col7">电费</th>
                            <th class="col8">电量</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                <td class="col5">
                                    <input type="text" name="shuifei" id="shuifei" />
                                </td>
                                <td class="col6">
                                    <input type="text" name="shuiliang" id="shuiliang" />
                                </td>
                                <td class="col7">
                                   <input type="text" name="dianfei" id="dianfei" />
                                </td>
                                <td class="col8">
                                  <input type="text" name="dianliang" id="dianliang" />
                                </td>


                            </tr>



                        </tbody>




                    </table>
                    
                                        <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">备注</th>
                            <th class="col1"></th>
                            <th class="col1"></th>
                            <th class="col1"></th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                            
                            <tr>
                                <td class="col9">
                                  <input type="text" name="note" id="note" />
                                </td>
                                <td class="col9">
                                </td>
                                <td class="col9">
                                </td>
                                <td class="col9">
                                </td>

                            </tr>



                        </tbody>

<tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button style="float:right;" type="button" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/shuidian/shuidianListPage.do'">返回上一级</button>
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