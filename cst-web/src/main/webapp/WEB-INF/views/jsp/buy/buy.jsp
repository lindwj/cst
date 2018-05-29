<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
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

<link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
    <link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/components/header/footer.css"/>
    <link rel="stylesheet" href="<%=path%>/resource/css/ordersnew.css"/>


	
<script src="<%=path%>/resource/components/jquery/jquery-3.0.0.min.js"></script>
<script src="<%=path%>/resource/scripts/ordersnew.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/buy.js"></script>

<style type="text/css">
label.error, label.tip {
	color: red;
}
</style>

<script type="text/javascript">
function sbm(){
	
	//if(! $('#buy-confirm').is(':checked')) {
	//	return;
	//}
	
	
	var showCapacity=$("#showCapacity").val();
	var capacity =${orders.capacity};
	
	
	
	var shopId = $("#shopId").val();
	if(shopId==-1){
		buy.popModal("请选择收货地址");
		return;
	}
	
	
	if(shopId==0){
			buy.popModal("该区域暂不支持配送");
			return;
	}
	
	if(buy.isInteger(showCapacity) && buy.isInteger(capacity)){
		if(capacity*1>showCapacity*1){
			buy.popModal("商品库存不足");
			return;
		}
	}
	
	
	$("#buyForm").submit();
}

function setObj(data,obg) {
	var arr = jsonParse(data);
	//有匹配的专营店时 才修改值
	if (arr != null &&arr.shopId > 0 ) {
		$("#shopName").val(arr.shopName);
		$("#shopId").val(arr.shopId);
		$("#shopmc").text("由  "+arr.shopName+"  配送");
		$("#showCapacity").val(arr.capacity);
		
		var name =	$("#addrId_"+obg).attr("tname");
		var p = $("#addrId_"+obg).attr("tprovincestr");
		var c =$("#addrId_"+obg).attr("tcitystr");
		var d =$("#addrId_"+obg).attr("tdistrictstr");
		var s =$("#addrId_"+obg).attr("tstreetstr");
		var m = $("#addrId_"+obg).attr("tmobile");
		var a = $("#addrId_"+obg).attr("taddress");
		
		
		$("#receiveName").val(name);
		$("#receiveAddress").val(p+c+d+s+a);
		$("#receiveMobile").val(m);
	}else{
		buy.popModal("该地址不支持配送，请选择其他地址！");
		$("#showCapacity").val(0);
	}
}

function chg(obg){
	
	var goodsAddressUuid = obg;
	
	var productUuid = $("#productUuid").val();
	
	jQuery.ajax({
		  url: "<%=path%>/orders/getShop.do",
						cache : false,
						data : "goodsAddressUuid=" + goodsAddressUuid+"&productUuid=" + productUuid,
						success : function(data) {
							
							setObj(data,obg);
						}
					});
	
}


function setdef(obj,gid) {
	$("li[id^='sel_']").each(function () {
		$(this).attr("class",'clearfix');
	});
	
	$("#sel_"+gid).attr("class",'clearfix selected ');
	
	$("em[id^='addrDef']").each(function () {
		$(this).attr("class",'tip hidden');
	});
	
	$("#addrDef"+gid).attr("class",'tip');
	
	
	$("a[id^='setDef_']").each(function () {
		$(this).attr("class",'set-default');
	});
	
	$("#setDef_"+gid).attr("class",'set-default hidden');
	
}


function def(obj){
	
	var gid= $(obj).attr("gid");
	jQuery.ajax({
		  url: "<%=path%>/goodsAddress/goodsAddressDefault.do",
						cache : false,
						data : "goodsAddressUuid=" + gid,
						success : function(data) {
							if(data==1){
								setdef(obj,gid);
								bingdef(obj);
							}
						}
					});
	
}


function saveAds(){
	
	
    var perName = $("#full-name").val();
    var phone = $("#mobile").val();
    var telephone = $("#telephone").val();
    
    
	
	
	var p= $("#selprovince").val();
	var c= $("#selcity").val();
	var d= $("#seldistrict").val();
	var s= $("#selstreet").val();
	
	var sPostStr='';
	if(s != null && s != ''){
		sPostStr="&street="+s;
	}
	
	
	var address=$("#street").val();
	
	
	//参数验证
	if(perName.length<=0 || phone.length<=0 || p<=0 || c<=0 || d<=0 || address.length<=0 ){
		$("#Error").text("收件人，手机，省市县，地址 必填！");
		return;
	}
	
	var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
	
	if(phone.length != 11 || !myreg.test(phone)){
		$("#Error").text("请输入有效的手机号码！");
		return;
	}
	
	
	
	
	var num=0;
	
	if($('#set-default').is(':checked')) {
		num=1;
	}else{
		num=0;
	}
	
	
	jQuery.ajax({
		  url: "<%=path%>/goodsAddress/goodsAddressAdd.do",
						cache : false,
						 type:"POST",
						data : "province=" + p+"&city="+c+"&district="+d+sPostStr+"&address="+address+"&name="+perName+"&mobile="+phone+"&isDefault="+num+"&telephone="+telephone,
						success : function(data) {
							if(data.length>=10){
								
								var sda=eval(data)
								$("#uuid").val(sda);
								
								add(num);
								
								chg(sda);
								
								$("#addr-bottom").text($("#selprovince").find("option:selected").text() + " " + $("#selcity").find("option:selected").text() + " "  + $("#seldistrict").find("option:selected").text() + " " +address);
						        $("#person-bottom").text(perName + " " + phone);
						        $("#address-barnew").show();
							}
						}
					});
	
}

</script>
</head>
<body>

<div id="frame-mask" style="width: 100%; left: 0px; top: 0px; height: 100%; position: fixed;-webkit-user-select: none; z-index: 20000;" class="dialog-mask display-none"></div>

	<%@include file="../../../../resource/include/header.jsp"%>

<form name="buyForm" id="buyForm" method="post"
		action="<%=path%>/orders/ordersAddEdit.do">
		<div id="err" style="color: red">
				<c:if test="${param.errcode == 103 }">
					<h3>库存不足</h3>
				</c:if>
				<c:if test="${param.errcode == 104 }">
					<h3>此地址不支持配送，请选择其他地址！</h3>
				</c:if>
			</div>
	<input id="productUuid" name="productUuid" type="hidden"
		value="${product.productUuid}" />
	<input id="shopId" name="shopId" type="hidden"
		value="${shopId}" />
		
	<input id="receiveName" name="receiveName" type="hidden"
		value="${goodsAddress.name}" />
		<input id="receiveAddress" name="receiveAddress" type="hidden"
		value="${goodsAddress.provincestr}${goodsAddress.citystr}${goodsAddress.districtstr}${goodsAddress.streetstr}${goodsAddress.address}" />
		<input id="receiveMobile" name="receiveMobile" type="hidden"
		value="${goodsAddress.mobile}" />
		<input id="totalFromBdh" name="totalFromBdh" type="hidden"
		value="${orders.totalFromBdh}" />
		
		<input id="subject" name="subject" type="hidden"
		value="${product.name}" />
		<input id="shopName" name="shopName" type="hidden"
		value="${shopName}" />
		<input id="pic" name="pic" type="hidden"
		value="${product.pic}" />
		<input id="price" name="price" type="hidden"
		value="${product.price}" />
		<input id="capacity" name="capacity" type="hidden"
		value="${orders.capacity}" />
		
	<input id="showCapacity"  type="hidden"
		value="${showCapacity }" />
		
		
		
		<div class="content">
    <div class="address">

            <h3>确认收货地址
                <span class="manage-address">
                <a href="<%=path%>/goodsAddress/goodsAddressListPage.do" target="_blank" class="J_MakePoint" >管理收货地址</a>
                </span>
            </h3>
            
            
            <ul id="address-list" class="address-list">
            
            <c:if test="${goodsAddress != null}">
            <li class="clearfix <c:if test="${1 == goodsAddress.isDefault}">selected</c:if>" id="sel_${goodsAddress.goodsAddressUuid}" data-addressid="${goodsAddress.goodsAddressUuid}">
                    <i class="marker iconfont"></i>
                    <span class="marker-tip">寄送至</span>
                    <div class="address-info">
                        <a href="<%=path%>/goodsAddress/goodsAddressAddEditIni.do?goodsAddressUuid=${goodsAddress.goodsAddressUuid}" class="modify" data-value="${goodsAddress.goodsAddressUuid}">修改本地址</a>
                        <input onclick="chg('${goodsAddress.goodsAddressUuid}')" name="goodsAddressUuid" type="radio" value="${goodsAddress.goodsAddressUuid}" id="addrId_${goodsAddress.goodsAddressUuid}" taddress="${goodsAddress.address}" tname="${goodsAddress.name}" tprovincestr="${goodsAddress.provincestr}" tcitystr="${goodsAddress.citystr}" tdistrictstr="${goodsAddress.districtstr}" tstreetstr="${goodsAddress.streetstr}" tmobile="${goodsAddress.mobile}"  <c:if test="${1 == goodsAddress.isDefault}">checked="checked"</c:if> >
                        <label for="addrId_${goodsAddress.goodsAddressUuid}" class="user-address">
                            <span>${goodsAddress.provincestr}&nbsp;&nbsp;${goodsAddress.citystr}&nbsp;&nbsp;${goodsAddress.districtstr}&nbsp;&nbsp;${goodsAddress.streetstr}&nbsp;&nbsp;${goodsAddress.address}</span>
                            （
                            <span>${goodsAddress.name}</span>
                            &nbsp;收）
                            <span class="addr-person"><em>${goodsAddress.mobile}</em></span>
                        </label>
                        <em class="tip  <c:if test="${1 != goodsAddress.isDefault}">hidden</c:if>" id="addrDef${goodsAddress.goodsAddressUuid}">默认地址</em>
                        <a class="set-default hidden" href="javascript:void(0);" id="setDef_${goodsAddress.goodsAddressUuid}" onclick="def(this)" gid="${goodsAddress.goodsAddressUuid}">设置为默认收货地址</a>
                        <p class="ok naked hidden">设置成功！</p>
                    </div>
                </li>
            </c:if>
            
            <c:forEach var="item" items="${goodsAddressListadd}">
            
            <li class="clearfix <c:if test="${1 == item.isDefault}">selected</c:if>" id="sel_${item.goodsAddressUuid}" data-addressid="${item.goodsAddressUuid}">
                    <i class="marker iconfont"></i>
                    <span class="marker-tip">寄送至</span>
                    <div class="address-info">
                        <a href="<%=path%>/goodsAddress/goodsAddressAddEditIni.do?goodsAddressUuid=${item.goodsAddressUuid}" class="modify" data-value="${item.goodsAddressUuid}">修改本地址</a>
                        <input onclick="chg('${item.goodsAddressUuid}')" name="goodsAddressUuid" type="radio" value="${item.goodsAddressUuid}" id="addrId_${item.goodsAddressUuid}" taddress="${item.address}" tname="${item.name}" tprovincestr="${item.provincestr}" tcitystr="${item.citystr}" tdistrictstr="${item.districtstr}" tstreetstr="${item.streetstr}" tmobile="${item.mobile}"  <c:if test="${1 == item.isDefault}">checked="checked"</c:if> >
                        <label for="addrId_${item.goodsAddressUuid}" class="user-address">
                            <span>${item.provincestr}&nbsp;&nbsp;${item.citystr}&nbsp;&nbsp;${item.districtstr}&nbsp;&nbsp;${item.streetstr}&nbsp;&nbsp;${item.address}</span>
                            （
                            <span>${item.name}</span>
                            &nbsp;收）
                            <span class="addr-person"><em>${item.mobile}</em></span>
                        </label>
                        <em class="tip  <c:if test="${1 != item.isDefault}">hidden</c:if>" id="addrDef${item.goodsAddressUuid}">默认地址</em>
                        <a class="set-default hidden" href="javascript:void(0);" id="setDef_${item.goodsAddressUuid}" onclick="def(this)" gid="${item.goodsAddressUuid}">设置为默认收货地址</a>
                        <p class="ok naked hidden">设置成功！</p>
                    </div>
                </li>
            
            </c:forEach>
            
            </ul>
            
            
            <!--隐藏地址部分-->
            <ul id="more-address" class="address-list hidden"  >
            
            <c:forEach var="item" items="${goodsAddressList}">
                <li class="clearfix" data-addressid="${item.goodsAddressUuid}" id="sel_${item.goodsAddressUuid}">
                    <i class="marker iconfont"></i>
                    <span class="marker-tip">寄送至</span>
                    <div class="address-info">
                        <a href="<%=path%>/goodsAddress/goodsAddressAddEditIni.do?goodsAddressUuid=${item.goodsAddressUuid}" class="modify" data-value="${item.goodsAddressUuid}">修改本地址</a>
                        <input onclick="chg('${item.goodsAddressUuid}')" name="goodsAddressUuid" type="radio" value="${item.goodsAddressUuid}" id="addrId_${item.goodsAddressUuid}" taddress="${item.address}" tname="${item.name}" tprovincestr="${item.provincestr}" tcitystr="${item.citystr}" tdistrictstr="${item.districtstr}" tstreetstr="${item.streetstr}" tmobile="${item.mobile}" >
                        <label for="addrId_${item.goodsAddressUuid}" class="user-address">
                           <span>${item.provincestr}&nbsp;&nbsp;${item.citystr}&nbsp;&nbsp;${item.districtstr}&nbsp;&nbsp;${item.streetstr}&nbsp;&nbsp;${item.address}</span>
                            （<span>${item.name}</span>&nbsp;收）
                            <span class="addr-person">
                                <em>${item.mobile}</em>
                            </span>
                        </label>
                        <em class="tip hidden" id="addrDef${item.goodsAddressUuid}">默认地址</em>
                        <a class="set-default " href="javascript:void(0);" id="setDef_${item.goodsAddressUuid}" onclick="def(this)" gid="${item.goodsAddressUuid}">设置为默认收货地址</a>
                        <p class="ok naked hidden">设置成功！</p>
                    </div>
                </li>
                </c:forEach>
            </ul>

            <div class="address-bar"  id="address-barnew" <c:if test="${goodsAddress == null}"> style="display: none;" </c:if> >
                <a id="otherAddressLink" class="edit J_MakePoint otherlink" href="javascript:void(0);" >使用其它地址</a>
                <a href="javascript:void(0);" class="new J_MakePoint hidden" id="newAddressBtn" >点击增加新地址</a>
            </div>
            
        </div>
        
        <div class="buy-order-field">
        <h3>确认订单信息</h3>
        <div class="buy-th-line clearfix">
            <span class="buy-th-title">商品信息</span>
            <span class="buy-th-type">规格</span>
            <span class="buy-th-price">单价(元)</span>
            <span class="buy-th-quantity">数量</span>
            <span class="buy-th-total">小计(元)</span>
        </div>

        <div class="order clearfix">
            <div class="orderInfo blue-line">
                <div>
                    <span class="orderInfo-shoparea" id="shopmc">店铺：${shopName }</span>
                </div>
            </div>

            <div id="item-list">
                <!--加载商品信息-->
                <div class="item clearfix">
                    <div class="itemInfo item-title">
                        <a href="<%=path%>/resource/electricBusiness/productDetail.html?productUuid=${product.productUuid}" class="itemInfo-link">
                            <span class="item-pic"><span>
                                <img class="itemInfo-pic" src="${product.pic}">
                            </span>
                            </span>
                            <span class="itemInfo-title">${product.name}</span>
                        </a>
                    </div>
                    <div class="item-type">
                        <span class="itemInfo type"><em>&nbsp;&nbsp;</em></span>
                    </div>
                    <div class="item-price">
                        <span class="price"><em id="showprice">${product.price}</em></span>
                    </div>
                    <div class="quantity item-quantity"><p>${orders.capacity}</p></div>
                    <div class="itemPay item-total"><p class="itemPay-price price"><em>${orders.totalFromBdh}</em></p></div>
                </div>
            </div>


            <div class="order-extra">
                <div class="order-user-info">
                    <div class="memo"><label>给卖家留言：</label>
                        <textarea name="note"  class="memo-input c2c-text-default memo-close" autocomplete="off" placeholder="选填：对本次交易的说明,最多50个字。" maxlength="50"></textarea>
                    </div>
                </div>
                
                <!--input type="checkbox" value="" id="buy-confirm">
                        <label style="color: red;font-size: 14px; font-weight:700;">本商品为测试品，暂时无货，请勿购买，如已支付，我们将原价退款。感谢您的支持！</label>
                

            -->
            
                 <div class="order-extra">
                <div class="order-user-info">
                    <div class="memo">
                        
                    </div>
                </div>

            </div>
            
            
            <div class="orderPay blue-line clearfix">
                <p>商品合计(含运费)：
                    <span class="price">
                        <span>¥</span>
                        <em class="style-middle-bold-red">${orders.totalFromBdh}</em>
                    </span>
                </p>
            </div>

            <div></div>
            <div></div>

        </div>


    </div>

    <div class="buy-footer">

        <div class="order-go">
            <div class="address-confirm clearfix">
                <div class="box">
                    <div class="realPay" tabindex="0">
                        <em>实付款：</em>
                        <span class="price g_price "><span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">${orders.totalFromBdh}</em></span>
                    </div>
                    <div class="address">

                        <p class="buy-footer-address">
                            <span class="buy-line-title buy-line-title-type">寄送至：</span>
                            <span id="addr-bottom">${goodsAddress.provincestr}&nbsp;&nbsp;${goodsAddress.citystr}&nbsp;&nbsp;${goodsAddress.districtstr}&nbsp;&nbsp;${goodsAddress.streetstr}&nbsp;&nbsp;${goodsAddress.address}</span>
                        </p>
                        <p class="buy-footer-address">
                            <span class="buy-line-title" id="">收货人：</span>
                            <span id="person-bottom">${goodsAddress.name} ${goodsAddress.mobile}</span>
                        </p>
                    </div>

                </div>

                <div class="submitOrder">
                    <div class="go-btn-wrap">
                        <a id="J_Go" href="javascript:void(0);" class="btn-go  " tabindex="0" onclick="sbm()">提交订单</a>
                    </div>
                </div>
                <!--<a href="" class="return-cart J_MakePoint" ><i class="iconfont icon-fanhui"></i>返回购物车</a>-->
            </div>
            <div class="clearfix"></div>


        </div>
    </div>

</div>
        



</form>
		<br /> <br /> <br /> <br />


<div class="dialog display-none" id="addr-frame">
    <div class="dialog-content">
        <a id="dialog-close" class="dialog-close" role="button">
        <span class="dialog-close-x">close</span>
        </a>
        <div class="dialog-title"><h3>添加收货地址</h3></div>
        <div class="dialog-content">
        <div><p id="Error" style="color: #c81623;padding-left: 105px"></p></div>
            <div class="form-item">
                <span class="item-label">所在地区<i>*</i></span>
                <select
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
                    <textarea class="address-detail" name="addressDetail" id="street" placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息" ></textarea>
                </div>
                <div>
                    <p id="addError" style="color: #c81623;padding-left: 105px"></p>
                </div>
            </div>
            <div class="form-item">
                <span class="item-label">收货人姓名<i>*</i></span>
                <div class="div-inline">
                <input type="hidden" id ="uuid">
                
                <input type="hidden" id ="goodsuuid" value="addrDef${goodsAddress.goodsAddressUuid}">
                    <input name="full-name" class="i-text"  type="text" id="full-name" placeholder="长度不超过25个字符"  value="">
                </div>
                <div>
                    <p class="nameError" style="color: #c81623;padding-left: 105px"></p>
                </div>

            </div>

            <div class="form-item">
                <span class="item-label">手机号码
                <i>*</i></span>
                <div class="div-inline">
                    <input name="fullName" class="i-text"  type="text" id="mobile" placeholder="请输入手机号"  value="">
                </div>
                <div><p id="mobileError" style="color: #c81623;padding-left: 105px"></p></div>
            </div>
            
            <div class="form-item">
                <span class="item-label">座机号码
                <i>&nbsp;</i></span>
                <div class="div-inline">
                    <input name="fullName" class="i-text"  type="text" id="telephone" placeholder="请输入座机号"  value="">
                </div>
                <div><p id="telephoneError" style="color: #c81623;padding-left: 105px"></p></div>
            </div>

            <div class="def-address">
                <input type="checkbox" class="checkbox" id="set-default" checked/>
                <label for="set-default">设置为默认收货地址</label>
            </div>
            <div class="def-address">
                <button type="button" onclick="saveAds()" class="submit-btn" id="submit-btn">保存</button>
            </div>
        </div>
    </div>
</div>


	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>

	
	<script type="text/javascript">
	$(function() {
		buy.init();
	});
	
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
			}else{
				if (toSelId == 'selstreet') {
					var obj = document.getElementById(toSelId);
					$("#"+toSelId).hide();
				}
			}
		}
		setSelect('1', 'selprovince');
		
		function ini(){
			var flag=$("#shopId").val();
			if(-1 == flag){
			$("#addr-frame").removeClass("display-none");
		      $("#frame-mask").removeClass("display-none");
		}
			}
		
		ini();
		
	</script>
</body>
</html>
