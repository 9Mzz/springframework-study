package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guest")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println("apiController/addList()");
		
		return "/aguestbook/addList";
	}
	
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("apiController/list()");
		
		return guestbookService.getList();
	}

	
	@ResponseBody
	@RequestMapping("/add")
	public GuestbookVo add(@ModelAttribute GuestbookVo vo) {
		System.out.println("apiController/add()");

		// 방명록 작성 & 작성한 글 가져오기
		return guestbookService.addresult(vo);
	}

	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo) {
		System.out.println("apiController/delete()");

		// "success" "fail"
		return guestbookService.delGuest(vo);
	}
	
	
	// 방명록 작성 (json 방식)
	@ResponseBody
	@RequestMapping("/add2")
	public GuestbookVo add2(@RequestBody GuestbookVo vo) {
		System.out.println("apiController/add2()");

		return guestbookService.addresult(vo);	
	}	
}
