<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Board Register Page</title>
</head>
<body>
	<h1>게시글 등록 페이지 입니다.</h1>

	<div align="center">
		<form action="/brd/insert" method="post" style="width: 50%;" enctype="multipart/form-data">
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">제목</span> <input
					type="text" class="form-control" placeholder="title"
					aria-label="Username" aria-describedby="basic-addon1" name="title">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text" id="basic-addon1">작성자</span> <input
					type="text" class="form-control" placeholder="writer"
					aria-label="Username" aria-describedby="basic-addon1" name="writer"
					value="${ses.id }" disabled="disabled"> <input
					type="hidden" name="writer" value="${ses.id }">
			</div>
			<div class="input-group">
				<span class="input-group-text">내용</span>
				<textarea class="form-control" aria-label="With textarea"
					name="post" placeholder="write here" style="height: 200px"></textarea>
			</div>

			<div class="input-group mb-3">
				<input type="file" class="form-control" id="file" name="imgfile"
					accept="image/png, image/jpeg, image/jpg, image/bmp, image/gif"
					onchange="setThumbnail(event)">
			</div>
			<br>
			<button type="submit" class="btn btn-success">완료</button>
			<a href="/"><button type="button" class="btn btn-danger">취소</button></a>
		</form>
	</div>

</body>
</html>