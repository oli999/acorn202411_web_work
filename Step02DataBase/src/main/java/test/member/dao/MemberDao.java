package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	
	//회원 한명의 정보를 삭제하고 성공 여부를 리턴하는 메소드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql = """
				DELETE FROM member
				WHERE num=?			
			""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setInt(1, num);
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//회원 한명의 정보를 추가하고 성공 여부를 리턴하는 메소드
	public boolean insert(MemberDto dto) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		try {
			conn=new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql="""
				INSERT INTO member
				(num, name, addr)
				VALUES(member_seq.NEXTVAL, ?, ?)
			""";
			pstmt=conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}
		}
		if(rowCount > 0) {
			return true;
		}else {
			return false;
		}
	}// insert()
	
	//회원 목록을 리턴하는 메소드 
	public List<MemberDto> getList(){
		List<MemberDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn=new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql="""
				SELECT num, name, addr
				FROM member
				ORDER BY num ASC
			""";
			pstmt=conn.prepareStatement(sql);
			// ? 에 값 바인딩할게 있으면 여기서 하기
			
			//sql 문 실행하고 결과를 ResultSet 객체로 리턴받기
			rs=pstmt.executeQuery();
			while(rs.next()) {
				// 커서가 위치한 곳의 칼럼 내용을 읽어와서 dto 에 저장
				MemberDto dto=new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//회원 한명의 정보가 담긴 dto 를 List 객체에 누적 시키기
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
				if (conn != null)conn.close();
			} catch (Exception e) {}
		}
		//List 객체 리턴하기 
		return list;
	}// getList()
	
}// class











