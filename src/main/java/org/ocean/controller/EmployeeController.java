package org.ocean.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.ocean.dao.EmployeeDAO;
import org.ocean.dao.TasksDAO;
import org.ocean.dto.Comment;
import org.ocean.dto.Employee;
import org.ocean.dto.ResourceNotFoundException;
import org.ocean.dto.ResponseMessage;
import org.ocean.dto.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private TasksDAO taskDAO;
	
	private Tasks task = null; 
	
	private Employee employee = null;
	
	private LocalTime time = LocalTime.now();
	
	private ResourceNotFoundException exception404 = new ResourceNotFoundException();
	
	@GetMapping(value = "/tasks/{id}")
	public @ResponseBody List<Tasks> getEmployeesTasks(@PathVariable("id")int employeeId) throws ResourceNotFoundException
	{
		
		employee = employeeDAO.findById(employeeId).orElse(null);
		if(employee == null)
		{
			throw exception404;
		}
		else
		{
			for(Tasks t: employee.getTasks())
			{
				if (!(t.getComments().isEmpty()))
						{
							employee.setNotification(true);
							break;
						}
			}
			employeeDAO.save(employee);
		}
				
		return employee.getTasks();
	}
	
	@GetMapping(value = "/task/{id}")
	public @ResponseBody Tasks getSingleTask(@PathVariable ("id") int taskId) throws ResourceNotFoundException
	{
		task = taskDAO.findById(taskId).orElse(null);
		if(task == null)
		{
			throw exception404;
		}
		return task;
	}
	
	@PostMapping(value = "/task/{id}")
	public @ResponseBody Tasks saveTask(@RequestBody Tasks task,@PathVariable ("id") int employeeId) throws ResourceNotFoundException
	{
		employee = employeeDAO.findById(employeeId).orElse(null);
		if(employee == null)
		{
			throw exception404;
		}
		task.setCreatedDate(LocalDateTime.now());
		task.setEmployee(employee);
		if((time.isAfter(employee.getEmployeeTime().getTimefrom())) && (time.isBefore(employee.getEmployeeTime().getTimeto())))
		{
			taskDAO.save(task);
		}
		
		return task;
		
	}		
	
	@PutMapping(value = "/task/{id}")
	public @ResponseBody Tasks updateTask(@RequestBody Tasks task,@PathVariable("id")int employeeId) throws ResourceNotFoundException
	{
		employee = employeeDAO.findById(employeeId).orElse(null);
		if(employee == null)
		{
			throw exception404;
		}
		if((time.isAfter(employee.getEmployeeTime().getTimefrom())) && (time.isBefore(employee.getEmployeeTime().getTimeto())))
		{
			task.setUpdatedDate(LocalDateTime.now());
			taskDAO.save(task);
		}
		return task;
	}
	
	@DeleteMapping(value = "/task/{id}")
	public ResponseEntity<ResponseMessage> deleteTask(@PathVariable ("id") int taskId) throws ResourceNotFoundException
	{
		task = taskDAO.findById(taskId).orElse(null);
		if(task == null)
		{
			throw exception404;
		}
		taskDAO.delete(task);
		ResponseMessage message = new ResponseMessage(200 , "Success");
		return new ResponseEntity<ResponseMessage>(message,HttpStatus.OK);
	}
	
	@GetMapping(value = "/comment/{id}")
	public @ResponseBody List<Comment> viewComments(@PathVariable ("id") int taskId) throws ResourceNotFoundException
	{
		
		task = taskDAO.findById(taskId).orElse(null);
		if(task == null)
		{
			throw exception404;
		}
		return task.getComments();
	}
	
	
		
}
