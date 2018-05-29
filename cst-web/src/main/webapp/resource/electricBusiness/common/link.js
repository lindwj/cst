var base_url = "http://www.beidahuang.com";
var view_url = base_url + "/resource/electricBusiness/showAll.html";
var index_url = base_url + "/resource/electricBusiness/index.html";
var hide_url = base_url + "/resource/electricBusiness/hideFile.html";
var introduce_url = base_url + "/resource/electricBusiness/aboutUs.html";
var hzdl_url = base_url + "/resource/electricBusiness/hzdl.html";
var login_url = base_url + "/resource/jsp/login.jsp";
var register_url = base_url + "/resource/jsp/signup.jsp";
var pro_url = base_url
		+ "/resource/electricBusiness/productDetail.html?productUuid=";
var orderUp_path = base_url + "/resource/electricBusiness/orderUp.html";
var address_url = base_url + "/resource/electricBusiness/myAddress.html";
var myorder_url = base_url + "/resource/electricBusiness/myOrder.html";
var orderPay_url = base_url + "/orders/ordersPay.do?code=";
var orderDetail_path = base_url
		+ "/resource/electricBusiness/orderDetail.html?code=";
var buy_url = base_url + "/resource/electricBusiness/orderNow.html";
var shop_url = base_url + "/resource/electricBusiness/shoppingMarket.html";
var loginTest_url = base_url + "/user/isLoginSvc.do";
var logout_url = base_url + "/user/logout.do";
var product_url = base_url + "/indexBanner/indexBannerAddEditIniSvc.do";
var prodetail_url = base_url + "/product/productDetailSvc.do?productUuid=";
var market_url = base_url + "/cart/cartAddEdit.do";
var marketpro_url = base_url + "/product/cartProductSvcListPage.do";
var delet_url = base_url + "/cart/cartDeleteSvc.do";
var delall_url = base_url + "/cart/cartDeleteAllSvc.do";
var orderUp_url = base_url + "/orders/ordersAddEditDetailIniSvc.do";
var addressAdd_url = base_url + "/goodsAddress/goodsAddressAdd.do";
var addressEdit_url = base_url + "/goodsAddress/goodsAddressEditSvc.do";
var addressDelete_url = base_url + "/goodsAddress/goodsAddressDeleteSvc.do";
var addressDefault_url = base_url + "/goodsAddress/goodsAddressDefault.do";
var select_url = base_url + "/nation/getNation.do";
var search_url = base_url + "/goodsAddress/goodsAddressAddEditIniSvc.do";
var alladdress_url = base_url + "/goodsAddress/goodsAddressListPageSvc.do";
var orderControl_url = base_url + "/orders/ordersListPageSvc.do";
var orderDetail_url = base_url + "/orders/ordersDetailSvc.do";
var nowpay_url = base_url + "/orders/ordersAddEditIniSvc.do";
var islogin_url = base_url + "/user/isLoginWx.do";
var pay_url = base_url + "/orders/ordersPay.do?code=";
var fsure_url = base_url + "/orders/ordersAddEdit.do";
var shopid_url = base_url + "/orders/getShop.do";
var shoporder_url = base_url + "/ordersAddEditDetail.do";
var contact_url = base_url + "/TellUs/addTellUs.do";
var wl_url = base_url + "/LogisticsInfo/getLogistics.do";
var _hmt = _hmt || [];
(function() {
	var hm = document.createElement("script");
	hm.src = "https://hm.baidu.com/hm.js?ec24d4cfebffa6fa8eb206bb4c00fbcc";
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(hm, s)
})();
function error(error) {
	$(".alert").text(error);
	$(".alert").fadeIn();
	$(".alert").fadeOut(3000)
};