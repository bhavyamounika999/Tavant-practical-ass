package com.tavant.EmployeeRestApi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
@Id
	private Integer employeeNumber;
	
@NotBlank(message="First name should not be blank")
	private String firstName;
@NotBlank(message="Last Name should not be blank")
	private String lastName;
	@NotBlank(message="email should not be blank")
	@Email(message="email should be a valid one")
	 private String EmailId;
	@NotBlank(message="job title should not be blank")
	private String jobTitle;
	
}
