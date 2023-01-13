package com.javaex.dao;

import java.util.List;

import com.javaex.vo.PhoneVo;

public class TestDao {

	public static void main(String[] args) {
		
		PhoneDao pd= new PhoneDao();
		
		List<PhoneVo> pList= pd.getPersonList();
		
		System.out.println(pList.toString());

	}
}
