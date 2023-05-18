<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body align = "center">
	<h1>Index page</h1>

	<c:if test="${ses.id ne null}">
		${ses.id}님이 login 하였습니다. <br>
		${ses.id}님의 나이는 ${ses.age}입니다. <br>
		계정생성일 : ${ses.reg_date }<br>
		마지막 접속 : ${ses.last_login }<br>
		<br><br>
	</c:if>

	<a href="/mem/join"><button>join</button></a>
	
	<c:choose>
		<c:when test="${ses.id ne null}">		
		<a href="/mem/logout"><button>logout</button></a><br>
		<a href="/brd/register"><button>게시글 작성</button></a>
	<a href="/mem/modify_rdy"><button>회원정보수정</button></a><br>
		</c:when>
		<c:otherwise>
		<a href="/mem/login"><button>login</button></a>
		</c:otherwise>
	</c:choose>
	<a href="/mem/list"><button>회원리스트 보기</button></a>
	<a href="/brd/list"><button>게시글보기</button></a>

	<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}" />`;
	console.log(msg_login);
	if(msg_login === '0'){
		alert('로그인 실패');
	}
	</script>

	<!-- /mem/을 붙였다 = controller을 거쳐온다 -->
	
</body>
</html>