<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 輸入統一編號 -->
<script type="text/javascript">
	function Quantity() {  
		    $('#quantityDialog').dialog('open');
	};
	function inputity(digit) {
		var quant = document.load.quant.value;
		
		if ((!newnumber))
			document.load.quant.value += eval(digit);
		else
			document.load.quant.value = "" + eval(digit);
		newnumber = false;
	};
	
	function cleartity() {
		document.load.quant.value = "";
		newnumber = true;

	};
	function invo(){
		var d = document.load.quant.value;
		document.load.quant.value = "";
		document.money.invoice.value = d;
		$('#quantityDialog').dialog('close');
				
	};
</script>

	<div id="quantityDialog" style="width: 250px;" class="easyui-dialog" data-options="title:'統一編號',modal:true,closed:true">
		<form name="load" >
			<input style="font-size:22px;" id="w1" type="text" size="20"
				name="quant" value=""  onFocus="blur()">
			<table id="cam">

				<tr>
					<td><button  type="button" id="b1" name="1" value=" 1 "
							onClick="inputity(this.value)">
							<h6>1</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="2" value=" 2 "
							onClick="inputity(this.value)">
							<h6>2</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="3" value=" 3 "
							onClick="inputity(this.value)">
							<h6>3</h6>
						</button>
					</td>
				</tr>
				<tr>
					<td><button type="button" id="b1" name="4" value=" 4 "
							onClick="inputity(this.value)">
							<h6>4</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="5" value=" 5 "
							onClick="inputity(this.value)">
							<h6>5</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="6" value=" 6 "
							onClick="inputity(this.value)">
							<h6>6</h6>
						</button>
					</td>

				</tr>
				<tr>
					<td><button type="button" id="b1" name="7" value=" 7 "
							onClick="inputity(this.value)">
							<h6>7</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="8" value=" 8 "
							onClick="inputity(this.value)">
							<h6>8</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="9" value=" 9 "
							onClick="inputity(this.value)">
							<h6>9</h6>
						</button>
					</td>
				</tr>
				<tr>
					<td><button type="button" id="b1" name="0" value=" 0 "
							onClick="inputity(this.value)">
							<h6>0</h6>
						</button>
					</td>
					<td><button type="button" id="b1" name="0" value=" '00' "
							onClick="inputity(this.value)">
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
						<button type="button" id="b13" onClick="invo()">
							<h6>確認</h6>
						</button>
						<button type="button" id="b14" onClick="cleartity()">
							<h6>清除</h6>
						</button>
					</td>
				</tr>
			</table>
			</form>
	</div>