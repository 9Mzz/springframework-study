package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 리스트 불러오기
	public List<RboardVo> getList() {
		return sqlSession.selectList("rboard.getList");
	}
	
	// 특정 게시글 선택
	public RboardVo getPost(int no) {
		return sqlSession.selectOne("rboard.getPost", no);
	}
	
	// 조회수 +1
	public void read(int no) {
		sqlSession.update("rboard.read", no);
	}
	
	// 수정
	public void modify(RboardVo vo) {
		sqlSession.update("rboard.modify", vo);
	}
	
	// 삭제
	public void delete(int no) {
		sqlSession.delete("rboard.delete", no);
	}
	
	// 글작성
	public void write(RboardVo vo) {
		sqlSession.insert("rboard.write", vo);
	}
	
	// 댓글달기
	public void reply(RboardVo vo) {
		sqlSession.insert("rboard.reply", vo);
	}
	
	// 댓글 순서조정
	public void arrange(RboardVo vo) {
		sqlSession.update("rboard.arrange", vo);
	}
	
}
