/**
 * Created by zhang on 6/14/16.
 */
$(function(){
	
	
	$("#link-buy").on( "click", function() {
	    $("#addr-frame").removeClass("display-none");
	    $("#frame-mask").removeClass("display-none");
	});

	//点击关闭按钮隐藏frame和面具
	$("#dialog-close").on( "click", function() {
	    $("#addr-frame").addClass("display-none")
	    $("#frame-mask").addClass("display-none");
	});

    //获取购物车商品数量，登陆状态下使用ajax数据库获取，非登陆状态从cookie中获取。

    //绑定增加减少按钮
    $("#amount-btn-increase").click(function() {
        var num = $("#amount-input");
        num.val( num.val() - 0 + 1);
    });
    $("#amount-btn-decrease").click(function() {
        var num = $("#amount-input");
        if( num.val()  > 1){
            num.val( num.val() - 1);
        }
    });

    ////增加商品小图片，绑定点击按钮
    //$("#thumb").append("<li><a><img class=\"th-selected\" src=\"images/thumb-img1.jpg\"></a></li>");
    //$("#thumb").append("<li><a><img src=\"images/thumb-img2.jpg\"></a></li>");
    //$("#thumb").append("<li><a><img src=\"images/thumb-img3.jpg\"></a></li>");
    //$("#thumb").append("<li><a><img src=\"images/thumb-img4.jpg\"></a></li>");
    //$("#thumb").append("<li><a><img src=\"images/thumb-img5.jpg\"></a></li>");

    //鼠标焦点放到下发的小图片上，大相框显示对应的图片，并修改小相框样式
    $("#thumb li img").on( "mouseover", function() {
        var $imgSrc =  $(this).attr("src") ;
        $("#img-booth").attr("src",$imgSrc);
        //修改小相框样式。先删除img样式，然后增加选择小相框样式
        $("#thumb img").removeClass("th-selected");
        $(this).addClass("th-selected");
    });


    ////最右侧商品推荐
    ////使用ajax获取：图片地址、价格、商标名称
    ////循环之前初始化变量
    //var imgTemp = "images/ald1.jpg";
    //var priceTemp = 39.8;
    //var titleTemp = "新米 福临门金典东北大米袋装5kg";
    //var rightProduct = "";
    //rightProduct = rightProduct + "<li><div class=\"wrap-con-img\"><a class=\"ALDCLS-act\">"
    //                + "<img src=\"" + imgTemp + "\"></a>"
    //                + "<p class=\"look_price\">¥" + priceTemp + "</p></div>"
    //                + "<p class=\"look_title\"><a class=\"ALDCLS-act\">" + titleTemp + "</a></p></li>";
    //$("#ald-switchable-content").append(rightProduct);
    //$("#ald-switchable-content").append(rightProduct);
    //$("#ald-switchable-content").append(rightProduct);
    //
    //
    ////左侧商品推荐
    ////使用ajax获取图片地址和链接
    //$("#left-banner").append("<div><a><img src=\"images/sub1.jpg\"></a></div>");
    //$("#left-banner").append("<div><a><img src=\"images/sub1.jpg\"></a></div>");
    //$("#left-banner").append("<div><a><img src=\"images/sub1.jpg\"></a></div>");


    

})



//购物车飞入效果
    
    function mv() {
        
        
      //购物车飞入效果
        var start = $("#link-basket");
        var target = $("#shopCart").offset();
        
        
            var flyer = $('<img class="flyer-img"  height="40" width="40" src="' + $("#img-booth").attr('src') + '">'); //抛物体对象
            //var winScrollTop = $(window).scrollTop();
            var winScrollTop = $(window).scrollTop();
            var shopScrollTop = $("#shopCart").scrollTop();
            flyer.fly({
                start: {
                    left: start.offset().left + 79,//起点横坐标
                    top: start.offset().top - 19 - winScrollTop //起点纵坐标
                },
                end: {
                    left: target.left + 20,//终点横坐标
                    //top: target.top + winScrollTop, //终点纵坐标
                    top: target.top - shopScrollTop, //终点纵坐标
                    width: 20,
                    height: 20
                },
                onEnd: function() {
                    $(".flyer-img").remove(); //销毁抛物体
                }
            });

            //修改购物车数量,延迟半秒钟修改
                $("#cart-num").text( ($("#cart-num").text() - 0 ) + ($("#amount-input").val() - 0));


    }