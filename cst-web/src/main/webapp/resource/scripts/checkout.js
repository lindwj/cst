/**
 * Created by robot on 2015/5/25.
 */
var Checkout = function(){

    
    var _popModal = function(msg){
        $('#inok i').text(msg);
        $("#inok").show();
        setTimeout("$('#inok').fadeOut(1000)", 800);
    };
    
	/**
	 * 模态加载对话框
	 */
	var my_popModal_loading = $('#my-modal-loading');
	
	var _startLoading = function(){
		my_popModal_loading.modal();
	}
	var _doneLoading = function(){
		my_popModal_loading.modal('close');
	}
    
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
    
    /**
     * 提交订单去支付
     */
    var _submitTrade = function(){
    	$("#alipayto").submit();
    	//_invokeAliPay();
    	
    	};
    
	var _invokeAliPay = function(){
			$('#wait-pay-modal').modal('open');
	}
    
    var _init = function(){
        _startLoading();
		
		$('#btn_check').click(function(){
			_submitTrade();
		});
		
		$('#afterPayBtn').click(function(){
			window.location.href="/trades/"+trade_id+"/index";
		});
		
		$('#questionBtn').click(function(){
			window.location.href="/trades/"+trade_id+"/index";
		});
    };
    
    return {
    
        // main function to initiate the module
        init: function(){
            _init();
        }
    };
    
}();
