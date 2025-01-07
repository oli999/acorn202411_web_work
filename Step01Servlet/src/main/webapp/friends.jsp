<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//DB 에서 읽어온 친구 목록이라고 가정하자
	List<String> names=new ArrayList<>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/friends.jsp</title>
</head>
<body>
	<h1>친구 목록</h1>
	<ul>
		<%for(String tmp:names){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
</body>
</html>


