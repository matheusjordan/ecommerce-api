package ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ecommerce.dto.productSell.CreateProductSellDto;
import ecommerce.model.ProductSell;
import ecommerce.service.ProductSellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("productssells")
@Api(value = "Product sells management endpoint")
public class ProductSellController {

	@Autowired
	private ProductSellService service;

	@ApiOperation(value = "Create Product Sell")
	@PostMapping("/")
	public ResponseEntity<ProductSell> create(@RequestBody CreateProductSellDto createProductSell) {
		service.create(createProductSell);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Read Product Sell")
	@GetMapping("/{id}")
	public ResponseEntity<ProductSell> read(@PathVariable(value = "id") Long id) {
		ProductSell ps = service.read(id);
		return new ResponseEntity<>(ps, HttpStatus.FOUND);
	}

	/**
	 * For security reasons this function commented is.
	 * If you want to update a product sell,
	 * Go to database and update yourself.
	 * @author Matheus Jordan
	 */
//	@ApiOperation(value = "Update Product Sell")
//	@PutMapping("/")
//	public ResponseEntity<ProductSell> update(@RequestBody ProductSell ps){
//		service.update(ps);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	/**
	 * For security reasons this function commented is.
	 * If you want to delete a product sell,
	 * Go to database and delete yourself.
	 * @author Matheus Jordan
	 */
//	@ApiOperation(value = "Delete Product Sell")
//	@DeleteMapping("/{id}")
//	public ResponseEntity<ProductSell> delete(@PathVariable(value = "id") Long id){
//		service.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@ApiOperation(value = "Read all Products Sells")
	@GetMapping("/all")
	public ResponseEntity<Iterable<ProductSell>> readAll(Pageable pageable) {
		Iterable<ProductSell> pss = service.readAll(pageable);
		return new ResponseEntity<>(pss, HttpStatus.OK);
	}
}
