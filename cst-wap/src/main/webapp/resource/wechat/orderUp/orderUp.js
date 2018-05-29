

$(function(){
	var orders="";
	var order = window.location.href.split("?")[1];
	var man="";
	var phone="";
	var addressAll="";
	var uuid="";
	if(order.indexOf("#")>0){
		orders=order.split("#")[0];
    }else{
    	orders=order;
    }
	
	//获取默认地址
	$.ajax({
        type: 'POST',
        url: firstAddress_url,
        dataType : "json" ,
        data: {},
        async:false,
        success:
            function(data){
        		getDefault(data);
            },
        error:
            function(data,textStatus){

            }
    });

	//获取下单详情信息
    $.ajax({
        type: 'POST',
        url: orderUp_url,
        dataType : "json" ,
        data: orders,
        success:
            function(data){
                orderUp(data);
            },
        error:
            function(data,textStatus){

            }
    });



    //获取默认地址数据
    function getDefault(info){
    	$(".order_state").empty();
    	var address="";
    	address = "<h1><i class='iconfont stateicon'>&#xe732;</i></h1>"
            +  "<p>收货人:&nbsp;"+info.name+"<br>手机号码:&nbsp;"+info.mobile+"<br>详细地址:&nbsp;"+info.provincestr+info.citystr+info.districtstr+testnull(info.streetstr)+info.address+"</p>"
            +  "<h2><i class='iconfont adresicon'>&#xe626;</i></h2>"
            +  "<div class='clear'></div>";
    	man=info.name;
    	phone=info.mobile;
    	uuid=info.goodsAddressUuid;
    	addressAll=info.provincestr+info.citystr+info.districtstr+testnull(info.streetstr)+info.address;
        $(".order_state").append(address);
    	$(".order_state").click(function(){
    		window.location.href=addressList_path+"?"+orders+"?1";
        })
    }


    //获取订单详情部分
    function orderUp(info) {
        var order= "";
        var selt = "";
        var orderDetail = "";
        var total=""
        var detailList="";
        $.each(info.ordersDetailList,function(i,item){
        	detailList=detailList+"&ordersDetailList["+i+"].productUuid="+item.productUuid+"&ordersDetailList["+i+"].pic="+item.pic+"&ordersDetailList["+i+"].productName="+item.productName+"&ordersDetailList["+i+"].price="+item.price+"&ordersDetailList["+i+"].capacity="+item.capacity;
        	
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

        selt = "<div class='selttment_box'>"
        	+  "<div class='seltm1'>"
            +  "<p class='p1'>配送商家</p>"
            +  "<p class='p2'>"+info.shopName+"</p>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "<div class='seltm1'>"
            +  "<p class='p1'>商品合计</p>"
            +  "<p class='p2'>￥"+ info.totalFromBdh.toFixed(2) +"</p>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "<div class='seltm2'>"
            +  "<p class='p1'>运费</p>"
            +  "<p class='p2'>￥0.00</p>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "<div class='seltm3'>"
            +  "<p class='p1'>买家留言</p>"
            +  "<input class='p3' type='text' placeholder='点击此处，开始填写'>"
            +  "<div class='clear'></div>"
            +  "</div>"
            +  "</div>";
        orderDetail = order + selt;
        
        total="<a class='buy' href='javascript:;'>提交订单</a>"
        	+	"<p class='selt_price'>￥<span>"+parseFloat(info.totalFromBdh)+"</span></p>"
        	+	"<p class='selt'>合计:</p>";
        
        $(".footer").append(total);
        $(".order_body").append(orderDetail);
        
        $(".buy").click(function(){
        	var note="";
        	note=$(".p3").val();
        	detailList=detailList+"&note="+note+"&totalFromBdh="+info.totalFromBdh+"&shopId="+info.shopId+"&shopName="+info.shopName+"&receiveName="+man+"&goodsAddressUuid="+uuid+"&receiveAddress="+addressAll+"&receiveMobile="+phone;
        	$.ajax({
        		type: 'POST',
                url:saveOrder_url,
                dataType : "json" ,
                data: detailList,
                success:
                    function(data){
                		if(data.code==null){
                			error("库存不足");
                			return false;
                		}else{
                			window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/pay.html&response_type=code&scope=snsapi_base&state="+data.code+","+data.totalFromBdh+"#wechat_redirect";
                		}
                    },
                error:
                    function(data,textStatus){

                    }
        	})
        })
        
    }
    
    
  //验证非空
	function testnull(obj){
		if(obj!=null){
			obj=obj;
		}else{
			obj="";
		}
		return obj;
	}
	
    $(".arrow").click(function(){
    	window.location.href=shopping_path;
    })
});