<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>用戶管理</title>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css2/form.css" rel="stylesheet" type="text/css"/>
<link href="css2/form-ie.css" rel="stylesheet" type="text/css" />
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>
</head>

<body>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png" /> </a>
			</div>
			

		</div>

		<h2>用戶管理</h2>
		<div id="main">

			

				<fieldset>
					<legend></legend>

					<p>
						<label>編號:</label>
						<label>${user.id}</label>
					</p>
					<p>
						<label>名字:</label>
						<label>${user.username}</label>
					</p>
					<p>
						<label>帳號:</label>
						<label>${user.account}</label>
					</p>

				</fieldset>

				<p align="center">
					<button type="button" onclick="self.location.href='admin/user/changePassword.jsp'">
						<h6>修改密碼</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/index.jsp'">
						<h6>退出</h6>
						<p>取消送出，回到選單。</p>
					</button>
				</p>

			
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
