package ecommerce.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.dto.productSell.CreateProductSellDto;
import ecommerce.dto.sell.CreateSellDto;
import ecommerce.model.Client;
import ecommerce.model.ProductSell;
import ecommerce.model.Sell;
import ecommerce.repository.SellRepository;

@Service
public class SellService {
	private static final Logger LOG = LoggerFactory.getLogger(SellService.class);
	
	@Autowired
	private SellRepository repository;
	
	@Autowired
	private ProductSellService productSellSer;
	
	@Autowired
	private ClientService clientSer;
	
	public void create(CreateSellDto createSell) {
		LOG.info("Creating a sell");
		Long clientId = createSell.getClientId();
		
		Client c = clientSer.readById(clientId);
		Sell s = this.createSellToSell(createSell);
		
		c.addSell(s);
		repository.save(s);
	}
	
	public Sell read(Long id) {
		LOG.info("Finding a Sell");
		Sell s = repository.findById(id).get();
		return s;
	}
	
	public void update(Sell s) {
		LOG.info("Updating a sell");
		repository.save(s);
	}
	
	public void delete(Long id) {
		LOG.info("Deleting a sell");
		repository.deleteById(id);
	}
	
	public Iterable<Sell> readAll(Pageable pageable) {
		LOG.info("Finding all sells");
		Iterable<Sell> ss = repository.findAll(pageable);
		return ss;
	}
	
	//Function to convert CreateSell DTO to Sell
	protected Sell createSellToSell(CreateSellDto createSell) {
		List<CreateProductSellDto> createProductsSells = createSell.getProducts();
		Long clientId = createSell.getClientId();

		List<ProductSell> productsSells = productSellSer.CreateProductsSellsToProductsSells(createProductsSells);
		Sell s = new Sell(clientId, productsSells);
		return s;
	}
}
