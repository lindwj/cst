$(function () {
	
	/*//公告
    $(".black_box1").show().delay(3000).fadeOut();*/
	
	var init= window.location.href.split("?")[1];
	var reg=/#/g;

	init=init.replace(reg,'');
	
	var eventid=init.split("=")[1].split("&")[0].split(",")[0];
	var starid=init.split("=")[1].split("&")[0].split(",")[1];
	
	
	
	$("#sy").attr("href",index_path+eventid) ;
	$("#jp").attr("href",jiangpin_path+eventid) ;
	$("#bd").attr("href",bangdan_path+eventid) ;
	
	
	//event 信息
    $.ajax({
        type: 'POST',
        url: event_url,
        dataType : "json" ,
        data: {id:eventid},
        async:false,
        success:
            function(data){
        	setBgColor(data);
            },
        error:
            function(data,textStatus){
            }
    });
	
	//jingpin list
    $.ajax({
        type: 'POST',
        url: liwuListPage_url,
        dataType : "json" ,
        data: {eventid:eventid},
        async:false,
        success:
            function(data){
                //显示订单管理第一页数据
                getOrderList(data);
                
            },
        error:
            function(data,textStatus){
            }
    });
    
  
    
    //滑块切换
    $(".listmenu").find("li").click(function(){
   		$(".listmenu").find("li").removeClass("current");
    	$(this).addClass("current");
    	$block.width($(".current").width()).css('left', $('.current a').position().left).data('rightLeft', $block.position().left).data('rightWidth', $block.width());
   	});
    
  
    
  
    
    
    
    //解析json数据，页面显示
    function getOrderList(info) {
//    	if(flag==0){
//    		$(".product_box").empty();
//    	}
    	

        var product = "";
        var allproduct = "";
        $.each(info, function (i, item) {
        	
        	//分页
//        	if(i==0){
//        		$("#ckgd").attr("page", item.page.currentPage) ;
//        		$("#ckgd").attr("allPage", item.page.totalPage) ;
//        	}
            	
        	var hrefs='';
        	
//                            if( 0 <= n && n <= 4 ){
        	
        	
        	
                                product += "<dl class='prodetail' name='"+item.name+"' price='"+item.price+"' data-id='"+item.id+"'>"
                                	+"<dt>"
                                	+"<img style='height:65px;width:80px;' src="+item.headurl+" alt=''>"
                                	+"<h5>"+item.txt+"</h5>"
                                	+"</dt>"
                                	+"<dd>"
                                	+"<p>"+"</p>"
                                	+"<a href=''>"+item.price*3+"票</a>"
                                	+"</dd>"
                                	+"</dl>";

//                            }
//                        }
                    });

//                    productmore = "<li class='item'>"
//                        +         "<a class='itemnore' href='#' data-id='" + "'>"
//                        +         "<h1 class='more'>更多" + "" + "</h1>"
//                        +         "<p class='morearrow'>"
//                        +         "<i class='iconfont moreicon'>&#xe65d;</i>"
//                        +         "</p>"
//                        +         "</a>"
//                        +         "</li>";

        
                    allproduct +=  product +"<p>一元抵三票！</p><a class='gif_pay' id='pay' key='' name='' price='' >支付</a>";
        
        
        
        
//        $(".parameter").append(parameter);
        $(".pro_infor").append(allproduct);
    

//    $(".index_header").find("img").click(function(){
//    	window.location.href =  index_path;
//    });
    
        //点击选中礼物
    	$(".prodetail").click(function(){
    		$("#pay").attr("key",$(this).attr("data-id"));
    		$("#pay").attr("name",$(this).attr("name"));
    		$("#pay").attr("price",$(this).attr("price"));
            //alert("请点击支付");
        });
        
      //查看更多
        
//        $("#ckgd").click(function(){
//        	var x = parseInt($("#ckgd").attr("page"))+1;
//        	var y =parseInt($("#ckgd").attr("allPage"));
//        	if(x > y){
//        		return false;
//        	}
//        	//star
//            $.ajax({
//                type: 'POST',
//                url: star_url,
//                dataType : "json" ,
//                data: {eventid:eventid,'page.currentPage':x,
//                	'page.totalPage':y},
//                async:false,
//                success:
//                    function(data){
//                        //显示订单管理第一页数据
//                        getOrderList(data,1);
//                        
//                    },
//                error:
//                    function(data,textStatus){
//                    }
//            });
//        });
        
        
        //添加滑块
//        $('.parameter').append('<div class="block"></div>');
//        var $block = $('.block');
//        $block.width($(".current").width()).css('left', $('.current a').position().left).data('rightLeft', $block.position().left).data('rightWidth', $block.width());
//        var length=$(".listmenu").find("li").length;
    }
    
    
    
  //送礼物
	$("#pay").click(function(){
		if($("#pay").attr("key")==null  ||  $("#pay").attr("key").length<1){
			alert("请先选择礼物！");
			return false;
		}
		
		var shualiwuid=0;
		
		$.ajax({
            type: 'POST',
            url: shualiwu_url,
            dataType : "json" ,
            data:{
            	//star
            	starId:parseInt(starid),
	        	eventid:eventid,
	        	//暂存openid
	        	//openid:'4444444',
	        	//name:'张三',
	        	//headurl:'/cst-wap/resource/upload/headImg/default.jpg',
	        	liwuId:parseInt($("#pay").attr("key")),
	        	liwuName:$("#pay").attr("name"),
	        	type:2
	        	
	        },
            success:
                function(data){
            	//alert($("#pay").attr("price")+ ","+parseInt(starid));
                    if(data==null){
//                        window.location.href= addressDetail_path + "?productUuid=" + prouuid1 + "&capacity=" + num;
                    }else if(data.length>0){
                    	
//                    	$.ajax({
//                            type: 'POST',
//                            url: ajaxURL,
//                            dataType : "json" ,
//                            data: {},
//                            async: false,
//                            success:
//                                function(data){
//                            		//获取商品详情数据
//                                    getProduct(data,1);
//                                },
//                            error:
//                                function(data,textStatus){
//                            		alert("服务器异常");
//                                }
//                        });
//                    	error("成功");
                    	//alert(pay_path + "?id=" + data + "," + parseFloat($("#pay").attr("price"))+ ","+parseInt(starid));
                    	//window.location.href= pay_path + "?id=" + data + "," + parseFloat($("#pay").attr("price"))+ ","+parseInt(starid);
                    	shualiwuid=data;
                    	$.ajax({
                	        type: 'POST',
                	        url: pay_url,
                	        dataType : "json" ,
                	        data:{
                	        	orderCode:data,
                	        	totalFromBdh:parseFloat($("#pay").attr("price"))
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
                	     		           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                	     		        	   
                	     		        	   //xiugai shualiwu status
                	     		        	  $.ajax({
                	     		        	        type: 'POST',
                	     		        	        url: shualiwuup_url,
                	     		        	        dataType : "json" ,
                	     		        	        data: {id:shualiwuid,type:1,starId:parseInt(starid),liwuId:parseInt($("#pay").attr("key"))},
                	     		        	        async:false,
                	     		        	        success:
                	     		        	            function(data){
                	     		        	                
                	     		        	            },
                	     		        	        error:
                	     		        	            function(data,textStatus){
                	     		        	            }
                	     		        	    });
                	     		        	  
                	     		        	   
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
                    	
                    	
	        			return false;
//                    	window.location.href= orderBuy_url + "?productUuid=" + prouuid1 + "&capacity=" + num;
                    }else if(data==-1){
                    	//window.location.href=login_path;
//                    	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/orderBuyWx.html&response_type=code&scope=snsapi_base&state=" + prouuid1 + "," + num +"#wechat_redirect";
                    }
                },
            error:
                function(data,textStatus){
                    alert("服务器异常，请稍后再试");

                }

        });

	});
    
    
});