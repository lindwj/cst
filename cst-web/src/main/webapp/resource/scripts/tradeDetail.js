/**
 * Created by robot on 2015/5/25.
 */
var TradeDetail = function(){

    var tradeId = $('#tradeId').val();
    
    var version = 0;
    
    var successModal = $('#success-modal');
    
    var _modal = function(msg){
        $('#modal_msg_div').text(msg);
        successModal.modal();
    };
    
    /**
     * 模态加载对话框
     */
    var my_modal_loading = $('#my-modal-loading');
    
    var _startLoading = function(){
        my_modal_loading.modal();
    };
    var _doneLoading = function(){
        my_modal_loading.modal('close');
    };
    
    /**
     * 判断是否是空
     * @param value
     */
    function isEmpty(value){
        if (value == null || value == "" || value == "undefined" || value == undefined || value == "null") {
            return true;
        }
        else {
            value = value.replace(/\s/g, "");
            if (value == "") {
                return true;
            }
            return false;
        }
    };
    
    var _fillForm = function(data){
    
        //version
        version = data.version;
        
        //交易信息汇总
        $('#tradeNo').text(data.tradeNo);
        //        $('#payment').text(data.payment);
        $('#createTime').text(data.createTime);
		
		$('#officeName').append(data.office.name);
        
        //交易状态
        var orderHeaderOper = "";
        var orderStatus = "";
        var stepStatus = "";
        
        var status = data.status;
        switch (status) {
            case "待买家付款":
                //GET /wx/trade/prepay?tradeId=<tradeId>
                orderHeaderOper = " <td style=\"padding-right:20px;text-align:right;\" class=\"last\" colspan=\"3\">" +
                "<a class=\"orange_color cancel_order\"  href=\"javascript:;\">取消订单</a></td>";
                
                orderStatus = "<p>订单提交，<strong>待付款</strong> <a class=\"pay_btn\"" +
                "href=\"/trades/prepay?tradeId=" +
                tradeId +
                "&version=" +
                version +
                "\"" +
                "target=\"_blank\">去支付</a> 支付方式：支付宝支付</p>" +
                "<p>请尽快付款，超时将取消订单</p>";
                stepStatus = "<li class=\"s13 past\">" +
                "<span class=\"on am-badge am-round\">1</span>" +
                "<p>订单提交</p>" +
                "</li>" +
                "<li class=\"s23\">" +
                "<span class=\" am-badge am-round\">2</span>" +
                "<p>配送中</p>" +
                "</li>" +
                "<li class=\"s33\">" +
                "<span class=\" am-badge am-round\">3</span>" +
                "<p>已收货</p>" +
                "</li>";
                break;
            case "买家已付款":
            case "待买家确认":
			
                //GET /wx/trade/prepay?tradeId=<tradeId>
                orderHeaderOper = " <td style=\"padding-right:20px;text-align:right;\" class=\"last\" colspan=\"3\">" +
                "<a class=\"orange_color cancel_order\"  href=\"javascript:;\"></a></td>";
                
                orderStatus = "<p>订单提交，<strong>已付款</strong>"+
				"<a class=\"pay_btn confirm_order\"" +
                "href=\"javascript:;\"" +
                "target=\"\">确认收货</a>"+
				"</p>";
				
                stepStatus = "<li class=\"s01 past\">" +
                "<span class=\"on am-badge am-round\">1</span>" +
                "<p>订单提交</p>" +
                "</li>" +
                "<li class=\"s02\">" +
                "<span class=\" am-badge am-round\">2</span>" +
                "<p>捡货中</p>" +
                "</li>" +
                "<li class=\"s03\">" +
                "<span class=\" am-badge am-round\">3</span>" +
                "<p>配送中</p>" +
                "</li>" +
                "<li class=\"s04\">" +
                "<span class=\" am-badge am-round\">4</span>" +
                "<p>已收货</p>" +
                "</li>";
				
                break;
            case "退货中":
			//GET /wx/trade/prepay?tradeId=<tradeId>
                orderHeaderOper = " <td style=\"padding-right:20px;text-align:right;\" class=\"last\" colspan=\"3\">" +
                "<a class=\"orange_color cancel_order\"  href=\"javascript:;\"></a></td>";
                
                orderStatus = "<p>退货中</p>";
				
                stepStatus = "<li class=\"s01 past\">" +
                "<span class=\"on am-badge am-round\">1</span>" +
                "<p>申请提交</p>" +
                "</li>" +
                "<li class=\"s02\">" +
                "<span class=\"on am-badge am-round\">2</span>" +
                "<p>退货中</p>" +
                "</li>" +
                "<li class=\"s03\">" +
                "<span class=\"am-badge am-round\">3</span>" +
                "<p>配送中</p>" +
                "</li>" +
                "<li class=\"s04\">" +
                "<span class=\"am-badge am-round\">4</span>" +
                "<p>已退货</p>" +
                "</li>";
                break;
            case "交易成功":
			
			//GET /wx/trade/prepay?tradeId=<tradeId>
                orderHeaderOper = " <td style=\"padding-right:20px;text-align:right;\" class=\"last\" colspan=\"3\">" +
                "<a class=\"orange_color cancel_order\"  href=\"javascript:;\"></a></td>";
                
                orderStatus = "<p>交易成功</p>";
				
                stepStatus = "<li class=\"s01 past\">" +
                "<span class=\"on am-badge am-round\">1</span>" +
                "<p>订单提交</p>" +
                "</li>" +
                "<li class=\"s02\">" +
                "<span class=\"on am-badge am-round\">2</span>" +
                "<p>捡货中</p>" +
                "</li>" +
                "<li class=\"s03\">" +
                "<span class=\"on am-badge am-round\">3</span>" +
                "<p>配送中</p>" +
                "</li>" +
                "<li class=\"s04\">" +
                "<span class=\"on am-badge am-round\">4</span>" +
                "<p>已收货</p>" +
                "</li>";
			
//                orderStatus = "<a href=\"/trades/" + tradeId + "/express\" class=\"pay-order\">订单跟踪</a>" +
//                "<a href=\"/trades/" +
//                tradeId +
//                "/express\" class=\"pay-order\">去评价</a>";
                break;
            case "交易关闭":
                orderHeaderOper = " <td style=\"padding-right:20px;text-align:right;\" class=\"last\" colspan=\"3\">" +
                "<a class=\"orange_color cancel_order\"  href=\"javascript:;\"></a></td>";

				orderStatus = "<p>交易已关闭</p>";
				
                stepStatus = "<li class=\"s01 past\">" +
                "<span class=\"on am-badge am-round\">1</span>" +
                "<p>订单提交</p>" +
                "</li>" +
                "<li class=\"s02\">" +
                "<span class=\" am-badge am-round\">2</span>" +
                "<p></p>" +
                "</li>" +
                "<li class=\"s03\">" +
                "<span class=\" am-badge am-round\">3</span>" +
                "<p></p>" +
                "</li>" +
                "<li class=\"s04\">" +
                "<span class=\"on am-badge am-round\">4</span>" +
                "<p>已关闭</p>" +
                "</li>";
                break;
            default:
                break;
        }
        
        $('.order_hd').append(orderHeaderOper);
        $('#orderStatus').empty().append(orderStatus);
        $('#stepStatus').empty().append(stepStatus);
		
        $('#orderList').empty();
        
        $.each(data.orderList, function(index, order){
            var li = "<div class=\"prod_list\">" +
            "<ul class=\"tr_ul\">" +
            "<li class=\"column_1\">&nbsp;</li>" +
            "<li class=\"column_2\"><a href=\"/products/index?id=" +
            order.skuId +
            "\">" +
            "<img src=\"" +
            order.picUrl +
            "\">" +
            order.title +
            "</a></li>" +
            "<li class=\"column_3\"></li>" +
            "<li class=\"column_4\"><span class=\"orange_color\">￥" +
            order.price +
            "</span></li>" +
            "<li class=\"column_5\">"+order.num+"</li></ul></div>";
            
            $('#orderList').append(li);
        });
        //收货人信息
        $('#addressDetail').append(data.buyer.name + "  " + data.buyer.mobile + "  " + data.buyer.address);
        
        //支付相关
        $('#payType').text(data.payType);
        $('#totalPrice').text(data.totalFee);
        $('#discountFee').text(data.discountFee);
        $('#postFee').text(data.postFee);
        $('#actualPayment').text(data.payment);
        
        //		$('#shippingType').text(data.payment);
        //TODO 配送时间
        
        //绑定按钮事件
        //取消订单
        $('.order_hd a.cancel_order ').click(function(){
        $('#template-confirm .am-modal-hd').text('取消订单');
        $('#template-confirm .am-modal-bd').text('确认取消订单？');
            $('#template-confirm').modal({
                relatedTarget: this,
                onConfirm: function(options){
                    // PUT /trade/<tradeId>/cancel
                    var actionUrl = "/trades/" + tradeId + "/cancel";
                    $.ajax({
                        type: "PUT", //提交方式  
                        dataType: "json", //数据类型  
                        data: {
                            "version": version
                        },
                        url: actionUrl, //请求url
                        success: function(data){ //提交成功的回调函数
                            if (data.code == 0) {
                                _modal(data.msg);
								window.location.href="/trades/"+tradeId+"/index";
                            }
                            else {
                                _modal(data.msg);
                            }
                        },
                        error: function(data){
                            _modal("取消订单失败！");
                        }
                    });
                },
                // closeOnConfirm: false,
                onCancel: function(){
                    //                        alert('算求，不弄了');
                }
            });
        });
		
		//确认收货
		 $('#orderStatus a.confirm_order ').click(function(){
        $('#template-confirm .am-modal-hd').text('确认收货？');
//        $('#template-confirm .am-modal-bd').text('确认收货？');
            $('#template-confirm').modal({
                relatedTarget: this,
                onConfirm: function(options){
                    // PUT {tradeId}/status/{status}
                    var actionUrl =  "/trades/"+tradeId + "/status/5" ;
                    $.ajax({
                        type: "PUT", //提交方式  
                        dataType: "json", //数据类型  
                        data: {
                            "version": version
                        },
                        url: actionUrl, //请求url
                        success: function(data){ //提交成功的回调函数
                            if (data.code == 0) {
                                _modal(data.msg);
								window.location.href="/trades/"+tradeId+"/index";
                            }
                            else {
                                _modal(data.msg);
                            }
                        },
                        error: function(data){
                            _modal("确认收货失败！");
                        }
                    });
                },
                // closeOnConfirm: false,
                onCancel: function(){
                    //                        alert('算求，不弄了');
                }
            });
        });
    };
    
    var _loadTradeDetail = function(){
        // GET /trade/info?tradeId=<tradeId>
        var actionUrl = "/trades/info?tradeId=" + tradeId;
        $.ajax({
            type: "get", //提交方式  
            dataType: "json", //数据类型  
            data: null,
            url: actionUrl, //请求url  
            success: function(data){ //提交成功的回调函数
                if (data.code == 0) {
                    if (data.size !== 0) {
                        _fillForm(data.data);
                    }
                }
                _doneLoading();
            },
            error: function(data){
                _doneLoading();
                _modal("请求交易详情失败！");
            }
        });
    };
    
    var _init = function(){
        _startLoading();
        _loadTradeDetail();
    };
    
    return {
    
        // main function to initiate the module
        init: function(){
            _init();
        },
    
    };
    
}();
