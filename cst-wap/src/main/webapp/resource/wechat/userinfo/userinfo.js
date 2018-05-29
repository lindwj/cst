$(function () {
	
	/*//公告
    $(".black_box1").show().delay(3000).fadeOut();*/
	
	var init= window.location.href.split("?")[1];
	var eventid=init.split("=")[1];
	
	var reg=/#/g;

	eventid=eventid.replace(reg,'');
	
	$("#sy").attr("href",index_path+eventid) ;
	$("#jp").attr("href",jiangpin_path+eventid) ;
	$("#bd").attr("href",bangdan_path+eventid) ;
	
	
	
	
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
    	

    	var parameter="<li class=''><a href='"+index_path+"'></a></li>";
        var producttitle = "";
        var product = "";
        var productmore = "";
        var allproduct = "";
        parameter += "<li class='classify' data-id='"+"'><a href='#'>"+""+"</a></li>";
        product = "";
        producttitle =  "<div class='product'>"
            +           "<ul class='list'>";
        $.each(info, function (i, item) {
        	
        	//分页
//        	if(i==0){
//        		$("#ckgd").attr("page", item.page.currentPage) ;
//        		$("#ckgd").attr("allPage", item.page.totalPage) ;
//        	}
            	
        	var hrefs='';
        	
//                            if( 0 <= n && n <= 4 ){
                                product += "<li class='item'>"
                                    +     "<a class='prodetail' href='"+hrefs+"' data-id='" + item.id + "'>"
                                    +     "<div class='propic'>"
                                    +     "<img src='" + item.headurl + "'>"
                                    +     "</div>"
                                    +     "<h1 class='protitle'>" + item.name+item.txt+ "</h1>"
                                    +     "<p class='proprice'>" + item.price + "</p>"
                                    +     "</a>"
                                    +     "</li>";

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

                    product = producttitle + product + productmore +  "</ul>" + "</div>" + "<div class='clear'>" + "</div>";
                    allproduct +=  product ;
        
        
        
        
//        $(".parameter").append(parameter);
        $(".product_box").append(allproduct);
    

    $(".index_header").find("img").click(function(){
    	window.location.href =  index_path;
    });
    
        
//    	$(".prodetail").click(function(){
//            var pid= $(this).attr("data-id");
//            window.location.href =  pro_url + pid;
//        });
        
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
    
    
});