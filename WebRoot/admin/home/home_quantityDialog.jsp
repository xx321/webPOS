<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 編輯商品數量 -->
<script type="text/javascript">

	function inputQuantity(digit) {		
		var quantity = document.loadTradeItem.quantity.value;
		
		if ((!newnumber) && (quantity != "0") && (quantity != "00"))
			document.loadTradeItem.quantity.value += eval(digit);
		else
			document.loadTradeItem.quantity.value = "" + eval(digit);
		newnumber = false;
	};
	
	function clearQuantity() {
		document.loadTradeItem.quantity.value = "";
		newnumber = true;
	
	};

</script>

	<div id="home_quantityDialog" style="width: 250px;" class="easyui-dialog" data-options="title:'請輸入數量',modal:true,closed:true">
		<s:form id="loadTradeItem" name="loadTradeItem">
				<input type=hidden name="productId">
				<input type=hidden name="barcodeNumber">
			<input style="font-size:22px;" id="w1" type="text" size="20"
				name="quantity" value="" onFocus="blur()">
			<table id="cam">

				<tr>
					<td><button  type="button" id="b1" name="1" value=" 1 "
							onClick="inputQuantity(this.value)">
							<h6>1</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="2" value=" 2 "
							onClick="inputQuantity(this.value)">
							<h6>2</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="3" value=" 3 "
							onClick="inputQuantity(this.value)">
							<h6>3</h6>
						</button>
					</td>
				</tr>
				<tr>
					<td><button type="button" id="b1" name="4" value=" 4 "
							onClick="inputQuantity(this.value)">
							<h6>4</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="5" value=" 5 "
							onClick="inputQuantity(this.value)">
							<h6>5</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="6" value=" 6 "
							onClick="inputQuantity(this.value)">
							<h6>6</h6>
						</button>
					</td>

				</tr>
				<tr>
					<td><button type="button" id="b1" name="7" value=" 7 "
							onClick="inputQuantity(this.value)">
							<h6>7</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="8" value=" 8 "
							onClick="inputQuantity(this.value)">
							<h6>8</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="9" value=" 9 "
							onClick="inputQuantity(this.value)">
							<h6>9</h6>
						</button>
					</td>
				</tr>
				<tr>
					<td><button type="button" id="b1" name="0" value=" 0 "
							onClick="inputQuantity(this.value)">
							<h6>0</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="0" value=" '00' "
							onClick="inputQuantity(this.value)">
							<h6>00</h6>
						</button>
					</td>
					<td><button type="button" id="b1">
							<h6></h6>
						</button>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<button type="button" id="b13" onClick="submitLoadTradeItem()">
							<h6>確認</h6>
						</button>
						<button type="button" id="b14" onClick="clearQuantity()">
							<h6>清除</h6>
						</button>
					</td>
				</tr>
			</table>
		</s:form>
	</div>