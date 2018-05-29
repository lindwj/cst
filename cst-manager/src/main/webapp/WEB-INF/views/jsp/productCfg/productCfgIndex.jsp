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
<link href="<%=path%>/resource/css/hotRecommend.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>



<script type="text/javascript">


function sbm(){
	//$('#file').empty();
	//$('#fileQueue').empty();
	$("#shareForm").submit();
}


function del(obj){
	$('#black1').fadeIn();
	//确定事件
	$("#surebtn").click(function(){
		$("#productCfgId").attr("value",$(obj).attr("id"));
		$("#shareForm").attr("action", "<%=path%>/productCfg/delProductCfg.do");
		$("#shareForm").submit();
	})
	//取消事件
	$('#canclebtn').click(function(){
        $('#black1').fadeOut();
    });
}


function edit(obj){
	
	$("#productCfgId").attr("value",$(obj).attr("id"));
	
	
	$("#shareForm").attr("action", "<%=path%>/productCfg/productCfgAddEditIni.do");
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
					action="<%=path%>/productCfg/productCfgListPage.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">商品uuid 不能为空，排序大于0</c:if>
					</div>

            <div class="content-title">热门推荐管理</div>

            <div class="search">

                <button type="button" class="btn-default" id="addbtn" onclick="javascript:location='<%=path%>/productCfg/productCfgAdd.do'">添加+</button>




            </div>

            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">图片</th>
                            <th class="col2">名称</th>
                            <th class="col3">类型</th>
                            <th class="col4">排序</th>
                            <th class="col6">操作</th>
                        </tr>

                        </thead>
                        <tbody>
<input type="hidden" name="productCfgId" id="productCfgId" />
									<c:forEach var="item" items="${productCfgList}">
										<tr>
											<td class="col1"><img src="${item.picUrl}" width="50px" height="50px"/></td>
											<td class="col2">${item.name}</td>
											<td class="col3">${item.typeStr}</td>
											<td class="col4">${item.sort}</td>
											<td class="col6">
											<input class='delete' type='button' id="${item.productCfgId}" onclick="del(this)" value='删除'><input class='modify' type='button' value='修改' id="${item.productCfgId}" onclick="edit(this)">
												</td>
										</tr>
									</c:forEach>


                        </tbody>

<%-- <%@include file="../include/innerNavigation.jsp"%> --%>
                    </table>
                </form>


            </div>



        </div>

</div>

</body>
</html>