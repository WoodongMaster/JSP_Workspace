<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지</title>
</head>
<body>
<form action="/brd/edit" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>글번호</th>
		</tr>
		<tr align="center">
			<td><input name="bnum" value="${post.bnum}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>제목</th>
		</tr>
		<tr align="center">
			<td><input name="title" value="${post.title}"></td>
		</tr>
		<tr>
			<th>작성자</th>
		</tr>
		<tr align="center">
			<td><input name="writer" value="${post.writer}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>등록일자</th>
		</tr>
		<tr align="center">
			<td><input name="write_date" value="${post.write_date}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>조회수</th>
		</tr>
		<tr align="center">
			<td><input name="count" value="${post.count}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>내용</th>
		</tr>
		<tr align="center">
			<td><input name="post" value="${post.post}"></td>
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
	이미지업로드 : 
	<input type="hidden" name="imgfile" value="${post.imgfile}">
	<input type="file" name="new_imgfile" onchange="setThumbnail(event)">
	<div id="image_container"></div>
	<button type="submit">수정완료</button><br>
	<a href="/"><button>수정취소</button></a>
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