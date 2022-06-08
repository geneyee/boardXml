package com.blog.project.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.project.beans.UserBean;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//아이디 중복 확인
	public String checkUserIdExist(String user_id) {
		return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
	}
	
	//회원가입 정보 저장
	public void addUserInfo(UserBean joinUserBean) {
		sqlSessionTemplate.insert("user.addUserInfo", joinUserBean);
	}
	
	//로그인
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		return sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
	}
	
	//정보수정
	public UserBean getModifyUserInfo(int user_idx) {
		return sqlSessionTemplate.selectOne("user.getModifyUserInfo", user_idx);
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		sqlSessionTemplate.update("user.modifyUserInfo", modifyUserBean);
	}
}






