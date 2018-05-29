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
<link href="<%=path%>/resource/css/contractDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
function sbm(){
	if($("#contractNoAmt").val()==""){
		$("#errorm4").fadeIn(300);
		$("#errorm4").fadeOut(5000);
	}else{
		$("#shareForm").attr("action", "<%=path%>/contractNo/contractNoAddEdit.do");
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
					action="<%=path%>/contractNo/contractNoListPage.do">
					<div id="errorm4">请填写合同编号生成数量</div>
					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">专营店合同编号已存在 </c:if>
						<c:if test="${errcode == 101 }">省市县，专营店，合同编号数量（最大6位数）  不能为空</c:if>
					</div>
					
            <div class="content-title">修改合同编号</div>


            <div class="content-main">
                <form>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">省份</th>
                            <th class="col2">城市</th>
                            <th class="col3">县区</th>
                            <th class="col4">专营店</th>
                            <th class="col5">合同编号生成数量</th>
                            <th class="col6">操作</th>
                        </tr>

                        </thead>
                        <tbody>
                            <tr>
                                <td class="col1">
                                    <input type="hidden" name="contractNoId" id="contractNoId"
											value="${contractNo.contractNoId }" /> <input type="hidden"
											name="dowhat" value="edit" /> ${contractNo.provincestr}
                                </td>
                                <td class="col2">
                                    ${contractNo.citystr}
                                </td>
                                <td class="col3">
                                    ${contractNo.districtstr}
                                </td>
                                <td class="col4">
                                    ${contractNo.shopIdstr}
                                </td>
                                <td class="col5">
                                <input type="text" name="contractNoAmt" id="contractNoAmt" value="${contractNo.contractNoAmt}"/>个
                                </td>
                                <td class="col6">
                                    <input class='modify' onclick="sbm()" type='button' value='保存'>
                                </td>

                            </tr>



                        </tbody>
                        
                        
                        
                        <tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button type="button" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/contractNo/contractNoListPage.do'">返回上一级</button>
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