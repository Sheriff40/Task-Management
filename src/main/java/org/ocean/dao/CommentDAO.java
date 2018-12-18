package org.ocean.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ocean.dto.Comment;


public interface CommentDAO extends JpaRepository<Comment, Integer> {

}
