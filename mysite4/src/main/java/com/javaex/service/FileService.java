package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String restore(MultipartFile file) {
		System.out.println("fileservice/restore()");
		String saveDir= "C:\\javaStudy\\upload";
		
		
		// 파일을 하드디스크에 저장(운영내용)
		// 원본파일이름
		String orgName= file.getOriginalFilename();
		
		
		// 확장자
		String exName= orgName.substring(orgName.lastIndexOf("."));

		
		// 저장파일이름
		String saveName= System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		
		
		// 파일패스 생성
		String filePath= saveDir+"\\"+saveName;
		
		
		// 파일 사이즈
		long fileSize= file.getSize();
		
		
		// 파일 저장(업로드)
		try {
			byte[] fileData= file.getBytes();
			OutputStream out= new FileOutputStream(filePath);
			BufferedOutputStream bout= new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
		
		// db에 저장
		// 과제
		
		return saveName;
	}
}
