<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dto.SessionDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session 에 저장된 정보를 이용해서
	SessionDto sessionDto=(SessionDto)session.getAttribute("sessionDto");
	//DB 에 저장된 회원 정보(UserDto) 를 얻어와서
	UserDto dto=UserDao.getInstance().getData(sessionDto.getNum());
	//회원 정보 수정 폼을 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/update-form.jsp</title>
<style>
	#profileImage{
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	#profileFile{
		display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>회원 정보 수정 양식</h3>
		<form action="${pageContext.request.contextPath }/user/protected/update-profile" 
			method="post" id="myForm" enctype="multipart/form-data">
			
			<input type="file" name="profileFile" id="profileFile" accept="image/*"/>
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" value="<%=dto.getUserName() %>" readonly/>
			</div>	
			<div>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" value="<%=dto.getEmail()%>"/>
			</div>
			<div>
				<label>프로필 이미지</label>
				<div>
					<a href="javascript:" id="profileLink">
						<%if(dto.getProfileImage()==null){ %>
							<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
								<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
								<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
							</svg>
						<%}else{ %>
							<img id="profileImage" src="${pageContext.request.contextPath}/upload/<%=dto.getProfileImage() %>" alt="프로필 이미지" />
						<%} %>
					</a>
				</div>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
		
	</div>
	<script>
		//프로필이미지 출력란에 원래 있었던 html 을 변수에 담아 두었다가 
		const saved=document.querySelector("#profileLink").innerHTML;
		
		//취소 버튼을 누르면 
		document.querySelector("#myForm").addEventListener("reset", ()=>{
			//변수에 담아둔 내용을 이용해서 원상 복구 시킨다.
			document.querySelector("#profileLink").innerHTML=saved;
		});
		
		//링크를 클릭했을때 
		document.querySelector("#profileLink").addEventListener("click", ()=>{
			// input type="file" 요소를 강제 클릭해서 파일 선택 창을 띄운다.
			document.querySelector("#profileFile").click();
		});
		//새로운 이미지가 선택되었을때
		document.querySelector("#profileFile").addEventListener("change", (e)=>{
			console.log("hi")
			//선택된 파일 배열 객체를 얻어낸다.
			const files = e.target.files;
			//만일 파일 데이터가 존재한다면
			if(files.length > 0){
				//파일로 부터 데이터를 읽어들일 객체 생성
				const reader=new FileReader();
				//로딩이 완료(파일데이터를 모드 읽었을때) 되었을때 실행할 함수 등록
				reader.onload=(event)=>{
					//읽은 파일 데이터 얻어내기 
					const data=event.target.result;
					const img=`<img src="\${data}" id="profileImage" alt="프로필이미지">`;
					document.querySelector("#profileLink").innerHTML=img;
				};
				//파일을 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);
			}
		});
	</script>
</body>
</html>












