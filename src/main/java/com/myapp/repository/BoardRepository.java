package com.myapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapp.vo.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b " +
            "FROM BoardEntity b " +
            "WHERE b.deleteYn = 'N' " +
            "AND (:title IS NULL OR b.title LIKE %:title%) " +
            "AND (:content IS NULL OR b.content LIKE %:content%) " +
            "AND (:id IS NULL OR b.id LIKE %:id%) " +
            "ORDER BY b.seq DESC")
    Page<BoardEntity> selectBoardList(@Param("title") String title, @Param("content") String content,
                                      @Param("id") String id, Pageable pageable);

}
