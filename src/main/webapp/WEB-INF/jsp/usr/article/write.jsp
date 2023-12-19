<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="Article Write" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
		
		const writeForm_onSubmit = function(form) {
			form.title.value = form.title.value.trim();
			form.body.value = form.body.value.trim();
			
			if (form.title.value.length == 0) {
				alert('내용을 입력해주세요');
				form.title.focus();
				return;
			}
			
			if (form.body.value.length == 0) {
				alert('제목을 입력해주세요');
				form.body.focus();
				return;
			}
			
			form.submit();
		}
	
	</script>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doWrite" method="post" onsubmit="writeForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table>
						<tr>
							<td>제목</td>
							<th><input name="title" type="text" class="input input-bordered input-lg w-full max-w-xs" placeholder="제목를 입력해주세요."/></th>
						</tr>
						<tr>
							<td>내용</td>
							<th><textarea name="body" placeholder="Type here" class="input input-bordered input-lg w-full max-w-xs" placeholder="내용을 입력해주세요."></textarea></th>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-sm btn-outline mt-4 p-2">작성</button></td>
						</tr>
					</table>
				</div>
			</form>
	 		<div class="btns">
				<button class="btn btn-sm btn-outline mt-4 p-2" onclick="history.back()">뒤로가기</button>
			</div>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>