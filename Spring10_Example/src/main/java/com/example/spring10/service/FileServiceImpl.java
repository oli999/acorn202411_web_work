package com.example.spring10.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;
import com.example.spring10.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
	
	//파일을 저장할 위치 
	@Value("${file.location}")
	private String fileLocation;
	
	@Autowired private FileDao dao;

	@Override
	public void saveFile(FileDto dto) {
		// FileDto 객체에서 MultipartFile 객체를 얻어낸다.
		MultipartFile myFile=dto.getMyFile();
		
		//만일 파일이 업로드 되지 않았다면
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다.");
		}
		
		//원본 파일명 
		String orgFileName = myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 문자열로 얻어내기
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath=fileLocation + File.separator + saveFileName;
		try {
			//업로드된 파일을 저장할 파일 객체 생성
			File saveFile=new File(filePath);
			myFile.transferTo(saveFile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//업로더
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//FileDto 에 추가 정보를 담는다. 
		dto.setUploader(userName);
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		//dao 를 이용해서 DB 에 저장하기
		dao.insert(dto);
	}

	@Override
	public void updateFile(FileDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteFile(long num) {
		dao.delete(num);
	}

	@Override
	public List<FileDto> getFiles() {
		
		return dao.getList();
	}
	
	
}
