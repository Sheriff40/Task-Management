package org.ocean.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.ocean.dao.EmployeeDAO;
import org.ocean.dao.TasksDAO;
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
public class TaskController {

	@Autowired
	private TasksDAO taskDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@GetMapping(value = "/tasks")
	public @ResponseBody List<Tasks> getTasks()
	{
		return taskDAO.findAll();
	}
	
	@GetMapping(value = "/task/{id}")
	public @ResponseBody Tasks getTasks(@PathVariable ("id") int taskId) throws ResourceNotFoundException
	{
		Tasks task = taskDAO.findById(taskId).orElse(null);
		if(task == null)
		{
			throw new ResourceNotFoundException();
		}
		return task;
	}
	
	
	@PostMapping(value = "/task/{id}")
	public @ResponseBody Tasks saveTask(@RequestBody Tasks task,@PathVariable ("id") int employeeId) throws ResourceNotFoundException
	{
		
		task.setCreatedDate(LocalDateTime.now());
		Employee employee = employeeDAO.findById(employeeId).orElse(null);
		if(employee == null)
		{
			throw new ResourceNotFoundException();
		}
		task.setEmployee(employee);
		taskDAO.save(task);
		return task;
	}
	
	@PutMapping(value = "/task")
	public @ResponseBody Tasks updateTask(@RequestBody Tasks task) throws ResourceNotFoundException
	{
		
		task.setUpdatedDate(LocalDateTime.now());
		taskDAO.save(task);
		return task;
	}
	
	@DeleteMapping(value = "/task/{id}")
	public ResponseEntity<ResponseMessage> deleteTask(@PathVariable ("id") int taskId) throws ResourceNotFoundException
	{
		Tasks task = taskDAO.findById(taskId).orElse(null);
		if(task == null)
		{
			throw new ResourceNotFoundException();
		}
		taskDAO.delete(task);
		ResponseMessage message = new ResponseMessage(200 , "Success");
		return new ResponseEntity<ResponseMessage>(message,HttpStatus.OK);
	}
	
}
