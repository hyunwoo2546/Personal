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
$(document).ready(function(){
	
	var formObj = $("form[name='deleteForm']");
	
	/* # 취소 버튼 */
	$(".cancle").on("click", function(){
		
		location.href = "/board/list";
				    
	})

	/* # 회원탈퇴 버튼 */
	$(".submit").on("click", function(){
		/* - 무결성 */
		if($("#userPass").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#userPass").focus();
			return false;
		}
		
		/* - 회원 탈퇴 */
		if($("#userPass").val() == ${member.userPass}){
			if(confirm("회원 탈퇴 하시겠습니까?") == true) {
				formObj.attr("action","/member/memberDelete");
				formObj.attr("method","POST");
				formObj.submit();
				alert("회원 탈퇴 완료 되었습니다.");
			} else {
				alert("취소되었습니다");
			}
		} else {
			alert("비밀번호를 확인해주세요.");
		}
	});
	
	/* # 패스워드 체크 버튼 */
	$(".passChk").on("click", function () {
		/* - 패스워드 체크 ajax */
		$.ajax({
			url : "/member/passChk",
			type : "POST",
			dataType : "json",
			data : $("#delForm").serializeArray(),
			success: function(data){
				
				if(data==0){
					alert("패스워드가 틀렸습니다.");
					return;
				} else {
					alert("비밀번호 일치");
				}
			},
			error:function(err){console.log("err",err)}
		});
	});
	
})
</script>
<body>
		<form action="/member/memberDelete" method="post" id = "delForm" name="deleteForm"> 
			<div class="form-group has-feedback">
				<label for = "userId" class="control-label">아이디</label>
				<input type="text" class="form-control" id = "userId" name = "userId" value="${member.userId }" readonly="readonly" />
			</div>
			<div class="form-group has-feedback">
				<label for = "userPass" class="control-label">패스워드</label>
				<input type="password" class="form-control" id = "userPass" name = "userPass" value="${member.userPass }" /><br/>
				<button class="passChk btn btn-primary" type="button">비밀번호 확인</button>
			</div>
			<div class="form-group has-feedback">
				<label for = "userName" class="control-label">이름</label>
				<input type="text" class="form-control" id = "userName" name = "userName" value="${member.userName }" readonly="readonly"/>
			</div>
			<div class="form-group has-feedback">
				<button class="submit btn btn-success" type="button">회원탈퇴</button>
				<button class="cancle btn btn-danger" type="button">취소</button>
			</div>
		</form>
</body>
</html>