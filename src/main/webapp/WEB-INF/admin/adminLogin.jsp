<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminLogin</title>
</head>
<body>
	<form id="f" method="post" action="./adminlogin.do">
		ID : <input type="text" name="adminId"><br> PW : <input
			type="password" name="adminPw"><br> <input type="submit"
			value="login">
	</form>
</body>
<script>
	document.querySelector('#f').addEventListener('submit', function() {
		
	})
</script>
</html>