package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao ud;
	
	
	public UserVo getUser(UserVo vo) {
		return ud.getUser(vo);
	}
	
	
	public void join(UserVo vo) {
		ud.userInsert(vo);
	}
	
	
	public void modify(UserVo vo) {
		ud.userUpdate(vo);
	}
	
	
	public String checkDup(UserVo vo) {
		int count= ud.checkDup(vo);
		
		if (count>0) {
			return "inuse";
		}
		else {
			return "available";
		}
	}
}
