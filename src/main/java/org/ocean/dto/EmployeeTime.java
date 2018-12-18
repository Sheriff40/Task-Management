package org.ocean.dto;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmployeeTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalTime timeFrom;
	private LocalTime timeTo;
	
	
	public EmployeeTime()
	{
	}
		
	public EmployeeTime(LocalTime timeFrom, LocalTime timeTo)
	{
		this.timeTo = timeTo;
		this.timeFrom = timeFrom;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalTime getTimeto() {
		return timeTo;
	}
	public void setTimeto(LocalTime timeto) {
		timeTo = timeto;
	}
	public LocalTime getTimefrom() {
		return timeFrom;
	}
	public void setTimefrom(LocalTime timefrom) {
		timeFrom = timefrom;
	}
	
	

//	public List<Employee> getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(List<Employee> employee) {
//		this.employee = employee;
//	}

	@Override
	public String toString() {
		return "EmployeeTime [id=" + id + ", Timeto=" + timeTo + ", Timefrom=" + timeFrom + "]";
	}
	
}
