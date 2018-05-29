/**
 * Created by robot on 2015/5/25.
 */
var Cart = function(){
    
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
    
    
    function isInteger(x){
        return parseInt(x, 10) == x;
    };
    
    /**
     * 选中与取消选中物品状态
     */
    var _checkAllHandler = function(obj){
    	if($(obj).is(":checked")){
    		$("input[id$='chk']").prop("checked", true);
    		$("input[id='selectAllChk']").prop("checked", true);
    	}else{
    		$("input[id$='chk']").prop("checked", false);
    		$("input[id='selectAllChk']").prop("checked", false);
    	}
    	
    	total();
    	
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
    
function total(){
	
	
	
	var flag=0;
	
    	//算钱
    	var amt=0;
    	$("input[id$='chk']").each(function () {
    		if($(this).is(":checked")){
            var uuid = $(this).attr("uuid");
            var price = parseFloat($("#"+uuid+"showprice").text());
            var count = parseInt($("#"+uuid+"column_num").val());
            var total=price*count;
    		amt=amt+total;
    		
    		
    		$("#"+uuid+"code").attr("value","1");
    		}else{
    			var uuid = $(this).attr("uuid");
    			$("#"+uuid+"code").attr("value","0");
    			
    			//未选中 则取消全选勾中
    			flag=1;
    			
    		}
    		
        });
		
    	$("#cart_realPrice").text(amt);
    	$("#cart_realPrice2").text(amt);
    	
    	
    	if(amt>0){
    	// 启用结算按钮
        $("#small-submit").removeClass("submit-btn-disabled");
        $("#a-sum-total").removeClass("submit-btn-disabled");
        $("#small-submit").addClass("submit-btn");
        $("#a-sum-total").addClass("submit-btn");
    	}else{
    		$("#small-submit").removeClass("submit-btn");
            $("#a-sum-total").removeClass("submit-btn");
            $("#small-submit").addClass("submit-btn-disabled");
            $("#a-sum-total").addClass("submit-btn submit-btn-disabled");	
    	}
    	
    	
    	//判断是否全选
    	if(flag == 1){
    		$("input[id='selectAllChk']").prop("checked", false);
    	}else{
    		$("input[id='selectAllChk']").prop("checked", true);
    	}
    	
		
	}

function _chg(uuid){
	
	total();
	
	
	var capacity =parseInt($("#"+uuid+"column_num").val());
	var temp=capacity;
	var oldcapacity =parseInt($("#"+uuid+"column_num").attr("oldvalue"));
	//数量差
	capacity=capacity-oldcapacity;
	
	$("#"+uuid+"column_num").attr("oldvalue",temp);
	cart(capacity,uuid);
	
}
    
    
    
    /**
     * 减少商品
     * @param {Object} id
     */
    function _subWareBybutton(uuid){
		var count = parseInt($("#"+uuid+"column_num").val());
        count=count-1;
        
        if(count>=1){
        cart(-1,uuid);
        }
        
		if(count<1){
			count =1;
		}
		$("#"+uuid+"column_num").val(count);
		
		total();
		
		var price = parseFloat($("#"+uuid+"showprice").text());
        var count = parseInt($("#"+uuid+"column_num").val());
        var t=price*count;
        
        $("#"+uuid+"sumprice").text(t);
		
	}
	
   /**
     * 增加商品
     * @param {Object} id
     */
	function _addWareBybutton(uuid){
		var count = parseInt($("#"+uuid+"column_num").val());
			count=count+1;
			$("#"+uuid+"column_num").val(count);
			
			total();
			
			cart(1,uuid);
			
			var price = parseFloat($("#"+uuid+"showprice").text());
	        var count = parseInt($("#"+uuid+"column_num").val());
	        var t=price*count;
	        
	        $("#"+uuid+"sumprice").text(t);
	}
    
	

    
    var _init = function(){
    	total();
    };
    
    return {
    
        // main function to initiate the module
        init: function(){
            _init();
        },
        checkAllHandler: function(obj){
            return _checkAllHandler(obj);
        },
        addWareBybutton: function(uuid){
            return _addWareBybutton(uuid);
        },
        subWareBybutton: function(uuid){
            return _subWareBybutton(uuid);
        },
        amt: function(){
            return total();
        },
        chg: function(uuid){
            return _chg(uuid);
        },
        popModal: function(x){
            return _popModal(x);
        }
    };
    
}();
