package Service;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import Model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@Transactional
public class ProjectService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Project> getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Project");
		return query.list();
	}
	
	public Project get(Integer projectId){
		Session session = sessionFactory.getCurrentSession();
		return (Project)session.get(Project.class,projectId);
	}
	
	public Project getProjectByName(String projectName){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Project.class);
		criteria.add(Restrictions.like("projectName",projectName));
		
		Object result = criteria.uniqueResult();
		Project project = (Project) result;
		
		return project;
		
	}
	
	public void add(Project project) {
		Session session = sessionFactory.getCurrentSession();
		session.save(project);
	}
	
	public void delete(Integer projectId){
		Session session = sessionFactory.getCurrentSession();
		Project project = (Project)session.get(Project.class,projectId);
		session.delete(project);
	}

	public void edit(Project project,Integer projectId){
		Session session = sessionFactory.getCurrentSession();
		Project project2 = (Project)session.get(Project.class,projectId);
		
		//project2.setProjectId(project.getProjectId());
		project2.setProjectName(project.getProjectName());
		project2.setDescription(project.getDescription());
		project2.setManager(project.getManager());
		project2.setFte(project.getFte());
		
		session.save(project2);
	}
	
}
