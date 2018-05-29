/**
 * Created by robot on 2015/5/25.
 */
var buy = function(){
    
    
    var isUpdate = false;
    var province = "";
    var city = "";
    var district = "";
    var street = "";
    var address = "";
    var name = "";
    var mobile = "";
    var nationId = "";
    
    var successModal = $('#success-modal');
    var addressModal = $('#addressList');
    
    var isDefaultAddress = true;
    
    var addressDailog = $('#address-dialog');
    
    var _popModal = function(msg){
        $('#inok i').text(msg);
        $("#inok").show();
        setTimeout("$('#inok').fadeOut(1000)", 800);
    };
    
    var _modal = function(msg){
        $('#modal_msg_div').text(msg);
        successModal.modal();
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
    }
    
    var isBlank = function(a){
        if (a == undefined || a == null || a.length == 0) {
            return true;
        }
        else {
            return false;
        }
    };
    
    /**
     * 减少商品
     * @param {Object} id
     */
    function _subWareBybutton(){
		var count = parseInt($("#column_num").val());
        count=count-1;
		if(count<1){
			count =1;
		}
		$("#column_num").val(count);
		
		var price = parseFloat($("#showprice").text());
		var total=price*count
		$("#cart_realPrice").text(total);
		$("#totalFromBdh").val(total);
		
	}
	
   /**
     * 增加商品
     * @param {Object} id
     */
	function _addWareBybutton(){
			var count =parseInt( $("#column_num").val());
			count=count+1;
			$("#column_num").val(count);
			
			var price = parseFloat($("#showprice").text());
			var total=price*count
			$("#cart_realPrice").text(total);
			$("#totalFromBdh").val(total);
	}
    
    
    function _isInteger(x){
        return parseInt(x, 10) == x;
    };
    
    
    /**
     * 做显示字符的转换
     */
    var _processString = function(str){
        if (str === null || str === undefined || str === "") {
            str = "";
        }
        return str;
    };
    
    
    /**
     * 更新价格
     * @param {Object} total
     * @param {Object} discount
     * @param {Object} payment
     */
    var _updatePrice = function(total, discount, payment){
        $('#cart_realPrice').text(total);
        //        $('#cart_oriPrice').text(total);
        //        $('#cart_rePrice').text(discount);
    };
    
    
    var _init = function(){
    };
    
    return {
    
        // main function to initiate the module
        init: function(){
            _init();
        },
        addWareBybutton: function(){
            return _addWareBybutton();
        },
        subWareBybutton: function(){
            return _subWareBybutton();
        },
        isInteger: function(x){
            return _isInteger(x);
        },
        popModal: function(x){
            return _popModal(x);
        }
    };
    
}();
