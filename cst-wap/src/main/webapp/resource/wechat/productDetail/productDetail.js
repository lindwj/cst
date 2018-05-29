$(function(){
	//获取链接的商品uuid
    var prouuid = window.location.href.split("=")[1];
    var prouuid1="";
    if(prouuid.indexOf("#")>0){
    	prouuid1=prouuid.split("#")[0];
    }else{
    	prouuid1=prouuid;
    }
    if(prouuid.indexOf("&")>0||prouuid.indexOf("&")==0){
    	prouuid1=prouuid.split("&")[0];
    }else{
    	prouuid1=prouuid;
    }
    var ajaxURL = stardetail_url + prouuid1;
    
    var eventid=1;
    var piaoshu=1;
    var title=null;
    var status=0;
    var starStatus=0;
    var time=null;
    
    $.ajax({
        type: 'POST',
        url: ajaxURL,
        dataType : "json" ,
        data: {},
        async: false,
        success:
            function(data){
        	
        	//验证cookie
        	var co= $.cookie('bdhcookie');
//        	alert(co);
        	if(co==null||co=='undefined'){
//        		alert(co);
        		window.location.href = index_path.replace('index','indexwysq2')+prouuid1+'user';
        		return false;
        	}
        	
        	
        	
        	//event 信息
            $.ajax({
                type: 'POST',
                url: event_url,
                dataType : "json" ,
                data: {id:data.eventid},
                async:false,
                success:
                    function(data){
                	setBgColor(data);
                	title=data.headtxt;
                	status=data.status;
                	time=data.endTime;
                    },
                error:
                    function(data,textStatus){
                    }
            });
            
            
        		//获取商品详情数据
                getProduct(data,0);
            },
        error:
            function(data,textStatus){
        		alert("服务器异常");
            }
    });
    
    
    //分享
    $.ajax({  
        type : "post",  
        dataType : "json",  
        url : setShare_url,  
        data : {  
            url : window.location.href
        },  
        async:false, 
        success : function(msg) {  
        	
            //var data = msg['success'];  
            //if (data) {  
//        	alert(msg.appId);
//        	alert(msg.timestamp);
//        	alert(msg.nonceStr);
//        	alert(msg.signature);
                wx.config({  
                    debug : false, //  
                    appId : msg.appId, // 必填，公众号的唯一标识  
                    timestamp : msg.timestamp, // 必填，生成签名的时间戳  
                    nonceStr : msg.nonceStr, // 必填，生成签名的随机串  
                    signature : msg.signature,// 必填，签名，见附录1  
                    jsApiList : [ 'onMenuShareTimeline',  
                            'onMenuShareAppMessage', 'showOptionMenu' ]  
                // 必填，需要使用的JS接口列表，所有JS接口列表见附录2  
                });  
           // }  
        } 
    });
    
    
    
    
    //刷礼物list
    $.ajax({
        type: 'POST',
        url: shualiwulist_url,
        dataType : "json" ,
        data: {
        	starId:prouuid1
        },
        async: false,
        success:
            function(data){
        	getOrderList(data,1);
            },
        error:
            function(data,textStatus){
        		alert("服务器异常");
            }
    });
    
    
    function getOrderList(info,flag) {
    	if(flag==0){
    		$(".pro_infor_list").empty();
    	}
    	

        var product = "";
        var allproduct = "";
        var count=0;
        $.each(info, function (i, item) {
        	
        	
            	
        	
//                            if( 0 <= n && n <= 4 ){
        	
        	
                                product += "<dl>"
                                	+"<dt><img style='height:48px;width:48px;' src='/cst-wap/resource/upload/headImg/"+item.headurl+"' alt=''></dt>"
                                	+"<dd>"
                                	+"<h5>"+item.name+"，给她送了"+item.liwuName+"！</h5>"
                                	+"<p>"+item.crtTimestr+"</p>"
                                	+"</dd>"
                                	+"</dl>";

                                count++;
                                
//                            }
//                        }
                    });

//                    productmore = "<li class='item'>"
//                        +         "<a class='itemnore' href='#' data-id='" + "'>"
//                        +         "<h1 class='more'>更多" + "" + "</h1>"
//                        +         "<p class='morearrow'>"
//                        +         "<i class='iconfont moreicon'>&#xe65d;</i>"
//                        +         "</p>"
//                        +         "</a>"
//                        +         "</li>";

                    allproduct +=  product +"<a href='javascript:void(0)' style='text-align:center' class='vote_more' id='ckgd' page='' allPage=''>查看更多</a>";
        
        
        
         var temp=document.getElementById("ckgd");
         if(temp!=null){
        	 $("#ckgd").attr("id", "asdasdsad");
        	 $("#asdasdsad").hide();
         }
        $(".pro_infor_list").append(allproduct);
        
        
      //分页
    	if(count>0){
    		$("#ckgd").attr("page", info[0].page.currentPage) ;
    		$("#ckgd").attr("allPage", info[0].page.totalPage) ;
    	}
    }
    
  //查看更多
    
    $("#ckgd").click(function(){
    	
    	var x = parseInt($("#ckgd").attr("page"))+1;
    	var y =parseInt($("#ckgd").attr("allPage"));
    	if(x > y){
    		return false;
    	}
    	
    	//star
        $.ajax({
            type: 'POST',
            url: shualiwulist_url,
            dataType : "json" ,
            data: {starId:prouuid1,'page.currentPage':x,
            	'page.totalPage':y},
            async:false,
            success:
                function(data){
                    //显示订单管理第一页数据
                    getOrderList(data,1);
                    
                },
            error:
                function(data,textStatus){
                }
        });
    });

//    $(".footer").find("li").eq(0).click(function(){
//    	window.location.href=index_path;
//    });
    //防止body被手机软键盘顶起来样式改变
    $('body').height($('body')[0].clientHeight);
    
    
    //获取商品详情
    function getProduct(info,flag){
    	if(flag==1){
//    		$("#redu").text(info.redu) ;
    		return false;
    	}
    	
    	eventid=info.eventid;
    	piaoshu=info.piaoshu;
    	starStatus=info.status;
    	$("#sy").attr("href","/cst-wap/resource/wechat/index.html?id="+info.eventid) ;
    	
    	if(info==null){
//			error("没有数据");
			return false;
    	}else{
    		
    		
    		
    		$("#uname").text(info.name) ;
    		
    		
    		
			//返回上一级页面
			$(".arrow").click(function(){
				history.back();
			});

			$("#banner1").attr("src",info.headurl);
			$("#pic").attr("src",info.headurl);
			
			
			$("#bh").text(info.code) ;
			$("#ps").text(info.piaoshu) ;
//			$("#redu").text(info.redu) ;
			$("#xy").text(info.txt) ;








			//送礼物
			$("#slw").click(function(){
//				if(starStatus<0){
//    				alert("用户异常，暂停投票！");
//    				return false;
//    			}
				
				if(status<0){
    				alert("活动未开始");
    				return false;
    			}
				
				var date3 = new Date(time).getTime() - new Date().getTime();   //时间差的毫秒数
		    	
		    	if(date3 <= 0){
		    		alert("活动已结束");
		    		return false;
		    	}
				window.location.href=liwu_path+eventid+","+prouuid1;
			});
			
			//送礼物
			$("#sss").click(function(){
				
				if(status<0){
    				alert("活动未开始");
    				return false;
    			}
				
				var date3 = new Date(time).getTime() - new Date().getTime();   //时间差的毫秒数
		    	
		    	if(date3 <= 0){
		    		alert("活动已结束");
		    		return false;
		    	}
		    	
				window.location.href=liwu_path+eventid+","+prouuid1;
			});
//投票
    		$("#tp").click(function(){
    			if(starStatus<0){
    				alert("用户存在刷票嫌疑，封停24小时！");
    				return false;
    			}

    			if(status<0){
    				alert("活动未开始");
    				return false;
    			}
    			
    			
    			var date3 = new Date(time).getTime() - new Date().getTime();   //时间差的毫秒数
		    	
		    	if(date3 <= 0){
		    		alert("活动已结束");
		    		return false;
		    	}
    			
    			$.ajax({
    				type: 'POST',
    		        url:toupiao_url ,
    		        dataType : "json" ,
    		        data: {
    		        	id:parseInt(prouuid1),
    		        	eventid:eventid,
    		        	//暂存openid
    		        	name:'4444444'
    		        	
    		        },
    		        success:
    		            function(data){
    		        		//获取商品详情数据
    		        		if(data==null){
    		        			alert("投票失败");
    		        			$("#tpjg").text("投票失败") ;
    		        			return false;
    		        		}else if(data.length>2){
//    		        			alert(data);
//    		        			alert(data.indexOf(","));
    		        			var ar=data.split(',');
//    		        			alert(ar);
//    		        			alert(ar[0]);
    		        			 if(ar[0]=='2'){
     		        				alert("不能重复投票，您还能投"+ar[1]+"票！");
    		        				 $("#tpjg").text("不能重复投票，您还能投"+ar[1]+"票！") ;
    		        				 

    		        			}else if(ar[0]=='1'){
        		        			alert("投票成功，您还能投"+ar[1]+"票！");
    		        				$("#ps").text((parseInt($("#ps").text())+1));

        		        			$("#tpjg").text("投票成功，您还能投"+ar[1]+"票！") ;
    		        			}
    		        			
    		        			
    		        			return false;
    		        		}else if(data.indexOf("2_")==0){
    		        			alert("不能重复投票，您还能投"+data.splt('_')[1]+"票！");
    		        			$("#tpjg").text("不能重复投票，您还能投"+data.splt('_')[1]+"票！") ;
    		        			return false;
    		        		}else if(data=='3'){
    		        			alert("您今天已投满3票！");
    		        			$("#tpjg").text("您今天已投满3票！") ;
    		        			return false;
    		        		}
    		            },
    		        error:
    		            function(data,textStatus){
    		        		alert("投票异常");
    		            }
    			});
    		});


    		//报名
    		$("#bm").click(function(){
    			//event 信息
    		    $.ajax({
    		        type: 'POST',
    		        url: event_url,
    		        dataType : "json" ,
    		        data: {id:eventid},
    		        async:false,
    		        success:
    		            function(data){
    		        	
    		        	if(data.status == -1){
    		        		alert("报名已结束");
    		        	}else{
    		        		window.location.href = baoming_path+eventid;
    		        	}
    		        	
    		            },
    		        error:
    		            function(data,textStatus){
    		            }
    		    });
    			
    			
        			
            });

    	}



}
    
    
    var shareTitle ="我是"+$("#uname").text()+",编号"+$("#bh").text()+",正在参加"+ title;
    var shareDesc = shareTitle;  
    var shareImg = base+$("#banner1").attr("src") ;  
    var url = window.location.href;  
//    alert(shareTitle);
//    alert(shareDesc);
//    alert(shareImg);
//    alert(url);
    wx.ready(function() {  
//    	alert("1");
    	share();  
    });  
    function share() {
//    	alert("2");
//    	alert(shareTitle);
//        alert(shareDesc);
//        alert(shareImg);
//        alert(url);
        wx.showOptionMenu();  
        wx.onMenuShareTimeline({  
            title : shareTitle, // 分享标题  
            link : url, // 分享链接  
            imgUrl : shareImg,  
            desc : shareDesc, // 分享描述  
            success : function() {  
            }  
        });  
        wx.onMenuShareAppMessage({  
            title : shareTitle, // 分享标题  
            link : url, // 分享链接  
            imgUrl : shareImg,  
            desc : shareDesc, // 分享描述  
            success : function() {  
            }  
        });  
    }
    
    wx.error(function (res) {
    	});

});