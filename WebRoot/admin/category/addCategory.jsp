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

<title>添加分類</title>

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
				<img src="images/logo.png"  />
			</div>
			

		</div>


		<div id="main">

			<h2>添加分類</h2>

			<s:form id="cmxform" action="addCategory" namespace="/admin" theme="simple">
				<fieldset>
					<legend></legend>

					<s:hidden name="category.id" />

					<p>
						<label id="add1">名稱:</label>
						<input  name="category.name" size="20" maxlength="20" class="required"/>
					</p>
					
					<p>
						<label id="add1">顯示順序:</label>
						<input name="category.displayOrder" size="2" maxlength="2" class="digits" class="required"/>
					</p>
					<p>
						<label id="add1">狀態:</label>
					</p>
					
					<s:radio name="category.status" list="#{'1':'開啟','0':'禁用'}" value="1"/>
					
					<p>
						<label id="add1">描述:</label>
						<s:textarea name="category.description" cols="50" rows="8" />
					</p>
					
					
					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['category.name'][0]" /><br />
					<s:property value="errors['category.displayOrder'][0]"/><br />
					<s:property value="errors['category.description'][0]"/><br />
					
				</fieldset>

				<p align="center">
					<button type="submit">
						<h6>送出</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/listCategory'">
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
