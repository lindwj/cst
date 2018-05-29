$(function(){
	var init= window.location.href.split("?")[1];
	var code=init.split("&state=")[0];
	var openid="";
	$.ajax({
		type: 'POST',
        url: getOpenid_url,
        dataType : "json" ,
        data:code,
        async: false,
        success:
            function(data){
        		openid=data;
            },
        error:
            function(data,textStatus){
            }
	})
	
	if(openid!=null&&openid!=""){
		$.ajax({
			type: 'POST',
	        url: isManager_url,
	        dataType : "json" ,
	        data:{
	        	openId:openid
	        },
	        async: false,
	        success:
	            function(data){
	        		if(data==0){
	        			//授权链接
	        			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/loginWd.html&response_type=code&scope=snsapi_userinfo&state=State&connect_redirect=1#wechat_redirect";
	        		}else{
	        			window.location.href=wdIndex_path+"?openid="+openid;
	        		}
	            },
	        error:
	            function(data,textStatus){
	            }
		})
	}
});