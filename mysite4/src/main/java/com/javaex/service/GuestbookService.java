package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao gd;
	
	
	public List<GuestbookVo> getList() {
		return gd.getList();
	}
	
	
	public void insert(GuestbookVo vo) {
		gd.guestInsert(vo);
	}
	
	
	public GuestbookVo getGuest(GuestbookVo vo) {
		return gd.getGuest(vo.getNo());
	}
	
	
	public void delete(GuestbookVo vo) {
		gd.guestDelete(vo.getNo());	
	}
	
	
	
	/*------------------------------------------------------------------*/
	// ajax 게시판에서 사용
	public GuestbookVo addresult(GuestbookVo vo) {	
		gd.addSelectKey(vo); // 방명록 작성 & no값 가져오기
		return gd.getGuest(vo.getNo()); // no값 이용해서 작성한글 가져오기
	}
	
	public String delGuest(GuestbookVo vo) {
		int count= gd.delGuest(vo);
		
		if(count>0) {
			return "success";	
		}
		else {
			return "fail";
		}
	}
}
