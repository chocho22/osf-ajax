<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String str = "${str}";
out.print("안녕안녕<br>안녕");
%>
${str}
<script>
	var str = 'abc';
</script>
</body>
</html>