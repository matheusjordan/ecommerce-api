package ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.dto.product.CreateProductDto;
import ecommerce.model.Product;
import ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/products")
@Api(value = "Products management endpoints")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@ApiOperation(value = "Create Product")
	@PostMapping("/")
	public ResponseEntity<Product> create(@RequestBody CreateProductDto createProduct){
		service.create(createProduct);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Read Product")
	@GetMapping("/{id}")
	public ResponseEntity<Product> read(@PathVariable(value = "id") Long id){
		Product p = service.read(id);
		return new ResponseEntity<>(p, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Update Product")
	@PutMapping("/")
	public ResponseEntity<Product> update(@RequestBody Product p){
		service.update(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete Product")
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Read all Products")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Product>> readAll(Pageable pageable){
		Iterable<Product> ps = service.readAll(pageable);
		return new ResponseEntity<>(ps, HttpStatus.OK);
	}
}
