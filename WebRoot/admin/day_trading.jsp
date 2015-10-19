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

<title>當日交易</title>

<link rel="stylesheet" href="css2/buy.css" type="text/css"/>

<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="jslib/syUtil.js"></script>

</head>
<script type="text/javascript" charset="utf-8">

	var listTrade;
	var listTradeitem;
	
	$(function() {
	
		listTrade = $('#listTrade').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridByStatusTrade',
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
			sortName : 'id',
			sortOrder : 'desc',
			columns : [ [ {
				title : '交易編號',
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
			}, {
				title : '發票編號',
				field : 'invoiceNumber',
				width : 100
			} ] ],
			toolbar : '#toolbar',
			onClickRow : function(rowIndex, rowData) {
				$('#listTradeitem').datagrid('load',{
					tradeId : rowData.id
				});
			}
		});
		
		
		listTradeitem = $('#listTradeitem').datagrid({
			url : '${pageContext.request.contextPath}/admin/getDatagridByTradeId',
			title : '交易明細',
			iconCls : '',
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			showFooter : true,
			columns : [ [ //{title : '編號',field : 'productId'},
			{
				title : '商品名稱',
				field : 'productName',
				width : 80
			}, {
				title : '數量',
				field : 'quantity',
				width : 80,
			}, {
				title : '單位',
				field : 'unit',
				width : 50
			}, {
				title : '價格',
				field : 'purchasePrice',
				precision : 2,
				width : 80,
			}, {
				title : '小計',
				field : 'total',
				precision : 2,
				width : 50
			} ] ]
		});
		
		trade_search = function() {

			listTrade.datagrid('load', sy.serializeObject($('#trade_searchForm')));
		};
		
		trade_clearSearch = function() {
			listTrade.datagrid('load', {});
			$('#trade_searchForm').find('input').val('');
			$('input[name="type"]').attr('checked', false);
			$('input[name="type"]')[0].value = 1;
			$('input[name="type"]')[1].value = 0;
		};
		
	});
	function invalidTrade() {
		var rows = listTrade.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('請確認', '您要作廢當前所選的交易項目 ? ', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						listTrade.datagrid('deleteRow', listTrade.datagrid('getRowIndex', rows[i]));  
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/admin/invalidTrade', ////////////////
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d && d.success) {
								listTrade.datagrid('acceptChanges');
								listTradeitem.datagrid('load',{
									id : ''
								});
							} else {
								listTrade.datagrid('rejectChanges');
							}
							listTrade.datagrid('unselectAll');
							$.messager.show({
								msg : d.msg,
								title : '提示'
							});
						}
					});
				}
			});
		} else {
				parent.sy.messagerAlert('提示', '請選擇要作廢的記錄 ！ ', 'error');
		}
	};
</script>
<body>
<div id="toolbar"style="height: 100px;">
	<form id="trade_searchForm" >
		交易編號 : <input id="ids" name="ids" />
		發票編號 : <input id="invoiceNumber" name="invoiceNumber" />
		<br />

		結帳方式:
			<input type="radio" name="type" value="1">發票結帳
			<input type="radio" name="type" value="0">現金結帳
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="trade_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="trade_clearSearch()">清空</a>
	</form>
</div>

	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>
			
<h2>當日交易</h2>
		<div id="main">
			
			<fieldset id="gridwrapper" class="gd1">
           		
				<table id="listTrade"></table>

			</fieldset>
			<fieldset id="gridwrapper" class="gd2">
			
				<table id="listTradeitem"></table>

			</fieldset>
				
			<fieldset id="bads">
            	<p align="center">
					<button type="button" onclick="self.location.href='admin/loadCheckout'">
					  <img src="images/save.png" width="35" height="35" />
					  <h6>當日結帳</h6>
					</button>
					<button type="button" onclick="invalidTrade();">
					  <img src="images/del.png" width="35" height="35" />
					  <h6>交易作廢</h6>
					</button>
					<button type="button" onclick="self.location.href='admin/index.jsp'">
					  <img src="images/quit.png" width="35" height="35" />
					  <h6>退出</h6>
					</button>
                </p>
			</fieldset>
		
		
		
	
	<div id="footer">&copy; ◎版權所有</div>
	</div>
    </div>
</body>
</html>
