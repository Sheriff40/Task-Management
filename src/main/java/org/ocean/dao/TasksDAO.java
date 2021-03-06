package org.ocean.dao;

import org.ocean.dto.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value = "TasksDAO")
public interface TasksDAO extends JpaRepository<Tasks, Integer> {

	
}
