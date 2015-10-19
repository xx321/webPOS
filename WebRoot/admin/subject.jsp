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

<title>科目維護</title>
<link href="css2/form.css" rel="stylesheet" type="text/css"/>
<link href="css2/layout.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css2/style.css" type="text/css"/>

<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery-1.8.3.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_TW.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="jslib/syUtil.js"></script>
</head>
<script type="text/javascript" charset="utf-8">

	var datagrid;
	$(function() {
	
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/admin/datagridSubject',
			title : '營業項目',
			iconCls : '',
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
			toolbar : '#toolbar'
		});
		
		_search = function() {
			datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
		};
		
		clearSearch = function() {
			datagrid.datagrid('load', {});
			$('#searchForm').find('input').val('');
		};
	});
	
	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if(rows.length > 1) {
			datagrid.datagrid('unselectAll');
			parent.sy.messagerAlert('提示', '只能擇選一項記錄進行修改 ! ', 'error');
		} else if (rows.length == 1) {
			document.editForm.id.value= rows[0].id; 
     		document.editForm.submit();
		} else {
			parent.sy.messagerAlert('提示', '請選擇要修改的記錄 ！ ', 'error');
		}
	};
	
	function removeSubject() {
	var rows = datagrid.datagrid('getSelections');
	var ids = [];
	if (rows.length > 0) {
		parent.sy.messagerConfirm('請確認', '您要刪除當前所選項目 ? ', function(r) {
			if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : '${pageContext.request.contextPath}/admin/deleteSubject',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(d) {
						datagrid.datagrid('load');
						datagrid.datagrid('unselectAll');
						$.messager.show({
							msg : d.msg,
							title : '提示'
						});
					}
				});
			}
		});
		} else {
			parent.sy.messagerAlert('提示', '請選擇要刪除的記錄 ！ ', 'error');
		}
	};
</script>
<body>
<s:form name="editForm" action="editSubject" namespace="/admin" >
	<input type=hidden name="id">
</s:form>
<div id="toolbar"style="height: 100px;">
	<form id="searchForm" >
		查詢項目 : <input id="name" name="name" />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="_search()">查詢</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearSearch()">清空</a>
	</form>
</div>

	<div class="center">
		<div id="header">
			<div>
				<img src="images/logo.png"  />
			</div>
			

		</div>


		<div id="main">
	
			<h2>科目維護</h2>
			<fieldset id="gridwrapper">
				<legend></legend>
				
					<table id="datagrid"></table>

			</fieldset>


			<p align="center">
				<button type="button"
					onclick="self.location.href='admin/subject/addSubject.jsp'">
					<h6>添加營業項目</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="edit();">
					<h6>编辑</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="removeSubject();">
					<h6>删除</h6>
					<p>請檢查後再按下按鈕。</p>
				</button>
				<button type="button" onclick="self.location.href='admin/backstage/financial_management.jsp'">
					<h6>退出</h6>
					<p>取消送出，回到選單。</p>
				</button>
			</p>
			
		</div>
		
		<div id="footer">&copy; ◎版權所有</div>

	</div>
</body>
</html>
