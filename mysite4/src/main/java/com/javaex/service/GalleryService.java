package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao gd;
	
	
	public List<GalleryVo> getList() {
		return gd.getList();
	}

	
	public void restore(MultipartFile file, GalleryVo vo) {
		System.out.println("service/restore()");
		String saveDir= "C:\\javaStudy\\upload";
		
		String orgName= file.getOriginalFilename(); // 원본파일명
		String exName= orgName.substring(orgName.lastIndexOf(".")); // 확장자
		String saveName= System.currentTimeMillis()+UUID.randomUUID().toString()+exName; // 저장파일명
		String filePath= saveDir+"\\"+saveName;
		long fileSize= file.getSize();
		
		GalleryVo gvo= new GalleryVo(vo.getUserNo(), vo.getUserName(), vo.getContent(), 
									 filePath, orgName, saveName, fileSize);

		// 업로드
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
		gd.upload(gvo);
	}
	
	
	public GalleryVo view(int no) {
		return gd.getImage(no);
	}
	
	
	public String delete(int no) {
		int count= gd.delete(no);
		
		if(count>0) {
			return "success";
		}
		else {
			return "fail";
		}
	}
}
