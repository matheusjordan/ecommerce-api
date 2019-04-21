package ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.dto.productSell.CreateProductSellDto;
import ecommerce.model.Product;
import ecommerce.model.ProductSell;
import ecommerce.repository.ProductSellRepository;

@Service
public class ProductSellService {
	private static final Logger LOG = LoggerFactory.getLogger(ProductSellService.class);

	@Autowired
	private ProductSellRepository repository;
	
	@Autowired
	private ProductService productSer;

	/**
	 * Method returning the created product
	 * Because the return is used in CreateProductsSellsToProductsSells()
	 * Prevening TRANSIENT OBJECT EXCEPTION UNSAVED
	 * */
	public ProductSell create(CreateProductSellDto createProductSell) {
		LOG.info("Creating product sell");
		ProductSell ps = this.CreateProductSellToProductSell(createProductSell);
		repository.save(ps);
		return ps;
	}

	public ProductSell read(Long id) {
		LOG.info("Finding product sell");
		return repository.findById(id).get();
	}

	public void update(ProductSell ps) {
		LOG.info("Updating product sell");
		repository.save(ps);
	}

	public void delete(Long id) {
		LOG.info("Deleting product sell");
		repository.deleteById(id);
	}

	public Iterable<ProductSell> readAll(Pageable pageable) {
		Iterable<ProductSell> pss = repository.findAll(pageable);
		return pss;
	}
	
	//Function to convert PrductSell DTO to productSell
	protected ProductSell CreateProductSellToProductSell(CreateProductSellDto createProductSell) {
		Integer amount = createProductSell.getAmount();
		Long id = createProductSell.getProductId();
		Product p = productSer.read(id);
		ProductSell ps = new ProductSell(amount, p);
		return ps;
	}
	
	public List<ProductSell> CreateProductsSellsToProductsSells(List<CreateProductSellDto> createProductsSells){
		List<ProductSell> productsSells = new ArrayList<>();
		
		for(CreateProductSellDto cps : createProductsSells) {
			ProductSell ps = this.create(cps);
			productsSells.add(ps);
		}
		
		return productsSells;
	}
}
