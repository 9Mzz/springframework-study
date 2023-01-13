package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao2;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/guest")
public class GuestbookController {
	
	@Autowired
	private GuestbookDao2 gd;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<GuestbookVo> gbList= gd.getList();
		model.addAttribute("gl", gbList);
		
		return "list";
	}
	
	
	@RequestMapping("/write")
	public String write(@ModelAttribute GuestbookVo vo) {
		gd.guestInsert(vo);
		
		return "redirect:/guest/list";
	}
	
	
	@RequestMapping("/deleteForm")
	public String deleteForm() {
		return "deleteForm";
	}
	
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		GuestbookVo vo= gd.getGuest(no);
		
		if(vo.getPassword().equals(password)) {
			System.out.println("password correct");
			gd.guestDelete(no);
		}
		else {
			System.out.println("password incorrect");
		}

		return "redirect:/guest/list";
	}
	
}
