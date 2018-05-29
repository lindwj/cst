/**
 * Created by 0x401 on 6/14/16.
 */


var timeCntTotal = 60;//记录获取验证码的冻结时间
var iCount;

var updateTimeCnt = function(){
    timeCntTotal--;
    var text = "获取验证码(" + timeCntTotal + "s)";
    var tmp = parseInt(timeCntTotal, 10);
    if (tmp <= 0) {
        clearInterval(iCount);
        $('#code-btn').val("获取验证码(60s)");
        $('#code-btn').removeClass('am-disabled');
        timeCntTotal = 60;
    }
    else {
        $('#code-btn').val(text);
    }
};


$("#code-btn").on( "click", function() {

    var $mobile = $("#mobile").val().trim();

    if($mobile == ""){
        $("#mobileError").text("请先输入手机号。");
        return false;
    }

    //var $pasw = $("#paswd").val().trim();
    //if($pasw == ""){
    //    $(".error").text("请输入密码。");
    //    return false;
    //}


    //ajax获取验证码
    if(1==1){
        //获取验证码成功。
        $('#code-btn').addClass ('am-disabled');

        iCount = setInterval(updateTimeCnt, 1000);
    }else{
        //获取验证码失败
        $(".error").text("请重新获取验证码。");
        return false;
    }

});

$("#registBtn").on( "click", function() {

    $("#mobileError").text("");
    $(".error").text("");

    var $mobile = $("#mobile").val().trim();
    if($mobile == ""){
        $("#mobileError").text("请先输入手机号。");
        return false;
    }
    var $pasw = $("#paswd").val().trim();
    if($pasw == ""){
        $(".error").text("请输入密码。");
        return false;
    }


});
