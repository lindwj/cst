/**
 * Created by 0x401 on 16/8/30.
 */

//
//1.获取input和原始数据。获取按钮
//2.点击按钮
//3.将文本框的数值和原始数值相加。
//4.input值清空
//5.给每一行动态添加id
//6.在被勾选的情况下点击按钮修改库存
//7.改变checkbox选中状态
//8.发现如果输入框不输入值。前面的库存值不是原值。需要if语句判断为空时等于0
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

$(function(){




    $(".username").text(getCookie("userName"));

    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
    var server_context=basePath;


    //默认显示第一页的数据
    var ajaxURL =  sorder_control;
    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {},

        success:
            function(data,textStatus){

                //getOrderList(data.preOrdersList,data.preOrdersId,data.dowhat,data.productList.productUuid,data.productList.name,data.managerList);
                getOrderList(data);

                $("#allpage").text(data.page.totalPage);
                $("#curpage").text(data.page.currentPage);

            },
        error:
            function(data,textStatus){
                alert("请先登录后再访问！");
                window.location.href= url_login;
            }

    });





    $("#searchbtn").click(function() {


        $("tbody").empty();
        //获取提交链接
        //var ajaxURL =  basePath + "/manager/login.do";
        var ajaxURL =  sorder_control;



        //查询条件
        var sdate = $("#item-type").val();
        var edate = $("#item-name").val().trim();
        var atel = $("#atele").val();
        var rtel = $("#rtele").val();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html();
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:sdate,
                endTime:edate,
                applyPhone:atel,
                receiveMobile:rtel,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage
            },

            success:
                function(data,textStatus){

                    getOrderList(data);

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




        var ajaxURL =  sorder_control;


        //获取账号和密码
        //查询条件
        var sdate = $("#item-type").val();
        var edate = $("#item-name").val().trim();
        var atel = $("#atele").val();
        var rtel = $("#rtele").val();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 + 1;
        var allPage = $("#allpage").html();
        if(curPage > allPage){
            $('#errorm3').fadeIn(300);
            $('#errorm3').fadeOut(5000);
            return false;
        }
        $("tbody").empty();
        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:sdate,
                endTime:edate,
                applyPhone:atel,
                receiveMobile:rtel,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data);

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
        $("tbody").empty();
        //获取提交链接
        //var ajaxURL =  basePath + "/manager/login.do";
        var ajaxURL =  sorder_control;

        //获取账号和密码
        //查询条件
        var sdate = $("#item-type").val();
        var edate = $("#item-name").val().trim();
        var atel = $("#atele").val();
        var rtel = $("#rtele").val();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 - 1;
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:sdate,
                endTime:edate,
                applyPhone:atel,
                receiveMobile:rtel,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data);

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
    //跳转到指定页数
    $("#goBtn").click(function(){

        $("tbody").empty();



        //获取提交链接
        //var ajaxURL =  basePath + "/manager/login.do";
        var ajaxURL =  sorder_control;


        //获取账号和密码
        //查询条件
        var sdate = $("#item-type").val();
        var edate = $("#item-name").val().trim();
        var atel = $("#atele").val();
        var rtel = $("#rtele").val();
        var sta = $("#item-status").val();
        var allPage = $("#allpage").html();
        var gotoPage = $("#gotoPageNo").val().trim();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                beginTime:sdate,
                endTime:edate,
                applyPhone:atel,
                receiveMobile:rtel,
                status:sta,
                'page.currentPage':gotoPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data);

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
    //function getOrderList(info,OrdersId,dow,uuid,name,manlist){
    function getOrderList(info){



        var control1 = "";


        var productName = "";
        var userPhone = "";
        var receiveName = "";
        var receiveAccount = "";
        var receiveAddress = "";
        var receiveMobile = "";
        var receivePeriod = "";
        var managerName = "";
        var contractNo = "";
        var applyName = "";
        var applyPhone = "";
        var status = "";
        $.each(info.preOrdersList,function(i,item){





            if(item.productName == null){
                productName = "";
            }else{
                productName =item.productName;
            }
            if(item.userPhone == null){
                userPhone = "";
            }else{
                userPhone = item.userPhone;
            }
            if(item.receiveName == null){
                receiveName = "";
            }else{
                receiveName = item.receiveName;
            }
            if(item.receiveAddress == null){
                receiveAddress = "";
            }else{
                receiveAddress = item.receiveAddress;
            }
            if(item.receiveMobile == null){
                receiveMobile = "";
            }else{
                receiveMobile = item.receiveMobile;
            }
            if(item.receivePeriod == null){
                receivePeriod = "";
            }else{
                receivePeriod = item.receivePeriod;
            }
            if(item.managerName == null){
                managerName = "";
            }else{
                managerName = item.managerName;
            }
            if(item.contractNo == null){
                contractNo = "";
            }else{
                contractNo = item.contractNo;
            }
            if(item.applyName == null){
                applyName = "";
            }else{
                applyName = item.applyName;
            }
            if(item.applyPhone == null){
                applyPhone = "";
            }else{
                applyPhone = item.applyPhone;
            }
            if(item.status == "0"){
                status = "待处理";
            }
            else if(item.status == "1"){
                status = "已签约";
            }
            else{
                status = "";
            }

            control1 =control1+"<tr id='" + item.preOrdersId + "'>"
                +             "<td>"
                +             "<p class='y1' id='" + item.productUuid + "'>"+productName+"</p>"
                +             "</td>"
                +             "<td class='y2'>"+userPhone+"</td>"
                +             "<td class='y3'>"+receiveName+"</td>"
                +             "<td class='y4'>"+receiveAccount+"</td>"
                +             "<td class='y5'>"+receiveAddress+"</td>"
                +             "<td class='y6'>"+receiveMobile+"</td>"
                +             "<td class='y7'>"+receivePeriod+"</td>"
                +             "<td class='y8' id='" + item.saleManager + "'>"+managerName+"</td>"
                +             "<td class='y9'>"+contractNo+"</td>"
                +             "<td class='y10'>"+applyName+"</td>"
                +             "<td class='y11'>"+applyPhone+"</td>"
                +             "<td class='y12'>"+status+"</td>"
                +             "<td>"
                +             "<input class='modify' type='button' value='修改' data-sId='"+item.preOrdersId+"' data-ordid='"+info.preOrdersId+"' data-do='"+info.dowhat+"'>"
                +             "</td>"
                +             "</tr>";


        });


        $("tbody").append(control1);



        //商品名称循环遍历
        $("#s1").empty();
        var select1 = "";
        $.each(info.productList,function(i,item){

            select1 = select1+"<option value='"+item.productUuid+"'>"+item.name+"</option>";

        });
        $("#s1").append(select1);
        $("#a1").append(select1);




        //vip专员循环遍历
        $("#s8").empty();
        var select2 = "";
        $.each(info.managerList,function(i,item){

            select2 = select2+ "<option value='"+item.managerId+"'>"+item.name+"</option>";

        });
        $("#s8").append(select2);
        $("#a8").append(select2);




        //点击添加订单
        $('#addbtn').click(function(){

            $('#black1').fadeIn();


        });

        //确认添加订单
        $("#surebtn").click(function(){


            var ord = $("#a1").val();
            var vip = $("#a8").val();

            var bsiorder = $("#a2").val();
            var gname = $("#a3").val();
            var gmoney = $("#a4").val();
            var gaddress = $("#a5").val();
            var gtele = $("#a6").val();
            var sendtime = $("#a7").val();

            var traynum = $("#a9").val();


            var reg = /(1[3-9]\d{9}$)/;

            if(!reg.test(bsiorder)){
                $('#errorm4').fadeIn(300);
                $('#errorm4').fadeOut(5000);
                return false;
            }
            if(!reg.test(gtele)){
                $('#errorm5').fadeIn(300);
                $('#errorm5').fadeOut(5000);
                return false;
            }


            //把页面中数据传到后台
            var ajaxURL =  sorder_addedit;

            $.ajax({
                type: 'POST',
                url: ajaxURL,
                dataType : "json" ,
                data: {
                    productUuid:ord,
                    userPhone:bsiorder,
                    receiveName:gname,
                    receiveAccount:gmoney,
                    receiveAddress:gaddress,
                    receiveMobile:gtele,
                    receivePeriod:sendtime,
                    saleManager:vip,
                    contractNo:traynum,
                    dowhat:'add'

                },
                success :
                    function(data,textStatus) {
                        //把页面中数据传到后台，ajax返回成功后，执行下面操作


                        if(data == '101'){
                            $('#errorm1').fadeIn(300);
                            $('#errorm1').fadeOut(5000);
                            return false;
                        }
                        if(data == '102'){
                            $('#errorm2').fadeIn(300);
                            $('#errorm2').fadeOut(5000);
                            return false;
                        }




                        var addnew = "";

                        var productname = $("#a1").find("option:selected").text();
                        var vipname = $("#a8").find("option:selected").text();

                        addnew =addnew+"<tr >"
                            +             "<td>"
                            +             "<p class='y1' id='" + ord + "'>"+productname+"</p>"
                            +             "</td>"
                            +             "<td class='y2'>"+bsiorder+"</td>"
                            +             "<td class='y3'>"+gname+"</td>"
                            +             "<td class='y4'>"+gmoney+"</td>"
                            +             "<td class='y5'>"+gaddress+"</td>"
                            +             "<td class='y6'>"+gtele+"</td>"
                            +             "<td class='y7'>"+sendtime+"</td>"
                            +             "<td class='y8' id='" + vip + "'>"+vipname+"</td>"
                            +             "<td class='y9'>"+traynum+"</td>"
                            +             "<td class='y10'>"+""+"</td>"
                            +             "<td class='y11'>"+""+"</td>"
                            +             "<td class='y12'>"+""+"</td>"
                            +             "<td>"
                            +             "<input class='modify' type='button' value='修改' data-do='"+'add'+"'>"
                            +             "</td>"
                            +             "</tr>";


                        $("tbody").append(addnew);

                        $('#black1').fadeOut();
                    },
                error :
                    function(data,textStatus) {



                        alert("服务器异常，请稍后再试")

                    }
            });


        });

        //取消添加订单
        $('#canclebtn').click(function(){
            $('#black1').fadeOut();
        });





        //修改订单按钮
        $(".modify").click(function(){



            $("#black2").fadeIn();

            var ID = $(this).attr("data-sId");

            $("#s1").val($("#" + ID + " .y1").attr("id"));
            $("#s2").val($("#" + ID + " .y2").text());
            $("#s3").val($("#" + ID + " .y3").text());
            $("#s4").val($("#" + ID + " .y4").text());
            $("#s5").val($("#" + ID + " .y5").text());
            $("#s6").val($("#" + ID + " .y6").text());
            $("#s7").val($("#" + ID + " .y7").text());

            $("#s8").val($("#" + ID + " .y8").attr("id"));



            $("#s9").val($("#" + ID + " .y9").text());

            $("#item-id").val(ID);



        });


        //确认修改订单
        $("#surebtn1").click(function(){


            var ord = $("#s1").val();
            var vip = $("#s8").val();

            var bsiorder = $("#s2").val();
            var gname = $("#s3").val();
            var gmoney = $("#s4").val();
            var gaddress = $("#s5").val();
            var gtele = $("#s6").val();
            var sendtime = $("#s7").val();

            var traynum = $("#s9").val();
            var orderid = $("#item-id").val();


            //校验电商账号和收获手机号合法性
            var reg = /(1[3-9]\d{9}$)/;

            if(!reg.test(bsiorder)){
                $('#errorm6').fadeIn(300);
                $('#errorm6').fadeOut(5000);
                return false;
            }
            if(!reg.test(gtele)){
                $('#errorm7').fadeIn(300);
                $('#errorm7').fadeOut(5000);
                return false;
            }


            //把页面中数据传到后台
            var ajaxURL =  sorder_addedit;

            $.ajax({
                type: 'POST',
                url: ajaxURL,
                dataType : "json" ,
                data: {
                    productUuid:ord,
                    userPhone:bsiorder,
                    receiveName:gname,
                    receiveAccount:gmoney,
                    receiveAddress:gaddress,
                    receiveMobile:gtele,
                    receivePeriod:sendtime,
                    saleManager:vip,
                    contractNo:traynum,
                    preOrdersId:orderid,
                    dowhat:'edit'

                },
                success :
                    function(data,textStatus) {
                        //把页面中数据传到后台，ajax返回成功后，执行下面操作


                        var ID = $("#item-id").val();
                        //$("#" + ID + " .y1").text(ord);


                        $.each(info.productList,function(i,item){

                            if(ord == item.productUuid){
                                $("#" + ID + " .y1").text(item.name);
                            }


                        });



                        $("#" + ID + " .y2").text(bsiorder);
                        $("#" + ID + " .y3").text(gname);
                        $("#" + ID + " .y4").text(gmoney);
                        $("#" + ID + " .y5").text(gaddress);
                        $("#" + ID + " .y6").text(gtele);
                        $("#" + ID + " .y7").text(sendtime);
                        //$("#" + ID + " .y8").text(vip);

                        $.each(info.managerList,function(i,item){

                            if(vip == item.managerId){
                                $("#" + ID + " .y8").text(item.name);
                            }


                        });


                        $("#" + ID + " .y9").text(traynum);
                        $("#black2").fadeOut();


                    },
                error :
                    function(data,textStatus) {
                        alert("服务器异常，请稍后再试")

                    }
            });


        });


        //取消修改订单
        $('#canclebtn1').click(function(){
            $('#black2').fadeOut();
        });










    }



});
