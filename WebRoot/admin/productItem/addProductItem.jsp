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

<title>添加組合商品</title>

<link rel="stylesheet" href="css2/purchase.css" type="text/css" />

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

</head>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {

		editRow = undefined;

		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/productItemDataGrid',
			title : '',
			iconCls : '',
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			columns : [ [ 
			{
				title : '商品編號',
				field : 'productId',
				width : 80
			}, {
				title : '商品名稱',
				field : 'itemName',
				width : 80
			}, {
				title : '數量',
				field : 'quantity',
				width : 80,
			} ] ],
			onAfterEdit : function(rowIndex, rowData, changes) {
				//這裡實現 : 前台數據修改完成後，傳至後台做更新。
				$
						.ajax({
							url : '${pageContext.request.contextPath}/admin/editProductItem',
							data : rowData,
							dataType : 'json',
							success : function(r) {
								if (r && r.success) {
									datagrid
											.datagrid('acceptChanges');
								} else {
									datagrid
											.datagrid('rejectChanges');
								}
								datagrid
										.datagrid('unselectAll');
								editRow = undefined;

							}
						});

			},
			onDblClickCell : function(rowIndex, field, value) {

				if (editRow != undefined) {
					datagrid.datagrid('endEdit', editRow);
				}
				if (editRow == undefined) {
					if (field == 'quantity') {
						datagrid.datagrid('addEditor', {
							field : field,
							editor : {
								type : 'numberbox',
								options : {
									required : true,
								}
							}
						});
					} else {
						return;
					}
					datagrid.datagrid('beginEdit', rowIndex);
					datagrid.datagrid('removeEditor', field);
					editRow = rowIndex;
				}
				//为editor绑定自定义事件  
				var editors = $('#datagrid').datagrid(
						'getEditors', rowIndex);
				for ( var i = 0; i < editors.length; i++) {
					var editor = editors[i];
					if (editor.field == field) {
						editor.target.focus();
						editor.target.bind("blur", function() {
							datagrid.datagrid('endEdit',
									editRow);
						});
					}
				}
			}
		});
		
	});

	function listProductOpen() {
		productDatagrid.datagrid('load', {
			categoryId : '100'
		});
		$('#product_searchForm').find('input').val('');
		document.productForm.categoryId.value = '100';
		$('#admin_product_listProduct').dialog('open');
	};
	//前後台分離，刪除所選商品項目。
	function removeitem() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				$('#datagrid').datagrid('deleteRow',
						$('#datagrid').datagrid('getRowIndex', rows[i]));
				ids.push(rows[i].productId);
			}
			$
					.ajax({
						url : '${pageContext.request.contextPath}/admin/deleteProductItem',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d && d.success) {
								datagrid.datagrid('acceptChanges');
							} else {
								datagrid.datagrid('rejectChanges');
							}
							datagrid.datagrid('unselectAll');
						}
					});
		} else {

		}
	};

</script>
<body>
	<jsp:include page="/admin/product/listProductForItem.jsp"></jsp:include>
	<!-- 輸入條碼編號 begin -->
	<jsp:include page="/admin/barcode/barcodeDialog.jsp"></jsp:include>
	<!-- 輸入條碼編號 end -->
	
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png" /> </a>
			</div>
		</div>

		<h2>添加組合商品</h2>
		<div id="main">

			

			<s:form name="addProductItem" action="addProductItem" namespace="/admin" theme="simple">
				<fieldset>
				<div id="r1">

					<s:hidden name="product.id" />
					<s:hidden name="product.stockNumber" />
					<s:hidden name="product.type" value="2"/>
					<s:hidden name="product.category.id" value="99"/>
										
					<p>
						<label id="add1">分類:</label>
						組合商品
					</p>
					
					<p>
						<label id="add1">名稱:</label>
						<s:textfield name="product.name" size="20" maxlength="20" />
					</p>
					<p>
						<label id="add1">單位:</label>
						<s:textfield name="product.unit" size="2" maxlength="2" />
					</p>
					<p>
						<label id="add1">價格:</label>
						<s:textfield name="product.price" size="20" maxlength="20" />
					</p>
					<p>
						<label id="add1">顯示順序:</label>
						<s:textfield name="product.displayOrder" class="required" size="2" maxlength="2" />
					</p>
					<p>
						<label id="add1">條碼編號:</label> 
						<s:textfield value="預設" name="product.barcodeNumber" size="20" maxlength="20" readonly="true" />
						<button type="button" onclick="openBarcodeDialog()">
							設定條碼編號
						</button>		
					</p>					
					<p>
						<label id="add1">狀態:</label>
					</p>
					
					<s:radio name="product.status" list="#{'1':'開啟','0':'禁用'}" value="1"/>
				</div>
				
				<div id="r2">
					<p>
						<label id="add1">描述:</label>
						<s:textarea name="product.description" cols="35" rows="8" />
					</p>

					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['product.name'][0]" /><br />
					<s:property value="errors['product.unit'][0]" /><br />					
					<s:property value="errors['product.price'][0]"/><br />
					<s:property value="errors['product.displayOrder'][0]"/><br />
					<s:property value="errors['product.description'][0]"/><br />
				</div>
				
				</fieldset>
				<fieldset id="gridwrapper">
	
	
					<table id="datagrid"></table>
	
	
				</fieldset>
				
				<fieldset id="bads">

				<button type="button" onclick="listProductOpen();">
					<img src="images/add.png" width="35" height="35" />
					<h6>新增</h6>
				</button>
				<button type="button" onclick="removeitem();">
					<img src="images/del.png" width="35" height="35" />
					<h6>刪除</h6>
				</button>
				<button type="submit">
					<img src="images/save.png" width="35" height="35" />
					<h6>儲存</h6>
				</button>
				<button type="button"
					onclick="self.location.href='admin/product.jsp'">
					<img src="images/quit.png" width="35" height="35" />
					<h6>退出</h6>
				</button>
			</fieldset>
					</s:form>	
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
