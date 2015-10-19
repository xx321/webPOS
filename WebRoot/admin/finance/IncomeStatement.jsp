<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    
<title>損益表</title>
<style type="text/css">
body{
}
#wrapper{
	margin:auto;
	width:850px;
	height:950px;
}
.list{
	padding:0 0 0 75px;
}
.list2{
	padding:0 0 0 75px;
	font-weight:bold;
}


.cssbutton {
	font-size:8px;
	font-family:Arial;
	font-weight:normal;
	-moz-border-radius:7px;
	-webkit-border-radius:7px;
	border-radius:7px;
	border:1px solid #dcdcdc;
	padding:4px 9px;
	text-decoration:none;
	background:-webkit-gradient( linear, left top, left bottom, color-stop(5%, #f9f9f9), color-stop(100%, #e9e9e9) );
	background:-moz-linear-gradient( center top, #f9f9f9 5%, #e9e9e9 100% );
	background:-ms-linear-gradient( top, #f9f9f9 5%, #e9e9e9 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9', endColorstr='#e9e9e9');
	background-color:#f9f9f9;
	color:#666666;
	display:inline-block;
	text-shadow:0px 1px 0px #ffffff;
 	-webkit-box-shadow:inset 1px 1px 0px 0px #ffffff;
 	-moz-box-shadow:inset 1px 1px 0px 0px #ffffff;
 	box-shadow:inset 1px 1px 0px 0px #ffffff;
}.cssbutton:hover {
	background:-webkit-gradient( linear, left top, left bottom, color-stop(5%, #e9e9e9), color-stop(100%, #f9f9f9) );
	background:-moz-linear-gradient( center top, #e9e9e9 5%, #f9f9f9 100% );
	background:-ms-linear-gradient( top, #e9e9e9 5%, #f9f9f9 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#e9e9e9', endColorstr='#f9f9f9');
	background-color:#e9e9e9;
}.cssbutton:active {
	position:relative;
	top:1px;
}


</style>

<script type="text/javascript" src="JSCal2-1.9/src/js/jscal2.js"></script>
<script type="text/javascript" src="JSCal2-1.9/src/js/lang/b5.js"></script>
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/jscal2.css" />
<link type="text/css" rel="stylesheet"
	href="JSCal2-1.9/src/css/border-radius.css" />
	
</head>
<script type="text/javascript" charset="utf-8">

	function _search() {
		document.findIncomeStatement.submit();
	};
	
	function clearSearch() {	
		document.findIncomeStatement.createtimeStart.value = '';
		document.findIncomeStatement.createtimeEnd.value = '';
		document.findIncomeStatement.submit();
	};
	
</script>	
<body>
<div id="wrapper">
 
    <p align="center" style="font-size:22px; color:#009; font-weight:bold;">汶洋企業社</p>
   

    <p align="center" style="font-size:22px; font-weight:bold;">損益表</p>
<s:form name="findIncomeStatement" action="findIncomeStatement" namespace="/admin" theme="simple">
   結帳日期 :
<input type="text" id="createtimeStart" name="createtimeStart" value="${createtimeStart}" size="10" readonly />
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
<input type="text" id="createtimeEnd" name="createtimeEnd" value="${createtimeEnd}" size="10" readonly />
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
					<a href="javascript:void(0)" class="cssbutton" onclick="_search()">查詢</a>
					<a href="javascript:void(0)" class="cssbutton" onclick="clearSearch()">清空</a>
					<a href="javascript:void(0)" class="cssbutton" onclick="self.location.href='admin/backstage/financial_management.jsp'">退出</a>
</s:form>
<table border="1" style="margin:10px 0 0 0;" cellspacing="0" cellpadding="0" width="850">
  <tr>
    <td style="background:#F3F3F3;" width="30%" align="center" rowspan="2"><br>
      項　　　目 </td>
    <td style="background:#F3F3F3;" colspan="4"><p align="center">本　　期 </p></td>
  </tr>
  <tr style="background:#F3F3F3;">
 
    
    <td width="35%" ><p align="center">合　計 </p></td>
    <td ><p align="center">％ </p></td>
  
  </tr>
 
    
    <tr style="background:#B7DBFF;">
    <td align="left" style="border-right:0px;">營業收入</td>
    <td style="border-right:0px; border-left:0px;"></td>
    <td style=" border-left:0px;"></td>
    </tr>
    
    <tr>
    <td class="list">發票收入</td>
    <td align="right">${incomeVO.invoicesIncome}</td>
    <td></td>
    
    </tr>
    
    <tr>
    <td class="list">現金收入</td>
    <td align="right">${incomeVO.cashIncome}</td>
    <td></td>
    </tr>
    
    <tr style="background:#FFFFBD;">
    <td class="list2">營業收入總計</td>
    <td align="right">${incomeVO.totalIncome}</td>
    <td></td>
    </tr>
    
     <tr style="background:#B7DBFF;">
    <td align="left" style="border-right:0px;">營業成本</td>
    <td style="border-right:0px; border-left:0px;"></td>
    <td style=" border-left:0px;"></td>
    </tr>
    
	<c:forEach items="${costVOs}" var="c">
	  <tr>
	    <td class="list">${c.name}</td>
	    <td align="right">${c.spending}</td>
	    <td></td>
	  </tr>
	</c:forEach>

    
    <tr style="background:#FFFFBD;">
    <td class="list2">營業成本總計</td>
    <td align="right">${costTotal}</td>
    <td></td>
    </tr>
    
    <tr style="background:#FFFFBD;">
    <td class="list2">營業毛利</td>
    <td align="right"> ${grossProfit}</td>
    <td></td>
    </tr>
    
    <tr style="background:#B7DBFF;">
    <td align="left" style="border-right:0px;">營業費用</td>
    <td style="border-right:0px; border-left:0px;"></td>
    <td style=" border-left:0px;"></td>
    </tr>

	<c:forEach items="${feeVOs}" var="f">
	  <tr>
	    <td class="list">${f.name}</td>
	    <td align="right">${f.spending}</td>
	    <td></td>
	  </tr>
	</c:forEach>
    
    <tr>
    <td class="list">營業費用總計</td>
    <td align="right">${feeTotal}</td>
    <td></td>
    </tr>
    
    <tr style="background:#FFFFBD;">
    <td class="list2">營業淨利</td>
    <td align="right">${operatingIncome}</td>
    <td></td>
    </tr>
    
    <tr style="background:#FFFFBD;">
    <td class="list2">稅前(損)益</td>
    <td align="right">${incomeBfTaxes}</td>
    <td></td>
    </tr>

</table>
<table border="0" cellspacing="0" cellpadding="0" style="margin:30px 0 0 0;">
  <tr>
    <td width="251"><p>負責人： </p></td>
    <td width="251"><p>經理人： </p></td>
    <td width="251"><p>主辦會計： </p></td>
  </tr>
</table>
</div>
</body>
</html>
