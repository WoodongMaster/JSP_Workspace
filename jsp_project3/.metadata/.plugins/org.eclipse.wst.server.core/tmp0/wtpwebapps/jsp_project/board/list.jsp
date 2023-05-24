<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트 페이지</title>
</head>
<body>
	<h1>Board List Page</h1>
	<%-- 게시글 검색 --%>
	<form action="/brd/page" method="post" >
		<div align="center">
			<c:set value="${pgh.pgvo.type}" var="typed"></c:set>
			<select class="form-select form-select-sm" aria-label="Default select example" name="type" style="width:10%;height:40px;float:left;border-radius:0;">
				<option ${typed == null ? 'selected':'' }>선택</option>
				<option ${typed eq 't' ? 'selected':'' } value="t">제목</option>
				<option ${typed eq 'w' ? 'selected':'' } value="w">작성자</option>				
				<option ${typed eq 'c' ? 'selected':'' } value="c">내용</option>
				<option ${typed eq 'tc' ? 'selected':'' } value="tc">제목 or 내용</option>
				<option ${typed eq 'tw' ? 'selected':'' } value="tw">제목 or 작성자</option>
				<option ${typed eq 'cw' ? 'selected':'' } value="cw">내용 or 작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="찾고자 하는 내용을 입력하세요" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" style="width:30%;height:40px;float:left;border-radius:0;">
			<input type="hidden" name="pageNum" value="${pgh.pgvo.pageNum}">
			<input type="hidden" name="qty" value="${pgh.pgvo.qty}">
			<button type="submit" class="btn btn-success" style="float:left;border-radius:0;height:40px;">검색</button>
		</div>
	</form>
	<table class="table">
		<thead>
			<tr align="center">
				<th scope="col">글번호</th>
				<th scope="col">작성자</th>
				<th scope="col">제목</th>
				<th scope="col">등록일</th>
				<th scope="col">조회수</th>
				<th scope="col">좋아요</th>
			</tr>
		</thead>
		  <tbody class="table-group-divider" align="center">
			<c:forEach items="${list }" var="b">
				<tr>
					<td scope="row">${b.bnum }</td>
					<td>${b.writer }</td>
					<td><a href="/brd/post?bnum=${b.bnum}">${b.title }</a></td>
					<td>${b.write_date }</td>
					<td>${b.count }</td>
					<td>${b.likes }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<div align="center">
	<a href="/"><button align="center">메인화면으로</button></a>
	<br>
	<%-- 페이지네이션 위치 --%>
	<%-- 컨트롤러에서 page 정보를 싣고 와야함. --%>
	<%-- 1 ~ endpage까지 숫자 반복 (forEach) --%>


	<%-- 이전페이지 버튼 --%>
	<c:if test="${pgh.prev}">
		<a href="/brd/page?pageNum=${pgh.startPage-1}&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"><button>◀</button></a>
	</c:if>

	<c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="currentPageNum">
		<a href="/brd/page?pageNum=${currentPageNum}&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${currentPageNum }</a>
	</c:forEach>

	<%-- 다음페이지 버튼 --%>
	<c:if test="${pgh.next}">
		<a href="/brd/page?pageNum=${pgh.endPage+1}&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"><button>▶</button></a>
	</c:if>
	</div>

</body>
</html>