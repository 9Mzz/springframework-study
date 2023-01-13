package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;


@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act= request.getParameter("action");
		
		
		// System.out.println("PhonebookController");
		
		if("list".equals(act)) {
			// System.out.println("action=list");
			PhoneDao pDao= new PhoneDao();
			List<PhoneVo> pList= pDao.getPersonList();
		
			// System.out.println(pList);
		
			// html 과 list 섞어서 표현해야함
			// servlet 으로는 표현이 복잡함 --> jsp 이용한다
		
			request.setAttribute("pl", pList);
		
			// *포워드*
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
	
		}
		// 전화번호 등록폼
		else if("writeForm".equals(act)) {
			// System.out.println("전화번호 등록폼");
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
		}
		// 등록
		else if("enroll".equals(act)) {
			// System.out.println("등록");
			
			//파라미터 3개의 꺼내온다
			String name= request.getParameter("name");
			String hp= request.getParameter("hp");
			String company= request.getParameter("company");
			
			//vo로만든다
			PhoneVo pv= new PhoneVo(name, hp, company);
			//System.out.println(personVo);
			
			//dao 메모리 올린다.
			PhoneDao pDao= new PhoneDao();
			
			//dao.insert(vo);
			pDao.personInsert(pv);
			
			//리다이렉트      (포워드X)
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}
		// 전화번호 수정폼
		else if("updateForm".equals(act)) {
			// System.out.println("전화번호 수정폼");
			
			PhoneDao pDao= new PhoneDao();
			
			int id= Integer.parseInt(request.getParameter("id"));
			PhoneVo pv= pDao.getPerson(id);
			request.setAttribute("pv", pv);
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);		
		}
		// 수정
		else if("update".equals(act)) {
			// System.out.println("수정");
			
			PhoneDao pDao= new PhoneDao();
			
			String name= request.getParameter("name");
			String hp= request.getParameter("hp");
			String company= request.getParameter("company");
			int id= Integer.parseInt(request.getParameter("id"));
			
			PhoneVo pv= new PhoneVo(id, name, hp, company);
			pDao.personUpdate(pv);
			
			response.sendRedirect("/phonebook2/pbc?action=list");
		}
		// 삭제
		else if("delete".equals(act) ) {
			// System.out.println("삭제");
			
			PhoneDao pDao= new PhoneDao();
			
			int id= Integer.parseInt(request.getParameter("id"));
			pDao.personDelete(id);
			
			response.sendRedirect("/phonebook2/pbc?action=list");	
		}
		
		else {
			System.out.println("error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
