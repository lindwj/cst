/**
 * Created by zhang on 6/14/16.
 */
function popModal(msg) {
		$('#inok i').text(msg);
		$("#inok").show();
		setTimeout("$('#inok').fadeOut(5000)", 2000);
	}


$("#loginBtn").on( "click", function() {

    //需要补充校验校验账号密码是否正确

    var $mobile = $("#mobile").val().trim();
    var $pasw = $("#password").val().trim();
    

    if($mobile == ""){
    	popModal("请输入手机号");
        return false;
    }
    if($pasw == ""){
    	popModal("请输入密码");
        return false;
    }
    
    $("#loginFormFix").submit();
    //ajax校验账号密码
//    if(1==2){
//        //密码正确。登陆，增加cookie。
//
//    }else{
//        //密码错误
//        $(".error").text("您输入的账号和密码不匹配，请检查。");
//        return false;
//    }



});


