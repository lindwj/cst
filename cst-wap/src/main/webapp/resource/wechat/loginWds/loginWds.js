$(function(){
	var openid="";
	var manager="";
	var init="";
	if(window.location.href.indexOf("?manager=")>0){
		init = window.location.href.split("?manager=")[1];
	}else{
		alert("没有manager");
	}
	manager=init.split("openid=")[0];
	openid=init.split("openid=")[1];
	//手机号码正则
	var phonetest=/^1[34578]\d{9}$/;
	//手机号校验
	$(".tel").blur(function(){
		if(phonetest.test($(this).val())){
		}else{
			error("请填写正确的手机号");
			return false;
		}
	});
	//登录
	$(".loginbtn").click(function(){
		if(isEmpty($(".tel").val())){
			error("请填写手机号");
			return false;
		}else if(isEmpty($(".password").val())){
			error("请填写密码");
			return false;
		}else{
			$.ajax({
				type:"post",
				url:loginWds_url,
				dataType : "json" ,
				data:{
					bindState:0,
					agentId:manager,
					openId:openid,
					mobile:$(".tel").val(),
					password:$(".password").val()
				} ,
				success:
					function(data){
						if (data == 1) {
							window.location.href=index_path;
						}else if(data == -3){
							error("请先去注册");
							return false;
						}else if(data == -1){
							error("账号密码不匹配");
							return false;
						}
					},
				error:
					function(data,textStatus){
	        			alert("服务器异常");
	            	}
			})
		}
	});
	$(".registerbtn").click(function(){
		window.location.href=wdRegisterS_path + manager + "openid=" +openid;
	});
	//返回上一级
	$(".arrow").click(function(){
		window.location.href= wdRegisterS_path + manager + "openid=" +openid;
		//window.location.href= index_path;
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