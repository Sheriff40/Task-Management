package org.ocean.controller;

import java.util.List;

import org.ocean.dao.EmployeeDAO;
import org.ocean.dto.Employee;
import org.ocean.dto.ResponseMessage;
import org.ocean.dto.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	private Employee employee;
	
	@GetMapping(value = "/employees")
	public List<Employee> getAllEmployee()
	{
		return employeeDAO.findAll();
	}
	
	@GetMapping(value = "/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) throws ResourceNotFoundException, Exception
	{
			employee = employeeDAO.findById(id).orElse(null);
			if(employee == null)
			{
				throw new ResourceNotFoundException();
			}
			return employee;
	}
	
	@PostMapping(value = "/employee")
	public Employee saveEmployee(@RequestBody Employee employee)
	{
				employeeDAO.save(employee);
				return employee;		
	}
	
	@PutMapping(value = "/employee")
	public Employee updateOrsaveEmployee(@RequestBody Employee employee)
	{
			employeeDAO.save(employee);
			return employee;		
	}
	
	
	@DeleteMapping(value = "/employee/{id}")	
	public ResponseEntity<ResponseMessage> GetEmployee(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		
			employee = employeeDAO.findById(id).orElse(null);
			if(employee == null)
			{
				ResourceNotFoundException e = new ResourceNotFoundException();
				throw e;
			}
			employeeDAO.delete(employee);
			ResponseMessage message = new ResponseMessage();
			message.setCode(HttpStatus.OK.value());
			message.setMessage("success");
			return new ResponseEntity<ResponseMessage>(message,HttpStatus.OK);		
	}
	

}
