<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/include-header.jsp" %>

 <div class="jumbotron text-center">
 	<h1>강공 Homepage</h1>
 	<p>블로그 개발 한 번 해봅시다!!!</p>
 </div>

 <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	 <a href="#" class="navbar-brand">강공</a>
	 <!-- Toggle Button -->
	 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	 	<span class="navbar-toggler-icon"></span>
	 </button>
	 <div class="collapse navbar-collapse" id="collapsibleNavbar">
		 <ul class="navbar-nav">
			 <li class="nav-item"><a href="#" class="nav-link">공지사항</a></li>
			 <li class="nav-item"><a href="javascript:kangong.form.submitPost('${pageContext.request.contextPath}/board/list.do');" class="nav-link">게시판</a></li>
			 <li class="nav-item"><a href="javascript:kangong.form.submitPost('${pageContext.request.contextPath}/user/list.do');" class="nav-link">사용자등록</a></li>
		 </ul>
	 </div>
 </nav>


