<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//수정할 음식번호를 읽어온다. 
	int num=Integer.parseInt(request.getParameter("num"));
	//수정할 음식의 정보를 DB 에서 읽어온다.
	FoodDto dto=new FoodDao().getData(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>음식 수정 양식</h1>
		
		<form action="update.jsp" method="post">
			<!-- 화면에 보이지는 않지만 폼 제출할때 같이 전송되는 값 -->
			<input type="hidden" name="num" value="<%=dto.getNum() %>"/>
			<div>
				<label for="type">유형</label>
				<select name="type" id="type">
					<option value="">선택</option>
					<option <%=dto.getType().equals("한식") ? "selected":"" %>>한식</option>
					<option <%=dto.getType().equals("중식") ? "selected":"" %>>중식</option>
					<option <%=dto.getType().equals("양식") ? "selected":"" %>>양식</option>
					<option <%=dto.getType().equals("일식") ? "selected":"" %>>일식</option>
					<option <%=dto.getType().equals("기타") ? "selected":"" %>>기타</option>
				</select>
			</div>
			<div>
				<label for="name">음식이름</label>
				<input type="text" name="name" id="name" value="<%=dto.getName() %>"/>
			</div>
			<div>
				<label for="price">가격</label>
				<input type="number" name="price" id="price" max="100000" min="1000" step="100" value="<%=dto.getPrice() %>"/>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>





