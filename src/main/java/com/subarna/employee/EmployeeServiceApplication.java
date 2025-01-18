package com.subarna.employee;

import com.subarna.employee.entity.Employee;
import com.subarna.employee.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(EmployeeRepository employeeRepository) {
		return args -> insertValues(employeeRepository);
	}

	public static void insertValues(EmployeeRepository employeeRepository) {
		List<Employee> employees = List.of(
				new Employee("Subarna","Zaandam"),
				new Employee( "Bose","Almere")
		);
		employeeRepository.saveAll(employees);
	}
}
