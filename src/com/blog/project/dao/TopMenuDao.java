package com.blog.project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.project.beans.BoardInfoBean;

@Repository
public class TopMenuDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;//xml에서는 mapper 직접 주입x
	
	public List<BoardInfoBean> getTopMenuList(){
		List<BoardInfoBean> topMenuList = sqlSessionTemplate.selectList("topmenu.get_topmenu_list");
		return topMenuList;
	}
}
