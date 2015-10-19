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

<title>進貨單</title>

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

	var listIntoGoods;
	var listGoodsItem;
	
	$(function() {
	
		listIntoGoods = $('#listIntoGoods').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridIntoGoods',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : false,
			sortName : 'id',
			sortOrder : 'desc',
			columns : [ [ {
				title : '進貨單編號',
				field : 'sid',
				width : 80,
				sortable : true
			}, {
				title : '供應商名稱',
				field : 'supplierName',
				width : 130
			}, {
				title : '發票編號',
				field : 'invoiceNumber',
				width : 100,
				sortable : true
			}, {
				title : '付款方式',
				field : 'paymentModeStr',
				width : 80
			}, {
				title : '進貨總金額',
				field : 'totalAmount',
				width : 80
			}, {
				title : '單據日期',
				field : 'createTimeStr',
				width : 130
			}, {
				title : '貨款狀態',
				field : 'statusStr',
				width : 80	
			} ] ],
			toolbar : '#toolbar',
			onClickRow : function(rowIndex, rowData) {
				$('#listGoodsItem').datagrid('load',{
					intoGoodsId : rowData.id
				});
			}
		});
		
		
		listGoodsItem = $('#listGoodsItem').datagrid({
			url : '${pageContext.request.contextPath}/admin/getDatagridByIntoGoodsId',
			title : '進貨明細',
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
				title : '進貨單價',
				field : 'purchasePrice',
				precision : 5,
				width : 80,
			}, {
				title : '小計',
				field : 'total',
				width : 50
			} ] ]
		});
		
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/intoGoodsPrepare',
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
		
		intoGoods_search = function() {

			listIntoGoods.datagrid('load', sy.serializeObject($('#intoGoods_searchForm')));
		};
		
		intoGoods_clearSearch = function() {
			listIntoGoods.datagrid('load', {});
			$('#intoGoods_searchForm').find('input').val('');
			$('input[name="status"]').attr('checked', false);
			$('input[name="status"]')[0].value = 1;
			$('input[name="status"]')[1].value = 0;
		};
		
	});
	
	function edit() {
		var rows = listIntoGoods.datagrid('getSelections');
		if(rows.length > 1) {
			listIntoGoods.datagrid('unselectAll');
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
	
	function print() {
		var rows = listIntoGoods.datagrid('getSelections');
		if(rows.length > 1) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			document.printMoreForm.ids.value= ids; 			
     		document.printMoreForm.submit();
		} 
		else if (rows.length == 1) {
			document.printForm.id.value= rows[0].id; 			
     		document.printForm.submit();
		} else {
			parent.sy.messagerAlert('提示', '請選擇要列印的記錄 ！ ', 'error');
		}
	};
	
	function audit() {
		var rows = listIntoGoods.datagrid('getSelections');
		if(rows.length > 1) {
			listIntoGoods.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項記錄進行審核 ! ', 'error');
		} else if (rows.length == 1) {
			$.ajax({
				url : '${pageContext.request.contextPath}/admin/auditIntoGoods',
				data : {
					id : rows[0].id
				},
				dataType : 'json',
				success : function(d) {
					if (d && d.success) {
						if (d.obj.status == 1) {
							listIntoGoods.datagrid('updateRow', {
								index : listIntoGoods.datagrid('getRowIndex', rows[0]),
								row : {
									status : d.obj.status,
									statusStr : d.obj.statusStr,
									paymentPersonnelName : d.obj.paymentPersonnelName,
									paymentTimeStr : d.obj.paymentTimeStr
								}
							});
						} else {
							listIntoGoods.datagrid('updateRow', {
								index : listIntoGoods.datagrid('getRowIndex', rows[0]),
								row : {
									status : d.obj.status,
									statusStr : d.obj.statusStr,
									paymentPersonnelName : '',
									paymentTimeStr : ''
								}
							});
						}
						listIntoGoods.datagrid('acceptChanges');
					} else {
						listIntoGoods.datagrid('rejectChanges');
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
<s:form name="editForm" action="editIntoGoods" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<s:form name="printForm" action="printIntoGoods" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<s:form name="printMoreForm" action="printMoreIntoGoods" namespace="/admin" >
	<input type=hidden name="ids">
</s:form>
<div id="toolbar"style="height: 100px;">
	<form id="intoGoods_searchForm" >
	單據日期&nbsp;&nbsp;&nbsp;&nbsp; : <input type="text" id="createTimeStr" name="createTimeStr" size="10" readonly />
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
			<br/>
		進貨單編號 : <input id="id" name="id" class="easyui-numberbox"/>
		發票編號 : <input id="invoiceNumber" name="invoiceNumber" />
		<br />
		供應商名稱 : <input id="supplierName" name="supplierName" />
		承辦人員 : <input id="userName" name="userName" />
		<br/>
		付款狀態:
			<input type="radio" name="status" value="1">已付款
			<input type="radio" name="status" value="0">未償付
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="intoGoods_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="intoGoods_clearSearch()">清空</a>
	
	</form>
	
</div>

	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>
			
<h2>進貨單</h2>
		<div id="main">
			
	<fieldset id="gridwrapper" class="gd1">
           
				
				
					<table id="listIntoGoods"></table>

			</fieldset>
			<fieldset id="gridwrapper" class="gd2">
           
				
				
					<table id="listGoodsItem"></table>

			</fieldset>
		
			
		
			
			<fieldset id="bads">
            
				<button type="button" onclick="self.location.href='admin/intoGoods/addIntoGoods.jsp'">
                  <img src="images/add.png" width="35" height="35"/>
				  <h6>新增</h6>
				</button>
				<button type="button" onclick="edit();">
                  <img src="images/2.png" width="35" height="35"/>
				  <h6>修改</h6>
				</button>
				<button type="button"  onclick="print();">
                  <img src="images/printer.png" width="35" height="35"/>
				  <h6>列印</h6>
				</button>
                <button type="button" onclick="">
                  <img src="images/search.png" width="35" height="35"/>
				  <h6>合併列印</h6>
				</button>
                <button type="button"  onclick="self.location.href='admin/backstage/inventory_management.jsp'">
                  <img src="images/quit.png" width="35" height="35"/>
				  <h6>退出</h6>
				</button>
                
			</fieldset>
		
		
		
	
	<div id="footer">&copy; ◎版權所有</div>
	</div>
    </div>
</body>
</html>
