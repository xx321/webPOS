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

<title>庫存管理</title>

<link href="css2/form.css" rel="stylesheet" type="text/css"/>
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css2/style.css" type="text/css"/>

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
			url : '${pageContext.request.contextPath}/admin/datagridStock',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [13, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			sortName : 'name',
			sortOrder : 'desc',
			columns : [ [ {
				title : '編號',
				field : 'id',
				width : 100,
				sortable : true
			}, {
				title : '分類',
				field : 'categoryName',
				width : 100,
				sortable : true
			}, {
				title : '名稱',
				field : 'name',
				width : 100,
				sortable : true
			}, {
				title : '商品售價',
				field : 'price',
				width : 100,
				sortable : true
			}, {
				title : '庫存數量',
				field : 'stockNumber',
				width : 80,
				sortable : true
			}, {
				title : '單位',
				field : 'unit',
				width : 50
			}, {
				title : '進貨價格',
				field : 'purchasePrice',
				width : 100,
				sortable : true
							}, {
				title : '包裝商品編號',
				field : 'boxId',
				width : 100
			}, {
				title : '包裝商品名稱',
				field : 'boxName',
				width : 100
			}, {
				title : '單位',
				field : 'boxUnit',
				width : 50
			} ] ],
			toolbar : '#toolbar'
		});
	
		_search = function() {

			datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
		};
	
		clearSearch = function() {
			datagrid.datagrid('load', {});
			$('#searchForm').find('input').val('');
		};
		
		var message = '${message}';
		if (message != "") {
			$.messager.show({
				msg : message,
				title : '提示'
			});
		};
	});
	
	function editBox() {
		var rows = datagrid.datagrid('getSelections');
		if(rows.length > 1) {
			datagrid.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項記錄進行設定 ! ', 'error');
		} else if (rows.length == 1) {
			document.editBoxForm.id.value= rows[0].id; 
     		document.editBoxForm.submit();
		} else {
			parent.sy.messagerAlert('提示', '請選擇要設定的商品 ！ ', 'error');
		}
	};
	
	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if(rows.length > 1) {
			datagrid.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項記錄進行修改 ! ', 'error');
		} else if (rows.length == 1) {
			document.editForm.id.value= rows[0].id; 
     		document.editForm.submit();
		} else {
			parent.sy.messagerAlert('提示', '請選擇要調整的商品 ！ ', 'error');
		}
	};
</script>
<body>
<s:form name="editBoxForm" action="editProductBox" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<s:form name="editForm" action="editProductStock" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<div id="toolbar"style="height: 100px;">
	<form id="searchForm" >
		名稱 : <input id="name" name="name" />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearSearch()">清空</a>
	</form>
	
	

</div>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>

		<h2>庫存管理</h2>
		<div id="main">
	

			<fieldset id="gridwrapper">
				<legend></legend>
				
					<table id="datagrid"></table>

			</fieldset>


			<p align="center">
				<button type="button" onclick="editBox();">
					<h6>拆箱設定</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="edit();">
					<h6>手動調整</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="self.location.href='admin/backstage/inventory_management.jsp'">
					<h6>退出</h6>
					<p>取消送出，回到選單。</p>
				</button>
			</p>
			
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>