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

<title>調整張數</title>

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

			<h2>調整張數</h2>

			<s:form id="cmxform" action="updateInvoiceQuantity" namespace="/admin" theme="simple">
				<fieldset>
					<legend></legend>
					
					<s:hidden name="invoiceForFront.id" />
					<s:hidden name="invoiceForFront.invoiceCoding" />
					<s:hidden name="invoiceForFront.invoiceNumber" />
					<s:hidden name="invoiceForFront.updateTime" />
					
					<p>
						<label id="add1">發票編碼:</label>
						${invoiceForFront.invoiceCoding}
					</p>
					
					<p>
						<label id="add1">發票號碼:</label>
						${invoiceForFront.invoiceNumber}
					</p>
					<p>
						<label id="add1">使用張數:</label>						
						<input  name="invoiceForFront.quantity" value="${invoiceForFront.quantity}" size="3" maxlength="3" class="digits"/>
					</p>
					
					<p>輸入列印出來的發票末三碼</p>

					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['invoiceForFront.quantity'][0]" /><br />

				</fieldset>

				<p align="center">
					<button type="submit">
						<h6>送出</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/showInvoiceForFront'">
						<h6>取消</h6>
						<p>取消送出，回到選單。</p>
					</button>
				</p>
			</s:form>
			
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
