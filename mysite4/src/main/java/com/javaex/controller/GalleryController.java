package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	// 리스트 불러오기
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("galleryController/list()");
		
		model.addAttribute("gList", galleryService.getList());
		return "/gallery/list";
	}
	
	// 이미지 업로드
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, 
						 @ModelAttribute GalleryVo vo,
						 Model model) {
		System.out.println("galleryController/upload()");
	
		galleryService.restore(file, vo);
		return "redirect:/gallery/list";
	}
	
	
	// 이미지 보기
	@ResponseBody
	@RequestMapping("/view")
	public GalleryVo view(@RequestParam("no") int no) {
		System.out.println("galleryController/view()");
		
		return galleryService.view(no);
	}
	
	
	// 이미지 삭제
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("galleryController/delete()");
		
		return galleryService.delete(no);
	}

}
