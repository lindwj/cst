$(function () {
	var initvalue=""
	var openCode="";
	var openid="";
	if(window.location.href.indexOf("?code=")>0){
		initvalue=window.location.href.split("?")[1];
		openCode=initvalue.split("&state=")[0];
		$.ajax({
			type: 'POST',
	        url: getOpenid_url,
	        dataType : "json" ,
	        data: openCode,
	        async:false,
	        success:
	            function(data){
	        		if(data==null||data==""||data==undefined){
	        			error("没有获取openid");
	        		}else{
		        		openid=data;
		        	}
	            },
	        error:
	            function(data,textStatus){

	            }
		})
	}else{
		openid=window.location.href.split("?&openid=")[1];
		if(openid.indexOf("#")>0){
			openid=openid.split("#")[0];
	    }else{
	    	openid=openid;
	    }
	}
	$(".header").find("h1").html(openid.substring(openid.length-14,openid.length));
	//跳转地址列表
	$(".address").click(function(){
		window.location.href =  addressWx_path + "?&openid=" + openid;
	});
	
	//待付款
	$(".order_box").find("a").eq(0).click(function(){
		window.location.href = payWait_path + "?&openid=" + openid;
	});
	
	//待发货
	$(".order_box").find("a").eq(1).click(function(){
		window.location.href = receiptWait_path + "?&openid=" + openid;
	});
	
	//全部订单列表
	$(".order_box").find("a").eq(2).click(function(){
		window.location.href = order_path + "?&openid=" + openid;
	})
	
	//跳转登录页面
	$(".log_in").click(function(){
		window.location.href = login_path + "?&openid=" + openid;
	})
});