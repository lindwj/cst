$(function(){
	var orders="";
	var order="";
	var shop="";
	var openid="";
	order = window.location.href.split("?")[1];
	openid = window.location.href.split("&openid=")[1];
	var goodsAddress = window.location.href.split("?addressUuid=")[1];
	shop = window.location.href.split("?").pop();
	if(goodsAddress==null||goodsAddress==undefined||goodsAddress==""){
		$(".header").find("p").eq(1).text("添加新地址");
	}else{
		$(".header").find("p").eq(1).text("编辑地址");
		if(goodsAddress.indexOf("#")>0){
			goodsAddress=goodsAddress.split("#")[0];
			if(goodsAddress.indexOf("?")){
				goodsAddress = goodsAddress.split("?")[0];
			}else{
				goodsAddress=goodsAddress;
			}
	    }else{
	    	goodsAddress=goodsAddress;
	    	if(goodsAddress.indexOf("?")){
				goodsAddress = goodsAddress.split("?")[0];
			}else{
				goodsAddress=goodsAddress;
			}
	    }
		
	}
	if(order==undefined||order==null||order==""||order==1||order.indexOf("openid=")>0){
		order="";
		orders="";
	}else{
		if(order.indexOf("#")>0){
			if(goodsAddress==orders.split("addressUuid=")[1]&&goodsAddress!=null&&goodsAddress!=""&&goodsAddress!=undefined){
				orders="";
			}else{
				orders=order.split("#")[0];
			}
	    }else{
	    	if(goodsAddress==orders.split("addressUuid=")[1]&&goodsAddress!=null&&goodsAddress!=""&&goodsAddress!=undefined){
				orders="";
			}else{
				orders=order;
			}
	    }
	}
	if(openid.indexOf("#")>0){
		openid=openid.split("#")[0];
		if(openid.indexOf("?")>0){
			openid=openid.split("?")[0];
		}else{
			openid=openid;
		}
    }else{
    	openid=openid;
    	if(openid.indexOf("?")>0){
			openid=openid.split("?")[0];
		}else{
			openid=openid;
		}
    }
	$(".add_street").hide();
	//防止body被手机软键盘顶起来样式改变
    $('body').height($('body')[0].clientHeight);
    var defaultId=1;
    //初始化地址插件
    var area1 = new LArea();
    area1.init({
    	'locate': '.location',
    	'trigger': '.add_city', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
    	'valueTo': '.value', //选择完毕后id属性输出到该位置
    	'street': '.add_street',
    	'keys': {
    		id: 'value',
    		name: 'text'
    	}, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
    	'type': 2, //数据源类型
    	'data': [provs_data, citys_data, dists_data] //数据源
    });
    if(!isEmpty(goodsAddress)){
    	$.ajax({
            type: 'POST',
            url: getAddress_url + goodsAddress,
            dataType : "json" ,
            data: {},
            async: false,
            success:
                function(data){

            		$(".add_name").val(data.name);
            		$(".add_tel").val(data.mobile);
            		$(".add_");
            		$(".value").val(data.provinceCode+" "+data.cityCode+" "+data.districtCode);
            		$(".add_city").val(data.provincestr+" "+data.citystr+" "+data.districtstr);
            		$(".location").val(data.provinceVal+" "+data.cityVal+" "+data.districtVal);
            		$(".add_address").val(data.address);
            		if(data.isDefault==1){
            			defaultId=1;
            		}else{
            			defaultId=0;
            			$(".defaulticon").addClass("checked");
                        $(".defaulticon").children("i").css("display","none")
            		}
                },
            error:
                function(data,textStatus){
            		alert("服务器异常");
                }
        });
    }
    //初始化位置
    
    if($(".location").val()!=null&&$(".location").val()!=""&&$(".location").val()!=undefined){
    	area1.value=[$(".location").val().split(" ")[0],$(".location").val().split(" ")[1],$(".location").val().split(" ")[2]];
    }
    $(".defaulticon").click(function(){
        if($(this).children("i").css("display")=="block"){
        	defaultId=0;
            $(this).addClass("checked");
            $(this).children("i").css("display","none")
        }else{
        	defaultId=1;
            $(this).removeClass("checked");
            $(this).children("i").css("display","block")
        }
    });
    
    $(".save_address").click(function(){
    	var streetVal=-1;
    	if($(".add_street").val()==null||$(".add_street").val()==""||$(".add_street").val()==undefined){
    		streetVal=-1;
    	}else if($(".add_street").val().match(/^[\u4e00-\u9fa5]+$/)){
    		streetVal=-1;
    	}else{
    		streetVal=$(".add_street").val();
    	}
    	var phonetest=/^1[34578]\d{9}$/;
    	if(isEmpty($(".add_name").val())){
			error("请添加姓名");
			return false;
    	}else if(isEmpty($(".add_tel").val())){
			error("请添加手机号码");
			return false;
    	}else if(!phonetest.test($(".add_tel").val())){
			error("请填写正确的手机号码");
			return false;
    	}else if(isEmpty($(".add_city").val())){
			error("请选择省市区");
			return false;
    	}
/*    	else if(isEmpty($(".add_street").val())){
    		alert("请选择街道");
    	}*/
    	else if(isEmpty($(".add_address").val())){
			error("请添加详细地址");
			return false;
    	}else{
    		$.ajax({
                type: 'POST',
                url: addAddressOpen_url,
                dataType : "json" ,
                async: false,
                data: {
                	openid:openid,
                	name:$(".add_name").val(),
                	mobile:$(".add_tel").val(),
                	province:$(".value").val().split(" ")[0],
                	city:$(".value").val().split(" ")[1],
                	district:$(".value").val().split(" ")[2],
                	street:streetVal,
                	address:$(".add_address").val(),
                	isDefault:defaultId,
                	goodsAddressUuid:goodsAddress,
                	provinceVal:$(".location").val().split(" ")[0],
                	cityVal:$(".location").val().split(" ")[1],
                	districtVal:$(".location").val().split(" ")[2]
                },
                success:
                    function(data){
                		if(orders!=null&&orders!=""&&orders!=undefined&&shop!=1){
                			window.location.href = addressWx_path+"?"+orders+"?&openid="+openid;
                		}else if(shop==1){
                			window.location.href = addressWx_path+"?"+orders+"?&openid="+openid+"?1";
                		}else{
                			window.location.href = addressWx_path+"?&openid="+openid;
                		}
                    },
                error:
                    function(data,textStatus){
                		alert("服务器异常");
                    }
            });
    	}
    });
	$(".arrow").click(function(){
		if(orders!=null&&orders!=""&&orders!=undefined&&shop!=1){
			window.location.href = addressWx_path +"?"+orders + "?&openid=" +openid;
		}else if(shop==1){
			window.location.href = addressWx_path +"?"+orders + "?&openid=" +openid + "?1";
		}else{
			window.location.href = addressWx_path + "?&openid=" +openid;
		}
	});
    //判断是否为空
	function isEmpty(value){
		if(value==""||value==null||value==undefined||value=="undefined"){
			return true;
		}else{
			return false;
		}
	}
});