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
    <link href="<%=path%>/resource/css/salesDaily.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resource/common/iconfont.css"/>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
	
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/DatePicker/WdatePicker.js"></script>
	
	<script src="<%=path%>/resource/scripts/salesDaily.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>


<script type="text/javascript">
//搜索专营店
function sbm(){
	
	$("#shareForm").submit();
}

function sbmtfs(){
	var tfs= $("#transferDateStr").val();
	if(tfs.length==0){
		alert("转账时间必填");
		return;
	}
	
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

 <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/shopFinancial/shopFinancialListPage.do">
<div class="black" id="hidebox">

    <div class="fadebox">
        <div class="ordertitle">
            <h1>确认转账？</h1>
        </div>
        <div class="prodetail">
            <p>转账时间:&nbsp;</p>
            <input  style="width:80px;" type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="transferDateStr" id="transferDateStr">
        </div>

        <div class="prodetail1">
            <p>操作人:&nbsp;</p>
            <input type="text" name="transferUser">
        </div>
        <div class="clear"></div>

        <div class="orderbtn">
            <input id="surebtn" class="btn-default" type="button" onclick="sbmtfs()" value="确定">
            <input id="canclebtn" class="btn-default" type="button" value="取消">
        </div>
    </div>
</div>



<div class="container">

<%@ include file="../include/top2.jsp"%>


        <%@ include file="../include/left2.jsp"%>
        
       
					
        <div class="content">
            <div class="content-title">流水汇总表</div>

            <div class="search">

                <label style="margin-left: 10px;" class="search-label">日期：</label>
                <input style="width:80px;height:26px;border:1px solid #cccccc;border-radius:2px;" type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="beginTime" value="${shopFinancial.beginTime}" id="beginTime" />
                <label>至</label>
                <input style="width:80px;height:26px;border:1px solid #cccccc;border-radius:2px;" type="text" class="Wdate" onclick="WdatePicker({readOnly:true})" name="endTime" value="${shopFinancial.endTime}" id="endTime" />
				
                <label style="margin-left: 10px;" class="search-label">门店名称：</label>
                <input name="shopName" type="text" value="${shopFinancial.shopName}"  class="input-default">

                <label class="search-label1">状态：</label>
                <select id="item-status" name="status" class="select-default">
                    <option value="">选择状态</option>
                    <option value="0" <c:if test="${shopFinancial.status == 0}">selected="selected" </c:if>>未转账</option>
                    <option value="1" <c:if test="${shopFinancial.status == 1}">selected="selected" </c:if>>已转账</option>
                </select>



                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>
                <span id="errorm">请输入手机号、订单号或者订单状态,进行搜索!</span>
                <!--<input style="float: right;margin-right: 1%;" class='btn-default' type='button' value='未转账' id="passbtn">-->
                <!--<input onclick="javascript:location='salesDetail.html'" style="float: right;margin-right: 1%;" class='btn-default' type='button' value='转账'>-->

            </div>



            <div class="productorder">


                <table class="table1">
                    <thead>
                        <tr>
                            <th class="col1"><input id="checkall" class="checkbox" type="checkbox"><label>全选</label></th>
                            <th class="col2">店铺编码</th>
                            <th class="col3">店铺名称</th>
                            <th class="col4">日期</th>
                            <th class="col5">实付款</th>
                            <th class="col6">服务费</th>
                            <th class="col7">转账金额</th>
                            <th class="col8">状态</th>
                            <th class="col8">支付方式</th>
                        </tr>
                    </thead>


                    <tbody class='table2'>
                    <c:forEach var="item" items="${shopFinancialList}">
                    
                        <tr>
                            <td class='col1'><input name="shopFinancialIdList" value="${item.shopFinancialId}" class="checkbox" type="checkbox"></td>
                            <td class='col2'>${item.code}</td>
                            <td class='col3' >${item.shopName}</td>
                            <td class='col4' >${item.dayStr}</td>
                            <td class='col5'>${(item.amount != null && item.amount !=0)?item.amount:0 }</td>
                            <td class='col6'>${(item.serviceFee != null && item.serviceFee !=0)?item.serviceFee:0 }</td>
                            <td class='col7'>${(item.amount != null && item.amount !=0)?(item.transferFee):0 }</td>
                            <td class='col8'>
                            <c:if test="${item.status == 0}">未转账</c:if>
							<c:if test="${item.status == 1}">已转账</c:if>
                            <br>${item.transferDateStr}</td>
                            <td class='col8'>
                            <c:if test="${item.payType == 2}">微信</c:if><c:if test="${item.payType == 1}">支付宝</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

							
                </table>

<button id="transfer" style="margin-top:20px;background-color:#ececec;" type="button" class="btn-default">转账</button>
                            <button style="margin:20px 0 0 10px;background-color:#ececec;" id="nottransfer" type="button" onclick="sbm()" class="btn-default" style="margin-left: 10px;">未转账</button>
					<%@ include file="../include/innerNavigation.jsp"%>
            </div>


        </div>


</div>

</form>
<script type="text/javascript">

var p='${shopFinancial.province}';
var c='${shopFinancial.city}';
var d='${shopFinancial.district}';

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