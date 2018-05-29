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
	src="<%=path%>/resource/uploadify/jquery.uploadify.min.js"></script>
	
	
	<script type="text/javascript">


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




function sbm(){
	//$('#file').empty();
	//$('#fileQueue').empty();
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
					action="<%=path%>/productPic/saveProductPic.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">已存在 </c:if>
						<c:if test="${errcode == 101 }">图片 排序   不能为空</c:if>
					</div>

            <div class="content-title">修改图组</div>


            <div class="content-main">
<input type="hidden" name="dowhat" value="edit" />
<input type="hidden" name="productUuid" id="productUuid" value="${productPic.productUuid}" />

                    <div class="table2">

                        <%int i=0; %>
									<c:forEach var="item" items="${productPic.productPicList}">
                        
                        <div class="add1">
                        
                        
	                        <div class="addproduct">
	                            <p><img src="${item.picUrl}" width="50px" height="50px"/></p>
	                            <input type="hidden" name="productPicList[<%=i%>].productPicId" id="" value="${item.productPicId}"/>
											<input class="input-default" type="text" name="productPicList[<%=i%>].picUrl" value="${item.picUrl}" id="pic_url<%=i%>" />
	                		</div>
	                						
	                	    <div class="addproduct3">
	                			<p><span class="redicon">*</span>图片排序:</p>
								<input style="width:40px;" class="input-default" type="text" name="productPicList[<%=i%>].sort" id="sort" value="${item.sort}"/>
							</div>
                        
                        
                        <div class="addproduct4" >
	                            <p><span class="redicon">*</span>状态:</p>
	                            <select name="productPicList[<%=i%>].status" id="selstatus" class="input-default">
												<option value="-1" <c:if test="${item.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${item.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${item.status == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${item.status == 2}">selected="selected" </c:if> >已下架</option>
										</select>  
	                		</div>

</div>
							<div class="clear"></div>				
										
								<%i++; %>
									</c:forEach>
									
									
									
									<% if(i<=3){ %>
									<c:forEach var="s"  begin="<%=i %>" end="3">
									
									
									<div class="add1">
	                        <div class="addproduct">
	                            <p><span class="redicon">*</span>橱窗图片${s+1}:</p>
	                            <input type="text" class="input-default" name="productPicList[${s}].picUrl" id="pic_url${s}" />
	                            <input type="hidden" name="productPicList[${s}].productUuid"  value="${productPic.productUuid}" />
	                            <input type="hidden" name="productPicList[${s}].status"  value="1" />
	                		</div>
	                						
	                	    <div class="addproduct3">
	                			<p><span class="redicon">*</span>图片排序:</p>
								<input style="width:40px;" class="input-default" type="text" name="productPicList[${s}].sort" id="" value="0" />
							</div>
							
                        </div>

							<div class="clear"></div>
							
									
									</c:forEach>
								<%} %>

                    </div>

                    <div id="returnbutton">
                      <button style="float:right;margin:9px 10px;" type="button"  class='modify' id="goBtn" onclick="javascript:location='${home}/product/productListPage.do'">返回上一级</button>
                      <input style="float:right;margin:9px 10px;background-color: #FFFFFF;border-bottom: 2px solid #b0b4b5;" class='modify' type='button'  onclick="sbm()" value='保存' style="margin-top:20px;">
                    </div>

               

            </div>

		</form>

        </div>


</div>

</body>
</html>