<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
	 	<title>게시판</title>
	 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</head>
	
	<script type="text/javascript">
		$(document).ready(function () {
			var formObj = $("form[name='readForm']");
			
			/* # 수정 버튼 */
			$(".update_btn").on("click", function () {
				formObj.attr("action","/board/updateView");
				formObj.attr("method","get");
				formObj.submit();
			})
			
			/* # 삭제 버튼 */
			$(".delete_btn").on("click", function () {
				var deleteYN = confirm("삭제하시겠습니까?");
				
				if(deleteYN == true) {
					formObj.attr("action","/board/delete");
					formObj.attr("method","post");
					formObj.submit();
				} else {
					event.returnValue = false;
				}
			})
			
			/* # 목록 버튼 */
			$(".list_btn").on("click", function () {
				location.href = "/boadr/list";
			})
		})
	</script>
	
	<body>
	
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<div>
				<%@include file = "nav.jsp" %>
			</div>
			<hr />
			
			<section id="container">
				<form role="form" method="post" name="readForm">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="bno">글 번호</label><input type="text" id="bno" name="bno" value="${read.bno}" readonly="readonly"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="title">제목</label><input type="text" id="title" name="title" value="${read.title}" readonly="readonly"/>
								</td>
							</tr>	
							<tr>
								<td>
									<label for="content">내용</label>
									<textarea id="content" name="content" readonly="readonly"><c:out value="${read.content}" /></textarea>
								</td>
							</tr>
							<tr>
								<td>
									<label for="writer">작성자</label><input type="text" id="writer" name="writer" value="${read.writer}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td>
									<label for="regdate">작성날짜</label>
									<fmt:formatDate value="${read.regdate}" pattern="yyyy-MM-dd"/>					
								</td>
							</tr>		
						</tbody>			
					</table>
					<div>
						<button type="submit" class="update_btn">수정</button>
						<button type="submit" class="delete_btn">삭제</button>
						<button type="submit" class="list_btn">목록</button>	
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>