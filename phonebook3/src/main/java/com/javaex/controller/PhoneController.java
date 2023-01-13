package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value= "/phone")
public class PhoneController {
	
	private PhoneDao pd= new PhoneDao();
	
	@RequestMapping(value= "/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController/writeForm()");
		
		return "writeForm";
	}

	
	@RequestMapping(value= "/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute PhoneVo vo) {		
		System.out.println("PhoneController/write()");

		pd.personInsert(vo);
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value= "/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController/list()");
		
		// Dao에서 list 가져오기
		List<PhoneVo> pList= pd.getPersonList();
		
		// Controller --> DispatcherServlet 데이터 보내기 (model)
		model.addAttribute("pl", pList);

		return "list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		System.out.println("PhoneController/delete()");

		pd.personDelete(id);

		return "redirect:/phone/list";
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("id") int id, Model model) {
		System.out.println("PhoneController/updateForm");

		PhoneVo vo= pd.getPerson(id);
		
		model.addAttribute("vo", vo);
		
		return "updateForm";	
	}
	
	
	@RequestMapping("/update")
	public String update(@ModelAttribute PhoneVo vo) {
		System.out.println("PhoneController/update");

		pd.personUpdate(vo);
		
		return "redirect:/phone/list";	
	}
	
	
	/*******************************************************************************/
	
	
	@RequestMapping(value= "/test", method= {RequestMethod.GET, RequestMethod.POST})
	public String test(@RequestParam(value= "name") String name, 
					   @RequestParam(value= "age", required= false, defaultValue= "-1") int age) {
		
		System.out.println(name);
		System.out.println(age);
		
		return "";
	}
	
	@RequestMapping(value= "/view/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String view(@PathVariable("no") int no) {
		
		System.out.println(no+"번 게시글 가져오기");
		
		return "";
	}
	
	/*
	@RequestMapping(value= "/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@RequestParam("name") String name, 
						@RequestParam("hp") String hp, 
						@RequestParam("company") String company) {		
		System.out.println("PhoneController/write()");
		
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		PhoneVo vo= new PhoneVo(name, hp, company);
		
		PhoneDao pd= new PhoneDao();
		pd.personInsert(vo);
		
		return "";
		
	}
	*/
	
}
