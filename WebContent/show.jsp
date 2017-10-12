<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="com.yw.Dao.TableDao"%>
<%@ page language="java" import="com.yw.bean.TableBean"%>
<%@ page language="java" import="java.util.*"%>
<%@ page import="com.yw.bean.FamilyBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();

	ArrayList<FamilyBean> familys = (ArrayList<FamilyBean>) request.getSession().getAttribute("familys");
	String tablename = (String) request.getSession().getAttribute("tablename");
	if (familys != null) {
		//System.out.print(tablename);
	}
%>
<script type="text/javascript">
    window.onload=function(){
//        PagingManage($('#page'),100,10);

        <%if (familys != null) {%>
        $("#Structure_Modal").modal('toggle');
        $("#Show_TableName").val("<%=tablename%>");
        <%for (int i = 0; i < familys.size(); i++) {%>
        $("#Familys").append('<a style="text-decoration:none;"name="<%=tablename%>&<%=familys.get(i).getFamilyName()%>"class="glyphicon glyphicon-remove" onclick="RemoveFamily(this)"></a>');
        $("#Familys").append('<input style="width:35%;padding-left:10px;"type="text" name="tablename" value="<%=familys.get(i).getFamilyName()%>"/> ');
        $("#Familys").append('<input style="width:25%;padding-left:10px;"type="text" name="tablename" value="MaxVersion" disabled="disabled"/> ');
        $("#Familys").append('<div class="btn-group"><a href style="width:10%"class="glyphicon glyphicon-menu-down" data-toggle="dropdown" href="javascript:;"></a><ul class="dropdown-menu"><li><a value="<%=familys.get(i).getMaxVersion()%>" name="MaxVersion" onclick="ChangeSelect(this)">MaxVersion</a></li><li><a value="<%=familys.get(i).getCompression()%>"name="Compression" onclick="ChangeSelect(this)">Compression</a></li><li><a value="<%=familys.get(i).getTimeToLive()%>"name="TimeToLive"onclick="ChangeSelect(this)">TimeToLive</a></li></ul></div>');

// 			$("#Familys").append("<div class='btn-group'>"+
//  								"<a href style='width:5%'class='glyphicon glyphicon-menu-down' data-toggle='dropdown' href='javascript:;'></a>"+
// // 								"<ul class='dropdown-menu'>"+
// // 								"<li><a value='' name='MaxVersion' onclick='ChangeSelect(this)'>MaxVersion</a></li>"+
// // 								"<li><a name='Compression' onclick='ChangeSelect(this)'>Compression</a></li>"+
// // 								"<li><a name='TimeToLive'onclick='ChangeSelect(this)'>TimeToLive</a></li>"+
// // 								"</ul>"+
// 								"</div>");


        $("#Familys").append('<input id="<%=familys.get(i).getFamilyName()%>"style="width:20%;padding-left:10px;"type="text"name="tablename" value="<%=familys.get(i).getMaxVersion()%>"/>');
        $("#Familys").append('<a style="width:5%;text-decoration:none;"class="glyphicon glyphicon-minus"></a> ');
        $("#Familys").append('<a style="width:5%;text-decoration:none;"class="glyphicon glyphicon-plus"></a> ');

// 						<a href class="glyphicon glyphicon-remove"></a>
// // 						<a href class="glyphicon glyphicon-remove"></a>
// // 						<input style="width:30%"type="text"name="tablename" />
// // 						<input style="width:30%"type="text"name="tablename" />
// // 						<a href class="glyphicon glyphicon-menu-down"></a>
// // 						<input style="width:20%"type="text"name="tablename" />
// // 						<a href class="glyphicon glyphicon-minus"></a>
// // 						<a href class="glyphicon glyphicon-plus"></a>
// 							');
// 										<a href
// 							style="float: left;" class="glyphicon glyphicon-remove"></a> <input
// 							style="float: left; width: 30%" type="text" name="tablename" />
// 							<input style="float: left; width: 30%" type="text"
// 							name="tablename" />

// 							<ul style="float:left;" class="nav nav-pills">
// 								<li class="dropdown all-camera-dropdown"><a
// 									class="dropdown-toggle" data-toggle="dropdown" href="#"> <b
// 										class="caret"></b>
// 								</a>
// 									<ul class="dropdown-menu">
// 										<li data-filter-camera-type="all"><a data-toggle="tab"
// 											href="#">HTML5</a></li>
// 										<li data-filter-camera-type="Alpha"><a data-toggle="tab"
// 											href="#">PHP</a></li>
// 										<li data-filter-camera-type="Zed"><a data-toggle="tab"
// 											href="#">MySQL</a></li>
// 										<li data-filter-camera-type="Bravo"><a data-toggle="tab"
// 											href="#">JavaScript</a></li>

// 									</ul></li>
// 							</ul> <input style="width: 20%; float: left;" type="text"
// 							name="tablename" /> <a href style="width: 4%; float: left;"
// 							class="glyphicon glyphicon-minus"></a> <a href
// 							style="width: 4%; float: left;" class="glyphicon glyphicon-plus"></a>
        <%}%>
        <%request.getSession().setAttribute("familys", null);
            %>
    }

    <%}%>
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="./js/show.js"></script>
<script src="./js/addtable.js"></script>
<script src="./js/Table_Structure.js"></script>
<link rel="stylesheet" href="css/show.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<%
	    String mess=(String)session.getAttribute("message");
	    if("".equals(mess) || mess==null){
	         
	    }
	 	else{%>
	    <script type="text/javascript">
	        alert("<%=mess%>");
		</script>
	    <% session.setAttribute("message", "");%> 
	<% }%>
	<div id="div_body">
		<div id="div_top">
			<p id="log">HBaseTool</p>
		</div>
		<div id="div_link">
			<a id="a_Home" href="index.jsp">Home</a>
		</div>
		<hr>
		<div id="div_query">
			<div class="col-lg-6">
				<div class="input-group">
					<input type="text" class="form-control">
					<span class="input-group-btn">
						<button class="btn btn-default" onclick="querybtn()" type="button">
							Go!
						</button>
					</span>
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
			<button  type="button" onclick="dropbtn()" class="btn btn-default">Drop</button>
			<button class="btn btn-default pull-right" id="btn_add" data-toggle="modal" data-target="#myModal" onclick="fresh()">Add New Table</button>
			
		</div>
		<hr>
		<div id="div_table">
			<%--<c:set var="tableNum" value="${requestScope.tableNum}"/>--%>
			<%--<c:set var="pageShowNum" value="${requestScope.pageShowNum}"/>--%>
			<%--<c:set var="pageNum" value="${requestScope.pageNum}"/>--%>
			<%--<c:set var="beginIndex" value="${requestScope.beginIndex}"/>--%>
			<%--<c:set var="endIndex" value="${requestScope.endIndex}"/>--%>
			<%--<c:set var="page" value="${requestScope.page}"/>--%>
			<%--<c:set var="currentTables" value="${requestScope.tableList.subList(beginIndex, endIndex)}"/>--%>
			<%--<p>${tableNum}</p>--%>
			<%
				ArrayList tablist = (ArrayList)session.getAttribute("tablist");
				int tableNum = (int)session.getAttribute("tableNum");
				int beginIndex = (int)session.getAttribute("beginIndex");
				int endIndex = (int)session.getAttribute("endIndex");
				int pageNum = (int)session.getAttribute("pageNum");
				int currentPage = (int)session.getAttribute("page");
				ArrayList currentTables = (ArrayList)session.getAttribute("tableList");
				List test = currentTables.subList(beginIndex, endIndex);

			%>
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="col1"><input type="checkbox" name="check_all"
							onclick="checkAll(this)"></th>
						<th class="col2">TableName</th>
						<th class="col3">Enable</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i = 0; i < test.size(); i++) {
					   TableBean tab = (TableBean)test.get(i);
					   String status = tab.getStatus();

				%>

				<tr>
					<td class="col1"><input type="checkbox" name="info"
											id="<%=tab.getTableName()%>" onclick="dropcheck()"></td>
					<td class="col2">
						<a href="<%=path%>/hinServlet?action=showTable&tableName=<%=tab.getTableName() %>"><%=tab.getTableName() %></a>
						<button class="btn btn-default pull-right" id=""
								onclick="GetFamilys(this)" data-toggle="modal"
								data-target="#Structure_Modal">查看表结构
						</button>
					</td>
					<td class="col3">
						<%
							if(status == "Enable"){
						%> <input type="checkbox" onclick="enable(this)"
								  name="<%=tab.getTableName() %>" checked="checked"> <%
					}else{
					%> <input type="checkbox" onclick="enable(this)"
							  name="<%=tab.getTableName() %>"> <%
						}
					%>
					</td>
				</tr>
				<%}%>
				</tbody>
			</table>
		</div>

		<div id="page">
		<%if(pageNum > 1){%>
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li>
						<a href="/index?page=<%=currentPage - 1%>" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<%for(int i = 0; i < pageNum; i++){%>
					<li><a href="/index?page=<%=i + 1%>"><%=i + 1%></a></li>
					<%}%>
					<li>
						<a href="/index?page=<%=currentPage + 1%>" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>
		<%}%>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						添加表
					</h4>
				</div>
				<div class="modal-body">
					<ul class="list-group" style="list-style: none;overflow-y:scroll;max-height: 358.8px" id="ul_addtable">
			    		<li class="list-group-item" id="tableName">表名：<input type="text"  name="tablename"/></li>
			    		<li class="list-group-item" style="list-style: none">
							<span class="glyphicon glyphicon-remove" style="font-size: 150%;vertical-align:middle" onclick="deleteCol(this)"></span>
							列族名：
							<input type="text" name="cols">
							<span> Add a column property</span>
							<span class="glyphicon glyphicon-plus-sign" style="font-size: 150%;vertical-align:middle;margin-left: 5px" onclick="addProperty(this)"></span>
						</li>
			    		<li class="list-group-item" id="add_btn"><input type="button" onclick="addcol()" value="添加列族"></li>
	    			</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" onclick="submit()" class="btn btn-primary">
						提交
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 查看表结构模态框（Modal） -->
	<div class="modal fade" id="Structure_Modal" tabindex="-1"
		 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Table structure</h4>
				</div>
				<div class="modal-body">
					<ul style="height:auto" class="inline" id="ul_addtable">
						<li class="list-group-item">Table Name:</li>
						<li class="list-group-item"><input style="padding-left:10px;"id="Show_TableName"
														   type="text" name="tablename" /></li>
						<li class="list-group-item">Column Family:</li>
						<li style="height:auto" id="Familys" class="list-group-item">
							<!-- 						<a href -->
							<!-- 							class="glyphicon glyphicon-remove"></a> <input style="width: 30%" -->
							<!-- 							type="text" name="tablename" /> <input style="width: 30%" -->
							<!-- 							type="text" name="tablename" />  -->


							<!-- 							<input -->
							<!-- 							style="width: 20%" type="text" name="tablename" /> <a href -->
							<!-- 							style="width: 4%" class="glyphicon glyphicon-minus"></a> <a href -->
							<!-- 							style="width: 4%" class="glyphicon glyphicon-plus"></a>  -->

							<!-- 													<a href -->
							<!-- 							class="glyphicon glyphicon-remove"></a> <input style="width: 30%" -->
							<!-- 							type="text" name="tablename" /> <input style="width: 30%" -->
							<!-- 							type="text" name="tablename" /> <a href -->
							<!-- 							class="glyphicon glyphicon-menu-down"></a> <input -->
							<!-- 							style="width: 20%" type="text" name="tablename" /> <a href -->
							<!-- 							style="width: 4%" class="glyphicon glyphicon-minus"></a> <a href -->
							<!-- 							style="width: 4%" class="glyphicon glyphicon-plus"></a>  -->
							<!-- 										<a href -->
							<!-- 							style="float: left;" class="glyphicon glyphicon-remove"></a> <input -->
							<!-- 							style="float: left; width: 30%" type="text" name="tablename" /> -->
							<!-- 							<input style="float: left; width: 30%" type="text" -->
							<!-- 							name="tablename" /> -->

							<!-- 							<ul style="float:left;" class="nav nav-pills"> -->
							<!-- 								<li class="dropdown all-camera-dropdown"><a -->
							<!-- 									class="dropdown-toggle" data-toggle="dropdown" href="#"> <b -->
							<!-- 										class="caret"></b> -->
							<!-- 								</a> -->
							<!-- 									<ul class="dropdown-menu"> -->
							<!-- 										<li data-filter-camera-type="all"><a data-toggle="tab" -->
							<!-- 											href="#">HTML5</a></li> -->
							<!-- 										<li data-filter-camera-type="Alpha"><a data-toggle="tab" -->
							<!-- 											href="#">PHP</a></li> -->
							<!-- 										<li data-filter-camera-type="Zed"><a data-toggle="tab" -->
							<!-- 											href="#">MySQL</a></li> -->
							<!-- 										<li data-filter-camera-type="Bravo"><a data-toggle="tab" -->
							<!-- 											href="#">JavaScript</a></li> -->

							<!-- 									</ul></li> -->
							<!-- 							</ul> <input style="width: 20%; float: left;" type="text" -->
							<!-- 							name="tablename" /> <a href style="width: 4%; float: left;" -->
							<!-- 							class="glyphicon glyphicon-minus"></a> <a href -->
							<!-- 							style="width: 4%; float: left;" class="glyphicon glyphicon-plus"></a> -->
							<!-- 							<ul >   </ul> -->



						</li>
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" onclick="EditTable('<%=tablename%>');" class="btn btn-primary">
						提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>