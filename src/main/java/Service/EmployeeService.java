package Service;

import java.util.List;

import DAO.DBConnection;
import Exceptions.EmployeeNotFoundException;
import Exceptions.SystemException;
import POJO.Employee;

public interface EmployeeService {
	// Read - fetch all books
			List<Employee> fetchAllEmployees()throws SystemException, EmployeeNotFoundException;
			// Create
			Employee addEmployee(Employee employee)throws SystemException;
			// Update
			Employee updateEmployee(Employee employee)throws SystemException;
			// Delete
			Employee deleteEmployee(int employeeID)throws SystemException;
			// Read - fetch a employee
			Employee fetchAEmployee(int employeeID)throws SystemException;
			
<<<<<<< Updated upstream
=======
			Reimbursement submitRequest(Reimbursement reimbursement)throws SystemException;
			
			Reimbursement fetchARequest(int employeeID) throws SystemException;
			
>>>>>>> Stashed changes
			
			
			
			
			
			//Exit
			default void exitApplication()throws SystemException{
				DBConnection.closeConnection();
			}

}
