<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html class="no-js">
<head>
<title>${webTitle }</title>
<meta http-equiv="Refresh" content="0; URL=<%=path%>/resource/electricBusiness/index.html">
<link rel="icon" type="images/png" href="<%=path%>/resource/components/images/logo/logo-icon.jpg">

</head>
<body>
</body>
</html>
