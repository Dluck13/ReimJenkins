package DAO;

import java.util.List;

import Exceptions.EmployeeNotFoundException;
import Exceptions.SystemException;
import POJO.Employee;
import POJO.Reimbursement;

public interface EmployeeDao {
	
	// Read - fetch all books
		List<Employee> fetchAllEmployees()throws SystemException, EmployeeNotFoundException;
		// Create
		Employee addEmployee(Employee employee)throws SystemException;
		// Update
		Employee updateEmployee(Employee employee)throws SystemException;
		// Delete
		Employee deleteEmployee(int employeeID)throws SystemException;
//		 Read - fetch a employee
		Employee fetchAEmployee(int employeeID)throws SystemException;
		

		Reimbursement submitRequest(Reimbursement reimbursement)throws SystemException;
		
		Reimbursement fetchARequest(int employeeID)throws SystemException;
		
		
		

		
		
		

		
		
		
		
		
		//Exit
		default void exitApplication()throws SystemException{
			DBConnection.closeConnection();
		}
		
	}
	


