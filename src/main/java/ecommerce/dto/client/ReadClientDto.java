package ecommerce.dto.client;

import java.util.List;

import ecommerce.model.Sell;

public class ReadClientDto {
	private Long id;
	private String username;
	private List<Sell> sells;
	
	public ReadClientDto(Long id, String username, List<Sell> sells) {
		this.id = id;
		this.username = username;
		this.sells = sells;
	}
	
	public ReadClientDto() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Sell> getSells() {
		return sells;
	}

	public void setSells(List<Sell> sells) {
		this.sells = sells;
	}
}
