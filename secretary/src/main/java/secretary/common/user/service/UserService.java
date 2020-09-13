package secretary.common.user.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import secretary.common.user.model.CustomUserDetails;

@Service
public class UserService { 
	
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	 @Autowired
	private SqlSession sqlSession;
	
	public void insertUser(final CustomUserDetails userDetails) { 
		//signupForm.setPw(passwordEncoder.encode(signupForm.getPw())); 
		// 암호화
		//userDAO.insert(signupForm);
		sqlSession.insert("seckim.test.insertUser", userDetails);
	}
	
	public CustomUserDetails findBy(String userId) {
		CustomUserDetails userDetails1 = sqlSession.selectOne("seckim.test.selectUsername", userId);
				
		return sqlSession.selectOne("seckim.test.selectUsername", userId) ;
	}
}
