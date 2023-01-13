package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/guest")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	// 방명록 리스트 & 등록
	@RequestMapping("/addList")
	public String addList(Model model) {
		System.out.println("GuestController/addList()");
		
		List<GuestbookVo> gList= guestbookService.getList();
		model.addAttribute("gList", gList);
		
		return "/guestbook/addList";
	}
	
	// 방명록 등록
	@RequestMapping("/insert")
	public String insert(@ModelAttribute GuestbookVo vo) {
		System.out.println("GuestController/insert()");
		
		guestbookService.insert(vo);
		
		return "redirect:/guest/addList";
	}
	
	// 삭제폼
	@RequestMapping("/deleteForm")
	public String deleteForm() {
		System.out.println("/GuestController/deleteForm()");
		
		return "/guestbook/deleteForm";
	}
	
	// 삭제
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo) {
		System.out.println("GuestController/delete()");
		
		GuestbookVo post= guestbookService.getGuest(vo);
		if(post.getPassword().equals(vo.getPassword())) { // 비밀번호 일치
			System.out.println("password correct");
			guestbookService.delete(post);
		}
		else { // 비밀번호 불일치
			System.out.println("password incorrect");
		}
		return "redirect:/guest/addList";
	}
}
