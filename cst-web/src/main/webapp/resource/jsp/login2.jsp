<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
    <link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
    <link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>

<title>${webTitle }</title>
<link rel="stylesheet" href="<%=path%>/resource/css/loginnew.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js" type="text/javascript"></script>

<script type="text/javascript">
$().ready(function() {
	 $("#loginFormFix").validate({
	        rules: {
	        	mobile: {
	    required: true
	   },
	   password: {
	    required: true,
	    minlength: 3
	   }
	  },
	        messages: {
	        	mobile: {
	    required: "请输入账号"
	   },
	   password: {
	    required: "请输入密码",
	    minlength: "密码不能小于8个字符"
	   }
	  }
	    });
	});

</script>		
<style type="text/css">
label.error, label.tip {
	color: red;
}
</style>
</head>
<body>


<div class="login_box">

    <form class="login_form" id="loginFormFix" method="post" action="<%=path%>/user/login.do">
        <h1>账号登录</h1>
        <p class="telephone">请输入手机号
            <a href="<%=path%>/resource/jsp/signup.jsp">去注册</a>
        </p>
        <p><input class="tel-input" type="text" name="mobile" maxlength="11" id="mobile" ></p>

        <p class="password">
            请输入密码
            <a href="<%=path%>/resource/jsp/pwd.jsp">忘记密码?</a>
        </p>
        <p><input class="paswd-input" id="password" name="password" type="password" maxlength="24"></p>
        <p class="error" style="color: #c81623">
				<c:if test="${param.errcode == 001 }">
					您输入的账号和密码不匹配，请检查。
				</c:if>
				<c:if test="${param.errcode == 101 }">
					账号或密码，不符合规范！
				</c:if>
        </p>
        <p><input type="button" class="loginBtn" id="loginBtn" value="登录"></p>
    </form>

</div>

<p class="login_footer">Copyright © 2005-2016 beidahuang.com 版权所有 苏ICP证16015588号</p>
</body>
<script src="<%=path%>/resource/scripts/loginnew.js"></script>

</html>
