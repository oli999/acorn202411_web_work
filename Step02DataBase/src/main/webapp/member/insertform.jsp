<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/member/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 추가폼</h1>
		<form action="${pageContext.request.contextPath }/member/insert.jsp" method="post">
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" placeholder="이름 입력..."/>
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" placeholder="주소 입력..."/>
			</div>
			<button type="submit">저장</button>
		</form>
	</div>
</body>
</html>



