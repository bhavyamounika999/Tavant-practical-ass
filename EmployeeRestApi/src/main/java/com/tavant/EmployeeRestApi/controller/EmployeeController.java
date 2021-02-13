package com.tavant.EmployeeRestApi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.EmployeeRestApi.exception.EmployeeNotFoundException;
import com.tavant.EmployeeRestApi.exception.EmptyData;
import com.tavant.EmployeeRestApi.exception.NoDataFoundException;
import com.tavant.EmployeeRestApi.model.Employee;
import com.tavant.EmployeeRestApi.respositroy.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
//here we have to use the resources for employee
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployee() throws NoDataFoundException, EmployeeNotFoundException{
		
		
		Optional<List<Employee>> optional = Optional.of(employeeRepository.findAll());
		
		if(optional.isEmpty()) {
			throw new EmployeeNotFoundException("record not found");
		}
		
		else {
			return ResponseEntity.ok(optional.get());
		}
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id")Integer id) throws EmployeeNotFoundException {
	
		Optional<Employee> optional = employeeRepository.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		else {
			throw new EmployeeNotFoundException("no record found");
		}
	}
	
	public Employee addEmployee(@RequestBody @Valid Employee employee) throws EmptyData {
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId) throws EmployeeNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not Found for this id::"+employeeId));
		
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response =  new HashMap<>();
		return response;
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId, @Valid @RequestBody Employee employeeDetails) throws EmployeeNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not Found for this id::"+employeeId));
		
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employee.getFirstName());
		
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	
	

}