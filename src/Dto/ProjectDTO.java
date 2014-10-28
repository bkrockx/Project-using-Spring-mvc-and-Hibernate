package Dto;
import java.util.*;

import Model.*;

public class ProjectDTO {
	
	private Integer projectId;
	private String projectName;
	private String description;
	private String manager;
	private int fte;
	
	private List<Employee> employee;
	private List<Client> client;
	
	public Integer getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getManager() {
		return manager;
	}
	
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public int getFte() {
		return fte;
	}
	
	public void setFte(int fte) {
		this.fte = fte;
	}
	
	public List<Employee> getEmployee() {
		return employee;
	}
	
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	public List<Client> getClient() {
		return client;
	}
	
	public void setClient(List<Client> client) {
		this.client = client;
	}

}
 