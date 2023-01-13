package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rd;
	
	public List<RboardVo> list() {
		return rd.getList();
	}
	
	public RboardVo read(int no) {
		rd.getPost(no);
		rd.read(no);
		return rd.getPost(no);
	}
	
	public RboardVo post(int no) {
		return rd.getPost(no);
	}
	
	public void modify(RboardVo vo) {
		rd.modify(vo);
	}
	
	public void delete(int no) {
		rd.delete(no);
	}
	
	public void write(RboardVo vo) {
		rd.write(vo);
	}
	
	public void reply(RboardVo vo) {
		rd.reply(vo);
		rd.arrange(vo);
	}
}
