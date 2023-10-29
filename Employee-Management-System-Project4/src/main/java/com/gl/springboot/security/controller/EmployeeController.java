package com.gl.springboot.security.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.springboot.security.model.Employee;
import com.gl.springboot.security.model.Role;
import com.gl.springboot.security.model.User;
import com.gl.springboot.security.repository.EmployeeRepository;
import com.gl.springboot.security.service.EmployeeService;
import com.gl.springboot.security.service.UserService;
import com.gl.springboot.security.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	private EmployeeService employeeService;
	
	@Autowired
	private RoleServiceImpl roleService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// build get all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// build get employee by id REST API
	// http://localhost:8080/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	// build update employee REST API
	// http://localhost:8080/api/employees/1
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}

	// build delete employee REST API
	// http://localhost:8080/api/employees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {

		// delete employee from DB
		employeeService.deleteEmployee(id);

		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}

	@GetMapping("/sort")
	public List<Employee> getAllEmployeeSortedByName(@RequestParam(defaultValue = "asc") String order) {
        return employeeService.getAllEmployeeSortedByName(order);
	
    }
	
	@GetMapping("/search/{keyword}")
	public List<Employee> findByKeyword(@PathVariable String keyword) {
        return employeeService.findByKeyword(keyword);
	
    }
	
	@PostMapping("/add-role")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return new ResponseEntity<Role>(roleService.addRole(role), HttpStatus.CREATED);
	}
	
	 @Autowired
	    private UserService userService;

	    @PostMapping("/add-user")
	    public ResponseEntity<String> addUser(@RequestBody User user) {
	        // You can add validation and error handling here
	        userService.save(user);
	        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
	    }

}
