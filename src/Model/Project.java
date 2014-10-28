package Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	@Id
	@Column(name="projectId")
	@GeneratedValue
	private Integer projectId;
	
	@Column(name="projectName")
	private String projectName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="manager")
	private String manager;
	
	@Column(name="fte")
	private int fte;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="ProjectEmployee",
			joinColumns = @JoinColumn(name="PROJECT_ID"),
			inverseJoinColumns = @JoinColumn(name="EMPLOYEE_ID")
	)
	public Set<Employee> employee;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="ProjectClient",
			joinColumns = @JoinColumn(name="PROJECT_ID"),
			inverseJoinColumns = @JoinColumn(name="CLIENT_ID")
	)
	public Set<Client> client;
	
	// Getters and Setters
	
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

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	public Set<Client> getClient() {
		return client;
	}

	public void setClient(Set<Client> client) {
		this.client = client;
	}	

}
