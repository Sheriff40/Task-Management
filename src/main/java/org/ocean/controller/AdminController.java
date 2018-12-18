package org.ocean.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.ocean.dao.CommentDAO;
import org.ocean.dao.EmployeeDAO;
import org.ocean.dao.EmployeeTimeDAO;
import org.ocean.dao.TasksDAO;
import org.ocean.dto.Comment;
import org.ocean.dto.Employee;
import org.ocean.dto.EmployeeTime;
import org.ocean.dto.ResourceNotFoundException;
import org.ocean.dto.ResponseMessage;
import org.ocean.dto.Tasks;
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
	
	@Autowired
	private EmployeeTimeDAO employeeTimeDAO;
	
	@Autowired
	private TasksDAO taskDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	private Employee employee;
	
	private Tasks task;
	
	private EmployeeTime employeeTime;
	
	private ResourceNotFoundException exception404 = new ResourceNotFoundException();
	
	@GetMapping(value = "/employees")
	public List<Employee> getAllEmployee()
	{
		return employeeDAO.findAll();
	}
	
	@GetMapping(value = "/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) throws ResourceNotFoundException
	{
			employee = employeeDAO.findById(id).orElse(null);
			if(employee == null)
			{
				throw exception404;
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
				throw exception404;
			}
			employeeDAO.delete(employee);
			ResponseMessage message = new ResponseMessage(200, "Success");
			return new ResponseEntity<ResponseMessage>(message,HttpStatus.OK);		
	}
	
	
	@PostMapping(value = "/time")
	public EmployeeTime SaveEmployeeTime(@RequestBody EmployeeTime employeeTime)
	{
		employeeTime.setTimefrom(LocalTime.of(10, 00));
		employeeTime.setTimeto(LocalTime.of(18, 00));
		employeeTimeDAO.save(employeeTime);
		return employeeTime;
	}
	

	@GetMapping(value = "/times")
	public List<EmployeeTime> GetEmployeeTime()
	{
		return employeeTimeDAO.findAll();
	}
	
	
	@PostMapping(value= "/employee/{employeeId}/time/{employeeTimeId}")
	public ResponseMessage saveEmployeeTime(@PathVariable("employeeId")int employeeId, @PathVariable("employeeTimeId")int employeeTimeId) throws ResourceNotFoundException
	{	
	
		employeeTime = employeeTimeDAO.findById(employeeTimeId).orElse(null);
		employee = employeeDAO.findById(employeeId).orElse(null);
		if(employee == null || employeeTime == null)
		{
			
			throw exception404;
		}
		
		employee.setEmployeeTime(employeeTime);
		employeeDAO.save(employee);
		return new ResponseMessage(200,"success");
	}
	
	@PostMapping(value= "/comment/{id}")
	public Comment giveComment(@PathVariable("id")int taskId, @RequestBody Comment comment) throws ResourceNotFoundException
	{	
		
		task = taskDAO.findById(taskId).orElse(null);
		if(task == null)
		{
			throw exception404;
		}
		comment.setTask(task);
		comment.setDate(LocalDate.now());
		commentDAO.save(comment);
		task.getComments().add(comment);
		taskDAO.save(task);
		return comment;
	}

	
	
}
