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
    	  	$("h1").eq(1).html(data.sendMoney+"元");
    	  	var stu="";
    	  	if(data.sendStatus==1){
    	  		stu="完好无损";
    	  	}else if(data.sendStatus==2){
    	  		stu="轻微破损";
    	  	}else if(data.sendStatus==3){
    	  		stu="严重破损";
    	  	}else if(data.sendStatus==4){
    	  		stu="货物缺少";
    	  	}
    	  	$("h1").eq(2).html(stu);
    	  	$("h1").eq(3).html(data.sendDescription);
          },
      error:
          function(data,textStatus){
              alert("服务器异常，请稍后再试");
          }
	})
});