package com.example.spring10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.UserDto;
import com.example.spring10.exception.PasswordException;
import com.example.spring10.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	// SecurityConfig 클래스에 @Bean 설정으로 bean 이된 PasswordEncoder 객체 주입 받기
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public UserDto getByNum(long num) {
		
		return dao.getData(num);
	}

	@Override
	public UserDto getByUserName(String userName) {
		
		return dao.getData(userName);
	}

	@Override
	public void createUser(UserDto dto) {
		//저장할때 비밀번호를 암호화 한 후에 저장 되도록 한다.
		String encodedPwd = encoder.encode(dto.getPassword());
		//인코딩된 비밀번호를 다시 dto 객체에 넣어주고 
		dto.setPassword(encodedPwd);
		//DB 에 저장한다 
		int rowCount = dao.insert(dto);
		if(rowCount == 0) {
			throw new RuntimeException("회원정보 저장 실패");
		}
	}

	@Override
	public void updateUserInfo(UserDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(UserDto dto) {
		//1. 로그인된 userName 을 얻어낸다.
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//2. 기존의 비밀번호를 DB 에서 읽어와서 (암호화된 비밀번호)
		String encodedPwd=dao.getData(userName).getPassword();
		//3. 입력한(암호화 되지 않은 구비밀번호) 와 일치하는지 비교 해서
		// .checkpw( 암호화되지않은 비밀번호, 암호화된 비밀번호)
		boolean isValid = BCrypt.checkpw(dto.getPassword(), encodedPwd);
		//4. 만일 일치하지 않으면 Exception 을 발생 시킨다.
		if(!isValid) {
			throw new PasswordException("기존 비밀번호가 일치하지 않습니다!");
		}
		//5. 일치하면 새비밀번호를 암호화해서 dto 에 담은 다음
		dto.setNewPassword(encoder.encode(dto.getNewPassword()));
		//6. userName 도 dto 에담고 
		dto.setUserName(userName);
		//7. DB 에 비밀번호 수정반영를 한다.
		dao.updatePassword(dto);
		
	}
}









