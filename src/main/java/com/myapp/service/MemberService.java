package com.myapp.service;

import com.myapp.vo.BoardVO;
import com.myapp.vo.CommentVO;
import com.myapp.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final SqlSession sqlQuery;

	public MemberVO checkId(String id) {
		return sqlQuery.selectOne("checkId", id);
	}

	public MemberVO checkGameId(String checkGameId) {
		return sqlQuery.selectOne("checkGameId", checkGameId);
	}

	public void insertMember(MemberVO vo) {
		vo.setPassword("{noop}"+vo.getPassword());
		sqlQuery.insert("insertMember",vo);
	}

}
