package com.blog.devyoung.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="TB_USER",
		uniqueConstraints = {@UniqueConstraint(
											name = "userId",
											columnNames = {"userId"} 
											)
							}
	  )
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "userId", nullable = false, unique = false)
	private String userId;
	
	private String password;
	private String roles;  // USER, ADMIN
	private String userEmail;
	private String userName;
	
	public List<String> getRoleList() {
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
}
