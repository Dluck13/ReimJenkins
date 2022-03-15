import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import Exceptions.EmployeeNotFoundException;
import Exceptions.SystemException;
import POJO.Employee;
import POJO.Reimbursement;
import Service.EmployeeService;
import Service.EmployeeServiceImpl;
import Service.FinanceManagerService;
import Service.LoginService;
import io.javalin.Javalin;


public class Project1Main {
	static EmployeeService employeeService = new EmployeeServiceImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Javalin myServer = Javalin.create((config) -> config.enableCorsForAllOrigins()).start(4040);
		System.out.println("port 4040");
		myServer.get("/hi", ctx -> ctx.result("Hello"));
		
		
		myServer.get("/RequestsPending", (ctx)-> {
			ctx.json(FinanceManagerService.getPendingReimbursements());	
		});
		
		myServer.get("/RequestsResolved", (ctx)-> {
			ctx.json(FinanceManagerService.getCompletedReimbursements());	
			
		});
		myServer.get("/RequestsAll", (ctx)-> {
			ctx.json(FinanceManagerService.getAllReimbursements());	
			
		});
		myServer.get("/RequestsEmployee/{bid}", (ctx)-> {
			String employeeID = ctx.pathParam("bid");
			ctx.json(FinanceManagerService.getEmployeeReimbursements(Integer.parseInt(employeeID)));
			
		});
		
		myServer.get("/AllEmployeesManagerView", (ctx)-> {
			ctx.json(FinanceManagerService.getAllEmployees());
			
		});
		
		// what is the practical difference between post/put my post both inserts and updates a table.
		myServer.post("/UpdateRequest", (ctx)->{
			Reimbursement rUpdate = ctx.bodyAsClass(Reimbursement.class);
			ctx.json(FinanceManagerService.updateReimbursement(rUpdate));
		});
		
		

		myServer.post("/Login", (ctx)->{
			Employee e = ctx.bodyAsClass(Employee.class);
			ctx.json(LoginService.login(e));
		});
		
		
		
		
		
		
		// all employees
		myServer.get("/AllEmployees", (ctx)->{
		ctx.json(employeeService.fetchAllEmployees());
		});
		
		//fetch employee
		myServer.get("/GetEmployee/{employee_id}", (ctx)->{
			//retrieve the path param value,specify path param key
			String empId = ctx.pathParam("employee_id");
			System.out.println(empId);
			//tell service layer to fetch emplo yee
			Employee fetchedEmployee = employeeService.fetchAEmployee(Integer.parseInt(empId));
			//return
			ctx.json(fetchedEmployee);
		});
		//delete an employee
		myServer.delete("/DeleteEmployees/{bid1}", (ctx)->{
			//retrieve the path param value,specify path param key
			String empId = ctx.pathParam("bid1");
			//tell service layer to delete
			Employee deletedEmp = employeeService.deleteEmployee(Integer.parseInt(empId));
			//return 
			//ctx.json(deletedEmp);
			});
		
		//add a employee
		myServer.post("/AddEmployees", (ctx)->{
			//converts json to pojo
		Employee newEmp = ctx.bodyAsClass(Employee.class);
		Employee returnedEmp = employeeService.addEmployee(newEmp);
		ctx.json(returnedEmp);
		});
		
		//update a 
		
		myServer.put("/UpdateEmployees", (ctx)->{
			
		Employee newUpdate = ctx.bodyAsClass(Employee.class);
		Employee returnedUpdate = employeeService.updateEmployee(newUpdate);
		ctx.json(returnedUpdate);
		});
		
		// this is the catch block for SystemException
				myServer.exception(SystemException.class,(se, ctx)->{
					Map<String, String> error = new HashMap<String, String>();
					error.put("message", se.getMessage());
					error.put("datetime", LocalDateTime.now()+"");
					ctx.json(error);
				} );
				
				myServer.exception(EmployeeNotFoundException.class,(ee, ctx)->{
					Map<String, String> error = new HashMap<String, String>();
					error.put("message", ee.getMessage());
					error.put("datetime", LocalDateTime.now()+"");
					ctx.json(error);
				} );
				
				//github webhook hello
				
				
		
		
		
	}

}
