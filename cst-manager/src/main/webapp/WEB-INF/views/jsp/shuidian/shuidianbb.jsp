<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	
	if($("#selprovince").val()==null || $("#selprovince").val()<=0 ){
		alert("分公司必选");
		return false;
	}
	
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
					action="<%=path%>/shuidian/shuidianbbList.do">


        <div class="content">

            <div class="content-title">水电同期对比</div>

            <div class="search">

                <label class="search-label">分公司：</label>
                <select  id="selprovince"  name="fengsid" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select>

                <label class="search-label1">年：</label>
               <select name="nian" id="" class="input-default"
										>
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
										
										<label class="search-label1">起始月：</label>
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
										
										<label class="search-label1">截止月：</label>
               <select name="shuidianId" id="" class="input-default"
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


                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>

                <button type="button" class="btn-default" id="addbtn" value="导出" onclick="tableToExcel('teamtable','data')">导出报表</button>




            </div>

            <div class="content-main">
                    <table class="table" id="teamtable">
                        <thead>
                        <tr>
                            <th class="col17">项目</th>
                            <th class="col27">上年</th>
                            <th class="col37">水费</th>
                            <th class="col47">水量</th>
                            <th class="col57">电费</th>
                            <th class="col67">电量</th>
                            <th class="col77">水电费</th>
                            <th class="col87">年</th>
                            <th class="col97">水费</th>
                            <th class="col10">水量</th>
                            <th class="col11">电费</th>
                            <th class="col12">电量</th>
                            <th class="col13">水电费</th>
                            <th class="col14">水费增量</th>
                            <th class="col15">水量增量</th>
                            <th class="col16">电费增量</th>
                            <th class="col17">电量增量</th>
                            <th class="col18">水电费增比</th>
                            <th class="col19">水费增比</th>
                            <th class="col20">水量增比</th>
                            <th class="col21">电费增比</th>
                            <th class="col22">电量增比</th>
                            <th class="col23">水电费增比</th>
                        </tr>

                        </thead>
                        <tbody>

                            
									<c:forEach var="item" items="${shuidianList}">
										<tr>
											<td id="y2" class="col17">${item.xmname}</td>
											<td id="y1" class="col27">${item.shuidian.nian}</td>
											<td id="y3" class="col37">${item.shuidian.shuifei}</td>
											<td id="y4" class="col47">${item.shuidian.shuiliang}</td>
											<td id="y5" class="col57">${item.shuidian.dianfei}</td>
											<td id="y7" class="col67">${item.shuidian.dianliang}</td>
											<td id="y9" class="col77">${item.shuidian.shuidianfei}</td>
											<td id="y1" class="col87">${item.nian}</td>
											<td id="y3" class="col97">${item.shuifei}</td>
											<td id="y4" class="col10">${item.shuiliang}</td>
											<td id="y5" class="col11">${item.dianfei}</td>
											<td id="y7" class="col12">${item.dianliang}</td>
											<td id="y9" class="col13">${item.shuidianfei}</td>
											<td id="y3" class="col14">${item.shuifei-item.shuidian.shuifei}</td>
											<td id="y4" class="col15">${item.shuiliang-item.shuidian.shuiliang}</td>
											<td id="y5" class="col16">${item.dianfei-item.shuidian.dianfei}</td>
											<td id="y7" class="col17">${item.dianliang-item.shuidian.dianliang}</td>
											<td id="y9" class="col18">${item.shuidianfei-item.shuidian.shuidianfei}</td>
											
											
											
											
											
											
											<td id="y3" class="col19"><fmt:formatNumber value="${(item.shuifei-item.shuidian.shuifei)*100/item.shuidian.shuifei}" pattern="#,#00.0#"/>%</td>
											<td id="y4" class="col20"><fmt:formatNumber value="${(item.shuiliang-item.shuidian.shuiliang)*100/item.shuidian.shuiliang}" pattern="#,#00.0#"/>%</td>
											<td id="y5" class="col21"><fmt:formatNumber value="${(item.dianfei-item.shuidian.dianfei)*100/item.shuidian.dianfei}" pattern="#,#00.0#"/>%</td>
											<td id="y7" class="col22"><fmt:formatNumber value="${(item.dianliang-item.shuidian.dianliang)*100/item.shuidian.dianliang}" pattern="#,#00.0#"/>%</td>
											<td id="y9" class="col23"><fmt:formatNumber value="${(item.shuidianfei-item.shuidian.shuidianfei)*100/item.shuidian.shuidianfei}" pattern="#,#00.0#"/>%</td>
										</tr>
									</c:forEach>



                        </tbody>

                    </table>
                

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
	
	<script type="text/javascript">
    function base64 (content) {
       return window.btoa(unescape(encodeURIComponent(content)));         
    }
    /*
    *@tableId: table的Id
    *@fileName: 要生成excel文件的名字（不包括后缀，可随意填写）
    */
    function tableToExcel(tableID,fileName){
        var table = document.getElementById(tableID);
      var excelContent = table.innerHTML;
      var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
      excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
      excelFile += "<body><table>";
      excelFile += excelContent;
      excelFile += "</table></body>";
      excelFile += "</html>";
      var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
      var a = document.createElement("a");
      a.download = fileName+".xls";
      a.href = link;
      a.click();
    }
</script>

</body>
</html>