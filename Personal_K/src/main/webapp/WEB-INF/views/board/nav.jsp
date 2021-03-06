<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<style type="text/css">
	li {list-style: none; display:inline; padding: 6px;}
</style>

<ul>
	<li><a href="/board/list">목록</a></li>
	<li><a href="/board/writeView">글 작성</a></li>
	<li>
		<c:if test="${member == null }">
			<a href = "/member/login">로그인</a>
		</c:if>
	</li>
	<li>
		<c:if test="${member != null }">
			<p>${member.userId }님 안녕하세요.</p>
			<p>${member.userName }님 안녕하세요.</p>
			<a href = "/member/memberUpdateView">회원정보 수정</a>
			<a href = "/member/logout">로그아웃</a>
		</c:if>
	</li>
</ul>
