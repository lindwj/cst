$(function () {
	//首页
    $.ajax({
        type: 'POST',
        url: product_url,
        dataType : "json" ,
        data: {},
        async:false,
        success:
            function(data){
        		//单品分类
                getOrderList(data.pList);
            },
        error:
            function(data,textStatus){
            }
    });
    //添加滑块
    $('.listmenu ul').append('<div class="block"></div>');
    var $block = $('.block');
    $block.width($(".current").width()).css('left', $('.current a').position().left).data('rightLeft', $block.position().left).data('rightWidth', $block.width());
    var length=$(".listmenu").find("li").length;
    //滑块切换
    $(".listmenu").find("li").click(function(){
   		$(".listmenu").find("li").removeClass("current");
    	$(this).addClass("current");
    	$block.width($(".current").width()).css('left', $('.current a').position().left).data('rightLeft', $block.position().left).data('rightWidth', $block.width());
   	});
    //解析json数据，页面显示
    function getOrderList(info) {
    	var parameter="<li><a href='"+index_path+"'>首页</a></li>";
        var producttitle = "";
        var product = "";
        var productmore = "";
        var allproduct = "";
        var picture = "";
        $.each(info, function (i, item) {
        	var detail=window.location.href.split("=")[1];
        	if(detail.indexOf("#")>0){
        		detail=detail.split("#")[0];
        	}else{
        		detail=detail;
        	}
        	if(i == 0){
            	parameter += "";
                producttitle = "";
                productmore = "";
            }else{
            	if(i==detail){
            		parameter += "<li class='classify current' data-id='"+i+"'><a href='#'>"+item.parameterName+"</a></li>";
                    product = "";
            	}else{
            		parameter += "<li class='classify' data-id='"+i+"'><a href='#'>"+item.parameterName+"</a></li>";
                    product = "";
            	}
            }
            if(i==detail){
            	//picture = "<img src='"+item.picture+"'>";
            	producttitle =  "<div class='product'>"
                    +           "<h1 class='title'>" + item.parameterName + "</h1>"
                    +           "<p class='desctitle'>" + item.description + "</h1>"
                    +           "<ul class='list'>";
            	 if (item.productList.length > 0) {
                     $.each(item.productList, function (n, ntem) {

                         var price = ntem.price;

                         if( price == 100000){
                             ntem.price = "即将上线";
                             product += "<li class='item'>"
                                 +     "<a class='prodetail' href='#' data-id='" + ntem.productUuid + "'>"
                                 +     "<div class='propic'>"
                                 +     "<img src='" + ntem.pic + "'>"
                                 +     "</div>"
                                 +     "<h1 class='protitle'>" + ntem.name + "</h1>"
                                 +     "<p class='proprice'>" + ntem.price + "</p>"
                                 +     "</a>"
                                 +     "</li>";

                         }else{
                             ntem.price = ntem.price;
                             product += "<li class='item'>"
                                 +     "<a class='prodetail' href='#' data-id='" + ntem.productUuid + "'>"
                                 +     "<div class='propic'>"
                                 +     "<img src='" + ntem.pic + "'>"
                                 +     "</div>"
                                 +     "<h1 class='protitle'>" + ntem.name + "</h1>"
                                 +     "<p class='proprice'>￥" + ntem.price.toFixed(2) + "</p>"
                                 +     "</a>"
                                 +     "</li>";

                         }
                    });

                    product = producttitle + product +  "</ul>" + "</div>" + "<div class='clear'>" + "</div>";
                    allproduct +=  product ;
                }
            }
        });
        $(".probanner").append(picture);
        $(".parameter").append(parameter);
        $(".product_box").append(allproduct);
        $(".index_header").find("img").click(function(){
        	window.location.href =  index_path;
        })
        $(".headericon").click(function(){
        	window.location.href = shopping_path;
        })
        $(".prodetail").click(function(){
            var pid= $(this).attr("data-id");
            window.location.href =  pro_url + pid;
        });
        $(".classify").click(function(){
            var code = parseInt($(this).attr("data-id"));
            window.location.href = class_path + '?code=' + code;
        })
    }
});