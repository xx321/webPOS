<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>拆箱設定</title>

<link href="css2/jqvalidate.css" rel="stylesheet" type="text/css"/>
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/reset.css" type="text/css"/>

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

</head>
<script type="text/javascript" charset="utf-8">
	

	function listProductOpen() {
		productDatagrid.datagrid('load', {
			categoryId : '100'
		});
		$('#product_searchForm').find('input').val('');
		document.productForm.categoryId.value = '100';
		$('#admin_product_listProduct').dialog('open');
	};
	//前後台分離，刪除所選商品項目。
	function removebox() {
		document.objectForm.boxId.value = '';
		document.objectForm.id.value= ''; 
		document.objectForm.name.value= ''; 
		document.objectForm.unit.value= '';
		document.objectForm.quantity.value= '';
	};

</script>
<script type="text/javascript" src="js/cmxforms.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#cmxform").validate();
});
</script>
<body>
	<jsp:include page="/admin/product/listProductForBox.jsp"></jsp:include>
	
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png" /> </a>
			</div>
		</div>

		<h2>拆箱設定</h2>
		<div id="main">

			

			<s:form id="cmxform" name="objectForm" action="updateProductBox" namespace="/admin" theme="simple">
				<fieldset>
				<div id="r1">
					
					<input type="hidden" name="productId" value="${product.id}">	
					<input type="hidden" name="boxId" value="${product.productBox.box.id}">					
					
					<p>
						<label id="add1">分類:</label>
						${product.category.name}						
					</p>
					
					<p>
						<label id="add1">名稱:</label>
						${product.name}
					</p>
					
					<p>
						<label id="add1">轉換數量:</label>
						<input name="quantity" value="${product.productBox.quantity}" size="20" maxlength="20" class="digits"/>
					</p>
					
					<p>
						<label id="add1">單位:</label>
						${product.unit}
					</p>

				</div>
				
				</fieldset>
				
				<fieldset id="gridwrapper">
					<h2>拆解商品</h2>
					<p>
						<label id="add1">商品名稱:</label>
						<input name="name" value="${product.productBox.box.name}" size="20" maxlength="20" readonly="readonly" />
					</p>
					<p>
						<label id="add1">商品數量:</label>
						1
					</p>
					<p>
						<label id="add1">商品單位:</label>
						<input name="unit" value="${product.productBox.box.unit}" size="2" maxlength="2" readonly="readonly" />
					</p>
	
				</fieldset>
				
				<fieldset id="bads">
					<p align="center">
						<button type="button" onclick="listProductOpen();">
							<img src="images/add.png" width="35" height="35" />
							<h6>選擇商品</h6>
						</button>
						<button type="button" onclick="removebox();">
							<img src="images/del.png" width="35" height="35" />
							<h6>移除設定</h6>
						</button>
						<button type="submit">
							<img src="images/save.png" width="35" height="35" />
							<h6>確認</h6>
						</button>
						<button type="button"
							onclick="self.location.href='admin/stock.jsp'">
							<img src="images/quit.png" width="35" height="35" />
							<h6>退出</h6>
						</button>
					</p>
			</fieldset>
					</s:form>	
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
