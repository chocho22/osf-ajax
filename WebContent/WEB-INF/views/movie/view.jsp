<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 상세 조회</title>
</head>
<body>
<c:if test="${selectMovie==null}">
	조회하신 번호는 없는 번호입니다.
	<a href="/movie/list">리스트로 돌아가기</a>
</c:if>
<c:if test="${selectMovie!=null}">
<table border="1">
	<tr>
		<th colspan="2" style="green">영화 상세 조회</th>
	</tr>
	<tr>
		<th>번호</th>
		<td>${selectMovie.miNum}</td>
	</tr>
	<tr>
		<th>영화명</th>
		<td>${selectMovie.miName}</td>
	</tr>
	<tr>
		<th>년도</th>
		<td>${selectMovie.miYear}</td>
	</tr>
	<tr>
		<th>국가</th>
		<td>${selectMovie.miNational}</td>
	</tr>
	<tr>
		<th>제작사</th>
		<td>${selectMovie.miVendor}</td>
	</tr>
	<tr>
		<th>감독</th>
		<td>${selectMovie.miDirector}</td>
	</tr>
</table>
<c:if test='${sessionScope.user!=null}'>
	<form method="post" action="/movie/delete">
		<input type="hidden" name="mi_num" value="${selectMovie.miNum}">
		<button>삭제</button>
	</form>
</c:if>
<a href="/movie/list"><button>리스트로 돌아가기</button></a>
</c:if>
</body>
</html>