<%@ page import="java.util.Collection"%>
<%@ page import="org.apache.hadoop.hbase.HColumnDescriptor"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.Hin.java.Table"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.Hin.java.Cell"%>
<%@ page import="com.Hin.java.Row"%><%--
  Created by IntelliJ IDEA.
  User: yycq
  Date: 17-8-22
  Time: 下午5:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	Table table = (Table)session.getAttribute("table");
	String path = request.getContextPath();
%>
<link href="<%=path%>/css/tableView.css" rel="stylesheet">
<html>
<head>
<title>Title</title>
<%--<link rel="styleSheet" type="text/css" href="CSS/tableView.css">--%>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function serach() {
		document.getElementById("SearchForm").submit();
	};
	function AddField(){
		var RowNum = document.getElementById("RowNum").value;
		RowNum = RowNum * 1 + 1;
		document.getElementById("RowNum").value = RowNum;
		var newheight = $("#MyInsert").height() + 50;
		$("#MyInsert").height(newheight);
		$("#ItemCourseList")
				.append(
						"<li class='list-group-item clearfix'><input type='text' name='columnfamily"+RowNum+"' placeholder='family:column_name' ><input type='text' name='cell_value"+RowNum+"' placeholder='cell_value'></li>");		
		
	};
	//删除cell
    function deleteCell(obj) {
        var families_cols = new Array();
        var i = 0;

        $(obj).parents(".view-row").find(".cell-clicked").each(function () {
            families_cols[i] = $(this).find("#family").text();
            i++;
        });
        if(i == 0){
            alert("you haven't chosen any cell");
            return ;
        }
        if (i == 1) {
            var con = confirm("Do you want to delete the cell");
        }else{
            var con = confirm("Do you want to delete the cells");
        }

        if(con == true){
            var rowKey = $(obj).parents(".view-row").find(".rowKey-value").text().split(":")[1];
            location.href = "<%=path%>/hinServlet?action=deleteCol" +
                "&tableName=<%=table.getTableName()%>" + "&rowKey=" + rowKey + "&families_cols=" + families_cols;
        }

    }
    //提交put操作
   function put() {
       var value=$("#pop-value").val();
       var rowKey = $("#rowKey-edit").text();
       var family_col = $("#family_col").text();

    location.href = "<%=path%>/hinServlet?action=put"
    +"&tableName=<%=table.getTableName()%>" + "&rowKey=" + rowKey
                 + "&family_col=" + family_col + "&value=" + value;
   }
   //关闭put操作框
   function cancel() {
       $("#popDiv").css("display","none");
       $("#layer").css("display", "none");
   }


	$(document).ready(function(){
		var values = new Array();
		//鼠标移动到行显示工具栏
		$(".rowKey").mouseenter(function () {
            $(this).find(".tools").toggleClass("show", 1);
        });
        $(".rowKey").mouseleave(function () {
            $(this).find(".tools").toggleClass("show", 1);
        });
        //设置cell选中状态
        $(".cell").click(function () {
            /* $(this).toggleClass("cell", 1); */
            $(this).toggleClass("cell-clicked");
        });
      	//鼠标移动到cell显示编辑按钮
        $(".cell").mouseenter(function () {
            $(this).find(".edit").toggleClass("show", 1);
        });
        $(".cell").mouseleave(function () {
            $(this).find(".edit").toggleClass("show", 1);
        });
        //设置rowKey选中状态
        $(".rowKey").click(function () {
            $(this).find(".rowKey-value-ok").toggleClass("show", 1);
        });
        //设置点击属性值不选中Cell
        $(".value").click(function(){
        	return false;
        });
        //鼠标进入显示垃圾桶
        $(".view-row").mouseenter(function () {
            $(this).find(".trash-row").addClass("show");
       
   
        });
        $(".view-row").mouseleave(function () {
        	$(this).find(".trash-row").removeClass("show");
        });

        //删除行
		$(".trash-row").click(function(){
		    var con = confirm("Do you want to delete this row?");
		    if(con == true){
		        alert("sss");
				var row = $(this).parents(".view-row").find(".rowKey-value").text().split(":")[1];
				alert(row);

                location.href = "/hinServlet?action=deleteRow" +
                    "&tableName=<%=table.getTableName()%>" + "&rows=" + row;
			}
		});
        //修改Value

        //编辑Cell
        $(".edit").click(function () {
            $("#popDiv").css("display", "block");
            $("#layer").css("display", "block");
            var family_col = $(this).prev().prev().text();
            var value = $(this).prev().text();
            var rowKey = $(this).parents(".cells").prev().find(".rowKey-value").text().split(":")[1];
            $("#pop-value").val(value);
            $("#rowKey-edit").text(rowKey);
            $("#family_col").text(family_col);
            $.ajax({
                type:"post",
                url:"<%=path%>/hinServlet",
                async : true,
                dataType: 'json',
                data: {'action':'getCellTimestamp','tableName': '<%=table.getTableName()%>',
                    'rowKey':rowKey, 'family_col': family_col, 'value': value},
                
                success : function(data){
                    alert("success");
                    var versioList = $("#version_list");
                    values.slice(0, values.length);
                    values.push(value);
                    versioList.empty();
                    versioList.append("<li role=\"presentation\" class=\"active\"><a href=\"#\">VERSION</a></li>");
//                    versioList.append("<li role=\"presentation\" class=\"timestamp\" id=\"0\"><a href=\"#\">CurrentVersion</a></li>");
                    for(var i = 0; i < data.length; i++){
                        values.push(data[i].value);
                        var j = i + 1;
                        versioList.append("<li role=\"presentation\" class=\"timestamp\" id="+ j +"><a href=\"#\">"+ data[i].timestamp +"</a></li>");
                    }
                    $(".timestamp").click(function () {
                   
                        var i = parseInt(this.id);
                        $("#pop-value").val(values[i]);
                    });
                },
                error: function () {
                    alert("error");
                },
            });

        });

	}); 
	
       <%--
		//新增row
		$(".tool-item").children(".plus").click(function () {
			$("#RowDiv").css("display", "block");
            $("#layer2").css("display", "block");
		});
		//新增column
		$(".row-item").children(".insertcolumn").click(function(){
			var rowkey = $(this).parent().siblings("#rowKey").text();
			$("#ColumnDiv").css("display", "block");
            $("#layer3").css("display", "block");
    		//新增列的提交
    		$("#submit3").click(function(){
    			var FamilyColumn = document.getElementById("FamilyColumn");
    			var Value = document.getElementById("Value");
    			if (FamilyColumn.value == "") {
    				alert("列族不能为空");
    				AddRowKey.focus();
    				return false;
    			}
    			if(Value.value == ""){
    				alert("值不能为空");
    				AddRowKey.focus();
    				return false;
    			}
    			location.href = "<%=path%>/hinServlet?action=AddColumn"+"&rowkey="+rowkey+"&FamilyColumn="+FamilyColumn.value+"&Value="+Value.value;
    		});

		});
        //关闭事件
        $("#cancel").click(function () {
            $("#popDiv").css("display","none");
            $("#layer").css("display", "none");
        });
        $("#cancel2").click(function () {
            $("#RowDiv").css("display","none");
            $("#layer2").css("display", "none");
        });
        $("#cancel3").click(function () {
            $("#ColumnDiv").css("display","none");
            $("#layer3").css("display", "none");
        });
        $("#MyRemove").click(function () {
            $("#RowDiv").css("display","none");
            $("#layer2").css("display", "none");
        });



        });
		//新增行的提交
		$("#submit2").click(function(){
			var AddRowKey = document.getElementById("rowkey");
			if (AddRowKey.value == "") {
				alert("行健不能为空");
				AddRowKey.focus();
				return false;
			}
			document.getElementById("AddRowKeyForm").submit();
		});


        --%>

    
    </script>
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<span class="headline-item" href="#"> <img alt="Brand"
					src="<%=path%>/img/letter-h.png" width="12%">
				</span> <a class="headline-item" href="allTables.jsp">Home</a> <a
					class="headline-item">/<%=table.getTableName()%></a>
			</div>
		</div>
	</nav>
	<form id="SearchForm" method="post"
		action="<%=path%>/hinServlet?action=Search">
		<div class="input-group col-md-3" style="margin: 50px position:left;">
			<input id="SearchTerm" name="Term" type="text" class="form-control"
				placeholder="请输入字段名"/ > <span class="input-group-btn">
				<input type="button" class="btn btn-info btn-search" value="查找"
				onclick="serach()">
			</span>
		</div>
	</form>
	

	
	<div class="view">
		<%
			if(table.getRows().size() == 0){
		%>
		<p style="text-align:center">There is no data in this table</p>
		<%
			}else{
			    for (Row row : table.getRows()) {
		%>
		<div class="view-row">
			<div class="rowKey">
				<div class="rowKey-value">rowKey:<%=row.getRowKey()%></div>
				<div class="rowKey-value-ok hide"><span class="glyphicon glyphicon-ok-circle"></span></div>
				<div class="tools hide">
					<button class="glyphicon glyphicon-plus"></button>
					<button class="glyphicon glyphicon-trash" onclick="deleteCell(this)"></button>
				</div>
			</div>


			<div id="cells" class="cells">
				<%
					for (Cell cell : row.getCells()) {
				%>
				<div class="cell">
					<div id="family"><%=cell.getFamily()%>:<%=cell.getCol()%></div> <textarea class="value"><%=cell.getValue()%></textarea>
					<div class="edit">
						<button class="glyphicon glyphicon-pencil"></button>
					</div>
				</div>
				<%
					}
				%>
			</div>
			<div class="trash-row">
				<button class="glyphicon glyphicon-trash"></button>
			</div>
		</div>
		<%
				}
			}
		%>
	</div>
	
	<!-- 编辑遮罩层 -->
	<div id="layer" class="layer"></div>
	<!-- 编辑数据框 -->
	<div id="popDiv" class="valuediv" style="display:none">
	    <div class="page-header">
	        <h1><span>Edit Cell-</span>
	            <span id="rowKey-edit"></span>
	            <span id="family_col"></span>
	            <small id="timestamp"></small>
	        </h1>
	    </div>
    	<textarea id="pop-value" class="value-textarea"></textarea>
	    <div class="btn-group btn-group-justified" style="width:75%;margin-left:10px" role="group" aria-label="...">
	        <div class="btn-group" role="group">
	            <button id="submit" type="button" class="btn btn-default" onclick="put()">Sumbit</button>
	        </div>
	        <div class="btn-group" role="group">
	            <button id="cancel" type="button" class="btn btn-default"onclick="cancel()">Cancel</button>
	        </div>
	    </div>
	    <div class="version">
	        <ul id="version_list" class="nav nav-pills nav-stacked">
	            <li role="presentation" class="active"><a href="#">Version</a></li>
	            <li id="currentVersion" role="presentation"><a href="#">CurrentVersion</a></li>
	        </ul>
	    </div>
	</div>
			
</body>
<%-- <body>
	
	<form id="SearchForm" method="post"
		action="<%=path%>/hinServlet?action=Search">
		<div class="input-group col-md-3" style="margin: 50px position:left;">
			<input id="SearchTerm" name="Term" type="text" class="form-control"
				placeholder="请输入字段名"/ > <span class="input-group-btn">
				<input type="button" class="btn btn-info btn-search" value="查找"
				onclick="serach()">
			</span>
		</div>
	</form>
	<div class="tools">
		<ul class="horizontal">
			<li class="tool-item">
				<button class="btn btn-default btn-sm trash">
					<span class="glyphicon glyphicon-trash"></span>DeleteRows
				</button>
				<button class="btn btn-default btn-sm plus">
					<span class="glyphicon glyphicon-plus"></span> InsertRow
				</button>
			</li>
		</ul>
	</div>
	<div class="view">
		<%
			for (Row row : table.getRows()) {
		%>
		<div class="view-row">
			<h5 class="rowKey-normal">
				<ul class="horizontal">
					<li class="test" id="rowKey"><%=row.getRowKey()%></li>
					<li><span class="glyphicon glyphicon-ok-circle hide"></span></li>
					<li class="row-item"><span
						class="glyphicon glyphicon-plus insertcolumn"></span></li>
				</ul>
			</h5>

			<ul class="cells">
				<%
					for (Cell cell : row.getCells()) {
				%>
				<li id="cell" class="cell-normal">
					<div id="family"><%=cell.getFamily()%>:<%=cell.getCol()%></div> <textarea
						class="value"><%=cell.getValue()%></textarea>
					<div class="edit hide">
						<button class="glyphicon glyphicon-pencil"></button>
					</div>
				</li>
				<%
					}
				%>
			</ul>
		</div>
		<%
			}
		%>
		<div class="absulute1">
			<button class="glyphicon glyphicon-trash"></button>
		</div>
	</div>
	编辑遮罩层
	<div id="layer" class="layer"></div>
	编辑数据框
	<div id="popDiv" class="valuediv" style="display:none">
    <div class="page-header">
        <h1><span>Edit Cell-</span>
            <span id="rowKey-edit"></span>
            <span id="family_col"></span>
            <small id="timestamp"></small>
        </h1>
    </div>
    <textarea id="pop-value" class="value-textarea"></textarea>
    <div class="btn-group btn-group-justified" style="width:75%;margin-left:10px" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button id="submit" type="button" class="btn btn-default">Sumbit</button>
        </div>
        <div class="btn-group" role="group">
            <button id="cancel" type="button" class="btn btn-default">Cancel</button>
        </div>
    </div>
    <div class="version">
        <ul id="version_list" class="nav nav-pills nav-stacked">
            <li role="presentation" class="active"><a href="#">Version</a></li>
            <li id="currentVersion" role="presentation"><a href="#">CurrentVersion</a></li>
        </ul>
    </div>
</div>
	行遮罩层
	<div id="layer2" class="layer2"></div>
	编辑新增行框
	<form id="AddRowKeyForm" method="post"
		action="<%=path%>/hinServlet?action=AddRow">
		<div id="RowDiv" class="InsertRowdiv" style="display: none">
			<div style="text-align: right; cursor: default; height: 5px;">
				<button id="MyRemove" class="glyphicon glyphicon-remove"></button>
			</div>
			<div class="page-header">
				<h1>
					<span>Insert Row-</span> <span id="rowKey-edit"></span> <span
						id="family_col"></span> <small id="timestamp"></small>
				</h1>
			</div>
			<div id="MyInsert" class="NewRow">
				<input type="hidden" value='0' id="RowNum" name="RowNum">
				<div style="text-align: left; cursor: default; height: 30px;">
					<span style="font-size: 20px">Row Key</span>
				</div>
				<div style="text-align: left; cursor: default; height: 30px;">
					<input type="text" id="rowkey" name="rowkey">
				</div>
				<div
					style="text-align: left; cursor: default; height: auto; top: 20px">
					<button type="button" class="btn btn-default btn-sm plus"
						onclick="AddField()">
						<span class="glyphicon glyphicon-plus"></span> Add Fields
					</button>
					<div style="height: 10px"></div>
					<ul id="ItemCourseList" class="list-group sortable-list"
						data-role="list" style="margin-bottom: 10px; width: 350px"></ul>
				</div>
				<div class="btn-group btn-group-justified"
					style="width: 75%; margin-left: 10px" role="group" aria-label="...">
					<div class="btn-group" role="group">
						<button id="submit2" type="button" class="btn btn-default">Sumbit</button>
					</div>
					<div class="btn-group" role="group">
						<button id="cancel2" type="button" class="btn btn-default">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	列遮罩层
	<div id="layer3" class="layer3"></div>
	编辑新增列框
	<div id="ColumnDiv" class="InsertColumndiv" style="display: none">
		<div class="page-header">
			<h1>
				<span>Insert Column-</span> <span id="rowKey-edit"></span> <span
					id="family_col"></span> <small id="timestamp"></small>
			</h1>
		</div>
		<div style="text-align: left; cursor: default; height: 30px;">
			<span style="font-size: 20px">Create Column</span>
		</div>
		<div style="text-align: left; cursor: default; height: 30px;">
			<input type="text" id="FamilyColumn" name="FamilyColumn">
		</div>
		<div style="text-align: left; cursor: default; height: 20px;">
			<span style="font-size: 15px">Value</span>
		</div>
		<div style="text-align: left; cursor: default; height: 30px;">
			<input type="text" id="Value" name="Value">
		</div>
		<div style="height: 10px"></div>
		<div class="btn-group btn-group-justified"
			style="width: 75%; margin-left: 10px" role="group" aria-label="...">
			<div class="btn-group" role="group">
				<button id="submit3" type="button" class="btn btn-default">Submit</button>
			</div>
			<div class="btn-group" role="group">
				<button id="cancel3" type="button" class="btn btn-default">Cancel</button>
			</div>
		</div>
	</div>
</body> --%>
</html>
