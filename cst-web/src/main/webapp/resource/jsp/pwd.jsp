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
<title>密码修改</title>

<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/signup.js"></script>


<link rel="icon" type="images/png" href="<%=path%>/resource/images/logo.png">
<link rel="stylesheet" href="<%=path%>/resource/css/xiugai.css"/>

<script type="text/javascript">
	$(function() {
		Signup.init();
	});
</script>
</head>
<body>



<div class="zhucebox">
<form class="regist_form" method="post" id="signupForm" name="signupForm" action="<%=path%>/user/userAddEdit.do">
  <input type="hidden" id="dowhat" name="dowhat" value="edit" />
  <div class="zhuce">
  <a href="${home }/resource/electricBusiness/index.html">
     <img class="logo" src="<%=path%>/resource/images/logo.png" />
     </a>
     <h1 class="title">重置密码</h1>
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
     <div class="text">
      <input type="hidden" value="2" id="flag">
       <input class="text1" type="text" placeholder="请输入手机号码" name="mobile" id="mobile" maxlength="11"/>
       <input class="text2" type="password" id="password" name="password" placeholder="请输入新的密码" />
       <input class="text3" type="text" name="captcha" id="captcha" placeholder="短信验证码" maxlength="4" />
       <input class="anniu" type="button" value="获取验证码(60s)" id="getCaptchaBtn" />
     </div>
     <div class="clear"></div>
     <a class="ljzc" href="javascript:void(0);" id="registerSubmit">确认修改</a>
     
  </div>
  </form>
</div>
<%@include file="../include/modal.jsp"%>
<div class="footerbox">
  <div class="footer">
    
    <p class="xinxi">Copyright ©2016 beidahuang.com 版权所有 苏ICP证16015588号 服务热线：400-863-1119</p>
  </div>
</div>

</body>
</html>
