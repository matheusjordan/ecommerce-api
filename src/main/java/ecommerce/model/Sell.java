package ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Sell implements AbstractModel {
	private static final long serialVersionUID = -1923987989587463873L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long clientId;

	@OneToMany
	@JoinTable(name = "solded_products")
	private List<ProductSell> products;

	protected Sell() {
	}
	
	public Sell(Long clientId, List<ProductSell> products) {
		this.clientId = clientId;
		this.products = products;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public List<ProductSell> getProducts() {
		return products;
	}

	public void setProducts(List<ProductSell> products) {
		this.products = products;
	}

	@Override
	public Long getId() {
		return this.id;
	}
}
