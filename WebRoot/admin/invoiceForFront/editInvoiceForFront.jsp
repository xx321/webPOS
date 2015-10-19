<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>更換發票</title>

<link href="css2/jqvalidate.css" rel="stylesheet" type="text/css"/>
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/reset.css" type="text/css"/>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/cmxforms.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#cmxform").validate();
});
</script>
</head>

<body>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>


		<div id="main">

			<h2>更換發票</h2>

			<s:form id="cmxform" action="updateInvoiceForFront" namespace="/admin" theme="simple">
				<fieldset>
					<legend></legend>

					<s:hidden name="invoiceForFront.id" />
					<s:hidden name="invoiceForFront.quantity" value="0"/>
					<s:hidden name="invoiceForFront.updateTime" />

					<p>
						<label id="add1">發票編碼:</label>
						<input  name="invoiceForFront.invoiceCoding" value="" size="2" maxlength="2" class="required"/>
					</p>
					<p>
						<label id="add1">發票號碼:</label>
						<input  name="invoiceForFront.invoiceNumber" value="" size="8" maxlength="8" class="digits"/>
					</p>				

					<br />
					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['invoiceForFront.invoiceCoding'][0]" /><br />
					<s:property value="errors['invoiceForFront.invoiceNumber'][0]"/><br />

					<p>
						${message}
					</p>

				</fieldset>

				<p align="center">
					<button type="submit">
						<h6>確定更換</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/showInvoiceForFront'">
						<h6>取消</h6>
						<p>取消送出，回到選單。</p>
					</button>
				</p>
			</s:form>
			
		</div>
		
		<div id="footer">&copy; ◎醒吾科技大學 - 夜四技 - 資訊管理系</div>

	</div>
</body>
</html>
