package com.myapp.service;

import java.util.List;

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
@Repository
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepo;
	
	@Autowired
    private SqlSession sqlQuery; 
	
	public Page<BoardEntity> selectBoardlist (Pageable pageable) {
		return boardRepo.findAll(pageable);
	}
	
	public int selectBoardListCnt(BoardVO vo) {
		
		return sqlQuery.selectOne("selectBoardListCnt", vo);
		
	}

	public void insertBoard(BoardVO vo) {
		vo.setId("애기사자");
		sqlQuery.insert("insertBoard", vo);
		
	}

	public BoardVO selectBoardOne(int seq) {
		
		BoardVO vo = sqlQuery.selectOne("selectBoardOne", seq);
		
		return vo;
	}
}
