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
.am-article p {
	margin: 1.6em;
}
</style>
</head>
<body>
<body>
<%@include file="../../include/header.jsp"%>
	<div class="w960 prod_all">
		<article class="am-article">

			<div class="am-article-bd">
				<p class="am-article-lead">
					<img src="<%=path%>/resource/images/info/gywm.png"
						style="display:block;margin:0 auto;">
				</p>
				<h3>【公司简介】</h3>
				<p>
					江苏朋来先敬食品有限公司，专注于生态食品的销售，公司总部位于无锡食品科技园核心区——中国生态食品城。公司自建电商平台，线上线下同步销售，实现“千店专营，基地直供”。</p>
				<h3>【自有基地】</h3>
				<p>公司在黑龙江等地拥有水稻、杂粮种植基地60多万亩，实现“基地”掌控。</p>
				<h3>【自建渠道】</h3>
				<p>公司以统一模式、统一品牌，逐步在全国设立3000家专营店，实现“渠道”掌控。</p>
				<h3>【自主品牌】</h3>
				<p>公司主营产品包括粮食、食用油、调味品、乳制品、进口葡萄酒和保健食品等九大类160多个单品，主营产品均为自有品牌，实现“品牌”掌控。</p>
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
