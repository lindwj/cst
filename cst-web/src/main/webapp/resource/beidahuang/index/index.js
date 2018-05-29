// JavaScript Document
$(function(){




    ////加载slider商品信息
    //$(".unslider ul").append(" <li><a href=\"../productdetal/ProductDetail.html\"><img src=\"images/bing-1.jpg\"></a></li>");

    //加载slider样式
    if(window.chrome) {
        $('.unslider li').css('background-size', '100% 100%');
    }
    $('.unslider').unslider({
        autoplay: true,//自动循环
        speed: 500,//速度500
        delay: 3000,//等待3妙
        arrows: false,//取消“前后”按钮
        keys: true, //允许键盘左右键翻页
        fluid: true,    //支持响应式设计
        dots: true  //显示dot导航

    });
    $('a[href^="#"]').click(function() {
        //  Find the target element
        var target = $($(this).attr('href'));

        var pos = target.offset(); // fallback to scrolling to top || {left: 0, top: 0};
        if(pos) {
            $('html, body').animate({
                scrollTop: pos.top,
                scrollLeft: pos.left
            }, 1000);
        }
        //  Don't let them visit the url, we'll scroll you there
        return false;
    });


});




