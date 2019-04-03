<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.getParameterNames();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 리스트</title>
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
	<c:if test="${sessionScope.user!=null}">
		<a href="/views/movie/ajax_insert"><button>개봉 영화 등록</button></a>
	</c:if>
<script>
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/am/list');
	xhr.onreadystatechange = function() {
		if(xhr.readyState==4 && xhr.status==200) {
			var list = JSON.parse(xhr.response);
			var html = '';
			for (var i=0;i<list.length;i++) {
				html += '<tr onmouseover="this.style.backgroundColor=\'green\'"';
				html += ' onmouseout="this.style.backgroundColor=\'\'"';
				html += ' onclick="goPage(' + list[i]['miNum'] + ')">';
				html += '<td>' + list[i]['miNum'] + '</td>';
				html += '<td>' + list[i]['miName'] + '</td>';
				html += '<td>' + list[i]['miYear'] + '</td>';
				html += '<td>' + list[i]['miNational'] + '</td>';
				html += '<td>' + list[i]['miVendor'] + '</td>';
				html += '<td>' + list[i]['miDirector'] + '</td>';
				html += '</tr>';
			}
			document.querySelector('#tbody').innerHTML = html;
		}
	}
	xhr.send();
	function goPage(miNum) {
		location.href='/views/movie/ajax_view?miNum=' + miNum;
	}
</script>

</body>
</html>