$(function(){
	var orders="";
	var allPrice="";
	var order = window.location.href.split("?")[1];
//	alert(order);
	var reg=/#/g;

	order=order.replace(reg,'');
	
	var code=order.split("=")[1].split(",")[0];
	var pr=order.split("=")[1].split(",")[1];
	var starid=order.split("=")[1].split(",")[2];
	

	
//	$(".impay").click(function(){
//		alert(code);
//		alert(pr);
//		alert(starid);
		$.ajax({
	        type: 'POST',
	        url: pay_url,
	        dataType : "json" ,
	        data:{
	        	orderCode:code,
	        	totalFromBdh:pr
	        },
	        success:
	            function(data){
	        		WeixinJSBridge.invoke(
	        			'getBrandWCPayRequest',{
	     		           "appId": data.appId,     //公众号名称，由商户传入     
	    		           "timeStamp":data.timeStamp,         //时间戳，自1970年以来的秒数     
	    		           "nonceStr" : data.nonceStr, //随机串     
	    		           "package" : data.paypackage,     
	    		           "signType" : data.signType,         //微信签名方式：     
	    		           "paySign" : data.paySign //微信签名 
	    		       },
	        			function(res){     
	     		           if(res.err_msg == "get_brand_wcpay_request：ok" ) {
	     		        	  window.location.href=star_path+starid;
	     		           }else{
	     		        	  window.location.href=star_path+starid;
	     		           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
	     		       }
	        		)
	        	},
        	error: function() {
        		alert("error");
            }
	    });
//	})
	
	//获取订单价格
		var price="";
		price="￥<span>"+pr+"</span>";
		$(".price").append(price);
});