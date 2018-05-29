<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html class="no-js">
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${webTitle }</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<!-- <meta http-equiv="Cache-Control" content="no-siteapp" /> -->

<link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
<link rel="stylesheet" href="<%=path%>/resource/css/register.css" />
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">

<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/signup.js"></script>

<style type="text/css">
label.error, label.tip {
	color: red;
}

/** 设置默认字体 **/
body,
button, input, select, textarea /* for ie */ {
    font: 12px/1.5 tahoma, arial, \5b8b\4f53, sans-serif;
}
</style>

<script type="text/javascript">
	$(function() {
		Signup.init();
	});
</script>
</head>
<body>



	<div class="login_box">

		<form id="signupForm" name="signupForm" class="login_form formid"
			method="post" action="<%=path%>/user/userAddEdit.do">
			<input type="hidden" id="registType" name="registType" value="0" /> <input
				type="hidden" id="dowhat" name="dowhat" value="add" />


			<h1>账号注册</h1>
			<div id="err" style="color: red">
				<c:if test="${param.errcode == 100 }">
					<h3>手机已存在</h3>
				</c:if>
				<c:if test="${param.errcode == 101 }">
					<h3>手机 密码 验证码 等不能为空</h3>
				</c:if>
				<c:if test="${param.errcode == 102 }">
					<h3>验证码不正确</h3>
				</c:if>
				<c:if test="${param.errcode == 103 }">
					<h3>请先获取验证码</h3>
				</c:if>
			</div>
			<div class="am-form-group">
				<p class="text_right">
					<span class="left">已经有账号？&nbsp;&nbsp;&nbsp;&nbsp;<a class="orange_color"
						href="<%=path%>/resource/jsp/login.jsp">去登录</a></span> 
				</p>

				<p>
					<input class="am-form-field js-pattern-mobile" type="text"
						id="mobile" name="mobile" autocomplete="off" placeholder="请输入您的手机号"
						required />
				</p>
			</div>

			<div class="am-form-group">
				<p>
					<input type="text" class="w150" maxlength="4" name="captcha"
						id="captcha" placeholder="请输入验证码" value=""> <input type="button"
						class="btn w150 right am-btn am-disabled"  value="获取验证码(60s)"
						id="getCaptchaBtn">
				</p>
			</div>


			<div class="am-form-group">
				<p>
					<input id="password" name="password" class="am-form-field"
						type="password"  placeholder="请输入密码" required />
				</p>
			</div>
			<p>
				<input id="registerSubmit" type="button"
					class="btn loginBtn am-btn am-disabled" value="注册">
			</p>
		</form>
		<!-- div class="login_right">
			<div class="text_right">
				<a href="/"> <img alt=""
					src="<%=path%>/resource/components/images/logo/logo-icon.jpg"></a>
			</div>
			<div class="app_div">
				<div class="app">
					<img alt="" src="<%=path%>/resource/images/login/weixin-fuwu.png">
				</div>
				<div class="weixin right">
					<img alt=""
						src="<%=path%>/resource/images/login/weixin-dingyue.png">
				</div>
			</div>
		</div> -->
	</div>
	<%@include file="../include/modal.jsp"%>
	<p class="login_footer">Copyright © 2005-2016 beidahuang.com 版权所有 苏ICP证16015588号 服务热线：4008631119</p>



</body>
</html>
