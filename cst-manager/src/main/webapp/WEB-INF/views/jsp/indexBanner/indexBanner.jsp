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
    <!--<link rel="icon" href="/static/images/favicon.ico?v=1" type="image/x-icon"/>-->

<title>${webTitle }</title>
<link rel="icon" href="<%=path%>/resource/images/logo.png" />
<link href="<%=path%>/resource/common/reset.css" rel="stylesheet"
	type="text/css" />
<link href="<%=path%>/resource/css/rollingDiagram.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
	<script src="<%=path%>/resource/uploadify/swfobject.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/uploadify/jquery.uploadify.min.js"></script>
		<link rel="stylesheet" type="text/css"
	href="<%=path%>/resource/uploadify/uploadify.css">	

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
        'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp/xlsx/xls.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
        'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.xlsx;*.xls;*.bmp',//允许的格式    
        
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
	
	function up() {
		$('#file0').uploadify('upload');
	}
	
	
	
	function del(obj){
		$('#black1').fadeIn();
		//确定事件
		$("#surebtn").click(function(){
			$("#indexBannerIdfordel").attr("value",$(obj).attr("id"));
			$("#shareForm").attr("action", "<%=path%>/indexBanner/indexBannerDelete.do");
			$("#shareForm").submit();
		})
		//取消事件
		$('#canclebtn').click(function(){
	        $('#black1').fadeOut();
	    });
	}
	
	
</script>
</head>
<body>

<div class="black" id="black1">

    <div class="fadebox">
        <div class="ordertitle">
            <h1>确认删除？</h1>
        </div>

        <div class="orderbtn">
            <input id="surebtn" class="btn-default" type="button" value="确定">
            <input id="canclebtn" class="btn-default" type="button" value="取消">
        </div>
    </div>
</div>

<div class="container">

<%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>
        <div class="content">

            <div class="content-title">文件上传</div>


            <div class="content-main">
                <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/indexBanner/indexBannerAddEdit.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 101 }">图片 排序 链接   不能为空</c:if>
					</div>
					
                    <table class="table">
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
										<input type="file" name="file${s}" id="file${s}" onchange="up()"/>  
                						<div id="fileQueue${s}"></div>  </td>
										</tr>
										
								</c:forEach>
								
								</c:if>
								
								<c:if test="${indexBanner.dowhat == 'edit'}">
								<%int i=0; %>
									<c:forEach var="item" items="${indexBanner.indexBannerList}">
										<tr>
											<td class="col1" id="st<%=i%>"><img src="<%=path%>/resource/upload/productIndex/${item.bannerUrl}" width="50px" height="50px"/></td>
											<td class="col2"><input type="text" name="indexBannerList[<%=i%>].productUrl" id="" value="${item.productUrl}"/></td>
											
											<td class="col3">
											<input type="hidden" name="indexBannerList[<%=i%>].indexBannerId" id="" value="${item.indexBannerId}"/>
											<input type="text"  name="indexBannerList[<%=i%>].bannerUrl" id="pic_url<%=i%>" value="${item.bannerUrl}"/>
										</td>
											<td class="col4"><input type="text" name="indexBannerList[<%=i%>].sort" id="sort" value="${item.sort}"/></td>
											<td class="col5"><select class="input-default" name="indexBannerList[<%=i%>].state" id="selstatus">
												<option value="-1" <c:if test="${item.state == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${item.state == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${item.state == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${item.state == 2}">selected="selected" </c:if> >已下架</option>
										</select></td>
										
										<td class="col6"><select class="input-default" name="indexBannerList[<%=i%>].type" id="type">
												<option value="1" <c:if test="${item.type == 1}">selected="selected" </c:if> >电商</option>
												<option value="2" <c:if test="${item.type == 2}">selected="selected" </c:if> >微信</option>
										</select></td>
												
												<td class="col7"><input id="${item.indexBannerId}" onclick="del(this)" class='delete' type='button' value='删除'></td>
										</tr>
										<%i++; %>
									</c:forEach>
									
									
									<% if(i<12){ %>
									<c:forEach var="s"  begin="<%=i %>" end="11">

								<tr>
											<td class="col1" id="st${s}"></td>
											
											<td class="col2"><input type="text" name="indexBannerList[${s}].productUrl" id="" value=""/></td>
											<td class="col3">
											<input type="text"  name="indexBannerList[${s}].bannerUrl" id="pic_url${s}" />
										</td>
											<td class="col4"><input type="text" name="indexBannerList[${s}].sort" id="" value="0"/></td>
											<td class="col5"><select class="input-default" name="indexBannerList[${s}].state" id="selstatus">
												<option value="-1"  >已删除</option>
												<option value="0"  >草稿</option>
												<option value="1" selected="selected"  >已上架</option>
												<option value="2"  >已下架</option>
										</select></td>
										<td class="col6"><select class="input-default" name="indexBannerList[${s}].type" id="type">
												<option value="1"   >电商</option>
												<option value="2"  >微信</option>
										</select></td>
										<td>
												</td>
										</tr>
										
								</c:forEach>
								<%} %>
									
									</c:if>

                        </tbody>
                        

                    </table>
                     
                </form>
                    <div style="margin-bottom:30px;background-color:#e4eaec;width:99%;height:40px;">
                        <input class='samestyle' type='button'  onclick="sbm()" value='保存'>
                    </div>

            </div>

                   

        </div>

</div>


<script type="text/javascript">
function onSelectChange(obj,toSelId){
 setSelect(obj.value,toSelId);
}
function setSelect(fromSelVal,toSelId){
 //alert(document.getElementById("province").selectedIndex);
 document.getElementById(toSelId).innerHTML="";
 jQuery.ajax({
  url: "<%=path%>/nation/getNation.do",
				cache : false,
				data : "parent=" + fromSelVal,
				success : function(data) {
					createSelectObj(data, toSelId);
				}
			});
		}
		function createSelectObj(data, toSelId) {
			var arr = jsonParse(data);
			if (arr != null && arr.length > 0) {
				var obj = document.getElementById(toSelId);
				obj.innerHTML = "";
				var nullOp = document.createElement("option");
				nullOp.setAttribute("value", "");
				nullOp.appendChild(document.createTextNode("请选择"));
				obj.appendChild(nullOp);
				for ( var o in arr) {
					var op = document.createElement("option");
					op.setAttribute("value", arr[o].id);
					//op.text=arr[o].name;//这一句在ie下不起作用，用下面这一句或者innerHTML
					if (toSelId == 'selprovince') {
						op
								.appendChild(document
										.createTextNode(arr[o].province));
					} else if (toSelId == 'selcity') {
						op.appendChild(document.createTextNode(arr[o].city));
					} else if (toSelId == 'seldistrict') {
						op
								.appendChild(document
										.createTextNode(arr[o].district));
					}else if (toSelId == 'selstreet') {
						op
						.appendChild(document
								.createTextNode(arr[o].street));
					}
					obj.appendChild(op);
				}
			}
		}
		setSelect('1', 'selprovince');
	</script>


</body>
</html>