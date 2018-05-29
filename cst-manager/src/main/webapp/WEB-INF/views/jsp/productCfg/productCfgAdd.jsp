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
<link href="<%=path%>/resource/css/hotDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>


<script type="text/javascript">


function sbm(){
	if($("#productUuid").val()==""){
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else if($("#sort").val()==0){
		$("#errorm5").fadeIn(300);
		$("#errorm5").fadeOut(5000);
	}else if($("#seltypeId").val()==""){
		$("#errorm6").fadeIn(300);
		$("#errorm6").fadeOut(5000);
	}else{
		$("#shareForm").attr("action", "<%=path%>/productCfg/saveProductCfg.do");
		$("#shareForm").submit();
	}
	//$('#file').empty();
	//$('#fileQueue').empty();
}




  

</script>

</head>
<body>



<div class="container">

<%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>
        <div class="content">

            <div class="content-title">新增热门推荐</div>


            <div class="content-main">
            
            <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/productCfg/productCfgListPage.do">
					<div id="errorm4">请输入商品uuid</div>
        			<div id="errorm5">请输入排序</div>
        			<div id="errorm6">请选择类型</div>

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">图片，商品uuid 不能为空，排序大于0</c:if>
					</div>
					
                    <table class="table">
                        <thead>
                        <tr>
                        	<th class="col1">图片</th>
                            <th class="col2">商品uuid</th>
                            <th class="col3">排序</th>
                            <th class="col4">类型</th>
                            <th class="col5">操作</th>
                        </tr>

                        </thead>
                        <tbody>
<input type="hidden" name="dowhat" value="add" />
                            <tr>
                            
                            	<td class="col1">
                                    <input type="text" name="picUrl" id="picUrl" />
                                </td>
                                <td class="col2">
                                    <input type="text" name="productUuid" id="productUuid" />
                                </td>
                                <td class="col3">
                                    <input type="text" name="sort" id="sort" value="0"/>
                                </td>
                                <td class="col4">
                                    <select name="type" id="seltypeId" class="input-default"
										>
												<c:forEach var="item" items="${parameterList}">
												<option value="${item.parameterId}">${item.parameterName}</option>
												</c:forEach>
										</select>
                                </td>
                                <td class="col5"><input onclick="sbm()" class='modify' type='button' value='保存'></td>
                            </tr>



                        </tbody>
                        
                        <tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button type="button" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/productCfg/productCfgListPage.do'">返回上一级</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </tfoot>

                    </table>
                </form>


            </div>



        </div>


</div>

</body>
</html>