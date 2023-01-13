package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao2 {
	
	@Autowired
	private SqlSession sqlSession;

	
	// list 가져오기
	public List<GuestbookVo> getList() {	
		return sqlSession.selectList("guestbook.selectList");
	}
	
	// 작성
	public void guestInsert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
	}
	
	// 특정글 선택
	public GuestbookVo getGuest(int no) {
		return sqlSession.selectOne("guestbook.selectOne", no);
	}
	
	// 삭제
	public void guestDelete(int no) {
		sqlSession.delete("guestbook.delete", no);
	}
}
