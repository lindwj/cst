$(function(){$.ajax({type:"post",url:orderControl_url,dataType:"json",data:{},success:function(data){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data){error("请先登录后再访问！");window.location.href=login_url}});$("#order1").click(function(){$(".class").removeClass("style1");$(".menua").removeClass("style2");$(this).addClass("style1");$(this).children("a").addClass("style2");$(".orders").empty();$.ajax({type:"post",url:orderControl_url,dataType:"json",data:{status:100},success:function(data){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data){}})});$("#order2").click(function(){$(".class").removeClass("style1");$(".menua").removeClass("style2");$(this).addClass("style1");$(this).children("a").addClass("style2");$(".orders").empty();$.ajax({type:"post",url:orderControl_url,dataType:"json",data:{status:9},success:function(data){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data){}})});$("#order3").click(function(){$(".class").removeClass("style1");$(".menua").removeClass("style2");$(this).addClass("style1");$(this).children("a").addClass("style2");$(".orders").empty();$.ajax({type:"post",url:orderControl_url,dataType:"json",data:{status:1},success:function(data){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data){}})});$("#order4").click(function(){$(".class").removeClass("style1");$(".menua").removeClass("style2");$(this).addClass("style1");$(this).children("a").addClass("style2");$(".orders").empty();$.ajax({type:"post",url:orderControl_url,dataType:"json",data:{status:2},success:function(data){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data){}})});$("#nextmove").click(function(){var curPage=$("#curpage").html()-0+1;var allPage=$("#allpage").html();if(curPage>allPage){error("已经是最后一页了");return false}$(".orders").empty();$.ajax({type:"POST",url:orderControl_url,dataType:"json",data:{"page.currentPage":curPage,"page.totalPage":allPage},success:function(data,textStatus){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data,textStatus){alert("服务器异常，请稍后再试")}})});$("#prevmove").click(function(){var curPage=$("#curpage").html()-0-1;var allPage=$("#allpage").html();if(curPage<1){error("已经第一页了");return false}$(".orders").empty();$.ajax({type:"POST",url:orderControl_url,dataType:"json",data:{"page.currentPage":curPage,"page.totalPage":allPage},success:function(data,textStatus){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data,textStatus){alert("服务器异常，请稍后再试")}})});$("#jump2").click(function(){var curPage=$("#curpage").html()-0+1;var allPage=$("#allpage").html();if(curPage>allPage){error("已经是最后一页了");return false}$(".orders").empty();$.ajax({type:"POST",url:orderControl_url,dataType:"json",data:{"page.currentPage":curPage,"page.totalPage":allPage},success:function(data,textStatus){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data,textStatus){alert("服务器异常，请稍后再试")}})});$("#jump1").click(function(){var curPage=$("#curpage").html()-0-1;var allPage=$("#allpage").html();if(curPage<1){error("已经第一页了");return false}$(".orders").empty();$.ajax({type:"POST",url:orderControl_url,dataType:"json",data:{"page.currentPage":curPage,"page.totalPage":allPage},success:function(data,textStatus){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data,textStatus){alert("服务器异常，请稍后再试")}})});$("#goBtn").click(function(){var allPage=$("#allpage").html();var gotoPage=$("#gotoPageNo").val().trim();if(gotoPage<1){error("页数至少从1开始");return false}else{if(gotoPage>allPage){error("一共只有"+allPage+"页");return false}}$(".orders").empty();$.ajax({type:"POST",url:orderControl_url,dataType:"json",data:{"page.currentPage":gotoPage,"page.totalPage":allPage},success:function(data,textStatus){getorders(data.ordersList);$("#allpage").text(data.page.totalPage);$("#curpage").text(data.page.currentPage)},error:function(data,textStatus){alert("服务器异常，请稍后再试")}})});function getorders(info){var order="";var order1="";$.each(info,function(i,item){order1="<table class='table' cellpadding='0' cellspacing='0'>"+"<thead>"+"<tr>"+"<th colspan='10' class='title'>"+item.createDatestr+"&nbsp;&nbsp;&nbsp;&nbsp;订单号:&nbsp;"+item.code+"</th>"+"</tr>"+"</thead>"+"<tbody>";if(item.productUuid!=0){order1=order1+"<tr>"+"<th class='col7'>"+"<img src='"+item.pic+"'>"+"<span>"+item.subject+"</span>"+"</th>"+"<th class='col8'>"+item.price.toFixed(2)+"</th>"+"<th class='col9'>"+item.capacity+"</th>"+"<th class='col10'>"+item.totalFromBdh.toFixed(2)+"<br>(含运费)</th>";if(item.status==9){order1=order1+"<th class='col11'>待付款</th>"+"<th class='col12'>"+"<a class='pay' data-code='"+item.code+"'>付款</a>"+"<p class='fdetail' data-code='"+item.code+"'>查看详情</p>"+"</th>"+"</tr>"}else{if(item.status==2){order1=order1+"<th class='col11'>已签收</th>"+"<th class='col12'>"+"<p class='fdetail' data-code='"+item.code+"'>查看详情</p>"+"</th>"+"</tr>"}else{if(item.status==1){order1=order1+"<th class='col11'>待收货</th>"+"<th class='col12'>"+"<p class='fdetail' data-code='"+item.code+"'>查看详情</p>"+"</th>"+"</tr>"}}}}else{var total=item.totalFromBdh.toFixed(2);var state=item.status;var code=item.code;var xx=item.ordersDetailList.length;$.each(item.ordersDetailList,function(j,jtem){if(xx==1){order1=order1+"<tr>"+"<th class='col7'>"+"<img src='"+jtem.pic+"'>"+"<span>"+jtem.productName+"</span>"+"</th>"+"<th class='col8'>"+jtem.price.toFixed(2)+"</th>"+"<th class='col9'>"+jtem.capacity+"</th>"+"<th class='col10'>"+total+"<br>(含运费)</th>";if(state==9){order1=order1+"<th class='col11'>待付款</th>"+"<th class='col12'>"+"<a class='pay' data-code='"+code+"'>付款</a>"+"<p class='fdetail' data-code='"+code+"'>查看详情</p>"+"</th>"+"</tr>"}else{if(state==2){order1=order1+"<th class='col11'>已签收</th>"+"<th class='col12'>"+"<p class='fdetail' data-code='"+code+"'>查看详情</p>"+"</th>"+"</tr>"}else{if(state==1){order1=order1+"<th class='col11'>待收货</th>"+"<th class='col12'>"+"<p class='fdetail' data-code='"+code+"'>查看详情</p>"+"</th>"+"</tr>"}}}}else{if(j==0){order1=order1+"<tr>"+"<th class='col7'>"+"<img src='"+jtem.pic+"'>"+"<span>"+jtem.productName+"</span>"+"</th>"+"<th class='col8'>"+jtem.price.toFixed(2)+"</th>"+"<th class='col9'>"+jtem.capacity+"</th>"+"<th class='col10' rowspan='"+xx+"'>"+total+"<br>(含运费)</th>";if(state==9){order1=order1+"<th class='col11' rowspan='"+xx+"'>待付款</th>"+"<th class='col12' rowspan='"+xx+"'>"+"<a class='pay' data-code='"+code+"'>付款</a>"+"<p class='fdetail' data-code='"+code+"'>查看详情</p>"+"</th>"+"</tr>"}else{if(state==2){order1=order1+"<th class='col11' rowspan='"+xx+"'>已签收</th>"+"<th class='col12' rowspan='"+xx+"'>"+"<p class='fdetail' data-code='"+code+"'>查看详情</p>"+"</th>"+"</tr>"}else{if(state==1){order1=order1+"<th class='col11' rowspan='"+xx+"'>待收货</th>"+"<th class='col12' rowspan='"+xx+"'>"+"<p class='fdetail' data-code='"+code+"'>查看详情</p>"+"</th>"+"</tr>"}}}}else{order1=order1+"<tr>"+"<th class='col7'>"+"<img src='"+jtem.pic+"'>"+"<span>"+jtem.productName+"</span>"+"</th>"+"<th class='col8'>"+jtem.price.toFixed(2)+"</th>"+"<th class='col9'>"+jtem.capacity+"</th>"+"</tr>"}}})}order1=order1+"</tbody>"+"</table>";order=order+order1});$(".orders").append(order);$(".fdetail").click(function(){var codes=$(this).attr("data-code");window.location.href=orderDetail_path+codes});$(".pay").click(function(){var code=$(this).attr("data-code");window.location.href=orderPay_url+code})}});