<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/tag.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html >
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<title>${webTitle }</title>

<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/signup.js"></script>


<link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">
<link rel="stylesheet" href="<%=path%>/resource/components/header/header.css"/>
<link rel="stylesheet" href="<%=path%>/resource/css/registernew.css"/>

<script type="text/javascript">
	$(function() {
		Signup.init();
	});
</script>
</head>
<body>




<div class="regist_box">

    <form class="regist_form" method="post" id="signupForm" name="signupForm" action="<%=path%>/user/userAddEdit.do">
        <input type="hidden" id="dowhat" name="dowhat" value="edit" />
        <h1>密码修改</h1>
        <div id="err" style="color: red">
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
        <p class="telephone">已经有账号
            <a href="<%=path%>/resource/jsp/login.jsp">去登录</a>
        </p>
        <p><input class="tel-input" type="text" maxlength="11" name="mobile" id="mobile" placeholder="请输入您的手机号"></p>
        <p id="mobileError" style="color: #c81623"></p>
        <p>
            <input type="text" class="code-input"  name="captcha"
						id="captcha" placeholder="请输入验证码" value=""> <input type="button"
						class="code-btns" value="获取验证码(60s)"
						id="getCaptchaBtn">
        </p>
        <p class="text_right"><span class="left">设置新密码</span></p>
        <p><input class="paswd-input" type="password"  id="password" name="password" maxlength="24" placeholder="请输入密码"></p>

        <p><input type="button" class="registBtn" id="registerSubmit" value="保存"></p>
    </form>
</div>
<%@include file="../include/modal.jsp"%>
<p class="regist_footer">Copyright © 2005-2016 beidahuang.com 版权所有 苏ICP证16015588号</p>
</body>
</html>
