function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

$(function(){



    //显示从cookie中获取的用户名
    $(".username").text(getCookie("userName"));

    //获取登录后进入的订单管理的链接
    var ajaxURL =  sales_AllList;
    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {},

        success:
            function(data){

                //显示订单管理第一页数据
                getOrderList(data.shopFinancialList);

                //显示当前页和所有页
                /*$("#allpage").text(data.page.totalPage);
                $("#curpage").text(data.page.currentPage);*/

            },
        error:
            function(data,textStatus){

                //若没有登录提示登录
                alert("请先登录后再访问！");

                //跳转到登录页链接
                window.location.href= url_login;
            }

    });

    //搜索
    $("#searchbtn").on( "click", function() {

        //清空前面的所有数据
        $(".tablebox").empty();

        //获取提交链接
        var ajaxURL =  sales_AllList;


        //查询条件
        var time1 = $("#time1").val().trim();
        var time2 = $("#time2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html();
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:time1,
                endTime:time2,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data){

                    //显示搜索出来的数据
                    getOrderList(data.shopFinancialList);

                    //显示当前页和所有页
                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);


                },
            error:
                function(data,textStatus){

                    alert("服务器异常，请稍后再试");
                }

        });
    });

    //下一页
    $("#nextmove").click(function(){

        //获取提交链接
        var ajaxURL =  sales_AllList;


        //查询条件
        var time1 = $("#time1").val().trim();
        var time2 = $("#time2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 + 1;
        var allPage = $("#allpage").html();

        //如果当前页大于所有页数则提示
        if(curPage > allPage){
            $('#errorm1').fadeIn(300);
            $('#errorm1').fadeOut(5000);
            return false;
        }
        //清空前一页数据
        $(".tablebox").empty();
        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:time1,
                endTime:time2,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data){


                    //显示当前页数据
                    getOrderList(data.shopFinancialList);

                    //显示当前和和所有页
                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);



                },
            error:
                function(data,textStatus){
                    //alert("success:" + data + "," + textStatus);
                    alert("服务器异常，请稍后再试");
                }

        });

    });

    //上一页
    $("#prevmove").click(function(){


        //清空前一页所有与数据
        $(".tablebox").empty();

        //获取提交链接
        var ajaxURL =  sales_AllList;


        //查询条件
        var time1 = $("#time1").val().trim();
        var time2 = $("#time2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 - 1;
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:time1,
                endTime:time2,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data){

                    //显示当前页数据
                    getOrderList(data.shopFinancialList);
                    //显示当前和和所有页
                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);


                },
            error:
                function(data,textStatus){

                    alert("服务器异常，请稍后再试");
                }

        });

    });

    //跳转到指定页
    $("#goBtn").click(function(){

        //清空前一页所有数据
        $(".tablebox").empty();

        //获取提交链接
        var ajaxURL =  sales_AllList;


        //查询条件
        var time1 = $("#time1").val().trim();
        var time2 = $("#time2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html();
        var allPage = $("#allpage").html();
        var gotoPage = $("#gotoPageNo").val().trim();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:time1,
                endTime:time2,
                status:sta,
                'page.currentPage':gotoPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){

                    //显示当前页数据
                    getOrderList(data.shopFinancialList);

                    //显示当前页和所有页
                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);


                },
            error:
                function(data,textStatus){
                    //alert("success:" + data + "," + textStatus);
                    alert("服务器异常，请稍后再试");
                }

        });

    });




    //解析json数据，页面显示
    function getOrderList(info){
        var table1 = "";
        $.each(info,function(i,item){
            var statu = "";
            var time = "";
            var amount = "";
            var service = "";
            var transfer = "";
            var paytype = "";
            if(item.status == 0){
                statu = "未转账";
                time = "";
            }else if(item.status == 1){
                statu = "已转账";
                time = item.transferDateStr;
            }
            if(item.amount == null){
                amount = 0;
            }else{
                amount = item.amount;
            }
            if(item.serviceFee == null){
                service = 0;
            }else{
                service = item.serviceFee;
            }
            if(item.transferFee == null){
                transfer = 0;
            }else{
                transfer = item.transferFee-item.moneyWd;
            }
            if(item.payType == 1){
                paytype = "支付宝";
            }else if(item.payType == 2){
                paytype = "微信";
            }



            table1 += "<table class='table2'>"
                +    "<tr>"
                +    "<td class='col1'>"+ item.code +"</td>"
                +    "<td class='col2'>"+ item.shopName +"</td>"
                +    "<td class='col3' >"+ item.dayStr +"</td>"
                +    "<td class='col4'>"+ amount +"</td>"
                +    "<td class='col5'>"+ service +"</td>"
                +    "<td class='col6'>"+ item.moneyWd +"</td>"
                +    "<td class='col7'>"+ transfer +"</td>"
                +    "<td class='col9' data-day='"+item.dayStr+"'>查看详情</td>"
                +    "</tr>"
                +    "</table>";
        });

        $(".tablebox").append(table1);   //把所有数据放到class为productmenu里

        $(".col9").click(function(){
        	window.location.href=link_transferDetail+"?"+$(this).attr("data-day");
        })
        


    }






});