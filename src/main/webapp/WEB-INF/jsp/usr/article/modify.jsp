<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="Article Modify" />
	
	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doModify" method="post">
				<input name="id" type="hidden" value="${article.id }" />
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
							<th><input name="title" type="text" value="${article.title }" placeholder="제목을 입력해주세요." /></th>
						</tr>
						<tr>
							<td>내용</td>
							<th><textarea name="body" placeholder="내용을 입력해주세요.">${article.body }</textarea></th>
						</tr>
						<tr>
							<td colspan="2"><button>수정</button></td>
						</tr>
					</table>
				</div>
			</form>
			<div class="btns">
				<button onclick="history.back()">뒤로가기</button>
			</div>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>