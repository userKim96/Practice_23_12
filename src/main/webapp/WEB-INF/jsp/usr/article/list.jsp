<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="${board.name }" />

	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="flex justify-between">
				<div class="flex mb-2">
					<span>총 : ${articlesCnt } 글</span>
				</div>
				<form>
					<input name="boardId" type="hidden" value="${board.id }" />
					<div class="flex">
						<div>
							<select name="searchKeywordType" data-value="${searchKeywordType }" class="select select-sm select-bordered w-full max-w-xs">
								<option value="keywordTitle">제목</option>
							    <option value="keywordBody">내용</option>
							    <option value="keywordTitle,Body">제목+내용</option>
							</select>
						</div>
						<div class="mx-1">
							<input name="searchKeyword" value="${searchKeyword }" placeholder="검색어 입력" class="input input-sm input-bordered flex w-full max-w-xs" />
						</div>
						<div>
							<button class="btn btn-sm btn-outline">검색</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="overflow-x-auto">
				<table class="table table-zebra text-xl">
					<thead class="text-base">
						<tr>
							<th>번호</th>
							<th>작성일</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>추천수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr>
								<th>${article.id }</th>
								<th>${article.regDate.substring(2, 16) }</th>
								<th class="hover:underline"><a href="detail?id=${article.id }">${article.title }</a></th>
								<th>${article.writerName }</th>
								<th>${article.hitCount }</th>
								<th>${article.point }</th>
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
			<div class="mt-2 flex justify-center">
				<div class="join">
					<c:set var="pageMenuLen" value="5"/>
					<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1}"/>
					<c:set var="endPage" value="${page + pageMenuLen <= pagesCnt ? pge + pageMenuLen : pagesCnt }"/>
					
					<c:set var="baseUri" value="boardId=${board.id }&searchKeywordType=${searchKeywordType }&searchKeyword=${searchKeyword }"/>
					
					<c:if test="${page == 1 }">
						<a class="join-item btn btn-disabled"></a>
					</c:if>
					<c:if test="${page > 1 }">
						<a class="join-item btn" href="?page=1&${baseUri }">«</a>
					</c:if>
					
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<a class="join-item btn ${page == i ? 'btn-active' : ''}" href="?page=${i }&${baseUri }">${i }</a>
					</c:forEach>
					
					<c:if test="${page < pagesCnt }">
						<a class="join-item btn" href="?page=${pagesCnt }&&${baseUri }">»</a>
					</c:if>
					<c:if test="${page == pagesCnt }">
						<a class="join-item btn btn-disabled"></a>
					</c:if>
				</div>
			</div>
			
		</div>
		
	</section>
	
	
	<%@ include file="../common/foot.jsp" %>