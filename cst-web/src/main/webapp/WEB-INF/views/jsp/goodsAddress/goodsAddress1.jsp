<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html class="no-js">
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${webTitle }</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="<%=path%>/resource/images/favicon.png">
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">

<link rel="stylesheet" href="<%=path%>/resource/css/cart.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<style type="text/css">
label.error, label.tip {
	color: red;
}
</style>
<script type="text/javascript">
$().ready(function() {
//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

$("#shareForm").validate({
    rules: {
    	mobile: {
required: true,
isMobile : true
},
name:{
required: true
},
district:{
required: true
},
province:{
required: true
},
city:{
required: true
},
address: {
required: true,
}
},
    messages: {
    	mobile: {
required: "请输入手机"
},
name:{
    required: "请输入收货人"
 },
province:{
	required: "请选择省份"
	},
city:{
	required: "请选择城市"
},
district:{
	required: "请选择区县"
},
   address: {
required: "请输入地址"
}
}
});

});

function sbm(){
	
	$("#shareForm").attr("action", "<%=path%>/goodsAddress/goodsAddressAddEdit.do");
	$("#shareForm").submit();
}


function del(obj){
	
	$("#goodsAddressUuid").attr("value",$(obj).attr("id"));
	
	
	$("#editForm").attr("action", "<%=path%>/goodsAddress/goodsAddressDelete.do");
	$("#editForm").submit();
}


function edit(obj){
	
	$("#goodsAddressUuid").attr("value",$(obj).attr("id"));
	
	
	$("#editForm").attr("action", "<%=path%>/goodsAddress/goodsAddressAddEditIni.do");
	$("#editForm").submit();
}



function setObj(obj,gid) {
	$("li[id$='gid']").each(function () {
		$(this).attr("class",'');
	});
	
	$("#"+gid+"gid").attr("class",'on');
}

function def(obj){
	
	var gid= $(obj).attr("gid");
	jQuery.ajax({
		  url: "<%=path%>/goodsAddress/goodsAddressDefault.do",
						cache : false,
						data : "goodsAddressUuid=" + gid,
						success : function(data) {
							if(data==1){
								setObj(obj,gid);
							}
						}
					});
	
}

</script>
</head>
<body>

	<%@include file="../../../../resource/include/header.jsp"%>

	<form name="editForm" id="editForm" method="post"
		action="<%=path%>/goodsAddress/goodsAddressListPage.do">
		<input type="hidden" name="goodsAddressUuid" id="goodsAddressUuid"
			value="${goodsAddress.goodsAddressUuid }" />
	</form>

	<form name="shareForm" id="shareForm" method="post"
		action="<%=path%>/goodsAddress/goodsAddressListPage.do">

		<div id="err" style="color: red">
			<c:if test="${errcode == 101 }">省市县街道，手机，收货人,地址  不能为空</c:if>
		</div>

		<div class="" id="address-dialog">
			<div class="">
				<div class="w960">新增收货地址</div>
				<div class="">
					<div class="">
						<div>
							<table class="dialog_tab">
								<tbody>
									<tr>
										<th>收货人：</th>
										<td style="float: left;"><input type="text"
											placeholder="请输入收货人" id="name" name="name"> <input
											type="hidden" name="isDefault" value="${isDefault }" /> <input
											type="hidden" name="dowhat" value="add" /></td>
									</tr>
									<tr>
										<th>选择地址：
										</th>
										<td style="float: left;"><span id="provinceDiv"><select
												id="selprovince" name="province"
												onchange="onSelectChange(this,'selcity');"></select></span> <span
											id="cityDiv" style=""> <select id="selcity"
												name="city" onchange="onSelectChange(this,'seldistrict');">
													<option value="">请选择</option>
											</select></span> <span id="districtDiv" style=""><select
												name="district" id="seldistrict"
												onchange="onSelectChange(this,'selstreet');">
													<option value="">请选择</option>
											</select> </span> <span id="streetDiv" style=""><select name="street"
												id="selstreet">
													<option value="">请选择</option>
											</select></span></td>
									</tr>
									<tr>
										<th valign="top">收货地址：</th>
										<td style="float: left;"><input type="text"
											placeholder="请输入详细收货地址" id="address" name="address">
										</td>
									</tr>
									<tr>
										<th>手机号：</th>
										<td style="float: left;"><input type="text"
											maxlength="11" placeholder="请输入收货人手机号" id="mobile"
											name="mobile"></td>
									</tr>
									<tr>
										<th>
										</th>
										<td><p class="">
												<input type="button" onclick="sbm()" id="addaddress"
													value="保存" />
											</p></td>
									</tr>
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>
		</div>




		<div class="">
			<p class="w960">设置默认收货地址</p>
		</div>

		<div class="w960 check_address" id="has-adderss" style="">
			<ul id="addressListUl">
				<c:forEach var="item" items="${goodsAddressList}">
					<li id="${item.goodsAddressUuid}gid" 
						<c:if test="${item.isDefault == 1}">is_default="1" class="on"</c:if>
						<c:if test="${item.isDefault == 0}">is_default="0" class=""</c:if>><i gid="${item.goodsAddressUuid}" onclick="def(this)"></i><span>${item.name}&nbsp;&nbsp;&nbsp;&nbsp;${item.provincestr}&nbsp;&nbsp;${item.citystr}&nbsp;&nbsp;${item.districtstr}&nbsp;&nbsp;${item.streetstr}&nbsp;&nbsp;${item.mobile}</span>
						<input class="orange_color right" style="" type="button"
						id="${item.goodsAddressUuid}" onclick="edit(this)" value="编辑">
						<input class="orange_color right" type="button"
						id="${item.goodsAddressUuid}" onclick="del(this)" value="删除"></li>
				</c:forEach>
			</ul>
			<%@include file="../../../../resource/include/innerNavigation.jsp"%>
		</div>

	</form>




	<%@include file="../../../../resource/include/footer.jsp"%>

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
					} else if (toSelId == 'selstreet') {
						op.appendChild(document.createTextNode(arr[o].street));
					}
					obj.appendChild(op);
				}
			}
		}
		setSelect('1', 'selprovince');
	</script>
</body>
</html>
