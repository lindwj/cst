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
    <link href="<%=path%>/resource/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resource/css/franchiseDetail.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resource/common/iconfont.css"/>
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript"
	src="<%=path%>/resource/scripts/json-minified.js"></script>
	
	
	
	<script type="text/javascript">
function sbm(){
	if($("#name").val()==""){
		$("#errorm8").fadeIn(300);
		$("#errorm8").fadeOut(5000);
	}else if($("#nickName").val()==""){
		$("#errorm9").fadeIn(300);
		$("#errorm9").fadeOut(5000);
	}else if($("#code").val()==""){
		$("#errorm10").fadeIn(300);
		$("#errorm10").fadeOut(5000);
	}else if($("#address").val()==""){
		$("#errorm11").fadeIn(300);
		$("#errorm11").fadeOut(5000);
	}else{
		$("#shareForm").submit();
	}
}



</script>

</head>
<body>



<div class="container">

<%@ include file="../include/top2.jsp"%>


        <%@ include file="../include/left2.jsp"%>
        
        
        <div class="content">
        <form name="shareForm" id="shareForm" method="post"
					action="<%=path%>/shop/saveShop.do">
					<div id="errorm8">请填写名称</div>
        			<div id="errorm9">请填写简称</div>
        			<div id="errorm10">请填写编号</div>
        			<div id="errorm11">请填写地址</div>
					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">专营店已存在 </c:if>
						<c:if test="${errcode == 101 }">省市县街道，名称，编号 ,地址  不能为空</c:if>
					</div>

            <div class="content-title">修改专营店</div>


            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">省份</th>
                            <th class="col2">城市</th>
                            <th class="col3">县区</th>
                            <th class="col4">街道</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                            
                            <tr>
                                <td class="col1">
                                    <input type="hidden" name="shopId" id="shopId"
											value="${shop.shopId }" /> <input type="hidden" name="dowhat"
											value="edit" /> ${shop.provincestr}
                                </td>
                                <td class="col2">
                                    ${shop.citystr}
                                </td>
                                <td class="col3">
                                    ${shop.districtstr}
                                </td>
                                <td class="col4">
                                   ${shop.streetstr}
                                </td>

                            </tr>



                        </tbody>


                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col5">名称</th>
                            <th class="col6">简称</th>
                            <th class="col7">编号</th>
                            <th class="col8">地址</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                <td class="col5">
                                    <input type="text" name="name" id="name"
											value="${shop.name }" />
                                </td>
                                <td class="col6">
                                    <input type="text" name="nickName" id="nickName"
											value="${shop.nickName }" />
                                </td>
                                <td class="col7">
                                   <input type="text" name="code" id="code"
											value="${shop.code }" />
                                </td>
                                <td class="col8">
                                  <input type="text" name="address" id="address"
											value="${shop.address }" />
                                </td>


                            </tr>



                        </tbody>
<tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button type="button" style="float:right;" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/shop/shopListPage.do'">返回上一级</button>
                                            <input class='samestyle' style="height:32px;margin:0 10px;" type='button' onclick="sbm()" value='保存'>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </tfoot>


                    </table>



            </div>


            </form>
        </div>


</div>
</body>
</html>