<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="${board.name }" />

	<%@ include file="../common/head.jsp" %>
	
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
		
			<div class="mb-2">
				<div><span>총 : ${articlesCut } 글</span></div>
			</div>
			
			<div class="overflow-x-auto">
				<table class="table table-zebra text-xl">
					<thead class="text-base">
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
								<th class="hover:underline"><a href="detail?id=${article.id }">${article.title }</a></th>
								<th>${article.writerName }</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<div class="mt-4 flex justify-end">
					<a class="btn btn-sm btn-outline" href="write?id=${article.id }">글쓰기</a>
				</div>
			</c:if>
		</div>
		
	</section>
	
	
	<%@ include file="../common/foot.jsp" %>