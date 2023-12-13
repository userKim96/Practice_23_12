<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set var="pageTitle" value="Member Login" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
		
		const loginForm_onSubmit = function(form) {
			form.loginId.value = form.loginId.value.trim();
			form.loginPw.value = form.loginId.value.trim();
			
			if (form.loginId.value.length == 0) {
				alert('아이디를 입력해주세요');
				form.loginId.focus();
				return;
			}
			
			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요');
				form.loginPw.focus();
				return;
			}
			
			form.submit();
		}
	
	</script>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doLogin" method="post" onsubmit="loginForm_onSubmit(this); return false;">
				<div class="table-box-type">
					<table>
						<tr>
							<td>아이디</td>
							<th><input name="loginId" type="text" placeholder="아이디를 입력해주세요."/></th>
						</tr>
						<tr>
							<td>비밀번호</td>
							<th><input name="loginPw" type="text" placeholder="비빌번호를 입력해주세요."/></th>
						</tr>
						<tr>
							<td colspan="2"><button>로그인</button></td>
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