package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 리스트 불러오기
	public List<GuestbookVo> getList() {	
		return sqlSession.selectList("guest.getList");	
	}
	
	// 방명록작성
	public void guestInsert(GuestbookVo vo) {
		sqlSession.insert("guest.guestInsert", vo);		
	}
	
	// 특정글 선택
	public GuestbookVo getGuest(int no) {
		return sqlSession.selectOne("guest.getGuest", no);
	}
	
	// 삭제
	public void guestDelete(int no) {
		sqlSession.delete("guest.guestDelete", no);
	}
	
	/*********************************************************************/
	// ajax 게시판에서 사용
	// 방명록작성 & no값 가져오기
	public void addSelectKey(GuestbookVo vo) {
		sqlSession.insert("guest.addSelect", vo);
	}
	
	public int delGuest(GuestbookVo vo) {
		return sqlSession.delete("guest.delGuest", vo);
	}
}
