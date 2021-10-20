<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@  page import="main.java.need.mig.ShowColumn" %>
<%@  page import="main.java.need.db.Column" %>
<%@  page import="java.util.ArrayList" %>
<%@  page import="java.util.List" %>
<%
	String tableName = request.getParameter("tableName");
	
	ShowColumn list = new ShowColumn();
	List<Column> result = list.getColumnList(tableName);
%>
<style>
  table {
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 10px;
  }
  tr{
      height: 42px;
  }
</style>
<html>
<head>
	<link href="/assets/css.css" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>Create File and Folder Result</div>
	<br>
	<div style="border: 1px solid; float: left;">
		<table>
			<%
			for(int i=0; i<result.size(); i++){
			%>
				<tr>	
					<td><%=result.get(i).getColumnName()%></td>
					<td><%=result.get(i).getComment()%></td>
				</tr>
			<%	
			}
			%>
		</table>
	</div>
	
	
	<div style="    border: 1px solid;float: left;margin-left: 100px;    width: 500px;padding-left: 100px;">
		
			<%
			for(int i=0; i<result.size(); i++){
			%>	
				
				/** <%=result.get(i).getComment()%> **/
				<br>
				private <%=result.get(i).columnTypeToJava()%> <%=result.get(i).columnNameToLower()%>; 
				<br>
				<br>
			<%	
			}
			%>
	</div>
	
	<div style="float: left;margin-left: 100px;    width: 500px;padding-left: 100px;">
		<table>
			<%
			for(int i=0; i<result.size(); i++){
			%>
				<tr>	
					<td><%=result.get(i).getColumnName().toLowerCase()%></td>
				</tr>
			<%	
			}
			%>
		</table>
	</div>
	
	
</body>
</html>