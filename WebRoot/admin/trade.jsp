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

<title>前台交易信息</title>
<link href="css2/form.css" rel="stylesheet" type="text/css"/>
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="jslib/syUtil.js"></script>
</head>

<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridTrade',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : false,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			sortName : 'id',
			sortOrder : 'desc',
			columns : [ [ {
				title : '編號',
				field : 'id',
				width : 100,
				sortable : true
			}, {
				title : '交易時間',
				field : 'createTime',
				width : 120,
				sortable : true
			}, {
				title : '數量',
				field : 'quantity',
				width : 80,
				sortable : true
			}, {
				title : '金額',
				field : 'total',
				width : 120,
				sortable : true
			}, {
				title : '交易人員',
				field : 'username',
				width : 80
			}, {
				title : '結帳方式',
				field : 'typeStr',
				width : 80
			} ] ],
			toolbar : '#toolbar'
		});
		
		datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
		
	});
	
	function getTradeSubmit() {
		var rows = datagrid.datagrid('getSelections');
		if(rows.length > 1) {
			datagrid.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項交易進行檢示 ! ', 'error');
		} else if (rows.length == 1) {
			document.loadTrade.id.value= rows[0].id; 
     		document.loadTrade.submit();
		} else {
			parent.sy.messagerAlert('提示', '請選擇要檢示的交易 ！ ', 'error');
		}
	}
</script>
<body>
<s:form action="getTrade" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<div id="toolbar"style="height: 60px;">
	<form id="searchForm" >
		查詢日期範圍 : 從
		<input type="text" id="createtimeStart" name="createtimeStart" value="${createtimeStart}" size="10" readonly />
		至
		<input type="text" id="createtimeEnd" name="createtimeEnd" value="${createtimeEnd}" size="10" readonly />
	</form>
</div>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>

		<h2>前台交易信息</h2>
		<div id="main">
	
			
			<fieldset>
				<legend></legend>
				
					<table id="datagrid"></table>

			</fieldset>

			<p align="center">
				<button type="button" onClick="javascript:history.back(1)">
					<h6>退出</h6>
					<p>取消送出，回到選單。</p>
				</button>
				
			</p>
			
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
