<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개봉 영화 등록</title>
</head>
<body>
<form method="post" action="/movie/insert">
	<table border="1">
		<tr>
			<th>영화명</th>
			<td><input type="text" name="miName" id="miName"></td>
		</tr>
		<tr>
			<th>년도</th>
			<td><input type="number" name="miYear" id="miYear"></td>
		</tr>
		<tr>
			<th>국가</th>
			<td><input type="text" name="miNational" id="miNational"></td>
		</tr>
		<tr>
			<th>제작사</th>
			<td><input type="text" name="miVendor" id="miVendor"></td>
		</tr>
		<tr>
			<th>감독</th>
			<td><input type="text" name="miDirector" id="miDirector"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button>등록</button>
		</tr>
	</table>
</form>
</body>
</html>