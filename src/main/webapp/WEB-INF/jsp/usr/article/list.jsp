<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<h1>Article List</h1>

		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>작성일</th>
					<th>작성자</th>
					<th>제목</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr>
						<th>${article.id }</th>
						<th>${article.regDate }</th>
						<th>${article.memberId }</th>
						<th>${article.title }</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
</body>
</html>