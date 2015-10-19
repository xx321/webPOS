<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 確認收款 -->
<script type="text/javascript">

	function addIncome() {
		var rows = listSell.datagrid('getSelections');
		var ids = [];
		for ( var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addIncome',
			data : {
				sellIds : ids.join(','),
				incomeTimeStr : document.addIncomeFrom.incomeTimeStr.value,   
				incomeMode : $('input[name="incomeMode"]:checked').val(),
				description : document.addIncomeFrom.description.value,
				checkNumber : document.addIncomeFrom.checkNumber.value,
				checkAmount : document.addIncomeFrom.checkAmount.value,
				checkDateStr : document.addIncomeFrom.checkDateStr.value
			},
			dataType : 'json',
			success : function(d) {
				if (d && d.success) {
					for ( var i = 0; i < rows.length; i++) {
						if (d.obj[i].status == 1) {
							listSell.datagrid('updateRow', {
								index : listSell.datagrid('getRowIndex', rows[i]),
								row : {
									status : d.obj[i].status,
									statusStr : d.obj[i].statusStr,
									incomePersonnelName : d.obj[i].incomePersonnelName,
									incomeTimeStr : d.obj[i].incomeTimeStr
								}
							});
						} else {
							listIntoGoods.datagrid('updateRow', {
								index : listSell.datagrid('getRowIndex', rows[i]),
								row : {
									status : d.obj[i].status,
									statusStr : d.obj[i].statusStr,
									incomePersonnelName : '',
									incomeTimeStr : ''
								}
							});
						}
					}
					listSell.datagrid('acceptChanges');
					exportExcel();
				} else {
					listSell.datagrid('rejectChanges');
				}
				listSell.datagrid('unselectAll');
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
				closeIncome_addDialog();
				listSell.datagrid('load', {});
			}
		});
	};
	
	function closeIncome_addDialog() {  //關閉視窗時，還原初始值。
		document.addIncomeFrom.sellIds.value = '';
		var now = new Date();
		var nowStr = parseInt(now.getFullYear())+"/"+ (parseInt(now.getMonth())+1)+"/"+( parseInt(now.getDate()));
		document.addIncomeFrom.incomeTimeStr.value = nowStr;
		$('input[name="incomeMode"]')[0].checked = true;
		document.addIncomeFrom.description.value = '';
		
		clear_checkData();

		listSell.datagrid('unselectAll');
		listSellitem.datagrid('load', {});
		
		
		$('#income_addDialog').dialog('close');
	};
	
	function exportExcel() {
		//不需要匯出excel 的功能
		//var url = "${pageContext.request.contextPath}/admin/exportExcelLastIncome";
		//window.location.href = url;
	}
	
	function clear_checkData() {
		document.addIncomeFrom.checkNumber.value = '';
		document.addIncomeFrom.checkNumber.readOnly = true;
		$('#checkAmount').numberbox('setValue', '');
		$('#checkAmount').numberbox({    
			disabled:true
		});  
		document.addIncomeFrom.checkDateStr.value = '';
		document.addIncomeFrom.BTN4.disabled = true;
	};
	
	function choiceIncomeMode(form) {
		var incomeMode;
	    for (var i=0; i<form.incomeMode.length; i++)
	    {
	      if (form.incomeMode[i].checked)
	      {
	    	  incomeMode = form.incomeMode[i].value;
	    	  break;
	      }
	    }	
		if (incomeMode == '1') {
			clear_checkData();
		} else if (incomeMode == '0') {
			document.addIncomeFrom.checkNumber.readOnly = false;	
			$('#checkAmount').numberbox({    
				disabled: false
			});  
			document.addIncomeFrom.BTN4.disabled = false;
		}  
	};
</script>

	<div id="income_addDialog" style="width: 350px;" class="easyui-dialog"
	data-options="title:'確認收款',modal:true,closable:true,closed:true,buttons:[{
				text:'確認',
				iconCls:'icon-ok',
				handler:function(){
					addIncome();
				}
			},{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					closeIncome_addDialog();
				}
			}]">
			
		<s:form id="addIncomeFrom" name="addIncomeFrom">
						<input type=hidden name="sellIds">
						<p>
							<label>收款時間:</label> <input size="20" id="incomeTimeStr" name="incomeTimeStr" maxlength="20" readonly="readonly">
								<input type="button" value="" id="BTN3" name="BTN3" />
								<script type="text/javascript">
									new Calendar({
										inputField : "incomeTimeStr",
										dateFormat : "%Y/%m/%d",
										trigger : "BTN3",
										bottomBar : true,
										weekNumbers : false,
										onSelect : function() {
											this.hide();
										}
									});
								</script>
						</p>
						
						<p>
							<label>收款方式:</label>
							<input type="radio" name="incomeMode" value="1" checked="checked" onclick="choiceIncomeMode(this.form)"> 現金收入
							<input type="radio" name="incomeMode" value="0" onclick="choiceIncomeMode(this.form)"> 支票收入
						
						</p>
						<p>
						<label>支票號碼:</label><input name="checkNumber" type="text" readonly="readonly"/> 
						</p>
						<p>
							<label>支票金額:</label><input id="checkAmount" name="checkAmount" class="easyui-numberbox" data-options="disabled:true"/>
						</p>
						<p>
							<label>兌現日期:</label> <input size="20" id="checkDateStr" name="checkDateStr" maxlength="20" readonly="readonly">
								<input type="button" value="" id="BTN4" name="BTN4" disabled="disabled"/>
								<script type="text/javascript">
									new Calendar({
										inputField : "checkDateStr",
										dateFormat : "%Y/%m/%d",
										trigger : "BTN4",
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
							<textarea id="description" name="description" rows="4" cols="30"></textarea>
						</p>
		</s:form>
	</div>