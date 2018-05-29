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
<link href="<%=path%>/resource/css/signDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>

<script type="text/javascript"
	src="<%=path%>/resource/scripts/navigation.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="container">

        <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        	<div class="content">

        <div class="content-title">签收详情</div>
        <div class="table">
            <div class="addproduct">
                <p>运&nbsp;&nbsp;&nbsp;&nbsp;费&nbsp;&nbsp;:</p>
                <h1 style="font-size: 14px;color:#3d474d;float: left;margin-left: 10px;font-weight: normal;">${sign.sendMoney}元</h1>
            </div>
            <div class="addproduct1">
                <p>物流状况:</p>
                <h1 style="font-size: 14px;color:#3d474d;float: left;margin-left: 10px;font-weight: normal;">
                <c:if test="${sign.sendStatus==1}">完好无损</c:if>
                <c:if test="${sign.sendStatus==2}">轻微破损</c:if>
                <c:if test="${sign.sendStatus==3}">严重破损</c:if>
                <c:if test="${sign.sendStatus==4}">货物缺少</c:if>
                </h1>
            </div>

            <div class="clear"></div>

            <div class="addproduct">
                <p>情况描述:</p>
                <h1 style="font-size: 14px;color:#3d474d;float: left;margin-left: 10px;font-weight: normal;padding-bottom: 20px;">${sign.sendDescription}</h1>
            </div>

            <div class="clear"></div>
        </div>
        <div class="footerbtn">
                <input type="button" value="返回上一级" class="add" onclick="javascript:history.back()">
        </div>
    </div>
</div>
</body>
</html>