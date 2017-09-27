<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,entity.Table"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<Table> tables = (ArrayList<Table>)request.getSession().getAttribute("tables");
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<%
			if(tables!=null){
				for(int i = 0;i < tables.size();i++){
					String tablename = tables.get(i).getTableName();
		%>
		<tr><td><a href="<%=path%>/DataDisplay?tablename=<%=tablename%>"><%=tablename%></a></td></tr>
		<%
				}
			}
		%>
	</table>
</body>
</html>