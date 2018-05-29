
$(function () {



    //获取登录后进入的订单管理的链接
    var ajaxURL = shop_url;

    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {},

        success:
            function(data){

                getOrderList(data.products);


            },
        error:
            function(data,textStatus){

            }

    });



    //解析json数据，页面显示
    function getOrderList(info) {


        var product = "";
        var proleft = "";
        var promiddle = "";
        var proright = "";
        var calcul = "";

        $.each(info, function (i, item) {

            proleft = "<div class='product1_left'>"
                //+     "<input id='checkbox"+i +"' type='checkbox' class='chk first' name='option'>"
                //+     "<label for='checkbox"+i +"'>"
                //+     "</label>"
                +     "<p class='defaulticon first'><i class='iconfont deficon'>&#xe662;</i></p>"
                +     "<span class='pic_box' data-id='"+ item.productUuid +"'>"
                +     "<img src='"+ item.pic +"'>"
                +     "</span>"
                +     "<div class='clear'>"
                +     "</div>"
                +     "</div>";

            promiddle = "<div class='product1_middle'>"
                +       "<h1 class='title escp' data-id='"+ item.productUuid +"'>"+ item.name +"</h1>"
                +       "<div class='num_box'>"
                +       "<p class='numminus' style='border-right: 0;'>"
                +       "<i class='iconfont numicon1'>&#xe650;</i>"
                +       "</p>"
                +       "<input class='num numchange' name='hayden' type='number' value='"+ item.capacity +"' data-capacity='"+ item.showCapacity +"' data-price='"+item.price+"'>"
                +       "<p class='numplus' style='border-left: 0;' data-capacity='"+ item.showCapacity +"'>"
                +       "<i class='iconfont numicon2'>&#xe653;</i>"
                +       "</p>"
                +       "</div>"
                +       "</div>";

            proright = "<div class='product1_right'>"
                +      "<p class='price'>￥"+ item.price +"</p>"
                +      "<p class='delete' data-del='"+ item.productUuid +"'>"
                +      "<i class='iconfont deleteicon'>&#xe654;</i>"
                +      "</p>"
                +      "</div>";

            product += "<div class='product1'>" + proleft + promiddle + proright + "<div class='clear'>" + "</div>" + "</div>";
        });
        $(".allpro").append(product);


        calcul = "<p id='checkall' class='defaulticon'><i class='iconfont deficon'>&#xe662;</i></p>"
            +    "<label class='all'>全选</label>"
            +    "<a class='pay' href='javascript:;'>结算(<span class='allnum'>0</span>)</a>"
            +    "<span class='word'>"
            +    "<P id='total'>合计:￥<span id='price'>0</span>"
            +    "</P>"
            +    "<p id='freight'>不含运费</p>"
            +    "</span>"
            +    "<div class='clear'>"
            +    "</div>";

        $(".settlement").append(calcul);


        //正则校验输入框里不允许有中文、英文、字符并且最大数字为100
        $("input[name='hayden']").keyup(function(){
            $(this).val($(this).val().replace(/\D|^0/g,''));
            var capacity = $(this).attr('data-capacity');
            if($(this).val() > 100){
                $(this).val(100);
            }else if($(this).val() > capacity){
                error("数量已达库存上限");
                return false;
            }
        }).bind("paste",function(){
            $(this).val($(this).val().replace(/\D|^0/g,''));

        }).css("ime-mode", "disabled");


        //数量加减
        $(".numminus").click(function(){
            var priceAll=0;
            var num1 = parseInt($(this).next().val());

            if(num1<=1){
                error("本商品1件起售");
                return false;
            }else{
                num1 --;
                for(var i=0;i<$(".first").length;i++){
                    //if($(".first").eq(i).attr("checked") == "checked"){
                    if($(".first").eq(i).children("i").css("display")=="block"){
                        priceAll+=parseFloat($(".num").eq(i).val())*$(".num").eq(i).attr("data-price");
                    }else{
                    }
                }
                if($(this).parents(".product1").find(".first").children("i").css("display")=="block"){
                    priceAll=priceAll-parseFloat($(this).parent(".num_box").find(".num").attr("data-price"));
                }
                $("#price").text(priceAll);
                $(this).next().val(num1);
            }

        });
        $(".numplus").click(function(){
            var priceAll=0;
            var num1 = parseInt($(this).prev().val());
            var capacity = $(this).attr('data-capacity');

            if(num1 >= capacity){
                error("数量已达库存上限");
                return false;
            }else if( num1 == "100"){
                error("单品已达上限");
                return false;
            }
            else{
                num1 ++;
                for(var i=0;i<$(".first").length;i++){
                    //if($(".first").eq(i).attr("checked") == "checked"){
                    if($(".first").eq(i).children("i").css("display")=="block"){
                        priceAll+=parseFloat($(".num").eq(i).val())*$(".num").eq(i).attr("data-price");
                    }else{
                    }
                }
                if($(this).parents(".product1").find(".first").children("i").css("display")=="block") {
                    priceAll = priceAll + parseFloat($(this).parent(".num_box").find(".num").attr("data-price"));
                }
                $("#price").text(priceAll);
                $(this).prev().val(num1);
            }
        });

        //删除商品
        $(".delete").click(function(){

            var del = $(this).attr("data-del");
            $(".black_box").fadeIn(500);
            $(".delete").attr("data-del", del) ;

        });
        //取消删除
        $("#cancle").click(function(){
            $(".black_box").fadeOut(500);
        });
        //确认删除
        $("#delbtn").click(function(){

            var del = $(".delete").attr("data-del");
            $.ajax({
                type: 'POST',
                url: deleteShop_url,
                dataType : "json" ,
                data: {
                    productUuid:del
                },

                success:
                    function(data){


                        window.location.reload();



                    },
                error:
                    function(data){

                    }

            });
        });

        //勾选计算数量以及总价
        $(".first").click(function() {
            var priceAll=0;
            var numAll=0;
            //全选状态下，一个不勾选，全选框也跟着不勾选
            if($(this).children("i").css("display")=="none"){

                $(this).addClass("checked");
                $(this).children("i").css("display","block");

                var a=1;
                for(var i=0;i<$(".first").length;i++){

                    if($(".first").eq(i).children("i").css("display")=="block"){
                        priceAll+=$(".num").eq(i).val()*$(".num").eq(i).attr("data-price");
                        numAll += $(".first").eq(i).length;
                    }else{
                        if(a==1){
                            a=0;
                        }
                    }
                }
                $("#price").text(priceAll);
                $(".allnum").text(numAll);



                if(a==0){
                    $("#checkall").removeClass("checked");
                    $("#checkall").children("i").css("display","none");
                }else{
                    $("#checkall").addClass("checked");
                    $("#checkall").children("i").css("display","block");
                }
            }else{

                $(this).removeClass("checked");
                $(this).children("i").css("display","none");

                for(var i=0;i<$(".first").length;i++){
                    if($(".first").eq(i).children("i").css("display")=="block"){
                        priceAll+=$(".num").eq(i).val()*$(".num").eq(i).attr("data-price");
                        numAll += $(".first").eq(i).length;
                    }else{
                    }
                }
                $("#price").text(priceAll);
                $(".allnum").text(numAll);

                $("#checkall").removeClass("checked");
                $("#checkall").children("i").css("display","none");
            }

        });

        //全选
        $("#checkall").click(function(){
            var priceAll=0;
            var numAll=0;
            if($(this).children("i").css("display")=="none"){
                $(this).addClass("checked");
                $(this).children("i").css("display","block");
                $(".first").addClass("checked");
                $(".first").children("i").css("display","block");
                var length=$(".first").length;
                for(var i=0;i<length;i++){
                    priceAll+=$(".num").eq(i).val()*$(".num").eq(i).attr("data-price");
                    numAll += $(".first").eq(i).length;
                }
                $("#price").text(priceAll);
                $(".allnum").text(numAll);
            }else{
                $(this).removeClass("checked");
                $(this).children("i").css("display","none");
                $(".first").removeClass("checked");
                $(".first").children("i").css("display","none");

                $("#price").text(priceAll);
                $(".allnum").text(numAll);
            }



        });

        //输入框修改数量
        $(".numchange").blur(function(){
            var priceAll=0;
            for(var i=0;i<$(".first").length;i++){
                //if($(".first").eq(i).attr("checked") == "checked"){
                if($(".first").eq(i).children("i").css("display")=="block"){
                    priceAll+=$(".num").eq(i).val()*$(".num").eq(i).attr("data-price");
                }else{
                }
            }
            $("#price").text(priceAll);
        });



        //结算
        $(".pay").click(function(){

        	var i=0;
            var ajaxData = "";
            var ajaxDataWx = "";
            for(var x=0;x<$(".first").length;x++){

                if($(".first").eq(x).children("i").css("display")=="block"){

                    var number = $(".num").eq(x).val();
                    var uuid = $(".title").eq(x).attr("data-id");

                    if(number == ""){
                        error("请输入购买数量");
                        return false;
                    }else{
                        ajaxData = ajaxData + "&ordersDetailList[" + i +"].productUuid="+ uuid + "&ordersDetailList[" + i + "].capacity="+ number +"";
                        ajaxDataWx = ajaxDataWx + uuid + "," + number + ",";
                        i=i+1;
                    }


                }
            }
            if(ajaxData!=null&&ajaxData!=""&&ajaxData!=undefined){
	            $.ajax({
	                type: 'POST',
	                url: isAddress_url,
	                dataType : "json" ,
	                data:{},
	
	                success:
	                    function(data){
	                        if(data==0){
	                            window.location.href= addressDetail_path + "?" +ajaxData +"?1";
	                        }else if(data==1){
	                        		window.location.href= selt_path + "?" + ajaxData;
	                        }else if(data==-1){
	                        	window.location.href=login_path;
	                        	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2c47da0ba1fbfd0&redirect_uri=http://www.m.beidahuang.com/resource/wechat/orderUpWx.html&response_type=code&scope=snsapi_base&state=" + ajaxDataWx + "#wechat_redirect";
	                        }
	                    },
	                error:
	                    function(data,textStatus){
	                        alert("服务器异常，请稍后再试");
	
	                    }
	
	            });
            }else{
        		error("至少选择一个商品");
        		
        	}
        });


        //图片以及文字点击后可查看商品详情
        $(".pic_box").click(function(){
            var pid= $(this).attr("data-id");
            window.location.href =  pro_url + pid;
        });
        $(".title").click(function(){
            var pid= $(this).attr("data-id");
            window.location.href =  pro_url + pid;
        });





    }


});
