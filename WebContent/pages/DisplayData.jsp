<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="entity.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<RowCell> cells = (ArrayList<RowCell>) request.getSession().getAttribute("cells");
	ArrayList<String> rowkeys = new ArrayList<String>();
	String path = request.getContextPath();
%>
<html>
<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: absolute;
	top: 10%;
	left: 10%;
	width: 50%;
	height: 50%;
	border: 16px solid lightblue;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}

.white_content_small {
	display: none;
	position: absolute;
	top: 20%;
	left: 30%;
	width: 30%;
	height: 45%;
	border: 16px solid lightblue;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入已经压缩的css文件 -->
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
<!-- 引入已经压缩的js文件 -->
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<form id="SearchForm" method="post" action="<%=path%>/DataSearch">
		<div class="input-group col-md-3" style="margin: 50px position:left;">
			<input id="SearchTerm" name="Term" type="text" class="form-control"
				placeholder="请输入字段名"/ > <span class="input-group-btn">
				<input type="button" class="btn btn-info btn-search" value="查找"
				onclick="serach()">
			</span>
		</div>
	</form>
	<div style="height: 50px"></div>

	<form id="AddRowKeyForm" method="post" action="<%=path%>/AddRow">
		<input id="Button1" type="button" value="添加行"
			onclick="ShowDiv('MyDiv','fade')" /> <input type="hidden" value='0'
			id="RowNum" name="RowNum">
		<!--弹出层时背景层DIV-->
		<div id="fade" class="black_overlay"></div>
		<div id="MyDiv" class="white_content">
			<div style="text-align: right; cursor: default; height: 40px;">
				<span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')">关闭</span>
			</div>
			<div style="text-align: left; cursor: default; height: 60px;">
				<span style="font-size: 25px">Insert New Row</span>
			</div>
			<div style="text-align: left; cursor: default; height: 30px;">
				<span style="font-size: 20px">Row Key</span>
			</div>
			<div style="text-align: left; cursor: default; height: 30px;">
				<input type="text" id="rowkey" name="rowkey">
			</div>
			<div style="text-align: left; cursor: default; height: auto;">
				<input type="button" value="Add Field" onclick="AddField()" />
				<ul id="ItemCourseList" class="list-group sortable-list"
					data-role="list" style="margin-bottom: 10px;"></ul>
			</div>

			<div style="text-align: right; cursor: default; height: 30px;">
				<input type="button" value="Cancel"
					onclick="CloseDiv('MyDiv','fade')" /> <input type="button"
					value="Submit" onclick="AddRow()" />
			</div>
		</div>
	</form>

	<div style="overflow: auto; width: 100%;">
		<table cellspacing="0" width="100%" id="dataTableDetail"
			align="center" class="table table-bordered table-striped table-hover">
			<%
				if (cells != null) {
					int j=0;
					for (int i = 0; i < cells.size(); i++) {
						//判断是否是同一行
						if (!rowkeys.contains(cells.get(i).getRowkey())) {
							rowkeys.add(cells.get(i).getRowkey());
							j++;
			%>
			<tr height="30">
				
				<td><%=cells.get(i).getRowkey()%></td>
				<td>
					<input type='button' id='testBtn' value='InsertColumn'onclick="ShowDiv('MyDiv2','fade2')">
					<form id='AddColumnForm<%=i%>' method="post" action="<%=path%>/AddColumn?test=<%=cells.get(i).getRowkey()%>"> 
						<!--弹出层时背景层DIV-->
						<div id="fade2" class="black_overlay"></div>
						<div id="MyDiv2" class="white_content">
							<%=cells.get(i).getRowkey()%>
							<div style="text-align: right; cursor: default; height: 40px;">
								<span style="font-size: 16px;"
									onclick="CloseDiv('MyDiv2','fade2')">关闭</span>
							</div>
							<div style="text-align: left; cursor: default; height: 60px;">
								<span style="font-size: 25px">Create new Column</span>
							</div>
							<div style="text-align: left; cursor: default; height: 30px;">
								<span style="font-size: 20px">Create Name</span>
							</div>
							<div style="text-align: left; cursor: default; height: 30px;">
								<input type="text" id="rowkey" name="rowkey">
							</div>
							<div style="text-align: left; cursor: default; height: 30px;">
								<span style="font-size: 20px">Create Value</span>
							</div>
							<div style="text-align: left; cursor: default; height: 30px;">
								<input type="text" id="rowkey" name="rowkey">
							</div>
							
							<div style="text-align: right; cursor: default; height: 30px;">
								<input type="button" value="Cancel"	onclick="CloseDiv('MyDiv2','fade2')" /> 
								<input type="button" value="Submit" onclick="AddColumn('<%=cells.get(i).getRowkey()%>')">
							</div>
						</div>
					</form>
				</td>
			</tr>
			<tr height="50">
				<td width=""><%=cells.get(i).getFamily()%>:<%=cells.get(i).getQualifier()%></br><%=cells.get(i).getValue()%></td>

				<%
					} else {
				%>

				<td width="15%"><%=cells.get(i).getFamily()%>:<%=cells.get(i).getQualifier()%></br><%=cells.get(i).getValue()%></td>

				<%
					}
						}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</div>

</body>
</html>
<script>
	function serach() {

		var SearchTerm = document.getElementById("SearchTerm");
		if (SearchTerm.value == "") {
			alert("搜索条件不能为空");
			SearchTerm.focus();
			return false;
		}
		document.getElementById("SearchForm").submit();

	}
	//弹出隐藏层
	function ShowDiv(show_div, bg_div) {
		document.getElementById(show_div).style.display = 'block';
		document.getElementById(bg_div).style.display = 'block';
		var bgdiv = document.getElementById(bg_div);
		bgdiv.style.width = document.body.scrollWidth;
		// bgdiv.style.height = $(document).height();
		$("#" + bg_div).height($(document).height());
	}
	//关闭弹出层
	function CloseDiv(show_div, bg_div) {
		document.getElementById(show_div).style.display = 'none';
		document.getElementById(bg_div).style.display = 'none';

	}
	function AddRow() {

		var AddRowKey = document.getElementById("rowkey");
		if (AddRowKey.value == "") {
			alert("行健不能为空");
			AddRowKey.focus();
			return false;
		}
		document.getElementById("AddColumnForm").submit();
	}
	function AddColumn(name) {
		alert(name);
// 		var AddRowKey = document.getElementById("rowkey");
// 		if (AddRowKey.value == "") {
// 			alert("行健不能为空");
// 			AddRowKey.focus();
// 			return false;
// 		}
	
		//$("AddColumnForm").reset();
	}
	function AddField() {
		var RowNum = document.getElementById("RowNum").value;
		RowNum = RowNum * 1 + 1;
		document.getElementById("RowNum").value = RowNum;
		var newheight = $("#MyDiv").height() + 50;
		$("#MyDiv").height(newheight);
		$("#ItemCourseList")
				.append(
						"<li class='list-group-item clearfix'><input type='text' name='columnfamily"+RowNum+"' placeholder='family:column_name' ><input type='text' name='cell_value"+RowNum+"' placeholder='cell_value'></li>");

	}
</script>
