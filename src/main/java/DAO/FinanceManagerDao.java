package DAO;

import POJO.Employee;
import POJO.Reimbursement;
import POJO.Role;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;
public class FinanceManagerDao {
	private final static Logger log = LogManager.getLogger(FinanceManagerDao.class);
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static LinkedList<Reimbursement> getPendingReimbursements() throws Exception
	{
		log.info("in getPendingReimbursements Dao Layer");
		LinkedList<Reimbursement> riems = new LinkedList<Reimbursement>();	
	
		String query = "select * from all_reimbursements where db_status_id < 4;";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		ResultSet results = null;

		results = st.executeQuery();
		if(results != null)
		{
			while(results.next())
			{
				Reimbursement r = new Reimbursement();
				r.setAmount(results.getBigDecimal(7));
				r.setReimbursementID(results.getInt(1));
				r.setStatus(results.getString(5));
				r.setEmployee(results.getString(4));
				r.setCurrentComment(results.getString(9));
				r.setDetails(results.getString(8));
				r.setDateSubmitted(results.getString(10));
				r.setDateOfTransaction(results.getString(11));
				r.setDateUpdated(results.getString(12));
				r.setExpenseType(results.getString(6));
				r.setEmployeeID(results.getInt(3));
				r.setStatusID(results.getInt(2));
				riems.add(r);
			}
		}

		return riems;
	
	
	}

	public static LinkedList<Reimbursement> getCompletedReimbursements() throws SQLException {
		log.info("in getPendingReinbursements Dao Layer");
		LinkedList<Reimbursement> riems = new LinkedList<Reimbursement>();	
	
		String query = "select * from all_reimbursements where db_status_id > 3;";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		ResultSet results = null;

		results = st.executeQuery();
		if(results != null)
		{
			while(results.next())
			{
				Reimbursement r = new Reimbursement();
				r.setAmount(results.getBigDecimal(7));
				r.setReimbursementID(results.getInt(1));
				r.setStatus(results.getString(5));
				r.setEmployee(results.getString(4));
				r.setCurrentComment(results.getString(9));
				r.setDetails(results.getString(8));
				r.setDateSubmitted(results.getString(11));
				r.setDateOfTransaction(results.getString(10));
				r.setDateUpdated(results.getString(12));
				r.setExpenseType(results.getString(6));
				r.setEmployeeID(results.getInt(3));
				r.setMerchant(results.getString(13));	
				r.setStatusID(results.getInt(2));
				riems.add(r);
			}
		}

		return riems;
	}

	public static LinkedList<Reimbursement> getAllReimbursements() throws SQLException {
		log.info("in getAllReinbursements Dao Layer");
		LinkedList<Reimbursement> riems = new LinkedList<Reimbursement>();	
	
		String query = "select * from all_reimbursements;";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		ResultSet results = null;

		results = st.executeQuery();
		if(results != null)
		{
			while(results.next())
			{
				Reimbursement r = new Reimbursement();
				r.setAmount(results.getBigDecimal(7));
				r.setReimbursementID(results.getInt(1));
				r.setStatus(results.getString(5));
				r.setEmployee(results.getString(4));
				r.setCurrentComment(results.getString(9));
				r.setDetails(results.getString(8));
				r.setDateSubmitted(results.getString(11));
				r.setDateOfTransaction(results.getString(10));
				r.setDateUpdated(results.getString(12));
				r.setExpenseType(results.getString(6));
				r.setEmployeeID(results.getInt(3));
				r.setMerchant(results.getString(13));
				r.setStatusID(results.getInt(2));
				riems.add(r);
			}
		}

		return riems;
	}

	public static LinkedList<Reimbursement> getEmployeeReimbursements(int employee) throws SQLException {
		log.info("in getEmployeeReimbersements Dao Layer");
		LinkedList<Reimbursement> riems = new LinkedList<Reimbursement>();	
	
		String query = "select * from all_reimbursements where db_employee_id = " + employee + ";";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		ResultSet results = null;

		results = st.executeQuery();
		if(results != null)
		{
			while(results.next())
			{
				Reimbursement r = new Reimbursement();
				r.setAmount(results.getBigDecimal(7));
				r.setReimbursementID(results.getInt(1));
				r.setStatus(results.getString(5));
				r.setEmployee(results.getString(4));
				r.setCurrentComment(results.getString(9));
				r.setDetails(results.getString(8));
				r.setDateSubmitted(results.getString(11));
				r.setDateOfTransaction(results.getString(10));
				r.setDateUpdated(results.getString(12));
				r.setExpenseType(query);;
				r.setEmployeeID(results.getInt(3));
				r.setMerchant(results.getString(13));
				r.setStatusID(results.getInt(2));
				riems.add(r);
			}
		}

		return riems;
	}

	public static LinkedList<Employee> getAllEmployees() throws SQLException {
		log.info("in getAllEmployees Dao Layer");
		LinkedList<Employee> employees = new LinkedList<Employee>();	
	
		String query = "select * from v_employees;";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		ResultSet results = null;

		results = st.executeQuery();
		if(results != null)
		{
			while(results.next())
			{
				Employee e = new Employee();
				e.setEmployeeID(results.getInt(1));
				e.setFullName(results.getString(2));
				e.setEmail(results.getString(3));
				e.setPhone(results.getString(4));
				e.setJobTitle(results.getString(5));
				e.setRoles(getRoles(results.getInt(1)));
				employees.add(e);
				
				// need to add select to get permissions
				
				
				
			}
		}

		return employees;
	}
	
	
	// this can be put in a shared class, but class assignment....
	public static LinkedList<Role> getRoles(int employeeID) throws SQLException
	{
		
		log.info("in getRoles Dao Layer");
		LinkedList<Role> roles = new LinkedList<Role>();	
	
		String query = "select permission_id,permission_type from v_employee_permissions where employee_id= "+ employeeID+";";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		ResultSet results = null;

		results = st.executeQuery();
		if(results != null)
		{
			while(results.next())
			{
				Role r = new Role();
				r.setRoleID(results.getInt(1));
				r.setRole(results.getString(2));
				roles.add(r);

			}
		}

		return roles;
		
		
		
		
	}
	
	
	public static Reimbursement updateReimbursement(Reimbursement rUpdate) throws SQLException  {
		log.info("in getPendingReinbursements Dao Layer");
		Reimbursement r = rUpdate;	
	
		String query =  "call approve_request(?,?,?);";
		
		PreparedStatement st = DBConnection.getConnection().prepareStatement(query);
		st.setInt(1, r.getReimbursementID());
		st.setInt(2, r.getStatusID());
		st.setString(3, r.getCurrentComment());
		st.execute();

		return r;
	}
}
