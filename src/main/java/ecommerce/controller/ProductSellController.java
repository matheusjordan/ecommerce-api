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

import ecommerce.model.ProductSell;
import ecommerce.service.ProductSellService;

public class ProductSellController {
	
	@Autowired
	private ProductSellService service;
	
	@PostMapping("/")
	public ResponseEntity<ProductSell> create(@RequestBody ProductSell ps){
		service.create(ps);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductSell> read(@PathVariable(value = "id") Long id){
		ProductSell ps = service.read(id);
		return new ResponseEntity<>(ps, HttpStatus.FOUND);
	}
	
	@PutMapping("/")
	public ResponseEntity<ProductSell> update(@RequestBody ProductSell ps){
		service.update(ps);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductSell> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<ProductSell>> readAll(Pageable pageable){
		Iterable<ProductSell> pss = service.readAll(pageable);
		return new ResponseEntity<>(pss, HttpStatus.OK);
	}
}
