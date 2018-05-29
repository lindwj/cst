/**
 * Created by zhang on 6/13/16.
 */
$(function(){

    //ajax获取登陆信息，获取用户信息和购物车数量信息，并在页面显示
//    var username = "188005681181";
//    //如果登陆状态，修改“登陆”和“注册”为登陆名和“退出”按钮
//    if(username == "18800568118"){
//        $("#username").text(username);
//        $("#logout").html("<a href=\"../register/register.html\">退出</a>");
//    };


    ////加载slider商品信息
    //$(".unslider ul").append(" <li><a href=\"../productdetal/ProductDetail.html\"><img src=\"images/bing-1.jpg\"></a></li>");

    //加载slider样式
    if(window.chrome) {
        $('.unslider li').css('background-size', '100% 100%');
    };
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


    ////界面加载商品信息
    //var itemID = "001";
    //var itemLink = "../ProductDetal/ProductDetail.html";
    //var itemImg = "images/1.jpg";
    //var itemName = "蒲江红阳猕猴桃";
    //var itemPrice = "59.00";
    //var itemLI = "<li id=\"" + itemID + "\">"
    //        +       "<div>"
    //        +           "<a href=\"" + itemLink + "\"><img src=\"" + itemImg +  "\"></a>"
    //        +           "<p class=\"orange\">" + itemName + "</p>"
    //        +           "<p><span class=\"orange_color\">￥" + itemPrice + "</span></p>"
    //        +           "<p class=\"bar_p\" >"
    //        +               "<a><img src=\"images/add_shop.png\" class=\"add_shop\"  id=\"img_" + itemID +  "\"></a>"
    //        +           "</p>"
    //        +       "</div>"
    //        +       "</li>"
    //
    //$("#pre-item").append(itemLI);


    
  //鼠标放到图片上，显示加入购物车bar
    $(".item-pic").hover(
        function () {
            $(this).find('p:last').css('display','block') ;
        },
        function () {
            $(this).find('p:last').css('display','none') ;
        }
    );
    
})

//购物车飞入效果
    function mv(obj) {
        var start = $(obj);
        var target = $("#shopCart").offset();

        var flyer = $('<img class=\"flyer-img\" src=\"../resource/images/index/add_shop.png\" >'); //获取移动对象
        var winScrollTop = $(window).scrollTop();
        flyer.fly({
            start: {
                left: start.offset().left ,//起点横坐标
                top: start.offset().top - winScrollTop //起点纵坐标
            },
            end: {
                left: target.left ,//终点横坐标
                top: target.top - winScrollTop, //终点纵坐标
                width: 20,
                height: 20
            },
            onEnd: function() {
                $(".flyer-img").remove(); //销毁抛物体
            }
        });

        //修改购物车数量,延迟半秒钟修改
        setTimeout(function(){
            $("#cart-num").text( $("#cart-num").text() - 0  + 1 );
        },200);

    }


