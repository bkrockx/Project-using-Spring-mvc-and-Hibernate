package Service;

import java.util.*;
import java.util.*;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import Model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

@Service("employeeService")
@Transactional
public class EmployeeService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Employee> getAll(Integer projectId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Project as c WHERE c.id="+projectId);
		Project project = (Project)query.uniqueResult();
		return new ArrayList<Employee>(project.getEmployee());
	}

	public List<Employee>getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Employee");
		return query.list();
	}
	
	public Employee get(Integer employeeId){
		Session session = sessionFactory.getCurrentSession();
		Employee employee = (Employee)session.get(Employee.class,employeeId);
		return employee;
	}
	
	public Employee getEmployeeByName(String employeeName){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("employeeName",employeeName));
		
		Object result = criteria.uniqueResult();
		Employee employee = (Employee) result;
		
		return employee;
		
	}
	
	public boolean validate(String employeeName,String password){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("employeeName",employeeName));
		
		boolean flag = false;
		Object result = criteria.uniqueResult();
		if(result!=null){
			Employee employee = (Employee) result;
			if(employee.getPassword().equalsIgnoreCase(password)){
				flag = true;
			}
		}
		if(flag==true){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void add(Integer projectId,Employee employee){
		
		Session session = sessionFactory.getCurrentSession();
	/*	
        Employee emp = new Employee();
        emp.setEmployeeId(employee.getEmployeeId());
        emp.setEmployeeName(employee.getEmployeeName());
        emp.setDesignation(employee.getDesignation());
        emp.setDepartment(employee.getDepartment());
 
		session.save(emp);
	*/
	//	Employee emp = (Employee)session.get(Employee.class,employee.getEmployeeId());
	
		Project project1 = (Project)session.get(Project.class,projectId);
		project1.getEmployee().add(employee);
		
		session.save(project1);
	}
	
	public void addEmp(Employee employee){
		
		Session session = sessionFactory.getCurrentSession();
		
        Employee emp = new Employee();
        emp.setEmployeeId(employee.getEmployeeId());
        emp.setEmployeeName(employee.getEmployeeName());
        emp.setDesignation(employee.getDesignation());
        emp.setDepartment(employee.getDepartment());
 
		session.save(emp);
	}
	
	public void delete(Integer employeeId){
		Session session = sessionFactory.getCurrentSession();
		
		Employee employee = (Employee)session.get(Employee.class,employeeId);
		session.delete(employee);
	}
	
	public void edit(Employee employee){
		Session session = sessionFactory.getCurrentSession();
		Employee employee2 = (Employee)session.get(Employee.class,employee.getEmployeeId());
		employee2.setEmployeeName(employee.getEmployeeName());
		employee2.setDesignation(employee.getDesignation());
		employee2.setDepartment(employee.getDepartment());
		session.save(employee2);
	}
	
	public void editEmp(Employee employee){
		Session session = sessionFactory.getCurrentSession();
		Employee employee1 = (Employee)session.get(Employee.class,employee.getEmployeeId());
		employee1.setEmployeeName(employee.getEmployeeName());
		employee1.setDesignation(employee.getDesignation());
		employee1.setDepartment(employee.getDepartment());
		session.save(employee1);
	}
	
	public void deleteEmp(Integer employeeId){
		Session session = sessionFactory.getCurrentSession();
		
		Employee employee = (Employee)session.get(Employee.class,employeeId);
		session.delete(employee);
	}
	
}



