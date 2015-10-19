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

<title>新增用戶</title>

<link href="css2/form.css" rel="stylesheet" type="text/css"/>
<link href="css2/form-ie.css" rel="stylesheet" type="text/css" />
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>

</head>

<body>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>


		<div id="main">

			<h2>新增用戶</h2>

			<s:form action="addUser" namespace="/admin" theme="simple">
				<fieldset>
					<legend></legend>

					<s:hidden name="user.id" />
					<s:hidden name="user.status" value="1" />
					
					<p>
						<label>名字:</label>
						<s:textfield name="user.username" size="20" maxlength="20" />
					</p>
					<p>
						<label>帳號:</label>
						<s:textfield name="user.account" size="20" maxlength="20" />
					</p>
					<p>
						<label>密碼:</label>
						<s:textfield name="user.password" size="20" maxlength="20" />
					</p>
					<p>
						<label>權限:</label>
					</p>
					<s:radio name="user.purview" list="#{'1':'管理員','0':'操作員'}" value="0" />
					
					<br />
					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['user.username'][0]" /><br />
					<s:property value="errors['user.account'][0]"/><br />
					<s:property value="errors['user.password'][0]"/><br />
					
					<p>
						${message}
					</p>

				</fieldset>

				<p align="center">
					<button type="submit">
						<h6>送出</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/listUser'">
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
