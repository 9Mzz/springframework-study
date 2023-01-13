package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bd;
	
	
	public List<BoardVo> list() {
		return bd.getList();
	}
	
	
	public BoardVo read(int no) {
		bd.getPost(no); // 특정글 선택
		bd.read(no); // 조회수 +1
		return bd.getPost(no);
	}

	
	public BoardVo post(int no) {
		return bd.getPost(no);
	}
	
	
	public void modify(BoardVo vo) {
		bd.modify(vo);
	}
	
	
	public void delete(int no) {
		bd.delete(no);
	}
	
	
	public void write(BoardVo vo) {
		bd.write(vo);
	}
	
	
	// 리스트 & 페이징
	public Map<String, Object> list2(int crtPage) {
		
		// 페이징 리스트 영역
		// 현재 페이지 처리 (3항 연산자) crtPage 1이상을 제외하고 1로 처리
		int crtPageNo= (crtPage>0) ? crtPage : (crtPage= 1);
		
		int listCnt= 10; // 한 페이지당 글 개수
		int startRnum= (crtPage-1)*listCnt +1; // 시작글 번호
		int endRnum= (startRnum+listCnt) -1; // 마지막글 번호
		
		
		// 페이징 버튼영역
		// 데이터 총개수
		int totalCnt= bd.count();
		
		// 페이지당 버튼 갯수
		int pageBtnCnt= 5;
		
		// 마지막 버튼
		int endBtnNo= (int)(Math.ceil(crtPage/(double)pageBtnCnt))*pageBtnCnt;
		
		// 시작버튼
		int startBtnNo= endBtnNo-(pageBtnCnt-1);
		
		// 다음화살표 유무
		boolean next= false;
		if(endBtnNo*listCnt<totalCnt) {
			next= true;
		}
		else { // 다음화살표가 없을때, 마지막 버튼값을 다시계산
			endBtnNo= (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		// 이전화살표 유무
		boolean prev= false;
		if(startBtnNo!=1) {
			prev= true;
		}
		
		// 데이터 포장
		Map<String, Object> pMap= new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startBtnNo", startBtnNo);
		pMap.put("endBtnNo", endBtnNo);
		pMap.put("next", next);
		pMap.put("bList", bd.getList2(startRnum, endRnum));
		pMap.put("crtPageNo", crtPageNo);

		return pMap;	
	}
}
