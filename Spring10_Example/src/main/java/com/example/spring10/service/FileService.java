package com.example.spring10.service;

import java.util.List;

import com.example.spring10.dto.FileDto;

public interface FileService {
	public void saveFile(FileDto dto);
	public void updateFile(FileDto dto);
	public void deleteFile(long num);
	public List<FileDto> getFiles();
}
