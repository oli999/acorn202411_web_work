package com.example.spring11.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring11.entity.Member;

/*
 *  아래와 같이 선언만해도 MemberRepository 인터페이스를 구현한 클래스로 생성된 객체가 
 *  bean 으로 관리 된다.
 */


// extends JpaRepository<Entity type, Entity 에서 아이디 역활을 하는 필드의 type> 
public interface MemberRepository extends JpaRepository<Member, Integer>{
	//번호에 대해서 내림차순 정렬해서 select 하는 메소드 추가
	
	/*
	 *  정렬된 결과를 select 하는 메소드를 custom 으로 추가하기
	 *  
	 *  - 정해진 형식이 있다
	 *  
	 *  findAllByOrderBy칼럼명Desc()
	 *  findAllByOrderBy칼럼명Asc()
	 *  
	 *  칼럼명은 camel case 로 작성 
	 */
	
	public List<Member> findAllByOrderByNumDesc();
	
	/*
	 *  Java Persistence Query Language (JPQL)
	 *  - JPQL은 SQL과 유사하지만 엔티티와 속성에 기반하여 작성되며, 데이터베이스 종속적이지 않음
	 *  - JPQL 만의 문법이 존재한다 
	 *  - Entity 의 name 은 @Entity 어노테이션이 붙어있는 클래스의 이름 혹은 name 속성의 value
	 *  - Entity 의 별칭은 필수 
	 *  - select 된 row 의 정보를 Entity 혹은 Dto 에 담을수 있다. 
	 */
	@Query(value = "SELECT m FROM MEMBER_INFO m ORDER BY m.num DESC")
	public List<Member> getList(); //메소드명은 마음대로 규칙없이 
	
}



















