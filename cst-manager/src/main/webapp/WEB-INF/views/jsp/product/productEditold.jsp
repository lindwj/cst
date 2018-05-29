<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../include/tag.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link href="<%=path%>/resource/css/Pagestyle.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/resource/css/icon.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resource/css/dropkick.css">
	<link rel="stylesheet" type="text/css"
	href="<%=path%>/resource/uploadify/uploadify.css">	
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/toastr/toastr.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/global.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery.dropkick-min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/layer/layer.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
	
	
	<script type="text/javascript"
	src="<%=path%>/resource/ueditor/ueditor.config.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/ueditor/ueditor.all.js"></script>
	
	<script type="text/javascript"
	src="<%=path%>/resource/ueditor/zh-cn.js"></script>
	
	<script type="text/javascript"
	src="<%=path%>/resource/uploadify/jquery.uploadify.min.js"></script>
<title>${webTitle }</title>

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
		return "http://localhost:8080/business-manager/upload/pUpload.do";
	}else{
		return this._bkGetActionUrl.call(this,action);
	}
 }

	function sbm() {
		$("#shareForm").submit();
	}
</script>
</head>

<body>
	<div class="container">
		<%@ include file="../include/top.jsp"%>
		<!--main-->
		<div class="cat_main">

			<%@ include file="../include/left.jsp"%>
			<div class="cat_m_right">
				<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/product/saveProduct.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == '100' }">商品已存在 </c:if>
						<c:if test="${errcode == '101' }">名称，编号 ,价格，类型，描述，图片  不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>修改商品</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>图片</th>
										<th>名称</th>
										<th>价格</th>
										<th>原价</th>
										<th>编号</th>
										<th>类型</th>
										<th>状态</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="hidden" name="productUuid" id="productUuid"
											value="${product.productUuid }" /> <input type="hidden" name="dowhat"
											value="edit" />
											<img src="<%=path%>/resource/upload/product/${product.pic}" width="80px" height="80px"/>
											
											<input type="hidden" name="pic" id="pic" />
										<input type="file" name="file" id="file" />  
                						<div id="fileQueue"></div>
											</td>
											
											<td><input type="text" name="name" id="name"
											value="${product.name }" /></td>
											<td><input type="text" name="price" id="price"
											value="${product.price }" /></td>
											<td><input type="text" name="costPrice" id="costPrice"
											value="${product.costPrice }" /></td>
											<td><input type="text" name="code" id="code"
											value="${product.code }" /></td>
											<td><select name="typeId" id="seltypeId"
										>
												<c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}"  <c:if test="${product.typeId == item.parameterId}">selected="selected" </c:if>>${item.parameterName}</option>
												</c:forEach>
										</select></td>
											<td>
											<select name="status" id="selstatus">
												<option value="-1" <c:if test="${product.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${product.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${product.status == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${product.status == 2}">selected="selected" </c:if> >已下架</option>
										</select>
										</td>

										<td></td>
									</tr>
								</tbody>
							</table>
							
							<div>
									<textarea name="description" id="description" ">
									${product.description}
									</textarea>
									<script type="text/javascript" charset="utf-8">
									UE.getEditor('description');
									</script>
									<a href="#" onclick="sbm()"><i
												class="page_icon detail_icon"></i><h1 style="color:blue"> 保存</h1></a>
									</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

</body>
</html>
