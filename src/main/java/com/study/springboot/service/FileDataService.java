package com.study.springboot.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.FileData;
import com.study.springboot.repository.FileDataRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileDataService {
	
	private final String FOLDER_PATH = "c:\\image\\";
	private final FileDataRepository fileDataRepository;
	
	public String uploadImageSystem(MultipartFile file) throws IOException{
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		FileData fileData = fileDataRepository.save(
				FileData.builder()
						.name(file.getOriginalFilename())
						.type(file.getContentType())
						.filePath(filePath)
						.build()
				);
		file.transferTo(new File(filePath));
		if(filePath != null) {
			return "File Success" + filePath;
		}
		return null;
	}
	
	// get image
	public byte[] downloadImageSystem(Long id) throws IOException{
		FileData fileData = fileDataRepository.findById(id).orElseThrow(RuntimeException::new);
		
		String filePath = fileData.getFilePath();
		return Files.readAllBytes(new File(filePath).toPath());
	}
	
	public List<FileData> findAll(){
		return fileDataRepository.findAll();
	}

}
