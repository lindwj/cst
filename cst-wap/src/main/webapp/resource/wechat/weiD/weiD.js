$(function(){
	var openid= window.location.href.split("?openid=")[1];
	var manager="";
	$.ajax({
		type: 'POST',
        url: managerInfo_url,
        dataType : "json" ,
        data:{
        	openId:openid
        },
        async: false,
        success:
            function(data){
        		$(".picWx").attr("src",base_url+"/resource/upload/headImg/"+data.picWx);
        		$(".nickname").html(data.nickname);
        		manager=data.managerId;
            },
        error:
            function(data,textStatus){
            }
	})
/*	$.ajax({
		type: 'POST',
		url: getNoMoney_url,
		dataType : "json" ,
		data:{
			agentId:manager
		},
		async: false,
		success:
			function(data){
			$(".priceN").text(data);
		},
		error:
			function(data,textStatus){
		}
	})*/
	$.ajax({
		type: 'POST',
		url: getAllMoney_url,
		dataType : "json" ,
		data:{
			agentId:manager
		},
		success:
			function(data){
			$(".priceA").text(data.toFixed(2))
;
		},
		error:
			function(data,textStatus){
		}
	})
	$.ajax({
		type: 'POST',
		url: getDayMoney_url,
		dataType : "json" ,
		data:{
			agentId:manager
		},
		success:
			function(data){
			$(".priceD").text(data.toFixed(2));
		},
		error:
			function(data,textStatus){
		}
	})
	$.ajax({
		type: 'POST',
		url: getMonthMoney_url,
		dataType : "json" ,
		data:{
			agentId:manager
		},
		async: false,
		success:
			function(data){
			$(".priceM").text(data.toFixed(2));
		},
		error:
			function(data,textStatus){
		}
	})
	$.ajax({
		type: 'POST',
		url: getFan_url,
		dataType : "json" ,
		data:{
			agentId:manager
		},
		success:
			function(data){
			$(".fan").text(data);
		},
		error:
			function(data,textStatus){
		}
	})
	$.ajax({
		type: 'POST',
		url: getFans_url,
		dataType : "json" ,
		data:{
			agentId:manager
		},
		success:
			function(data){
			$(".fans").text(data);
		},
		error:
			function(data,textStatus){
		}
	})
	
	
	$(".qrcode").click(function(){
		window.location.href = brand_path+"?openid="+openid+"manager="+manager;
	})
})