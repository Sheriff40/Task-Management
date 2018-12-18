package org.ocean.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String body;
	private LocalDate date;
	
	@JsonIgnore
	@ManyToOne
	private Tasks task;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Tasks getTask() {
		return task;
	}
	public void setTask(Tasks task) {
		this.task = task;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", body=" + body + ", date=" + date + ", task=" + task + "]";
	}
	
	
}
