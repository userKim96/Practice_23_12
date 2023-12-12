<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="Article Detail" />
	
	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table>
					<tr>
						<td>번호</td>
						<th>${article.id }</th>
					</tr>
					<tr>
						<td>작성일</td>
						<th>${article.regDate.substring(2, 16) }</th>
					</tr>
					<tr>
						<td>수정일</td>
						<th>${article.updateDate.substring(2, 16) }</th>
					</tr>
					<tr>
						<td>작성자</td>
						<th>${article.writerName }</th>
					</tr>
					<tr>
						<td>제목</td>
						<th>${article.title }</th>
					</tr>
					<tr>
						<td>내용</td>
						<th>${article.body }</th>
					</tr>
				</table>
				
				
			</div>
			
			<div class="btns">
				<button onclick="history.back()">뒤로가기</button>
				
				<c:if test="${logindMemberId != null && logindMemberId  == article.memberId }">
					<a href="modify?id=${article.id }">수정</a>
					<a href="doDelete?id=${article.id }">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>