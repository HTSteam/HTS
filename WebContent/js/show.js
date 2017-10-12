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

function test(){
	alert("test");
}
// //控制表级分页
// function PagingManage(obj, pageCount, pageSize, currentPage){
// 	if(obj){
// 		var dataCount = pageCount;
// 		var pageSize = pageSize;
// 		var currentPage = currentPage;
// 		var pageNum;
// 		var showPageNum = 6;
//
// 		var pagehtml = "";
// 		var divId = "" + obj.attr('id');
//
// 		 if(dataCount % pageSize == 0){
// 		 	pageNum = parseInt(dataCount / pageSize)
// 		 }else
// 		 	pageNum = parseInt(dataCount / pageSize) + 1;
//
// 		 if(pageNum > 1){
// 		 	pagehtml += "<nav aria-label='Page navigation'>" +
//              "<ul class='pagination'>" +
//                  "<li>" +
//                  "<a href='#' aria-label='Previous'>" +
//                  "<span aria-hidden='true'>&laquo;</span>" +
//              "</a>" +
//              "</li>";
// 		 	for(var i = 0; i < pageNum; i++){
// 		 		pagehtml += "<li><a href='#'>" + i + "</a></li>";
// 			 }
// 		 	pagehtml += "<li>" +
//              "<a href='#' aria-label='Next'>" +
//              "<span aria-hidden='true'>&raquo;</span>" +
//              "</a>" +
//              "</li>" +
//              "</ul>" +
//              "</nav>";
// 		 }
// 		 // if(pageNum > 1){
// 		 // 	pagehtml += '<li>' +
// 			// 	'<a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + (currentPage -1)+ ')">' +
// 			// 	'上一页' +
// 			// 	'</a>' +
// 			// 	'</li>'
// 		 // }
// 		 // var startPage = 1;
// 		 // if(showPageNum > pageNum){
// 		 // 	startPage = 1;
// 		 // }else{
// 		 // 	if(currentPage - (showPageNum / 2) <= 0){
// 		 // 		startPage = 1;
// 			// }else if(currentPage + (showPageNum / 2) >= pageNum){
// 		 // 		startPage = pageNum - showPageNum;
// 			// }else{
// 			// 	startPage = currentPage - (showPageNum / 2);
// 			// }
// 		 // }
//          //
// 		 // startPage = parseInt(startPage);
//          //
// 		 // for(var i = startPage; i < (startPage + showPageNum); i++){
// 		 // 	if(i > pageNum){
// 		 // 		break;
// 			// }
// 			// if(i == currentPage){
// 		 // 		pagehtml += '<li class="active">' +
// 			// 	'<a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + i +')">' + i + '</a>' +
// 			// 	'</li>';
// 			// }else{
// 			// 	pagehtml += '<li>' +
//           //           '<a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + i +')">' + i + '</a>' +
//           //           '</li>';
// 			// }
// 		 // }
//          //
// 		 // if(currentPage < pageNum){
// 		 // 	pagehtml += '<li>' +
//           //       '<a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + i +')">下一页</a>' +
//           //       '</li>';
// 		 // }
// 	}
// 	obj.html(pagehtml);
// }


// PagingManage($('#page'), 100, 10, 1);
