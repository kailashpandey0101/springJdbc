package com.imcs.spring.services;

import java.util.List;

import com.imcs.spring.beans.Employee;
import com.imcs.spring.exceptions.EmployeeNotFoundException;
import com.imcs.spring.exceptions.InvalidSalaryException;

public interface EmployeeService {
	boolean addEmployee(Employee e) throws InvalidSalaryException;

	boolean deleteEmployee(int employeeId) throws EmployeeNotFoundException;

	boolean updateEmployee(Employee e) throws InvalidSalaryException, EmployeeNotFoundException;

	Employee getEmployee(int empId) throws EmployeeNotFoundException;

	List<Employee> getAllEmployees();

	int getHra(int empId) throws EmployeeNotFoundException;

	int getGrossSalary(int empId) throws EmployeeNotFoundException;

	List<Employee> highSalaryIterator(int salary);

	List<Employee> sortAs(String sortingName);

	int getSalary(int empId) throws EmployeeNotFoundException;
}
