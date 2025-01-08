<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>음식 추가 양식</h1>
		<form action="insert.jsp" method="post">
			<div>
				<label for="type">유형</label>
				<select name="type" id="type">
					<option value="">선택</option>
					<option value="한식">한식</option>
					<option>중식</option>
					<option>양식</option>
					<option>일식</option>
					<option>기타</option>
				</select>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name"/>
			</div>
			<div>
				<label for="price">가격</label>
				<input type="number" name="price" id="price" max="100000" min="1000" step="100"/>
			</div>
			<button type="submit">등록</button>
		</form>
	</div>
</body>
</html>




