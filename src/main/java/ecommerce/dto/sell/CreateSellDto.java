package ecommerce.dto.sell;

import java.util.List;

import ecommerce.dto.productSell.CreateProductSellDto;

public class CreateSellDto {
	private Long clientId;
	private List<CreateProductSellDto> products;

	public CreateSellDto(Long clientId, List<CreateProductSellDto> products) {
		this.clientId = clientId;
		this.products = products;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public List<CreateProductSellDto> getProducts() {
		return products;
	}

	public void setProducts(List<CreateProductSellDto> products) {
		this.products = products;
	}
}
