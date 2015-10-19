<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">

		$(function() {
		
			subjectDatagrid = $('#listSubject').datagrid({
				url : '${pageContext.request.contextPath}/admin/datagridSubject',
				title : '',
				iconCls : '',
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
					title : '編號',
					field : 'id',
					width : 100,
					sortable : true
				}, {
					title : '營業項目',
					field : 'name',
					width : 100,
					sortable : true
				}, {
					title : '顯示順序',
					field : 'displayOrder',
					width : 100,
					sortable : true
				}, {
					title : '狀態',
					field : 'statusStr',
					width : 120
				}, {
					title : '備註',
					field : 'description',
					width : 250
				},  ] ],
				toolbar : '#toolbarSubject'
			});
			
			subject_search = function() {
				subjectDatagrid.datagrid('load', sy.serializeObject($('#subject_searchForm')));
			};
			
			subject_clearSearch = function() {
				subjectDatagrid.datagrid('load', {status : 1});
				document.subject_searchForm.name.value= ''; 
			};
		});

</script>
<div id="toolbarSubject"style="height: 100px;">
	<form id="subject_searchForm" name="subject_searchForm">
	<input type="hidden" name="status" value="1">
		查詢項目 : <input id="name" name="name" />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="subject_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="subject_clearSearch()">清空</a>
	</form>
</div>
<div id="admin_subject_listSubject" style="width: 850px; height: 510px;" class="easyui-dialog"
	data-options="title:'選擇零用金科目',modal:true,closed:true,closable:false,buttons:[{
				text:'確認',
				iconCls:'',
				handler:function(){
					var rows = subjectDatagrid.datagrid('getSelections');
					var ids = [];
					if (rows.length > 0) {
							for ( var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.ajax({
								url : '${pageContext.request.contextPath}/admin/addSpendingItem',
								data : {
										ids : ids.join(',')
								},
								dataType : 'json',
								success : function(d) {
									datagrid.datagrid('load', {});
									$('#admin_subject_listSubject').dialog('close');
								}
							});
					} else {
						parent.sy.messagerAlert('提示', '請選擇零用金科目 ！ ', 'error');
					}
				}
			},{
				text:'退出',
				iconCls:'',
				handler:function(){
					$('#admin_subject_listSubject').dialog('close');
				}
			}]">
			
		<fieldset style="width: 800px; height: 400px;">

			<table id="listSubject"></table>

		</fieldset>

</div>