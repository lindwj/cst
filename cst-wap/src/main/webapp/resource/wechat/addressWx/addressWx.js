
$(function(){

	var orders="";
	var order="";
	var shop="";
	var openid="";
	order = window.location.href.split("?")[1];
	openid=window.location.href.split("?&openid=")[1];
	shop = window.location.href.split("?").pop();
	if(openid==undefined||openid==null||openid==""){
		alert("无该微信用户");
	}else{
		if(openid.indexOf("?")){
			openid=openid.split("?")[0];
		}else{
			openid=openid;
		}
	}
	console.log(order);
	if(order==undefined||order==null||order==""){
		order="";
		orders="";
	}else{
		console.log(order.indexOf("openid="));
		if(order.indexOf("openid=")>0){
			orders="";
	    }else{
	    	orders=order;
	    }
	}
    $.ajax({
        type: 'POST',
        url: addressListOpen_url,
        dataType : "json" ,
        data: {
        	openid:openid
        },

        success:
            function(data){
                getOrderList(data);
            },
        error:
            function(data,textStatus){

            }

    });

    $(".addnew").click(function(){
    	if(orders!=null&&orders!=""&&orders!=undefined&&shop!=1){
    		window.location.href = addressDetailWx_path + '?' +orders + "?&openid=" + openid;
    	}else if(shop==1){
    		window.location.href = addressDetailWx_path + '?' +orders + "?&openid=" + openid+"?1";
    	}else{
    		window.location.href = addressDetailWx_path + "?&openid=" +openid;
    	}
    })





    //解析json数据，页面显示
    function getOrderList(info) {

        var address = "";
        var address1 = "";
        var address2 = "";
        var address3 = "";



        $.each(info,function(i,item){



            address1 =  "<div class='address_box' id='"+ item.goodsAddressUuid +"'>"
                +      "<h1 class='name'>"+ item.name +"</h1>"
                +      "<h1 class='tele'>"+ item.mobile +"</h1>"
                +      "<p class='address'>"+ item.provincestr + item.citystr + item.districtstr + item.address +"</p>"
                +      "<div class='clear'></div>"
                +      "</div>"
                +      "<div class='address_footer' id='c"+ item.goodsAddressUuid +"'>"
                +      "<div class='add_default'>";


            if(item.isDefault == 1){

                address2 = "<p class='defaulticon checked' id='i"+ item.goodsAddressUuid +"' data-defult='"+ item.goodsAddressUuid +"' data-checked='"+ item.isDefault +"'>"
                    +      "<i class='iconfont deficon' style='display:block;'>&#xe662;</i>"
                    +      "</p>"
                    +      "<p class='default' style='color:#d7282d;'>设为默认</p>";
            }else{
                address2 = "<p class='defaulticon' id='i"+ item.goodsAddressUuid +"' data-defult='"+ item.goodsAddressUuid +"' data-checked='"+ item.isDefault +"'>"
                    +      "<i class='iconfont deficon'>&#xe662;</i>"
                    +      "</p>"
                    +      "<label class='default'>设为默认</label>";
            }


            address3 = "</div>"
                +      "<P class='dele' data-del='"+ item.goodsAddressUuid +"'>"
                +      "<i class='iconfont dicon'>&#xe654;</i>删除</P>"
                +      "<P class='edit' data-edit='"+ item.goodsAddressUuid +"'>"
                +      "<i class='iconfont eicon'>&#xe669;</i>编辑</P>"
                +      "<div class='clear'></div>"
                +      "</div>";
            address += address1 +address2 +address3;

        });
        $(".address_all").append(address);
        if(orders!=null&&orders!=""&&orders!=undefined){
        	$(".dele").each(function(i,item){
        		$(item).hide();
        	})
        }


        //删除地址
        $(".dele").click(function(){

            var del = $(this).attr("data-del");
            $(".black_box").fadeIn(500);
            $(".dele").attr("data-del", del) ;

        });

        //取消删除
        $("#cancle").click(function(){
            $(".black_box").fadeOut(500);
        });
        //确认删除
        $("#delbtn").click(function(){

            var del = $(".dele").attr("data-del");
            $.ajax({
                type: 'POST',
                url: deleteAddress_url,
                dataType : "json" ,
                data: {
                    goodsAddressUuid:del
                },

                success:
                    function(data){


                        window.location.reload();
                        $("#" + del +"").remove();
                        $("#c" + del +"").remove();



                    },
                error:
                    function(data){

                    }

            });
        });

        //编辑修改地址
        $(".edit").click(function(){
            var pid= $(this).attr("data-edit");
            if(orders!=null&&orders!=""&&orders!=undefined&&shop!=1){
            	window.location.href = addressDetailWx_path + '?' +orders + "?&openid=" + openid + '?addressUuid=' + pid;
            }else if(shop==1){
            	window.location.href = addressDetailWx_path + '?' +orders + "?&openid=" + openid + '?addressUuid=' + pid + '?1';
            }else{
            	window.location.href = addressDetailWx_path + "?&openid=" + openid + '?addressUuid=' + pid;
            }
        });

        $(".arrow").click(function(){
        	if(orders!=null&&orders!=""&&orders!=undefined&&shop!=1){
    			window.location.href = orderBuyWx_path + '?' +orders + '?&openid=' +openid;
    		}else if(shop==1){
    			window.location.href = orderUpWx_path + '?' +orders + '?&openid=' +openid;
    		}else{
    			window.location.href = mineout_path + '?&openid=' +openid;
    		}
        });



        ////设为默认地址
        //$(".defaulticon").click(function(){
        //
        //    $(".defaulticon").removeClass("checked");
        //    $(".defaulticon").next().css("color","#666");
        //    $(".defaulticon").children("i").css("display","none");
        //
        //    var del = $(this).attr("data-defult");
        //    $.ajax({
        //        type: 'POST',
        //        url: orderDefult_url,
        //        dataType : "json" ,
        //        data: {
        //            goodsAddressUuid:del
        //        },
        //
        //        success:
        //            function(data){
        //
        //
        //                $("#i" + del +"").addClass("checked");
        //                $("#i" + del +"").next().css("color","#d7282d");
        //                $("#i" + del +"").children("i").css("display","block");
        //
        //
        //
        //
        //            },
        //        error:
        //            function(data){
        //
        //            }
        //
        //    });
        //
        //
        //
        //
        //});


        //设为默认地址
        $(".add_default").click(function(){
        	$(".defaulticon").each(function(i,item){
	            $(item).removeClass("checked");
	            $(item).next().css("color","#666");
	            $(item).children("i").css("display","none");
            })
        	var del = $(this).find("p").eq(0).attr("data-defult");
            $.ajax({
                type: 'POST',
                url: defaultAddressOpen_url,
                dataType : "json" ,
                data: {
                	openid:openid,
                    goodsAddressUuid:del
                },

                success:
                    function(data){
                		if(data==1){
                			$("#i" + del +"").addClass("checked");
                			$("#i" + del +"").next().css("color","#d7282d");
                			$("#i" + del +"").children("i").css("display","block");
                		}
                    },
                error:
                    function(data){

                    }
            });
        });
    }

});