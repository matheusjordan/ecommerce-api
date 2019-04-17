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

import ecommerce.model.Stock;
import ecommerce.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	@Autowired
	private StockService service;
	
	@PostMapping("/")
	public ResponseEntity<Stock> create(@RequestBody Stock s){
		service.create(s);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Stock> read(@PathVariable(value = "id") Long id){
		Stock s = service.read(id);
		return new ResponseEntity<>(s, HttpStatus.FOUND);
	}
	
	@PutMapping("/")
	public ResponseEntity<Stock> update(@RequestBody Stock s){
		service.update(s);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Stock> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<Stock>> readAll(Pageable pageable){
		Iterable<Stock> ss = service.readAll(pageable);
		return new ResponseEntity<>(ss, HttpStatus.OK);
	}
}
