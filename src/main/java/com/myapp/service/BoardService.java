package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import com.myapp.vo.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.myapp.repository.BoardRepository;
import com.myapp.vo.BoardEntity;
import com.myapp.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final SqlSession sqlQuery;

	public List<BoardVO> selectBoardlist (BoardVO vo) {
		return sqlQuery.selectList("selectBoardList", vo);
	}
	
	public int selectBoardListCnt(BoardVO vo) {
		return sqlQuery.selectOne("selectBoardListCnt", vo);
	}

	public void insertBoard(BoardVO vo) {
		vo.setId("QA");
		sqlQuery.insert("insertBoard", vo);
	}

	public void updateBoard(BoardVO vo) {
		sqlQuery.update("updateBoard", vo);
	}

	public void deleteBoard(int seq) {
		sqlQuery.update("deleteBoard", seq);
	}

	public BoardVO selectBoardOne(int seq) {
		return sqlQuery.selectOne("selectBoardOne", seq);
	}

	public List<CommentVO> selectComments(int boardSeq) {
		return sqlQuery.selectList("selectComment", boardSeq);
	}

	public List<CommentVO> selectSubComments(int boardSeq) {
		return sqlQuery.selectList("selectSubComment", boardSeq);
	}

	public void updateViewCount(int seq) {
		sqlQuery.update("updateViewCount",seq);
	}

	public void insertComment(CommentVO vo) {
		sqlQuery.insert("insertComment", vo);
	}

	public void insertSubComment(CommentVO vo) {
		sqlQuery.insert("insertSubComment", vo);
	}
}
