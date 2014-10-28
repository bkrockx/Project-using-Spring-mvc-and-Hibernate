package Model;
import java.io.Serializable;

import javassist.bytecode.ByteArray;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name="employeeId")
	@GeneratedValue
	private Integer employeeId;
	
	@Column(name="employeeName")
	private String employeeName;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="department")
	private String department;
	
	@Column(name="password")
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name="ProjectEmployee",
			joinColumns= @JoinColumn(name="EMPLOYEE_ID")
	)
	private Project project;
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
