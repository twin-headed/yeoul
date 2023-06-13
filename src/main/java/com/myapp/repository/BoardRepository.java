package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.vo.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}