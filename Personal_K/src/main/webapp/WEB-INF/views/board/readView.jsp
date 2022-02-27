<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
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
				event.preventDefault();
				location.href = "/board/list?page=${scri.page}"
						+ "&perPageNum=${scri.perPageNum}"
						+ "&searchType=${scri.searchType}"
						+ "&keyword=${scri.keyword}";
			})
			
			/* # 댓글 작성 버튼 */
			$(".replyWriteBtn").on("click", function () {
				var formObj = $("form[name='replyForm']");
				formObj.attr("action","/board/replyWrite")
				formObj.submit();
			})
			
			/* # 댓글 수정 VIEW (GET) */
			$(".replyUpdateBtn").on("click", function () {
				location.href = "/board/replyUpdateView?bno=${read.bno}"
								+ "&page=${scri.page}"		
								+ "&perPageNum=${scri.perPageNum}"		
								+ "&searchType=${scri.searchType}"		
								+ "&keyword=${scri.keyword}"
								+ "&rno=" +$(this).attr("data-rno");
			})
			
			/* # 댓글 삭제 VIEW (GET) */
			$(".replyDeleteBtn").on("click", function () {
				location.href = "/board/replyDeleteView?bno=${read.bno}"
								+ "&page=${scri.page}"		
								+ "&perPageNum=${scri.perPageNum}"		
								+ "&searchType=${scri.searchType}"		
								+ "&keyword=${scri.keyword}"
								+ "&rno=" +$(this).attr("data-rno");
			})
		})
	</script>
	
	<body>
	
		<div id="container">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<div>
				<%@include file = "nav.jsp" %>
			</div>
			<hr />
			
			<section class="container">
				<form role="form" method="post" name="readForm">
					<input type="hidden" id = "bno" name="bno" value="${read.bno }">
					<input type="hidden" id = "page" name="page" value="${scri.page }">
					<input type="hidden" id = "perPageNum" name="perPageNum" value="${scri.perPageNum }">
					<input type="hidden" id = "searchType" name="searchType" value="${scri.searchType }">
					<input type="hidden" id = "keyword" name="keyword" value="${scri.keyword }">
				</form>
				<div class="form-group">
					<label for="bno" class="col-sm-2 control-label">글 번호</label>
					<input type="text" id="bno" name="bno" value="${read.bno}" readonly="readonly" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="title" class="col-sm-2 control-label">제목</label>
					<input type="text" id="title" name="title" value="${read.title}" readonly="readonly" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="content" class="com-sm-2 control-label">내용</label>
					<textarea id="content" name="content" readonly="readonly" class="form-control"><c:out value="${read.content}" /></textarea>
				</div>
				<div class="form-group">
					<label for="writer" class="col-sm-2 control-label">작성자</label>
					<input type="text" id="writer" name="writer" value="${read.writer}" readonly="readonly" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="regdate" class="col-sm-2 control-label">작성날짜</label>
					<fmt:formatDate value="${read.regdate}" pattern="yyyy-MM-dd"/>
				</div>		
							
				<div class="form-group pull-right">
					<button type="submit" class="update_btn btn btn-warning">수정</button>
					<button type="submit" class="delete_btn btn btn-danger">삭제</button>
					<button type="submit" class="list_btn btn btn-primary">목록</button>	
				</div>
				
				<!-- # 댓글 수정/삭제 -->
				<div id="reply">
					<ol class="replyList">
						<c:forEach items="${replyList}" var="replyList">
							<li>
								<div class="form-group">
								작성자 : <input type="text" id="writer" name="writer" value="${replyList.writer}" readonly="readonly" class="form-control"/>
								</div>
								<div class="form-group">
								작성 내용 : <input type="text" id="content" name="content" value="${replyList.content}" readonly="readonly" class="form-control"/>
								</div>
								<div class="form-group">
								작성 날짜 : <input type="text" id="content" name="content" value="${replyList.regdate}" readonly="readonly" class="form-control"/>
								</div>
								  
								<div>
									<button type="button" class="replyUpdateBtn btn btn-warning" data-rno="${replyList.rno}">수정</button>
									<button type="button" class="replyDeleteBtn btn btn-danger" data-rno="${replyList.rno}">삭제</button>
								</div>
							</li>
						</c:forEach>   
					</ol>
				</div>
				<br/><br/>
				<!-- # 댓글 폼 -->
				<form name = "replyForm" method="post" class="form-horizontal">
					<input type="hidden" id = "bno" name = "bno" value="${read.bno }">
					<input type="hidden" id = "page" name = "page" value="${scri.page }">
					<input type="hidden" id = "perPageNum" name = "perPageNum" value="${scri.perPageNum }">
					<input type="hidden" id = "searchType" name = "searchType" value="${scri.searchType }">
					<input type="hidden" id = "keyword" name = "keyword" value="${scri.keyword }">
					
					<div class="form-group">
						<label for="writer" class="col-sm-2 control-label">댓글 작성자</label>
						<div class="col-sm-10">
							<input type="text" id = "writer" name="writer" class="form-control">
						</div>
						<br/>
						<label for="writer" class="col-sm-2 control-label">댓글 내용</label>
						<div class="col-sm-10">
							<input type="text" id = "content" name="content" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="replyWriteBtn btn btn-success pull-right">작성</button>
						</div>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>