$(function () {
	
	/*//公告
    $(".black_box1").show().delay(3000).fadeOut();*/
	
	var init= window.location.href.split("?")[1];
	var eventid=init.split("=")[1].split("&")[0];
	
	var reg=/#/g;

	eventid=eventid.replace(reg,'');
	var title=null;
	var evepic=null;
	
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
        	title=data.headtxt;
        	evepic=data.banner;
            },
        error:
            function(data,textStatus){
            }
    });
    
    
  //分享
    $.ajax({  
        type : "post",  
        dataType : "json",  
        url : setShare_url,  
        data : {  
            url : window.location.href
        },  
        async:false, 
        success : function(msg) {  
        	
            //var data = msg['success'];  
            //if (data) {  
//        	alert(msg.appId);
//        	alert(msg.timestamp);
//        	alert(msg.nonceStr);
//        	alert(msg.signature);
                wx.config({  
                    debug : false, //  
                    appId : msg.appId, // 必填，公众号的唯一标识  
                    timestamp : msg.timestamp, // 必填，生成签名的时间戳  
                    nonceStr : msg.nonceStr, // 必填，生成签名的随机串  
                    signature : msg.signature,// 必填，签名，见附录1  
                    jsApiList : [ 'onMenuShareTimeline',  
                            'onMenuShareAppMessage', 'showOptionMenu' ]  
                // 必填，需要使用的JS接口列表，所有JS接口列表见附录2  
                });  
           // }  
        } 
    });
	
	
	//jingpin list
    $.ajax({
        type: 'POST',
        url: getStarList_url,
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
            	
        	
//                            if( 0 <= n && n <= 4 ){
        	
                                product += "<dd class='dtl' data-id='"+item.id+"'>"
            +"<div class='tab_img top_three'><i class='iconfont icon-top'></i><img src='"+item.headurl+"' alt=''></div>"
            +"<div class='tab_text'>"
                +"<h3>"+item.name+" "+item.code+"号</h3>"
                +"<p>票数"+item.piaoshu+"</p>"
                +"</div>"
                +"<span class='ranking rankong_red'>"+(i+1)+"</span>"
                +"</dd>";

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

                    //product = producttitle + product + productmore +  "</ul>" + "</div>" + "<div class='clear'>" + "</div>";
                    allproduct +=  product ;
        
        
        
        
//        $(".parameter").append(parameter);
        $(".product_box").append(allproduct);
    

    $(".index_header").find("img").click(function(){
    	window.location.href =  index_path;
    });
    
        
    	$(".dtl").click(function(){
            var pid= $(this).attr("data-id");
            window.location.href =  star_path + pid;
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
    
    
    var shareTitle = title;
    var shareDesc = shareTitle;  
    var shareImg = base+evepic ;  
    var url = window.location.href;  
//    alert(shareTitle);
//    alert(shareDesc);
//    alert(shareImg);
//    alert(url);
    wx.ready(function() {  
//    	alert("1");
    	share();  
    });  
    function share() {
//    	alert("2");
//    	alert(shareTitle);
//        alert(shareDesc);
//        alert(shareImg);
//        alert(url);
        wx.showOptionMenu();  
        wx.onMenuShareTimeline({  
            title : shareTitle, // 分享标题  
            link : url, // 分享链接  
            imgUrl : shareImg,  
            desc : shareDesc, // 分享描述  
            success : function() {  
            }  
        });  
        wx.onMenuShareAppMessage({  
            title : shareTitle, // 分享标题  
            link : url, // 分享链接  
            imgUrl : shareImg,  
            desc : shareDesc, // 分享描述  
            success : function() {  
            }  
        });  
    }
    
    wx.error(function (res) {
    	});
    
    
});