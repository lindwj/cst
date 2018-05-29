

$(function(){
	var code="";
	var openid="";
	code = window.location.href.split("=")[1];
	openid = window.location.href.split("?&openid=")[1];
	if(code.indexOf("?")){
		code=code.split("?")[0];
	}else{
		code=code;
	}
	if(openid==null||openid==""||openid==undefined){
		
	}else{
		if(openid.indexOf("#")){
			openid=openid.split("#")[0];
		}else{
			openid=openid;
		}
	}
	
    $.ajax({
        type: 'POST',
        url: orderDetail_url+code,
        dataType : "json" ,
        data: {},
        success:
            function(data){
                getOrderList(data);
            },
        error:
            function(data,textStatus){

            }

    });






    var time = "";
    var selt = "";
    var orderDetail = "";
    var statebtn= "";
    //解析json数据，页面显示
    function getOrderList(info) {
        var order= "";
        var status="";
        if(info.status==9){
        	status="待付款";
        }else if(info.status==1){
        	status="待发货";
        }else if(info.status==2){
        	status="已签收";
        }else if(info.status==-1){
        	status="交易关闭";
        }

        time =   "<div class='order_state wl_detail' data-code='"+ info.code +"'>"
            +    "<h1 style='margin-top: 6px;'>"
            +    "<i class='iconfont stateicon'>&#xe670;</i>"
            +    "</h1>"
            +    "<p>订单状态:&nbsp;"+ status +"<br>订单编号:&nbsp;"+ info.code +"<br>下单时间:&nbsp;"+ info.createDatestr +"</p>"
            +    "<h2 style='margin-top: 6px;'>"
            +    "<i class='iconfont aicon'>&#xe627;</i>"
            +    "</h2>"
            +    "<div class='clear'></div>"
            +    "</div>"
            +    "<div class='order_state'>"
            +    "<h1>"
            +    "<i class='iconfont stateicon'>&#xe732;</i>"
            +    "</h1>"
            +    "<p>收货人:&nbsp;"+ info.receiveName +"<br>手机号码:&nbsp;"+ info.receiveMobile +"<br>详细地址:&nbsp;"+ info.receiveAddress +"</p>"
            +    "<div class='clear'></div>"
            +    "</div>";
        	if(info.productUuid!="0"){
        		order +=  "<div class='order_box' data-id='"+ info.productUuid +"'>"
                +    "<div class='img_box'>"
                +    "<img src='"+ info.pic +"'>"
                +    "</div>"
                +    "<div class='middle'>"
                +    "<p class='title escp'>"+ info.productName +"</p>"
                +    "</div>"
                +    "<div class='right'>"
                +    "<p class='price'>￥"+ info.price.toFixed(2) +"</p>"
                +    "<p class='num'>x"+ info.capacity +"</p>"
                +    "</div>"
                +    "<div class='clear'></div>"
                +    "</div>";
        		
        	}else{

            $.each(info.ordersDetailList,function(i,item){


                order +=  "<div class='order_box' data-id='"+ item.productUuid +"'>"
                    +    "<div class='img_box'>"
                    +    "<img src='"+ item.pic +"'>"
                    +    "</div>"
                    +    "<div class='middle'>"
                    +    "<p class='title escp'>"+ item.productName +"</p>"
                    +    "</div>"
                    +    "<div class='right'>"
                    +    "<p class='price'>￥"+ item.price.toFixed(2) +"</p>"
                    +    "<p class='num'>x"+ item.capacity +"</p>"
                    +    "</div>"
                    +    "<div class='clear'></div>"
                    +    "</div>";

            });
            
        	}

        selt = "<div class='selttment_box'>"
            +  "<div class='seltm1'>"
            +  "<p class='p1'>商品合计</p>"
            +  "<p class='p2'>￥"+ info.totalFromBdh.toFixed(2) +"</p>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "<div class='seltm2'>"
            +  "<p class='p1'>运费</p>"
            +  "<p class='p2'>￥"+ info.totalFee.toFixed(2) +"</p>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "<div class='seltm3'>"
            +  "<p class='p1'>买家留言</p>"
            +  "<p class='p3'>"+info.note+"</p>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "</div>";



        if(info.status == 9){

            statebtn = "<div class='btn_box'>"
                +   "<input class='payorder' data-price='"+info.totalFromBdh+"' data-code='"+info.code+"' type='button' value='付款'>"
                +   "<input type='button' data-code='"+info.code+"' class='cancel' value='取消订单'>"
                +   "<div class='clear'>"
                +   "</div>"
                +   "</div>";

        }else if(info.status == 1){

            statebtn = "<div class='btn_box'>"
                +   "<input class='payorder' type='button' data-id='"+info.code+"' value='确认收货'>"
                +   "<div class='clear'>"
                +   "</div>"
                +   "</div>";

        }

        orderDetail = time + order + selt;

        $(".order_detail").append(orderDetail);
        $(".btns").append(statebtn);


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
                        code:$(this).attr("data-id")
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
        	}
        });
        
         //删除商品
        $(".cancel").click(function(){

            var del = $(this).attr("data-code");
            $(".black_box").fadeIn(500);
            $("#delbtn").attr("data-code", del) ;

        });
        //取消删除
        $("#cancle").click(function(){
            $(".black_box").fadeOut(500);
        });
        //确认删除
        $("#delbtn").click(function(){

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
        });

        //物流详情
        $(".wl_detail").click(function(){
            var ordercode = $(this).attr("data-code");
            window.location.href = wl_path + "?orderCode=" + ordercode;
        });
        //商品链接
        $(".order_box").click(function(){
            var pid= $(this).attr("data-id");
            window.location.href = pro_url + pid;
        });

        $(".arrow").click(function(){
        	if(openid==null||openid==""||openid==undefined){
        		window.location.href = order_path;
        	}else{
        		window.location.href = order_path + "?&openid=" + openid;
        	}
        })



    }






});