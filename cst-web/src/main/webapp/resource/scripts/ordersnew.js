/**
 * Created by 0x401 on 6/20/16.
 */
$(function(){



    ////默认地址放在第一个，设为选中状态
    //var addrID =  "6418923309";
    //var addrContent = "江苏省 无锡市 崇安区 广益街道 通沙路898号中国生态食品城北大荒";
    //var perName = "张勋";
    //var phone = "18800568118";
    ////var notAddrDef = "hidden";
    //
    ////如果不是默认地址，隐藏“默认地址”文字。初始加载时，选中状态下的地址即为默认地址
    //var addrLi = "<li class=\"clearfix selected\" data-addressid=\"" + addrID + "\">"
    //        +   "<i class=\"marker iconfont\"></i><span class=\"marker-tip\">寄送至</span>"
    //        +   "<div class=\"address-info\">"
    //        +       "<a href=\"javascript:void(0);\" class=\"modify\" data-value=\"" + addrID + "\">修改本地址</a>"
    //        +       "<input name=\"address\" type=\"radio\" value=\"6418923309\" id=\"addrId_" + addrID + "\" checked=\"checked\">"
    //        +       "<label for=\"addrId_" + addrID + "\" class=\"user-address\">"
    //        +           "<span>" + addrContent + "</span>（<span>" + perName + "</span>&nbsp;收）"
    //        +           "<span class=\"addr-person\"><em>" + phone + "</em></span>"
    //        +       "</label>"
    //        +       "<em class=\"tip\" id=\"addrDef" + addrID + "\" >默认地址</em>"
    //        +       "<a class=\"set-default hidden\" href=\"javascript:void(0);\" id=\"setDef_" + addrID + "\">设置为默认收货地址</a>"
    //        //+   "<div class=\"msg hidden\" id=\"test\"><p class=\"ok naked\">设置成功！</p></div>"
    //        +       "<p class=\"ok naked hidden\">设置成功！</p>"
    //        +   "</div></li>";
    //$("#address-list").append(addrLi);

    ////加载其余地址并隐藏
    //addrID =  "641892";
    //addrContent = "北京 北京市 朝阳区 大屯路 ";
    //var addrLi3 = "<li class=\"clearfix\" data-addressid=\"" + addrID + "\">"
    //    +   "<i class=\"marker iconfont\"></i><span class=\"marker-tip\">寄送至</span>"
    //    +   "<div class=\"address-info\">"
    //    +       "<a href=\"javascript:void(0);\" class=\"modify\" data-value=\"" + addrID + "\">修改本地址</a>"
    //    +       "<input name=\"address\" type=\"radio\" value=\"6418923309\" id=\"addrId_" + addrID + "\">"
    //    +       "<label for=\"addrId_" + addrID + "\" class=\"user-address\">"
    //    +           "<span>" + addrContent + "</span>（<span>" + perName + "</span>&nbsp;收）"
    //    +           "<span class=\"addr-person\"><em>" + phone + "</em></span>"
    //    +       "</label>"
    //    +       "<em class=\"tip hidden\" id=\"addrDef" + addrID + "\" >默认地址</em>"
    //    +       "<a class=\"set-default hidden\" href=\"javascript:void(0);\" id=\"setDef_" + addrID + "\">设置为默认收货地址</a>"
    //        //+   "<div class=\"msg hidden\" id=\"test\"><p class=\"ok naked\">设置成功！</p></div>"
    //    +   "<p class=\"ok naked hidden\" >设置成功！</p>"
    //    +   "</div></li>";
    //
    //$("#more-address").append(addrLi3);


    ////ajax获取加载商品信息
    //var itemLink = "";//商品详情页链接
    //var itemImg = "thumb-img1.jpg";
    //var itemTitle = "东北大米2015年盘锦有机新米10斤5kg赛黑龙江五常稻花香泰国香米";
    //var itemType = "5KG"; //规格
    //var itemPrice = "49.00";
    //var itemNum = "3";
    //var itemSum = "147.00";//金额是由前台计算？还是后台获取？
    //var listOfItem = "<div class=\"item clearfix\">"
    //        +       "<div class=\"itemInfo item-title\">"
    //        +           "<a  href=\"\" class=\"itemInfo-link\" >"
    //        +               "<span class=\"item-pic\">"
    //        +                   "<span><img class=\"itemInfo-pic\" src=\"" + itemImg +"\"></span>"
    //        +               "</span>"
    //        +               "<span class=\"itemInfo-title\" >" + itemTitle + "</span>"
    //        +           "</a>"
    //        +       "</div>"
    //        +       "<div class=\"item-type\"><span class=\"itemInfo type\"><em >" + itemType + "</em></span></div>"
    //        +           "<div class=\"item-price\"><span  class=\"price\"><em>" + itemPrice + "</em></span></div>"
    //        +           "<div class=\"quantity item-quantity\"><p>" + itemNum + "</p></div>"
    //        +           "<div class=\"itemPay item-total\"><p class=\"itemPay-price price\"><em>" + itemSum + "</em></p></div>"
    //        +       "</div></div>";
    //
    //$("#item-list").append(listOfItem);

    //绑定单选按钮点击按钮
    $(".address-list input").click(function(){
        //修改选中状态样式
        $(".address-list li").removeClass("selected");
        $(this).parent().parent().addClass("selected");

        //更新页面底部收件地址
        var addr =  $(this).next().children().eq(0).text();
        var perName =  $(this).next().children().eq(1).text();
        var phone = $(this).next().children().eq(2).text() ;

        $("#addr-bottom").text(addr);
        $("#person-bottom").text(perName + " " + phone);
    });

    //绑定LI hover属性
    $(".address-list li").hover(function(){

        var addrID = $(this).attr("data-addressid");
        $(".set-default").addClass("hidden");
        //不是默认地址时，显示“设置默认地址”按钮
        if( $("#addrDef" + addrID).attr("class") != "tip"  ){
            $("#setDef_" + addrID).removeClass("hidden");
        }

    });

    //绑定“其他地址”按钮
    $("#otherAddressLink").on( "click", function() {

        $(this).addClass("hidden");
        //显示更多地址
        $("#more-address").removeClass("hidden");
        //显示地址增加按钮
        $("#newAddressBtn").removeClass("hidden");

    });

    
    
  //点击增加新地址，显示frame和面具
  //当收货地址为空的时候，显示frame和面具
  $("#newAddressBtn").on( "click", function() {
      $("#addr-frame").removeClass("display-none");
      $("#frame-mask").removeClass("display-none");
  });

  //点击关闭按钮隐藏frame和面具
  $("#dialog-close").on( "click", function() {
      $("#addr-frame").addClass("display-none")
      $("#frame-mask").addClass("display-none");
  });


    

});


//绑定设置默认地址按钮
function bingdef(obj) {

//    $(".naked").addClass("hidden");
    $(obj).next().removeClass("hidden") ;
    //修改“默认地址”样式
//    $(".tip").addClass("hidden");
//    $(obj).prev().removeClass("hidden") ;

    //2秒“设置成功”设置为不可见
    setTimeout(function(){
        $(".naked").addClass("hidden");

    },2000);

}




//frame保存按钮
function add(num) {
    $("#addr-frame").addClass("display-none")
    $("#frame-mask").addClass("display-none");

    //取消选择样式
    $(".address-list li").removeClass("selected");
    //默认地址放在第一个，设为选中状态
    var addrID = $("#uuid").val();//分配一个ID
    var perName = $("#full-name").val();
    var phone = $("#mobile").val();
    
    
    
    var he="";
    if(num==1){
    	he="";
    }else{
    	he="hidden";
    }
    
    
    if(num==1){
    	var gid = $("#goodsuuid").val();
    	$("#"+gid).addClass("hidden");
    }
    

    var addrContent = $("#selprovince").find("option:selected").text() + "&nbsp;" + $("#selcity").find("option:selected").text() + "&nbsp;"  + $("#seldistrict").find("option:selected").text() + "&nbsp;" +$("#street").val();

    var temp = "sel_" + addrID;
    //如果不是默认地址，隐藏“默认地址”文字。初始加载时，选中状态下的地址即为默认地址
    var addrLi = "<li class=\"clearfix selected\" data-addressid=\"" + addrID + "\" id=" + temp + ">"
            +   "<i class=\"marker iconfont\"></i><span class=\"marker-tip\">寄送至</span>"
            +   "<div class=\"address-info\">"
            +       "<a href=\"/goodsAddress/goodsAddressAddEditIni.do?goodsAddressUuid="+addrID+"\" class=\"modify\" data-value=\"" + addrID + "\">修改本地址</a>"
            +       "<input onclick=\"chg('"+addrID+"')\" name=\"goodsAddressUuid\" type=\"radio\" value=\""+addrID+"\" id=\"addrId_" + addrID + "\"  taddress=\""+$("#street").val()+"\" tname=\""+perName+"\" tprovincestr=\""+$("#selprovince").find("option:selected").text()+"\" tcitystr=\""+$("#selcity").find("option:selected").text()+"\" tdistrictstr=\""+$("#seldistrict").find("option:selected").text()+"\" tmobile=\""+phone+"\" tstreetstr=\"\" checked=\"checked\">"
            +       "<label for=\"addrId_" + addrID + "\" class=\"user-address\">"
            +           "<span>" + addrContent + "</span>（<span>" + perName + "</span>&nbsp;收）"
            +           "<span class=\"addr-person\"><em>" + phone + "</em></span>"
            +       "</label>"
            +       "<em class=\"tip "+he+" \" id=\"addrDef" + addrID + "\" >默认地址</em>"
            +       "<a class=\"set-default hidden\" href=\"javascript:void(0);\"  onclick=\"def(this)\" gid=\""+addrID+"\"  id=\"setDef_" + addrID + "\">设置为默认收货地址</a>"
            //+   "<div class=\"msg hidden\" id=\"test\"><p class=\"ok naked\">设置成功！</p></div>"
            +       "<p class=\"ok naked hidden\">设置成功！</p>"
            +   "</div></li>";
    $("#address-list").prepend(addrLi);


    //绑定单选按钮点击按钮
    $(".address-list input").click(function(){
        //修改选中状态样式
        $(".address-list li").removeClass("selected");
        $(this).parent().parent().addClass("selected");

        //更新页面底部收件地址
        var addr =  $(this).next().children().eq(0).text();
        var perName =  $(this).next().children().eq(1).text();
        var phone = $(this).next().children().eq(2).text() ;

        $("#addr-bottom").text(addr);
        $("#person-bottom").text(perName + " " + phone);
    });
    //绑定LI hover属性
    $(".address-list li").hover(function(){
        var addrID = $(this).attr("data-addressid");
        $(".set-default").addClass("hidden");
        //不是默认地址时，显示“设置默认地址”按钮
        if( $("#addrDef" + addrID).attr("class") != "tip"  ){
            $("#setDef_" + addrID).removeClass("hidden");
        }
    });

}