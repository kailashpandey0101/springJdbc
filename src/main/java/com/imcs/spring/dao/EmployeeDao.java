package com.imcs.spring.dao;

import java.util.List;

import com.imcs.spring.beans.Employee;
import com.imcs.spring.exceptions.EmployeeNotFoundException;

public interface EmployeeDao {
	boolean addEmployee(Employee e);

	boolean deleteEmployee(int employeeId);

	boolean updateEmployee(Employee e);

	Employee getEmployee(int empId) throws EmployeeNotFoundException;

	List<Employee> getAllEmployees();

	List<Employee> sortAs(String string);

	List<Employee> highSalaryIterator(int salary);

}
