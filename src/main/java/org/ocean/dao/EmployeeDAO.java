package org.ocean.dao;

import org.ocean.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value = "employeeDAO")
public interface EmployeeDAO extends JpaRepository<Employee, Integer>{

}
