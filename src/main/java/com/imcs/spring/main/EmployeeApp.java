package com.imcs.spring.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.imcs.spring.beans.Employee;
import com.imcs.spring.context.AppContext;
import com.imcs.spring.exceptions.EmployeeNotFoundException;
import com.imcs.spring.exceptions.InvalidSalaryException;
import com.imcs.spring.services.EmployeeService;
import com.imcs.spring.services.EmployeeServiceImpl;

@Component
public class EmployeeApp {

	public static void main(String[] args) throws ParseException {
		try (Scanner sc = new Scanner(System.in);) {
			while (true) {
				System.out.println("-------------------MENU------------------\n");
				System.out.println("1:  ADD an Employee");
				System.out.println("2:  DELETE an Employee");
				System.out.println("3:  UPDATE an Employee");
				System.out.println("4:  DISPLAY an Empl0yee Information");
				System.out.println("5:  DISPLAY All Employees");
				System.out.println("6:  DISPLAY an Employee's HRA");
				System.out.println("7:  DISPLAY an Employee's Gross Salary");
				System.out.println("8:  SORT Employees");
				System.out.println("9:  Iterate highsalary employees than given salary");
				/* System.out.println("10: Print the salary of an employee"); */
				System.out.println("11  Exit");
				int option = sc.nextInt();
				switch (option) {
				case 1:
					addEmployee(sc);
					break;
				case 2:
					deleteEmmployee(sc);
					break;
				case 3:
					updateEmployee(sc);
					break;
				case 4:
					displayEmployee(sc);
					break;
				case 5:
					displayAllEmployees();
					break;
				case 6:
					displayHra(sc);
					break;
				case 7:
					displayGrossSalary(sc);
					break;
				case 8:
					sortEmployees(sc);
					break;
				case 9:
					highSalaryIterator(sc);
					break;
				/*
				 * case 10: getSalary(sc); break;
				 */
				case 11:
					System.exit(0);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private static void getSalary(Scanner sc) throws EmployeeNotFoundException {
		System.out.println("Enter the employee id number");
		int empId = sc.nextInt();
		EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
		System.out.println(eService.getSalary(empId));

	}

	private static void highSalaryIterator(Scanner sc) {
		System.out.println("Input the minumum salary amount");
		int salary = sc.nextInt();
		EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
		eService.highSalaryIterator(salary);

	}

	private static void sortEmployees(Scanner sc) {

		System.out.println("------You are in sorting module----------");
		System.out.println("1:	Sort by Salary");
		System.out.println("2:	Sort by Name and Salary");
		System.out.println("3:	Sort by Department Number");
		System.out.println("4:	Sort by Employee Id");
		System.out.println("5:	Exit the module");
		int choice = sc.nextInt();
		List<Employee> empList = new ArrayList<>();
		EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
		switch (choice) {

		case 1:
			empList = eService.sortAs("salary");
			displayEmployees(empList);
			System.out.println("Employees are sorted by Salary successfully");
			break;
		case 2:
			empList = eService.sortAs("nameAndSalary");
			displayEmployees(empList);
			System.out.println("Employees are sorted by Name and Salary successfully");
			break;
		case 3:
			empList = eService.sortAs("departmentNo");
			displayEmployees(empList);
			System.out.println("Employees are sorted by Department Number successfully");
			break;
		case 4:
			empList = eService.sortAs("EmployeeId");
			displayEmployees(empList);
			System.out.println("Employee are sorted by their Employee Id successfully");
			break;
		case 5:
			break;
		}

	}

	private static void displayEmployees(List<Employee> empList) {
		for (Employee e : empList) {
			System.out.println(e);
		}

	}

	private static void displayGrossSalary(Scanner sc) {
		System.out.println("Please enter the ID of the Employee");
		int empId = sc.nextInt();
		try {
			EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
			int grossSalary = eService.getGrossSalary(empId);
			System.out.println("The gross salary of the employee is = " + grossSalary);
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void displayHra(Scanner sc) {
		System.out.println("Please enter the ID of the Employee");
		int empId = sc.nextInt();
		try {
			EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
			int hra = eService.getHra(empId);
			System.out.println("Employee's HRA =  " + hra);
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void displayAllEmployees() {
		System.out.println("--------------All Employees Information----------");
		EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
		List<Employee> employees = eService.getAllEmployees();
		for (Employee e : employees) {
			System.out.println(e);
		}

	}

	private static void displayEmployee(Scanner sc) {
		System.out.println("---------------Employee Information-------------");
		System.out.println("Enter the ID of the Employee");
		int empId = sc.nextInt();
		try {
			EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
			Employee e = eService.getEmployee(empId);
			System.out.println(e);
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void updateEmployee(Scanner sc) throws ParseException {
		System.out.println("----------------------UPDATE EMPLOYEE--------------");
		System.out.println("Enter the ID of the Employee that you want to update");
		int id = sc.nextInt();
		Employee emp = AppContext.getInstance().getBean(Employee.class);
		// have time check if there is an employee of that id or not
		emp.setEmployeeId(id);
		System.out.println("Enter the Name. Example -> Jon");
		String name = sc.next();
		emp.setName(name);
		System.out.println("Enter the age. Example ->30");
		int age = sc.nextInt();
		emp.setAge(age);
		System.out.println("Enter the Phone No. Example ->9378557848");
		String phoneNo = sc.next();
		emp.setPhoneNo(phoneNo);
		System.out.println("Enter the Salary. Example ->65000");
		int salary = sc.nextInt();
		emp.setSalary(salary);
		System.out.println("Ener the Company Name. Example -> Dell");
		String companyName = sc.next();
		emp.setCompanyName(companyName);
		System.out.println("Enter the Department Number. Example -> 1");
		int deptNo = sc.nextInt();
		emp.setDeptNo(deptNo);
		System.out.println("Enter the start date of the job. (MM/dd/yyyy)");
		String date = sc.next();
		SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
		emp.setStartDate(parser.parse(date));
		System.out.println("Enter the end date of the job. (MM/dd/yyyy)");
		date = sc.next();
		emp.setEndDate(parser.parse(date));
		try {
			EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
			eService.updateEmployee(emp);
			System.out.println("Updated Successfully");
		} catch (EmployeeNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (InvalidSalaryException e2) {
			System.out.println(e2.getMessage());
		}
	}

	private static void deleteEmmployee(Scanner sc) {
		System.out.println("----------------DELETING EMPLOYEE-----------------");
		System.out.println("Enter the ID number");
		int empId = sc.nextInt();
		try {
			EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
			if (eService.deleteEmployee(empId)) {
				System.out.println("Deleted Successfully");
			}
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void addEmployee(Scanner sc) throws ParseException {
		System.out.println("----------------ADDING EMPLOYEE-----------------");
		System.out.println("Enter the Name. Example -> Jon");
		String name = sc.next();
		Employee emp = AppContext.getInstance().getBean(Employee.class);
		emp.setName(name);
		System.out.println("Enter the age. Example ->30");
		int age = sc.nextInt();
		emp.setAge(age);
		System.out.println("Enter the Phone No. Example ->9378557848");
		String phoneNo = sc.next();
		emp.setPhoneNo(phoneNo);
		System.out.println("Enter the Salary. Example ->65000");
		int salary = sc.nextInt();
		emp.setSalary(salary);
		System.out.println("Ener the Company Name. Example -> Dell");
		String companyName = sc.next();
		emp.setCompanyName(companyName);
		System.out.println("Enter the Department Number. Example -> 1");
		int deptNo = sc.nextInt();
		emp.setDeptNo(deptNo);
		System.out.println("Enter the start date of the job. (MM/dd/yyyy)");
		String date = sc.next();
		SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
		emp.setStartDate(parser.parse(date));
		System.out.println("Enter the end date of the job. (MM/dd/yyyy)");
		date = sc.next();
		emp.setEndDate(parser.parse(date));
		try {
			EmployeeServiceImpl eService = AppContext.getInstance().getBean(EmployeeServiceImpl.class);
			if (eService.addEmployee(emp)) {
				System.out.println("customer added successfully");
			}
		} catch (InvalidSalaryException e) {
			System.out.println(e.getMessage());
		}
	}

}
