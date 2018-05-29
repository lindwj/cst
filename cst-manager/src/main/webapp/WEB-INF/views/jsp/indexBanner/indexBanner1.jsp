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
	src="<%=path%>/resource/uploadify/jquery.uploadify.min.js"></script>
<title>${webTitle }</title>

<script type="text/javascript">

$(document).ready(function() {  
	$("#file0").uploadify({  
        /*注意前面需要书写path的代码*/  
        'swf'       : '<%=path%>/resource/uploadify/uploadify.swf',  
        'uploader'         : '<%=path%>/upload/uploadIndexAction.do',  
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
	
    
    
    
});  

	function sbm() {
		$("#shareForm").submit();
	}
	
	
	function del(obj){
		
		$("#indexBannerIdfordel").attr("value",$(obj).attr("id"));
		
		
		$("#shareForm").attr("action", "<%=path%>/indexBanner/indexBannerDelete.do");
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
					action="<%=path%>/indexBanner/indexBannerAddEdit.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 101 }">  所有项 不能为空</c:if>
					</div>
					<div class="cat_table">
						<h1>图组列表</h1>
						<div class="cat_alarm">

							<table cellpadding="0" cellspacing="0" class="alarm_table">
								<thead>
									<tr>
										<th>分公司</th>
										<th>文件上传</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" name="indexBannerId" id="indexBannerIdfordel" value="" />
								<input type="hidden" name="dowhat" id="dowhat" value="${indexBanner.dowhat}" />
								
								<c:if test="${indexBanner.dowhat == 'add'}">
								<c:forEach var="s"  begin="0" end="0">

								<tr>
											<td><select  id="selprovince"  name="indexBannerList[${s}].sort" class="input-default"
											onchange="onSelectChange(this,'selcity');"></select></td>
											<td>
											<input type="hidden" name="indexBannerList[${s}].bannerUrl" id="pic_url${s}" />
										<input type="file" name="file${s}" id="file${s}" />  
                						<div id="fileQueue${s}"></div>  </td>
										</tr>
										
								</c:forEach>
								
								</c:if>
								
								<c:if test="${indexBanner.dowhat == 'edit'}">
								<%int i=0; %>
									<c:forEach var="item" items="${indexBanner.indexBannerList}">
										<tr>
											<td id="st<%=i%>"><img src="<%=path%>/resource/upload/productIndex/${item.bannerUrl}" width="50px" height="50px"/></td>
											<td><input type="text" name="indexBannerList[<%=i%>].productUrl" id="" value="${item.productUrl}"/></td>
											
											<td>
											<input type="hidden" name="indexBannerList[<%=i%>].indexBannerId" id="" value="${item.indexBannerId}"/>
											<input type="hidden" name="indexBannerList[<%=i%>].bannerUrl" id="pic_url<%=i%>" />
										<input type="file" name="file<%=i%>" id="file<%=i%>" />  
                						<div id="fileQueue<%=i%>"></div>  </td>
											<td><input type="text" name="indexBannerList[<%=i%>].sort" id="sort" value="${item.sort}"/></td>
											<td><select name="indexBannerList[<%=i%>].state" id="selstatus">
												<option value="-1" <c:if test="${item.state == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${item.state == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${item.state == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${item.state == 2}">selected="selected" </c:if> >已下架</option>
										</select></td>
										<td>
												<a href="#" id="${item.indexBannerId}" onclick="del(this)"><i class="page_icon detail_icon"></i>删除</a>
												</td>
										</tr>
										<%i++; %>
									</c:forEach>
									
									
									<% if(i<=2){ %>
									<c:forEach var="s"  begin="<%=i %>" end="2">

								<tr>
											<td id="st${s}"></td>
											
											<td><input type="text" name="indexBannerList[${s}].productUrl" id="" value=""/></td>
											<td>
											<input type="hidden" name="indexBannerList[${s}].bannerUrl" id="pic_url${s}" />
										<input type="file" name="file${s}" id="file${s}" />  
                						<div id="fileQueue${s}"></div>  </td>
											<td><input type="text" name="indexBannerList[${s}].sort" id="" value="0"/></td>
											<td><select name="indexBannerList[${s}].state" id="selstatus">
												<option value="-1"  >已删除</option>
												<option value="0"  >草稿</option>
												<option value="1" selected="selected"  >已上架</option>
												<option value="2"  >已下架</option>
										</select></td>
										<td>
												</td>
										</tr>
										
								</c:forEach>
								<%} %>
									
									</c:if>
								</tbody>
							</table>
							
							<div><a href="#" id="" onclick="sbm()"><i class="page_icon detail_icon"></i><h1>批量保存</h1></a></div>


						</div>
					</div>
				</form>
			</div>
		</div>
		<!--main-->
	</div>

</body>
</html>
