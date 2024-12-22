package oneToMany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateInheritanceExample {

	 public static void main(String[] args) {
	        // Load the configuration and build the SessionFactory
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	        		.applySettings(configuration.getProperties())
	        		.build();
	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	        // Create a session
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
  
	        try {
	            transaction = session.beginTransaction();
	            
	            // Create a new Department
	            Department department = new Department();
	            department.setName("IT");

	            // Create Employees
	            Employee emp1 = new Employee();
	            emp1.setName("John Doe");

	            Employee emp2 = new Employee();
	            emp2.setName("Jane Doe");

	            // Assign employees to the department
	            Set<Employee> employees = new HashSet();
	            employees.add(emp1);
	            employees.add(emp2);
	            department.setEmployees(employees);

	            // Save the department (Employees will be saved automatically due to cascading)
	            session.save(department);
	           
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            sessionFactory.close();
	        }
	    }	            
}