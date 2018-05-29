$(function(){
	//var openid="12345";
	var openid="";
	var manager="";
	var init = window.location.href.split("?openid=")[1];
	openid = init.split("manager=")[0];
	manager = init.split("manager=")[1];
	
	$(".arrow").click(function(){
		window.location.href = wdIndex_path + "?openid=" + openid;
	})
	$.ajax({
		type:"post",
		url:getQRCode_url,
		dataType : "json" ,
		data:{
			managerId:manager,
			url:"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/qrcode.html&response_type=code&scope=snsapi_base&state="+manager+"#wechat_redirect",//需要改成授权链接
			openid:openid
			} ,
		success:
			function(data){
				$("#code").attr("src",base_url+data);
			},
		error:
			function(data,textStatus){
    			alert("服务器异常");
        	}
	})
});