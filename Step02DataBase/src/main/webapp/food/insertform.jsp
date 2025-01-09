<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insertform.jsp</title>
<jsp:include page="/include/resource.jsp"/>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/food/list.jsp">음식목록</a>
				</li>
				<li class="breadcrumb-item active">음식추가</li>
			</ol>
		</nav>
		<h1>음식 추가 양식</h1>
		<form action="insert.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="type">유형</label>
				<select class="form-control" name="type" id="type">
					<option value="">선택</option>
					<option value="한식">한식</option>
					<option>중식</option>
					<option>양식</option>
					<option>일식</option>
					<option>기타</option>
				</select>
			</div>
			<div class="mb-2">
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name"/>
			</div>
			<div class="mb-2">
				<label class="form-label" for="price">가격</label>
				<input class="form-control" type="number" name="price" id="price" max="100000" min="1000" step="100"/>
			</div>
			<button class="btn btn-outline-success btn-sm" type="submit">등록</button>
		</form>
	</div>
</body>
</html>




