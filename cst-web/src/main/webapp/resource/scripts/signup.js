/**
 * Created by robot on 2015/5/30.
 */
var Signup = function() {
	var timeCntTotal = 60;// 记录获取验证码的冻结时间
	var iCount;
	var _popModal = function(msg) {
		$('#inok i').text(msg);
		$("#inok").show();
		setTimeout("$('#inok').fadeOut(5000)", 2000);
	};

	/**
	 * 判断是否是空
	 * 
	 * @param value
	 */
	function isEmpty(value) {
		if (value == null || value == "" || value == "undefined"
				|| value == undefined || value == "null") {
			return true;
		} else {
			value = value.replace(/\s/g, "");
			if (value == "") {
				return true;
			}
			return false;
		}
	}
	;

	function getRootPath() {
		// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
//		var curWwwPath = window.document.location.href;
//		// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
//		var pathName = window.document.location.pathname;
//		var pos = curWwwPath.indexOf(pathName);
//		// 获取主机地址，如： http://localhost:8083
//		var localhostPaht = curWwwPath.substring(0, pos);
//		// 获取带"/"的项目名，如：/uimcardprj
//		var projectName = pathName.substring(0,
//				pathName.substr(1).indexOf('/') + 1);
		return "";
	}

	var _updateTimeCnt = function() {
		timeCntTotal--;
		var text = "获取验证码(" + timeCntTotal + "s)";
		var tmp = parseInt(timeCntTotal, 10);
		if (tmp <= 0) {
			clearInterval(iCount);
			$('#getCaptchaBtn').val("获取验证码(60s)");
			$('#getCaptchaBtn').removeClass('am-disabled');
			timeCntTotal = 60;
		} else {
			$('#getCaptchaBtn').val(text);
		}
	};

	var _init = function() {

		$('#getCaptchaBtn').show();

		$('#getCaptchaBtn').click(function() {
			var val = $('#mobile').val();
			
			var registType = $('#flag').val();
			var rootp = getRootPath();
			if (!isEmpty(val)) {
				// GET /captcha
				$.ajax({
					type : "post",
					url : rootp + '/user/getMobileCaptcha.do',
					data : "mobile=" + val+"&registType="+registType,
					dataType : "text",
					error : function(e) {
						_popModal("获取验证码失败，请检查您的手机号！");
					},
					success : function(data) {
						if (data == 0) {
							// 获取短信成功
							$('#getCaptchaBtn').addClass('am-disabled');
							iCount = setInterval(_updateTimeCnt, 1000);
							_popModal("验证码已经发送");
						}else if(data == 2){
							_popModal("手机已注册！");
							// 重新开始
							$('#getCaptchaBtn').removeClass('am-disabled');
							if (iCount) {
								clearInterval(iCount);
								$('#getCaptchaBtn').val("获取验证码(60s)");
								$('#getCaptchaBtn').show();
								timeCntTotal = 60;
							}
						}
						
						else {
							_popModal("获取验证码失败，请检查您的手机号！");
							// 获取短信失败
							// 重新开始
							$('#getCaptchaBtn').removeClass('am-disabled');
							if (iCount) {
								clearInterval(iCount);
								$('#getCaptchaBtn').val("获取验证码(60s)");
								$('#getCaptchaBtn').show();
								timeCntTotal = 60;
							}
						}

					}
				});

			}
		});

		// 手机号码验证
		jQuery.validator
				.addMethod(
						"isMobile",
						function(value, element) {
							var length = value.length;
							var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
							return this.optional(element)
									|| (length == 11 && mobile.test(value));
						}, "请正确填写您的手机号码");

		$("#signupForm").validate({
			rules : {
				
				nickname : {
					required : true
				},
				mobile : {
					required : true,
					isMobile : true
				},
				captcha : {
					required : true
				},
				password : {
					required : true,
					minlength : 8
				}
			},
			messages : {
				nickname : {
					required : ""
				},
				mobile : {
					required : ""
				},
				captcha : {
					required : ""
				},
				password : {
					required : "",
					minlength : ""
				}
			}
		});

		var flag = false;

		$('#registerSubmit').click(function() {
			if (flag) {
				$("#signupForm").submit();
			} else {
				_popModal("密码至少8位，必须由字母数字组成！");
			}

		});

/*		$('#password')
				.blur(
						function(e) {

							// 密码为七位及以上并且字母、数字两项，强度是中等
							var mediumRegex = new RegExp(
									"^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$",
									"g");
							if (mediumRegex.test($(this).val())) {
								flag = true;
							} else {
								_popModal("密码至少8位，必须由字母数字组成！");
							}
							
						});*/
		
		
		$('#captcha')
		.blur(
				function(e) {

					if ($(this).val().length>4) {
						_popModal("验证码为4位数字！");
					}
					
				});
		

		// 输入验证码时使能验证按钮
		$('#captcha').on('input', function() {
			var val = $(this).val();
			if (!isEmpty(val)) {
				$('#registerSubmit').removeClass('am-disabled');
			} else {
				$('#registerSubmit').addClass('am-disabled');
			}
		});

		// 输入验证码时使能验证按钮
		$('#mobile').on('input', function() {
			var val = $(this).val();
			if (!isEmpty(val)) {
				$('#getCaptchaBtn').removeClass('am-disabled');
			} else {
				$('#getCaptchaBtn').addClass('am-disabled');
			}
		});
	};

	return {

		// main function to initiate the module
		init : function() {
			_init();
		},
	
	popModal : function(msg) {
		_popModal(msg);
	}
		
	};

}();
