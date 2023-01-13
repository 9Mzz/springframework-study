package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/*
	// 게시글 리스트
	@RequestMapping("/list2")
	public String list(Model model) {
		System.out.println("boardController/list()");
		
		model.addAttribute("bList", boardService.list());
		return "/board/list";
	}
	*/
	
	// 게시글 리스트 & 페이징
	@RequestMapping("/list")
	public String list2(@RequestParam(value="crtPage", required= false, defaultValue= "1") int crtPage, Model model) {
		System.out.println("boardController/list()");
		
		// 해당페이지(crtPage)의 리스트 10개
		model.addAttribute("pMap", boardService.list2(crtPage));
		
		return "/board/list";
	}
	
	
	// 게시글 읽기
	@RequestMapping("/read")
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("boardController/read()");

		model.addAttribute("post", boardService.read(no));
		return "/board/read";
	}
	
	
	// 게시글 수정폼
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("boardController/modifyForm()");

		model.addAttribute("post", boardService.post(no));		
		return "/board/modifyForm";
	}
	
	
	// 게시글 수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo vo) {
		System.out.println("boardController/modify()");
		
		boardService.modify(vo);
		return "redirect:/board/list";
	}
	
	
	// 게시글 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("boardController/delete()");
		
		boardService.delete(no);
		return "redirect:/board/list";
	}
	
	
	// 글쓰기폼
	@RequestMapping("/writeForm")
	public String writeForm() {
		System.out.println("boardController/writeForm()");
		
		return "/board/writeForm";
	}
	
	
	// 글쓰기
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo vo) {
		System.out.println("boardController/write()");
		
		boardService.write(vo);
		return "redirect:/board/list";
	}
	
}
