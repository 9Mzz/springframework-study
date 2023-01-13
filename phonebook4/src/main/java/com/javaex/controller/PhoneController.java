package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao2;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value= "/phone")
public class PhoneController {
	
	@Autowired
	private PhoneDao2 pd; 
	
	
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

		List<PhoneVo> pList= pd.getPersonList();
		System.out.println(pList.toString());
		
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
}
