//微信接口

//var base_url = "http://192.168.1.103:8080/business-wap";
//var base_url = "http://192.168.0.235:8080/business-wap";
//var base_url = "http://121.41.17.4:9030";
//var base_url = "http://www.bdhtdc.com";
//var base_url = "http://www.m.beidahuang.com";wj.wxycedu.com
//var base_url = "http://hrbloly.vip/cst-wap";
var base_url = "http://localhost:8080/cst-wap";
//var base_url = "http://wj.wxycedu.com/cst-wap";
//fenxiang
var base = "http://localhost:8080";
//var base = "http://hrbloly.vip";
//var base_url = "http://localhost:63342/business-wap";

//页面
//查看全部
var class_path = base_url + "/resource/wechat/class.html";
//首页

var sy_path = base_url + "/resource/wechat/loginWd2.html";
var index_path = base_url + "/resource/wechat/index.html?id=";
var jiangpin_path = base_url + "/resource/wechat/jiangpin.html?id=";
var bangdan_path = base_url + "/resource/wechat/paiming.html?id=";
var liwu_path = base_url + "/resource/wechat/liwu.html?id=";
var baoming_path = base_url + "/resource/wechat/userinfo.html?id=";

var star_path = base_url + "/resource/wechat/productDetail.html?id=";


//忘记密码或者修改密码
var forget_path = base_url + "/resource/wechat/forgetpasw.html";
//登录页面
var login_path = base_url + "/resource/wechat/login.html";
//注册页面
var register_path = base_url + "/resource/wechat/register.html";
//购物车
var shopping_path = base_url + "/resource/wechat/shoppingmall.html";
//我的页面
var mine_path = base_url + "/resource/wechat/mine.html";
//新建和编辑收货地址
var addressDetail_path = base_url + "/resource/wechat/addressDetail.html";
//下单结算
var selt_path = base_url + "/resource/wechat/orderUp.html";
//地址列表
var addressList_path = base_url + "/resource/wechat/address.html";
//支付页面
var pay_path = base_url + "/resource/wechat/pay.html";
//支付成功页面
var succ_path = base_url + "/resource/wechat/success.html";
//支付失败页面
var fail_path = base_url + "/resource/wechat/fail.html";
//待支付页面
var payWait_path = base_url + "/resource/wechat/payWait.html";
//待发货页面
var receiptWait_path = base_url + "/resource/wechat/receiptWait.html";
//已签收页面
var sign_path = base_url + "/resource/wechat/sign.html";
//订单全部列表页面
var order_path = base_url + "/resource/wechat/myOrder.html";
//订单详情页面
var orderDetail_path = base_url + "/resource/wechat/orderDetail.html?code=";
//产品链接
var pro_url = base_url + "/resource/wechat/productDetail.html?productUuid=";
//立即购买下单页面
var orderBuy_url = base_url + "/resource/wechat/orderBuy.html";
//微信用户页面
var mineout_path = base_url + "/resource/wechat/mineOut.html";
//微信地址列表
var addressWx_path = base_url +"/resource/wechat/addressWx.html";
//微信地址详情
var addressDetailWx_path = base_url + "/resource/wechat/addressDetailWx.html";
//下单页面（未登录）
var orderUpWx_path = base_url + "/resource/wechat/orderUpWx.html";
//立即购买（未登录）
var orderBuyWx_path = base_url + "/resource/wechat/orderBuyWx.html";
//推广二维码
var brand_path = base_url + "/resource/wechat/brand.html";
//二维码
var qrcode_path = base_url + "/resource/wechat/qrcode.html?manager=";
//注册（微代）
var wdRegister_path = base_url + "/resource/wechat/brand_register.html";
//注册（用户和微代）
var wdRegisterS_path = base_url + "/resource/wechat/wdRegisters.html?manager=";
//首页（微代）
var wdIndex_path = base_url + "/resource/wechat/weiD.html";
//登陆页面（微代）
var wdLogin_path = base_url + "/resource/wechat/loginWd.html";
//提示页面（微代）
var hint_path = base_url + "/resource/wechat/hint.html";
//登陆页面（微代客户）
var loginWds_path = base_url + "/resource/wechat/loginWds.html";
//物流详情页面
var wl_path = base_url + "/resource/wechat/wlDetail.html";



//接口
//我的订单
var orderDetail_url = base_url + "/orders/orderDetailWx.do?code=";
//退出
var logout_url = base_url + "/user/logoutWx.do";
//产品页面


///黑龙江项目

var event_url = base_url + "/event/eventAddEditIni.do";

//list
var star_url = base_url + "/star/starListPage.do";

//toupiao shouye
var starinfo_url = base_url + "/star/starListInfo.do";


var stardetail_url = base_url + "/star/starAddEditIni.do?id=";


var toupiao_url = base_url + "/star/toupiao.do";

var setShare_url = base_url + "/orders/setShare.do";



var shualiwu_url = base_url + "/shualiwu/shualiwuAddEdit.do";

var shualiwuup_url = base_url + "/shualiwu/shualiwuup.do";


var shualiwulist_url = base_url + "/shualiwu/shualiwuListPage.do";

var jiangpinlist_url = base_url + "/jiangpin/jiangpinListPage.do";

//排名
var getStarList_url = base_url + "/star/getStarList.do";

var liwuListPage_url = base_url + "/liwu/liwuListPage.do";


var upload_url = base_url + "/upload/headUpload.do";

var wysq_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbb9e6069ef9733a1&redirect_uri="+sy_path+"&response_type=code&scope=snsapi_userinfo&state=";

//黑龙江项目

//var product_url = base_url + "/indexBanner/indexBannerAddEditIniSvc.do";
//购物车接口
var shop_url = base_url + "/product/cartProductListPage.do";
//商品详情页
var prodetail_url = base_url + "/product/productDetailSvc.do?productUuid=";
//添加进入购物车接口
var cart_url = base_url + "/cart/cartSaveWx.do";
//是否登录接口
var isLogin_url = base_url + "/user/isLoginWx.do";
//我的购物车接口
var basket_url = base_url + "/product/cartProductListPage.do";
//注册接口
var register_url = base_url + "/user/userSaveWx.do";
//验证码接口（未注册）
var validate_url = base_url + "/user/getMobileCaptchaWx.do";
//验证码接口（注册过）
var validateCode_url = base_url + "/user/getMobileCodeWx.do";
//登录接口
var login_url= base_url + "/user/loginWx.do";
//忘记密码接口
var forget_url = base_url + "/user/forgetWx.do";
//删除购物车商品接口
var deleteShop_url = base_url + "/cart/cartDeleteWx.do";
//订单列表接口
var orders_url = base_url + "/orders/ordersListPageWx.do";
//删除地址接口
var deleteAddress_url = base_url + "/goodsAddress/goodsAddressDeleteWx.do";
//添加地址或修改地址接口
var addAddress_url = base_url + "/goodsAddress/addressSaveWx.do";
//地址列表接口
var addressList_url = base_url + "/goodsAddress/addressListPageWx.do";
//回填地址接口
var getAddress_url = base_url + "/goodsAddress/addressGetWx.do?goodsAddressUuid=";
//点击设为默认地址接口
var defaultAddress_url = base_url + "/goodsAddress/goodsAddressDefault.do";
//取街道接口
var getStreet_url = base_url + "/goodsAddress/getStreetWx.do";
//获取默认地址或者第一个地址
var firstAddress_url = base_url + "/goodsAddress/getDefaultAddressWx.do";
//判断是否有地址
var isGoodsAddress_url = base_url + "/goodsAddress/isGoodsAddressWx.do";
//下单页面数据接口
var orderUp_url = base_url + "/orders/ordersBuyDetailWx.do";
//提交订单接口
var saveOrder_url = base_url + "/orders/orderSaveWx.do";
//是否有地址
var isAddress_url = base_url + "/goodsAddress/isGoodsAddressWx.do";
//订单数据
var getOrder_url = base_url + "/orders/getOrdersWx.do";
//订单支付接口
var pay_url = base_url + "/orders/wxOrder.do";
//取消订单接口
var cancelOrder_url = base_url + "/orders/cancelOrderWx.do";
//立即购买
var buyNow_url = base_url + "/orders/ordersBuyWx.do";
//保存订单（立即购买）
var saveNow_url = base_url + "/orders/orderSaveNowWx.do";
//订单确认收货
var okOrders_url = base_url + "/orders/ordersStatusWx.do";
//地址列表（未登录）
var addressListOpen_url = base_url + "/goodsAddress/addressListPageWxOpen.do";
//获取默认地址或者第一个地址（未登录）
var firstAddressOpen_url = base_url + "/goodsAddress/getDefaultAddressWxOpen.do";
//地址添加或者修改（未登录）
var addAddressOpen_url = base_url + "/goodsAddress/addressSaveWxOpen.do";
//选取默认地址（未登录）
var defaultAddressOpen_url = base_url + "/goodsAddress/goodsAddressDefaultOpen.do";
//订单列表接口（未登录）
var ordersOpen_url = base_url + "/orders/ordersListPageWxOpen.do";
//提交订单接口（未登录）
var saveOrderWx_url = base_url + "/orders/orderSaveWxOpen.do";
//立即购买（未登录）
var saveNowOpen_url = base_url + "/orders/orderSaveNowWxOpen.do";
//获取openid
var getOpenid_url = base_url + "/orders/getOpenid.do";
//获取QRCode
var getQRCode_url = base_url + "/code/getQRCode.do";
//注册接口（微代）
var bRegister_url = base_url + "/manager/agencySaveWx.do"; 
//发送验证码（微代）
var getBMobileCaptcha_url = base_url + "/manager/getMobileCaptchaWd.do";
//是否有该用户（微代）
var getUserCtrl_url = base_url + "/user/isUser.do";
//注册（微代客户）
var wdRegister_url = base_url + "/user/registerWd.do";
//判断是否注册（微代）
var isManager_url = base_url + "/manager/isManager.do";
//授权获取头像和昵称（微代）
var userInfo_url = base_url + "/orders/getUserInfo.do";
//获取微代信息
var managerInfo_url = base_url + "/manager/getManagerOpenid.do";
//获取当日粉丝
var getFan_url = base_url + "/user/getFan.do";
//获取当日粉丝
var getFans_url = base_url + "/user/getFans.do";
//获取所有价格
var getAllMoney_url = base_url + "/orders/getAllMoney.do";
//获取未结算价格
var getNoMoney_url = base_url + "/orders/getNoMoney.do";
//获取当日价格
var getDayMoney_url = base_url + "/orders/getDayMoney.do";
//获取当月价格
var getMonthMoney_url = base_url + "/orders/getMonthMoney.do";
//登陆（微代）
var loginWd_url = base_url + "/user/isLoginWd.do";
//登陆（微代客户）
var loginWds_url = base_url + "/user/loginWd.do";
//物流接口
var wl_url = base_url + "/LogisticsInfo/getLogistics.do";


//$(".menu").find("li").eq(0).click(function(){
//	window.location.href=index_path;
//});
//$(".menu").find("li").eq(1).click(function(){
//	window.location.href=class_path+ '?code=' + 1;
//});
//$(".menu").find("li").eq(2).click(function(){
//	window.location.href=shopping_path;
//});
//$(".menu").find("li").eq(3).click(function(){
////	$.ajax({
////		type: 'POST',
////        url: isLogin_url,
////        dataType : "json" ,
////        data: {},
////
////        success:
////            function(data){
////        		isLogin(data);
////            },
////        error:
////            function(data,textStatus){
////
////            }
////	});
//});

//判断是否登陆的跳转
//function isLogin(info){
//	if(info.status==0){
//		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/mineOut.html&response_type=code&scope=snsapi_base&state=State#wechat_redirect";
//	}else{
//		window.location.href=mine_path;
//	}
//}

//弹框提示方法
function error(error){
	$(".alert").text(error);
	$(".alert").fadeIn(500);
	$(".alert").fadeOut(1500);
}

function setBgColor(info) {
	$("#mirco").attr("style", "background-color:"+info.bgcolor) ;
	
}
