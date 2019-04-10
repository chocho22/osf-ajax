<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/common.js"></script>
</head>
<body>
	<script>
	function callback(res) {
		var res = JSON.parse(res);
		for(var addr of res.list) {
			document.write(addr);
		}
	}
		var au = new AjaxUtil();
		au.open('/addr2/list');
		au.setCallback(callback);
		au.send();
		
		
	</script>
</body>
</html>