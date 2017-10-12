//by wdz
function Show_Structure(){
	
}
function GetFamilys(obj){

	var tablename = $(obj).parent().prev().find("input").attr('id');	
	var url = "GetFamilys?tablename="+tablename;
	window.location.href=url;
	
}
function ChangeSelect(obj){

	var SelcetName = $(obj).attr('name');
	var SelcetValue = $(obj).attr('value');
	$(obj).parent().parent().parent().prev().val(SelcetName);
	$(obj).parent().parent().parent().next().val(SelcetValue);

}
function RemoveFamily(obj){
	var arry = $(obj).attr('name').split('&');
	
	$.ajax({
	    type : "POST",
	    contentType : "application/json",
	    url : "RemoveFamily?tablename="+arry[0]+"&familyname="+arry[1],
	    dataType : 'json',
	    success : function(result) {
	    	for(var i=0;i<=5;i++){
	    		$(obj).next().remove();	    		
	    	}
	    	$(obj).remove();
	    }
	}); 
}
function FamilyMinus(obj){
	alert($(obj).attr('name'));
}
//显示加载数据
function ShowDiv() {
$("#loading").show();
}

//隐藏加载数据
function HiddenDiv() {
$("#loading").hide();
}
function EditTable(Oldtablename){
	if($("#Show_TableName").val() == ""){
		alert("表名不能为空");
		return false;
	}
	var New_TableName = $("#Show_TableName").val();
	$.ajax({
	    type : "POST",
	    beforeSend: function () {
	        ShowLoading();
	    },
	    complete: function () {
	        HiddenLoading();
	        
	    },
	    contentType : "application/json",
	    url : "EditTable?New_TableName="+New_TableName,
	    dataType : 'json',
	    success : function(result) {
//	    	var AllRows= $(".col2").find("a");
//	    	$(AllRows).each(function(){
//	    		if($(this).text()==Oldtablename){
//	    			$(this).text(New_TableName);
	    			$("#Structure_Modal").modal('hide');
//	    		}
//	    	})
	    }
	}); 
}
