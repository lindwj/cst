/**
 * Created by robot on 2015/5/25.
 */
var Trade = function(){

    /**
     * 模态加载对话框
     */
    var my_modal_loading = $('#my-modal-loading');
    
    var _startLoading = function(){
        my_modal_loading.modal();
    }
    var _doneLoading = function(){
        my_modal_loading.modal('close');
    }
    
    var successModal = $('#success-modal');
    
    var _modal = function(msg){
        $('#modal_msg_div').text(msg);
        successModal.modal();
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
    
    /**
     * 给顶部菜单绑定切换效果
     */
    var _initHeadMenu = function(){};
    
    var _init = function(){
        _initHeadMenu();
    };
    
    return {
    
        // main function to initiate the module
        init: function(){
            _init();
        }
    };
    
}();
