package com.myapp.service;

import com.myapp.vo.BoardVO;
import com.myapp.vo.CommentVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

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
