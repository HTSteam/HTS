<%@ page import="java.util.ArrayList" %>
<%@ page import="static javax.swing.text.html.CSS.getAttribute" %>
<%@ page import="com.Hin.java.TableList" %>
<%@ page import="com.Hin.java.Table" %><%--
  Created by IntelliJ IDEA.
  User: yycq
  Date: 17-8-22
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList tables = (ArrayList)session.getAttribute("tables");
	String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>

</div>
<%
    for (int i = 0; i < tables.size(); i++){
        Table table = (Table)tables.get(i);
%>
<a href="<%=path%>/hinServlet?action=showTable&tableName=<%=table.getTableName()%>">
    <%=table.getTableName()%>
</a><br>
<%
    }
%>
</body>
</html>
