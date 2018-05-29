function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

$(function(){
	var ajaxURL_parameter=order_getparameter;
    var ajaxURL_product=order_getproduct;
    var ajaxURL_price=order_getprice;
    var ajaxURL_productlist=order_getproductlist;
    var ajaxURL_orderproduct=order_product;
    var ajaxURL_orderproductdraft=order_productdraft;
    var updateorder="";
    $(".username").text(getCookie("userName"));
	
    $("#applybtn").click(function(){
    	$("#p3").val(1);
    	$("#unit_price").val("");
    	$("#num").val("");
    	$.ajax({
            type: 'POST',
            url: ajaxURL_parameter,
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
        
        $.ajax({
        	type: 'POST',
            url: ajaxURL_productlist,
            dataType : "json" ,
            data:{},

            success:
                function(data,textStatus){
            		getProductList(data);
                },
            error:
                function(data,textStatus){
            		alert("请先登录后再访问！");
            		window.location.href= url_login;
                }
        });
        $("#hidebox").fadeIn();
    });
    $("#canclebtn").click(function(){
        $("#hidebox").fadeOut();
    })
    
    function getParameter(info){
    	$("#p1").empty();
    	var select_parameter="<option value='"+0+"' selected = 'selected'>"+"请选择"+"</option>";
    	
    	$.each(info,function(i,item){
    		select_parameter=select_parameter+"<option value='"+item.parameterId+"'>"+item.parameterName+"</option>";
    	});
    	$("#p1").append(select_parameter);
    }
    
    function getProductList(info){
    	$("#p2").empty();
    	var select_productlist="";
    	$.each(info,function(i,item){
    		if(i==0){
    			select_productlist=select_productlist+"<option value='"+item.productUuid+"' selected = 'selected'>"+item.name+"</option>";
    		}else{
    			select_productlist=select_productlist+"<option value='"+item.productUuid+"'>"+item.name+"</option>";
    		}
    	})
    	$("#p2").append(select_productlist);
    }
    
    $("#p1").change(function(){
    	var para1=$(this).children('option:selected').val();
    	$.ajax({
    		type:'POST',
    		url:ajaxURL_product,
    		dataType:"json",
    		data:
    		{
    			typeId:para1
    		},
    		success:
    			function(data,textStatus){
    				$("#unit_price").val(0);
    				getProduct(data);
    			},
    		error:
    			function(data,textStatus){
    				alert("请先登录后再访问！");
    				window.location.href= url_login;
    			}
    	});
    });
    
    var select_product="";
    var product1="";
    function getProduct(info){
    	$("#p2").empty();
    	select_product="";
    	$.each(info,function(i,item){
    		select_product=select_product+"<option value='"+item.productUuid+"'>"+item.name+"</option>";
    	});
    	$("#p2").append(select_product);
    	product1=$("#p2").val();
    	if(product1!=null){
    		$.ajax({
    			type:'POST',
    			url:ajaxURL_price,
    			dataType:"json",
    			data:{
    				productUuid:product1,
    			},
    			success:
    				function(data,textStatus){
    				getPrice(data);
    			},
    			error:
    				function(data,textStatus){
    					alert("请先登录后再访问！");
    					window.location.href= url_login;
    			}
    		});
    	}else{
    		$("#unit_price").val(0);
    	}
    }
    
    $("#p2").change(function(){
    	product1=$(this).children('option:selected').val();
    	if(product1!=null){
    	$.ajax({
    		type:'POST',
    		url:ajaxURL_price,
    		dataType:"json",
    		data:{
    			productUuid:product1,
    		},
    		success:
    			function(data,textStatus){
    				getPrice(data);
    		},
    		error:
    			function(data,textStatus){
    				alert("请先登录后再访问！");
    				window.location.href= url_login;
    		}
    	});
    	}else{
    		$("#unit_price").val(0);
    	}
    });
    
    function getPrice(info){
    	if(info!=null){
    		$("#unit_price").val(0);
        	$("#unit_price").val(info.costPrice);
    	}else{
    		$("#unit_price").val(0);
    	}
    }
    var addproduct="";
    $("#surebtn").click(function(){
    		var product_text=$("#p2").find("option:selected").text();
    		var unit_text=$("#p3").find("option:selected").text();
    		var parameter_val=$("#p1").val();
    		var product_val=$("#p2").val();
    		var unit_val=$("#p3").val();
    		var price_val=$("#unit_price").val();
    		var num=$("#num").val();
    		var ex = /^\d+$/;
    		addproduct=
    			"<tr class='"+parameter_val+"'>"
    			+"<td class='col1' value='"+product_val+"'>"+product_text+"</td>"
    			+"<td class='col2' value='"+price_val+"'>"+price_val+"</td>"
    			+"<td class='col3' value='"+unit_val+"'>"+unit_text+"</td>"
    			+"<td class='col4' value='"+num+"'>"+num+"</td>"
    			+"<td class='col5'>"+num*price_val+"</td>"
    			+"<td class='col6'>"
    			+"<input class='delbtn' type='button' value='删除'>"
    			+"</td>"
    			+"</tr>";
    		if (ex.test(num)) {
    			$(".tbody").append(addproduct);
            }
        $(".delbtn").click(function(){
        	$(this).parents("tr").remove();
        })
        $(".changebtn").click(function(){
        	$.ajax({
            	type: 'POST',
                url: ajaxURL_productlist,
                dataType : "json" ,
                data:{},
                async:false,
                success:
                    function(data,textStatus){
                		getProductList(data);
                    },
                error:
                    function(data,textStatus){
                		alert("请先登录后再访问！");
                		window.location.href= url_login;
                    }
            });
        	$("#p1").val($(this).parents("tr").attr("class"));
        	$("#p2").val($(this).parents("tr").find(".col1").attr("value"));
        	$("#p3").val($(this).parents("tr").find(".col3").attr("value"));
        	$("#unit_price").val($(this).parents("tr").find(".col2").attr("value"));
        	$("#num").val($(this).parents("tr").find(".col4").attr("value"));
        	updateorder=$(this).parents("tr");
        	$("#hidebox").fadeIn();
        })
        if (ex.test(num)) {
        	$("#hidebox").fadeOut();
        }else{
        	$("#errorm3").fadeIn(300);
        	$('#errorm3').fadeOut(5000);
        }
    })
    
    $("#savebtn").click(function(){
    	var trList=$(".tbody").children("tr");
    	var product_value="";
    	var unit_value="";
    	var num_value="";
    	var price_value="";
    	var orderproduct_value="";
    	var productname="";
    	var price=0;
    	for(var i=0;i<trList.length;i++){
    		orderproduct_value = orderproduct_value + "&";
    		product_value=trList.eq(i).find("td").eq(0).attr("value");
    		unit_value=trList.eq(i).find("td").eq(2).attr("value");
    		price_value=trList.eq(i).find("td").eq(1).text();
    		num_value=trList.eq(i).find("td").eq(3).attr("value");
    		
    		orderproduct_value=orderproduct_value
    		+"&ordergoodsProducts["+i+"].productUuid="+product_value
    		+"&ordergoodsProducts["+i+"].unit="+unit_value
    		+"&ordergoodsProducts["+i+"].applyNum="+num_value;
    		price=price+parseInt(trList.eq(i).find("td").eq(4).text());
		}
    	orderproduct_value=orderproduct_value+"&totalPrice="+price;
    	console.log(orderproduct_value);
    	$.ajax({
    		type: 'POST',
            url: ajaxURL_orderproduct,
            dataType : "json" ,
            data:orderproduct_value,
            success:
                function(data,textStatus){
            		if(data == '100'){
            			window.location.href=link_orderApplication;
            		}
            		if(data == '101'){
            			alert("没有数据！");
            		}
                },
            error:
                function(data,textStatus){
            		alert("服务器异常，请稍后再试");
                }
    	});
    });
    
    $("#draftbtn").click(function(){
    	var trList=$(".tbody").children("tr");
    	var product_value="";
    	var unit_value="";
    	var num_value="";
    	var orderproduct_value="";
    	var price=0;
    	for(var i=0;i<trList.length;i++){
    		orderproduct_value = orderproduct_value + "&";
    		product_value=trList.eq(i).find("td").eq(0).attr("value");
    		unit_value=trList.eq(i).find("td").eq(2).attr("value");
    		num_value=trList.eq(i).find("td").eq(3).attr("value");
    		orderproduct_value=orderproduct_value
    		+"ordergoodsProducts["+i+"].productUuid="+product_value
    		+"&ordergoodsProducts["+i+"].unit="+unit_value
    		+"&ordergoodsProducts["+i+"].applyNum="+num_value
    		price=price+parseInt(trList.eq(i).find("td").eq(4).text());
		}
    	orderproduct_value=orderproduct_value+"&totalPrice="+price;
    	$.ajax({
    		type: 'POST',
            url: ajaxURL_orderproductdraft,
            dataType : "json" ,
            data:
            	orderproduct_value,
            success:
                function(data,textStatus){
            		if(data == '100'){
            			window.location.href=link_orderApplication;
            		}
            		if(data == '101'){
            			alert("没有数据！");
            		}
                },
            error:
                function(data,textStatus){
            		alert("服务器异常，请稍后再试");
                }
    	});
    });
});