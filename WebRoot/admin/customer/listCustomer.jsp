<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	$(function() {
		customerDatagrid = $('#listCustomer').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridCustomer',
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
			columns : [ [ //{title : '編號',field : 'id'},
			{
				title : '統一編號',
				field : 'companyCode',
				width : 100,
				sortable : true
			}, {
				title : '客戶名稱',
				field : 'name',
				width : 100,
				sortable : true
			}, {
				title : '聯絡人',
				field : 'contact',
				width : 100,
				sortable : true
			}, {
				title : '電話',
				field : 'phone',
				width : 120,
				sortable : true
			}, {
				title : '傳真',
				field : 'fax',
				width : 120,
				sortable : true
			}, {
				title : '電子郵件',
				field : 'email',
				width : 200,
				sortable : true
			}, {
				title : '狀態',
				field : 'statusStr',
				width : 50
			} ] ],
			toolbar : '#toolbarCustomer'
		});
		
		customer_search = function() {

			customerDatagrid.datagrid('load', sy.serializeObject($('#customer_searchForm')));
		};
		
		customer_clearSearch = function() {
			customerDatagrid.datagrid('load', {});
			$('#customer_searchForm').find('input').val('');
		};
	});

</script>
<div id="toolbarCustomer"style="height: 100px;">
	<form id="customer_searchForm" >
		客戶名稱 : <input id="name" name="name" />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="customer_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="customer_clearSearch()">清空</a>
	</form>
</div>
<div id="admin_customer_listCustomer" style="width: 850px; height: 510px;" class="easyui-dialog"
	data-options="title:'選擇客戶',modal:true,closed:true,closable:false,buttons:[{
				text:'確認',
				iconCls:'',
				handler:function(){
					var rows = customerDatagrid.datagrid('getSelections');
					if(rows.length > 1) {
						datagrid.datagrid('unselectAll');
						parent.sy.messagerAlert('提示', '只能擇選一家客戶 ! ', 'error');
					} else if (rows.length == 1) {
						document.objectForm.customerId.value= rows[0].id; 
						document.customerForm.companyCode.value= rows[0].companyCode; 
						document.customerForm.name.value= rows[0].name; 
						document.customerForm.contact.value= rows[0].contact; 
						document.customerForm.phone.value= rows[0].phone; 
						document.customerForm.fax.value= rows[0].fax; 
						document.customerForm.email.value= rows[0].email; 
						
			     		$('#admin_customer_listCustomer').dialog('close');
					} else {
						parent.sy.messagerAlert('提示', '請選擇客戶 ！ ', 'error');
					}
				}
			},{
				text:'退出',
				iconCls:'',
				handler:function(){
					$('#admin_customer_listCustomer').dialog('close');
				}
			}]">
			
		<fieldset style="width: 800px; height: 400px;">

			<table id="listCustomer"></table>

		</fieldset>

</div>