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

<title>零用金支出</title>

<link rel="stylesheet" href="css2/buy.css" type="text/css"/>

<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="jslib/syUtil.js"></script>


<script type="text/javascript" src="JSCal2-1.9/src/js/jscal2.js"></script>
<script type="text/javascript" src="JSCal2-1.9/src/js/lang/b5.js"></script>
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/jscal2.css" />
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/border-radius.css" />


</head>
<script type="text/javascript" charset="utf-8">


	
	$(function() {
	
		listPettyCash = $('#listPettyCash').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridPettyCash',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			sortName : 'id',
			sortOrder : 'desc',
			columns : [ [ {
				title : '編號',
				field : 'id',
				width : 80,
				sortable : true
			}, {
				title : '廠商名稱',
				field : 'supplierName',
				width : 130
			}, {
				title : '支出帳戶',
				field : 'paymentAccount',
				width : 100,
			}, {
				title : '金額',
				field : 'totalAmount',
				width : 80
			},  {
				title : '支出日期',
				field : 'paymentTimeStr',
				width : 100,
			},  {
				title : '單據日期',
				field : 'createTimeStr',
				width : 100,
			},{
				title : '支付狀態',
				field : 'statusStr',
				width : 80	
			}, {
				title : '經辦人員',
				field : 'username',
				width : 80
			} ] ],
			toolbar : '#toolbar',
			onClickRow : function(rowIndex, rowData) {
				$('#listSpendingItem').datagrid('load',{
					pettyCashId : rowData.id
				});
			}
		});
		
		
		listSpendingItem = $('#listSpendingItem').datagrid({
			url : '${pageContext.request.contextPath}/admin/getDatagridByPettyCashId',
			title : '支出明細',
			iconCls : '',
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			showFooter : true,
			columns : [ [
			{
				title : '項目編號',
				field : 'subjectId',
				width : 100,
			}, {
				title : '費用名稱',
				field : 'subjectName',
				width : 120
			}, {
				title : '金額',
				field : 'total',
				width : 100
			} ] ]
		});
		
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/pettyCashPrepare',
			data : {
			},
			dataType : 'json',
			success : function(d) {
			}
		});
		
		var message = '${message}';
		if (message != "") {
			$.messager.show({
				msg : message,
				title : '提示'
			});
		};
		
		pettyCash_search = function() {

			listPettyCash.datagrid('load', sy.serializeObject($('#pettyCash_searchForm')));
		};
		
		pettyCash_clearSearch = function() {
			listPettyCash.datagrid('load', {});
			$('#pettyCash_searchForm').find('input').val('');
			$('input[name="status"]').attr('checked', false);
			$('input[name="status"]')[0].value = 1;
			$('input[name="status"]')[1].value = 0;
		};
	});
	
	function edit() {
		var rows = listPettyCash.datagrid('getSelections');
		if(rows.length > 1) {
			listPettyCash.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項記錄進行修改 ! ', 'error');
		} else if (rows.length == 1) {
			if (rows[0].status == 0) {
				document.editForm.id.value= rows[0].id; 
     			document.editForm.submit();
			} else {
				$.messager.show({
					msg : '該交易已完成，不能修改 !',
					title : '提示'
				});
			}
		} else {
			parent.sy.messagerAlert('提示', '請選擇要修改的記錄 ！ ', 'error');
		}
	};
	function removePettyCash() {
		var rows = listPettyCash.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('請確認', '您要刪除當前所選項目 ? ', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						listPettyCash.datagrid('deleteRow', listPettyCash.datagrid('getRowIndex', rows[i]));  
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/admin/deletePettyCash',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d && d.success) {
								listPettyCash.datagrid('acceptChanges');
								listGoodsItem.datagrid('load',{
									id : ''
								});
							} else {
								listPettyCash.datagrid('rejectChanges');
							}
							listPettyCash.datagrid('unselectAll');
							$.messager.show({
								msg : d.msg,
								title : '提示'
							});
						}
					});
				}
			});
		} else {
				parent.sy.messagerAlert('提示', '請選擇要刪除的記錄 ！ ', 'error');
		}
	};
	function audit() {
		var rows = listPettyCash.datagrid('getSelections');
		if(rows.length > 1) {
			listPettyCash.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項記錄進行審核 ! ', 'error');
		} else if (rows.length == 1) {
			$.ajax({
				url : '${pageContext.request.contextPath}/admin/auditPettyCash',
				data : {
					id : rows[0].id
				},
				dataType : 'json',
				success : function(d) {
					if (d && d.success) {
						if (d.obj.status == 1) {
							listPettyCash.datagrid('updateRow', {
								index : listPettyCash.datagrid('getRowIndex', rows[0]),
								row : {
									status : d.obj.status,
									statusStr : d.obj.statusStr,
									incomePersonnelName : d.obj.incomePersonnelName,
									incomeTime : d.obj.incomeTime
								}
							});
						} else {
							listPettyCash.datagrid('updateRow', {
								index : listPettyCash.datagrid('getRowIndex', rows[0]),
								row : {
									status : d.obj.status,
									statusStr : d.obj.statusStr,
									incomePersonnelName : '',
									incomeTime : ''
								}
							});
						}
						listPettyCash.datagrid('acceptChanges');
					} else {
						listPettyCash.datagrid('rejectChanges');
					}
					$.messager.show({
						msg : d.msg,
						title : '提示'
					});
				}
			});
		} else {
			parent.sy.messagerAlert('提示', '請選擇要審核的記錄 ！ ', 'error');
		};
	};
</script>
<body>
<s:form name="editForm" action="editPettyCash" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<s:form name="closedForm" action="closedPurchase" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<div id="toolbar"style="height: 100px;">
	<form id="pettyCash_searchForm" >
		編號 : <input id="ids" name="ids" />
		支出帳戶 : <input id="paymentAccount" name="paymentAccount" />
		<br />
		廠商名稱 : <input id="supplierName" name="supplierName" />
		經辦人員 : <input id="username" name="username" />
		<br />
		單據日期 : <input type="text" id="createTimeStr" name="createTimeStr" size="10" readonly />
			<input type="button" value="" id="BTN1" name="BTN1" />
			<script type="text/javascript">
				new Calendar({
					inputField : "createTimeStr",
					dateFormat : "%Y/%m/%d",
					trigger : "BTN1",
					bottomBar : true,
					weekNumbers : false,
					onSelect : function() {
						this.hide();
					}
				});
			</script>
		至 <input type="text" id="createTimeEnd" name="createTimeEnd" size="10" readonly />
			<input type="button" value="" id="BTN2" name="BTN2" />
			<script type="text/javascript">
				new Calendar({
					inputField : "createTimeEnd",
					dateFormat : "%Y/%m/%d",
					trigger : "BTN2",
					bottomBar : true,
					weekNumbers : false,
					onSelect : function() {
						this.hide();
					}
				});
			</script>			
		支付狀態:
			<input type="radio" name="status" value="1">已支付
			<input type="radio" name="status" value="0">未支付
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="pettyCash_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="pettyCash_clearSearch()">清空</a>
	</form>
</div>

	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>
			
<h2>零用金支出</h2>
		<div id="main">
			
			<fieldset id="gridwrapper" class="gd1">
           							
				<table id="listPettyCash"></table>

			</fieldset>
			<fieldset id="gridwrapper" class="gd2">       
				
				<table id="listSpendingItem"></table>

			</fieldset>
		
			<fieldset id="bads">
            
				<button type="button" onclick="self.location.href='admin/pettyCash/addPettyCash.jsp'">
                  <img src="images/add.png" width="35" height="35"/>
				  <h6>新增</h6>
				</button>
				<button type="button" onclick="edit();">
                  <img src="images/2.png" width="35" height="35"/>
				  <h6>修改</h6>
				</button>
				<button type="button" onclick="removePettyCash();">
                  <img src="images/del.png" width="35" height="35"/>
				  <h6>刪除</h6>
				</button>
				<button type="button" onclick="">
                  <img src="images/printer.png" width="35" height="35"/>
				  <h6>列印</h6>
				</button>
             <button type="button" onclick="audit();">
                  <img src="images/search.png" width="35" height="35"/>
				  <h6>審核</h6>
				</button>
                <button type="button"  onclick="self.location.href='admin/backstage/payable_receivable.jsp'">
                  <img src="images/quit.png" width="35" height="35"/>
				  <h6>退出</h6>
				</button>
                
			</fieldset>
		
		
		
	
	<div id="footer">&copy; ◎版權所有</div>
	</div>
    </div>
</body>
</html>

