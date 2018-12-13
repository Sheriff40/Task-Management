package org.ocean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class EmployeeController {

	@GetMapping(value = "/home")
	@ResponseBody
	public String home()
	{
		return "Welcome to home page";
	}
	
}
