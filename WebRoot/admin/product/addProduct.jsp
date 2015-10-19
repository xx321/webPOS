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

<title>添加商品</title>

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


<script type="text/javascript" src="js/cmxforms.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#cmxform").validate();
});
</script>
</head>

<body>
	<!-- 輸入條碼編號 begin -->
	<jsp:include page="/admin/barcode/barcodeDialog.jsp"></jsp:include>
	<!-- 輸入條碼編號 end -->
	
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png"  /> </a>
			</div>
			

		</div>

		<h2>添加商品</h2>
		<div id="main">

			

			<s:form id="cmxform" action="addProduct" namespace="/admin" theme="simple">
				<fieldset>
					<legend></legend>
					
					<s:hidden name="product.id" />
					<s:hidden name="product.stockNumber" />
										
					<p>
						<label id="add1">分類:</label>
						<s:select name="product.category.id" list="#session.categorys" listKey="id" listValue="name" style="width:155px;"/>
					</p>
					
					<p>
						<label id="add1">名稱:</label>
						<s:textfield name="product.name" size="20" maxlength="20" />
					</p>
					<p>
						<label id="add1">單位:</label>
						<s:textfield name="product.unit" size="2" maxlength="2" />
					</p>
					<p>
						<label id="add1">商品類型:</label>
					</p>
					
					<s:radio name="product.type" list="#{'1':'庫存商品','0':'非庫存商品'}" value="1"/>
					
					<p>
						<label id="add1">價格:</label>
						<s:textfield name="product.price" size="20" maxlength="20" />
					</p>
					<p>
						<label id="add1">顯示順序:</label>
						<s:textfield name="product.displayOrder" class="required" size="2" maxlength="2" />
					</p>
					<p>
						<label id="add1">條碼編號:</label> 
						<s:textfield value="預設" name="product.barcodeNumber" size="20" maxlength="20" readonly="true" />
						<button type="button" onclick="openBarcodeDialog()">
							設定條碼編號
						</button>		
					</p>
					<p>
						<label id="add1">狀態:</label>
					</p>
					
					<s:radio name="product.status" list="#{'1':'開啟','0':'禁用'}" value="1"/>
					
					<p>
						<label id="add1">描述:</label>
						<s:textarea name="product.description" cols="50" rows="8" />
					</p>

					<!-- 後台數據校驗後，所輸出的對應欄位，錯誤信息 -->
					<s:property value="errors['product.name'][0]" /><br />
					<s:property value="errors['product.unit'][0]" /><br />					
					<s:property value="errors['product.price'][0]"/><br />
					<s:property value="errors['product.displayOrder'][0]"/><br />
					<s:property value="errors['product.description'][0]"/><br />

				</fieldset>

				<p align="center">
					<button type="submit">
						<h6>送出</h6>
						<p>請檢查後再按下按鈕。</p>
					</button>
					<button type="button" onclick="self.location.href='admin/product.jsp'">
						<h6>取消</h6>
						<p>取消送出，回到選單。</p>
					</button>
				</p>
			</s:form>
			
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
