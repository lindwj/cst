$(function(){
	if(window.location.href.indexOf("?openId=")>0){
		var openid = "";
		var img = "";
		var nickname = "";
		var valueInit = window.location.href.split("?")[1];
		var init = window.location.href.split("?openId=")[1];
		openid = init.split("&nickname=")[0];
		var nickInit = init.split("&nickname=")[1];
		nickname = decodeURI(nickInit.split("&picWx=")[0]);
		img = nickInit.split("&picWx=")[1];
	}else{
		var init = window.location.href.split("?")[1];
		var code = init.split("&state=")[0];
		var eventid = init.split("&state=")[1];
		var img = "";
		var nickname = "";
		var openid = "";
		$.ajax({
			type: 'POST',
	        url: userInfo_url,
	        dataType : "json" ,
	        data:code,
	        async: false,
	        success:
	            function(data){
	        		img=data.pic;
	        		openid=data.openid;
	        		nickname=data.subject;
	        		
	        		if(eventid.indexOf("user") >= 0 ) {
	        			window.location.href=star_path+eventid.replace('user','');
	        		}else{
	        		window.location.href=index_path+eventid;
	        		}
	            },
	        error:
	            function(data,textStatus){
	            }
		})
	}
	
});