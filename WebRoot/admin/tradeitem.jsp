<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<title>銷售統計</title>
<link href="css2/form.css" rel="stylesheet" type="text/css" />
<link href="css2/layout.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css2/style.css" type="text/css"/>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>

<script type="text/javascript" src="jslib/syUtil.js"></script>


<script type="text/javascript" src="JSCal2-1.9/src/js/jscal2.js"></script>
<script type="text/javascript" src="JSCal2-1.9/src/js/lang/b5.js"></script>
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/jscal2.css" />
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/border-radius.css" />


</head>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	
	$(function() {
		
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridTradeitem',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			singleSelect : true,
			sortName : 'categoryName',
			sortOrder : 'desc',
			columns : [ [ {
				title : '類別',
				field : 'categoryName',
				width : 100,
				sortable : true
			}, {
				title : '商品名稱',
				field : 'productName',
				width : 120,
				sortable : true
			}, {
				title : '銷售數量',
				field : 'quantity',
				width : 80,
				sortable : true
			}, {
				title : '銷售數量比重(百分比%)',
				field : 'itemScaleStr',
				width : 150,
				sortable : true
			}, {
				title : '銷售金額',
				field : 'total',
				width : 80,
				sortable : true
			}, {
				title : '銷售金額比重(百分比%)',
				field : 'amountScaleStr',
				width : 150,
				sortable : true
			} ] ],
			toolbar : '#toolbar'
		});
		
		datagrid.datagrid('load', sy.serializeObject($('#findCheckout')));
		
	});
	
	function _search() {
		//優化TradeitemAction 必須將findCheckout 改成 ajax 方式。
		//主要 CheckoutVO 以無刷傳回前台 ， 更新 至頁面上。 datagrid 直接load刷新 即可。
		document.findCheckout.submit();
	};
	
	function clearSearch() {	
		$('#findCheckout').find('input').val('');
		document.findCheckout.submit();
	};
	
	function findTradeSubmit() {
		document.findTrade.submit();
	}
	
	function exportExcel() {
		var url = "${pageContext.request.contextPath}/admin/exportExcelTradeitem?createtimeStart=${checkoutVO.createtimeStart}&createtimeEnd=${checkoutVO.createtimeEnd}&totalItem=${checkoutVO.totalItem}&totalAmount=${checkoutVO.totalAmount}";
		window.location.href = url;
	}
</script>
<body>
<s:form action="findTrade" namespace="/admin" >
	<input type=hidden name="createtimeStart" value="${checkoutVO.createtimeStart}">
	<input type=hidden name="createtimeEnd" value="${checkoutVO.createtimeEnd}">
</s:form>
	<div id="toolbar" style="height: 170px;">
		<s:form action="findCheckout" namespace="/admin">
			<input type=hidden name="totalItem" value="${checkoutVO.totalItem}">
			<input type=hidden name="totalAmount" value="${checkoutVO.totalAmount}">
結帳日期 :
<input type="text" id="createtimeStart" name="createtimeStart" value="${checkoutVO.createtimeStart}" size="10" readonly />
			<input type="button" value="" id="BTN1" name="BTN1" />
			<script type="text/javascript">
				new Calendar({
					inputField : "createtimeStart",
					dateFormat : "%Y/%m/%d",
					trigger : "BTN1",
					bottomBar : true,
					weekNumbers : false,
					onSelect : function() {
						this.hide();
					}
				});
			</script>
至
<input type="text" id="createtimeEnd" name="createtimeEnd" value="${checkoutVO.createtimeEnd}" size="10" readonly />
			<input type="button" value="" id="BTN2" name="BTN2" />
			<script type="text/javascript">
				new Calendar({
					inputField : "createtimeEnd",
					dateFormat : "%Y/%m/%d",
					trigger : "BTN2",
					bottomBar : true,
					weekNumbers : false,
					onSelect : function() {
						this.hide();
					}
				});
			</script>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="_search()">查詢</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearSearch()">清空</a>

		</s:form>

		<table id="tab">
			<tr>
				<th>操作人員</th>
				<td>${checkoutVO.user.username}</td>
				<th>查詢時間</th>
				<td><fmt:formatDate value="${checkoutVO.createTime}"
						pattern="yyyy年MM月dd日 HH:mm:ss" />
				</td>				
			</tr>
		</table>
		<br />
		<table id="tabResult">
			<tr>
				<th>現金交易</th>
				<td>${checkoutVO.cashCount}筆</td>
				<th>件數</th>
				<td>${checkoutVO.cashItem}件</td>
				<th>金額</th>
				<td>${checkoutVO.cashAmount}元</td>				
			</tr>
			<tr>
				<th>發票交易</th>
				<td>${checkoutVO.invoiceCount}筆</td>				
				<th>件數</th>
				<td>${checkoutVO.invoiceItem}件</td>				
				<th>金額</th>
				<td>${checkoutVO.invoiceAmount}元</td>							
			</tr>
			<tr>
				<th>交易總筆數</th>
				<td>${checkoutVO.totalCount}筆</td>				
				<th>共出售件數</th>
				<td>${checkoutVO.totalItem}件</td>				
				<th>交易總金額</th>
				<td>${checkoutVO.totalAmount}元</td>				
			</tr>
		</table>
	</div>
	<div class="center">
		<div id="header">
			<div>
				<a href="index.html"><img src="images/logo.png" /> </a>
			</div>


		</div>

		<h2>銷售統計</h2>
		<div id="main">

			
			<fieldset id="gridwrapper">
				<legend></legend>

				<table id="datagrid"></table>

			</fieldset>

			<p align="center">
				<button type="button" onclick="findTradeSubmit()">
					<h6>查看交易信息</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="exportExcel()">
					<h6>匯出報表</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="self.location.href='admin/index.jsp'">
					<h6>退出</h6>
					<p>取消送出，回到選單。</p>
				</button>

			</p>


		</div>

		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
