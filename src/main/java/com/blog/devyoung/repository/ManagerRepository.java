package com.blog.devyoung.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.devyoung.model.Board;
import com.blog.devyoung.model.User;

public interface ManagerRepository extends JpaRepository<Board, Long> {

}
