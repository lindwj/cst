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
    <link rel="icon" type="images/png" href="<%=path%>/resource/images/logo.png">

<title>欢迎登录！北大荒生态食品</title>
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
<link rel="stylesheet" href="<%=path%>/resource/css/loginnew2.css" />
<link rel="stylesheet" href="<%=path%>/resource/electricBusiness/common/footer.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>

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
	    required: ""
	   },
	   password: {
	    required: "",
	    minlength: ""
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




<div class="headerbox">
  <div class="header">
  <a href="${home }/resource/electricBusiness/index.html">
    <img src="<%=path%>/resource/images/logo.png" />
    </a>
    
  </div>
</div>

<div class="bannerbox">
  <form class="login_form" id="loginFormFix" method="post" action="<%=path%>/user/login.do">

  <div class="banner">
     <img class="im1" src="<%=path%>/resource/images/back.png" />
     <%-- <img class="im2" src="<%=path%>/resource/images/laizi.png" />
     <img class="im3" src="<%=path%>/resource/images/dzr.png" />
     <img class="im4" src="<%=path%>/resource/images/dstsp.png" /> --%>
     <div class="dlk">
       <h1 class="zhdd">账户登录</h1>
       <p class="error" style="color: #c81623">
				<c:if test="${param.errcode == 101 }">
					账号或密码，不符合规范！
				</c:if>
        </p>
         <div class="input">
           <img src="<%=path%>/resource/images/usr.jpg" />
           <input class="srk1" type="text"  placeholder="请输入手机号" maxlength="11" name="mobile" id="mobile"/>
         </div>
       <div class="input">
         <img src="<%=path%>/resource/images/pas.jpg"/>
        <input class="srk2" type="password"  placeholder="请输入密码" name="password"  id="password" />
       </div>
       <input class="dxk" type="checkbox" style="display: none;" checked/>
       <p class="jzmm" style="display: none;">记住密码</p>
       <a class="wjmm" href="<%=path%>/resource/jsp/pwd.jsp">忘记密码？</a>
       <a class="login" href="javascript:void(0);" id="loginBtn">立即登录</a>
       <a class="zhuce" href="<%=path%>/resource/jsp/signup.jsp">前往注册>></a>

     </div>
  </div>
  </form>

</div> 




<%-- <div class="footerbox">
  <div class="footer">
    <div class="leftbox">
      <div class="line"></div>
      <ul class="lianjie">

        <li><a href="">公司介绍</a><span>|</span></li>
        <li><a href="">门店查询</a><span>|</span></li>
        <li><a href="">人才招聘</a><span>|</span></li>
        <li><a href="">合作代理</a><span>|</span></li>
        <li><a href="">合作伙伴</a><span>|</span></li>
        <li><a href="">企业团购 0510-68798999</a><span>|</span></li>
        <li><a href="">客服热线 400-863-1119 </a></li>

      </ul>
      <div class="line"></div>
      <div class="clear"></div>
      <p class="xinxi">苏ICP备16015588号<span>|</span>食品流通许可证编号：JY13202020009651<span>|</span>统一社会信用代码 91320200MA1MGN8MXH<br />Copyright © 2016-2017 北大荒,All Right Reserved.</p>
    </div>
    <div class="rightbox">
      <div class="gzh">
        <img style="height:75px; width:75px;" src="<%=path%>/resource/images/dyh.jpg" />
        <h1>微信订阅号</h1>
      </div>
      <div class="fwh">
        <img style="height:75px; width:75px;" src="<%=path%>/resource/images/fwh.jpg" />
        <h1>微信服务号</h1>
      </div>
    </div>
  </div>
</div> --%>

<div class="footer-all">
  <div class="footerbox">

  </div>

  <div class="footer1box">

  </div>

  <div class="clear"></div>
</div>

<%@include file="../include/modal.jsp"%>
<script src="<%=path%>/resource/electricBusiness/common/link.js"></script>
<script src="<%=path%>/resource/electricBusiness/common/footer.js"></script>
</body>
<script src="<%=path%>/resource/scripts/loginnew.js"></script>

<c:if test="${param.errcode == 001 }">
<script type="text/javascript">
popModal("您输入的账号和密码不匹配，请检查。");
</script>
				</c:if>

</html>
