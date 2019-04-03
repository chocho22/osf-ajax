<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개봉 영화 등록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>영화명</th>
			<td><input type="text" name="mi_name"></td>
		</tr>
		<tr>
			<th>년도</th>
			<td><input type="number" name="mi_year"></td>
		</tr>
		<tr>
			<th>국가</th>
			<td><input type="text" name="mi_national"></td>
		</tr>
		<tr>
			<th>제작사</th>
			<td><input type="text" name="mi_vendor"></td>
		</tr>
		<tr>
			<th>감독</th>
			<td><input type="text" name="mi_director"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button onclick="insertMovie()">등록</button>
		</tr>
	</table>
	<button>리스트로 돌아가기</button>
	<script>
		function insertMovie() {
			var inputs = document.querySelectorAll('input[name]');
			var params = '';
			for(var i=0;i<inputs.length;i++) {
				var input = inputs[i];
				params += input.name + '=' + input.value + '&';
			}
			
			var xhr = new XMLHttpRequest();
			xhr.open('POST','/am/insert');
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 && xhr.status == 200) {
					var result = JSON.parse(xhr.response);
					alert(result.msg);
					if(result.url) {
						locaion.href = result.url;
					}
				}
			}
			xhr.send(params);
		}
	</script>
</body>
</html>