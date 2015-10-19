<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="css2/listProduct.css" type="text/css"/>

<script type="text/javascript">
	
	$(function() {
		
		productDatagrid = $('#listProduct').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridProduct',
			title : '',
			iconCls : 'icon-save',
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
				width : 100
			}, {
				title : '分類',
				field : 'categoryName',
				width : 100,
				sortable : true
			}, {
				title : '商品名稱',
				field : 'name',
				width : 100,
				sortable : true
			}, {
				title : '進貨價格',
				field : 'purchasePrice',
				width : 100,
				sortable : true
			}, {
				title : '單位',
				field : 'unit',
				width : 100,
				sortable : true
			} ] ],
			toolbar : '#toolbarProduct'
		});
		
		product_search = function() {
			productDatagrid.datagrid('load', sy.serializeObject($('#product_searchForm')));
		};
		
		product_clearSearch = function() {
			var id = document.productForm.categoryId.value;
			productDatagrid.datagrid('load', {categoryId : id});
			$('#product_searchForm').find('input').val('');
			document.productForm.categoryId.value= id; 
		};
	});
	
	function loadProducts(categoryId) {
		document.productForm.categoryId.value= categoryId; 
		product_search();
	};

</script>
<style type="text/css">
#category ul {
	margin: 0;
	padding: 0;
	list-style: none;
	
}
#category ul li {
		
		background: #eee;
		
		font-size: 18px;
		z-index: 2;
		text-align:left;
		border-bottom:1px solid #CCC;
	
	}
#category ul li a {
		padding:16px;
		display:block;
		font-size: 18px;
		color:#666;
		text-align:left;
		font-weight:bold;
	
	
	}
#category ul li:hover {
		background: #333;
	
	}
#category ul li a:hover {
		color:#fff;
		text-decoration:none;
}	


</style>
<div id="toolbarProduct"style="height: 50px;">
	<form id="product_searchForm" name="productForm">
		<input type="hidden" name="categoryId" />
		商品名稱 : <input id="name" name="name" />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="product_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="product_clearSearch()">清空</a>
	</form>
</div>
<div id="admin_product_listProduct" style="width: 750px; height: 470px;" class="easyui-dialog"
	data-options="title:'選擇商品',modal:true,closed:true,closable:false,buttons:[{
				text:'確認',
				iconCls:'',
				handler:function(){
					var rows = productDatagrid.datagrid('getSelections');
					var ids = [];
					if (rows.length > 0) {
							for ( var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.ajax({
								url : '${pageContext.request.contextPath}/admin/addItem',
								data : {
										ids : ids.join(',')
								},
								dataType : 'json',
								success : function(d) {
									datagrid.datagrid('load', {});
									$('#admin_product_listProduct').dialog('close');
								}
							});
					} else {
						parent.sy.messagerAlert('提示', '請選擇商品 ！ ', 'error');
					}
				}
			},{
				text:'退出',
				iconCls:'',
				handler:function(){
					$('#admin_product_listProduct').dialog('close');
				}
			}]">
		
						
		<fieldset id="category">

        	<c:forEach items="${categorys}" var="c">																							
								
			<ul>
				<li>	
					<a href="javascript:void(0)" onclick="loadProducts(${c.id});">${c.name}</a>		
				</li>
					
				</ul>		
																				
			</c:forEach>
            
		</fieldset>
		<fieldset id="productgridwrapper">
           			
			<table id="listProduct"></table>

		</fieldset>
		

</div>