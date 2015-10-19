<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>修改營業費用</title>

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
			url : '${pageContext.request.contextPath}/admin/spendingItemDataGrid',
			title : '',
			iconCls : '',
			pagination : false,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			showFooter : true,
			columns : [ [ {
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
			} ] ],
			onAfterEdit : function(rowIndes, rowData, changes) {
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/editSpendingItem',
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
			onDblClickCell : function(rowIndex, field, value) {
				if (editRow != undefined) {
					datagrid.datagrid('endEdit', editRow);
				}
				if (editRow == undefined) {
					if (field == 'total') {
						datagrid.datagrid('addEditor', {
							field : field,
							editor : {
								type : 'numberbox',
								options : {
									required : true,
									precision : 2
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
		
	});

	function listSupplierOpen() {
		supplierDatagrid.datagrid('load', {});
		$('#supplier_searchForm').find('input').val('');
		$('#admin_supplier_listSupplier').dialog('open');
	};
	
	function listSubjectOpen() {
		subjectDatagrid.datagrid('load', {status : 1});
		document.subject_searchForm.name.value= ''; 
		$('#admin_subject_listSubject').dialog('open');

	};
	
	function removeitem() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
				for ( var i = 0; i < rows.length; i++) {
					$('#datagrid').datagrid('deleteRow', $('#datagrid').datagrid('getRowIndex', rows[i]));  
					ids.push(rows[i].subjectId);
				}
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/deleteSpendingItem',
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
			totalAmount = eval(totalAmount * 100 + rows[i].total * 100) / 100;
		}
		datagrid.datagrid('reloadFooter', [ {
			subjectName : '合計',
			total : totalAmount
		}, {
			subjectName : '營業稅',
			total : eval(totalAmount * tax).toFixed(2)
		}, {
			subjectName : '總計',
			total : eval(totalAmount * eval(1 + tax)).toFixed(2)
		} ]);
		document.objectForm.total.value = totalAmount;
		document.objectForm.salesTax.value = eval(totalAmount * tax).toFixed(2);
		document.objectForm.totalAmount.value = Math.round(eval(totalAmount * eval(1 + tax))); 
	};

	function addPettyCash() {
		document.objectForm.username.value = document.supplierForm.username.value;
		document.objectForm.paymentAccount.value = document.supplierForm.paymentAccount.value;
		document.objectForm.paymentTimeStr.value = document.supplierForm.paymentTimeStr.value;
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
	function choicePaymentAccount(form) {
		var account;
	    for (var i=0; i<form.choiceAccount.length; i++)
	    {
	      if (form.choiceAccount[i].checked)
	      {
	    	  account = form.choiceAccount[i].value;
	    	  break;
	      }
	    }	
		if (account == '現金') {
			form.paymentAccount.value = "現金支出";
			form.paymentAccount.readOnly = "true";
		} else if (account == '銀行') {
			form.paymentAccount.value = "";
			form.paymentAccount.readOnly = "";
		}                               
	}
</script>
<body>
	<jsp:include page="/admin/supplier/listSupplier.jsp"></jsp:include>
	<jsp:include page="/admin/subject/listSubject.jsp"></jsp:include>
	
	<div class="center">
		<div id="header">
			<div>
				<img src="images/logo.png" />
			</div>


		</div>

		<h2>修改營業費用</h2>
		<div id="main">
			<fieldset>
				<div id="r1">

					<form name="supplierForm">
						<input type="hidden" name="companyCode"/>
						<input type="hidden" name="contact"/>
						<input type="hidden" name="phone"/>
						<input type="hidden" name="fax"/>
						<input type="hidden" name="email"/>
						<p>
							<label>廠商:</label> <input value="${pettyCash.supplier.name}" name="name" size="20" maxlength="20" readonly="readonly">
								<span> 
									<a href="javascript:void(0)" onclick="listSupplierOpen();"><img src="images/firm.png" width="25" height="25"  /></a>
								</span>
						</p>
					
						<p>
							<label>經辦人:</label> <input value="${pettyCash.username}" name="username" size="20"
								maxlength="20">
						</p>
						
						<p>
							<c:choose>
							<c:when test="${pettyCash.paymentAccount=='現金支出'}">
			       				<input type="radio" value="現金" name="choiceAccount" onclick="choicePaymentAccount(this.form)" checked="checked">現金
	                            <input type="radio" value="銀行" name="choiceAccount" onclick="choicePaymentAccount(this.form)">銀行
		       				</c:when>
		       				<c:otherwise>
			       				<input type="radio" value="現金" name="choiceAccount" onclick="choicePaymentAccount(this.form)">現金
	                            <input type="radio" value="銀行" name="choiceAccount" onclick="choicePaymentAccount(this.form)" checked="checked">銀行 
		       				</c:otherwise>
		       				</c:choose> 	
						</p>
						<p>
							<c:choose>
							<c:when test="${pettyCash.paymentAccount=='現金支出'}">
		       					<label>支出帳戶:</label> <input value="${pettyCash.paymentAccount}" name="paymentAccount" size="20" maxlength="20" readonly="readonly">
		       				</c:when>
		       				<c:otherwise>
		       					<label>支出帳戶:</label> <input value="${pettyCash.paymentAccount}" name="paymentAccount" size="20" maxlength="20">
		       				</c:otherwise>
		       				</c:choose> 	
						</p>
						<p>
							<label>支出日期:</label> <input id="paymentTimeStr" name="paymentTimeStr" value="${paymentTimeStr}"
									size="20" maxlength="20"  readonly="readonly">
								<input type="button" value="" id="BTN1" name="BTN1" />
								<script type="text/javascript">
									new Calendar({
										inputField : "paymentTimeStr",
										dateFormat : "%Y/%m/%d",
										trigger : "BTN1",
										bottomBar : true,
										weekNumbers : false,
										onSelect : function() {
											this.hide();
										}
									});
								</script>
						</p>
						</form>
							<p>
							<label>稅別設定:</label>
						</p>
						<form name="taxForm">   			
    					<c:choose>
						<c:when test="${pettyCash.tax==0.05}">
	       					<input type="checkbox"  name="taxChoice" onclick="choiceTax(this.form)">含稅<p> 
	       				</c:when>
	       				<c:otherwise>
	       					<input type="checkbox"  name="taxChoice" onclick="choiceTax(this.form)" checked>含稅<p> 
	       				</c:otherwise>
	       				</c:choose> 			
						</form>
				</div>
				<div id="r2">
					<s:form name="objectForm" action="updatePettyCash"
						namespace="/admin">
						
						<input type="hidden" name="id" value="${pettyCash.id}">
						<input type=hidden name="supplierId" value="${pettyCash.supplier.id}">
						<input type=hidden name="username" value="${pettyCash.username}">
						<input type=hidden name="total" value="${pettyCash.total}">
						<input type="hidden" name="tax" value="${pettyCash.tax}">
						<input type=hidden name="salesTax" value="${pettyCash.salesTax}">
						<input type=hidden name="totalAmount" value="${pettyCash.totalAmount}">
						<input type=hidden name="paymentAccount" value="${pettyCash.paymentAccount}">
						<input type=hidden name="paymentTimeStr" value="${paymentTimeStr}">
						<input type="hidden" name="status" value="${pettyCash.status}">
						
						<p>
							<label>單據日:</label> <input size="20" id="createTimeStr" name="createTimeStr" value="${createTimeStr}"
									maxlength="20" readonly="readonly">
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
							<label>備註:</label>
						</p>
						<p>
							<textarea name="description" rows="5" cols="40">${pettyCash.description}</textarea>
						</p>
					</s:form>
				</div>
			</fieldset>

			<fieldset id="gridwrapper">


				<table id="datagrid"></table>


			</fieldset>


			<fieldset id="bads">

				<button type="button" onclick="listSubjectOpen();">
					<img src="images/add.png" width="35" height="35" />
					<h6>新增</h6>
				</button>
				<button type="button" onclick="removeitem();">
					<img src="images/del.png" width="35" height="35" />
					<h6>刪除</h6>
				</button>
				<button type="button" onclick="addPettyCash();">
					<img src="images/save.png" width="35" height="35" />
					<h6>儲存</h6>
				</button>
				<button type="button" onclick="self.location.href='admin/pettyCash.jsp'">
					<img src="images/quit.png" width="35" height="35" />
					<h6>退出</h6>
				</button>
			</fieldset>

		</div>


		<div id="footer">&copy; ◎版權所有</div>
	</div>

</body>
</html>
