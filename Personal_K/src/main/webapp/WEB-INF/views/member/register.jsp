<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
	$(document).ready(function () {
		/* # 취소 버튼 */
		$(".cancle").on("click", function () {
			location.href = "/member/login";
		});
		
		/* # 무결성 체크 */
		$("#joinCheck").on("click", function () {
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
			if($("#userName").val() == "") {
				alert("이름을 입력해주세요.");
				$("#userName").focus();
				return false;
			}
		});
	})
</script>
<body>
	<section id="container">
		<form action="/member/register" method="post">
			<div class="form-group has-feeback">
				<label class="control-label" for="userId">아이디</label>
				<input type="text" class="form-control" id = "userId" name="userId" />
			</div> 
			<div class="form-group has-feeback">
				<label class="control-label" for="userId">패스워드</label>
				<input type="password" class="form-control" id = "userPass" name="userPass" />
			</div> 
			<div class="form-group has-feeback">
				<label class="control-label" for="userId">성명</label>
				<input type="text" class="form-control" id = "userName" name="userName" />
			</div> 
			<div class="form-group has-feeback">
				<button class="btn btn-success" type="submit" id="joinCheck">회원가입</button>
				<button class="cancle btn btn-danger" type="button">취소</button>
			</div> 
		</form>
	</section>
</body>
</html>