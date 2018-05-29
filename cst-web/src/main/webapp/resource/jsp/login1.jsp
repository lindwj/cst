<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
<link rel="icon" type="image/png"
	href="<%=path%>/resource/images/favicon.png">
	<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
<link rel="stylesheet" href="<%=path%>/resource/css/register.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<style type="text/css">

label.error,label.tip{
    
    color: red;
}


</style>

<script type="text/javascript">
$().ready(function() {
	 $("#loginFormFix").validate({
	        rules: {
	        	mobile: {
	    required: true
	   },
	   password: {
	    required: true,
	    minlength: 8
	   }
	  },
	        messages: {
	        	mobile: {
	    required: "请输入账号"
	   },
	   password: {
	    required: "请输入密码",
	    minlength: "密码不能小于3个字符"
	   }
	  }
	    });
	});

</script>		
		
</head>
<body>

	<div class="login_box">
		<form id="loginFormFix" class="login_form formid" action="<%=path%>/user/login.do"  method="post">
			<h1>登录</h1>
			<div id="err" style="color: red">
				<c:if test="${param.errcode == 000 }">
					<h3>保存成功，赶紧登陆吧！</h3>
				</c:if>
				<c:if test="${param.errcode == 001 }">
					<h3>登陆失败，请重新尝试！</h3>
				</c:if>
				<c:if test="${param.errcode == 101 }">
					<h3>账号 密码 不能为空！</h3>
				</c:if>
			</div>
			<div class="am-btn am-btn-danger am-btn-block alert-danger"
				style="display:none;">
				<button class="close" data-close="alert"></button>
				<span> 请输入手机号和密码！ </span>
			</div>
			<div class="am-form-group">
				<p class="text_right">
					<span class="left">请输入手机号</span>马上<a class="orange_color"
						href="<%=path%>/resource/jsp/signup.jsp">去注册</a>
				</p>
				<p>
					<input class="am-form-field js-pattern-mobile" type="text"
						id="mobile" name="mobile" autocomplete="off" placeholder=""
						required />
				</p>
			</div>
			<div class="am-form-group">
				<p class="text_right">
					<span class="left">请输入密码</span><a href="<%=path%>/resource/jsp/pwd.jsp">忘记密码?</a>
				</p>
				<p>
					<input id="password" name="password" class="am-form-field"
						type="password" placeholder="" required />
				</p>
			</div>
			<p>
				<input type="submit" class="btn loginBtn" value="登录">
			</p>
		</form>
		<div class="login_right">
			<div class="text_right">
				<a href="/"> <img alt=""
					src="<%=path%>/resource/images/login/logo-on.png"></a>
			</div>
			<div class="app_div">
				<div class="app">
					<img alt="" src="<%=path%>/resource/images/login/weixin-fuwu.png">
				</div>
				<div class="weixin right">
					<img alt="" src="<%=path%>/resource/images/login/weixin-dingyue.png">
				</div>
			</div>
		</div>
	</div>

	<p class="login_footer">Copyright 2012-2015 www.plxj.cc 版权所有
		江苏朋来先敬食品有限公司 苏ICP备14050571号-2</p>


	
</body>
</html>
