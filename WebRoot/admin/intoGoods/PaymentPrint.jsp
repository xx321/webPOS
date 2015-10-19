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
    
 <title>付款單</title>
<style type="text/css">
#wrapper{
	margin:0 auto;
	width:1000px;
}
.bodtom{
	border-bottom:#000 1px solid;
}
.trborder{
	border-top:#000000 1px solid;
	border-bottom:#000000 1px solid;
	border-left:#000000 1px solid;
	}
.trborder2{
	border-top:#000000 1px solid;
	border-bottom:#000000 1px solid;
	border-right:#000000 1px solid;
	}
.trb{
	border-top:#000000 1px solid;
	border-bottom:#000000 1px solid;
}
</style>
</head>

<body>
<div id="wrapper">
<table width="1000" cellspacing="0" cellpadding="0">
  <col width="66" />
  <col width="64" />
  <col width="103" />
  <col width="86" />
  <col width="58" />
  <col width="31" />
  <col width="70" />
  <col width="65" />
  <col width="68" />
  <col width="60" />
  <col width="86" />
  <col width="66" span="3" />
  <tr>
    <td colspan="13" align="center" style="font-size:22px; color:#009; font-weight:bold;">汶洋企業社</td>
    <td width="90"></td>
  </tr>
  <tr>
    <td colspan="13" align="center" style="font-size:22px; font-weight:bold;">應付帳款明細對帳單</td>
    <td></td>
  </tr>
  <tr height="25">
    <td colspan="3">廠商:${intoGoods.supplier.name}</td>
    <td width="111"></td>
    <td width="57"></td>
    <td width="56"></td>
    <td width="32"></td>
    <td width="119"></td>
    <td></td>
    <td width="86"></td>
    <td colspan="2">日期:<fmt:formatDate value="${intoGoods.createTime}" pattern="yyyy/MM/dd"/></td>
    <td width="81"></td>
    <td width="76"></td>
    <td></td>
  </tr>

  <tr height="25">
    <td colspan="2">聯絡人:${intoGoods.supplier.contact}</td>
    <td width="143"></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td colspan="2">電話:${intoGoods.supplier.phone}</td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  
  <tr height="25">
  	<td width="251">統一編號:${intoGoods.supplier.companyCode}</td>
    <td width="76"></td>
    <td width="76"></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td width="106">傳真:${intoGoods.supplier.fax}</td>
    <td width="89"></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr height="25">
    <td width="251">發票編號:${intoGoods.invoiceNumber}</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td colspan="2">付款條件:匯款</td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr height="10">
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
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
<table width="1000" cellspacing="0" cellpadding="0">
  <col width="66" />
  <col width="64" />
  <col width="103" />
  <col width="86" />
  <col width="58" />
  <col width="31" />
  <col width="70" />
  <col width="65" />
  <col width="68" />
  <col width="60" />
  <col width="86" />
  <col width="66" span="3" />
  <tr style="background:#C8FFC8; color:#03F; font-weight:bold;" align="center">
    
    <td class="trb" width="81">類別</td>
    <td class="trb" width="152">單號/品號</td>
    <td class="trb" width="118">商      品</td>
    <td class="trb" width="76">數     量</td>
    <td  class="trb" width="41">單位</td>
    <td class="trb" width="93">單     價</td>
    <td class="trb" width="80">未稅金額</td>
    <td class="trb" width="91">稅額</td>
    <td align="right" class="trb" width="87">金　     額</td>
    
  </tr>
   <c:forEach items="${intoGoods.goodsItems}" var="s">
  <tr>
    
    <td class="bodtom">採購進貨</td>
    <td align="center"  class="bodtom">${s.id}</td>
    <td  class="bodtom">${s.product.name}</td>
    <td align="center" class="bodtom">${s.quantity}</td>
    <td align="center" class="bodtom">${s.product.unit}</td>
    <td align="center" class="bodtom">${s.purchasePrice}</td>
    <td class="bodtom" align="right">${s.total}</td>
    <td class="bodtom" align="center"></td>
    <td class="bodtom" align="right">${s.total}</td>
    
  </tr>
</c:forEach>
  <tr>
    <td></td>
    <td></td>
   
    <td class="bodtom"></td>
    <td class="bodtom"></td>
    <td class="bodtom" align="center"></td>
    <td class="bodtom" align="center"></td>
    <td class="bodtom" align="center"></td>
    <td class="bodtom" align="right"></td>
    <td class="bodtom" align="center">　</td>
    
    
  </tr>
 
  
  <tr>
    <td colspan="2">幣別:新台幣    小計</td>
    <td></td>
    <td></td>
    <td></td>
    <td>本期應付</td>
    <td class="bodtom" align="right">${intoGoods.total}</td>

    <td class="bodtom" align="center">${intoGoods.salesTax}</td>
    <td class="bodtom" align="right">${intoGoods.totalAmount}</td>
  
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>本期已付</td>
    <td class="bodtom" align="right">0 </td>
  
    <td>前期應付</td>
    <td class="bodtom" align="right">0 </td>
  
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>預付款</td>
    <td class="bodtom" align="right">0 </td>
   
    <td>應付餘額</td>
    <td class="bodtom" align="right">${intoGoods.totalAmount}</td>
  
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
    <td></td>
    
 
  </tr>
</table>
</div>
</body>
</html>
