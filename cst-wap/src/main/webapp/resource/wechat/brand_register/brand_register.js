$(function(){
	/*var img = "123.png";
	var nickname = "123";
	var openid = "oKdJYs3FwqCEdM081uZoM0nM84IM";*/
/*	var init = window.location.href.split("?")[1];
	var code = init.split("&state=")[0];
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
            },
        error:
            function(data,textStatus){
            }
	})*/
	
	var openid = "";
	var img = "";
	var nickname = "";
	var valueInit = window.location.href.split("?")[1];
	var init = window.location.href.split("?openId=")[1];
	openid = init.split("&nickname=")[0];
	var nickInit = init.split("&nickname=")[1];
	nickname = decodeURI(nickInit.split("&picWx=")[0]);
	img = nickInit.split("&picWx=")[1];
	
	//手机号码正则
	var phonetest=/^1[34578]\d{9}$/;
	var time=60;
	var iCount;
	//倒计时方法
	var count=function (){
		time--;
		var timeval="获取验证码("+time+"s)";
		var tmp = parseInt(time, 10);
		if (tmp <= 0) {
			clearInterval(iCount);
			$(".codebtn").val("获取验证码(60s)");
			$('.codebtn').removeClass('am-disabled');
			time = 60;
		} else {
			$('.codebtn').val(timeval);
		}
	};
	//起始验证手机号非空
	if(!isEmpty($(".tel").val())){
		$('.codebtn').removeClass('am-disabled');
	}
	//获取验证码
	$(".codebtn").click(function(){
		if($(".tel").val()==""||$(".tel").val()==null){
			error("请填写手机号码");
			return false;
		}else if(!(phonetest.test($(".tel").val()))){
			error("请填写正确的手机号码");
			return false;
		}else{
			$('.codebtn').addClass('am-disabled');
			$.ajax({
				type:"post",
				url:getBMobileCaptcha_url,
				dataType : "json" ,
				data:{
					account:$(".tel").val()
				} ,
				success:
					function(data){
						if (data == 0) {
							// 获取短信成功
							$('.codebtn').addClass('am-disabled');
							iCount = setInterval(count, 1000);
							error("验证码已经发送");
							return false;
						}else if(data == -1){
							error("手机已注册");
							return false;
							// 重新开始
							$('.codebtn').removeClass('am-disabled');
							if (iCount) {
								clearInterval(iCount);
								$('.codebtn').val("获取验证码(60s)");
								$('.codebtn').show();
								time = 60;
							}
						}else {
							error("获取手机号验证码失败");
							return false;
							// 获取短信失败
							// 重新开始
							$('.codebtn').removeClass('am-disabled');
							if (iCount) {
								clearInterval(iCount);
								$('.codebtn').val("获取验证码(60s)");
								$('.codebtn').show();
								time = 60;
							}
						}
					},
				error:
					function(data,textStatus){
	        			alert("服务器异常");
	            	}
			})
		}
	});
	
	//密码校验
	$('.password').blur(function() {
		// 密码为七位及以上并且字母、数字两项，强度是中等
		var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
		if (mediumRegex.test($(this).val())) {
		} else {
			error("密码至少8位，必须由字母数字组成");
			return false;
		}
	});
	//验证码校验
	$('.code').blur(function() {
		if ($(this).val().length>4||$(this).val().length<4) {
			error("验证码为4位数字");
			return false;
		}	
	});
	//昵称校验
	$(".nic").blur(function(){
		if($(this).val().length<4||$(this).val().length>10){
			error("昵称长度为4-10");
			return false;
		}
	});
	//所有input不为空使注册能点
	$("input").on('input',function(){
		var length=$("input").length;
		var a=0;
		for(var i=0;i<length;i++){
			if(!isEmpty($("input").eq(i).val())){
				
			}else{
				if(a==0){
					a=1;
				}
			}
		}
		if(a==0){
			$('.loginbtn').removeClass('am-disabled');
		}else{
			$('.loginbtn').addClass('am-disabled');
		}
	});
	//手机号校验
	$(".tel").blur(function(){
		if(phonetest.test($(this).val())){
		}else{
			error("请填写正确的手机号");
			return false;
		}
	});
	// 输入手机号显示验证按钮
	$('.tel').on('input', function() {
		var val = $(this).val();
		if (!isEmpty(val)) {
			$('.codebtn').removeClass('am-disabled');
		} else {
			$('.codebtn').addClass('am-disabled');
		}
	});



	//判断是否为空
	function isEmpty(value){
		if(value==""||value==null||value=="undefined"){
			return true;
		}else{
			return false;
		}
	}
	//提交注册信息
	$(".loginbtn").click(function(){
		var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
		if(!phonetest.test($(".tel").val())){
			error("请填写正确的手机号");
			return false;
		}else if($(".nic").val().length<4||$(".nic").val().length>10){
			error("昵称长度为4-10");
			return false;
		}else if ($(".code").val().length>4||$(".code").val().length<4) {
			error("验证码为4位数字");
			return false;
		}else if (!mediumRegex.test($(".password").val())) {
			error("密码至少8位，必须由字母数字组成");
			return false;
		}else{
			$.ajax({
				type:"post",
				url:bRegister_url,
				dataType : "json" ,
				data: {
					mobile:$(".tel").val(),
					account:$(".tel").val(),
					openId:openid,
					name:$(".nic").val(),
					password:$(".password").val(),
					captcha:$(".code").val(),
					dowhat:"add",
					nickname:nickname,
					picWx:img
				},
				success:
					function(data){
						if(data==1){
							//注册成功
							window.location.href =wdIndex_path+"?openid="+openid;
						}else if(data==-1){
							error("验证码不正确");
							return false;
						}else if(data==-2){
							error("未获取验证码");
							return false;
						}else if(data==-3){
							window.location.href = hint_path;
						}else if(data==-4){
							window.location.href =wdIndex_path+"?openid="+openid;
						}
					},
				error:
					function(data){
	        			alert("服务器异常");
	            	}
			})
		}
	})
	
	$(".arrow").click(function(){
		window.location.href = wdLogin_path+"?openId="+openid+"&nickname="+encodeURI(nickname)+"&picWx="+img;
	})
});