<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기 페이지</title>
</head>
<body>

	<table border="1" align="center">
		<tr>
			<th>글번호</th>
		</tr>
		<tr align="center">
			<td>${post.bnum}</td>
		</tr>
		<tr>
			<th>제목</th>
		</tr>
		<tr align="center">
			<td>${post.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
		</tr>
		<tr align="center">
			<td>${post.writer}</td>
		</tr>
		<tr>
			<th>등록일자</th>
		</tr>
		<tr align="center">
			<td>${post.write_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
		</tr>
		<tr align="center">
			<td>${post.count}</td>
		</tr>
		<tr>
			<th>내용</th>
		</tr>
		<tr align="center">
			<td>${post.post}</td>
		</tr>
		<c:if test="${post.imgfile ne '' && post.imgfile ne null}">		
		<tr>
			<th>이미지</th>
		</tr>
		<tr align="center">
			<td><img alt="없음" src="/_fileUpload/${post.imgfile}"></td>
		</tr>
		</c:if>
	</table>

	<div align="center">
		<c:if test="${post.writer eq sesID}">
			<a href="/brd/modify?bnum=${post.bnum}"><button>수정하기</button></a>
			<a href="/brd/remove?bnum=${post.bnum}&imgName=${post.imgfile}"><button>삭제하기</button></a>
		</c:if>

		<c:choose>
			<c:when test="${not empty sesID}">
				<a href="/brd/like?bnum=${post.bnum}&value=true"><button>좋아요</button></a>
			</c:when>
			<c:otherwise>
				<a href="/brd/like?bnum=${post.bnum}&value=false"><button>좋아요
						취소</button></a>
			</c:otherwise>
		</c:choose>

		<a href="/brd/page"><button>돌아가기</button></a>
	</div>
	<div align="center">
		comment line<br>
		<%-- 댓글 작성 라인 --%>
		<input type="text" id="cmtWriter" value="${ses.id}"
			readonly="readonly"> <input type="text" id="cmtText"
			placeholder="Add Comment">
		<button type="button" id="cmtAddBtn">댓글등록</button>
	</div>
	<br>
	<%-- 댓글 표시 라인 --%>

	<div class="accordion accordion-flush" id="accordionFlushExample">
		<div class="accordion-item">
			<h2 class="accordion-header" id="flush-headingOne">
				<button class="accordion-button collapsed" type="button"
					data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
					aria-expanded="false" aria-controls="flush-collapseOne">
					cno, writer</button>
			</h2>
			<div id="flush-collapseOne" class="accordion-collapse collapse"
				aria-labelledby="flush-headingOne"
				data-bs-parent="#accordionFlushExample">
				<div class="accordion-body">comment</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		const bnumVal = `<c:out value="${post.bnum}"/>`;
	</script>
	<script src="/resources/board_post.js"></script>
	<script type="text/javascript">
		printCommentList(bnumVal);
	</script>
</body>
</html>