<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 計算機功能 -->
<script type="text/javascript">
	function input(digit) {
		var result = document.calc.result.value;

		if ((!newnumber) && (result != "0") && (result != "00"))
			document.calc.result.value += eval(digit);
		else
			document.calc.result.value = "" + eval(digit);
		newnumber = false;
	};

	function clearnow() {
		document.calc.result.value = "";
		document.money.pocket.value = "";
		newnumber = true;
	};

	function count() {

		var a = document.calc.result.value;
		var b = document.money.totalAmount.value;
		var c = eval(a - b);
		if (c < 0) {
			parent.sy.messagerAlert('提示', '金錢不足 ! ', 'error');
			document.calc.result.value = "";
		} else if (c >= 0) {
			document.money.pocket.value = c;
			document.calc.result.value = "";
		}

	};
</script>

<div id="calcu">

	<form id="caform" name="calc">
		<input style="font-size:22px;" id="w1" type="text" size="20"
			name="result" value="" onFocus="blur()" />
		<table id="cam">
		
			<tr>
				<td><button type="button" id="b1" name="1" value=" 1 "
						onClick="input(this.value)">
						<h6>1</h6>
					</button></td>
				<td><button type="button" id="b1" name="2" value=" 2 "
						onClick="input(this.value)">
						<h6>2</h6>
					</button></td>
				<td><button type="button" id="b1" name="3" value=" 3 "
						onClick="input(this.value)">
						<h6>3</h6>
					</button></td>
			</tr>
			
			<tr>
				<td><button type="button" id="b1" name="4" value=" 4 "
						onClick="input(this.value)">
						<h6>4</h6>
					</button></td>
				<td><button type="button" id="b1" name="5" value=" 5 "
						onClick="input(this.value)">
						<h6>5</h6>
					</button></td>
				<td><button type="button" id="b1" name="6" value=" 6 "
						onClick="input(this.value)">
						<h6>6</h6>
					</button></td>
			</tr>
			
			<tr>
				<td><button type="button" id="b1" name="7" value=" 7 "
						onClick="input(this.value)">
						<h6>7</h6>
					</button></td>
				<td><button type="button" id="b1" name="8" value=" 8 "
						onClick="input(this.value)">
						<h6>8</h6>
					</button></td>
				<td><button type="button" id="b1" name="9" value=" 9 "
						onClick="input(this.value)">
						<h6>9</h6>
					</button></td>
			</tr>
			
			<tr>
				<td><button type="button" id="b1" name="0" value=" 0 "
						onClick="input(this.value)">
						<h6>0</h6>
					</button></td>
				<td><button type="button" id="b1" name="0" value=" '00' "
						onClick="input(this.value)">
						<h6>00</h6>
					</button></td>
				<td><button type="button" id="b1">
						<h6></h6>
					</button></td>
			</tr>
			
			<tr>
				<td colspan="4"><button type="button" id="b13"
						onClick="count()">
						<h6>確認</h6>
					</button>
					<button type="button" id="b14" onClick="clearnow()">
						<h6>清除</h6>
					</button></td>
			</tr>
		</table>
	</form>
</div>