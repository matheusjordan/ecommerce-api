package ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Client implements AbstractModel{
	private static final long serialVersionUID = 1020414291391356820L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany
	@JoinTable(name = "client_sells")
	private List<Sell> sells;
	
	protected Client() {
	}
	
	public Client(String username, String password, List<Sell> sells) {
		this.username = username;
		this.password = password;
		this.sells = sells;
	}
	
	public Client(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Sell> getSells() {
		return sells;
	}

	public void setSells(List<Sell> sells) {
		this.sells = sells;
	}

	public void addSell(Sell sell) {
		sells.add(sell);
	}
	@Override
	public Long getId() {
		return this.id;
	}
}
