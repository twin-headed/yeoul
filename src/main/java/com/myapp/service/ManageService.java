package com.myapp.service;

import com.myapp.vo.LinkVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageService {

    private final SqlSession sqlQuery;

    public LinkVO selectLink() {
        return sqlQuery.selectOne("selectLink");
    }

    public void updateCommunity(String community) {
        sqlQuery.update("updateCommunity", community);
    }

    public void updateDownload(String download) {
        sqlQuery.update("updateDownload", download);
    }
}
