<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../include/tag.jsp"%>
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
<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.css">

<link rel="stylesheet" href="<%=path%>/resource/css/index.css" />
<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/tradeDetail.js"></script>


<style type="text/css">
.am-article p{
	margin:1.6em;
}
</style>
</head>
<body>
<body>
<%@include file="../../include/header.jsp"%>
	<div class="w960 prod_all">
		<article class="am-article">
			<!-- 			<div class="am-article-hd">
				<h1 class="am-article-title"></h1>
				<p class="am-article-meta"></p>
			</div>
 -->
			<div class="am-article-bd">
				<p class="am-article-lead"> 
					<img src="<%=path%>/resource/images/info/lxwm.png" style="display:block;margin:0 auto;width:100%;">
				</p>
				<h3>【朋来先敬食品有限公司总部】</h3>
				<p>
					地址：无锡市崇安区通江大道898号 A座</p>
				<p>电话：400-863-1119  0510-68798999</p>
				<p>传真：0510-68798999</p>
				<p>邮箱：jsplxj@sina.com</p>
				<p>乘车路线：乘公交车55路在“天鹏食品城”站下车，或乘99路在“广益物流”站下车。</p>
				<p>自驾路线：通过百度地图或高德地图，搜索“朋来先敬”或“中国生态食品城”即可导航到达。</p>
			</div>
		</article>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../include/footer.jsp"%>

</body>
</html>
