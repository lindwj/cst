

$(function(){

	var code = window.location.href.split("=")[1];

	
    $.ajax({
        type: 'POST',
        url: wl_url,
        dataType : "json" ,
        data: {
            orderCode:code
        },
        success:
            function(data){
                getWlList(data);
            },
        error:
            function(data,textStatus){

            }

    });




    var time = "";
    //解析json数据，页面显示
    function getWlList(info) {




        if(info.lTraces==""){
            $(".detailm").remove();
            $(".order_detail").remove();
            $(".wllogo").show();
        }else{
            var status="";
            if(info.state==9){
                status="待付款";
            }else if(info.state==1){
                status="待发货";
            }else if(info.state==2){
                status="已签收";
            }else if(info.state==-1){
                status="交易关闭";
            }

            time =   "<div class='order_state'>"
                +    "<h1 style='margin-top: 6px;'>"
                +    "<i class='iconfont stateicon'>&#xe670;</i>"
                +    "</h1>"
                +    "<p>订单状态:&nbsp;"+ status +"<br>订单编号:&nbsp;"+ info.logisticCode +"<br>快递公司:&nbsp;"+ info.shipperCode +"</p>"
                +    "<div class='clear'></div>"
                +    "</div>"
                +    "<div class='clear'></div>"
                +    "</div>";

            $(".order_detail").append(time);

            var wldetail = "";
            var circle = "";
            var length = info.lTraces.length;
            $.each(info.lTraces,function(i,item){

                if(i==0){
                    wldetail = "<div class='textbox'>"
                        +       "<h1 class='color green'>" + item.acceptStation + "</h1>"
                        +       "<p class='color green'>" + item.acceptTime + "</p>"
                        +       "</div>";

                    circle = "<div class='circle1'>"
                        +    "<span class='circle2'></span>"
                        +    "</div>";
                }else{

                    wldetail += "<div class='textbox'>"
                        +       "<h1 class='color'>" + item.acceptStation + "</h1>"
                        +       "<p class='color'>" + item.acceptTime + "</p>"
                        +       "</div>";

                    circle += "<div class='line'></div>"
                        +    "<div class='circle3'></div>";
                }


            });
            $(".rightbox").append(wldetail);
            $(".leftbox").append(circle);
        }








        $(".arrow").click(function(){
        	history.back();
        });



    }






});