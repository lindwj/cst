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
    //$(".username").text("ca");


    //默认显示第一页的数据
    var ajaxURL =  order_control;
    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {},

        success:
            function(data){

                getOrderList(data.ordersList);

                $("#allpage").text(data.page.totalPage);
                $("#curpage").text(data.page.currentPage);

            },
        error:
            function(data,textStatus){
                alert("请先登录后再访问！");
                window.location.href= url_login;
            }

    });







    //搜索
    $("#searchbtn").on( "click", function() {
        $(".productmenu").empty();

        //获取提交链接
        var ajaxURL =  order_control;


        //获取账号和密码
        //查询条件
        var tele = $("#tel-name").val().trim();
        var ord = $("#item-name").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html();
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                receiveMobile:tele,
                code:ord,
                statusstr:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data){


                    getOrderList(data.ordersList);

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
    //下一页
    $("#nextmove").click(function(){



        //$(".table3").empty();
        //if( data.page.currentPage < data.page.totalPage ){
        //    data.page.currentPage ++;
        //}



        //获取提交链接
        //var ajaxURL =  basePath + "/manager/login.do";
        var ajaxURL =  order_control;


        //获取账号和密码
        //查询条件
        var tele = $("#tel-name").val().trim();
        var ord = $("#item-name").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 + 1;
        var allPage = $("#allpage").html();

        if(curPage > allPage){
            $('#errorm1').fadeIn(300);
            $('#errorm1').fadeOut(5000);
            return false;
        }
        $(".productmenu").empty();
        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                receiveMobile:tele,
                code:ord,
                statusstr:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data.ordersList);

                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);
                    //


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


        $(".productmenu").empty();


        //获取提交链接
        var ajaxURL =  order_control;


        //获取账号和密码
        //查询条件
        var tele = $("#tel-name").val().trim();
        var ord = $("#item-name").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 - 1;
        var allPage = $("#allpage").html();

        $.ajax({
            type: 'POST',
            url: ajaxURL,
            dataType : "json" ,
            data:
            {
                receiveMobile:tele,
                code:ord,
                statusstr:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data.ordersList);

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
    //跳转到指定页
    $("#goBtn").click(function(){


        $(".productmenu").empty();
        //$(".table3").empty();
        //if( data.page.currentPage < data.page.totalPage ){
        //    data.page.currentPage ++;
        //}



        //获取提交链接
        //var ajaxURL =  basePath + "/manager/login.do";
        var ajaxURL =  order_control;


        //获取账号和密码
        //查询条件
        var tele = $("#tel-name").val().trim();
        var ord = $("#item-name").val().trim();
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
                receiveMobile:tele,
                code:ord,
                statusstr:sta,
                'page.currentPage':gotoPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


                    getOrderList(data.ordersList);

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
        var table = "";
        var tableTemp = "";

        var statu = "";
        var note = "";
        $.each(info,function(i,item){
        	var dealCode="";
        	var way="";
        	if(item.wayType==null){
        		way="支付宝";
        		dealCode=testnull(item.tradeNo);
        	}else if(item.wayType==1){
        		way="微信支付";
        		dealCode=testnull(item.transactionId);
        	}

            tableTemp = "<table class='table2'>"
                +     "<tr class='table2title'>"
                +     "<td colspan='8'>"+ item.createDatestr +"&nbsp;&nbsp;&nbsp;&nbsp;订单号："+ item.code +"&nbsp;&nbsp;&nbsp;&nbsp;交易号："+ dealCode +"&nbsp;&nbsp;&nbsp;&nbsp;支付方式："+ way +"</td>"
                +     "<td><input class='wlbutton changebtn' type='button' value='物流信息' data-code='"+ item.code +"'></td>"
                +     "</tr>";




            if(item.status == "1"){
                statu = "已付款";
                //$("#item-status option").val("1");
            }else if(item.status == "2"){
                statu = "已签收";
                //$("#item-status option").val("2");
            }else{
                statu = "";
            }

            if(item.productUuid  != '0'){
                //当订单中商品数量只有1个的时候
                tableTemp = tableTemp + "<tr id='"+item.status+"'>"
                    +              "<td class='col4'>"
                    +              "<div class='tablepic'>"
                    +              "<img src=\""+ link_pic + item.pic +"\">"
                    +              "</div>"
                    +              "</td>"
                    +              "<td class='col5'>" + item.subject + "</td>"
                    +              "<td class='col6' valign='top'>"+ item.price +"</td>"
                    +              "<td class='col7' valign='top'>"+ item.capacity +"</td>"
                    +              "<td class='col8' valign='top'>"+ item.receiveName +"&nbsp;&nbsp;"+ item.receiveMobile +"<br>"+ item.receiveAddress +"</td>"
                    +              "<td class='col9' valign='top'>"+ item.totalFromBdh +"</td>"
                    +              "<td class='col10' valign='top'>"+ item.serviceFee +"</td>"
                    +              "<td class='col11' valign='top'>"+ testnull(item.note) +"</td>"
                    +              "<td class='col12' valign='top'>"
                    +              "<span class='state1'>"+ statu +"</span>"
                    +              "<br>";


                if(item.status == "1"){
                    tableTemp = tableTemp + "<input class='changebtn' type='button' value='已签收' data-orderid='" +  item.code +  "' data-name='"+item.status+"'  data-user='"+item.createByUser+"' data-openid='"+item.openid+"'>"
                        +              "<input class='changebtn1 display-none' type='button' value='已付款' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                        +              "</td>"
                        +              "</tr>";
                }else if(item.status == "2"){
                    tableTemp = tableTemp + "<input class='changebtn display-none' type='button' value='已签收' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                        +              "<input class='changebtn1' type='button' value='已付款' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                        +              "</td>"
                        +              "</tr>";
                }

            }else{
                //当订单中商品数量大于等于2个的时候


                $.each(item.ordersDetailList,function(i,ordersListItem){
                    if( i == 0){
                        tableTemp = tableTemp + "<tr>"
                            +              "<td class='col4'>"
                            +              "<div class='tablepic'>"
                            +              "<img src=\""+ link_pic + ordersListItem.pic +"\">"
                            +              "</div>"
                            +              "</td>"
                            +              "<td class='col5'>"+ ordersListItem.productName +"</td>"
                            +              "<td class='col6' valign='top'>"+ ordersListItem.price +"</td>"
                            +              "<td class='col7' valign='top'>"+ ordersListItem.capacity +"</td>"
                            +              "<td class='col8' valign='top'>"+ item.receiveName +"&nbsp;&nbsp;"+ item.receiveMobile +"<br>"+ item.receiveAddress +"</td>"
                            +              "<td colspan='i' class='col9' valign='top'>"+ item.totalFromBdh +"</td>"
                            +              "<td class='col10' valign='top'>"+ item.serviceFee +"</td>"
                            +              "<td class='col11' valign='top'>"+ testnull(item.note) +"</td>"
                            +              "<td class='col12' valign='top'>"
                            +              "<span class='state1'>"+ statu +"</span>"
                            +              "<br>";

                        if(item.status == "1"){
                            tableTemp = tableTemp + "<input class='changebtn' type='button' value='已签收' data-orderid='" +  item.code +  "' data-name='"+item.status+"' data-user='"+item.createByUser+"' data-openid='"+item.openid+"'>"
                                +              "<input class='changebtn1 display-none' type='button' value='已付款' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                                +              "</td>"
                                +              "</tr>";
                        }else if(item.status == "2"){
                            tableTemp = tableTemp + "<input class='changebtn display-none' type='button' value='已签收' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                                +              "<input class='changebtn1' type='button' value='已付款' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                                +              "</td>"
                                +              "</tr>";
                        }
                        //tableTemp = tableTemp +
                        //
                        //    +              "<input class='changebtn display-none' type='button' value='已签收' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                        //    +              "<input class='changebtn1' type='button' value='已付款' data-orderid='" +  item.code +  "' data-name='"+item.status+"'>"
                        //    +              "</td>"
                        //    +              "</tr>";
                    }
                    else{
                        tableTemp = tableTemp + "<tr>"
                            +              "<td class='col4'>"
                            +              "<div class='tablepic'>"
                            +              "<img src=\""+ link_pic + ordersListItem.pic +"\">"
                            +              "</div>"
                            +              "</td>"
                            +              "<td class='col5'>" + ordersListItem.productName + "</td>"
                            +              "<td class='col6' valign='top'>"+ ordersListItem.price +"</td>"
                            +              "<td class='col7' valign='top'>"+ ordersListItem.capacity +"</td>"
                            +              "<td class='col8' valign='top'>"
                            +              "</td>"
                            +              "<td colspan='i' class='col9' valign='top'>"
                            +              "</td>"
                            +              "<td class='col10' valign='top'>"
                            +              "</td>"
                            +              "<td class='col11' valign='top'>"
                            +              "</td>"
                            +              "<td class='col12' valign='top'>"
                            +              "</td>"
                            +              "</tr>";
                    }

                });


            }

            tableTemp = tableTemp + "</table>" ;
            table = table + tableTemp ;

        });

        $(".productmenu").append( table );


        //已签收
        $(".changebtn").on( "click", function() {

            //获取提交链接
            var ajaxURL =  state_change;
            var orderUser = $(this).attr("data-user");
            var orderOpenid = $(this).attr("data-openid");

            var orderID = $(this).attr("data-orderid");
            //var sta = $(this).attr("data-name");
            if(orderUser!=null){
            	$.ajax({
                    type : "post",
                    url: bind_user,
                    dataType : "json",
                    data :
                    {
                    	userId:orderUser
                    },
                    success : function(data) {
                    	/*if(data==1){
                    		
                    	}else{
                    		
                    	}*/
                    },
                    error : function(data) {
                        alert("数据异常，请稍后重试");
                    }
                });
            }else{
            	if(orderOpenid!=null){
            		$.ajax({
                        type : "post",
                        url: bindOpen_user,
                        dataType : "json",
                        data :
                        {
                        	openId:orderOpenid
                        },
                        success : function(data) {
                        	/*if(data==1){
                        		
                        	}else{
                        		
                        	}*/
                        },
                        error : function(data) {
                            alert("数据异常，请稍后重试");
                        }
                    });
            	}else{
            		//error("订单异常");
            		return false;
            	}
            	
            }

            var obj = $(this);

            $.ajax({
                type : "post",
                url: ajaxURL,
                dataType : "json",
                data :
                {
                    code:orderID,
                    statusstr: '2'
                },
                success : function(data,textStatus) {


                    //$(obj).parent().find("span").html("已付款（配货中）");
                    //$(obj).val("已签收");
                    $(obj).parent().find("span").html("已签收");


                },
                error : function(data,textStatus) {
                    alert("数据异常，请稍后重试");
                }
            });


            $(this).addClass("display-none");
            $(this).next("input").removeClass("display-none");


        });
        //已配送
        $(".changebtn1").on( "click", function() {


            //获取提交链接
            var ajaxURL =  state_change;



            var orderID = $(this).attr("data-orderid");

            var sta = $(this).prev("span").text();
            var obj = $(this);

            $.ajax({
                type : "post",
                url: ajaxURL,
                dataType : "json",
                data :
                {
                    code:orderID,
                    statusstr: '1'
                },
                success : function(data,textStatus) {

                    $(obj).parent().find("span").html("已付款");

                },
                error : function(data,textStatus) {
                    alert("数据异常，请稍后重试");
                }
            });
            $(this).addClass("display-none");
            $(this).prev("input").removeClass("display-none");
            

        });



        $(".wlbutton").click(function(){

            var ddcode = $(this).attr("data-code");
            $("#surebtn").attr("data-code","");

            $("#unit_price").val("");

            $.ajax({
                type: 'POST',
                url:link_wuliu,
                dataType : "json" ,
                data:{},

                success:
                    function(data,textStatus){
                        getParameter(data);
                    },
                error:
                    function(data,textStatus){
                        alert("请先登录后再访问！");
                        window.location.href= url_login;
                    }
            });


            $("#hidebox").fadeIn();
            $("#surebtn").attr("data-code",ddcode);
        });
        $("#canclebtn").click(function(){
            $("#hidebox").fadeOut();
        });


        function getParameter(info){
            $("#p1").empty();
            var select_parameter="<option value='"+0+"' selected = 'selected'>"+"请选择"+"</option>";

            $.each(info,function(i,item){
                select_parameter=select_parameter+"<option value='"+item.kdnId+"'>"+item.business+"</option>";
            });
            $("#p1").append(select_parameter);
        }


        $("#surebtn").click(function(){


            var ID = $("#select3").find("option:selected").val();
            var code1 = $("#unit_price").val().trim();
            var code2 = $(this).attr("data-code");

            if(code1.length<6){
                error("订单号不能小于6位");
            }else{
                $.ajax({
                    type: 'POST',
                    url:link_sendcode,
                    dataType : "json" ,
                    data:{
                        kdnId:ID,
                        code:code1,
                        orderCode:code2
                    },

                    success:
                        function(data,textStatus){
                            //$("#hidebox").fadeOut();
                            console.log(data);
                            if(data==1){
                                $("#hidebox").fadeOut();
                            }else if(data==0){
                                error("操作不成功,请稍后再试");
                            }

                        },
                    error:
                        function(data,textStatus){
                            alert("请先登录后再访问！");
                            window.location.href= url_login;
                        }
                });

            }

        });




    }

    function testnull(obj){
		if(obj!=null){
			obj=obj;
		}else{
			obj="";
		}
		return obj;
	}







});

