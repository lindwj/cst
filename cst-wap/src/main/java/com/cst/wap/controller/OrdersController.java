package com.cst.wap.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cst.service.model.Cart;
import com.cst.service.model.GoodsAddress;
import com.cst.service.model.Orders;
import com.cst.service.model.OrdersDetail;
import com.cst.service.model.Product;
import com.cst.service.model.Shop;
import com.cst.service.model.Stock;
import com.cst.service.model.User;
import com.cst.service.util.Common;
import com.cst.wap.weixin.weChatpay.utils.CommonUtil;
import com.cst.wap.weixin.weChatpay.utils.GetWxOrderno;
import com.cst.wap.weixin.weChatpay.utils.RequestHandler;
import com.cst.wap.weixin.weChatpay.utils.Sha1Util;
import com.cst.wap.weixin.weChatpay.utils.TenpayUtil;
import com.cst.wap.weixin.weChatpay.utils.WeixinUtils;
import com.mysql.jdbc.log.Log;

import net.sf.json.JSONObject;

import com.cst.service.CartService;
import com.cst.service.GoodsAddressService;
import com.cst.service.OrdersDetailService;
import com.cst.service.OrdersService;
import com.cst.service.ProductService;
import com.cst.service.ShopService;
import com.cst.service.StockService;
import com.cst.service.UserService;

/**
 * @author lind
 */

@Controller
@RequestMapping("/orders")
public class OrdersController {

	// 业务逻辑对象
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrdersDetailService ordersDetailService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private GoodsAddressService goodsAddressService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private StockService stockService;

	// 查询结果
	private List<Orders> ordersList;

	private Map<String, Object> session;
	private HttpServletRequest request;
	private String doWhat;
	private int pare_moduleid;
	
	
	//异步通知接口
		@SuppressWarnings({ "static-access", "rawtypes" })
		@RequestMapping("/setShare.do")
		@ResponseBody
		public Map<String, String> setShare(HttpServletRequest request,HttpServletResponse response,String url) throws Exception{
	    	
		    		
//				response.setContentType("html/text; charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
//				out.flush();
//				out.close();
//		     }
			return WeixinUtils.setShareConfig(request, url);
		    
		    
	    }
	
	
	//异步通知接口
	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestMapping("/wxOrderNotice.do")
	
	public void wxOrderNotice(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	
//    	BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while((line = br.readLine())!=null){
//            sb.append(line);
//        }
//        
//	    Map map = new GetWxOrderno().doXMLParse(sb.toString());
//	    Orders orders=new Orders();
////	    WechatPayCallback  wechatPayCallback = new WechatPayCallback();
////	    wechatPayCallback.setOutTradeNo((String)map.get("out_trade_no"));
//	    orders.setCode((String)map.get("out_trade_no"));
//	    
//	    orders.setResultCode((String)map.get("result_code"));
//	    orders.setReturnCode((String)map.get("return_code"));
//	    orders.setTransactionId((String)map.get("transaction_id"));
//	    orders.setOpenid(((String)map.get("openid")));
//	    orders.setBdhOrderStatus("TRADE_SUCCESS");
//	    
//	 // update status
//        orders.setTotalFee(Double.valueOf((String)map.get("total_fee")));
//      //已付款
//        orders.setStatus(1);
//	    
//	    	
//	    	if("SUCCESS".equals(orders.getResultCode()) && "SUCCESS".equals(orders.getReturnCode())){
//	    		
//	    	this.ordersService.updateOrdersForwx(orders);
//	    	
//	    	//修改库存 发通知
//	    	this.ordersService.payOver(orders);
	    		
			response.setContentType("html/text; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			out.flush();
			out.close();
//	     }
	    
	    
    }
	
	
	
	//统一下单接口 for h5
	@SuppressWarnings({"static-access", "rawtypes" })
	@RequestMapping("/wxOrder.do")
	@ResponseBody
		public Orders wxOrder(Orders orders,HttpServletRequest request,HttpServletResponse response) throws Exception {
	    	
			
			
			
			String appid = "wxbb9e6069ef9733a1";
			String mch_id="1496415962";
			String appsecret =  "b9adf83def1c10b00ff46cb79f645d61";
			String partnerkey = "";
			String appkey = "aa450059573aa450059573aa45005957";
			
			String currTime = TenpayUtil.getCurrTime();
			//8位日期
			String strTime = currTime.substring(8, currTime.length());
			//四位随机数
			String strRandom = TenpayUtil.buildRandom(4) + "";
			//10位序列号,可以自行调整。
			String nonce_str = strTime + strRandom;
			
			
			//商品描述根据情况修改
			String body = "送礼物";
			
			//商户订单号
			String out_trade_no = orders.getOrderCode();
			
			//订单生成的机器 IP
			String spbill_create_ip = "127.0.0.1";
			
			//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url ="http://wj.wxycedu.com/cst-wap/orders/wxOrderNotice.do";
					
			String trade_type = "JSAPI";
			
			//金额转化为分为单位
			float sessionmoney = Float.parseFloat(String.valueOf(orders.getTotalFromBdh()));
			String finalmoney = String.format("%.2f", sessionmoney);
			finalmoney = finalmoney.replace(".", "");
			
			int intMoney = Integer.parseInt(finalmoney);
			
			
//			String openid ="";
//			String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+orders.getCode()+"&grant_type=authorization_code";
//
//			JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
//			if (null != jsonObject && jsonObject.containsKey("openid")) {
//				openid = jsonObject.getString("openid");
//				
////				System.out.println("openid:"+openid);
//			}
			
			String openid=null;
//			String name=null;
//			String headurl=null;
			
			Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
			if (bdhcookie != null) {
				// cookie 获取数据
				String value = URLDecoder.decode(bdhcookie.getValue());
				if (value.length() > 5) {
					openid=value.split(":")[0];
//					name=value.split(":")[1];
//					headurl=value.split(":")[2];
				} else {
					return null;
				}
			}else {
				return null;
			}
			
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", appid);  
			packageParams.put("mch_id", mch_id);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("body", body);  
			packageParams.put("out_trade_no", out_trade_no);  
			packageParams.put("total_fee", String.valueOf(intMoney));  
			packageParams.put("spbill_create_ip", spbill_create_ip);  
			packageParams.put("notify_url", notify_url); 
			packageParams.put("trade_type", trade_type); 
			packageParams.put("openid", openid);  

			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(appid, appsecret, partnerkey,appkey);
					
			String paySign = reqHandler.createSign(packageParams);
			
			
			String xml="<xml>"+
					"<appid>"+appid+"</appid>"+
					"<body><![CDATA["+body+"]]></body>"+
					"<mch_id>"+mch_id+"</mch_id>"+
					"<nonce_str>"+nonce_str+"</nonce_str>"+
					"<notify_url>"+notify_url+"</notify_url>"+
					"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
					"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
					"<total_fee>"+String.valueOf(intMoney)+"</total_fee>"+
					"<trade_type>"+trade_type+"</trade_type>"+
					"<openid>"+openid+"</openid>"+
					"<sign>"+paySign+"</sign>"+
					"</xml>";
			
			String prepay_id ="";
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			try {
				String jsonStr = new GetWxOrderno().getJsonStr(createOrderURL, xml);
			    Map map = new GetWxOrderno().doXMLParse(jsonStr);
			    String return_code = (String) map.get("return_code");
			    String return_msg = (String) map.get("return_msg");
			    String result_code = (String) map.get("result_code");
			    String err_code = (String) map.get("err_code");
			    String err_code_des = (String) map.get("err_code_des");
			    
			    if("SUCCESS".equals(result_code)&&"SUCCESS".equals(return_code)){
			    	prepay_id  = (String) map.get("prepay_id");	
			    	System.out.println("ok888");
			    }else{
			    	
			    	System.out.println(return_code+"/"+return_msg+"/"+result_code+"/"+err_code+"/"+err_code_des);
			    	return orders;
			    }
			    
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				
			}
			
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timeStamp = Sha1Util.getTimeStamp();
			prepay_id = "prepay_id="+prepay_id;
			String paypackage = prepay_id;
			finalpackage.put("appId", appid);  
			finalpackage.put("timeStamp", timeStamp);  
			finalpackage.put("nonceStr", nonce_str);  
			finalpackage.put("package", paypackage);  
			finalpackage.put("signType", "MD5");
			paySign = reqHandler.createSign(finalpackage);
			
			
			orders.setAppId(appid);
			orders.setTimeStamp(timeStamp);
			orders.setNonceStr(nonce_str);
			orders.setPaypackage(paypackage);
			orders.setSignType("MD5");
			orders.setPaySign(paySign);
			return orders;
		}
	
	//获取openid
	@RequestMapping("/getAllMoney.do")
	@ResponseBody
	public Double getAllMoney(User user)throws Exception{
		Orders order = new Orders();
		order.setCreateByUser(user.getAgentId());
		Orders o=ordersService.getPrice(order);
		Orders os=ordersService.getPrices(order);
		if(o!=null){
			if(os!=null){
				return o.getPrice()+os.getPrice();
			}else{
				return o.getPrice();
			}
		}else if(os!=null){
			return os.getPrice();
		}else{
			return 0.0;
		}
	}
	
	//获取openid
	@RequestMapping("/getNoMoney.do")
	@ResponseBody
	public Double getNoMoney(User user)throws Exception{
		Orders order = new Orders();
		order.setCreateByUser(user.getAgentId());
		Orders o=ordersService.getPriceNoBuy(order);
		Orders os=ordersService.getPriceNoBuys(order);
		if(o!=null){
			if(os!=null){
				return o.getPrice()+os.getPrice();
			}else{
				return o.getPrice();
			}
		}else if(os!=null){
			return os.getPrice();
		}else{
			return 0.0;
		}
	}
	
	//获取openid
	@RequestMapping("/getDayMoney.do")
	@ResponseBody
	public Double getDayMoney(User user)throws Exception{
		Orders order = new Orders();
		order.setCreateByUser(user.getAgentId());
		Orders o=ordersService.getPriceDay(order);
		Orders os=ordersService.getPriceDays(order);
		if(o!=null){
			if(os!=null){
				return o.getPrice()+os.getPrice();
			}else{
				return o.getPrice();
			}
		}else if(os!=null){
			return os.getPrice();
		}else{
			return 0.0;
		}
	}
	
	//获取openid
	@RequestMapping("/getMonthMoney.do")
	@ResponseBody
	public Double getMonthMoney(User user)throws Exception{
		Orders order = new Orders();
		order.setCreateByUser(user.getAgentId());
		Orders o=ordersService.getPriceMonth(order);
		Orders os=ordersService.getPriceMonths(order);
		if(o!=null){
			if(os!=null){
				return o.getPrice()+os.getPrice();
			}else{
				return o.getPrice();
			}
		}else if(os!=null){
			return os.getPrice();
		}else{
			return 0.0;
		}
	}
	
	//获取openid
	@RequestMapping("/getOpenid.do")
	@ResponseBody
	public String getOpenid(Orders orders)throws Exception{
		String openid ="";
//		String appid = "wxd2c47da0ba1fbfd0";
//		String appsecret =  "afc901fc013f6590ca06c784afea2066";
		
		String appid = "wxbb9e6069ef9733a1";
		String appsecret =  "b9adf83def1c10b00ff46cb79f645d61";
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+orders.getCode()+"&grant_type=authorization_code";

		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
		if (null != jsonObject && jsonObject.containsKey("openid")) {
			openid = jsonObject.getString("openid");
			return openid;
		}else{
			return openid;
		}
	}
	
	
	
	//获取用户信息
		@RequestMapping("/getUserInfo.do")
		@ResponseBody
		public Orders getUserInfo(Orders orders,HttpServletRequest request,HttpServletResponse response)throws Exception{
			String openid ="";
			String access_token="";
			String appid = "wxbb9e6069ef9733a1";
			String appsecret =  "b9adf83def1c10b00ff46cb79f645d61";
			
			//授权获取 access_token openid
			String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+orders.getCode()+"&grant_type=authorization_code";

			JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
			
//			System.out.println(jsonObject.toString());
			
			if (null != jsonObject && jsonObject.containsKey("openid")) {
				openid = jsonObject.getString("openid");
				access_token=jsonObject.getString("access_token");
				
//				System.out.println("access_token:"+access_token);
				
			}else{
				//授权获取 access_token openid  失败 返回 null
				
//				System.out.println(jsonObject.getString("errcode")+":"+jsonObject.getString("errmsg"));
				return null;
			}
			
			
//			//判断 access token是否过期
//			String isAccessURL = "https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;
//			JSONObject isAccessObject = CommonUtil.httpsRequest(isAccessURL, "GET", null);
//			if (null != isAccessObject && isAccessObject.containsKey("errcode")) {
//				if(!"0".equals(isAccessObject.getString("errcode"))){
//					//token 无效  刷新
//					
//					
//					
//						//刷新 access token
//						String refreshURL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appid+"&grant_type=refresh_token&refresh_token="+refresh_token;
//						JSONObject refreshObject = CommonUtil.httpsRequest(refreshURL, "GET", null);
//						if (null != refreshObject && refreshObject.containsKey("access_token")) {
//							
//						}else{
//							//授权获取 用户信息  失败 返回 null
//							return null;
//						}
//					
//				}
//				
//				
//			}
			
			
			//授权获取 用户信息
			String userinfoURL = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
			JSONObject userinfojsonObject = CommonUtil.httpsRequest(userinfoURL, "GET", null);
			
			System.out.println(1);
//			if (null != userinfojsonObject && userinfojsonObject.containsKey("openid")) {
				orders.setSubject(userinfojsonObject.getString("nickname"));
				
				String headimgurl = userinfojsonObject.getString("headimgurl");//获取头像
				
				orders.setOpenid(openid);
				
				
				if(headimgurl.length()>1 && !headimgurl.equals("")){
					
					
             	   String fileName = openid;
             	   String houZui = Common.download(headimgurl,fileName,request,"headImg");
             	   orders.setPic(openid+houZui);
             	   
                }else{
                	orders.setPic("default.jpg");
                	
                }
				
				
			/*}else{
				//授权获取 用户信息  失败 返回 null
				System.out.println(jsonObject.getString("errcode")+":"+jsonObject.getString("errmsg"));
				return null;
			}*/
				String value = orders.getOpenid()+ ":" + orders.getSubject()+ ":" + orders.getPic();
				value = URLEncoder.encode(value);
				Common.addCookie(response, "bdhcookie", value);
			
			return orders;
		}
	
	
	
	
	
	/** 取消订单*/
	@RequestMapping("/cancelOrderWx.do")
	@ResponseBody
	public int cancelOrderWx(Orders orders)throws Exception{
		if(orders.getCode()!=null){
			orders = this.ordersService.getOrdersByCode(orders);
			Stock stock = null;
			// 合并支付
			if ("0".equals(orders.getProductUuid())) {
				List<Stock> stkList=new ArrayList<Stock>();
				//库存
				for (OrdersDetail od : orders.getOrdersDetailList()) {
							// 生成 批量 修改 售卖数量 list
							stock=new Stock();
							stock.setSellCapacity(od.getCapacity() * -1);
							stock.setProductUuid(od.getProductUuid());
							stock.setShopId(orders.getShopId());
							stkList.add(stock);
				}
				//批量修改库存
				stockService.updateSellAll(stkList);
			} else {
				stock = new Stock();
				stock.setProductUuid(orders.getProductUuid());
				stock.setShopId(orders.getShopId());
				stock = stockService.getStockByPrd(stock);
				// 库存
				if (stock != null) {
					// 回退售卖数量
					stock.setSellCapacity(orders.getCapacity() * -1);
					stockService.updateSell(stock);
				}
			}
			ordersService.deleteOrders(orders.getCode());
			return 1;
		}else{
			return 0;
		}
	}

	/** 保存立即购买订单*/
	@RequestMapping("/orderSaveNowWx.do")
	@ResponseBody
	public Orders orderSaveNowWx(Orders orders)throws Exception{
		// 如果已登陆 匹配 默认地址 附近的店
		Stock stock = new Stock();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();
			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			orders.setCode(user.getUserId() + time.format(now) + i);
			orders.setCreateByUser(user.getUserId());
			// 0 支付宝   1微信
			orders.setPayType(2);
			orders.setWayType(1);
			// 查询库存 计算总金额
			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock = stockService.getStockByPrd(stock);
			// 显示库存
			if (stock != null) {
				if (orders.getCapacity() > (stock.getShowCapacity() - stock.getSellCapacity())) {
					// 库存不足 103
					orders.setCode(null);
					return orders;
				}
			} else {
				// 库存未设置
				orders.setCode(null);
				return orders;
			}
			Product product = this.productService.getProductByUuid(orders.getProductUuid());

			double total = product.getPrice() * orders.getCapacity();

			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}
		}
		ordersService.saveOrders(orders, "add");
		// 增加售卖数量
		return orders;
	}
	
	/** 保存立即购买订单*/
	@RequestMapping("/orderSaveNowWxOpen.do")
	@ResponseBody
	public Orders orderSaveNowWxOpen(Orders orders,HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 如果已登陆 匹配 默认地址 附近的店
		Stock stock = new Stock();
			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 1000000;
			orders.setCode(time.format(now) + i);
			User u=new User();
			u.setOpenId(orders.getOpenid());
			User users=userService.getUserByOpenid(u);
			if(users!=null){
				orders.setCreateByUser(users.getUserId());
			}else{
				
			}
			// 0 支付宝   1微信
			orders.setPayType(2);
			orders.setWayType(1);
			// 查询库存 计算总金额
			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock = stockService.getStockByPrd(stock);
			// 显示库存
			if (stock != null) {
				if (orders.getCapacity() > (stock.getShowCapacity() - stock.getSellCapacity())) {
					// 库存不足 103
					orders.setCode(null);
					return orders;
				}
			} else {
				// 库存未设置
				orders.setCode(null);
				return orders;
			}
			Product product = this.productService.getProductByUuid(orders.getProductUuid());
			
			double total = product.getPrice() * orders.getCapacity();
			
			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}
		// 删除购物车对应数据
		Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
		if (bdhcookie != null) {
			// cookie 获取数据
			String value = URLDecoder.decode(bdhcookie.getValue());
			if (value.length() > 5) {
				// 删除后 结束程序
				String[] bdnlist = value.split(";");
				Map<String, Integer> kv = new HashMap<String, Integer>();
				for (String st : bdnlist) {
					if (orders.getProductUuid().equals(st.split(":")[0])) {
						continue;
					}
					kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));
				}

				// 将删除后的数据 生成cookie值
				int j = 0;
				value = "";
				for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

					if (j == 0) {
						value = kvmap.getKey() + ":" + kvmap.getValue();
					} else {
						value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
					}
					j++;
				}
			}
			value = URLEncoder.encode(value);
			Common.addCookie(response, "bdhcookie", value);
		}
		ordersService.saveOrders(orders, "add");
		// 增加售卖数量
		return orders;
	}
	
	/** 立即购买接口 */
	@RequestMapping("/ordersBuyWx.do")
	@ResponseBody
	public Orders ordersAddEditIni(Orders orders) throws Exception {
		Product product = this.productService.getProductByUuid(orders.getProductUuid());
		orders.setProductUuid(product.getProductUuid());
		orders.setSubject(product.getName());
		orders.setPic(product.getPic());
		orders.setPrice(product.getPrice());
		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			List<GoodsAddress> goodsAddressList=null;
			GoodsAddress goodsAddress=new GoodsAddress();
			User user = (User)subject.getPrincipal();
			goodsAddress.setCreateByUser(user.getUserId());
			goodsAddressList=goodsAddressService.getGoodsAddressListPageWx(goodsAddress);
			GoodsAddress g=new GoodsAddress();
			for(int i=0; i<goodsAddressList.size();i++){
				if(goodsAddressList.get(i).getIsDefault()==1){
					g = goodsAddressList.get(i);
				}
			}
			if(g==null){
				g=goodsAddressList.get(0);
			}
			if (g != null) {
				Shop shop = new Shop();
				List<Shop> shopList=null;
				//根据 街道查询店
				if(g.getStreet()!=null && g.getStreet()>0){
					shop.setStreet(g.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
				shop.setCity(g.getCity());
				shopList = this.shopService.getShopByAddress(shop);
				
				}
				
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {
					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					shopList.add(sh);
				}
				orders.setShopId(shopList.get(0).getShopId());
				orders.setShopName(shopList.get(0).getName());
				if (shopList != null && shopList.size() > 0) {
					// 查询库存
					Stock stock = new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock = stockService.getStockByPrd(stock);
					// 显示库存
					if (stock != null) {
						orders.setShowCapacity(stock.getShowCapacity() - stock.getSellCapacity());
					} else {
						orders.setShowCapacity(0);
					}
				}
			}
		}else{
			List<GoodsAddress> goodsAddressList=null;
			GoodsAddress goodsAddress=new GoodsAddress();
			goodsAddress.setOpenid(orders.getOpenid());;
			goodsAddressList=goodsAddressService.getGoodsAddressListPageWxOpen(goodsAddress);
			GoodsAddress g=new GoodsAddress();
			if(goodsAddressList.size()==0||goodsAddressList == null){
				g.setGoodsAddressUuid(null);
			}else{
				for(int i=0; i<goodsAddressList.size();i++){
					if(goodsAddressList.get(i).getIsDefault()==1){
						g = goodsAddressList.get(i);
					}
				}
				if(g==null){
					g=goodsAddressList.get(0);
				}
			}
			if (g != null) {
				Shop shop = new Shop();
				List<Shop> shopList=null;
				//根据 街道查询店
				if(g.getStreet()!=null && g.getStreet()>0){
					shop.setStreet(g.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
				shop.setCity(g.getCity());
				shopList = this.shopService.getShopByAddress(shop);
				
				}
				
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {
					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					shopList.add(sh);
				}
				orders.setShopId(shopList.get(0).getShopId());
				orders.setShopName(shopList.get(0).getName());
				if (shopList != null && shopList.size() > 0) {
					// 查询库存
					Stock stock = new Stock();
					stock.setProductUuid(product.getProductUuid());
					stock.setShopId(shopList.get(0).getShopId());
					stock = stockService.getStockByPrd(stock);
					// 显示库存
					if (stock != null) {
						orders.setShowCapacity(stock.getShowCapacity() - stock.getSellCapacity());
					} else {
						orders.setShowCapacity(0);
					}
				}
			}
		}
		// 总金额
		orders.setTotalFromBdh(orders.getCapacity() * product.getPrice());
		return orders;
	}
	
	/** 微信订单分页*/
	@RequestMapping("/ordersListPageWx.do")
	@ResponseBody
	public Orders ordersListPageWx(Orders orders, Model model) throws Exception {
		if (orders.getStatus() == null || orders.getStatus() <= 0) {
			// 查询全部订单
			orders.setStatus(100);
		}
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		orders.setCreateByUser(user.getUserId());
		ordersList = ordersService.getOrdersListPage(orders);
		List<Orders> os=new ArrayList<Orders>();
		for(Orders o:ordersList){
			if(!o.getProductUuid().equals("0")){
				o.setProductName(productService.getProductByUuid(o.getProductUuid()).getName());
				os.add(o);
			}else{
				os.add(o);
			}
		}
		orders.setOrdersList(os);
		return orders;

	}
	/** 微信订单分页*/
	@RequestMapping("/ordersListPageWxOpen.do")
	@ResponseBody
	public Orders ordersListPageWxOpen(Orders orders, Model model) throws Exception {
		if (orders.getStatus() == null || orders.getStatus() <= 0) {
			// 查询全部订单
			orders.setStatus(100);
		}
		ordersList = ordersService.getOrdersListPageWxOpen(orders);
		List<Orders> os=new ArrayList<Orders>();
		for(Orders o:ordersList){
			if(!o.getProductUuid().equals("0")){
				o.setProductName(productService.getProductByUuid(o.getProductUuid()).getName());
				os.add(o);
			}else{
				os.add(o);
			}
		}
		orders.setOrdersList(os);
		return orders;
		
	}

	/** 微信订单详情*/
	@RequestMapping("/orderDetailWx.do")
	@ResponseBody
	public Orders orderDetailWx(Orders orders)throws Exception{
		orders = ordersService.getOrdersByCode(orders);
		if(orders.getProductUuid().equals("0")){
			return orders;
		}else{
			orders.setProductName(productService.getProductByUuid(orders.getProductUuid()).getName());
			return orders;
		}
	}

	/** 下单页面接口*/
	@RequestMapping("/ordersBuyDetailWx.do")
	@ResponseBody
	public Orders ordersBuyDetailWx(Orders orders) throws Exception {
		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			List<GoodsAddress> goodsAddressList=null;
				GoodsAddress goodsAddress=new GoodsAddress();
				User user = (User)subject.getPrincipal();
				goodsAddress.setCreateByUser(user.getUserId());
				goodsAddressList=goodsAddressService.getGoodsAddressListPageWx(goodsAddress);
				GoodsAddress g=new GoodsAddress();
				for(int i=0; i<goodsAddressList.size();i++){
					if(goodsAddressList.get(i).getIsDefault()==1){
						g = goodsAddressList.get(i);
					}
				}
				if(g==null){
					g=goodsAddressList.get(0);
				}
				List<Shop> shopList=null;
				Shop shop = new Shop();
				//根据 街道查询店
				if(g.getStreet()!=null && g.getStreet()>0){
					shop.setStreet(g.getStreet());				
					shopList = this.shopService.getShopByStreet(shop);
				}
				//如果 街道没有匹配的店
				if (shopList == null || shopList.size() == 0) {
					shop.setCity(g.getCity());
					shopList = this.shopService.getShopByAddress(shop);
				}
				//如果 区县也没有匹配的店  ，就匹配总部
				if (shopList == null || shopList.size() == 0) {

					shopList = new ArrayList<Shop>();
					Shop sh =new Shop();
					sh.setShopId(3);
					sh.setName("无锡市通江大道店");
					sh.setNickName("无锡市通江大道店");
					
					shopList.add(sh);
				}
				orders.setShopId(shopList.get(0).getShopId());
				orders.setShopName(shopList.get(0).getName());
				// 查询 商品及 库存list
				int i = 0;
				StringBuffer sb = new StringBuffer();
				if (orders.getOrdersDetailList().size() > 0) {
					// 筛选选中的 商品
					for (OrdersDetail od : orders.getOrdersDetailList()) {
						if (i == 0) {
							sb.append("'").append(od.getProductUuid()).append("'");
						} else {
							sb.append(",").append("'").append(od.getProductUuid()).append("'");
						}
						i++;
					}
				}
				OrdersDetail ordersDetail = new OrdersDetail();
				ordersDetail.setProductUuid(sb.toString());
				ordersDetail.setShopId(shopList.get(0).getShopId());
				List<OrdersDetail> ordersDetailList = null;
				if (sb.toString().length() > 1) {
					ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
				}
				// 赋值 购买的数量
					for (OrdersDetail od : ordersDetailList) {
						for (OrdersDetail oldod : orders.getOrdersDetailList()) {
							if (od.getProductUuid().equals(oldod.getProductUuid())) {
								od.setCapacity(oldod.getCapacity());
									// 计算库存
								if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
									od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
								} else {
									od.setShowCapacity(0);
								}
								break;
							}
						}
					}
					// 清空入参
					orders.getOrdersDetailList().clear();
					if (ordersDetailList != null && ordersDetailList.size() > 0) {
						orders.getOrdersDetailList().addAll(ordersDetailList);
					}
				}else {
					List<GoodsAddress> goodsAddressList=null;
					GoodsAddress goodsAddress=new GoodsAddress();
					goodsAddress.setOpenid(orders.getOpenid());
					goodsAddressList=goodsAddressService.getGoodsAddressListPageWxOpen(goodsAddress);
					GoodsAddress g=new GoodsAddress();
					if(goodsAddressList.size()==0||goodsAddressList == null){
						g.setGoodsAddressUuid(null);
					}else{
						for(int i=0; i<goodsAddressList.size();i++){
							if(goodsAddressList.get(i).getIsDefault()==1){
								g = goodsAddressList.get(i);
							}
						}
						if(g==null){
							g=goodsAddressList.get(0);
						}
					}
					List<Shop> shopList=null;
					Shop shop = new Shop();
					//根据 街道查询店
					if(g.getStreet()!=null && g.getStreet()>0){
						shop.setStreet(g.getStreet());				
						shopList = this.shopService.getShopByStreet(shop);
					}
					//如果 街道没有匹配的店
					if (shopList == null || shopList.size() == 0) {
						shop.setCity(g.getCity());
						shopList = this.shopService.getShopByAddress(shop);
					}
					//如果 区县也没有匹配的店  ，就匹配总部
					if (shopList == null || shopList.size() == 0) {

						shopList = new ArrayList<Shop>();
						Shop sh =new Shop();
						sh.setShopId(3);
						sh.setName("无锡市通江大道店");
						sh.setNickName("无锡市通江大道店");
						
						shopList.add(sh);
					}
					orders.setShopId(shopList.get(0).getShopId());
					orders.setShopName(shopList.get(0).getName());
					// 查询 商品及 库存list
					int i = 0;
					StringBuffer sb = new StringBuffer();
					if (orders.getOrdersDetailList().size() > 0) {
						// 筛选选中的 商品

						for (OrdersDetail od : orders.getOrdersDetailList()) {
								if (i == 0) {
									sb.append("'").append(od.getProductUuid()).append("'");
								} else {
									sb.append(",").append("'").append(od.getProductUuid()).append("'");
								}
								i++;
						}
					}
					OrdersDetail ordersDetail = new OrdersDetail();
					ordersDetail.setProductUuid(sb.toString());

					List<OrdersDetail> ordersDetailList = null;
					if (sb.toString().length() > 1) {
						ordersDetailList = this.ordersDetailService.getOrdersDetailListNoshop(ordersDetail);
					}
					// 赋值 购买的数量
					for (OrdersDetail od : ordersDetailList) {
						for (OrdersDetail oldod : orders.getOrdersDetailList()) {
							if (od.getProductUuid().equals(oldod.getProductUuid())) {
								od.setCapacity(oldod.getCapacity());
									// 计算库存
									if (od.getShowCapacity() != null && od.getShowCapacity() > 0) {
										od.setShowCapacity(od.getShowCapacity() - od.getSellCapacity());
									} else {
										od.setShowCapacity(0);
									}
									break;
								}
							}
						}
					// 清空入参
					orders.getOrdersDetailList().clear();
					if (ordersDetailList != null && ordersDetailList.size() > 0) {
						orders.getOrdersDetailList().addAll(ordersDetailList);
					}
				}
		// 总金额
		double total = 0;
		for (OrdersDetail od : orders.getOrdersDetailList()) {
			total = total + od.getCapacity() * od.getPrice();
		}
		orders.setTotalFromBdh(total);
		return orders;
	}

	public String editCancel() throws Exception {
		// this.setPare_moduleid(14);
		Orders orders = null;
		// orders = this.ordersService.getOrdersById(orders.getOrdersId());
		return "detail";
	}

	@RequestMapping("/orderSaveWx.do")
	@ResponseBody
	public Orders orderSaveWx(Orders orders, Model model) throws Exception {
		Stock stock = null;
		// 如果已登陆 匹配 默认地址 附近的店
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = (User) subject.getPrincipal();
			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 10000;
			orders.setCode(user.getUserId() + time.format(now) + i);
			orders.setCreateByUser(user.getUserId());
			// 0 支付宝   1微信
			orders.setPayType(2);
			orders.setProductUuid("0");
			orders.setWayType(1);
			// 查询库存 计算总金额
			double total = 0;
			Product product=null;
			int flag = 0;
			StringBuffer sb = new StringBuffer();
			i = 0;
			List<Stock> stkList=new ArrayList<Stock>();
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				// 金额
				product = this.productService.getProductByUuid(od.getProductUuid());
				total = total + product.getPrice() * od.getCapacity();
				// 设置订单编号
				od.setCode(orders.getCode());
				// 生成 购物车删除 uuid 入参
				if (i == 0) {
					sb.append("'").append(od.getProductUuid()).append("'");
				} else {
					sb.append(",").append("'").append(od.getProductUuid()).append("'");
				}
				i++;
			}
			OrdersDetail ordersDetail = new OrdersDetail();
			ordersDetail.setProductUuid(sb.toString());
			ordersDetail.setShopId(orders.getShopId());
			//批量查询库存
			List<OrdersDetail> ordersDetailList = null;
			if (sb.toString().length() > 1) {
				ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
			}
			//验证库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				for (OrdersDetail st : ordersDetailList) {	
					
					if(od.getProductUuid().equals(st.getProductUuid())){
						
						// 显示库存
						if (st.getShowCapacity() != null&&st.getShowCapacity()>0) {
							if (od.getCapacity() > (st.getShowCapacity() - st.getSellCapacity())) {

								// 库存不足 103
								flag = 1;
							}
						} else {
							// 库存未设置
							flag = 1;
						}
						if(flag==1){
							orders.setCode(null);
							return orders;
						}
						// 生成 批量 修改 售卖数量 list
						break;
					}
				}
			}
			//批量修改库存
			// 计算总金额 规避前端恶意修改总金额
			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}
			// 删除购物车对应数据
			Cart cart = new Cart();
			cart.setCreateByUser(user.getUserId());
			cart.setProductUuid(sb.toString());
			cartService.deleteCartAll(cart);
		}
		ordersService.saveOrders(orders, "add");
		// 保存订单 商品 list
		ordersDetailService.insertOrdersDetailAll(orders.getOrdersDetailList());
		
		return orders;
	}
	@RequestMapping("/orderSaveWxOpen.do")
	@ResponseBody
	public Orders orderSaveWxOpen(Orders orders, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Stock stock = null;
		// 如果已登陆 匹配 默认地址 附近的店
			// 订单编号
			Date now = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
			SecureRandom random = new SecureRandom();
			int i = Math.abs(random.nextInt()) % 1000000;
			orders.setCode(time.format(now) + i);
			User u=new User();
			u.setOpenId(orders.getOpenid());
			User users=userService.getUserByOpenid(u);
			if(users!=null){
				orders.setCreateByUser(users.getUserId());
			}else{
				
			}
			// 0 支付宝   1微信
			orders.setPayType(2);
			orders.setProductUuid("0");
			orders.setWayType(1);
			// 查询库存 计算总金额
			double total = 0;
			Product product=null;
			int flag = 0;
			StringBuffer sb = new StringBuffer();
			i = 0;
			List<Stock> stkList=new ArrayList<Stock>();
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				// 金额
				product = this.productService.getProductByUuid(od.getProductUuid());
				total = total + product.getPrice() * od.getCapacity();
				// 设置订单编号
				od.setCode(orders.getCode());
				// 生成 购物车删除 uuid 入参
				if (i == 0) {
					sb.append("'").append(od.getProductUuid()).append("'");
				} else {
					sb.append(",").append("'").append(od.getProductUuid()).append("'");
				}
				i++;
			}
			OrdersDetail ordersDetail = new OrdersDetail();
			ordersDetail.setProductUuid(sb.toString());
			ordersDetail.setShopId(orders.getShopId());
			//批量查询库存
			List<OrdersDetail> ordersDetailList = null;
			if (sb.toString().length() > 1) {
				ordersDetailList = this.ordersDetailService.getOrdersDetailList(ordersDetail);
			}
			//验证库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
				for (OrdersDetail st : ordersDetailList) {	
					
					if(od.getProductUuid().equals(st.getProductUuid())){
						
						// 显示库存
						if (st.getShowCapacity() != null&&st.getShowCapacity()>0) {
							if (od.getCapacity() > (st.getShowCapacity() - st.getSellCapacity())) {
								
								// 库存不足 103
								flag = 1;
							}
						} else {
							// 库存未设置
							flag = 1;
						}
						if(flag==1){
							orders.setCode(null);
							return orders;
						}
					}
				}
			}
			// 计算总金额 规避前端恶意修改总金额
			if (total != orders.getTotalFromBdh()) {
				orders.setTotalFromBdh(total);
			}
			// 删除购物车对应数据
			Cookie bdhcookie = Common.getCookieByName(request, "bdhcookie");
			if (bdhcookie != null) {
				// cookie 获取数据
				String value = URLDecoder.decode(bdhcookie.getValue());
				if (value.length() > 5) {
					// 删除后 结束程序
					String[] bdnlist = value.split(";");
					Map<String, Integer> kv = new HashMap<String, Integer>();
					for (String st : bdnlist) {
						for (OrdersDetail od : orders.getOrdersDetailList()) {
							if (od.getProductUuid().equals(st.split(":")[0])) {
								continue;
							}
							kv.put(st.split(":")[0], Integer.valueOf(st.split(":")[1]));
						}
					}

					// 将删除后的数据 生成cookie值
					int j = 0;
					value = "";
					for (Map.Entry<String, Integer> kvmap : kv.entrySet()) {

						if (j == 0) {
							value = kvmap.getKey() + ":" + kvmap.getValue();
						} else {
							value = value + ";" + kvmap.getKey() + ":" + kvmap.getValue();
						}
						j++;
					}
				}
				value = URLEncoder.encode(value);
				Common.addCookie(response, "bdhcookie", value);
			}
		ordersService.saveOrders(orders, "add");
		// 保存订单 商品 list
		ordersDetailService.insertOrdersDetailAll(orders.getOrdersDetailList());
		
		return orders;
	}
	
	/** 获取订单 */
	@RequestMapping("/getOrdersWx.do")
	@ResponseBody
	public Orders ordersPay(Orders orders, Model model) throws Exception {
		String code=orders.getState().split(",")[0];
		Orders o=ordersService.getOrdersById(code);
		return o;
	}
	
	/** 订单确认收货*/
	@RequestMapping("/ordersStatusWx.do")
	@ResponseBody
	public int ordersStatusWx(Orders orders)throws Exception{
		if(orders.getCode()!=null){
			orders.setStatus(2);
			ordersService.setStatus(orders);
			return 1;
		}else{
			return -1;
		}
	}

	/** 删除对象 */
	@RequestMapping("/ordersDelete.do")
	public String ordersDelete(Orders orders, Model model) throws Exception {
		orders = this.ordersService.getOrdersByCode(orders);

		this.ordersService.deleteOrders(orders.getCode());
		Stock stock = null;
		// 合并支付
		if ("0".equals(orders.getProductUuid())) {
			List<Stock> stkList=new ArrayList<Stock>();
			
			//库存
			for (OrdersDetail od : orders.getOrdersDetailList()) {
						
						// 生成 批量 修改 售卖数量 list
						stock=new Stock();
						stock.setSellCapacity(od.getCapacity() * -1);
						stock.setProductUuid(od.getProductUuid());
						stock.setShopId(orders.getShopId());
						stkList.add(stock);
			}

			//批量修改库存
			stockService.updateSellAll(stkList);
			

		} else {
			stock = new Stock();
			stock.setProductUuid(orders.getProductUuid());
			stock.setShopId(orders.getShopId());
			stock = stockService.getStockByPrd(stock);
			// 库存
			if (stock != null) {

				// 回退售卖数量
				stock.setSellCapacity(orders.getCapacity() * -1);
				stockService.updateSell(stock);
			}
		}

		return "redirect:/orders/ordersListPage.do?status=" + 100;
	}

	public List<Orders> getOrdersList() {
		return this.ordersList;
	}

	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

	public String getDoWhat() {
		return doWhat;
	}

	public void setDoWhat(String doWhat) {
		this.doWhat = doWhat;
	}

	public void setPare_moduleid(int pareModuleid) {
		pare_moduleid = pareModuleid;
	}

}
