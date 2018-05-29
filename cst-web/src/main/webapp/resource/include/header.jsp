<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?ec24d4cfebffa6fa8eb206bb4c00fbcc";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</head>
<body>

<div class="top">
    <div class="top-nav">

        <div class="logo">
           <a href="${home }/resource/electricBusiness/index.html"> <img src="${home }/resource/components/images/logo/logo-icon.jpg"></a>
        </div>

        <div class="top-nav-left">
            <p>      
            欢迎来到！</p>

        </div>

        <ul class="top_ul">
			<li><a href="${home }/product/cartProductListPage.do">购物车</a></li>
				
				<shiro:authenticated>
				<li class="line">|</li>
				<li><a href="${home }/orders/ordersListPage.do">我的订单</a></li>
				<li class="line">|</li>
				<li><a href="${home }/goodsAddress/goodsAddressListPage.do">收货地址</a></li>
				<li class="line">|</li>
				<li><shiro:principal property="mobile"/></li>
				
			   </shiro:authenticated>
				<shiro:notAuthenticated> 
					<li class="line">|</li>
					<li><a href="${home }/resource/jsp/login.jsp">登录</a></li>
					<li class="line">|</li>
					<li><a href="${home }/resource/jsp/signup.jsp">注册</a></li>
					
				</shiro:notAuthenticated>
				<shiro:authenticated>
				<li class="line">|</li>
					<li><a href="${home }/user/logout.do">退出</a></li>
				</shiro:authenticated>
			</ul>
    </div>
</div>

</body>
</html>
