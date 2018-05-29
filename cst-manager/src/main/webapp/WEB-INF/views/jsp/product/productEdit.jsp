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
	if($("#code").val()==""){	
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else if($("#name").val()==0){
		$("#errorm5").fadeIn(300);
		$("#errorm5").fadeOut(5000);
	}else if($("#price").val()==0){
		$("#errorm6").fadeIn(300);
		$("#errorm6").fadeOut(5000);
	}else if($("#costPrice").val()==""){
		$("#errorm7").fadeIn(300);
		$("#errorm7").fadeOut(5000);
	}else{
		$("#shareForm").attr("action", "<%=path%>/product/saveProduct.do");
		$("#shareForm").submit();
	}
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
					<div id="errorm4">请填写商品编号</div>
        			<div id="errorm5">请填写商品名称</div>
        			<div id="errorm6">请填写销售价格</div>
        			<div id="errorm7">请填写订货价格</div>

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">每项必填</c:if>
					</div>

            <div class="content-title">修改商品</div>


            <div class="content-main">
            <input type="hidden" name="seoName" value="${product.seoName}" />
            <input type="hidden" name="seoStatus" value="${product.seoStatus}" />
            <input type="hidden" name="seoTypeId" value="${product.seoTypeId}" />
            <input type="hidden" name="page.currentPage" value="${product.page.currentPage}" />
            <input type="hidden" name="page.totalPage" value="${product.page.totalPage}" />
            <input type="hidden" name="page.totalResult" value="${product.page.totalResult}" />
            <input type="hidden" name="page.showCount" value="${product.page.showCount}" />
<input type="hidden" name="productUuid" id="productUuid"
											value="${product.productUuid }" /> <input type="hidden" name="dowhat"
											value="edit" />
                    <div class="table">
                    <div class="addproduct">
                    <p><img src="${product.pic}" width="50px" height="50px"/></p>
                            
                            <input type="text" class="input-default" name="pic" id="pic" value="${product.pic }"/>
                        </div>
                        
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>商品编号:</p>
                             <input class="input-default" type="text" name="code" id="code"  value="${product.code }"/>
                        </div>
                        
                          <div class="clear"></div>
                          
                        <div class="addproduct">
                            <p><span class="redicon">*</span>商品名称:</p>
                            <input class="input-default" type="text" name="name" id="name" value="${product.name }"/>
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>销售价格:</p>
                            <input class="input-default" type="text" name="price" id="price" value="${product.price }"/>
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon">*</span>订货价格:</p>
                            <input class="input-default" type="text" name="costPrice" id="costPrice"  value="${product.costPrice }"/>
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>商品类型:</p>
                             <select name="typeId" id="seltypeId" class="input-default">
												<c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}"  <c:if test="${product.typeId == item.parameterId}">selected="selected" </c:if>>${item.parameterName}</option>
												</c:forEach>
										</select>
                        </div>


                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon">*</span>商品状态:</p>
                            
                            <select name="status" id="selstatus" class="input-default">
												<option value="-1" <c:if test="${product.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${product.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${product.status == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${product.status == 2}">selected="selected" </c:if> >已下架</option>
										</select>

                        </div>
                        
                        <div class="addproduct1">
                            <p><span class="redicon"></span>商品描述:</p>
                           <input class="input-default" type="text" name="describes" id="describes" value="${product.describes}"/>
                        </div>
                        
                         <div class="clear"></div>
                        
                        <div class="addproduct">
                            <p><span class="redicon"></span>seo关键字:</p>
                           <input class="input-default" type="text" name="keywords" id="keywords" value="${product.keywords}"/>
                        </div>
                        
                        <div class="addproduct1">
                            <p><span class="redicon">*</span>seo描述:</p>
                             <input  class="input-default" type="text" name="descriptionSeo" id="descriptionSeo"  value="${product.descriptionSeo}"/>
                        </div>
                        
                        <div class="clear"></div>
                        
                        <div class="addproduct">
                            <p><span class="redicon">*</span>分类-商品图:</p>
                             <input  class="input-default" type="text" name="picForAll" id="picForAll"  value="${product.picForAll}"/>
                        </div>

                        <div class="clear"></div>
                    </div>

                    <div class="table" >
                        <div class="addproduct">
                            <p><span class="redicon"></span>许可证号:</p>
                            <input class="input-default" type="text" name="license" id="license" value="${product.license }"/>
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon"></span>保质期:</p>
                           <input class="input-default" type="text" name="releaseDate" id="releaseDate" value="${product.releaseDate }"/>
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon"></span>标&nbsp;准&nbsp;号&nbsp;:</p>
                            <input class="input-default" type="text" name="standardNo" id="standardNo" value="${product.standardNo }"/>
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon"></span>原产地:</p>
                            <input class="input-default" type="text" name="provenance" id="provenance" value="${product.provenance }"/>
                        </div>

                        <div class="clear"></div>

                        <div class="addproduct">
                            <p><span class="redicon"></span>品&nbsp;&nbsp;&nbsp;&nbsp;牌&nbsp;&nbsp;:</p>
                           <input class="input-default" type="text" name="brand" id="brand" value="${product.brand }"/>
                        </div>
                        <div class="addproduct1" style="margin-bottom: 20px;">
                            <p><span class="redicon"></span>净含量:</p>
                            <input class="input-default" type="text" name="netContent" id="netContent" value="${product.netContent }"/>
                        </div>

                         <div class="clear"></div>
                        <div class="addproduct">
                            <p><span class="redicon"></span>条形码：</p>
                           <input class="input-default" type="text" name="barcode" id="barcode" value="${product.barcode }"/>
                        </div>
                        <div class="addproduct1">
                            <p><span class="redicon"></span>系&nbsp;&nbsp;&nbsp;&nbsp;列&nbsp;&nbsp;:</p>
                            <input class="input-default" type="text" name="series" id="series" value="${product.series }"/>
                        </div>

                        <div class="clear"></div>
                        <div class="addproduct">
                            <p><span class="redicon"></span>存储方式:</p>
                           <input class="input-default" type="text" name="storage" id="storage" value="${product.storage }"/>
                        </div>
                        
                        <div class="addproduct1" >
                            <p><span class="redicon"></span>排&nbsp;&nbsp;&nbsp;&nbsp;序&nbsp;&nbsp;:</p>
                            <input class="input-default" type="text" name="sort" id="sort" value="${product.sort }"/>
                        </div>

                        <div class="clear"></div>
                        
                        

                    </div>


                    <div class="table" style="margin-bottom: 20px;">
                            <textarea  style="width:800px;height:200px;"  name="description" id="description">${product.description}</textarea>


                        <div class="clear"></div>

                    </div>
                    <div id="returnbutton" style="margin-bottom:30px;">
                      <button style="float:right;margin:9px 10px;" type="button"  class='modify' id="goBtn" onclick="javascript:history.back();">返回上一级</button>
                <input style="float:right;margin:9px 10px;background-color: #FFFFFF;border-bottom: 2px solid #b0b4b5;" class='modify' type='button'  onclick="sbm()" value='保存'>
                    </div>
            </div>

		</form>

        </div>


</div>

</body>
</html>