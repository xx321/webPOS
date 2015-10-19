<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">

	$(function() {
		$('#admin_audit_auditForm').form({
			url:'${pageContext.request.contextPath}/admin/auditPrepare',  
			success:function(r){
				var obj = jQuery.parseJSON(r);
				$('#admin_audit_auditDialog').dialog('close');
				if(obj.success) {
					document.auditForm.submit();			
				} else {
					$.messager.show({
						title:'提示',
						msg:obj.msg,
					});
				}
			}
		});
		$('#admin_audit_auditForm input').bind('keyup', function(event) {/* 增加按下Enter提交的功能*/
			if (event.keyCode == '13') {
				$('#admin_audit_auditForm').submit();
			}
		});
	});
</script>
<div id="admin_audit_auditDialog" style="width: 250px;" class="easyui-dialog"
	data-options="title:'審核確認',modal:true,closed:true,buttons:[{
				text:'確認',
				iconCls:'icon-edit',
				handler:function(){
					$('#admin_audit_auditForm').submit();
				}
			}]">
	<form id="admin_audit_auditForm" method="post">
		<table>
			<tr>
				<th>帳號</th>
				<td><input name="user.account" class="easyui-validatebox"
					data-options="required:true,missingMessage:'請輸入帳號'" /></td>
			</tr>
			<tr>
				<th>密碼</th>
				<td><input name="user.password" type="password"
					class="easyui-validatebox" data-options="required:true,missingMessage:'請輸入密碼'" /></td>
			</tr>
		</table>
	</form>
</div>