function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

$(function(){
	var day=window.location.href.split("?")[1];

    //显示从cookie中获取的用户名
    $(".username").text(getCookie("userName"));

    //获取登录后进入的订单管理的链接
    var ajaxURL =  sales_AllDDList;
    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {
        	dayStr:day
        },
        success:
            function(data){

                //显示订单管理第一页数据
                getOrderList(data.shopAgentFinancials);

            },
        error:
            function(data,textStatus){

                //若没有登录提示登录
                alert("请先登录后再访问！");

                //跳转到登录页链接
                window.location.href= url_login;
            }

    });



    var tableTemp = "";

    //解析json数据，页面显示
    function getOrderList(info){

        var table1 = "";
        var total = 0;
        var totalS = 0;
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
                transfer = item.transferFee;
            }
            if(item.payType == 1){
                paytype = "支付宝";
            }else if(item.payType == 2){
                paytype = "微信";
            }





            table1 += "<table class='table2'>"
                +    "<tr>"
                +    "<td class='col0'><input type='checkbox' data-shopId='"+item.shopAgentFinancialId+"'></td>"
                +    "<td class='col1'>"+ item.code +"</td>"
                +    "<td class='col2'>"+ item.shopName +"</td>"
                +    "<td class='col3' >"+ item.dayStr +"</td>"
                +    "<td class='col4'>"+ amount +"</td>"
                +    "<td class='col5'>"+ service +"</td>"
                +    "<td class='col6'>"+ transfer +"</td>"
                +    "<td class='col7'>"+ statu +"</td>"
                +    "</tr>"
                +    "</table>";
            
            total=total+item.amount;
            totalS=totalS+item.serviceFee;
            
        });

        $(".tablebox").append(table1);   //把所有数据放到class为productmenu里

        tableTemp = "<table class='table2'>"
            +     "<tr class='table2title'>"
            +     "<td colspan='9' style='text-align: left;'>日期:"+day+"&nbsp;&nbsp;&nbsp;&nbsp;总金额:"+total+"元&nbsp;&nbsp;&nbsp;&nbsp;手续费:"+totalS+"元</td>"
            +     "</tr>"
            +    "</table>";
        $(".tablebox").append(tableTemp);

        

    }
    
    $(".renew").click(function(){
    	var ajaxData="";
    	$("input[type=checkbox]:checked").each(function(index,element){
    		var shopId = $(this).attr("data-shopId");
    		ajaxData=ajaxData+"&shopAgentFinancials["+index+"].status="+1+"&shopAgentFinancials["+index+"].shopAgentFinancialId="+shopId;
    	});
    	$.ajax({
            type: 'POST',
            url: updateShop,
            dataType : "json" ,
            data:ajaxData,
            success:
                function(data){
            		if(data==1){
            			window.location.reload()
            		}else{
            			alert("请重新选择");
            		}
                },
            error:
                function(data,textStatus){
                    //若没有登录提示登录
                    alert("修改失败");
                }

        });
    })
    
    $(".renew1").click(function(){
    	var ajaxData="";
    	$("input[type=checkbox]:checked").each(function(index,element){
    		var shopId = $(this).attr("data-shopId");
    		ajaxData=ajaxData+"&shopAgentFinancials["+index+"].status="+0+"&shopAgentFinancials["+index+"].shopAgentFinancialId="+shopId;
    	});
    	$.ajax({
            type: 'POST',
            url: updateShop,
            dataType : "json" ,
            data:ajaxData,
            success:
                function(data){
            		if(data==1){
            			window.location.reload()
            		}else{
            			alert("请重新选择");
            		}
                },
            error:
                function(data,textStatus){
                    //若没有登录提示登录
                    alert("修改失败");
                }
        });
    })





});