<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@  page import="main.java.need.AutoFileAndFolder" %>
<%
	String rootPath = request.getParameter("rootPath");
	String javaRootPath = request.getParameter("javaRootPath");
	String xmlRootPath = request.getParameter("xmlRootPath");
	String viewRootPath = request.getParameter("viewRootPath");
	String filePath = request.getParameter("filePath");
	String tableName = request.getParameter("tableName");
	
	AutoFileAndFolder autoFile = new AutoFileAndFolder();
	String result = autoFile.start(rootPath, javaRootPath, xmlRootPath, viewRootPath, filePath, tableName);
%>

<html>
<head>
	<link href="/assets/css.css" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>Create File and Folder Result</div>
	<br>
	<div>
		<%= result %>
	</div>
	
	test psuh!!
	
</body>
</html>