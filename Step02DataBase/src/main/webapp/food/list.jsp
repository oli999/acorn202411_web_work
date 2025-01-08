<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//음식 목록을 FoodDao 객체를 이용해서 얻어온다.
	List<FoodDto> list=new FoodDao().getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/food/list.jsp</title>
</head>
<body>
	<div class="container">
		<a href="insertform.jsp">음식 추가</a>
		<h1>음식 목록</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>유형</th>
					<th>이름</th>
					<th>가격</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<%for(FoodDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getType() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getPrice() %></td>
					<td><a href="updateform.jsp?num=<%=tmp.getNum() %>">수정</a></td>
					<td><a href="javascript: deleteConfirm(<%=tmp.getNum() %>)">삭제</a></td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
	<script>
		function deleteConfirm(num){
			const isDelete = confirm("삭제할까요?");
			//확인을 눌렀을때만 delete.jsp 페이지로 이동하도록 한다.
			if(isDelete){
				// javascript 를 이용해서 페이지 이동 
				location.href = "delete.jsp?num="+num;
			}
		}
	</script>
</body>




</html>











