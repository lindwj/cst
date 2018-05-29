<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../include/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">

<title>${webTitle }</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/pictureDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=path%>/resource/uploadify/uploadify.css">
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>

	
	<script type="text/javascript"
	src="<%=path%>/resource/ueditor/ueditor.config.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/ueditor/ueditor.all.js"></script>
	
	<script type="text/javascript"
	src="<%=path%>/resource/ueditor/zh-cn.js"></script>
	
	<script type="text/javascript"
	src="<%=path%>/resource/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">
$(document).ready(function() {  
    $("#file").uploadify({  
        /*注意前面需要书写path的代码*/  
        'swf'       : '<%=path%>/resource/uploadify/uploadify.swf',  
        'uploader'         : '<%=path%>/upload/uploadAction.do',  
        'cancelImg'      : '<%=path%>/resource/uploadify/uploadify-cancel.png',  
        'queueID'        : 'fileQueue', //和存放队列的DIV的id一致  
        'fileDataName'   : 'file', //和以下input的name属性一致  
        'auto'           : true, //是否自动开始  
        'multi'          : false, //是否支持多文件上传  
        'buttonText'     : '文件上传', //按钮上的文字  
        'simUploadLimit' : 1, //一次同步上传的文件数目  
        'sizeLimit'      : 19871202, //设置单个文件大小限制  
        'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
        'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
        'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式    
        
      //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
        	$('#pic').val(file.name);
        	$('#file').attr("name","");
            //alert(file.name + ' | ' + response + ':' + data);  
        },  
        //上传错误  
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString); 
        	alert("文件:" + file.name + "上传失败");
        },  
        //上传完成  
        'onUploadComplete' : function(file) {  
            //alert('The file ' + file.name + ' finished processing.');  
        } 
    });  
});  


$(document).ready(function() {  
	$("#file0").uploadify({  
        /*注意前面需要书写path的代码*/  
        'swf'       : '<%=path%>/resource/uploadify/uploadify.swf',  
        'uploader'         : '<%=path%>/upload/uploadPicAction.do',  
        'cancelImg'      : '<%=path%>/resource/uploadify/uploadify-cancel.png',  
        'queueID'        : 'fileQueue0', //和存放队列的DIV的id一致  
        'fileDataName'   : 'file0', //和以下input的name属性一致  
        'auto'           : true, //是否自动开始  
        'multi'          : false, //是否支持多文件上传  
        'buttonText'     : '文件上传', //按钮上的文字  
        'simUploadLimit' : 1, //一次同步上传的文件数目  
        'sizeLimit'      : 19871202, //设置单个文件大小限制  
        'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
        'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
        'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式    
        
      //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
        	$('#pic_url0').val(file.name);
        	$('#st0').text(file.name);
        	$('#file0').attr("name","");
            //alert(file.name + ' | ' + response + ':' + data);  
        },  
        //上传错误  
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString); 
        	alert("文件:" + file.name + "上传失败");
        },  
        //上传完成  
        'onUploadComplete' : function(file) {  
            //alert('The file ' + file.name + ' finished processing.');  
        } 
    });
	
    $("#file1").uploadify({  
        /*注意前面需要书写path的代码*/  
        'swf'       : '<%=path%>/resource/uploadify/uploadify.swf',  
        'uploader'         : '<%=path%>/upload/uploadPicAction.do',  
        'cancelImg'      : '<%=path%>/resource/uploadify/uploadify-cancel.png',  
        'queueID'        : 'fileQueue1', //和存放队列的DIV的id一致  
        'fileDataName'   : 'file1', //和以下input的name属性一致  
        'auto'           : true, //是否自动开始  
        'multi'          : false, //是否支持多文件上传  
        'buttonText'     : '文件上传', //按钮上的文字  
        'simUploadLimit' : 1, //一次同步上传的文件数目  
        'sizeLimit'      : 19871202, //设置单个文件大小限制  
        'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
        'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
        'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式    
        
      //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
        	$('#pic_url1').val(file.name);
        	$('#st1').text(file.name);
        	$('#file1').attr("name","");
            //alert(file.name + ' | ' + response + ':' + data);  
        },  
        //上传错误  
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString); 
        	alert("文件:" + file.name + "上传失败");
        },  
        //上传完成  
        'onUploadComplete' : function(file) {  
            //alert('The file ' + file.name + ' finished processing.');  
        } 
    });
    
    
    $("#file2").uploadify({  
        /*注意前面需要书写path的代码*/  
        'swf'       : '<%=path%>/resource/uploadify/uploadify.swf',  
        'uploader'         : '<%=path%>/upload/uploadPicAction.do',  
        'cancelImg'      : '<%=path%>/resource/uploadify/uploadify-cancel.png',  
        'queueID'        : 'fileQueue2', //和存放队列的DIV的id一致  
        'fileDataName'   : 'file2', //和以下input的name属性一致  
        'auto'           : true, //是否自动开始  
        'multi'          : false, //是否支持多文件上传  
        'buttonText'     : '文件上传', //按钮上的文字  
        'simUploadLimit' : 1, //一次同步上传的文件数目  
        'sizeLimit'      : 19871202, //设置单个文件大小限制  
        'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
        'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
        'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式    
        
      //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
        	$('#pic_url2').val(file.name);
        	$('#st2').text(file.name);
        	$('#file2').attr("name","");
            //alert(file.name + ' | ' + response + ':' + data);  
        },  
        //上传错误  
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString); 
        	alert("文件:" + file.name + "上传失败");
        },  
        //上传完成  
        'onUploadComplete' : function(file) {  
            //alert('The file ' + file.name + ' finished processing.');  
        } 
    });
    
    
    
    $("#file3").uploadify({  
        /*注意前面需要书写path的代码*/  
        'swf'       : '<%=path%>/resource/uploadify/uploadify.swf',  
        'uploader'         : '<%=path%>/upload/uploadPicAction.do',  
        'cancelImg'      : '<%=path%>/resource/uploadify/uploadify-cancel.png',  
        'queueID'        : 'fileQueue3', //和存放队列的DIV的id一致  
        'fileDataName'   : 'file3', //和以下input的name属性一致  
        'auto'           : true, //是否自动开始  
        'multi'          : false, //是否支持多文件上传  
        'buttonText'     : '文件上传', //按钮上的文字  
        'simUploadLimit' : 1, //一次同步上传的文件数目  
        'sizeLimit'      : 19871202, //设置单个文件大小限制  
        'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
        'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
        'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式    
        
      //上传成功  
        'onUploadSuccess' : function(file, data, response) {  
        	$('#pic_url3').val(file.name);
        	$('#st3').text(file.name);
        	$('#file3').attr("name","");
            //alert(file.name + ' | ' + response + ':' + data);  
        },  
        //上传错误  
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorString); 
        	alert("文件:" + file.name + "上传失败");
        },  
        //上传完成  
        'onUploadComplete' : function(file) {  
            //alert('The file ' + file.name + ' finished processing.');  
        } 
    });
});  


UE.Editor.prototype._bkGetActionUrl=UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl=function (action){
	if(action=='/upload/pUpload.do'){
		return "<%=path%>/upload/pUpload.do";
	}else{
		return this._bkGetActionUrl.call(this,action);
	}
 }


function sbm(){
	//$('#file').empty();
	//$('#fileQueue').empty();
	$("#shareForm").attr("action", "<%=path%>/product/saveProduct.do");
	$("#shareForm").submit();
}




  
function uploadifyUpload(){  
    $('#file').uploadifyUpload();  
}  

</script>

</head>
<body>



<div class="container">

      <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        <div class="content">
        <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/product/productListPage.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">每项必填，至少上传一组橱窗图片</c:if>
					</div>

            <div class="content-title">新增商品</div>


            <div class="content-main">
<input type="hidden" name="dowhat" value="add" />
                    <div class="table">
                        <div class="addproduct">
                            <p><span class="redicon">*</span>商品名称:</p>
                            <input class="input-default" type="text" name="name" id="name" />
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>销售价格:</p>
                            <input class="input-default" type="text" name="price" id="price" value="0"/>
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon">*</span>原始价格:</p>
                            <input class="input-default" type="text" name="costPrice" id="costPrice"  value="0"/>
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>商品类型:</p>
                             <select class="input-default" name="typeId" id="seltypeId"  style="height:28px;">
												<c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}">${item.parameterName}</option>
												</c:forEach>
										</select>
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon">*</span>橱窗主图：</p>
                            <input type="hidden" name="pic" id="pic" />
										<input type="file" name="file" id="file" />  
                						<div id="fileQueue"></div>
                        </div>
                        
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>商品编号:</p>
                             <input style="height:26px;" class="input-default" type="text" name="code" id="code"  />
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct2">
                            <p><span class="redicon">*</span>商品属性:</p>
                            <input style="width:200px;margin-left:20px;height:26px;" class="input-default" type="text" class="input1" name="productAttributeList[0].name" placeholder="属性名称">
                            <input style="width:200px;margin-left:17px;height:26px;" class="input-default" type="text" class="input2" name="productAttributeList[0].value" placeholder="属性值">
                            <input style="width:200px;margin-left:17px;height:26px;" class="input-default" type="text" class="input3" name="productAttributeList[0].unit" placeholder="属性单位">

                        </div>

                        <div class="clear"></div>
                    </div>

                    <div class="table" >
                        <div class="addproduct">
                            <p><span class="redicon">*</span>许可证号:</p>
                            <input class="input-default" type="text" name="license" id="license" />
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>保质期:</p>
                           <input class="input-default" type="text" name="releaseDate" id="releaseDate" />
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon">*</span>标&nbsp;准&nbsp;号&nbsp;:</p>
                            <input class="input-default" type="text" name="standardNo" id="standardNo" />
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>原产地:</p>
                            <input class="input-default" type="text" name="provenance" id="provenance" />
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon">*</span>品&nbsp;&nbsp;&nbsp;&nbsp;牌&nbsp;&nbsp;:</p>
                           <input class="input-default" type="text" name="brand" id="brand" />
                        </div>
                        <div class="addproduct1" style="margin-bottom: 20px;">
                            <p><span class="redicon">*</span>净含量:</p>
                            <input class="input-default" type="text" name="netContent" id="netContent"/>
                        </div>

                        <div class="clear"></div>

                    </div>

                    <div class="table2">

                        <c:forEach var="s"  begin="0" end="3">
                        
                        <div class="add1">
	                        <div class="addproduct">
	                            <p><span class="redicon">*</span>橱窗图片${s+1}:</p>
	                            <input type="hidden" name="productPicList[${s}].picUrl" id="pic_url${s}" />
								<input style="margin-left:100px;" type="file" name="file${s}" id="file${s}" />  
	                			<div id="fileQueue${s}"></div>
	                		</div>
	                						
	                	    <div class="addproduct3">
	                			<p><span class="redicon">*</span>图片排序:</p>
								<input type="text" name="productPicList[${s}].sort" id="" value="0" />
							</div>
                        </div>

							<div class="clear"></div>				
										
								</c:forEach>

                    </div>

                    <div class="table" style="margin-bottom: 20px;">
                            <textarea name="description" id="description">
									</textarea>
									<script type="text/javascript" charset="utf-8">
									UE.getEditor('description');
									</script>


                        <div class="clear"></div>

                    </div>
                    
                    <div class="clear"></div>
                    
                    <div id="returnbutton">
                      <button style="float:right;margin:9px 10px;" type="button"  class='modify' id="goBtn" onclick="javascript:location='${home}/product/productListPage.do'">返回上一级</button>
                    </div>
                     

                <input style="margin-top:15px;" class='modify' type='button'  onclick="sbm()" value='保存'>

            </div>

		</form>

        </div>


</div>

</body>
</html>