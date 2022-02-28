<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>회원탈퇴</title>
</head>
<script type="text/javascript">
	$(document).ready(function () {
		/* # 취소 버튼 */
		$(".cancle").on("click", function () {
			location.href = "/board/list";
		});
		
		/* # 회원탈퇴 버튼 */
		$("#submit").on("click", function () {
			if($("#userPass").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#userPass").focus();
				return false;
			}
			if($("#userPass").val() == ${member.userPass}){
				alert("회원탈퇴가 완료되었습니다.");
			}
		});
	})
</script>
<body>
	<section id = "container">
		<form action="/member/memberDelete" method="post">
			<div class="form-group has-feedback">
				<label for = "userId" class="control-label">아이디</label>
				<input type="text" class="form-control" id = "userId" name = "userId" value="${member.userId }" readonly="readonly" />
			</div>
			<div class="form-group has-feedback">
				<label for = "userPass" class="control-label">패스워드</label>
				<input type="password" class="form-control" id = "userPass" name = "userPass" />
			</div>
			<div class="form-group has-feedback">
				<label for = "userName" class="control-label">이름</label>
				<input type="text" class="form-control" id = "userName" name = "userName" value="${member.userName }" readonly="readonly"/>
			</div>
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
				<button class="cancle btn btn-danger" type="button">취소</button>
			</div>
			<div>
				<c:if test="${msg == false }">
					<script type="text/javascript">
						alert("비밀번호가 맞지 않습니다.");
					</script>
				</c:if>
			</div>
		</form>
	</section>
</body>
</html>