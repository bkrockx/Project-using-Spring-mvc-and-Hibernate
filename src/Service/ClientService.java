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

@Service("clientService")
@Transactional
public class ClientService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Client> getAll(Integer projectId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Project as c WHERE c.id="+projectId);
		Project project = (Project)query.uniqueResult();
		return new ArrayList<Client>(project.getClient());
	}

	public List<Client>getAll(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Client");
		return query.list();
	}
	
	public Client get(Integer clientId){
		Session session = sessionFactory.getCurrentSession();
		Client client = (Client)session.get(Client.class,clientId);
		return client;
	}
	
	public Client getClientByName(String clientName){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.like("clientName",clientName));
		Object result = criteria.uniqueResult();
		Client client = (Client) result;
		return client;
	}
	
	public void add(Integer projectId,Client client){
		
		Session session = sessionFactory.getCurrentSession();
	/*
        Client client1 = new Client();
        client1.setClientId(client.getClientId());
        client1.setClientName(client.getClientName());
        client1.setClientAddress(client.getClientAddress());
        
		session.save(client1);
	*/
		Project project1 = (Project)session.get(Project.class,projectId);
		project1.getClient().add(client);
		
		session.save(project1);
	}
	
	public void addClient(Client client){
		
		Session session = sessionFactory.getCurrentSession();
		
        Client client1 = new Client();
        client1.setClientId(client.getClientId());
        client1.setClientName(client.getClientName());
        client1.setClientAddress(client.getClientAddress());
		session.save(client1);
	}
	
	public void delete(Integer clientId){
		Session session = sessionFactory.getCurrentSession();
		
		Client client = (Client)session.get(Client.class,clientId);
		session.delete(client);
	}
	
	public void edit(Client client){
		Session session = sessionFactory.getCurrentSession();
		
		Client client2 = (Client)session.get(Client.class,client.getClientId());
		client2.setClientName(client.getClientName());
		client2.setClientAddress(client.getClientAddress());
		
		session.save(client2);
	}
	
	public void editClient(Client client){
		Session session = sessionFactory.getCurrentSession();
		Client client1 = (Client)session.get(Client.class,client.getClientId());
		client1.setClientName(client.getClientName());
		client1.setClientAddress(client.getClientAddress());
		session.save(client1);
	}
	
}




