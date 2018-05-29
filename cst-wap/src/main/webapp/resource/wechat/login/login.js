$(function(){
	var openid=""
	openid=window.location.href.split("?&openid=")[1];
	if(openid==null||openid==""||openid==undefined){
		
	}else{
		if(openid.indexOf("#")>0){
			openid=openid.split("#")[0];
	    }else{
	    	openid=openid;
	    }
	}
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
				url:login_url,
				dataType : "json" ,
				data:{
					openId:openid,
					mobile:$(".tel").val(),
					password:$(".password").val()
				} ,
				success:
					function(data){
						if (data == 1) {
							//还未给链接
							error("返回商品详情页");
							return false;
						}else if(data == 2){
							window.location.href=shopping_path;
						}else if(data == 3){
							window.location.href=index_path;
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
		window.location.href=register_path+"?&openid="+openid;
	});
	$(".forget").click(function(){
		window.location.href=forget_path+"?&openid="+openid;
	});
	//返回上一级
	$(".arrow").click(function(){
		window.location.href= mineout_path+"?&openid="+openid;
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