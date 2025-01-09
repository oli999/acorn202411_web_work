<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//MemberDao 객체를 이용해서 회원 목록 얻어오기
	MemberDao dao=new MemberDao();
	List<MemberDto> list=dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="flex-grow-1">
		<%-- 
			navbar.jsp 페이지를 포함 시키고 해당 페이지에 "current" 라는 파라미터 명으로 "member" 전달하기 
			그러면 navbar.jsp 페이지에서는 
			request.getParameter("current")  를 이용해서 "member" 문자열을 얻어낼수 있다.
		--%>
		<jsp:include page="/include/navbar.jsp">
			<jsp:param value="member" name="current"/>
		</jsp:include>
		<div class="container">
			<a href="insertform.jsp">
				<svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-person-add" viewBox="0 0 16 16">
					  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
					  <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
				</svg>
				<span class="visually-hidden">회원추가</span>
			</a>
			<h1>회원 목록입니다.</h1>
			<table class="table table-striped">
				<thead class="table-dark">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>주소</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
				<%for(MemberDto tmp:list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getName() %></td>
						<td><%=tmp.getAddr() %></td>
						<td><a href="updateform.jsp?num=<%=tmp.getNum() %>">수정</a></td>
						<td><a href="delete.jsp?num=<%=tmp.getNum() %>">삭제</a></td>
					</tr>
				<%} %>
				</tbody>
			</table>
		</div>	
	</div>	
	<%-- footer.jsp include --%>
	<jsp:include page="/include/footer.jsp"/>
</body>
</html>






