/**
 * show.js的js代码
 */
function enable(obj){
	var tablename = obj.name;
	var url="table?action=Enable&tablename="+tablename;
	window.location.href=url;
}
function checkAll(checkall) {    
    var arr = document.getElementsByName('info');   
    if (checkall.checked == true) {   
        for(i=0;i<arr.length;i++){      
            arr[i].checked = true;   
        }  
        }else{  
            for(i=0;i<arr.length;i++){   
            	arr[i].checked = false;  
            }  
        }  
}
function dropcheck(){
	var arr = document.getElementsByName('info');
	var checkall = document.getElementsByName('check_all');
	var status = 1;
	for(i=0;i<arr.length;i++){
		if(arr[i].checked == false){
			status = 0;
		}
	}
	if(status == 1){
		checkall[0].checked = true;
	}else{
		checkall[0].checked = false;
	}
}
function querybtn(){
	var arr = document.getElementsByName('txt_querykey');
	var key = arr[0].value;
	var url="table?action=querykey&key="+key;
	window.location.href=url;
}
function dropbtn(){
	var arr = document.getElementsByName('info');
	var tab_arr=new Array()
	for(var i=0, x=0;i<arr.length;i++){
		if(arr[i].checked == true){
			var tablename = arr[i].id;
			var tab_en = document.getElementsByName(tablename);
			if(tab_en[0].checked == true){
				alert("删除"+tablename+"表，必须先Disable表！")
				return;
			}else{
				tab_arr[x]=tablename;
				x++;
			}
		}
	}
	var url="table?action=drop&tablename="+tab_arr;
	window.location.href=url;
}
function addbtn(){
	var url="AddTable.jsp"
	window.location.href=url;
}
