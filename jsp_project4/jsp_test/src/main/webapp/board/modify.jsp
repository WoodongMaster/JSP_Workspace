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
<title>Board Modify Page</title>
</head>
<body>
<h1>게시글 수정 페이지 입니다.</h1>
<form action="/brd/edit" method="post" enctype="multipart/form-data">
	<table class="table" align="center" style="width:50%">
		<tr class="table-primary">
			<th>글번호</th>
		</tr>
		<tr align="center" >
			<td><input name="bnum" value="${post.bnum}" readonly="readonly" style="border:0px;width:100%;height:100%;text-align:center"></td>
		</tr>
		<tr class="table-primary">
			<th>제목</th>
		</tr>
		<tr align="center">
			<td><input type="text" name="title" class="form-control" id="exampleFormControlInput1" value="${post.title }"></td>
		</tr>
		<tr class="table-primary">
			<th>작성자</th>
		</tr>
		<tr align="center">
			<td><input name="writer" value="${post.writer}" readonly="readonly" style="border:0px;width:100%;height:100%;text-align:center"></td>
		</tr>
		<tr class="table-primary">
			<th>등록일자</th>
		</tr>
		<tr align="center">
			<td><input name="write_date" value="${post.write_date}" readonly="readonly" style="border:0px;width:100%;height:100%;text-align:center"></td>
		</tr>
		<tr class="table-primary">
			<th>조회수</th>
		</tr>
		<tr align="center">
			<td><input name="count" value="${post.count}" readonly="readonly" style="border:0px;width:100%;height:100%;text-align:center"></td>
		</tr>
		<tr class="table-primary">
			<th>내용</th>
		</tr>
		<tr align="center">
			<td><div class="mb-3">
  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="post">${post.post}</textarea>
</div></td>
		</tr>
		
		<c:if test="${post.imgfile ne '' && post.imgfile ne null}">		
		<tr class="table-primary">
			<th>이미지</th>
		</tr>
		<tr align="center">
			<td><img alt="없음" src="/_fileUpload/${post.imgfile}" style="border:0px;width:100%;height:100%;text-align:center"></td>
		</tr>
		</c:if>
	</table>
	<div align="center">
	이미지 : 
	<input type="hidden" name="imgfile" value="${post.imgfile}">
	<input type="file" name="new_imgfile" onchange="setThumbnail(event)" accept="image/png, image/jpeg, image/jpg, image/bmp, image/gif">
	</div>
	<div id="image_container" align="center"></div>
	<br>
	<div align="center">
	<button type="submit" class="btn btn-success" align="center">수정완료</button>
	<a href="/"><button class="btn btn-secondary">수정취소</button></a>
	</div>
	</form>
	
	<script type="text/javascript">
    function setThumbnail(event) {
       	document.getElementById("image_container").innerHTML = "";
        var reader = new FileReader();

        reader.onload = function(event) {
          var img = document.createElement("img");
          img.setAttribute("src", event.target.result);
          img.style.width = "75px";
          img.style.height = "75px";
          document.querySelector("div#image_container").appendChild(img);
        };

        reader.readAsDataURL(event.target.files[0]);
      }
	</script>
</body>
</html>