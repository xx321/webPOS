<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title>單日結算</title>

<link href="css2/form.css" rel="stylesheet" type="text/css" />
<link href="css2/layout.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css2/style.css" type="text/css"/>

<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>
<link rel="stylesheet"
	href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css"
	type="text/css" />
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css"
	type="text/css" />
<script type="text/javascript" src="jslib/syUtil.js"></script>


<script type="text/javascript" src="JSCal2-1.9/src/js/jscal2.js"></script>
<script type="text/javascript" src="JSCal2-1.9/src/js/lang/b5.js"></script>
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/jscal2.css" />
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/border-radius.css" />


</head>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	
	$(function() {
		
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridByStatusTradeitem',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			sortName : 'categoryName',
			sortOrder : 'desc',
			columns : [ [ {
				title : '類別',
				field : 'categoryName',
				width : 100,
				sortable : true
			}, {
				title : '商品名稱',
				field : 'productName',
				width : 120,
				sortable : true
			}, {
				title : '銷售數量',
				field : 'quantity',
				width : 80,
				sortable : true
			}, {
				title : '銷售金額',
				field : 'total',
				width : 80,
				sortable : true
			} ] ],
			toolbar : '#toolbar'
		});
		
	});
	
	function add() {
		var msg = "確認送出? \n\n 請確認 !";
		if (confirm(msg)==true){
				return true;
			}else{
				return false;
			}
		};
</script>
<body>
	<div id="toolbar" style="height: 120px;">
			<table id="tabh">
				<tr>
					<th>結算日期與時間 : </th>
					<td><fmt:formatDate value="${checkout.createTime}"
							pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
					<th>結算操作員 : </th>
					<td>${checkout.user.username}</td>
				</tr>
			</table>
			<br />
			<table id="tab">
				<tr>
					<th>現金交易</th>
					<td>${checkout.cashCount}筆</td>
					<th>件數</th>
					<td>${checkout.cashItem}件</td>
					<th>金額</th>
					<td>${checkout.cashAmount}元</td>				
				</tr>
				<tr>
					<th>發票交易</th>
					<td>${checkout.invoiceCount}筆</td>				
					<th>件數</th>
					<td>${checkout.invoiceItem}件</td>				
					<th>金額</th>
					<td>${checkout.invoiceAmount}元</td>							
				</tr>
				<tr>
					<th>交易總筆數</th>
					<td>${checkout.totalCount}筆</td>				
					<th>共出售件數</th>
					<td>${checkout.totalItem}件</td>				
					<th>交易總金額</th>
					<td>${checkout.totalAmount}元</td>				
				</tr>
			</table>
	</div>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png" /> </a>
			</div>
		</div>
		<h2>單日結算</h2>
		<h4>上一次結算日期與時間 : 
				<fmt:formatDate value="${lastCheckout.createTime}"
							pattern="yyyy年MM月dd日 HH:mm:ss" /> ----- ${lastCheckout.user.username}
			</h4> 
		<div id="main">

			
			
			
			
			<fieldset id="gridwrapper">
			
				<legend></legend>
				
		
			
				<table id="datagrid"></table>
				
			</fieldset>

			<p align="center">
				<a href="admin/addCheckout">
					<button type="button" onclick="javascript:return add()">
						<h6>結帳確認</h6>
						<p>請檢查後再按下按鈕。</p>						
					</button>
				</a> 
				<button type="button" onclick="self.location.href='admin/day_trading.jsp'">
					<h6>退出</h6>
					<p>取消送出，回到選單。</p>
				</button>
			</p>
		</div>
		<div id="footer">&copy; ◎版權所有</div>
	</div>
</body>
</html>