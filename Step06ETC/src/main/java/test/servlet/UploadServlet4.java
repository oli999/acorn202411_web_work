package test.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/file/upload4")
@MultipartConfig(
	fileSizeThreshold = 1024*1024*5, // 메모리 임계값
	maxFileSize = 1024*1024*50,  //최대 파일 사이즈
	maxRequestSize = 1024*1024*60 //최대 요청 사이즈
)
public class UploadServlet4 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//업로드될 실제 경로 얻어내기
		String uploadPath=this.getServletContext().getRealPath("/upload");
		
		File uploadDir = new File(uploadPath);
		//만일 upload 폴더가 존재 하지 않으면 
		if(!uploadDir.exists()) {
			uploadDir.mkdir(); //실제로 폴더 만들기
		}
		
		//파일명이 겹치지 않게 저장하기 위한 랜덤한 문자열 얻어내기
		String uid = UUID.randomUUID().toString();
		
		String orgFileName=null;
		String saveFileName=null;
		
		//파일 데이터 처리
		Part filePart = req.getPart("myImage");
		if(filePart != null) {
			//원본 파일의 이름 얻어내기
			orgFileName=filePart.getSubmittedFileName();
			//저장될 파일의 이름 구성하기
			saveFileName=uid+orgFileName;
			//저장할 파일의 경로 구성하기
			String filePath=uploadPath + File.separator + saveFileName;
			/*
			 * 업로드된 파일은 임시 폴더에 임시 파일로 저장이 된다.
			 * 해당 파일에서 byte 알갱이를 읽어 들일수 있는 InputStream 객체를 얻어내서 
			 */
			InputStream is=filePart.getInputStream();
			// 원하는 목적지에 copy 를 해야 한다 
			Files.copy(is,  Paths.get(filePath));
		}
		
		//응답에 필요한 데이터를 request 영역에 담기
		req.setAttribute("saveFileName", saveFileName);
		
		//jsp 페이지로 응답을 위임하기
		RequestDispatcher rd=req.getRequestDispatcher("/file/upload4.jsp");
		rd.forward(req, resp);
	}
}


















