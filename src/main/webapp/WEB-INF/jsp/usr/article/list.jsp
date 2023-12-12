<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="Article List" />
	
	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table>
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
								<th>${article.regDate.substring(2, 16) }</th>
								<th class="hover:nuderline"><a href="detail?id=${articles }">${article.title }</a></th>
								<th>${article.writerName }</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>