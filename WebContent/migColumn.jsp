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
		<form id="" action="<%= request.getContextPath() %>/migColumnResult.jsp" method="post">
			<table border="1">
				<tr>
					<td>1</td>
					<td>2</td>
					<tr>
						<td>Table 이름</td>
						<td><input type="text" id="tableName" name="tableName" value=""></td>
					</tr>
				</tr>
			</table>
			<br>
			<input type="submit" value="만들기"/>
		</form>
	</div>
	
</body>
</html>