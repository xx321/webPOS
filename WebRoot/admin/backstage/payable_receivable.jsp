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
<title>應收應付款</title>
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
	padding-right:30px;
	padding-left:50px;
	padding-top:20px;
}
</style>
</head>

<body>
<div id="wrapper">
		<div id="main">
			<div id="logo">
				<a href="#"><img src="images/logo.png" /> </a>
			</div>
			<div id="bse" class="T1">
				<a class="css_cost" href="admin/backstage/payable_receivable/accounts_receivable.jsp"><img src="images/21.png"
					height="64" width="64" />
					<br/>應收帳款單</a>
			</div>
              <div id="bse" class="T1">
				<a class="css_cost" href="admin/backstage/payable_receivable/accounts_payable.jsp"><img src="images/21.png"
					height="64" width="64" />
					<br/>應付帳款單</a>
			</div>
           
			<div id="bse" class="T1">
				<a class="css_cost" href="admin/pettyCash.jsp"><img src="images/fee.png" />
					<br/>零用金支出</a>
			</div>
		
			<div id="bse" class="T1">
				<a class="css_cost" href="javascript:void(0)"
					onclick="self.location.href='admin/backstage.jsp'"><img
					src="images/quit1.png" height="64" width="64" />
					<br/>退出</a>
			</div>
		</div>

	</div>

</body>
</html>