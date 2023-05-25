<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
</head>
<body>
<h1>Board Register Page</h1>

<form action="/brd/insert" method="post" enctype="multipart/form-data">>
제목 : <input type="text" id = "title" name="title"/><p id="title_text"></p>
작성자 : <input type="text" name="writer" value="${ses.id}" readonly/><br><p></p>
내용 : <textarea rows="3" cols="30" name="post"></textarea><br>
이미지업로드 : <input type="file" id="file" name="imgfile" accept="image/png, image/jpeg, image/jpg, image/bmp, image/gif"><br>
<button type="submit" id="smt" disabled="disabled">완료</button>
</form>
	<script type="text/javascript">
	var regExp = /^[a-zA-Z0-9ㄱ-ㅎ가-힣]{2,50}$/;
	var title = document.getElementById('title');
	var smt = document.getElementById('smt'); 
	title.addEventListener('input', () => {
			
		if(regExp.test(title.value)){
			title.style.borderColor = 'green';
			document.getElementById('title_text').innerHTML = '';
			smt.disabled = false;
		}else{
			title.style.borderColor = 'red';
			document.getElementById('title_text').innerHTML = '최소 2글자는 입력해야합니다';
			smt.disabled = true;
		}
	 });
	</script>
</body>
</html>