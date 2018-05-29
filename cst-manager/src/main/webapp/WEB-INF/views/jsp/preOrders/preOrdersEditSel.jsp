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
<link href="<%=path%>/resource/css/reserveDetail.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="<%=path%>/resource/common/iconfont.css" />
<script type="text/javascript"
	src="<%=path%>/resource/scripts/jquery-1.7.2.min.js"></script>


</head>
<body>



<div class="container">

        <%@ include file="../include/top2.jsp"%>


		<%@ include file="../include/left2.jsp"%>

        <div class="content">

            <div class="content-title">查看预定订单</div>


            <div class="content-main">
                <form>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col1">商品</th>
                            <th class="col2">消费者电商账号</th>
                            <th class="col3">收货人</th>
                            <th class="col4">打款账号</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                <td class="col1">
                                   <select name="productUuid" id="productUuid"
										>
												<c:forEach var="item" items="${productList}">
												<option value="${item.productUuid}"  <c:if test="${preOrders.productUuid == item.productUuid}">selected="selected" </c:if>>${item.name}</option>
												</c:forEach>
										</select>
                                </td>
                                <td class="col2">
                                   <input type="text" name="userPhone" id="userPhone"
											value="${preOrders.userPhone }" />
                                </td>
                                <td class="col3">
                                    <input type="text" name="receiveName" id="receiveName"
											value="${preOrders.receiveName }" />
                                </td>
                                <td class="col4">
                                   <input type="text" name="receiveAccount" id="receiveAccount"
											value="${preOrders.receiveAccount }" />
                                </td>

                            </tr>



                        </tbody>


                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col5">收货人地址</th>
                            <th class="col6">收货人手机</th>
                            <th class="col7">配送周期</th>
                            <th class="col8">贵宾专员</th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                <td class="col5">
                                    <input type="text" name="receiveAddress" id="receiveAddress"
											value="${preOrders.receiveAddress }" />
                                </td>
                                <td class="col6">
                                    <input type="text" name="receiveMobile" id="receiveMobile"
											value="${preOrders.receiveMobile }" />
                                </td>
                                <td class="col7">
                                    每<input type="text" name="receivePeriod" id="receivePeriod"
											value="${preOrders.receivePeriod }" />个月一次
                                </td>
                                <td class="col8">
                                    <select name="saleManager" id="saleManager"
										>
												<c:forEach var="item" items="${managerList}">
												<option value="${item.managerId}"  <c:if test="${preOrders.saleManager == item.managerId}">selected="selected" </c:if>>${item.name}</option>
												</c:forEach>
										</select>
                                </td>


                            </tr>



                        </tbody>


                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col5">合同编号</th>
                            <th class="col6"></th>
                            <th class="col7"></th>
                            <th class="col8"></th>
                        </tr>

                        </thead>
                        <tbody>

                            <tr>
                                <td class="col5">
                                    <input type="text" name="contractNo" id="contractNo"
											value="${preOrders.contractNo }" />
                                </td>
                                <td class="col6">
                                </td>
                                <td class="col7">
                                </td>
                                <td class="col8">
                                </td>


                            </tr>



                        </tbody>


                    </table>

                </form>


            </div>


        </div>


</div>
</body>
</html>