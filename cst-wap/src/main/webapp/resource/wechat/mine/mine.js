$(function () {
	
	$.ajax({
		type: 'POST',
        url: isLogin_url,
        dataType : "json" ,
        data: {},
        success:
            function(data){
        		if(data.status==1){
        			$(".header").find("h1").html(data.nickname);
        		}
            },
        error:
            function(data,textStatus){

            }
	});
	
	//跳转地址列表
	$(".address").click(function(){
		window.location.href =  addressList_path;
	});
	
	//跳转修改密码
	$(".password").click(function(){
		window.location.href = forget_path;
	});
	
	//待付款
	$(".order_box").find("a").eq(0).click(function(){
		window.location.href = payWait_path;
	});
	
	//待发货
	$(".order_box").find("a").eq(1).click(function(){
		window.location.href = receiptWait_path;
	});
	
	//全部订单列表
	$(".order_box").find("a").eq(2).click(function(){
		window.location.href = order_path;
	});
	
	 //退出登录
    $(".log_out").click(function(){

        $(".black_box").fadeIn(500);

    });
    //取消
    $("#cancle").click(function(){
        $(".black_box").fadeOut(500);
    });
    //确认
    $("#delbtn").click(function(){

    	$.ajax({
	        type: 'POST',
	        url: logout_url,
	        dataType : "json" ,
	        data: {},
	        success:
	            function(data){
	        		if(data==1){
	        			window.location.href =  index_path;
	        		}
	            },
	        error:
	            function(data,textStatus){
	        		alert("服务器异常");
	            }
	    });
    });
	
	
});