package com.imcs.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imcs.spring.beans.Employee;
import com.imcs.spring.context.AppContext;
import com.imcs.spring.exceptions.EmployeeNotFoundException;
import com.imcs.spring.factory.AppConfig;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private Connection con;

	PreparedStatement ps = null;
	ResultSet rs = null;

	// adding employee
	public boolean addEmployee(Employee e) {
		try {
			String sql = "INSERT INTO employeedetails(name, age, phone_no, salary, company_name, dept_no, start_date, end_date)"
					+ "VALUES(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(2, e.getName());
			ps.setInt(3, e.getAge());
			ps.setString(4, e.getPhoneNo());
			ps.setInt(5, e.getSalary());
			ps.setString(6, e.getCompanyName());
			ps.setInt(7, e.getDeptNo());
			ps.setDate(8, new java.sql.Date(e.getStartDate().getTime()));
			ps.setDate(9, new java.sql.Date(e.getEndDate().getTime()));
			ps.executeUpdate();

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	public boolean deleteEmployee(int employeeId) {

		try {
			String selectSQL = "select *from employeedetails where employee_id=?";
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String sql = "delete from employeedetails where employee_id=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, employeeId);
				ps.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean updateEmployee(Employee e) {
		try {
			String selectSQL = "select *from employeedetails where employee_id=?";
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, e.getEmployeeId());
			rs = ps.executeQuery();
			if (rs.next()) {

				String sql = "update employeedetails name=?,age=?, phone_no=?, aalary=?, company_name=?, dept_no=?, start_date=?, end_date=? where employee_id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, e.getName());
				ps.setInt(2, e.getAge());
				ps.setString(3, e.getPhoneNo());
				ps.setInt(4, e.getSalary());
				ps.setString(5, e.getCompanyName());
				ps.setInt(6, e.getDeptNo());
				ps.setDate(7, new java.sql.Date(e.getStartDate().getTime()));
				ps.setDate(8, new java.sql.Date(e.getEndDate().getTime()));
				ps.setInt(9, e.getEmployeeId());
				ps.executeUpdate();
				return true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;

	}

	// returning the employee by their id
	public Employee getEmployee(int empId) throws EmployeeNotFoundException {
		try {
			String selectSQL = "select *from employeedetails where employee_id=?";
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			if (rs.next()) {
				Employee emp = AppContext.getInstance().getBean(Employee.class);
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoneNo(rs.getString("phone_no"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCompanyName(rs.getString("company_name"));
				emp.setDeptNo(rs.getInt("dept_no"));
				emp.setStartDate(new Date(rs.getDate("start_date").getTime()));
				emp.setEndDate(new Date(rs.getDate("end_date").getTime()));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new EmployeeNotFoundException("There is NO employee with given employee ID");
	}

	// displaying all employees information
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		try {
			String selectSQL = "select *from employeedetails";
			ps = con.prepareStatement(selectSQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = AppContext.getInstance().getBean(Employee.class);
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoneNo(rs.getString("phone_no"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCompanyName(rs.getString("company_name"));
				emp.setDeptNo(rs.getInt("dept_no"));
				emp.setStartDate(new Date(rs.getDate("start_date").getTime()));
				emp.setEndDate(new Date(rs.getDate("end_date").getTime()));
				employeeList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public List<Employee> sortAs(String selectSQL) {
		try {
			List<Employee> employeeList = new ArrayList<>();

			ps = con.prepareStatement(selectSQL);
			rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = AppContext.getInstance().getBean(Employee.class);
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoneNo(rs.getString("phone_no"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCompanyName(rs.getString("company_name"));
				emp.setDeptNo(rs.getInt("dept_no"));
				emp.setStartDate(new Date(rs.getDate("start_date").getTime()));
				emp.setEndDate(new Date(rs.getDate("end_date").getTime()));
				employeeList.add(emp);
			}
			return employeeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Employee> highSalaryIterator(int salary) {

		try {
			List<Employee> employeeList = new ArrayList<>();
			String selectSQL = "select *from employeedetails where salary>?";
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, salary);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = AppContext.getInstance().getBean(Employee.class);
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoneNo(rs.getString("phone_no"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCompanyName(rs.getString("company_name"));
				emp.setDeptNo(rs.getInt("dept_no"));
				emp.setStartDate(new Date(rs.getDate("start_date").getTime()));
				emp.setEndDate(new Date(rs.getDate("end_date").getTime()));
				employeeList.add(emp);
			}
			return employeeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}