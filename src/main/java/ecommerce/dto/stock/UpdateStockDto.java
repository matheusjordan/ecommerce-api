package ecommerce.dto.stock;

public class UpdateStockDto extends CreateStockDto{
	private Long id;

	public UpdateStockDto(Integer amount, Long productId, Long id) {
		super(amount, productId);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
