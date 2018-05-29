$(function () {
	
	/*//公告
    $(".black_box1").show().delay(3000).fadeOut();*/
	
	var init= window.location.href.split("?")[1];
	var eventid=init.split("=")[1].split("&")[0];
	
	var reg=/#/g;

	eventid=eventid.replace(reg,'');
	
	$("#sy").attr("href",index_path+eventid) ;
	$("#jp").attr("href",jiangpin_path+eventid) ;
	$("#bd").attr("href",bangdan_path+eventid) ;
	
	$('#name').click(function(){  
          
        if('输入编号或姓名'==$('#name').val()){
        	$('#name').val('');
        } 
    });
	
	
	//验证cookie
	var co= $.cookie('bdhcookie');
//	alert(co);
	if(co==null||co=='undefined'){
//		alert(co);
		window.location.href = index_path.replace('index','indexwysq2')+eventid;
		return false;
	}
	
//	alert(window.location.href);
	
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
	
	//tongji
    $.ajax({
        type: 'POST',
        url: starinfo_url,
        dataType : "json" ,
        data: {eventid:eventid},
        async:false,
        success:
            function(data){
                //显示订单管理第一页数据
                settongji(data);
            },
        error:
            function(data,textStatus){
            }
    });
	
	//event 信息
    $.ajax({
        type: 'POST',
        url: event_url,
        dataType : "json" ,
        data: {id:eventid},
        async:false,
        success:
            function(data){
        	getEvent(data);
        	setBgColor(data);
            },
        error:
            function(data,textStatus){
            }
    });
	
	//star
    $.ajax({
        type: 'POST',
        url: star_url,
        dataType : "json" ,
        data: {eventid:eventid},
        async:false,
        success:
            function(data){
                //显示订单管理第一页数据
                getOrderList(data,0);
                
            },
        error:
            function(data,textStatus){
            }
    });
    
  
    
  
    
    
  
    function timer(intDiff){
        window.setInterval(function(){
        var day=0,
            hour=0,
            minute=0,
            second=0;//时间默认值        
        if(intDiff > 0){
            day = Math.floor(intDiff / (60 * 60 * 24));
            hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
            minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        $('#day_show').html(day+"天");
        $('#hour_show').html('<s id="h"></s>'+hour+'时');
        $('#minute_show').html('<s></s>'+minute+'分');
        $('#second_show').html('<s></s>'+second+'秒');
        intDiff--;
        }, 1000);
    } 
    
  
    
    
    
    
  //event显示
    function getEvent(info) {
    	//$(".headertitle").text(info.headtxt) ;
    	$("#bannerurl").attr("alt", info.headtxt) ;
    	$("#bannerurl").attr("src", info.banner) ;
    	$("#des").html(info.des.split("|")[0]) ;
    	$("#desimg").html(info.des.split("|")[1]) ;
    	
    	var date3 = new Date(info.endTime).getTime() - new Date().getTime();   //时间差的毫秒数
    	
    	if(date3 <= 0){
    		alert("活动已结束");
    	}
    	
    	timer(date3/1000);
    }
    
    //解析json数据，页面显示
    function getOrderList(info,flag) {
    	if(flag==0){
    		$("#starlist").empty();
    	}
    	

    	var parameter="";
        var producttitle = "";
        var product = "";
        var productmore = "";
        var allproduct = "";
        parameter += "";
        product = "";
        producttitle =  "";
        $.each(info, function (i, item) {
        	
        	//分页
        	if(i==0){
        		$("#ckgd").attr("page", item.page.currentPage) ;
        		$("#ckgd").attr("allPage", item.page.totalPage) ;
        	}
            	
        	var hrefs='/cst-wap/resource/wechat/productDetail.html?id='+item.id;
        	
//                            if( 0 <= n && n <= 4 ){   class='prodetail'
                                product += "<div class='col-sm-6 relative'>"
                                    +     "<a  href='"+hrefs+"' data-id='" + item.id + "'>"
                                    +     "<img src='" + item.headurl + "'>"
                                    +"</a>"
                                    +     "<div class='vote'><span>"+item.code +"</span>号，<span>"+item.piaoshu+"</span>票</div>"
                                    +"<a href='"+hrefs+"' class='vote_add'>投票</a>"
                                    +"<p>"+item.name+"</p>"
                                    +"</div>"
                                    

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
        
        
        
        
   //     $(".parameter").append(parameter);
        $("#starlist").append(allproduct);
    

    $(".index_header").find("img").click(function(){
    	window.location.href =  index_path;
    });
    $(".headericon").click(function(){
    	window.location.href = shopping_path;
    });
    
        
//    	$(".prodetail").click(function(){
//            var pid= $(this).attr("data-id");
//            window.location.href =  pro_url + pid;
//        });
        $(".classify").click(function(){
            var code = parseInt($(this).attr("data-id"));
            window.location.href = class_path + '?code=' + code;
        });
        $(".itemnore").click(function(){
            var code = $(this).attr("data-id");
            window.location.href = class_path + '?code=' + code;
        });
        
      //查看更多
        
        $("#ckgd").click(function(){
        	var x = parseInt($("#ckgd").attr("page"))+1;
        	var y =parseInt($("#ckgd").attr("allPage"));
        	if(x > y){
        		return false;
        	}
        	//star
            $.ajax({
                type: 'POST',
                url: star_url,
                dataType : "json" ,
                data: {eventid:eventid,'page.currentPage':x,
                	'page.totalPage':y},
                async:false,
                success:
                    function(data){
                        //显示订单管理第一页数据
                        getOrderList(data,1);
                        
                    },
                error:
                    function(data,textStatus){
                    }
            });
        });
        
        //搜索按钮
        $("#but").click(function(){
        	var name=$("#name").val();
        	//star
            $.ajax({
                type: 'POST',
                url: star_url,
                dataType : "json" ,
                data: {eventid:eventid,name:name},
                async:false,
                success:
                    function(data){
                        //显示订单管理第一页数据
                        getOrderList(data,0);
                        
                    },
                error:
                    function(data,textStatus){
                    }
            });
        });
        
        //添加滑块
        //$('.parameter').append('<div class="block"></div>');
        //var $block = $('.block');
        //$block.width($(".current").width()).css('left', $('.current a').position().left).data('rightLeft', $block.position().left).data('rightWidth', $block.width());
        //var length=$(".listmenu").find("li").length;
    }
    
  //tongji
    function settongji(info) {
    	$("#ybm").html(info.sort) ;
    	$("#ljtp").text(info.piaoshu) ;
    	$("#fwl").text(Math.round(info.piaoshu+info.piaoshu/2)) ;
    	
    }
    
    
    var shareTitle = $("#bannerurl").attr("alt");
    var shareDesc = shareTitle;  
    var shareImg = base+$("#bannerurl").attr("src") ;  
    var url = window.location.href;  
//    alert(shareTitle);
//    alert(shareDesc);
//    alert(shareImg);
//    alert(url);
    wx.ready(function() {  
        share();  
    });  
    function share() {
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
    
//    }
});