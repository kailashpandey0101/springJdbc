package com.imcs.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imcs.spring.beans.Employee;
import com.imcs.spring.context.AppContext;
import com.imcs.spring.dao.EmployeeDao;
import com.imcs.spring.exceptions.EmployeeNotFoundException;
import com.imcs.spring.exceptions.InvalidSalaryException;

@Service
public class EmployeeServiceImpl {

	public boolean addEmployee(Employee e) throws InvalidSalaryException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		if (e.getSalary() < 5000) {
			throw new InvalidSalaryException("Sorry:: Salary must greater than 5000");

		}

		if (employeeDao.addEmployee(e)) {
			return true;
		}

		return false;
	}

	public boolean deleteEmployee(int employeeId) throws EmployeeNotFoundException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		Employee e = employeeDao.getEmployee(employeeId);
		if (e == null) {
			throw new EmployeeNotFoundException("There is NO employee with given employee ID");
		}
		if (employeeDao.deleteEmployee(employeeId)) {
			return true;
		}
		return false;
	}

	public boolean updateEmployee(Employee e) throws InvalidSalaryException, EmployeeNotFoundException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		if (e.getSalary() < 5000) {
			throw new InvalidSalaryException("Sorry:: Salary should be greater than 5000");

		}

		if ((employeeDao.getEmployee(e.getEmployeeId()) == null)) {
			throw new EmployeeNotFoundException("There is NO employee with given employee ID");
		}
		if (employeeDao.updateEmployee(e)) {
			return true;
		}
		return false;
	}

	public Employee getEmployee(int empId) throws EmployeeNotFoundException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		return (employeeDao.getEmployee(empId));
	}

	public List<Employee> getAllEmployees() {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		return (employeeDao.getAllEmployees());
	}

	public int getHra(int empId) throws EmployeeNotFoundException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		if (employeeDao.getEmployee(empId) == null) {
			throw new EmployeeNotFoundException("There is NO employee with given employee ID");
		}

		Employee e = employeeDao.getEmployee(empId);
		return (calculateHra(e.getSalary(), e.getAge()));

	}

	// returns Gross salary of a particular employee
	public int getGrossSalary(int empId) throws EmployeeNotFoundException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		if (employeeDao.getEmployee(empId) == null) {
			throw new EmployeeNotFoundException("There is NO employee with given employee ID");
		}
		Employee e = employeeDao.getEmployee(empId);
		return (calculateGrossSalary(e.getSalary(), e.getAge()));
	}

	public List<Employee> highSalaryIterator(int salary) {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		return (employeeDao.highSalaryIterator(salary));
	}

	public List<Employee> sortAs(String sortingName) {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		String selectSQL;
		if (sortingName.equalsIgnoreCase("salary")) {
			selectSQL = "select *from employeedetails order by salary";
		} else if (sortingName.equalsIgnoreCase("nameAndSalary")) {
			selectSQL = "select *from employeedetails order by name,salary";
		} else if (sortingName.equalsIgnoreCase("departmentNo")) {
			selectSQL = "select *from employeedetails order by departmentno";
		} else if (sortingName.equalsIgnoreCase("employeeId")) {
			selectSQL = "select *from employeedetails order by employeeid";
		} else {
			System.out.println("Wrong choice : Try again");
			return null;
		}
		return employeeDao.sortAs(selectSQL);
	}

	public int getSalary(int empId) throws EmployeeNotFoundException {
		EmployeeDao employeeDao = AppContext.getInstance().getBean(EmployeeDao.class);
		if (employeeDao.getEmployee(empId) == null) {
			throw new EmployeeNotFoundException("There is NO employee with given employee ID");
		}

		Employee e = employeeDao.getEmployee(empId);
		return e.getSalary();
	}

	private int calculateGrossSalary(int salary, int age) {
		if (salary < 10000) {
			return ((int) (salary + 0.08 * salary + calculateHra(salary, age)));

		} else if (salary < 20000) {
			return ((int) (salary + 0.1 * salary + calculateHra(salary, age)));

		} else if (salary < 30000 && age >= 40) {
			return ((int) (salary + 0.15 * salary + calculateHra(salary, age)));

		} else if (salary < 30000 && age < 40) {
			return ((int) (salary + 0.13 * salary + calculateHra(salary, age)));

		} else {
			return ((int) (salary + 0.17 * salary + calculateHra(salary, age)));

		}

	}

	private int calculateHra(int salary, int age) {
		if (salary < 10000) {
			return ((int) (0.15 * salary));

		} else if (salary < 20000) {
			return ((int) (0.2 * salary));

		} else if (salary < 30000 && age >= 40) {
			return ((int) (0.27 * salary));

		} else if (salary < 30000 && age < 40) {
			return ((int) (0.25 * salary));

		} else {
			return ((int) (0.3 * salary));

		}
	}

}
