package ecommerce.dto.stock;

public class CreateStockDto {
	private Integer amount;
	private Long productId;

	public CreateStockDto(Integer amount, Long productId) {
		this.amount = amount;
		this.productId = productId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
