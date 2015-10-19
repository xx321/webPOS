<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>人資管理</title>
<link rel="stylesheet" href="css/style1.css" type="text/css" />

<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>
<link rel="stylesheet"
	href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css"
	type="text/css"></link>
<script type="text/javascript" src="jslib/syUtil.js"></script>
<style>
.T1{
	padding-left:115px;
	padding-top:50px;
}
.T2{
	padding-top:50px;
}
.T3{
	padding-right:15px;
	padding-top:20px;
}
.T4{
	padding-top:20px;
}
</style>
</head>

<body>
<div id="wrapper">
		<div id="main" >
			<div id="logo">
				<a href="#"><img src="images/logo.png" /> </a>
			</div>
			<div id="bse" class="T3">
				<a class="css_cost" href="admin/supplier.jsp"><img src="images/firm.png"
					height="64" width="64" />
					<br/>廠商設定</a>
			</div>
            <div id="bse" class="T3">
				<a class="css_cost"><img src="images/firm.png"
					height="64" width="64" />
					<br/>銀行設定</a>
			</div>
			<div id="bse" class="T4">
				<a class="css_cost" href="admin/customer.jsp"><img src="images/client.png"
					height="64" width="64" />
					<br/>客戶設定</a>
			</div> 
            
			<div id="bse" class="T1">
				<a class="css_cost" href="admin/listUser"><img
					src="images/user.png" height="64" width="64" />
					<br/>用戶管理</a>
			</div>
			<div id="bse" class="T2">
				<a class="css_cost" href="javascript:void(0)"
					onclick="self.location.href='admin/backstage.jsp'"><img
					src="images/quit1.png" height="64" width="64" />
					<br/>退出</a>
			</div>
		</div>

	</div>

</body>
</html>