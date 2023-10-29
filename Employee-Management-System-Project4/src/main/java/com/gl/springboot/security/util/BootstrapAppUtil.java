package com.gl.springboot.security.util;

import static java.util.stream.IntStream.range;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.gl.springboot.security.dao.RoleJpaRepository;
import com.gl.springboot.security.dao.UserJpaRepository;
import com.gl.springboot.security.model.Employee;
import com.gl.springboot.security.model.Role;
import com.gl.springboot.security.model.User;
import com.gl.springboot.security.repository.EmployeeRepository;


@Component
public class BootstrapAppUtil {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private UserJpaRepository userRepository;
	
	
	@Autowired
	private RoleJpaRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Faker faker = new Faker();

	@Value("${app.employeecount}")
	private int employeeCount;

	@EventListener(ApplicationReadyEvent.class)
	public void onReady(ApplicationReadyEvent event){
		range(0, employeeCount).forEach(index -> {
//			LocalDate EmployeeDate = faker
//									.date()
//									.past(10, TimeUnit.DAYS)
//									.toInstant()
//									.atZone(ZoneId.systemDefault())
//									.toLocalDate();
			Employee employee = Employee.builder()
							.firstName(faker.name().firstName())
							.lastName(faker.name().lastName())
							.email(faker.internet().emailAddress())
							.build();
			
			
			this.repository.save(employee);
			});
	}
			
		
	

	@EventListener(ApplicationReadyEvent.class)
	public void insertUsers(ApplicationReadyEvent event) {
		//User kiran = new User();
		//kiran.setName("kiran");
		//kiran.setPassword(this.passwordEncoder.encode("welcome"));

		User vinay = new User();
		vinay.setName("vinay");
		vinay.setPassword(this.passwordEncoder.encode("welcome"));
		
		//this.userRepository.save(kiran);
		this.userRepository.save(vinay);
		
		//Role userRole = new Role();
		//userRole.setRole("ROLE_USER");
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		
		//this.roleRepository.save(userRole);
		this.roleRepository.save(adminRole);
		
		
		//kiran.addRole(userRole);
		//vinay.addRole(userRole);
		vinay.addRole(adminRole);
		
		//this.userRepository.save(kiran);
		this.userRepository.save(vinay);
	}
}
