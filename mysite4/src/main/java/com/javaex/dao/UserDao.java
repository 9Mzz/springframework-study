package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	

	// 특정유저 정보 가져오기
	public UserVo getUser(UserVo vo) {	
		return sqlSession.selectOne("user.getUser", vo);	
	}
	
	
	// 회원가입
	public void userInsert(UserVo vo) {
		sqlSession.insert("user.userInsert", vo);	
	}
	
	
	// 회원정보 수정
	public void userUpdate(UserVo vo) {
		sqlSession.update("user.userUpdate", vo);
	}
	
	
	// 아이디 중복체크
	public int checkDup(UserVo vo) {
		return sqlSession.selectOne("user.checkDup", vo);
	}
}
