package ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductSell implements AbstractModel {
	private static final long serialVersionUID = 7241950029777328743L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer Amount;

	@OneToOne
	private Product product;
	
	protected ProductSell() {
	}

	public ProductSell(Integer amount, Product product) {
		Amount = amount;
		this.product = product;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public Long getId() {
		return this.id;
	}

}
