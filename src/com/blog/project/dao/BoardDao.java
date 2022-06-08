package com.blog.project.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.project.beans.ContentBean;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//글 저장
	public void addContentInfo(ContentBean writeContentBean) {
		sqlSessionTemplate.insert("board.addContentInfo", writeContentBean);
	}
	
	//글 목록 - 게시판 이름
	public String getBoardInfoName(int board_info_idx) {
		return sqlSessionTemplate.selectOne("board.getBoardInfoName", board_info_idx);
	}
	
	//글 목록 보기
	public List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds){
		return sqlSessionTemplate.selectList("board.getContentList", board_info_idx, rowBounds);
	}
	
	//글 읽기
	public ContentBean getContentInfo(int content_idx) {
		return sqlSessionTemplate.selectOne("board.getContentInfo", content_idx);
	}
	
	//글 수정
	public void modifyContentInfo(ContentBean modifyContentBean) {
		sqlSessionTemplate.selectOne("board.modifyContentInfo", modifyContentBean);
	}
	
	//글 삭제
	public void deleteContentInfo(int content_idx) {
		sqlSessionTemplate.delete("board.deleteContentInfo", content_idx);
	}
	
	//전체글 개수
	public int getContentCnt(int content_board_idx) {
		return sqlSessionTemplate.selectOne("board.getContentCnt", content_board_idx);
	}
}










