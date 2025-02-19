package com.example.spring05.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring05.dto.MemberDto;

// dao 를 bean 으로 만들기 위한 어노테이션 
@Repository
public class MemberDaoImpl implements MemberDao{
	
	// MyBatis 기반으로 DB 관련 작업을 하기 위한 핵심 의존객체를 DI 받는다.
	@Autowired
	private SqlSession session;

	@Override
	public List<MemberDto> getList() {
		/*
		 *  SqlSession 객체를 이용해서 회원 목록을 얻어온다.
		 */
		List<MemberDto> list=session.selectList("member.getList");
		
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		/*
		 *  Mapper 의 namespace : member
		 *  sql 의 id : insert
		 *  parameterType : MemberDto
		 */
		session.insert("member.insert", dto);
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 * Mapper namespace : member
		 * sql id : update
		 * parameterType : MemberDto
		 */
		session.update("member.update", dto);
	}

	@Override
	public void delete(int num) {
		
		session.delete("member.delete", num);
	}

	@Override
	public MemberDto getData(int num) {
		MemberDto dto=session.selectOne("member.getData", num);
		return dto;
	}

}










