

$(function(){
	var initvalue=""
	var openCode="";
	var orders="";
	var state="";
	var puuid="";
	var openid="";
	var uuidLength=0;
	if(window.location.href.indexOf("?code=")>0){
		initvalue=window.location.href.split("?")[1];
		openCode=initvalue.split("&state=")[0];
		state=initvalue.split("&state=")[1];
		uuidLength=state.split("%2C").length-1;
		for(var i=0;i<uuidLength;i++){
			if(i%2==0){
				orders=orders+"&ordersDetailList[" + (i/2) +"].productUuid="+ state.split("%2C")[i];
			}else{
				orders=orders+"&ordersDetailList[" + (i/2-0.5) +"].capacity="+ state.split("%2C")[i];
			}
		}
		$.ajax({
			type: 'POST',
	        url: getOpenid_url,
	        dataType : "json" ,
	        data: openCode,
	        async:false,
	        success:
	            function(data){
	        		if(data==null||data==""||data==undefined){
	        			error("没有获取openid");
	        		}else{
		        		openid=data;
		        	}
	            },
	        error:
	            function(data,textStatus){

	            }
		})
	}else{
		orders=window.location.href.split("?")[1];
		openid=window.location.href.split("?&openid=")[1];
		if(openid.indexOf("#")>0){
			openid=openid.split("#")[0];
	    }else{
	    	openid=openid;
	    }
	}
	
	
	var man="";
	var phone="";
	var addressAll="";
	var uuid="";
	
	var isBuy=1;
	
	$(".order_state1").hide();
	
	$(".order_state1").click(function(){
		window.location.href=addressWx_path+"?"+orders+"?&openid="+openid+"?1";
	})
	
	//获取默认地址
	$.ajax({
        type: 'POST',
        url: firstAddressOpen_url,
        dataType : "json" ,
        data: {
        	openid:openid
        },
        async:false,
        success:
            function(data){
	        	if(data.goodsAddressUuid==null||data.goodsAddressUuid==""||data.goodsAddressUuid==undefined){
	        		isBuy=0;
	        		$(".order_state1").show();
	        		$(".order_state").hide();
	        	}else{
	        		isBuy=1;
	        		getDefault(data);
	        	}
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
        data: orders+"&openid="+openid,
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
    		window.location.href=addressWx_path+"?"+orders+"?&openid="+openid+"?1";
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
        	detailList=detailList+"&note="+note+"&openid="+openid+"&totalFromBdh="+info.totalFromBdh+"&shopId="+info.shopId+"&shopName="+info.shopName+"&receiveName="+man+"&goodsAddressUuid="+uuid+"&receiveAddress="+addressAll+"&receiveMobile="+phone;
        	if(isBuy==1){
	        	$.ajax({
	        		type: 'POST',
	                url:saveOrderWx_url,
	                dataType : "json" ,
	                data: detailList,
	                success:
	                    function(data){
	                		console.log(data);
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
        	}else{
        		error("请添加地址");
        	}
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