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

<title>報價單</title>
<style type="text/css">
#wrapper{
	width:700px;
	margin:0 auto;
}
</style>

</head>

<body>
<div id="wrapper">
<table align="center" width="650" cellspacing="0" cellpadding="0">

  <tr>
  
    <td colspan="4"><img width="279" height="154" src="images/clip.jpg"></td>
    <td></td>
   	<td></td>
   
    <td align="right"><table cellspacing="0" cellpadding="0">
       
        <tr>
          <td  width="130"  style="font-size:24px; font-family:'標楷體'; font-weight:bold;">報　價　單</td>
        </tr>
        <tr>
         
       <td  width="130" style="font-family:'標楷體'; padding:10px 0 0 10px;" >
			日期: <fmt:formatDate value="${sell.createTime}" pattern="yyyy/MM/dd"/>
       </td>
        </tr>
      </table></td>
<tr>
<td width="180"><h5 style=" font-family:'標楷體'; font-size:15px; padding:0 0 0 30px;">特爲下列客戶報價
</h5></td>
</tr>
</table>
<table style="font-family:'標楷體';" width="620" align="center" cellspacing="0" cellpadding="0">

  <tr>

  </tr>
  <tr>
  	
    <td width="90">公司名稱：</td>
    <td width="340">${sell.customer.name}</td>
    <td width="90">統一編號：</td>
    <td>${sell.customer.companyCode}</td>
  </tr>
  <tr>
    <td>姓&nbsp;&nbsp;&nbsp;&nbsp;名：         </td>
    <td>${sell.customer.contact}</td>
    <td>傳&nbsp;&nbsp;&nbsp;&nbsp;真：</td>
    <td>${sell.customer.fax}</td>
  </tr>
  <tr>
    <td>電&nbsp;&nbsp;&nbsp;&nbsp;話：</td>
    <td>${sell.customer.phone}</td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
  <tr>
    <td>地&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
    <td colspan="2">${sell.customer.address}</td>
    <td></td>
  </tr>
</table>
<hr style="width:625px; border:#000000 1px solid" />
<table align="center" border="2" cellspacing="0" cellpadding="0">

  <tr>
    <td width="199"></td>
    <td width="156"></td>
    <td width="94"></td>
    <td width="83"></td>
    <td width="85"></td>
  </tr>
  <tr style="background:#E0E0E0; font-family:'標楷體'; font-weight:bold;" align="center" >
    <td>品項</td>
    <td>規格</td>
    <td>數量</td>
    <td>單價</td>
    <td>金額</td>
  </tr>
 <c:forEach items="${sell.sellitems}" var="s">
  <tr>
    <td>${s.product.name}　</td>
	<td>${s.product.description}　</td>
    <td>${s.quantity}　</td>
    <td>${s.purchasePrice} </td>
    <td>${s.total}　</td>
  </tr>
 </c:forEach>
<s:if test="sell.sellitems.size() <10">
 
 <c:forEach var="i" begin="${sell.sellitems.size()}" end="9" step="1"> 
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
 </c:forEach>
 
</s:if>
  <tr>
    <td align="right" colspan="3">小計</td>
    <td align="right" colspan="2"> $       ${sell.total} </td>
  </tr>
  <tr>
    <td align="right" colspan="3">營業稅</td>
    <td align="right" colspan="2"> $       ${sell.salesTax} </td>
  </tr>
  <tr>
    <td align="right" style="font-weight:bold;" colspan="3">總計</td>
    <td align="right" colspan="2"> $       ${sell.totalAmount} </td>
  </tr>
</table>
<br />
<table align="center" cellspacing="0" cellpadding="0">

  <tr>
    <td colspan="4" width="532" style="font-family:'標楷體'; font-size:20px;">付款方式：□現金      □匯款   □支票（票期    天）</td>
    <td width="85"></td>
  </tr>
  <tr><td><br /></td></tr>
  <tr>
    <td style="font-family:'標楷體'; font-size:20px;">預定交貨日期：_______________________</td>
 
  </tr>
 	<tr><td><br /></td></tr>
  <tr>
    <td style="font-family:'標楷體'; font-size:15px; font-weight:bold;">特別注意事項：</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  
  <tr>
    <td  style="font-size:10px;" colspan="5" dir="LTR" width="617">1.本報價單價格有效期限15天，如有變更設計，材質價格另議。<br>
      2.本報價單不含設計費用，如蒙貴公司同意試同買賣訂購單，金額無議後請簽章。<br>
      3.貴公司所提供製作任何圖，照片，文字，如涉及智慧財產權或版權等法律事項，貴公司自行負責，概與本中心無關。<br>
      4.貴公司下訂單後，製作圖中如有更改或因故停止製作，本中心依已製作部份收取製作費用。<br>
      5.若開二聯式或三聯式發票需加附營業稅（5%）；若開一般收據可不收取營業稅。<br>
      <br></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
 
</table>
<table align="center" border="2" style="border:#000000 solid 2px;" cellspacing="0" cellpadding="0">

  <tr>
    <td align="center" colspan="2" width="355">業務承辦人</td>
    <td align="center" colspan="3" width="262">客戶確認簽名（日期）</td>
  </tr>
  <tr>
    <td height="70" colspan="2">　</td>
    <td height="70" colspan="3" align="right">年         月       日</td>
  </tr>
</table>

</div>
</body>
</html>
