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

<title>採購單</title>

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
		
		editRow = undefined;
		
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/goodsItemDataGrid',
			title : '',
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
			} ] ],
			onAfterEdit : function(rowIndex, rowData, changes) {
				datagrid.datagrid('updateRow', {
					index : rowIndex,
					row : {
						total : Math.round(eval(rowData.quantity * rowData.purchasePrice))
					}
				});
				//這裡實現 : 前台數據修改完成後，傳至後台做更新。
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/editGoodsItem',
					data : rowData,				
					dataType : 'json',
					success : function(r) {
						if (r && r.success) {
							datagrid.datagrid('acceptChanges');
						} else {
							datagrid.datagrid('rejectChanges');
						}
						datagrid.datagrid('unselectAll');
						editRow = undefined;
						
						choiceTax(document.taxForm);
					}
				});
				
			}, 
	        onDblClickCell:function(rowIndex, field, value){  

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
					} else if (field == 'purchasePrice') {
						datagrid.datagrid('addEditor', {
							field : field,
							editor : {
								type : 'numberbox',
								options : {
									required : true,
									precision : 5
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
	            var editors = $('#datagrid').datagrid('getEditors', rowIndex);  
	            for (var i = 0; i < editors.length; i++){  
					var editor = editors[i];     
					if(editor.field == field) {						
						editor.target.focus();	
						editor.target.bind("blur",function(){
							datagrid.datagrid('endEdit', editRow);	           		
		            	}); 
					} 
	            }  
	        }
		});
		
		var now = new Date();
		var nowStr = parseInt(now.getFullYear())+"/"+ (parseInt(now.getMonth())+1)+"/"+( parseInt(now.getDate()));
		document.objectForm.createTimeStr.value = nowStr;
	});

	function listSupplierOpen() {
		supplierDatagrid.datagrid('load', {});
		$('#supplier_searchForm').find('input').val('');
		$('#admin_supplier_listSupplier').dialog('open');
		
	};
	
	function listProductOpen() {
		productDatagrid.datagrid('load', {categoryId : '100'});
		$('#product_searchForm').find('input').val('');
		document.productForm.categoryId.value= '100'; 
		$('#admin_product_listProduct').dialog('open');
	};
	//前後台分離，刪除所選商品項目。
	function removeitem() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
				for ( var i = 0; i < rows.length; i++) {
					$('#datagrid').datagrid('deleteRow', $('#datagrid').datagrid('getRowIndex', rows[i]));  
					ids.push(rows[i].productId);
				}
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/deleteGoodsItem',
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
						choiceTax(document.taxForm);
					}
				});
		} else {

		}
	};
	//前台無刷新，重新載入表格底部數據。
	function loadFooter(tax) {
		var rows = datagrid.datagrid('getRows');
		var totalAmount = 0;
		for ( var i = 0; i < rows.length; i++) {
			totalAmount = totalAmount + rows[i].total;
		}
		datagrid.datagrid('reloadFooter',[{
			productName : '',
			quantity : '',
			purchasePrice : '合計',
			total : Math.round(totalAmount)
		}, {
			productName : '',
			quantity : '',
			purchasePrice : '營業稅',
			total : Math.round(eval(totalAmount * tax))
		}, {
			productName : '',
			quantity : '',
			purchasePrice : '總計',
			total : Math.round(eval(totalAmount * eval(1 + tax)))
		}
		]);
	};
	
	function addIntoGoods() {
		document.objectForm.submit();
	};
	
	function choiceTax(taxForm) {
		var tax;
	      
		if(taxForm.taxChoice.checked) {
			tax = 0;
			document.objectForm.tax.value = "0";
		}
		else {	
			tax = 0.05;
			document.objectForm.tax.value = "0.05";
		}
		loadFooter(tax);
	};
</script>
<body>
	<jsp:include page="/admin/supplier/listSupplier.jsp"></jsp:include>
	<jsp:include page="/admin/product/listProduct.jsp"></jsp:include>
	
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png" /> </a>
			</div>
		</div>

		<h2>採購單</h2>
		<div id="main">
			<fieldset>
				<div id="r1">

					<form name="supplierForm">
						<p>
							<label>統一編號:</label> <input name="companyCode" size="20" maxlength="20" readonly="readonly"> 
								<span> 
									<a href="javascript:void(0)" onclick="listSupplierOpen();"><img src="images/firm.png" width="25" height="25"  /></a>
								</span>
						</p>
						<p>
							<label>廠商名稱:</label> <input name="name" size="20" maxlength="20" readonly="readonly">
						</p>
						<p>
							<label>聯絡人:</label> <input name="contact" size="20"
								maxlength="20" readonly="readonly">
						</p>
						<p>
							<label>電話:</label> <input name="phone" size="20" maxlength="20" readonly="readonly">
						</p>
						<p>
							<label>傳真:</label> <input name="fax" size="20" maxlength="20" readonly="readonly">
						</p>
						<p>
							<label>E-mail:</label> <input name="email" size="20"
								maxlength="20" readonly="readonly">
						</p>
					</form>
					<p>
							<label>稅別設定:</label>
					</p>
					<form name="taxForm">
    					<input type="checkbox"  name="taxChoice" onclick="choiceTax(this.form)">含稅<p> 
    				</form>					
				</div>
				<div id="r2">
					<s:form name="objectForm" action="addPurchase"
						namespace="/admin">
						<input type=hidden name="supplierId">
						<input type="hidden" name ="status" value="0">
						<input type="hidden" name ="closed" value="0">
						<input type="hidden" name="tax" value="0.05">
						
						<p>
							<label>單據日:</label> <input size="20" id="createTimeStr" name="createTimeStr" maxlength="20" readonly="readonly">
								<input type="button" value="" id="BTN2" name="BTN2" />
								<script type="text/javascript">
									new Calendar({
										inputField : "createTimeStr",
										dateFormat : "%Y/%m/%d",
										trigger : "BTN2",
										bottomBar : true,
										weekNumbers : false,
										onSelect : function() {
											this.hide();
										}
									});
								</script>
						</p>						
						<p>
							<label>發票號碼:</label> <input name="invoiceNumber" size="20"
								maxlength="20">
						</p>
						<p>
							<label>付款方式:</label>
							<input type="radio" name="paymentMode" value="1"> 匯款方式
							<input type="radio" name="paymentMode" value="0" checked="checked"> 現金支出
						</p>					
						<p>
							<label>備註:</label>
						</p>
						<p>
							<textarea name="description" rows="5" cols="40"></textarea>
						</p>
					</s:form>
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
				<button type="button" onclick="addIntoGoods();">
					<img src="images/save.png" width="35" height="35" />
					<h6>儲存</h6>
				</button>
				<button type="button" onclick="self.location.href='admin/purchase.jsp'">
					<img src="images/quit.png" width="35" height="35" />
					<h6>退出</h6>
				</button>
			</fieldset>

		</div>


		<div id="footer">&copy; ◎版權所有</div>
	</div>

</body>
</html>
