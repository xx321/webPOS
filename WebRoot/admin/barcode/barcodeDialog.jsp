<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 輸入條碼編號 -->
<script type="text/javascript">
$(function() {
	$('#barcodeForm input[name=barcodeNumber]').bind('keyup', function(event) {/* 增加按下Enter提交的功能*/
		if (event.keyCode == '13') {
			editBarcodeNumber();
		}
	});
});
	function openBarcodeDialog() {  
	    $('#barcodeDialog').dialog('open');
		window.setTimeout(function() {
			$('#barcodeForm input[name=barcodeNumber]').focus();	
		}, 0);
	};
	function editBarcodeNumber() {
		$("input[name='product.barcodeNumber']").val(document.barcodeForm.barcodeNumber.value);
		document.barcodeForm.barcodeNumber.value = "";
		$('#barcodeDialog').dialog('close');
	};
	
	function DefaultNumber() {
		$("input[name='product.barcodeNumber']").val("預設");
		document.barcodeForm.barcodeNumber.value = "";
	    $('#barcodeDialog').dialog('close');
	};

</script>

<div id="barcodeDialog" style="width: 350px;" class="easyui-dialog"
	data-options="title:'輸入條碼編號',modal:true,closable:true,closed:true,buttons:[{
				text:'確認',
				iconCls:'icon-edit',
				handler:function(){
					editBarcodeNumber();
				}
			},{
				text:'預設',
				iconCls:'icon-help',
				handler:function(){
					DefaultNumber();
				}
			}]">

		<form id="barcodeForm" name="barcodeForm" onsubmit="return false">
			<input style="font-size:22px;" id="w1" type="text" size="20"
				name="barcodeNumber" value="" class="easyui-validatebox" data-options="required:true">
		</form>
</div>

