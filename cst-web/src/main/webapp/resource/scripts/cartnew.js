/**
 * Created by zhang on 6/15/16.
 */

$(function(){

    //
    ////ajax获取商品列表，并计算商品价格
    //var chkID = "160598224418";
    //var imgLink = "";
    //var imgAddress = "thumb-img1.jpg";
    //var itemTiele = "运通农场黄小米500g东北小黄米2015新米月子米农家杂粮食小米粥";
    //var price = "5.9";
    //var priceSum = 11.8;
    //var inputNum = 2;
    //var proID = "16059823";
    //var order =
    //    "<div class=\"order-content\">" +
    //        "<ul class=\"item-content\">" +
    //            "<li class=\"td td-chk\">" +
    //                "<div class=\"td-inner\">" +
    //                    "<div class=\"cart-checkbox\">" +
    //                    "<input id=\"checkBox" + proID + "\" type=\"checkbox\" name=\"items\"  value=\"" + proID + "\">" +
    //                    "</div>" +
    //                "</div>" +
    //            "</li>" +
    //            "<li class=\"td td-item\">" +
    //                "<div class=\"td-inner\">" +
    //                    "<div class=\"item-pic\">" +
    //                        "<a href=\"" + imgLink + "\"  target=\"_blank\">" +
    //                            "<img src=\"" + imgAddress + "\">" +
    //                        "</a>" +
    //                    "</div>" +
    //                    "<div class=\"item-info\">" +
    //                        "<div class=\"item-basic-info\">" +
    //                            "<a><span class=\"item-title\">" + itemTiele + "</span></a>" +
    //                        "</div>" +
    //                    "</div>" +
    //                "</div>" +
    //            "</li>" +
    //            "<li class=\"td td-info\">" +
    //                "<div class=\"item-props\"></div>" +
    //            "</li>" +
    //            "<li class=\"td td-price\">" +
    //                "<div class=\"td-inner\">" +
    //                    "<div class=\"price-content\">" +
    //                        "<em class=\"price-now\" id=\"price_" + proID +  "\"  >" + price + "</em>" +
    //                    "</div>" +
    //                "</div>" +
    //            "</li>" +
    //            "<li class=\"td td-amount\">" +
    //                "<div class=\"td-inner\">" +
    //                    "<div class=\"amount-wrapper\">" +
    //                        "<div class=\"item-amount\">" +
    //                            "<span  class=\"minus\" name=\"minusSelectd\" data-minus=\"" + proID + "\" >-</span>" +
    //                            "<input type=\"text\" value=\"" + inputNum  + "\" class=\"text-amount\"  id=\"inpNum_" + proID+ "\"  autocomplete=\"off\">" +
    //                            "<span class=\"plus\" name=\"plusSelectd\" data-plus=\"" + proID + "\" >+</span>" +
    //                        "</div>" +
    //                    "</div>" +
    //                "</div>" +
    //            "</li>" +
    //            "<li class=\"td td-sum\">" +
    //                "<div class=\"td-inner\">" +
    //                    "<em id=\"sum_" + proID +  "\" name=\"sumPrice\" >" + priceSum + "</em>" +
    //                "</div>" +
    //            "</li>" +
    //            "<li class=\"td td-op\">" +
    //                "<div class=\"td-inner\"><a href=\"\" >删除</a></div>" +
    //            "</li>" +
    //            "<div class=\"float-clear\"></div>" +
    //        "</ul>" +
    //    "</div>";
    //
    //$("#order-list").append(order);


    //购物车增加按钮绑定点击事件
    $("span[name='plusSelectd']").on( "click", function() {
        var $inputNum = $(this).prev();
        $inputNum.val( $inputNum.val() - 0 + 1);
        if( $inputNum.val() == 2){
            $(this).prev().prev().removeClass("no-minus");
            $(this).prev().prev().addClass("minus");
        }
        //修改金额
        var ID = $(this).attr("data-plus");
        var price = Number($("#price_" + ID).text());
        var sum = Number($("#sum_" + ID).text());
        $("#sum_" + ID).text(Number(price + sum).toFixed(2) );

        //本商品如果处于选中状态，修改合计金额
        if($("#checkBox" + ID).prop("checked") == true){
            var sumTotal = Number($("#sum-total").text());
            //金额清零，结算按钮不可用
            $("#small-total").text(Number(sumTotal + price).toFixed(2));
            $("#sum-total").text(Number(sumTotal + price).toFixed(2));
        }

    });

    //购物车减少按钮绑定点击事件
    $("span[name='minusSelectd']").on( "click", function() {
        var $inputNum = $(this).next();
        if( $inputNum.val()  > 1){
            $inputNum.val( $inputNum.val() - 1);
            if($inputNum.val() == 1){
                $(this).removeClass("minus");
                $(this).addClass("no-minus");
            }
            var ID = $(this).attr("data-minus");
            var price = Number($("#price_" + ID).text());
            var sum = Number($("#sum_" + ID).text());
            $("#sum_" + ID).text(Number(sum - price ).toFixed(2) );


            //本商品如果处于选中状态，修改合计金额
            if($("#checkBox" + ID).prop("checked") == true){
                var sumTotal = Number($("#sum-total").text());
                //金额清零，结算按钮不可用
                $("#small-total").text(Number(sumTotal - price).toFixed(2));
                $("#sum-total").text(Number(sumTotal - price).toFixed(2));
            }
        }



    });

    //checkbox事件
    $("#selectAllChk, #selectAllChk2").on("click", function() {
        if($(this).prop("checked")){
            $("input[name='items']").prop("checked", true);
            $("input[name='select-all']").prop("checked",true);
            //计算商品金额，
            var sumTotal = 0;
            $("em[name='sumPrice']").each(function(){
                sumTotal = Number( $(this).text() - 0 + Number(sumTotal)).toFixed(2) ;
            });
            $("#small-total").text(sumTotal);
            $("#sum-total").text(sumTotal);

            // 启用结算按钮
            $("#small-submit").removeClass("submit-btn-disabled");
            $("#a-sum-total").removeClass("submit-btn-disabled");
            $("#small-submit").addClass("submit-btn");
            $("#a-sum-total").addClass("submit-btn");
        }else{
            $("input[name='items']").prop("checked", false);
            $("input[name='select-all']").prop("checked",false);
            //金额清零，结算按钮不可用
            $("#small-total").text("0");
            $("#sum-total").text("0");
            $("#small-submit").addClass("submit-btn-disabled");
            $("#a-sum-total").addClass("submit-btn-disabled");
        }
    });

    $("input[name='items']").on("click", function() {

        var ID = $(this).val();
        var sum = Number($("#sum_" + ID).text());
        if($(this).prop("checked")){
            //全选按钮设置选中状态
            $("input[name='select-all']").prop("checked",true);
            //修改金额
            $("#small-total").text( Number($("#small-total").text() - 0 + sum).toFixed(2) );
            $("#sum-total").text( Number($("#sum-total").text() - 0 + sum).toFixed(2) );

            //启用结算按钮
            $("#small-submit").removeClass("submit-btn-disabled");
            $("#a-sum-total").removeClass("submit-btn-disabled");
            $("#small-submit").addClass("submit-btn");
            $("#a-sum-total").addClass("submit-btn");

        }else{
            //修改金额
            $("#small-total").text(  Number($("#small-total").text() - 0 - sum).toFixed(2) );
            $("#sum-total").text( Number($("#sum-total").text() - 0 - sum).toFixed(2) );

            //判断是否存在选中状态下商品，如果不存在，全选按钮设置为空状态，结算按钮置灰
            if($("input[name='items']:checked").length == 0){
                $("input[name='select-all']").prop("checked",false);
                //金额清零，结算按钮不可用
                $("#small-total").text("0");
                $("#sum-total").text("0");
                $("#small-submit").addClass("submit-btn-disabled");
                $("#a-sum-total").addClass("submit-btn-disabled");
            }

        }
    });


});




