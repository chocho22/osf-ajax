<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>번호</th>
		<th>영화명</th>
		<th>년도</th>
		<th>국가</th>
		<th>제작사</th>
		<th>감독</th>
	</tr>
		<tbody id="tbody">
		
		</tbody>
</table>
<script>
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/am/${param.miNum}');
	xhr.onreadystatechange = function() {
		var html = '';
		if(xhr.readyState==4 && xhr.status==200) {
			var movie = JSON.parse(xhr.response);
			html = '<tr>';
			html += '<td>' + movie['miNum'] + '</td>';
			html += '<td>' + movie['miName'] + '</td>';
			html += '<td>' + movie['miYear'] + '</td>';
			html += '<td>' + movie['miNational'] + '</td>';
			html += '<td>' + movie['miVendor'] + '</td>';
			html += '<td>' + movie['miDirector'] + '</td>';
			html += '</tr>';
		}
		document.querySelector('#tbody').innerHTML = html;
	}
	xhr.send();
</script>
<a href="/am/list"><button>리스트로 돌아가기</button></a>
</body>
</html>