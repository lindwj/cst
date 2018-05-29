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
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>

	
	
	
	<script type="text/javascript">


function sbm(){
	//$('#file').empty();
	//$('#fileQueue').empty();
	$("#shareForm").submit();
}





</script>

</head>
<body>



<div class="container">

      <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        <div class="content">
        <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/productAttribute/saveProductAtr.do">

					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">已存在 </c:if>
						<c:if test="${errcode == 101 }">名称 属性值  单位  不能为空</c:if>
					</div>

            <div class="content-title">属性列表</div>


            <div class="content-main">
<input type="hidden" name="dowhat" value="edit" />
<input type="hidden" name="productUuid" id="productUuid" value="${productAttribute.productUuid}" />
                    <div class="table">

                        <div class="addproduct2">
                        
									
                        
                            <p><span class="redicon">*</span>商品属性:</p>
                            <c:forEach var="item" items="${productAttribute.productAttributeList}">
                            <input type="hidden" name="productAttributeList[0].productAttributeId" id="" value="${item.productAttributeId}"/>
                            <input type="text" class="input1" name="productAttributeList[0].name" placeholder="属性名称" value="${item.name}">
                            <input type="text" class="input2" name="productAttributeList[0].value" placeholder="属性值" value="${item.value}">
                            <input type="text" class="input3" name="productAttributeList[0].unit" placeholder="属性单位" value="${item.unit}">
										</c:forEach>
                        </div>

                        <div class="clear"></div>
                    </div>


                <div id="returnbutton" >
                  <input class='modify' style="float:right;background-color: #FFFFFF;border-bottom: 2px solid #b0b4b5;margin:9px 10px;" type='button'  onclick="sbm()" value='保存'>
                </div>
            </div>

		</form>

        </div>


</div>

</body>
</html>