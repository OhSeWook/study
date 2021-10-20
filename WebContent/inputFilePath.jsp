<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="/assets/css.css" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>Create File and Folder</div>
	<br>
	<div>
		<form id="" action="<%= request.getContextPath() %>/result.jsp" method="post">
			<table border="1">
				<tr>
					<td>Root 경로(프로젝트)</td>
					<td><input type="text" id="rootPath" name="rootPath" value="C:/dev/open/ocu/src/main"></td>
				</tr>
				<tr>
					<td>Java 경로</td>
					<td><input type="text" id="javaRootPath" name="javaRootPath" value="/java/nwframework/pop/com/"></td>
				</tr>
				<tr>
					<td>Xml 경로</td>
					<td><input type="text" id="xmlRootPath" name="xmlRootPath" value="/resources/egovframework/sqlmap/mappers/pop/com/"></td>
				</tr>
				<tr>
					<td>View 경로</td>
					<td><input type="text" id="viewRootPath" name="viewRootPath" value="/webapp/WEB-INF/jsp/pages/pop/com/"></td>
				</tr>
				<tr>
					<td>File 경로</td>
					<td><input type="text" id="filePath" name="filePath" value=""></td>
				</tr>
				<tr>
					<td>Table 이름</td>
					<td><input type="text" id="tableName" name="tableName" value=""></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="만들기"/>
		</form>
	</div>
	
</body>
</html>