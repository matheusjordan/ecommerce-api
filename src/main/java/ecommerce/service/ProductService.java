package ecommerce.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.dto.product.CreateProductDto;
import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository repository;
	
	public void create(CreateProductDto createProduct) {
		LOG.info("Creating product");
		Product p = this.CreateProductToProduct(createProduct);
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
	
	// Function to convert CreateProduct DTO in Product
	protected Product CreateProductToProduct(CreateProductDto createProduct) {
		String name = createProduct.getName();
		Double price = createProduct.getPrice();
		Product p = new Product(name, price);
		return p;
	}
	
	// Function to get a product list by ids
	public List<Product> getProducts(List<Long> ids){
		List<Product> ps = new ArrayList<Product>();
		
		for(Long id : ids) {
			try {
				Product p = read(id);
				ps.add(p);
			} catch (Exception e) {
				throw e;
			}
		}
		return ps;
	}
}
