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
@Table(name="client")
public class Client {
	
	@Id
	@Column(name="clientId")
	@GeneratedValue
	private Integer clientId;
	
	@Column(name="clientName")
	private String clientName;
	
	@Column(name="clientAddress")
	private String clientAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name="ProjectClient",
			joinColumns= @JoinColumn(name="CLIENT_ID")
	)
	private Project project;
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
