<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
<form id="f" method="post" action="./addproduct2.do" enctype="multipart/form-data">
	상품코드 : <input type="text" name="pnum"><br>
	상품명 : <input type="text" name="pname"><br>
	상품이미지 : <input type="file" name="ppimage"><br>
	상품썸네일 : <input type="file" name="ppthumbnail"><br>
	상품재고 : <input type="text" name="pquantity"><br>
	<input type="submit" value="등록">
</form>
</body>
</html>