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
<link href="<%=path%>/resource/css/pictureControl.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>



<script type="text/javascript">

function sbm(){
	$("#shareForm").submit();
}


function del(obj){
	$('#black1').fadeIn();
	//确定事件
	$("#surebtn").click(function(){
		$("#productUuid").attr("value",$(obj).attr("id"));
		$("#shareForm").attr("action", "<%=path%>/product/delProduct.do");
		$("#shareForm").submit();
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#black1').fadeOut();
    });
}


function edit(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/product/productAddEditIni.do");
	$("#shareForm").submit();
}


function editPic(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/productPic/productPicAddEditIni.do");
	$("#shareForm").submit();
}


function editArt(obj){
	
	$("#productUuid").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/productAttribute/productAttributeAddEditIni.do");
	$("#shareForm").submit();
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
<form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/product/productListPage.do">
            <div class="content-title">商品管理</div>

            <div class="search">

                <label class="search-label">名称：</label>
                <input id="item-type" name="name" type="text" class="input-default" value="${product.name}">

                <label class="search-label1">类型：</label>
                <select id="item-name" name="typeId" type="text" class="input-default">
                <option value="100" <c:if test="${product.typeId == 100}">selected="selected" </c:if>>全部</option>
                    <c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}"   <c:if test="${product.typeId == item.parameterId}">selected="selected" </c:if>>${item.parameterName}</option>
												</c:forEach>
												
												
                </select>

                <label class="search-label1">状态：</label>
                <select id="atele" name="status" type="text" class="input-default">
                <option value="100" <c:if test="${product.status == 100}">selected="selected" </c:if>>全部</option>
                    <option value="-1" <c:if test="${product.status == -1}">selected="selected" </c:if> >已删除</option>
												<option value="0" <c:if test="${product.status == 0}">selected="selected" </c:if> >草稿</option>
												<option value="1" <c:if test="${product.status == 1}">selected="selected" </c:if> >已上架</option>
												<option value="2" <c:if test="${product.status == 2}">selected="selected" </c:if> >已下架</option>
												
                </select>


                <button type="button" class="btn-default" id="searchbtn" onclick="sbm()">搜索</button>

                <button type="button" class="btn-default" id="addbtn" onclick="javascript:location='<%=path%>/product/productAdd.do'">添加+</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">图片</th>
                            <th class="col2">名称</th>
                            <th class="col3">价格</th>
                            <th class="col4">原价</th>
                            <th class="col6">类型</th>
                            <th class="col7">状态</th>
                            <th class="col8">操作</th>
                        </tr>

                        </thead>
                        <tbody>
<input type="hidden" name="productUuid" id="productUuid" />
                            
                            <c:forEach var="item" items="${productList}">
										<tr>
											<td class="col1"><img src="${item.pic}" width="50px" height="50px"/></td>
											<td class="col2">${item.name}</td>
											<td class="col3">${item.price}</td>
											<td class="col4">${item.costPrice}</td>
											<td class="col6">${item.typeIdStr}</td>
											<td class="col7"><c:if test="${item.status == -1}">已删除</c:if>
											<c:if test="${item.status == 0}">草稿</c:if>
											<c:if test="${item.status == 1}">已上架</c:if>
											<c:if test="${item.status == 2}">已下架</c:if></td>
											<td class="col8">
												
												<input id="${item.productUuid}" onclick="del(this)" class='delete' type='button' value='删除'>
												<input id="${item.productUuid}" onclick="edit(this)" class='modify' type='button' value='修改'>
												<input id="${item.productUuid}" onclick="editPic(this)" class='delete' type='button' value='图组'>
												<input id="${item.productUuid}" onclick="editArt(this)" class='modify' type='button' value='属性'>
												
												</td>
												
												
										</tr>
									</c:forEach>



                        </tbody>

                        <%@include file="../include/innerNavigation.jsp"%>
                    </table>
                


            </div>


</form>
        </div>

</div>


</body>
</html>