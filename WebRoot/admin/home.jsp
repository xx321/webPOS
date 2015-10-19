<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>POS系統主頁面</title>
<link href="css/form.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="http://platform.twitter.com/widgets.js"></script>

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
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript" src="js/jquery.pagination2.js"></script>
<script type="text/javascript" src="js/xhdata.js"></script>
<script type="text/javascript" src="js/xhdata2.js"></script>
<script type="text/javascript" src="js/OccultReader.js"></script>

</head>
<script type="text/javascript">
	var newnumber = true;
	var datagrid;
	$(function() {

		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/tradeItemDataGrid',
			title : '',
			iconCls : 'icon-save',
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			singleSelect : true,
			columns : [ [ //{title : '編號',field : 'productId'},
			{
				title : '名稱',
				field : 'productName',
				width : 100,
			}, {
				title : '數量',
				field : 'quantity',
				width : 80,
			}, {
				title : '單位',
				field : 'unit',
				width : 50
			}, {
				title : '價格',
				field : 'purchasePrice',
				width : 80,
			}, {
				title : '小計',
				field : 'total',
				width : 50
			} ] ],
			toolbar : '#toolbar',
			showFooter : true
		});

		var message = '${message}';
		if (message != "") {
			$.messager.show({
				msg : message,
				title : '提示'
			});
		};
		
		OccultReader.setControl("isbn_entry",
		        { onchange_handler: submitByBarcode }
		        );

	});
	
	function add() {
		var msg = "是否完成交易? \n\n 請確認 !";
		if (confirm(msg) == true) {
			addTrade();
			return true;
		} else {
			return false;
		}
	};
	
	function addInvoice() {
		var msg = "是否完成交易? \n\n 請確認 !";
		if (confirm(msg) == true) {
			addInvoiceTrade();
			return true;
		} else {
			return false;
		}
	};
	
	//前台無刷新，完成前台交易(現金結帳)。
	function addTrade() {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addTrade',
			data : {
				totalAmount : document.money.totalAmount.value,
				totalItem : document.money.totalItem.value,
				type : 0
			},
			dataType : 'json',
			success : function(d) {
				if (d && d.success) {
					removeAllItem();
				} else {			
				}			
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		});
	}
	
	//前台無刷新，完成前台交易(發票結帳)。
	function addInvoiceTrade() {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/addTrade',
			data : {
				totalAmount : document.money.totalAmount.value,
				totalItem : document.money.totalItem.value,
				type : 1,
				companyCode : document.money.invoice.value
			},
			dataType : 'json',
			success : function(d) {
				if (d && d.success) {
					removeAllItem();
				} else {			
				}			
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		});
	}
	
	//開啟編輯商品數量的計算機
	function edititemQuantity() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 1) {
			datagrid.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項商品 ! ', 'error');
		} else if (rows.length == 1) {
			$('#home_quantityDialog').dialog('open');
			document.loadTradeItem.productId.value = rows[0].productId;
			document.loadTradeItem.quantity.value = '';
		} else {
			parent.sy.messagerAlert('提示', '請選擇商品 ！ ', 'error');
		}
	};
	
	//一維條碼方式，編輯商品數量。
	function submitByBarcode(barcodeNumber) {
		document.loadTradeItem.quantity.value = '+1';
		document.loadTradeItem.barcodeNumber.value = barcodeNumber;
		submitLoadTradeItem();
	}
	
	//前後台分離，編輯商品數量(選單編輯)。
	function submitByMenu(productId) {
		document.loadTradeItem.productId.value = productId;
		document.loadTradeItem.quantity.value = '+1';
		submitLoadTradeItem();
	}
	
	//前後台分離，編輯商品數量。
	function submitLoadTradeItem() {
		
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/loadTradeItem',
			data : {
				productId : document.loadTradeItem.productId.value,
				quantity : document.loadTradeItem.quantity.value,
				barcodeNumber : document.loadTradeItem.barcodeNumber.value
			},
			dataType : 'json',
			success : function(d) {
				if (d && d.success) {
					var rows = datagrid.datagrid('getRows');
					var rowIndex = undefined;
					for (var i=0; i<rows.length; i++) {
						if (rows[i].productId == d.obj.productId)
							rowIndex = i;
					}
					if (rowIndex == undefined) {
						datagrid.datagrid('appendRow', {
							productId : d.obj.productId,
							productName : d.obj.productName,								
							quantity : d.obj.quantity,
							unit : d.obj.unit,
							purchasePrice : d.obj.purchasePrice,
							total : d.obj.total
						});	
						rowIndex = rows.length-1;
					} else {
						datagrid.datagrid('updateRow', {
							index : rowIndex,
							row : {
								quantity : d.obj.quantity,
								total : d.obj.total
							}
						});		
					}
					datagrid.datagrid('acceptChanges');
				} else {
					$.messager.show({
						msg : d.msg,
						title : '提示'
					});
					datagrid.datagrid('rejectChanges');
				}
				datagrid.datagrid('selectRow',rowIndex);
				loadTotal();
				
				$('#loadTradeItem').find('input').val('');
				$('#home_quantityDialog').dialog('close');
				
			}
		});
	};
	
	//前後台分離，刪除所選商品項目。
	function removeitem() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
				for ( var i = 0; i < rows.length; i++) {
					$('#datagrid').datagrid('deleteRow', $('#datagrid').datagrid('getRowIndex', rows[i]));  
					ids.push(rows[i].productId);
				}
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/deleteTradeItem',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(d) {
						if (d && d.success) {
							datagrid.datagrid('acceptChanges');
						} else {
							datagrid.datagrid('rejectChanges');
						}
						datagrid.datagrid('unselectAll');
						loadTotal();
					}
				});
		} else {
		}
	};
	
	//前後台分離，刪除所有選中商品項目。
	function removeAllItem() {
		var rows = datagrid.datagrid('getRows');
		var ids = [];
		if (rows) {
				for ( var i = rows.length-1; i >= 0; i--) {
					ids.push(rows[i].productId);
					$('#datagrid').datagrid('deleteRow', $('#datagrid').datagrid('getRowIndex', rows[i]));  
				}
				$('#datagrid'). datagrid('loadData', { total: 0, rows: [] });
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/deleteTradeItem',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(d) {
						if (d && d.success) {
							datagrid.datagrid('acceptChanges');
						} else {
							datagrid.datagrid('rejectChanges');
						}
						datagrid.datagrid('unselectAll');
						document.money.invoice.value = '';
						loadTotal();
					}
				});
		} else {
		}
	};
	
	//前台無刷新，重新載入表格總計數據。
	function loadTotal() {
		var rows = datagrid.datagrid('getRows');
		var totalAmount = 0;
		var totalItem = 0;
		for ( var i = 0; i < rows.length; i++) {
			totalAmount = eval(totalAmount + rows[i].total);
			totalItem = eval(totalItem + rows[i].quantity);
		}
		document.money.totalAmount.value = Math.round(totalAmount);
		document.money.totalItem.value = totalItem;
		document.money.pocket.value = '';
	};
	
	//前台無刷新，重新載入商品息訊。
	function loadProducts(categoryId) {
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/loadProducts',
			data : {
				categoryId : categoryId
			},
			dataType : 'json',
			success : function(d) {
				if (d && d.success) {
					 $('#model').html(d.msg);	
					 reloadJqviewb();
				} else {
				}
			}
		});
	};
	
	//前台無刷新，重新載入商品分頁息訊。
	function reloadJqviewb() {	
		var jsonData = eval(json);
		$.each(jsonData,function(i,n){
			$("#Searchresult").append("<li></li>");  
		});
		var num_entries = $("#Searchresult li").length;	
		var showCount = 12;
		var initjqviewb = function() {
			
			// 创建分页
			$("#jqviewb").jqviewb(num_entries, {
				num_edge_entries: 1, //边缘页数
				num_display_entries: 6, //主体页数
				callback: pageselectCallback,
				items_per_page:showCount //每页显示1项
			});
		 }();	 
		function pageselectCallback(page_index, jq){
			var max_elem = Math.min((page_index+1) *showCount, num_entries);		
			$("#htcList").html("");		
			for(var i=page_index*showCount;i<max_elem;i++){
				var new_content = $("#Searchresult li:eq("+i+")").clone();
				$("#htcList").append(new_content); //装载对应分页的内容
			}
			return false;
		}
	};
</script>

<!--右上角 ， 時鐘功能 。 begin -->
<script type="text/javascript">
	window.onload = startTime;
	function startTime() {
		var today = new Date();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		// add a zero in front of numbers<10
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
		t = setTimeout('startTime()', 500);
	}

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
</script>
<!--右上角 ， 時鐘功能 。 end -->

<body>
	<!-- 輸入統一編號 begin -->
	<jsp:include page="/admin/home/company_codeDialog.jsp"></jsp:include>
	<!-- 輸入統一編號 end -->

	<!-- 編輯商品數量 begin -->
	<jsp:include page="/admin/home/home_quantityDialog.jsp"></jsp:include>
	<!-- 編輯商品數量 end -->

	<div id="toolbar" style="height: 40px;">
		<h3>購買商品</h3>
	</div>
	<!-- wrapper大容器 -->
	<div id="wrapper">

		<div id="header"></div>
		<!-- main主題內容 -->
		<div id="main">

			<div id="logo1">
				<img src="images/logo.png" align="left" />
				<div id="time"></div>
			</div>

			<!-- menu選單 -->
			<c:choose>
				<c:when test="${user.purview==1}">
					<jsp:include page="/admin/home/managerMenu.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="/admin/home/operatorMenu.jsp"></jsp:include>
				</c:otherwise>
			</c:choose>
			<!-- menu選單end -->
			<!-- content內容 -->
			<div id="content">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td>
							<!--購買商品明細-->
							<div id="Show">
								<table id="datagrid"></table>

							</div> <!--購買商品明細END--> <!--結帳顯現-->
							<div class="money">
								<form name="money">
									<ul>
										<li>小計 <input type="text" name="totalAmount" id="e1"
											value="0" readonly></li>
										<li>總件數 <input type="text" name="totalItem" id="e1" value="0"
											readonly></li>
										<li>找零 <input type="text" name="pocket" id="e1" readonly>
										</li>
										<li>統編 <input type="text" name="invoice" id="e1" maxlength="10" readonly>
										</li>
									</ul>
								</form>
							</div> <!--結帳顯現END-->
							<div id="top">
								<h1>商品類別</h1>

							</div>
							<div id="topco">
								<h1>商品資訊</h1>
							</div></td>
						<td>
							<!--現金結帳按鈕-->
							<div class="butt">
								<a href="javascript:void(0)" onclick="Quantity()">
									<button>
										<img src="images/invoice.png" width="30" height="30" />
										<p>統編</p>
									</button>
								</a>
								<br/>
								<a href="javascript:void(0)" onclick="edititemQuantity()">
									<button>
										<img src="images/edit.png" width="30" height="30" />
										<p>編輯數量</p>
									</button>
								</a>

								<br/>
								<a href="javascript:void(0)" onclick="removeAllItem()">
									<button>
										<img src="images/clearall.png" width="30" height="30" />
										<p>清除所有</p>
									</button>
								</a>
								<br/>
								<a href="javascript:void(0)" onclick="javascript:return addInvoice()">
									<button>
										<img src="images/print.png" width="30" height="30" />
										<p>發票結帳</p>
									</button>
								</a>
								<br/>
								<a href="javascript:void(0)" onclick="javascript:return add()">
									<button>
										<img src="images/money.png" width="30" height="30" />
										<p>現金結帳</p>
									</button>
								</a>

							</div> <!-- 計算機功能 begin --> <jsp:include
								page="/admin/home/caformDialog.jsp"></jsp:include> <!-- 計算機功能 begin -->
						</td>
					</tr>
				</table>
			</div>
			<!-- content內容end -->

			<!-- Category類別顯現 -->
			<div id="Category">
				<div id="mode">
					
					<ul id="hList">
					</ul>
					<ul id="Sresult" style="display:none;">

						<c:forEach items="${categorys}" var="c">

							<li><a href="javascript:void(0)" onclick="loadProducts(${c.id})"
								class="css_category">${c.name}</a></li>

						</c:forEach>
					</ul>
				</div>

				<div id="jqview" class="jqview"></div>
			</div>

			<!-- Category類別顯現end -->
			<!-- 商品顯現 -->
			<div id="Commodity">

				<div id="model">

					<ul id="htcList">
					</ul>

					<ul id="Searchresult" style="display:none;">

						<c:forEach items="${products}" var="p">
							<li><a class="css_commodity"
								href="javascript:void(0)" onclick="submitByMenu(${p.id})"> ${p.name} </a></li>
						</c:forEach>

					</ul>
				</div>

				<div id="jqviewb" class="jqviewb"></div>
			</div>

			<!-- 商品顯現end -->
		</div>
		<!-- main主題內容end -->
	</div>
	<!-- wrapper大容器end -->
	
	<input id="isbn_entry" type="text" />
	
</body>
</html>