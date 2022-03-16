package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.EmployeeNotFoundException;
import Exceptions.SystemException;
import POJO.Employee;

public class EmployeeJdbcDao implements EmployeeDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeJdbcDao.class);

	@Override
	public List<Employee> fetchAllEmployees() throws SystemException, EmployeeNotFoundException{
		LOG.info("Entered fetchAllEmployees() in DAO");
		// create an array list to hold all the employees info fetched from the DB
		List<Employee> allEmployees = new ArrayList<Employee>();
		Connection conn = DBConnection.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employees";
			ResultSet results = stmt.executeQuery(query);
			// iterate through the result set 
			while(results.next()) {
				//copy each record into a employeePojo object 
				Employee employeePojo = new Employee();
				employeePojo.setEmployeeID(results.getInt(1));
				employeePojo.setJobTitle(results.getString(2));
				employeePojo.setFullName(results.getString(3));
				employeePojo.setLastName(results.getString(4));
				employeePojo.setEmail(results.getString(5));
				employeePojo.setPhone(results.getString(6));
						
					
				// add the employeePojo object to the collection
				allEmployees.add(employeePojo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException();
		}
		if(allEmployees.isEmpty()) {
			throw new EmployeeNotFoundException();
		}
		
		LOG.info("Exited fetchAllEmployees() in DAO");
		// return the collection
		return allEmployees;
	}

	@Override
	public Employee addEmployee(Employee employee) throws SystemException {
		LOG.info("Entered addEmployee() in DAO");
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO employees(employee_id,job_title_id,first_name, last_name, email, phone, user_name, user_password) VALUES("+employee.getEmployeeID()+","+employee.getJobTitle()+",'"+employee.getFirstName()+"','"+employee.getLastName()+"','"+employee.getEmail()+"','"+employee.getPhone()+"','"+employee.getUserName()+"','"+employee.getPassword()+"') RETURNING employee_id";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				employee.setEmployeeID(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited addEmployee() in DAO");
		return employee;
			
			
	}

	@Override
	public Employee updateEmployee(Employee employee) throws SystemException {
		LOG.info("Entered updateEmployee() in DAO");
		Connection conn = DBConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "UPDATE employees SET email='"+employee.getEmail()+"' WHERE employee_id="+employee.getEmployeeID();
			int rows = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited updateBook() in DAO");
		return employee;
	}

//	@Override
//	public Employee deleteEmployee(int employeeID) throws SystemException {
//		LOG.info("Entered deleteEmployee() in DAO");
//		Employee employeePojo = null;
//		Connection conn = DBConnection.getConnection();
//		try {
//			Statement stmt = conn.createStatement();
//			employeePojo = fetchAEmployee(employeeID);
//			System.out.println(employeePojo);
//			String query = "DELETE FROM employees WHERE employee_id="+employeeID;
//			
//			int rows = stmt.executeUpdate(query);
//			System.out.println(rows);
//		} catch (SQLException e) {
//			throw new SystemException();
//		}
//		LOG.info("Exited deleteEmployee() in DAO");
//		return employeePojo;
//	}

	@Override
	public Employee fetchAEmployee(int employeeID) throws SystemException {
		LOG.info("Entered fetchABook() in DAO");
		Employee fetchEmp = null;
		Connection conn = DBConnection.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employees WHERE employee_id="+employeeID;
			ResultSet results = stmt.executeQuery(query);
			if(results.next()) {
						fetchEmp = new Employee();
						fetchEmp.setEmployeeID(results.getInt(1));
						fetchEmp.setJobTitle(results.getString(2));
						fetchEmp.setFullName(results.getString(3));
						fetchEmp.setLastName(results.getString(4));
						fetchEmp.setEmail(results.getString(5));
						fetchEmp.setPhone(results.getString(6));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAEmployee() in DAO");
		return fetchEmp;
	}

}
