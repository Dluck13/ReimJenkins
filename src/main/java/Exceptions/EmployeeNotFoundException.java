package Exceptions;

public class EmployeeNotFoundException extends Exception {
	
	@Override
	public String getMessage() {
		return "No Employees Found!!";
	}

}
