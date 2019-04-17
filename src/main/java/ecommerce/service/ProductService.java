package ecommerce.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	private final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository repository;
	
	public void create(Product p) {
		LOG.info("Creating product");
		repository.save(p);
	}
	
	public Product read(Long id) {
		LOG.info("Finding product");
		return repository.findById(id).get();
	}
	
	public void update(Product p) {
		LOG.info("Updating product");
		repository.save(p);
	}
	
	public void delete(Long id) {
		LOG.info("Deleting product");
		repository.deleteById(id);
	}
	
	public Iterable<Product> readAll(Pageable pageable){
		LOG.info("Reading all products");
		return repository.findAll(pageable);
	}
}
