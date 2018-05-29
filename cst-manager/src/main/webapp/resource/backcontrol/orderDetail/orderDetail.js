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
	var ordercode=window.location.href.split("=")[1];
	$.ajax({
        type: 'POST',
        url: order_detail,
        dataType : "json" ,
        data:
        {
            code:ordercode
        },
        success:
            function(data,textStatus){
        	var table="";
        	table="<th>订单信息</th>"
        	 +"<tr>"
             +"<td>"
             +"<div class='message'>"    
             +"<h1>收货人:</h1>"        
             +"<p>"+testnull(data.receiveMan)+"</p>"            
             +"</div>"            
             +"<br>"        
             +"<div class='message'>"        
             +"<h1>手机:</h1>"        
             +"<p>"+testnull(data.phone)+"</p>"            
             +"</div>"            
             +"<br>"        
             +"<div class='message'>"        
             +"<h1>订单号:</h1>"        
             +"<p>"+testnull(data.code)+"</p>"            
             +"</div>"            
             +"<br>"        
             +"<div class='message'>"    
             +"<h1>收货地址:</h1>"
             +"<p>"+testnull(data.address)+"</p>"            
             +"</div>"            
             +"<br>"        
             +"<div class='message'>"        
             +"<h1 style='height:78px;'>审批:</h1>"        
             +"<p>市场部："+testnull(data.marketRemark)+"&nbsp;&nbsp;"+testnull(data.marketTimeStr)+"</p><br>" 
             +"<p>产品中心："+testnull(data.pcenterRemark)+"&nbsp;&nbsp;"+testnull(data.pcenterTimeStr)+"</p><br>"
             +"<p>财务部："+testnull(data.accountantRemark)+"&nbsp;&nbsp;"+testnull(data.accountantTimeStr)+"</p><br>"
             +"<p>库管："+testnull(data.storeRemark)+"&nbsp;&nbsp;"+testnull(data.storeTimeStr)+"</p>"            
             +"</div>"            
             +"</td>"
             +"</tr>";
        	$(".table").append(table);
            },
        error:
            function(data,textStatus){
                alert("服务器异常，请稍后再试");
            }
    })
    $.ajax({
    	type: 'POST',
    	url: logistics_detail,
    	dataType : "json" ,
    	data:
    	{
    		code:ordercode
    	},
    	success:
    		function(data,textStatus){
    		getlogistics(data.logistics);
    	},
    	error:
    		function(data,textStatus){
    		alert("服务器异常，请稍后再试");
    	}
    })
    //获取物流信息
    function getlogistics(info){
		$.each(info,function(i,item){
			var table1="";
    		var signStu="";
    		var stu="";
    		if(item.logisticsStatus==0){
    			signStu="签收";
    			stu="已发货";
    		}else if(item.logisticsStatus==1||item.logisticsStatus==2){
    			signStu="查看";
    			stu="已签收<br>运费:"+item.sendMoney+"";
    		}
    		table1="<table class='table2'>"
    		+"<tr class='table2title'>"
    		+"<td style='text-align: left' colspan='8'>发货时间:"+item.logisticsTimeStr+"&nbsp;&nbsp;&nbsp;&nbsp;物流公司:"+item.logisticsBuiness+"&nbsp;&nbsp;&nbsp;&nbsp;物流单号:"+item.logisticsCode+"&nbsp;&nbsp;&nbsp;&nbsp;联系人:"+testnull(item.linkMan)+"&nbsp;&nbsp;"+testnull(item.logisticsPhone)+""
    		if(item.logisticsStatus==0){
    			table1=table1
        		+"<button type='button' class='btn btn-default status"+item.logisticsStatus+"' data-id='"+item.logisticsId+"'>"+signStu+"</button>"
                +"</td>"
                +"</tr>";
    		}else{
    			table1=table1
    			+"<button type='button' class='btn btn-default status"+item.logisticsStatus+"' data-id='"+item.logisticsId+"'>"+signStu+"</button>"
    			+"<button type='button' style='margin-right:10px;' class='btn btn-default status0' data-id='"+item.logisticsId+"'>修改</button>"
    			+"</td>"
    			+"</tr>";
    		}
    		$.each(item.logisticsProducts,function(n,ntem){
    			var unitval="";
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
    			if(n==0){
    				table1=table1+"<tr>"
    				+"<td class='col1'>"+ntem.productName+"</td>"
                    +"<td class='col2'>"+ntem.productPrice+"</td>"
                    +"<td class='col3'>"+unitval+"</td>"
                    +"<td class='col4'>"+ntem.logisticsNum+"</td>"
                    +"<td class='col6' valign='top'>"+item.totalPrice+"<br>(不含运费)</td>"
                    +"<td class='col7' valign='top'>"+stu+"</td>"
                    +"</tr>";
    			}else if(n>0){
    				table1=table1+"<tr>"
    				+"<td class='col1'>"+ntem.productName+"</td>"
                    +"<td class='col2'>"+ntem.productPrice+"</td>"
                    +"<td class='col3'>"+unitval+"</td>"
                    +"<td class='col4'>"+ntem.logisticsNum+"</td>"
                    +"<td class='col6' valign='top'></td>"
                    +"<td class='col7' valign='top'></td>"
                    +"</tr>";
    			}
    		})
    		table1=table1+"</table>";
    		$(".detail").append(table1);
		})
		var length=$(".status0").length;
		var length1=$(".status1").length;
		for(var i=0;i<length;i++){
			$(".status0").eq(i).click(function(){
				window.location.href=link_signDetail+"?logisticsId="+$(this).attr("data-id");
			})
		}
		
		for(var i=0;i<length1;i++){
			$(".status1").eq(i).click(function(){
				window.location.href=link_signView+"?logisticsId="+$(this).attr("data-id");
			})
		}
	}
	//验证非空
	function testnull(obj){
		if(obj!=null){
			obj=obj;
		}else{
			obj="";
		}
		return obj;
	}
});