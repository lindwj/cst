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
					action="<%=path%>/shuidian/saveShuidian.do">
					<div id="errorm8">请填写名称</div>
        			<div id="errorm9">请填写简称</div>
        			<div id="errorm10">请填写编号</div>
        			<div id="errorm11">请填写地址</div>
					<div id="err" style="color: red">
						<c:if test="${errcode == 100 }">已存在 </c:if>
						<c:if test="${errcode == 101 }">所有项  不能为空</c:if>
					</div>

            <div class="content-title">修改信息</div>


            <div class="content-main">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col5">水费</th>
                            <th class="col6">水量</th>
                            <th class="col7">电费</th>
                            <th class="col8">电量</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                            
                            <tr>
                                <td class="col1">
                                    <input type="hidden" name=shuidianId id="shuidianId"
											value="${shuidian.shuidianId }" /> <input type="hidden" name="dowhat"
											value="edit" />
											<input type="text" name="shuifei" id="shuifei" value="${shuidian.shuifei }"/>
                                </td>
                                <td class="col2">
                                <input type="text" name="shuiliang" id="shuiliang" value="${shuidian.shuiliang }"/>
                                </td>
                                <td class="col3">
                                    <input type="text" name="dianfei" id="dianfei" value="${shuidian.dianfei }"/>
                                </td>
                                <td class="col4">
                                   <input type="text" name="dianliang" id="dianliang" value="${shuidian.dianliang }"/>
                                </td>

                            </tr>



                        </tbody>


                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">备注</th>
                            <th class="col1"></th>
                            <th class="col1"></th>
                            <th class="col1"></th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                
                                <td class="col9">
                                  <input type="text" name="note" id="note" value="${shuidian.note }"/>
                                </td>
                                <td class="col9">
                                </td>
                                <td class="col9">
                                </td>
                                <td class="col9">
                                </td>



                            </tr>



                        </tbody>
<tfoot>
                            <tr>

                                <td colspan="13">

                                    <div class="pull-right page-box">
                                        <div class="pagination-goto">
                                            <button type="button" style="float:right;" class="btn btn-default" id="goBtn" onclick="javascript:location='${home}/shuidian/shuidianListPage.do'">返回上一级</button>
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