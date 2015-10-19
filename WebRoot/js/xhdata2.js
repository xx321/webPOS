var json = 
$(function(){	
	var jsonData = eval(json);
	$.each(jsonData,function(i,n){
		$("#Sresult").append("<li></li>");  
	});
	var num_entries = $("#Sresult li").length;	
	var showCount = 4;
	var initjqview = function() {
		
		// 创建分页
		$("#jqview").jqview(num_entries, {
			num_edge_entries: 0, //边缘页数
			num_display_entries: 0, //主体页数
			callback: pageselectCallback,
			items_per_page:showCount //每页显示1项
		});
	 }();	 
	function pageselectCallback(page_index, jq){
		var max_elem = Math.min((page_index+1) *showCount, num_entries);		
		$("#hList").html("");		
		for(var i=page_index*showCount;i<max_elem;i++){
			var new_content = $("#Sresult li:eq("+i+")").clone();
			$("#hList").append(new_content); //装载对应分页的内容
		}
		return false;
	}
});