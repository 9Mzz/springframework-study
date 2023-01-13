package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PhoneVo;

@Repository
public class PhoneDao2 {
	
	@Autowired
	private SqlSession sqlSession;
	

	public PhoneDao2() {
		
	}
	
	// 리스트 가져오기
	public List<PhoneVo> getPersonList() {
		List<PhoneVo> pList= sqlSession.selectList("phonebook.selectList");
		return pList;
	}
	
	
	// 전화번호 등록
	public int personInsert(PhoneVo pv) {
		return sqlSession.insert("phonebook.insert", pv);
	}
	
	
	/*
	// Map 사용하기
	// 전화번호 등록
	public int personInsert(PhoneVo pv) {
		System.out.println("PhoneController/write() 파라미터 여러개 받을때");
		
		String name= pv.getName();
		String hp= pv.getHp();
		String company= pv.getCompany();
		
		Map<String, String> personMap= new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap);
		
		
		return sqlSession.insert("phonebook.insert", personMap);
	}
	*/
	
	
	// 전화번호 삭제
	public void personDelete(int id) {
		sqlSession.delete("phonebook.delete", id);	
	}
	
	
	
	// 수정폼(특정 데이터 가져오기)
	public PhoneVo getPerson(int id) {
		return sqlSession.selectOne("phonebook.selectOne", id);
	}
	
	
	/*
	// Map 사용하기
	// 수정폼(특정 데이터 가져오기)
	public PhoneVo getPerson(int id) {
		Map<String, Object> personMap= sqlSession.selectOne("phonebook.selectOne2", id);
		System.out.println(personMap.keySet());
		System.out.println(personMap);
		
		personMap.get("PERSON_ID");
		personMap.get("NAME");
		personMap.get("HP");
		personMap.get("COMPANY");
		
		return null;
	}
	*/
	
	// 수정
	public void personUpdate(PhoneVo pv) {
		sqlSession.update("phonebook.update", pv);
	}
	
	
	
	/*
	
	public List<PhoneVo> personSearch(String search) {
		List<PhoneVo> pList= new ArrayList<PhoneVo>();
		
		this.getConnection();
		
		try {
			String query= "";
			query += " select   person_id, "; 
			query += "          name, ";
			query += "          hp, ";
			query += "          company ";
			query += " from     person ";
			query += " where    (name like ? or hp like ? or company like ?) ";

			pstmt= conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
		    
			rs= pstmt.executeQuery();

            while(rs.next()) {           
            	int personId= rs.getInt("person_id"); 
            	String name= rs.getString("name");
            	String hp= rs.getString("hp");
            	String company= rs.getString("company");
            	
            	PhoneVo vo= new PhoneVo(personId, name, hp, company);
            	pList.add(vo);
            }
            
            for(PhoneVo pv: pList) {
            	pv.showInfo();
            }

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}	
		this.close();
		
		return pList;
	}
	
	*/
}

