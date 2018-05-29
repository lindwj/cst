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
					<img src="<%=path%>/resource/images/info/zydl.png" style="display:block;margin:0 auto;width:100%;">
				</p>
				<h3>【专营店合作】</h3>
				<p>
					在全国县级以上行政区域建立专营店，单店覆盖30～50万人口。</p>
				<p>专营店实行“八统一”管理模式，即：“统一品质、统一价格、统一采购、统一物流、统一形象、统一宣传、统一培训、统一管理”。</p>
				<p>专营店担负着“品鉴中心”、“体验中心”和“分公司、经销商、办事处、事业部”的功能，拥有“区域独家经营权”。</p>
				<h3>【基地合作】</h3>
				<p>在自建、合建生产、加工基地的基础上，我们期望与致力于生态食品种植、养殖及生产加工型企业开展合作。具体方式，欢迎光临朋来先敬参观、洽谈！</p>
				<p class="am-article-lead"> 
					<img src="<%=path%>/resource/images/info/hzjl.png" style="display:block;margin:0 auto;">
				</p>
			</div>
		</article>
	</div>
	<br>
	<br>
	<br>
	<br>
	<%@include file="../../include/footer.jsp"%>

</body>
</html>
