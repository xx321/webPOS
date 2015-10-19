<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 確認付款 -->
<script type="text/javascript">

	function addPayment() {
		var rows = listIntoGoods.datagrid('getSelections');
		var ids = [];
		for ( var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addPayment',
			data : {
				intoGoodsIds : ids.join(','),
				paymentTimeStr : document.addPaymentFrom.paymentTimeStr.value,
				paymentMode : $('input[name="paymentMode"]:checked').val(),
				description : document.addPaymentFrom.description.value
			},
			dataType : 'json',
			success : function(d) {
				if (d && d.success) {
					for ( var i = 0; i < rows.length; i++) {
						if (d.obj[i].status == 1) {
							listIntoGoods.datagrid('updateRow', {
								index : listIntoGoods.datagrid('getRowIndex', rows[i]),
								row : {
									status : d.obj[i].status,
									statusStr : d.obj[i].statusStr,
									paymentPersonnelName : d.obj[i].paymentPersonnelName,
									paymentTimeStr : d.obj[i].paymentTimeStr
								}
							});
						} else {
							listIntoGoods.datagrid('updateRow', {
								index : listIntoGoods.datagrid('getRowIndex', rows[i]),
								row : {
									status : d.obj[i].status,
									statusStr : d.obj[i].statusStr,
									paymentPersonnelName : '',
									paymentTimeStr : ''
								}
							});
						}
					}
					listIntoGoods.datagrid('acceptChanges');
					exportExcel();
				} else {
					listIntoGoods.datagrid('rejectChanges');
				}
				listIntoGoods.datagrid('unselectAll');
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
				closePayment_addDialog();
				listIntoGoods.datagrid('load', {});
			}
		});
	};
	
	function closePayment_addDialog() {  //關閉視窗時，還原初始值。
		document.addPaymentFrom.intoGoodsIds.value = '';
		var now = new Date();
		var nowStr = parseInt(now.getFullYear())+"/"+ (parseInt(now.getMonth())+1)+"/"+( parseInt(now.getDate()));
		document.addPaymentFrom.paymentTimeStr.value = nowStr;
		$('input[name="paymentMode"]')[1].checked = true;
		document.addPaymentFrom.description.value = '';
		
		
		listIntoGoods.datagrid('unselectAll');
		listGoodsItem.datagrid('load', {});
		
		
		$('#payment_addDialog').dialog('close');
	};
	
	function exportExcel() {
		var url = "${pageContext.request.contextPath}/admin/exportExcelLastPayment";
		window.location.href = url;
	}
</script>

	<div id="payment_addDialog" style="width: 350px;" class="easyui-dialog"
	data-options="title:'確認付款',modal:true,closable:true,closed:true,buttons:[{
				text:'確認',
				iconCls:'icon-ok',
				handler:function(){
					addPayment();
				}
			},{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					closePayment_addDialog();
				}
			}]">
			
		<s:form id="addPaymentFrom" name="addPaymentFrom">
						<input type=hidden name="intoGoodsIds">
						<p>
							<label>付款時間:</label> <input size="20" id="paymentTimeStr" name="paymentTimeStr" maxlength="20" readonly="readonly">
								<input type="button" value="" id="BTN3" name="BTN3" />
								<script type="text/javascript">
									new Calendar({
										inputField : "paymentTimeStr",
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
							<label>付款方式:</label>
							<input type="radio" name="paymentMode" value="1"> 匯款方式
							<input type="radio" name="paymentMode" value="0" checked="checked"> 現金支出
						</p>
						<p>
							<label>備註:</label>
						</p>
						<p>
							<textarea name="description" rows="4" cols="30"></textarea>
						</p>
		</s:form>
	</div>