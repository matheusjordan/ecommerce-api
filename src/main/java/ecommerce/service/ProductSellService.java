package ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.model.ProductSell;
import ecommerce.repository.ProductSellRepository;

@Service
public class ProductSellService {
	private final Logger LOG = LoggerFactory.getLogger(ProductSellService.class);

	@Autowired
	private ProductSellRepository repository;

	public void create(ProductSell ps) {
		LOG.info("Creating product sell");
		repository.save(ps);
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
}
