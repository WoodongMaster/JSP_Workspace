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
	<h1>어서오세요 여기는 속마음게시판입니다!!</h1>

	<c:if test="${ses.id ne null}">
		${ses.id}님이 login 하였습니다. <br>
		<br><br>
	</c:if>

	<a href="/mem/join"><button>회원가입</button></a>
	
	<c:choose>
		<c:when test="${ses.id ne null}">		
		<a href="/mem/logout"><button>로그아웃</button></a><br><br>
		<a href="/brd/register"><button>게시글 작성</button></a><br><br>
	<a href="/mem/modify_rdy"><button>회원정보수정</button></a><br><br>
	<a href="/mem/list"><button>회원리스트 보기</button></a>
		</c:when>
		<c:otherwise>
		<a href="/mem/login"><button>로그인</button></a><br><br>
		</c:otherwise>
	</c:choose>
	<a href="/brd/list"><button>게시글보기</button></a>



	<!-- /mem/을 붙였다 = controller을 거쳐온다 -->
	
</body>
</html>