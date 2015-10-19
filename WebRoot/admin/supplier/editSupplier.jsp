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

<title>修改廠商</title>

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

			<h2>修改廠商</h2>

			<s:form id="cmxform" action="updateSupplier" namespace="/admin" theme="simple">
				<fieldset>
					<legend></legend>

					<s:hidden name="supplier.id" />

					<p>
						<label id="add1">統一編號:</label>
						<input  name="supplier.companyCode" value="${supplier.companyCode}" size="20" maxlength="20" class="required"/>
					</p>
					<p>
						<label id="add1">供應商名稱:</label>
						<input  name="supplier.name" value="${supplier.name}" size="20" maxlength="20" class="required"/>
					</p>
					<p>
						<label id="add1">聯絡人:</label>
						<input  name="supplier.contact" value="${supplier.contact}" size="20" maxlength="20"/>
					</p>
					<p>
						<label id="add1">電話:</label>
						<input  name="supplier.phone" value="${supplier.phone}" size="20" maxlength="20"/>
					</p>
					<p>
						<label id="add1">傳真:</label>
						<input  name="supplier.fax" value="${supplier.fax}" size="20" maxlength="20"/>
					</p>
					<p>
						<label id="add1">電子郵件:</label>
						<input  name="supplier.email" value="${supplier.email}" size="30" maxlength="30"/>
					</p>
					<p>
						<label id="add1">狀態:</label>
					</p>
					
					<s:radio name="supplier.status" list="#{'1':'開啟','0':'禁用'}"/>
										
					<p>
						<label id="add1">匯款帳號</label>
					</p>
					<p>
						<label id="add1">銀行名稱-分行名稱:</label>
						<input  name="supplier.agencyName" value="${supplier.agencyName}" size="25" maxlength="25"/>
					</p>
					<p>
						<label id="add1">戶名:</label>
						<input  name="supplier.accountUsername" value="${supplier.accountUsername}" size="25" maxlength="25"/>
					</p>
					<p>
						<label id="add1">總分支機構代碼:</label>
						<input  name="supplier.agencyCode" value="${supplier.agencyCode}" size="15" maxlength="15"/>
					</p>
					<p>
						<label id="add1">帳號:</label>
						<input  name="supplier.account" value="${supplier.account}" size="20" maxlength="20"/>
					</p>
					
					<p>
						<label id="add1">地址:</label>
						<s:textarea name="supplier.address" cols="35" rows="4" />
					</p>
					<p>
						<label id="add1">描述:</label>
						<s:textarea name="supplier.description" cols="50" rows="8" />
					</p>
					
					
					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['supplier.companyCode'][0]"/><br />
					<s:property value="errors['supplier.name'][0]" /><br />
					<s:property value="errors['supplier.cdescription'][0]"/><br />
					
				</fieldset>

				<p align="center">
					<button type="submit">
						<h6>送出</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/supplier.jsp'">
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
