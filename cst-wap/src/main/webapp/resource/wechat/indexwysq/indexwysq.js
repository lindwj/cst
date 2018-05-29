$(function () {
	
	/*//公告
    $(".black_box1").show().delay(3000).fadeOut();*/
	
	var init= window.location.href.split("?")[1];
	var eventid=init.split("=")[1].split("&")[0];
	
	var reg=/#/g;

	eventid=eventid.replace(reg,'');
	
	var wysqurl=wysq_url+eventid+"#wechat_redirect";
	
	window.location.href=wysqurl;
	
});