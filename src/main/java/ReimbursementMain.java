import java.math.BigDecimal;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entity.ReimbursementEntity;

public class ReimbursementMain {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		ReimbursementEntity addNewReim = new ReimbursementEntity(7, 2, 4, 1, "2020-11-11", BigDecimal.ZERO, "hotel",
				"expedia");

		session.save(addNewReim);
		System.out.println("reimbursement saved");

		transaction.commit();
		System.out.println("reimbursment committed");

		session.close();
		System.out.println("closed");

//--------------------------------------------------------------------------------------------------

		SessionFactory sessionFactory1 = HibernateUtil.getSessionFactory();

		Session session1 = sessionFactory1.openSession();

		Transaction transaction1 = session1.beginTransaction();

		ReimbursementEntity deleteReim = new ReimbursementEntity(7, 2, 4, 1, "2020-11-11", BigDecimal.ZERO, "hotel",
				"expedia");

		session1.delete(deleteReim);
		System.out.println("reimbursement deleted");

		transaction1.commit();
		System.out.println("reimbursment committed");

		session1.close();
		System.out.println("closed");

		// -------------------------------------------------------------------------------------------------

		SessionFactory sessionFactory2 = HibernateUtil.getSessionFactory();

		Session session2 = sessionFactory2.openSession();

		Transaction transaction2 = session2.beginTransaction();

		ReimbursementEntity updateReim = new ReimbursementEntity(7, 2, 4, 1, "2020-11-11", BigDecimal.ZERO, "travel",
				"expedia");

		session2.update(updateReim);
		System.out.println("reimbursement deleted");

		transaction2.commit();
		System.out.println("reimbursment committed");

		session2.close();
		System.out.println("closed");
		
		//-----------------------------------------------------------------------------------------------
		SessionFactory sessionFactory3 = HibernateUtil.getSessionFactory();

		Session session3 = sessionFactory3.openSession();

		Transaction transaction3 = session3.beginTransaction();

		ReimbursementEntity saveReim = new ReimbursementEntity(7, 2, 4, 1, "2020-11-11", BigDecimal.ZERO, "travel",
				"expedia");

		session3.save(saveReim);
		System.out.println("reimbursement deleted");

		transaction3.commit();
		System.out.println("reimbursment committed");

		session3.close();
		System.out.println("closed");
		
		//-------------------------------------------------------------------------------------------
		SessionFactory sessionFactory4 = HibernateUtil.getSessionFactory();

		Session session4 = sessionFactory4.openSession();

		Transaction transaction4 = session4.beginTransaction();

		ReimbursementEntity fetchedReim = session4.find(ReimbursementEntity.class, 2);
		System.out.println(fetchedReim);

		transaction4.commit();
		System.out.println(fetchedReim);

		session4.close();
		System.out.println("closed");

		HibernateUtil.shutdown();
	}
}
	
	
	
	




