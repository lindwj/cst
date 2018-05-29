$(function(){
	var initvalue="";
	var openCode="";
	var manager="";
	var openid="";
	if(window.location.href.indexOf("?code=")>0){
		initvalue=window.location.href.split("?")[1];
		openCode=initvalue.split("&state=")[0];
		manager=initvalue.split("&state=")[1];
	}
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
	
	$.ajax({
		type: 'POST',
		url: getUserCtrl_url,
		dataType : "json" ,
		data: {
			agentId:manager,
			openId:openid
		},
		success:
			function(data){
				if(data==0){
					window.location.href = wdRegisterS_path+manager+"openid="+openid;
				}else{
					window.location.href = index_path;
				}
			},
		error:
			function(data,textStatus){
			
		}
	})
	
});