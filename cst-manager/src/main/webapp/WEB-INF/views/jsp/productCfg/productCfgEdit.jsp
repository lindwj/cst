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


<script type="text/javascript">


function sbm(){
	if($("#sort").val()==""){
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
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

            <div class="content-title">修改热门推荐</div>


            <div class="content-main">
            
            <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/productCfg/productCfgListPage.do">
					<div id="errorm4">请输入排序</div>
					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">商品已存在 </c:if>
						<c:if test="${errcode == 101 }">商品uuid 不能为空，排序大于0</c:if>
					</div>
					
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
                        <input type="hidden" name="dowhat" value="edit" />
<input type="hidden" name="productCfgId" id="productCfgId" value="${productCfg.productCfgId}"/>
                        <tbody>

                            <tr>
                               <td class="col1"><img src="${productCfg.picUrl}" width="50px" height="50px"/>
                               <input type="text" name="picUrl" id="picUrl" value="${productCfg.picUrl}"/></td>
											<td class="col2">${productCfg.name}</td>
											<td class="col3">${productCfg.typeStr}</td>
											<td class="col4"><input type="text" name="sort" id="sort" value="${productCfg.sort}"/></td>
                                <td class="col6"><input onclick="sbm()" class='modify' type='button' value='保存'></td>
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