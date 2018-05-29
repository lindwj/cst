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
	var id=window.location.href.split("=")[1];
	$.ajax({
		type: 'POST',
		url: get_sign,		
		dataType : "json" ,
		data:
		{
			logisticsId:id,
		},
      success:
          function(data,textStatus){
    	  if(data.logisticsId!=id){
    	  		$("#s1").val(data.sendMoney+"元");
    	  		$("#s2").val(data.sendStatus);
    	  		$("#s3").val(data.sendDescription);
    	  	}
          },
      error:
          function(data,textStatus){
              alert("服务器异常，请稍后再试");
          }
	})
	$("#save").click(function(){
		var reg=/(^\d+$)/;
		var reg1=/(^\d+(\.)(\d+))|(^\d+)$/;
		if($("#s1").val()==""||$("#s2").val()==""){
			console.log("hehe");
			$("#errorm3").fadeIn(300);
			$("#errorm3").fadeOut(5000);
		}else if(reg.test($("#s1").val())||reg1.test($("#s1").val())){
			$.ajax({
				type: 'POST',
				url: logistics_sign,
				dataType : "json" ,
				data:
				{
					logisticsId:id,
					sendMoney:$("#s1").val(),
					sendStatus:$("#s2").val(),
					sendDescription:$("#s3").val()
				},
	      success:
	          function(data,textStatus){
	    	  	window.location.href= link_orderDetail+'?code='+data;
	          },
	      error:
	          function(data,textStatus){
	              alert("服务器异常，请稍后再试");
	          }
			})
		}else{
			$("#errorm4").fadeIn(300);
			$("#errorm4").fadeOut(5000);
		}
	})
});