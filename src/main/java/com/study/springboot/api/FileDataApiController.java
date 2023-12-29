package com.study.springboot.api;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.FileData;
import com.study.springboot.service.FileDataService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileDataApiController {

	private final FileDataService fileDataService;
	
//	@PostMapping("/file")
//	@CrossOrigin
//	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
//		String uploadImage = fileDataService.uploadImageSystem(file);
//		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
//	}
	
	@PostMapping("/file")
	@CrossOrigin
	public ResponseEntity<?> uploadImage(@RequestParam("images") List<MultipartFile> files) throws IOException{
		List<String> uploadResult = files.stream().map(file -> {
														try {
															return fileDataService.uploadImageSystem(file);
														} catch (Exception e) {
															e.printStackTrace();
															return "File Upload Failed" + file;
														}
													}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(uploadResult);
	}
	
	@GetMapping("/file/{id}")
	@CrossOrigin
	public ResponseEntity<?> downImage(@PathVariable("id") Long id) throws IOException{
		byte[] downloadImage = fileDataService.downloadImageSystem(id);
		if(downloadImage != null) {
			return ResponseEntity.status(HttpStatus.OK)
								.contentType(MediaType.valueOf("image/png"))
								.body(downloadImage);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/fileList")
	@CrossOrigin
	public ResponseEntity<List<FileData>> getFileListData(){
		List<FileData> fileDataList = fileDataService.findAll();
		if(fileDataList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(fileDataList);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
}
