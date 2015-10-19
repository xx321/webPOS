<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title>採購單</title>
<script type="text/javascript">
window.onload = start;
	function start() {
		var b = '${intoGoods.totalAmount}';
		var z = '${intoGoods.total}';
		var a = eval(b + z);
        document.getElementById('demo').innerHTML = a;
	
	}
</script>
</head>

<body>
<table width="840" style="padding:0 0 10px 0" align="center" cellspacing="0" cellpadding="0" >

  <tr>
    <td width="29"></td>
    <td colspan="2" ></td>
    <td width="127"></td>
    <td width="82"></td>
    <td width="37"><div id="demo">1</div></td>
 	<td> <input type="button" value="RUN" onclick="run();"> </td>
   
  </tr>
  <tr>
    <td width="29"></td>
    <td colspan="4"><img width="279" height="154" src="images/clip.jpg"></td>
    <td></td>
   	<td></td>
   
    <td align="right"><table cellspacing="0" cellpadding="0">
       
        <tr>
        <td width="175" align="right" style="font-size:24px; font-family:'標楷體'; font-weight:bold; border-bottom:1px solid #000;">採購收貨請款單</td>
        </tr>
        <tr>
         
       <td align="right" width="175" style="font-family:'標楷體'; padding:10px 0 0 0;" >日期:<fmt:formatDate value="${intoGoods.createTime}" pattern="yyyy/MM/dd"/></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td></td>
    <td colspan="4" ></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr><td><br/></td></tr>
  <tr >
    <td></td>
    <td width="85">廠商名稱：</td>
    <td>(${intoGoods.totalAmount} - ${intoGoods.total})</td>
    <td></td>
    <td></td>
    <td></td>
    <td width="85">單據號碼：</td>
    <td>${intoGoods.id}</td>
  </tr>
  <tr >
    <td></td>
    <td>聯絡電話：</td>
    <td>${intoGoods.supplier.phone}</td>
    <td></td>
    <td></td>
    <td></td>
    
    <td>請款金額：</td>
    <td>${intoGoods.totalAmount}</td>
  </tr>
  <tr>
    <td></td>
    <td>廠商統編：</td>
    <td>${intoGoods.supplier.companyCode}</td>
    <td></td>
    <td></td>
    <td></td>
    
    <td >稅　　額：</td>
    <td>${intoGoods.salesTax}</td>
  </tr>
  <tr >
    <td></td>
    <td>認&nbsp;&nbsp;帳&nbsp;&nbsp;日：</td>
    <td colspan="2"><fmt:formatDate value="${intoGoods.createTime}" pattern="yyyy/MM/dd"/></td>
    <td></td>
    <td></td>
    
    <td>幣　　別：</td>
    <td>新台幣</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td colspan="2"></td>
    <td></td>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
</table>
<table   align="center" cellspacing="0" border="1" style="border:#000000 1px solid;" rules="cols" width="800" cellpadding="0">

  <tr style="border:#000 1px solid; background:#DADADA;">
    <td  align="center" width="94">品號</td>
    <td align="center" width="231">品　名　‧　規　 格</td>
    <td align="center" width="101">訂購數</td>  
    <td align="center" width="61">單位</td>
    <td align="center" width="78">單價</td>
    <td align="center" width="87">未稅金額</td>
   
  </tr>
  <tr>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    
  </tr>
 <c:forEach items="${intoGoods.goodsItems}" var="s">
  <tr>
    <td>${s.id}</td>
    <td>${s.product.name}</td>
    <td align="right">${s.quantity}</td>
    <td align="center">${s.product.unit}</td>
    <td align="right">${s.purchasePrice}</td>
    <td align="right">${s.total}</td>
  </tr>
 </c:forEach>
  <tr height="280">
  
    <td></td>
	<td ></td>
	<td ></td>
	<td ></td>
    <td ></td>
    <td ></td>
  </tr>
  
</table>

<table align="center" style="padding:10px 0 0 0;" cellspacing="0" cellpadding="0">
  <col width="29">
  <col width="118">
  <col width="130">
  <col width="127">
  <col width="82">
  <col width="37">
  <col width="89">
  <col width="46">
  <col width="101">
  <tr>
    <td width="60"></td>
   
    <td width="46"></td>
    <td width="101"></td>
  </tr>
 <tr height="10"></tr>
  <tr>
    <td></td>
    
    <td colspan="6" style="font-family:'標楷體';">　　　　　　審  核：　　　　　　　　　　　 　　　     建  立：</td>
    <td></td>
    <td></td>
  </tr>

</table>
</body>
</html>
