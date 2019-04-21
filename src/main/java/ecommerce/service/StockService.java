package ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.dto.stock.CreateStockDto;
import ecommerce.dto.stock.UpdateStockDto;
import ecommerce.model.Product;
import ecommerce.model.Stock;
import ecommerce.repository.StockRepository;

@Service
public class StockService {
	private static final Logger LOG = LoggerFactory.getLogger(StockService.class);
	
	@Autowired
	private StockRepository repository;
	
	@Autowired
	private ProductService productSer;
	
	public void create(CreateStockDto createStock) {
		LOG.info("Creating stock");
		Stock s = this.createStockToStock(createStock);
		repository.save(s);
	}
	
	public Stock read(Long id) {
		LOG.info("Finding stock");
		return repository.findById(id).get();
	}
	
	public void update(UpdateStockDto updateStock) {
		LOG.info("Updating stock");
		Stock s = this.updateStockToStock(updateStock);
		repository.save(s);
	}
	
	public void delete(Long id) {
		LOG.info("Deleting stock");
		repository.deleteById(id);
	}
	
	public Iterable<Stock> readAll(Pageable pageable){
		LOG.info("Reading all stocks");
		return repository.findAll(pageable);
	}
	
	protected Stock createStockToStock(CreateStockDto createStock) {
		Integer amount = createStock.getAmount();
		Long id = createStock.getProductId();
		Product p = productSer.read(id);
		Stock s = new Stock(amount, p);
		return s;
	}
	
	protected Stock updateStockToStock(UpdateStockDto updateStock) {
		Integer amount = updateStock.getAmount();
		Long id = updateStock.getId();
		Long productId = updateStock.getProductId();
		Product p = productSer.read(productId);
		Stock s = this.read(id);
		s.setAmount(amount);
		s.setProduct(p);
		return s;
	}
}
