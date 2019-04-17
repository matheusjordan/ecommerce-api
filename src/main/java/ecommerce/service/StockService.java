package ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.model.Stock;
import ecommerce.repository.StockRepository;

@Service
public class StockService {
	private final Logger LOG = LoggerFactory.getLogger(StockService.class);
	
	@Autowired
	private StockRepository repository;
	
	public void create(Stock s) {
		LOG.info("Creating product");
		repository.save(s);
	}
	
	public Stock read(Long id) {
		LOG.info("Finding stock");
		return repository.findById(id).get();
	}
	
	public void update(Stock s) {
		LOG.info("Updating stock");
		repository.save(s);
	}
	
	public void delete(Long id) {
		LOG.info("Deleting stock");
		repository.deleteById(id);
	}
	
	public Iterable<Stock> readAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}
