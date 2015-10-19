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

<title>銷貨單</title>

<script type="text/javascript">
/*
window.onload = function(){
	window.print();
};
*/
</script>

</head>

<body>

<table width="840" style="padding:0 0 10px 0" align="center" cellspacing="0" cellpadding="0" >

  <tr>
    <td width="29"></td>
    <td colspan="2" width="248" ></td>
    <td width="127"></td>
    <td width="82"></td>
    <td width="37"></td>
 	<td></td>
  
  </tr>
  <tr>
    <td width="29"></td>
    <td colspan="4"><img width="279" height="154" src="images/clip.jpg"></td>
    <td></td>
   	<td></td>
   
    <td align="right"><table cellspacing="0" cellpadding="0">
       
        <tr>
          <td  width="130"  style="font-size:24px; font-family:'標楷體'; font-weight:bold;">銷　貨　單</td>
        </tr>
        <tr>
         
       <td  width="130" style="font-family:'標楷體'; padding:10px 0 0 10px;" >
      		 日期: <fmt:formatDate value="${sell.createTime}" pattern="yyyy/MM/dd"/>
       </td>
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
  <tr><td><br /></td></tr>
  <tr >
    <td></td>
    <td width="85">客戶名稱：</td>
    <td >${sell.customer.name}</td>
    <td></td>
    <td></td>
    <td></td>
    <td width="85">單據號碼：</td>
    <td>${sell.id}</td>
  </tr>
  <tr > 
    <td></td>
    <td>客戶電話：</td>
    <td>${sell.customer.phone}</td>
    <td></td>
    <td></td>
    <td></td>
    
    <td>客戶聯絡：</td>
    <td>${sell.customer.contact}</td>
  </tr>
  <tr >
    <td></td>
    <td>統一編號：</td>
    <td>${sell.customer.companyCode}</td>
    <td></td>
    <td></td>
    <td></td>
    
    <td >出貨倉庫：</td>
    <td>門市</td>
  </tr>
  <tr>
    <td></td>
    <td>發票住址：</td>
    <td colspan="2">${sell.customer.address}</td>
    <td></td>
    <td></td>
    
    <td>業務人員：</td>
    <td>${sell.user.username}</td>
  </tr>
  <tr>
    <td></td>
    <td>送貨住址：</td>
    <td colspan="2">${sell.delivery}</td>
    <td></td>
    <td></td>
    <td>發票號碼：</td>
    <td>${sell.invoiceNumber}</td>
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
<table   align="center" cellspacing="0" border="2" width="800" cellpadding="0">

  <tr style="background:#DADADA;">
    <td align="center" width="29">NO.</td>
    <td align="center" width="118">編    　號</td>
    <td align="center" width="130">品　名　‧　規　 格</td>  
    <td align="center" width="82">數　量</td>
    <td align="center" width="37">單位</td>
    <td align="center" width="89">單　　價</td>
    <td align="center" width="101">金　　額</td>
  </tr>
  <tr>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
    <td>　</td>
  </tr>
 <c:forEach items="${sell.sellitems}" var="s">
  <tr>
    <td>　</td>
    <td>${s.id}　</td>
    <td>${s.product.name}　</td>
   
    <td>${s.quantity}　</td>
    <td>${s.product.unit}　</td>
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
     <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
 </c:forEach>
 
</s:if>
  <tr>
    <td>　</td>
    <td colspan="4">付款方式：月結30天</td>
    
    <td>合　　計：</td>
    <td align="right">${sell.total}</td>
  </tr>
  <tr>
    <td>　</td>
    <td colspan="4">備　　註：</td>
   
    <td>營  業  稅：</td>
    <td align="right">${sell.salesTax}</td>
  </tr>
  <tr >
    <td>　</td>
    <td   colspan="4">總計新台幣：${chineseAmount}</td>

    <td>總　　計：</td>
    <td align="right">${sell.totalAmount}</td>
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
    <td style="font-family:'標楷體';" colspan="6" width="700">貨款未付清或票據未兌現之前，本公司仍保有貨品所有權，並得不經法律程序取回。</td>
    <td width="46"></td>
    <td width="101"></td>
  </tr>
 <tr height="10"></tr>
  <tr>
    <td></td>
    
    <td style="font-family:'標楷體';" colspan="6">核  准：　　　　 　業     務：　 　　　　倉 庫：　　　　 　製 單：　　　 　　     簽  收：</td>
    <td></td>
    <td></td>
  </tr>

</table>

</body>
</html>
