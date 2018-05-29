/**
 * 
 */
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
    
    var ajax_orderapplication=order_getlist;
    
    $.ajax({
    	type: 'POST',
        url: ajax_orderapplication,
        dataType : "json" ,
        data:{},
        success:
            function(data,textStatus){
        		if(data==null){
        			alert("没有订单！");
        		}else{
        			getorderlist(data);
        			$("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);
        		}
            },
        error:
            function(data,textStatus){
        		alert("请先登录后再访问！");
        		window.location.href= url_login;
            }
    })
    
    $("#searchbtn").click(function() {
    	search();
    });
    
     //下一页
    $("#nextmove").click(function(){
        //获取账号和密码
        //查询条件
        var begin = $("#date1").val().trim();
        var end = $("#date2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 + 1;
        var allPage = $("#allpage").html();

        if(curPage > allPage){
            $('#errorm3').fadeIn(300);
            $('#errorm3').fadeOut(5000);
        }
        $(".order").empty();
        $.ajax({
            type: 'POST',
            url: ajax_orderapplication,
            dataType : "json" ,
            data:
            {
                beginTime:begin,
                endTime:end,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){


            	getorderlist(data);

                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);
                },
            error:
                function(data,textStatus){
                    alert("服务器异常，请稍后再试");
                }

        });

    });
    //上一页
    $("#prevmove").click(function(){
    	$(".order").empty();
        var begin = $("#date1").val().trim();
        var end = $("#date2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 - 1;
        var allPage = $("#allpage").html();
        $.ajax({
            type: 'POST',
            url: ajax_orderapplication,
            dataType : "json" ,
            data:
            {
                beginTime:begin,
                endTime:end,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){
            		getorderlist(data);
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
        var begin = $("#date1").val().trim();
        var end = $("#date2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html() - 0 + 1;
        var allPage = $("#allpage").html();
        var gotoPage = $("#gotoPageNo").val().trim();
        $(".order").empty();
        $.ajax({
            type: 'POST',
            url: ajax_orderapplication,
            dataType : "json" ,
            data:
            {
                beginTime:begin,
                endTime:end,
                status:sta,
                'page.currentPage':gotoPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){
            		getorderlist(data);
                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);
                },
            error:
                function(data,textStatus){
                    alert("服务器异常，请稍后再试");
                }

        });
    });
    function getorderlist(info){
    	var table = "";
        var tableTemp = "";
        var statu="";
        var price="";
        
    	$.each(info.ordergoodList,function(i,item){
    		if(item.status==0){
    			statu="草稿";
    		}else if(item.status==1){
    			statu="已提交";
    		}else if(item.status==2){
    			statu="审核中";
    		}else if(item.status==3){
    			statu="已发货";
    		}else if(item.status==4){
    			statu="订单完成";
    		}else if(item.status==-1){
    			statu="拒绝";
    		}
    		
    		tableTemp = "<table class='table2'>"
                +     		"<tr class='table2title'>"
                +     		"<td style='text-align: left' colspan='8'>"+"提交时间："+ item.subDateStr +"&nbsp;&nbsp;&nbsp;&nbsp;订单号："+ item.code +"</td>"
                +     		"</tr>";
    		
    		if(item.ordergoodsProducts != null){
            $.each(item.ordergoodsProducts,function(n,ntem){
            	var unitval="";
            	var numval=0;
            	if(ntem.unit==1){
            		unitval="袋";
            	}else if(ntem.unit==2){
            		unitval="盒";
            	}else if(ntem.unit==3){
            		unitval="箱";
            	}else if(ntem.unit==4){
            		unitval="饼";
            	}else if(ntem.unit==5){
            		unitval="瓶";
            	}
            	if(ntem.updateNum!=null){
            		numval=ntem.updateNum;
            	}else{
            		numval="";
            	}
            	if(n==0){
            	tableTemp = tableTemp + "<tr>"
            	+				"<td class='col1'>"+ntem.productName+"</td>"
            	+				"<td class='col2'>"+ntem.productPrice+"</td>"
            	+				"<td class='col3'>"+unitval+"</td>"
            	+				"<td class='col4'>"+ntem.applyNum+"</td>"
            	+				"<td class='col6' valign='top'>"+item.totalPrice+"<br>(不含运费)</td>"
            	+				"<td class='col7' valign='top'>"+statu				
            	+				"</td>"
            	+				"<td class='col8' valign='top'>"
            	if(item.status==0){
            	tableTemp = tableTemp 
            	+				"<input class='changebtn sub' id='"+item.status+"' type='button' value='提交'>"
            	+				"<input class='changebtn del' id='"+item.code+"' type='button' value='删除'>"
            	}else if(item.status==1){
            	tableTemp = tableTemp
            	+				"<input class='changebtn backout' id='"+item.status+"' type='button' value='撤回'>"
            	}else if(item.status==-1){
            	tableTemp = tableTemp
            	+				"<input class='changebtn del' id='"+item.code+"' type='button' value='删除'>"
            	}
            	tableTemp = tableTemp
            	+				"<input class='changebtn detail' type='button' id='"+item.code+"' value='订单详情'>"
                +				"</td>"
                +				"</tr>"
            	}else if(n>0){
            		tableTemp = tableTemp + "<tr>"
            		+				"<td class='col1'>"+ntem.productName+"</td>"
                	+				"<td class='col2'>"+ntem.productPrice+"</td>"
                	+				"<td class='col3'>"+unitval+"</td>"
                	+				"<td class='col4'>"+ntem.applyNum+"</td>"
                    +              	"<td class='col6' valign='top'>"
                    +				"</td>"
                    +              	"<td class='col7' valign='top'>"
                    +				"</td>"
                    +              	"<td class='col8' valign='top'>"
                    +              	"</td>"
                    +				"</tr>";
            	}
            	})
            }else{
            	alert("没有订单");
            }
    		 tableTemp = tableTemp + "</table>" ;
             table = table + tableTemp ;
    	});
    	$(".order").append( table );
    	
        $(".detail").click(function(){
        	var ordercode=$(this).attr("id");
            window.location.href= link_orderDetail+'?code='+ordercode;
        });
        
        $(".sub").click(function(){
        	var ordercode=$(this).next().attr("id");
        	var orderstu=$(this).attr("id");
        	$.ajax({
        		type: 'POST',
                url: order_updateStatus,
                dataType : "json" ,
                data:
                {
                    code:ordercode,
                    status:orderstu
                },
                success:
                    function(data,textStatus){
                		window.location.reload();
                    },
                error:
                    function(data,textStatus){
                        alert("服务器异常，请稍后再试");
                    }
        	})
        })
        $(".backout").click(function(){
        	var ordercode=$(this).next().attr("id");
        	var orderstu=$(this).attr("id");
        	$.ajax({
        		type: 'POST',
                url: order_updateStatus,
                dataType : "json" ,
                data:
                {
                    code:ordercode,
                    status:orderstu
                },
                success:
                    function(data,textStatus){
                		window.location.reload();
                    },
                error:
                    function(data,textStatus){
                        alert("服务器异常，请稍后再试");
                    }
        	})
        })
        $(".del").click(function(){
        	$('#black1').fadeIn();
        	var ordercode=$(this).attr("id");
        	$("#surebtn").click(function(){
        	$.ajax({
        		type: 'POST',
                url: order_delete,
                dataType : "json" ,
                data:
                {
                    code:ordercode
                },
                success:
                    function(data,textStatus){
                	$('#black1').fadeOut();
                		search();
                    },
                error:
                    function(data,textStatus){
                        alert("服务器异常，请稍后再试");
                    }
        	})
        	})
        	$('#canclebtn').click(function(){
                $('#black1').fadeOut();
            });
        })
    }
    
    function search(){
    	var begin = $("#date1").val().trim();
        var end = $("#date2").val().trim();
        var sta = $("#item-status").val();
        var curPage = $("#curpage").html();
        var allPage = $("#allpage").html();
        $(".order").empty();
        $.ajax({
            type: 'POST',
            url: ajax_orderapplication,
            dataType : "json" ,
            data:
            {
                beginTime:begin,
                endTime:end,
                status:sta,
                'page.currentPage':curPage,
                'page.totalPage':allPage

            },

            success:
                function(data,textStatus){
            		getorderlist(data);
                    $("#allpage").text(data.page.totalPage);
                    $("#curpage").text(data.page.currentPage);
                },
            error:
                function(data,textStatus){
                    alert("服务器异常，请稍后再试");
                }

        });
    }
});