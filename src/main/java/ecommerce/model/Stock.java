package ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock implements AbstractModel {
	private static final long serialVersionUID = -1225633974024456685L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer amount;

	@Column(nullable = false, unique = true)
	private Product product;

	public Stock(Integer amount, Product product) {
		this.amount = amount;
		this.product = product;
	}

	public final Integer getAmount() {
		return amount;
	}

	public final void setAmount(Integer amount) {
		this.amount = amount;
	}

	public final Product getProduct() {
		return product;
	}

	public final void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public Long getId() {
		return this.id;
	}
}