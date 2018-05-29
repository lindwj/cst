
$(function () {

	var openid="";
	openid= window.location.href.split("?&openid=")[1];
	if(openid==null||openid==undefined||openid==""){
		$.ajax({
	        type: 'POST',
	        url: orders_url,
	        dataType : "json" ,
	        data: {
	        	status:1
	        },

	        success:
	            function(data){


	                getOrderList(data.ordersList);


	            },
	        error:
	            function(data,textStatus){


	            }

	    });
	}else{
		$.ajax({
	        type: 'POST',
	        url: ordersOpen_url,
	        dataType : "json" ,
	        data: {
	        	status:1,
	        	openid:openid
	        },

	        success:
	            function(data){
	                getOrderList(data.ordersList);
	            },
	        error:
	            function(data,textStatus){
	            }

	    });
	}


    //解析json数据，页面显示
    function getOrderList(info) {
        var order = "";
        var num = "";
        var ordset = "";
        var orders = "";
        var statebtn = "";
        $.each(info, function(i,item){
        	var numdetail=0;
        	if(item.productUuid!="0"){
        		order="";
        		order += "<div class='order_box' data-id='"+ item.code +"'>"
                +   "<div class='img_box'>"
                +   "<img src='"+ item.pic +"'>"
                +   "</div>"
                +   "<div class='middle'>"
                +   "<p class='title escp'>"+ item.productName +"</p>"
                +   "</div>"
                +   "<div class='right'>"
                +   "<p class='price'>￥"+ item.price +"</p>"
                +   "<p class='num'>x<span class='nums'>"+ item.capacity +"</span></p>"
                +   "</div>"
                +   "<div class='clear'>"
                +   "</div>"
                +   "</div>";
            numdetail=parseInt(item.capacity);
        	}else{
        		order="";
        		$.each(item.ordersDetailList,function(j,jtem){
        			
                order += "<div class='order_box' data-id='"+ jtem.code +"'>"
                    +   "<div class='img_box'>"
                    +   "<img src='"+ jtem.pic +"'>"
                    +   "</div>"
                    +   "<div class='middle'>"
                    +   "<p class='title escp'>"+ jtem.productName +"</p>"
                    +   "</div>"
                    +   "<div class='right'>"
                    +   "<p class='price'>￥"+ jtem.price +"</p>"
                    +   "<p class='num'>x<span class='nums'>"+ jtem.capacity +"</span></p>"
                    +   "</div>"
                    +   "<div class='clear'>"
                    +   "</div>"
                    +   "</div>";
                numdetail+=parseInt(jtem.capacity);

            	});
            }


            ordset = "<div class='settlement' data-status='"+ item.status +"'>"
                +   "<p>共计:"+ numdetail +"件商品&nbsp;&nbsp;合计:￥<span>"+ item.totalFromBdh.toFixed(2) +"</span>（不含运费￥"+ item.totalFee.toFixed(2) +"）</p>"
                +   "</div>";

            if(item.status == 9){

                statebtn = "<div class='btn_box'>"
                    +   "<input class='payorder' data-price='"+item.totalFromBdh+"' data-code='"+item.code+"' type='button' value='付款'>"
                    +   "<input class='cancel' data-code='"+item.code+"' type='button' value='取消订单'>"
                    +   "<div class='clear'>"
                    +   "</div>"
                    +   "</div>";

            }else if(item.status == 1){

                statebtn = "<div class='btn_box'>"
                    +   "<input class='payorder' data-id='"+item.code+"' type='button' value='确认收货'>"
                    +   "<div class='clear'>"
                    +   "</div>"
                    +   "</div>";

            }else if(item.status == 2){
                statebtn = "<div class='btn_box'>"
                    +   "<input class='payorder' data-id='"+item.code+"' type='button' value='查看'>"
                    +   "<div class='clear'>"
                    +   "</div>"
                    +   "</div>";
            }else if(item.status == -1){
                statebtn = "<div class='btn_box'>"
                    +   "<input class='payorder' data-id='"+item.code+"' type='button' value='交易关闭'>"
                    +   "<div class='clear'>"
                    +   "</div>"
                    +   "</div>";
            }

            orders += order + ordset +statebtn;
        });

        $(".orderAll").append(orders);

        $(".payorder").click(function(){
        	if($(this).val()=="付款"){
        		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/pay.html&response_type=code&scope=snsapi_base&state="+$(this).attr("data-code")+","+$(this).attr("data-price")+"#wechat_redirect";
        	}else if($(this).val()=="确认收货"){
        		$.ajax({
                    type: 'POST',
                    url: okOrders_url,
                    dataType : "json" ,
                    data:
                    {
                        code:$(this).attr("data-id"),
                    },
                    success:
                        function(data){
                    		if(data==1){
                    			window.location.reload();
                    		}
                        },
                    error:
                        function(data,textStatus){
                            alert("服务器异常，请稍后再试");
                        }
                });
        	}else if($(this).val()=="查看"){
        		var code= $(this).attr("data-id");
        		if(openid==null||openid==""||openid==undefined){
        			window.location.href =  orderDetail_path + code;
        		}else{
        			window.location.href =  orderDetail_path + code + "?&openid=" + openid ;
        		}
        	}
        })
        
        $(".cancel").click(function(){
        	$.ajax({
                type: 'POST',
                url: cancelOrder_url,
                dataType : "json" ,
                data:
                {
                    code:$(this).attr("data-code"),
                },
                success:
                    function(data){
                		if(data==1){
                			window.location.reload();
                		}
                    },
                error:
                    function(data,textStatus){
                        alert("服务器异常，请稍后再试");
                    }
            });
        })

        //待付款
        $(".payment").click(function(){
        	if(openid==null||openid==""||openid==undefined){
        		window.location.href=payWait_path;
        	}else{
        		window.location.href=payWait_path+"?&openid="+openid;
        	}
        });
        //待收货
        $(".receipt").click(function(){
        	if(openid==null||openid==""||openid==undefined){
        		window.location.href=receiptWait_path;
        	}else{
        		window.location.href=receiptWait_path+"?&openid="+openid;
        	}
        });
        //已签收
        $(".sign").click(function(){
        	if(openid==null||openid==""||openid==undefined){
        		window.location.href=sign_path;
        	}else{
        		window.location.href=sign_path+"?&openid="+openid;
        	}
        });
        //全部
        $(".all").click(function(){
        	if(openid==null||openid==""||openid==undefined){
        		window.location.href=order_path;
        	}else{
        		window.location.href=order_path+"?&openid="+openid;
        	}
        });
        
      //订单详情
        $(".order_box").click(function(){
            var code= $(this).attr("data-id");
            if(openid==null||openid==""||openid==undefined){
            	window.location.href =  orderDetail_path + code;
            }else{
            	window.location.href =  orderDetail_path + code + "?&openid=" + openid;
            }
        });

    }
    //返回键
    $(".arrow").click(function(){
    	if(openid==null||openid==""||openid==undefined){
    		window.location.href =  mine_path;
    	}else{
    		window.location.href =  mineout_path+"?&openid=" + openid;
    	}
    })

});