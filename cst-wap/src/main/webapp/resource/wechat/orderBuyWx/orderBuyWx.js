

$(function(){
	var initvalue=""
	var openCode="";
	var orders="";
	var state="";
	var puuid="";
	var openid="";
	var prouuid="";
	var num=0;
	if(window.location.href.indexOf("?code=")>0){
		initvalue=window.location.href.split("?")[1];
		openCode=initvalue.split("&state=")[0];
		state=initvalue.split("&state=")[1];
		puuid=state.split("%2C")[0];
		num=state.split("%2C")[1];
		orders="productUuid="+puuid+"&capacity="+num;
		$.ajax({
			type: 'POST',
	        url: getOpenid_url,
	        dataType : "json" ,
	        data: openCode,
	        async:false,
	        success:
	            function(data){
	        		if(data==null||data==""||data==undefined){
	        			window.location.href=index_path;
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
		prouuid=window.location.href.split("?productUuid=")[1];
		puuid=prouuid.split("&")[0];
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
		window.location.href=addressWx_path+"?"+orders+"?&openid="+openid;
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
        url: buyNow_url,
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
    		window.location.href=addressWx_path+"?"+orders+"?&openid="+openid;
        })
    }


    //获取订单详情部分
    function orderUp(info) {
        var order= "";
        var selt = "";
        var orderDetail = "";
        var total=""
        var detailList="";
    	detailList=detailList+"&productUuid="+info.productUuid+"&pic="+info.pic+"&subject="+info.subject+"&price="+info.price+"&capacity="+info.capacity+"&showCapacity="+info.showCapacity;
    	
        order +=  "<div class='order_box' data-id='"+ info.productUuid +"'>"
            +    "<div class='img_box'>"
            +    "<img src='"+ info.pic +"'>"
            +    "</div>"
            +    "<div class='middle'>"
            +    "<p class='title escp'>"+ info.subject +"</p>"
            +    "</div>"
            +    "<div class='right'>"
            +    "<p class='price'>￥"+ info.price.toFixed(2) +"</p>"
            +    "<p class='num'>x"+ info.capacity +"</p>"
            +    "</div>"
            +    "<div class='clear'></div>"
            +    "</div>";


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
        
        total="<a class='buy' href='#'>提交订单</a>"
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
	                url:saveNowOpen_url,
	                dataType : "json" ,
	                data: detailList,
	                success:
	                    function(data){
	                		if(data.code==null){
	                			error("库存不足");
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
    	window.location.href=pro_url+puuid;
    })
});