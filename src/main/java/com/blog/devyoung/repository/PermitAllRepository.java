package com.blog.devyoung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.devyoung.model.Board;

public interface PermitAllRepository extends JpaRepository<Board, Long> {

	List<Board> findAllByOrderByWriteDateDesc();
	
	Board findById(long l);
	
}
