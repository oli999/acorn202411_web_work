<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// GET 방식 파라미터로 전달되는 회원 번호 추출  ( updateform.jsp?num=x )
	int num=Integer.parseInt(request.getParameter("num"));
	// num 에 해당하는 회원 정보를 MemberDao 객체를 이용해서 얻어온다.
	MemberDto dto=new MemberDao().getData(num);
	// 응답한다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원정보 수정 폼</h1>
		<form action="update.jsp" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" name="num" id="num" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="<%=dto.getName() %>"/>
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" value="<%=dto.getAddr() %>"/>
			</div>
			<button type="submit">저장</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>




