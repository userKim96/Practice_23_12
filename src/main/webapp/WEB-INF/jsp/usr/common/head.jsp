<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>

<!-- 데이지UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.4.20/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />


</head>
<body>

	
	<div class="h-20 flex container mx-auto text-4xl">
		<div><a class="h-full px-3 flex items-conter" href="/"><span class="">로고</span></a></div>
		<div class="flex-grow"></div>
		<ul class="flex">
			<li><a class="hover:underline" href="/"><span class="h-full px-3 flex items-conter">HOME</span></a></li>
			<li><a class="hover:underline" href="/usr/article/list?boardId=1"><span class="h-full px-3 flex items-conter">NOTICE</span></a></li>
			<li><a class="hover:underline" href="/usr/article/list?boardId=2"><span class="h-full px-3 flex items-conter">FREE</span></a></li>
			<c:if test="${rq.getLoginedMemberId() == 0 }">
				<li><a class="hover:underline" href="/usr/member/login"><span class="h-full px-3 flex items-conter">로그인</span></a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<li><a class="hover:underline" href="/usr/member/doLogout"><span class="h-full px-3 flex items-conter">로그아웃</span></a></li>
			</c:if>
		</ul>
	</div>
	
	<section class="my-3 text-2xl">
		<div class="container my-auto px-3">
			<h1 class="font-bold">${pageTitle }</h1>
		</div>
	</section>