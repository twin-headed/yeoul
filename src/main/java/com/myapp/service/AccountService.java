package com.myapp.service;

import com.myapp.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final SqlSession sqlQuery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberVO vo = sqlQuery.selectOne("checkMember", username);

        if(vo == null) {
            throw new UsernameNotFoundException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }

        return User.builder()
                .username(vo.getGameId())
                .password(vo.getPassword())
                .roles(vo.getRole())
                .build();
    }
}
