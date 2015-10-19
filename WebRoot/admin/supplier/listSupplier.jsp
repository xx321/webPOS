<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	$(function() {
		supplierDatagrid = $('#listSupplier').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridSupplier',
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
				title : '廠商名稱',
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
			toolbar : '#toolbarSupplier'
		});
		
		supplier_search = function() {

			supplierDatagrid.datagrid('load', sy.serializeObject($('#supplier_searchForm')));
		};
		
		supplier_clearSearch = function() {
			supplierDatagrid.datagrid('load', {});
			$('#supplier_searchForm').find('input').val('');
		};
	});

</script>
<div id="toolbarSupplier"style="height: 100px;">
	<form id="supplier_searchForm" >
		廠商名稱 : <input id="name" name="name" />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="supplier_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="supplier_clearSearch()">清空</a>
	</form>
</div>
<div id="admin_supplier_listSupplier" style="width: 850px; height: 510px;" class="easyui-dialog"
	data-options="title:'選擇供應商',modal:true,closed:true,closable:false,buttons:[{
				text:'確認',
				iconCls:'',
				handler:function(){
					var rows = supplierDatagrid.datagrid('getSelections');
					if(rows.length > 1) {
						datagrid.datagrid('unselectAll');
						parent.sy.messagerAlert('提示', '只能擇選一家廠商 ! ', 'error');
					} else if (rows.length == 1) {
						document.objectForm.supplierId.value= rows[0].id; 
						document.supplierForm.companyCode.value= rows[0].companyCode; 
						document.supplierForm.name.value= rows[0].name; 
						document.supplierForm.contact.value= rows[0].contact; 
						document.supplierForm.phone.value= rows[0].phone; 
						document.supplierForm.fax.value= rows[0].fax; 
						document.supplierForm.email.value= rows[0].email; 
						
			     		$('#admin_supplier_listSupplier').dialog('close');
					} else {
						parent.sy.messagerAlert('提示', '請選擇廠商 ！ ', 'error');
					}
				}
			},{
				text:'退出',
				iconCls:'',
				handler:function(){
					$('#admin_supplier_listSupplier').dialog('close');
				}
			}]">
			
		<fieldset style="width: 800px; height: 400px;">

			<table id="listSupplier"></table>

		</fieldset>

</div>