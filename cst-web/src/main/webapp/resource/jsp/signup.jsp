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


<link rel="icon" type="images/png" href="<%=path%>/resource/images/logo.png">
<link rel="stylesheet" href="<%=path%>/resource/css/zhuce.css"/>
    

<script type="text/javascript">
	$(function() {
		Signup.init();
	});
</script>
</head>
<body>





<div class="zhucebox">
    <form class="regist_form" method="post" id="signupForm" name="signupForm" action="<%=path%>/user/userAddEdit.do">
    <input type="hidden" id="registType" name="registType" value="0" /> <input
				type="hidden" id="dowhat" name="dowhat" value="add" />
  <div class="zhuce">
  <a href="${home }/resource/electricBusiness/index.html">
     <img class="logo" src="<%=path%>/resource/images/logo.png" />
     </a>
     <h1 class="title">账号注册</h1>
     <p class="error" style="color: #c81623">
        <c:if test="${param.errcode == 100 }">
					<script type="text/javascript">
						$(function() {
							Signup.popModal("手机已存在");
						});
					</script>
					
				</c:if>
				<c:if test="${param.errcode == 101 }">
				<script type="text/javascript">
						$(function() {
							Signup.popModal("昵称 手机 密码 验证码 等不能为空");
						});
					</script>
					
				</c:if>
				<c:if test="${param.errcode == 102 }">
				<script type="text/javascript">
						$(function() {
							Signup.popModal("验证码不正确");
						});
					</script>
					
				</c:if>
				<c:if test="${param.errcode == 103 }">
					<script type="text/javascript">
						$(function() {
							Signup.popModal("请先获取验证码");
						});
					</script>
					
				</c:if>
        </p>
     <div class="text">
     <input type="hidden" value="1" id="flag">
     <input class="text1" type="text" placeholder="请输入昵称" name="nickname" id="nickname" maxlength="40"/>
       <input class="text1" type="text" placeholder="请输入手机号码" name="mobile" id="mobile" maxlength="11"/>
       <input class="text2" type="password" placeholder="请输入密码" id="password" name="password"/>
       <input class="text3" type="text" name="captcha"
						id="captcha" placeholder="短信验证码"  maxlength="4"/>
       <input class="anniu" type="button" value="获取验证码(60s)" id="getCaptchaBtn"/>
     </div>
     <div class="clear"></div>
     <a class="ljzc" href="javascript:void(0);" id="registerSubmit">立即注册</a>
     <div class="tk">
       <p>点击“立即注册”,即表示您同意并愿意遵守北大荒&nbsp;</p>
         <a href="">用户协议</a>
     </div>
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
