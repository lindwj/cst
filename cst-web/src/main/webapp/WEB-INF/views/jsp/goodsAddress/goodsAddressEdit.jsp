<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>

<style type="text/css">
label.error, label.tip {
	color: red;
}
</style>

<link rel="icon" type="images/png"
	href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
	<link rel="stylesheet" href="<%=path%>/resource/css/addressnew.css" />
<link rel="stylesheet"
	href="<%=path%>/resource/components/header/header.css" />
<link rel="stylesheet"
	href="<%=path%>/resource/components/header/footer.css" />
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
	district:{
	required: true
	},
	province:{
	required: true
	},
	city:{
	required: true
	},
	name:{
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
	province:{
		required: "请选择省份"
		},
	city:{
		required: "请选择城市"
	},
	district:{
		required: "请选择区县"
	},
	name:{
	    required: "请输入收货人"
	 },
	   address: {
	required: "请输入地址",
	}
	}
	});

	});


function sbm(){
	
	$("#shareForm").attr("action", "<%=path%>/goodsAddress/goodsAddressAddEdit.do");
	$("#shareForm").submit();
}

function chk(obj){
	if($(obj).is(':checked')){
		$("#isDefault").val(1);
	}else{
		$("#isDefault").val(0);
	}
	
}	

</script>
</head>
<body>

	<%@include file="../../../../resource/include/header.jsp"%>
<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/goodsAddress/goodsAddressAddEdit.do">

					
					
					<div class="content">
					
					<div id="err" style="color: red">
						<c:if test="${errcode == 101 }">手机，收货人 ,地址  不能为空</c:if>
					</div>

			<div class="content-title">收货地址</div>
			<div class="content-box">
				<div class="item-title">
					<span class="item-title-label">新增收货地址</span> <span
						class="item-title-content">地址、收货人姓名必填,其余均为必填项</span>
				</div>

					
<div class="content-form">

<div class="form-item">
						<span class="item-label">所在地区<i>*</i></span> <select
							id="selprovince" class="inpput-address" name="province"
							onchange="onSelectChange(this,'selcity');"></select> <select
							id="selcity" class="inpput-address" name="city"
							onchange="onSelectChange(this,'seldistrict');">
							<option value="">请选择</option>
						</select> <select name="district" class="inpput-address " id="seldistrict"
							onchange="onSelectChange(this,'selstreet');">
							<option value="">请选择</option>
						</select> <select name="street" class="inpput-address " id="selstreet">
							<option value="">请选择</option>
						</select>


					</div>

					<div class="form-item">
						<span class="item-label">详细地址<i>*</i></span>
						<div class="div-inline">
						<textarea class="address-detail" id="address" name="address"
								placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息">${goodsAddress.address }</textarea>	
						</div>
					</div>
					<div class="form-item">
						<span class="item-label">收货人姓名<i>*</i></span>
						<div class="div-inline">
							<input type="hidden" name="isDefault" id="isDefault" value="${goodsAddress.isDefault }" />
							 <input id="name"
								name="name" class="i-text" type="text" placeholder="长度不超过25个字符"
								value="${goodsAddress.name }" />
								<input type="hidden" name="goodsAddressUuid" value="${goodsAddress.goodsAddressUuid }"/>
											<input type="hidden" name="dowhat" value="edit"/>
						</div>

					</div>
					<div class="form-item">
						<span class="item-label">手机号码 <i>*</i></span>
						<div class="div-inline">
							<input maxlength="11" placeholder="请输入收货人手机号" id="mobile"
								name="mobile" class="i-text" type="text" value="${goodsAddress.mobile }">
						</div>

					</div>
					
					<div class="form-item">
						<span class="item-label">座机号码 <i>&nbsp;</i></span>
						<div class="div-inline">
							<input maxlength="15" placeholder="请输入座机号" id="telephone"
								name="telephone" class="i-text" type="text" value="${goodsAddress.telephone }">
						</div>

					</div>

					<div class="def-address">
						<input type="checkbox" class="checkbox" id="set-default" onclick="chk(this)"
							<c:if test="${goodsAddress.isDefault == 1}">checked="checked"</c:if> /> <label
							for="set-default">设置为默认收货地址</label>
					</div>
					<div class="def-address">
						<button type="button" onclick="sbm()" class="submit-btn"
							id="submit-btn">保存</button>
					</div>
					</div>
			</div>
			
			</div>
					

</form>
		



	<%@include file="../../../../resource/include/footer.jsp"%>
	
<script type="text/javascript">
var p=${goodsAddress.province};
var c=${goodsAddress.city};
var d=${goodsAddress.district};
var s=${goodsAddress.street};

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
					$("#"+toSelId).show();
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
		setSelect(p, 'selcity');
		setSelect(c, 'seldistrict');
		
		if(s!=null && s > 0){
		setSelect(d, 'selstreet');
		}
		
		
	</script>
</body>
</html>
