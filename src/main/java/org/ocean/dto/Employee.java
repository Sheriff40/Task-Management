package org.ocean.dto;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String profilePic;
	private String post;
	private String Address;
	private String Contact;
	private String Email;
	private String password;
	private Boolean enabled;
	
	
	@OneToMany(mappedBy = "employee")
	private List<Tasks> tasks;
	
	@ManyToOne
	private EmployeeTime employeeTime = new EmployeeTime(LocalTime.of(9, 00), LocalTime.of(17, 00));
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getContact() {
		return Contact;
	}
	public void setContact(String contact) {
		Contact = contact;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public EmployeeTime getEmployeeTime() {
		return employeeTime;
	}
	public void setEmployeeTime(EmployeeTime employeeTime) {
		this.employeeTime = employeeTime;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", profilePic=" + profilePic + ", post=" + post + ", Address="
				+ Address + ", Contact=" + Contact + ", Email=" + Email + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
	
	
}
