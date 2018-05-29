<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@include file="../include/tag.jsp"%>
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


function sbm(){
	//$('#file').empty();
	//$('#fileQueue').empty();
	$("#shareForm").attr("action", "<%=path%>/product/saveProduct.do");
	$("#shareForm").submit();
}


function del(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/product/delProduct.do");
	$("#shareForm").submit();
}


function edit(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/product/productAddEditIni.do");
	$("#shareForm").submit();
}



  
function uploadifyUpload(){  
    $('#file').uploadifyUpload();  
}  

</script>


</head>

<body>
	<div class="container">
		<%@include file="../include/top.jsp"%>
		<!--main-->
		<div class="cat_main">

			<%@include file="../include/left.jsp"%>
			<div class="cat_m_right">
				<div class="cat_title">商品管理</div>
				<div class="cat_overview"></div>
				<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/product/productListPage.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">名称，编号 ,价格，类型，描述，图片  不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>新增商品</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										
										<th>名称</th>
										<th>价格</th>
										<th>原价</th>
										<th>编号</th>
										<th>类型</th>
										<th>图片</th>
									</tr>
								</thead>
								<input type="hidden" name="dowhat" value="add" />
								<tbody>
									<tr>
										<td><input type="text" name="name" id="name" />
											</td>
										<td><input type="text" name="price" id="price" value="0"/>
										</td>
										<td><input type="text" name="costPrice" id="costPrice"  value="0"/>
										</td>
										<td><input type="text" name="code" id="code" />
										</td>
										<td><select name="typeId" id="seltypeId"
										>
												<c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}">${item.parameterName}</option>
												</c:forEach>
										</select></td>
										<td><input type="hidden" name="pic" id="pic" />
										<input type="file" name="file" id="file" />  
                						<div id="fileQueue"></div>  
										</td>
										
									</tr>
									<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
												</tr>
								</tbody>
							</table>
							<div>
									<textarea name="description" id="description">
									</textarea>
									<script type="text/javascript" charset="utf-8">
									UE.getEditor('description');
									</script>
									<a href="#" onclick="sbm()"><i
												class="page_icon detail_icon"></i><h1 style="color:blue"> 保存</h1></a>
									</div>
						</div>
						
					</div>
					<div class="cat_table">
						<h1>已添加商品列表</h1>
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
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" name="productUuid" id="productUuid" />
									<c:forEach var="item" items="${productList}">
										<tr>
											<td><img src="<%=path%>/resource/upload/product/${item.pic}" width="50px" height="50px"/></td>
											<td>${item.name}</td>
											<td>${item.price}</td>
											<td>${item.costPrice}</td>
											<td>${item.code}</td>
											<td>${item.typeIdStr}</td>
											<td><c:if test="${item.status == -1}">已删除</c:if>
											<c:if test="${item.status == 0}">草稿</c:if>
											<c:if test="${item.status == 1}">已上架</c:if>
											<c:if test="${item.status == 2}">已下架</c:if></td>
											<td><a href="#" id="${item.productUuid}" onclick="del(this)" ><i class="page_icon detail_icon"></i>删除</a>
												<a href="#" id="${item.productUuid}" onclick="edit(this)"><i class="page_icon detail_icon"></i>修改</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<%@include file="../include/innerNavigation.jsp"%>

						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

</body>
</html>
