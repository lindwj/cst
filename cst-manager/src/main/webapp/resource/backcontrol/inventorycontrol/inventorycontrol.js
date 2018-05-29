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

    //默认显示第一页的数据
    var ajaxURL =  invent_control;
    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {},

        success:
            function(data,textStatus){

                getOrderList(data.stockList,data.shopId,data.updateByAdm);


                //b = data.updateByAdm;

                $("#allpage").text(data.page.totalPage);
                $("#curpage").text(data.page.currentPage);

            },
        error:
            function(data,textStatus){
                alert("请先登录后再访问！");
                window.location.href= url_login;
            }

    });

    $.ajax({
        type: 'POST',
        url: order_getparameter,
        dataType : "json" ,
        data: {},

        success:
            function(data){
                getparameter(data);
            },
        error:
            function(data,textStatus){
                alert("请先登录后再访问！");
                window.location.href= url_login;
            }

    });






    var search = "";
    var search1 = "";
    var search2 = "";
    var search3 = "";
    //获取商品分类
    function getparameter(info){
        search1 = "<label class='search-label'>类型：</label>"
            +    "<select id='item-type' name='item-type' class='select-default'>"
            +    "<option value=''>选择类型</option>";
        $.each(info,function(i,item){
            search2 += "<option value='"+ item.parameterId +"'>"+ item.parameterName +"</option>"
        });

        search3 = "</select>"
            +    "<label class='search-label1'>名称：</label>"
            +    "<input id='item-name' name='item-name' type='text' placeholder='输入名称' class='input-default'>"
            +    "<button type='button' class='btn-default' id='searchbtn'>搜索</button>";

        search = search1 + search2 + search3;

        $(".search").append(search);


        //搜索
        $("#searchbtn").on( "click", function() {


            $("tbody").empty();
            //获取提交链接
            //var ajaxURL =  basePath + "/manager/login.do";
            var ajaxURL =  invent_control;



            //查询条件
            var ptype = $("#item-type").val();
            var ord = $("#item-name").val();
            var curPage = $("#curpage").html();
            var allPage = $("#allpage").html();

            $.ajax({
                type: 'POST',
                url: ajaxURL,
                dataType : "json" ,
                data:
                {
                    typeId:ptype,
                    name:ord,
                    'page.currentPage':curPage,
                    'page.totalPage':allPage
                },

                success:
                    function(data,textStatus){

                        getOrderList(data.stockList,data.shopId,data.updateByAdm);

                        $("#allpage").text(data.page.totalPage);
                        $("#curpage").text(data.page.currentPage);


                    },
                error:
                    function(data,textStatus){
                        alert("服务器异常，请稍后再试");

                    }

            });
        });
    }

    //解析json数据，页面显示
    function getOrderList(info,shopid,update){

        var control1 = "";

        var showCapacity = "";





        $.each(info,function(i,item){


            if(item.showCapacity == null){
                showCapacity = "0";
            }else{


                if(item.sellCapacity == null){
                    item.sellCapacity
                }
                showCapacity = item.showCapacity - item.sellCapacity;
            }
            //if(item.shopCapacity == null){
            //    shopCapacity = "0";
            //}else{
            //    shopCapacity = item.shopCapacity;
            //}


            //if(item.status == "-1"){
            //    status = "已删除";
            //    //$("#item-status option").val("1");
            //}else if(item.status == "1"){
            //    status = "已上架";
            //    //$("#item-status option").val("2");
            //}else if(item.status == "2"){
            //    status = "已下架";
            //    //$("#item-status option").val("2");
            //}else{
            //    status = "";
            //}




            control1 =control1+ "<tr class='dataid'> "
                +             "<td><input class='checkbox1' type='checkbox' name='ckbox' id='"+item.productUuid+"' data-shopid='"+shopid+"' data-stockId='"+item.stockId+"' data-updateByAdm='"+update+"' value='0'>"
                +             "</td>"
                +             "<td class='pic'>"
                +             "<div>"
                +             "<img src='"+link_pic + item.pic+"'>"
                +             "</div>"
                +             "</td>"
                +             "<td>"
                +             "<p class='producttitle'>"+item.name+"</p>"
                +             "</td>"
                +             "<td class='shownum' id='shownum" + item.productUuid + "'>"+showCapacity+"</td>"
                +             "<td>"
                +             "<input class='showplus' type='text' id='showplus" + item.productUuid + "'>"
                +             "</td>"
                //+             "<td class='shopnum' id='shopnum" + item.productUuid + "'>"+shopCapacity+"</td>"
                //+             "<td>"
                //+             "<input class='shopplus' type='text' id='shopplus" + item.productUuid + "'>"
                //+             "</td>"
                +             "<td id='istatus' style='display: none;'>"+status+"</td>"
                +         "</tr>";





        });
        $("tbody").append(control1);



    }








    //下一页
    $("#nextmove").click(function(){



        //$(".table3").empty();
        //if( data.page.currentPage < data.page.totalPage ){
        //    data.page.currentPage ++;
        //}



        //获取提交链接
        var ajaxURL =  invent_control;


        //获取账号和密码
        //查询条件
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
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data.stockList,data.shopId,data.updateByAdm);

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
        var ajaxURL =  invent_control;


        //获取账号和密码
        //查询条件
        var curPage = $("#curpage").html() - 0 - 1;
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data.stockList,data.shopId,data.updateByAdm);

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
        var ajaxURL =  invent_control;


        //获取账号和密码
        //查询条件
        var curPage = $("#curpage").html() - 0 + 1;
        var allPage = $("#allpage").html();
        var gotoPage = $("#gotoPageNo").val().trim();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                'page.currentPage':gotoPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data.stockList,data.shopId,data.updateByAdm);

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



    $("#renew").click(function () {

    	//$(this).attr('disabled',"true");
        //获取页面选中的数据


        //修改第一条数据的值

        var ajaxURL =  invent_addedit;




        var state = "1";
        var dshopid = $(".checkbox1").attr("data-shopid");
        var dupdateByAdm = $(".checkbox1").attr("data-updateByAdm");

        //var ajaxData = "stockList[0].productUuid:"+ orderID+ ",stockList[0].stockId:"+ dstockId+",stockList[0].shopId:"+dshopid+",stockList[0].updateByAdm:"+dupdateByAdm+ ",stockList[0].showCapacity:"+showC+",stockList[0].shopCapacity:"+shopC+",stockList[0].status=1";



        var ajaxData = "";

        $("input[type=checkbox]:checked").each(function(index,element){ //由于复选框一般选中的是多个,所以可以循环输出


            var dstockId = $(this).attr("data-stockId");
            var orderID = $(this).attr("id");
            var showC = $("#" + "showplus" + orderID).val();
            //var shopC = $("#" + "shopplus" + orderID).val();


            if(index != 0){
                ajaxData = ajaxData + "&";
            }

            if(dstockId == "null"){
               ajaxData = ajaxData + "stockList[" + index +"].productUuid="+ orderID+ "&stockList[" + index + "].stockId=&stockList[" + index + "].shopId="+dshopid+"&stockList[" + index + "].updateByAdm="+dupdateByAdm+ "&stockList[" + index + "].showCapacity="+showC+"&stockList[" + index + "].status=1";
            }

            if(dstockId != "null"){
               ajaxData = ajaxData + "stockList[" + index +"].productUuid="+ orderID+ "&stockList[" + index + "].stockId="+ dstockId+"&stockList[" + index + "].shopId="+dshopid+"&stockList[" + index + "].updateByAdm="+dupdateByAdm+ "&stockList[" + index + "].showCapacity="+showC+"&stockList[" + index + "].status=1";
            }
            //ajaxData = ajaxData + "stockList[" + index +"].productUuid="+ orderID+ "&stockList[" + index + "].stockId="+ dstockId+"&stockList[" + index + "].shopId="+dshopid+"&stockList[" + index + "].updateByAdm="+dupdateByAdm+ "&stockList[" + index + "].showCapacity="+showC+"&stockList[" + index + "].shopCapacity="+shopC+"&stockList[" + index + "].status=1";




        });

        if(!$("input[type=checkbox]").is(':checked')){
            $('#errorm2').fadeIn(300);
            $('#errorm2').fadeOut(5000);
        }


        var proid = $("input[type=checkbox]:checked").attr("id");
        var show1 = $("#" + "showplus" + proid).val();
        //var show2 = $("#" + "shopplus" + proid).val();
        var show3 = $("#" + "shownum" + proid).text();
        //var show4 = $("#" + "shopnum" + proid).text();
        //if(show1 =="" || show2 == ""){
        //    if(show3 == "0" && show4 == "0"){
                if(show1 == ""){
                    $('#errorm4').fadeIn(300);
                    $('#errorm4').fadeOut(5000);
                }else{
                    $.ajax({
                        type: 'POST',
                        url: ajaxURL,
                        //dataType : "json" ,
                        data:ajaxData,

                        success:
                            function(data,textStatus){

                                if( data == 300){

                                    $("input[type=checkbox]:checked").each(function(index,element){

                                        var proID = $(this).attr("id");
                                        var num1 = $("#" + "shownum" + proID).text();
                                        var num2 = $("#" + "showplus" + proID).val();
                                        //var num3 = $("#" + "shopnum" + proID).text();
                                        //var num4 = $("#" + "shopplus" + proID).val();
                                        if(num2 == ''){
                                            num2='0';
                                        }


                                        $("#" + "shownum" + proID).text(parseInt(num1) + parseInt(num2));
                                        //$("#" + "shopnum" + proID).text(parseInt(num3) + parseInt(num4));
                                        $("#" + "showplus" + proID).val("");
                                        //$("#" + "shopplus" + proID).val("");


                                        if($("input[type=checkbox]").is(':checked')){
                                            $('#errorm1').fadeIn(300);
                                            $('#errorm1').fadeOut(5000);
                                        }

                                        $(this).prop("checked",false);



                                    });

                                }else{
                                    alert("请检查输入是否有误，或刷新后再试");
                                }





                            },
                        error:
                            function(data,textStatus){
                                alert("数据库异常");

                            }

                    });
                }




    });



});