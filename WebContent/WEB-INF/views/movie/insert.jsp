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
			<td><input type="text" name="mi_name" id="mi_name"></td>
		</tr>
		<tr>
			<th>년도</th>
			<td><input type="number" name="mi_year" id="mi_year"></td>
		</tr>
		<tr>
			<th>국가</th>
			<td><input type="text" name="mi_national" id="mi_national"></td>
		</tr>
		<tr>
			<th>제작사</th>
			<td><input type="text" name="mi_vendor" id="mi_vendor"></td>
		</tr>
		<tr>
			<th>감독</th>
			<td><input type="text" name="mi_director" id="mi_director"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button>등록</button>
		</tr>
	</table>
</form>
	<a href="/movie/list"><button>리스트로 돌아가기</button></a>
</body>
</html>