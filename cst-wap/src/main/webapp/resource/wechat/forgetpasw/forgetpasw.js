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
				url:validateCode_url,
				dataType : "json" ,
				data:{
					mobile:$(".tel").val()
				} ,
				success:
					function(data){
						if (data == 0) {
							// 获取短信成功
							$('.codebtn').addClass('am-disabled');
							iCount = setInterval(count, 1000);
							error("验证码已经发送");
							return false;
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
	//提交密码修改信息
	$(".loginbtn").click(function(){
		var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
		if(!phonetest.test($(".tel").val())){
			error("请填写正确的手机号");
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
				url:forget_url,
				dataType : "json" ,
				data: {
					mobile:$(".tel").val(),
					password:$(".password").val(),
					captcha:$(".code").val(),
					dowhat:"edit"
				},
				success:
					function(data){
						if(data==1){
							window.location.href=login_path+"?&openid="+openid;;
						}else if(data==-1){
							error("验证码不正确");
							return false;
						}else if(data==-2){
							error("未获取验证码");
							return false;
						}else if(data==-3){
							error("手机未被注册");
							return false;
						}
					},
				error:
					function(data){
	        			alert("服务器异常");
	            	}
			})
		}
	});
	//返回上一级
	$(".arrow").click(function(){
		if(openid==""||openid==null||openid==undefined){
			window.location.href= mine_path;
		}else{
			window.location.href= login_path+"?&openid="+openid;
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
});