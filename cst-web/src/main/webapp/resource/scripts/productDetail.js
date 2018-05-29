/**
 * Created by robot on 2015/5/30.
 */
var ProductDetail = function(){

	
    var _popModal = function(msg){
        $('#inok i').text(msg);
        $("#inok").show();
        setTimeout("$('#inok').fadeOut(1000)", 800);
    };
    
    //    var successModal = $('#success-modal');
    //    
    //    var _popModal = function(msg){
    //        $('#modal_msg_div').text(msg);
    //        successModal.modal();
    //    };
    
    var _initSlider = function(){
        var $default = $('.slider ol li:first');
        $default.addClass("active");
        $('.slider ul li:first').show();
        
        // 图片展示
        $('.slider ol li').mouseover(function(){
            $('.slider ol li').removeClass('active');
            $(this).addClass('active');
            $('.slider ul li').hide();
            $('.slider ul li').eq($(this).index()).show();
        });
    };
    
    function _isInteger(x){
        return parseInt(x, 10) == x;
    };
    
    // 飞车效果
    var _moveIng = function(obj){
    	
        var myParabola = funParabola(document.querySelector("#flyItem"), document.querySelector("#shopCart"), {
            speed: 150,
            curvature: 0.0015,
            complete: function(){
                document.querySelector("#flyItem").style.visibility = "hidden";
            }
        });
        
        var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft || 0, scrollTop = document.documentElement.scrollTop || document.body.scrollTop || 0;
        
        document.querySelector("#flyItem").style.left = $(obj).offset().left + "px";
        
        document.querySelector("#flyItem").style.top = $(obj).offset().top + "px";
        
        document.querySelector("#flyItem").style.visibility = "visible";
        
        myParabola.position().move();
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
	}
	
   /**
     * 增加商品
     * @param {Object} id
     */
	function _addWareBybutton(){
			var count =parseInt( $("#column_num").val());
			count=count+1;
			$("#column_num").val(count);
	}
    
    
    var _init = function(){
    
        _initSlider();
        
        
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
        },
        moveIng: function(x){
            return _moveIng(x);
        }
        
        
    };
    
    
}();
