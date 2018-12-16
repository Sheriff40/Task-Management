package org.ocean.dao;

import org.ocean.dto.EmployeeTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "EmployeeTimeDAO")
public interface EmployeeTimeDAO extends JpaRepository<EmployeeTime, Integer>{

}
