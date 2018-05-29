<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会腾软件-邮件激活</title>
<script type="text/javascript">
function submitPay(){

 if($("#tatolCon").text() > 0 ){
	document.getElementById('alipayto').submit();
 }else{
	 $("#errorText").text('订单金额不合法！');
 }
}

</script>
</head>
<body>

<div class="container">

      <div class="sign_email">
         <p class="sign_log"><a href="#">首页</a></p>
         <h1>
         <s:action name="getMessageByLanguage" namespace="/language" executeResult="true">
			<s:param name="messageId" value="122"></s:param>
			<s:param name="eventId" value="%{eventId}"></s:param>
		</s:action>
          </h1>
         <div class="clear"></div>
          <div class="pay_list">
          <p class="center" id="errorText" style="color:red;"></p>
               <p class="center">
               <s:action name="getMessageByLanguage" namespace="/language" executeResult="true">
			<s:param name="messageId" value="123"></s:param>
			<s:param name="eventId" value="%{eventId}"></s:param>
		</s:action>
               </p>
               <div class="order">
                  <table cellpadding="0" cellspacing="0" class="choose_border">
                     <tr class="trhui">
                        <td width="50%">
                          <s:action name="getMessageByLanguage" namespace="/language" executeResult="true">
			<s:param name="messageId" value="124"></s:param>
			<s:param name="eventId" value="%{eventId}"></s:param>
		</s:action>
                        </td>
                        <td>
          <s:action name="getMessageByLanguage" namespace="/language" executeResult="true">
			<s:param name="messageId" value="125"></s:param>
			<s:param name="eventId" value="%{eventId}"></s:param>
		</s:action>               
                        （￥）</td>
                     </tr>
                     <tr>
                        <td width="50%">${orderNo}</td>
                        <td id="tatolCon" >${orderTatol}</td>
                     </tr>
                  </table>
                  
               </div>
               <form id="alipayto" action="/jsp/alipayb/alipayto.jsp" method="post">
		<s:hidden name="orderNo" value="%{orderNo}"></s:hidden>
		<s:hidden name="orderTatol" value="%{orderTatol}"></s:hidden>
		<s:hidden name="seller_email" value="%{seller_email}"></s:hidden>
		<!-- %{seller_email} -->
		<s:hidden name="subject" value="%{subject}"></s:hidden>
		<s:hidden name="royalty" value="george.mo@links-e.com^%{royalty}^会腾分润"></s:hidden>
		</form>
                <p class="center" ><a href="#" onclick="submitPay();contactshow('pay_remind')"><img src="/images/pay_button.png" /></a></p>
        <div class="email_foot">Canevent Inc 无锡市惠山区智慧路18号智慧大厦8楼805    苏ICP备12067427号</div>
      </div>
   </div>
</div>
</body>
</html>
