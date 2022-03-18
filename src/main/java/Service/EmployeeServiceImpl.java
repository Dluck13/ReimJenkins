package Service;

import java.util.List;

import DAO.EmployeeDao;
import DAO.EmployeeJdbcDao;
import Exceptions.EmployeeNotFoundException;
import Exceptions.SystemException;
import POJO.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao;
	
	public EmployeeServiceImpl() {
		employeeDao = new EmployeeJdbcDao();
	}

	@Override
	public List<Employee> fetchAllEmployees() throws SystemException, EmployeeNotFoundException {
		// TODO Auto-generated method stub
		return employeeDao.fetchAllEmployees();
	}

	@Override
	public Employee addEmployee(Employee employee) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.addEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public Employee deleteEmployee(int employeeID) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.deleteEmployee(employeeID);
	}

	@Override
<<<<<<< Updated upstream
	public Employee fetchAEmployee(int employeeID) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.fetchAEmployee(employeeID);
=======
	public Reimbursement fetchARequest(int employeeID) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.fetchARequest(employeeID);
>>>>>>> Stashed changes
	}

}
