<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>상품번호</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>재고</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${data}">
				<tr>
					<td>${item.getPnum()}</td>
					<td><img src="${item.getPimage()}"></td>
					<td>${item.getPname()}</td>
					<td>${item.getPquantity()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<a href="./addproduct.do">상품등록</a>
</body>
</html>