<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<a href="/board/list">게시판 목록</a><br/>
<script type="text/javascript">
	$(document).ready(function () {
		/* # 로그아웃 */
		$("#logoutBtn").on("click", function () {
			location.href = "/member/logout";
		});
		/* # 회원가입 이동 */
		$("#join").on("click", function () {
			location.href = "/member/register";
		});
		
		/* # 무결성 체크 */
		$("#loginCheck").on("click", function () {
			if($("#userId").val() == "") {
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			if($("#userPass").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#userPass").focus();
				return false;
			}
			if(${msg == false}) {
				alert("로그인 실패")
				return false;
			}
		});
		
	})
</script>
<body>
	<form action="/member/login" method="post" name="loginForm">
		<!-- # 로그인 안된 사용자 -->
		<c:if test="${member == null }">
			<div>
				<label for="userId"></label>
				<input type="text" id = "userId" name="userId">
			</div>
			<div>
				<label for="userPass"></label>
				<input type="password" id = "userPass" name="userPass">
			</div>
			<div id="loginCheck">
				<div>
					<button type="submit">로그인</button>
				</div>
			</div>
			<button type="button" id = "join">회원가입</button>
		</c:if>
		
		<!-- # 로그인 한 사용자 -->
		<c:if test="${member != null }">
			<div>
				<p>${member.userId }님 환영합니다.</p>
				<button id = "logoutBtn" type="button">로그아웃</button>
			</div>
		</c:if>
	</form>
</body>
</html>