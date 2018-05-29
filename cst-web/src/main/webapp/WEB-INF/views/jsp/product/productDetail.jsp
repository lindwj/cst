<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../resource/include/tag.jsp"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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

<link rel="icon" type="images/png"
	href="<%=path%>/resource/components/images/logo/logo-icon.jpg">


<link rel="stylesheet"
	href="<%=path%>/resource/components/header/footer.css" />

<link rel="stylesheet" href="<%=path%>/resource/css/amazeui.min.css">
<link rel="stylesheet"
	href="<%=path%>/resource/components/iconfont/iconfont.css">
<link rel="stylesheet"
	href="<%=path%>/resource/components/header/header.css" />

<link rel="stylesheet"
	href="<%=path%>/resource/css/ProductDetailnew.css" />
<script src="<%=path%>/resource/components/jquery/jquery-3.0.0.min.js"></script>
<script src="<%=path%>/resource/components/jquery/jquery.fly.min.js"></script>
<script src="<%=path%>/resource/scripts/ProductDetailnew.js"></script>



<script src="<%=path%>/resource/scripts/amazeui.min.js"></script>
<script src="<%=path%>/resource/scripts/productDetail.js"></script>



<script type="text/javascript">
function sbm(){
	var showCapacity=$("#showCapacity").val();
	var capacity =$("#amount-input").val();
	
	if(ProductDetail.isInteger(showCapacity) && ProductDetail.isInteger(capacity)){
		if(capacity*1>showCapacity*1){
			ProductDetail.popModal("库存不足");
			return;
		}
	}
	
	
	$("#buyForm").submit();
}

function cart(obj){
	
	var showCapacity=$("#showCapacity").val();
	var capacity =$("#amount-input").val();
	
	if(ProductDetail.isInteger(showCapacity) && ProductDetail.isInteger(capacity)){
		if(capacity>showCapacity){
			ProductDetail.popModal("库存不足");
			return;
		}
	}
	
	var productUuid =$("#productUuid").val();
	
	jQuery.ajax({
		  url: "<%=path%>/cart/cartAddEdit.do",
			cache : false,
			data : "num=" + capacity + "&productUuid=" + productUuid,
			success : function(data) {
				if (1 == data) {
					mv();
				}

			}
		});
	}
</script>

</head>
<body>
	<%@include file="../../../../resource/include/header.jsp"%>

	<form name="buyForm" id="buyForm" method="post"
		action="<%=path%>/orders/ordersAddEditIni.do">

		<input id="productUuid" name="productUuid" type="hidden"
			value="${product.productUuid}" /> <input id="showCapacity"
			type="hidden" value="${showCapacity }" />

		<div id="content">
			<%@include file="../../../../resource/include/toolbar.jsp"%>

			<div class="detail">

				<div id="detail-meta">

					<div>
						<div id="property">
							<div id="property-wrap">
								<div class="title" id="item-title">
									<h1>${product.name}</h1>
								</div>
								<div class="price-panel">
									<dl>
										<dt class="price-name" id="item-price">价格</dt>
										<dd>
											<div class="price-content">
												<em class="tm-yen">¥</em> <span class="tm-price">${product.price }</span>
											</div>
										</dd>
									</dl>

								</div>

								<div class="item-panel">
									<dl>
										<dt class="item-name">规格</dt>

										<dd>
											<div class="item-content">
												<c:forEach items="${productAttributeList}" var="atr">
												
												${atr.name}:  ${atr.value}${atr.unit}&nbsp;
												</c:forEach>
											</div>
										</dd>


									</dl>
								</div>


								<div class="meta-panel">
									<dl>
										<dt class="metatit">运费</dt>
										<dd>
											<div class="postAge">
					配送至：
												<shiro:notAuthenticated>
                                ${goodsAddress.provincestr}&nbsp;${goodsAddress.citystr}
			</shiro:notAuthenticated>
												<shiro:authenticated>
													<c:choose>
														<c:when test="${product.dowhat == '2' }">
					${goodsAddress.provincestr}&nbsp;${goodsAddress.citystr}&nbsp;${goodsAddress.districtstr}
					</c:when>
														<c:otherwise>
					<c:if test="${product.dowhat == '1' }">
					${goodsAddress.provincestr}&nbsp;${goodsAddress.citystr}&nbsp;${goodsAddress.districtstr}&nbsp;包邮
					</c:if>
					<c:if test="${product.dowhat == '0' }">
					${goodsAddress.provincestr}&nbsp;${goodsAddress.citystr}&nbsp;${goodsAddress.districtstr}
					</c:if>
					
					</c:otherwise>
													</c:choose>

												</shiro:authenticated>




											</div>
										</dd>
									</dl>
								</div>


								<div class="sku-panel">
									<dl class="amount">
										<dt>数量</dt>
										<dd id="j_amount">
											<span class="amount-widget"> <input type="text"
												id="amount-input" value="1" name="capacity" title="请输入数量">
												<span class="amount-btn"> <span
													id="amount-btn-increase">&#xe60c;</span> <span
													id="amount-btn-decrease">&#xe60d;</span>
											</span>
											</span> <em id="sku-stock"
												style="display: inline; margin-left: 15px;">
												
												库存${showCapacity}件
											</em>
										</dd>
									</dl>
								</div>
								<div class="range">
									<dl>
										<dt class="range-name">温馨提醒</dt>
										<dd>
											<div class="range-content"><c:if test="${product.dowhat != '0' }">由 ${shopName} 销售和发货，并提供售后服务。</c:if><c:if test="${product.dowhat == '0' }">本地区暂不支持配送</c:if></div>
										</dd>
									</dl>
								</div>

								<div class="action">
									<div class="buy">
										<a id="link-buy" href="javascript:void(0)"
											title="点击此按钮，到下一步确认购买信息。" role="button" onclick="sbm()">立即购买</a>

									</div>
									<div class="basket">
										<a href="javascript:void(0)" id="link-basket" role="button"
											onclick="cart(this)">加入购物车</a>
									</div>

								</div>
							</div>

						</div>

						<div id="gllery">
							<div id="booth">
								<a> <span><img id="img-booth"
										src="<c:if test='${productPicList !=null && productPicList.size() >0 }'>${productPicList.get(0).picUrl}</c:if>" /></span>
								</a>
							</div>

							<ul class="thumb" id="thumb">
								<%
									int i = 0;
								%>
								<c:forEach items="${productPicList}" var="img">
									<li><a><img <c:if test="i==0"> class="th-selected" </c:if>
											src="${img.picUrl}"/></a></li>

									<%
										i++;
									%>
								</c:forEach>
							</ul>
						</div>
					</div>


					<!--右侧商品推荐-->
					<div class="ald-skuRight">
						<div class="ald-inner">
							<div class="ald-hd">
								<del></del>
								<span>大家都在买</span>
							</div>
							<div class="ald-carousel">
								<div class="wrap-con">
									<ul class="ald-switchable-content" id="ald-switchable-content">
										<!--这儿放置推荐商品-->
										<li>
											<div class="wrap-con-img">
												<a href="<%=path%>/product/productDetail.do?productUuid=7af0ded7fd2e4b209484e785509556a4" class="ALDCLS-act"> <img
													src="http://bdhec.oss-cn-hangzhou.aliyuncs.com/banner/lbb.jpg">
												</a>
												<p class="look_price">¥288.0</p>
											</div>
											<p class="look_title">
												<a class="ALDCLS-act">粮宝宝 有机五常大米稻花香新米8KG</a>
											</p>
										</li>
										<li>
											<div class="wrap-con-img">
												<a href="<%=path%>/product/productDetail.do?productUuid=b603b0e565be44708d5aa9c10cc12826" class="ALDCLS-act"> <img
													src="http://bdhec.oss-cn-hangzhou.aliyuncs.com/banner/jxd.jpg">
												</a>
												<p class="look_price">¥192.0</p>
											</div>
											<p class="look_title">
												<a class="ALDCLS-act">吉祥稻五常稻花香大米8KG黑龙江大米</a>
											</p>
										</li>
										<li>
											<div class="wrap-con-img">
												<a href="<%=path%>/product/productDetail.do?productUuid=b9bf796a3ab1437db624cd5b2feff993" class="ALDCLS-act"> <img
													src="http://bdhec.oss-cn-hangzhou.aliyuncs.com/banner/tdc.jpg">
												</a>
												<p class="look_price">¥400.0</p>
											</div>
											<p class="look_title">
												<a class="ALDCLS-act"></a>
											</p>
										</li>
									</ul>



								</div>

							</div>
						</div>
					</div>

					<div class="clear"></div>
				</div>
			</div>

			<div id="bd">
				<div class="layout">
					<div class="col-main">
						<div class="mainwrap">
							<div class="tab-bar-box"></div>

							<div id="J_TabBarBox">

    <ul id="J_TabBar">

        <li tabindex="0" class="tm-selected" >
            <a tabindex="-1" href="javascript:void(0)" >商品详情</a>
        </li>

        <li tabindex="0" >
            <a tabindex="-1" href="javascript:void(0)" >售后保障</a>
        </li>
    </ul>
</div>

<div id="attributes" class="attributes">
    <div>
        <ul class="auth-list">
            <li>
                商品具有生产许可证编号，符合食品质量安全准入标准。
            </li>
        </ul>
    </div>

    <div class="attributes-list" id="J_AttrList">
        <p class="attr-list-hd tm-clear"><em>产品参数：</em></p>
        <ul id="J_AttrUL">

            <li>生产许可证编号：<span>${product.license}</span></li>
            <li>产品标准号：<span>${product.standardNo}</span></li>
            <li>品牌：<span>${product.brand}</span></li>
            <li>保质期：<span>${product.releaseDate}</span></li>
            <li >产地：<span>${product.provenance}</span></li>
            <li >净含量：<span>${product.netContent}</span></li>
            <li>条形码：<span>${product.barcode}</span></li>
            <li >系列：<span>${product.series}</span></li>
            <li >存储方式：<span>${product.storage}</span></li>
        </ul>
    </div>
</div>
							<div class="description">
							
							
								<!--<p><img src="images/main1.jpg"></p>-->
								${product.description}

							</div>
						</div>
					</div>

					<!--左侧商品推荐-->
					<div class="col-sub">
						<div class="left-banner" id="left-banner">
							<!--这儿放置推荐商品-->
							<div>
								<a href="<%=path%>/product/productDetail.do?productUuid=bc622f7d92394be69e2b1b7c97a0a11d"><img
									src="http://bdhec.oss-cn-hangzhou.aliyuncs.com/banner/plxjgxb.jpg"></a>
							</div>
							<div>
								<a href="<%=path%>/product/productDetail.do?productUuid=1ac36f996b2c47088256dab478a6e33a"><img
									src="http://bdhec.oss-cn-hangzhou.aliyuncs.com/banner/ssrsjhb.jpg"></a>
							</div>
							<div>
								<a href="<%=path%>/product/productDetail.do?productUuid=48197f9290d345b189115f0d38152e57"><img
									src="http://bdhec.oss-cn-hangzhou.aliyuncs.com/banner/ccyw10.jpg"></a>
							</div>

						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>

		</div>

	</form>

	<br>
	<br>
	<br>
	<br>
	<br>


	<%@include file="../../../../resource/include/footer.jsp"%>
	<%@include file="../../../../resource/include/modal.jsp"%>


	<script>
		$(function() {
			ProductDetail.init();
		});
	</script>
</body>
</html>
