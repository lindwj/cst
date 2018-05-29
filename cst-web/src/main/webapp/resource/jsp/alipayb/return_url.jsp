<%
/* *
 功能：支付宝页面跳转同步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 可放入HTML等美化页面的代码、商户业务逻辑程序代码
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 * */
%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cst.web.alipay.util.*"%>
<%@ page import="com.cst.web.alipay.config.*"%>
<%@ page import="com.cst.service.OrdersService"%>
<%@ page import="com.cst.service.impl.OrdersServiceImpl"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.cst.service.model.Orders"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="<%=path%>/resource/scripts/jquery.min.js"></script>
		<title>支付宝页面跳转同步通知页面</title>
  </head>
  <body>
<%
	
	
//获取支付宝GET过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		params.put(name, valueStr);
	}
	
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

	String trade_no = request.getParameter("trade_no");				//支付宝交易号
	String order_no = request.getParameter("out_trade_no");	        //获取订单号
	String total_fee = request.getParameter("total_fee");	        //获取总金额
	String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
	String trade_status = request.getParameter("trade_status");		//交易状态
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
	//计算得出通知验证结果
	boolean verify_result = AlipayNotify.verify(params);
	Orders orders=new Orders(); 
	
	if(verify_result){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		
		if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
			//canevent get service
			try{
				
				ServletContext sc = this.getServletContext();  
			    ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
			    OrdersService ordersService=(OrdersServiceImpl)ac.getBean("OrdersService");
	            //查看是否已经更新过状态
	            orders = ordersService.getOrdersById(order_no);
	            if(orders != null && (orders.getBdhOrderStatus() == null || orders.getBdhOrderStatus().length()==0 || ! "TRADE_SUCCESS".equals(orders.getBdhOrderStatus()))){
	            // update status
	            orders = new Orders();
	            orders.setCode(order_no);
	            orders.setAlipayOrderNotify(trade_status);
	            orders.setBdhOrderStatus("TRADE_SUCCESS");
	            orders.setTradeNo(trade_no);
	            orders.setTotalFee(Double.valueOf(total_fee));
	            orders.setBuyerEmail(buyer_email);
	            //已付款
	            orders.setStatus(1);
	            ordersService.saveOrders(orders, "edit");
	            
	            
	          //修改库存 发通知
	            ordersService.payOver(orders);
	            }
	            
				}catch (Exception e ){
				out.println("支付宝 return to canevent 状态更新异常！");
				e.printStackTrace();
			}
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
		}
		
		//该页面可做页面美工编辑
		//out.println("验证成功<br />");
		//out.println("trade_no=" + trade_no);
		
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

		//////////////////////////////////////////////////////////////////////////////////////////
	}else{
		//该页面可做页面美工编辑
		out.println("");
	}
%>
	
  </body>
  <script type="text/javascript">
 $(document).ready(function(){  
	 window.location.href="<%=path%>/resource/electricBusiness/orderDetail.html?code=<%=order_no%>";
	 //http://www.beidahuang.com/resource/electricBusiness/orderDetail.html?code=48201703151716256250
	 
	 ///registationInfo/myRegistDetail
 });
      //window.onload=function(){
    	  //document.getElementById('myRegistDetail').submit();
      //};
   </script>
</html>
