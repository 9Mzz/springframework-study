package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	// 리스트 불러오기
	public List<BoardVo> getList() {
		return sqlSession.selectList("board.getList");
	}
	
	// 특정 게시글 선택
	public BoardVo getPost(int no) {
		return sqlSession.selectOne("board.getPost", no);
	}
	
	// 조회수 +1
	public void read(int no) {
		sqlSession.update("board.read", no);
	}
	
	// 수정
	public void modify(BoardVo vo) {
		sqlSession.update("board.modify", vo);
	}
	
	// 삭제
	public void delete(int no) {
		sqlSession.delete("board.delete", no);
	}
	
	// 글작성
	public void write(BoardVo vo) {
		sqlSession.insert("board.write", vo);
	}
	
	
	// 리스트 가져오기 & 페이징
	public List<BoardVo> getList2(int startRnum, int endRnum) {
		
		Map<String, Integer> map= new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);

		return sqlSession.selectList("board.getList2", map);
	}
	
	// 전체 글갯수 가져오기
	public int count() {
		return sqlSession.selectOne("board.count");
	}
}

