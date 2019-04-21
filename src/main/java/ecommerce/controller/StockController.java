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

import ecommerce.dto.stock.CreateStockDto;
import ecommerce.dto.stock.UpdateStockDto;
import ecommerce.model.Stock;
import ecommerce.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/stocks")
@Api(value = "Stocks management endpoints")
public class StockController {
	@Autowired
	private StockService service;
	
	@ApiOperation(value = "Create Stock")
	@PostMapping("/")
	public ResponseEntity<Stock> create(@RequestBody CreateStockDto createStock){
		service.create(createStock);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Read Stock")
	@GetMapping("/{id}")
	public ResponseEntity<Stock> read(@PathVariable(value = "id") Long id){
		Stock s = service.read(id);
		return new ResponseEntity<>(s, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Update Stock")
	@PutMapping("/")
	public ResponseEntity<Stock> update(@RequestBody UpdateStockDto updateStock){
		service.update(updateStock);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete Stock")
	@DeleteMapping("/{id}")
	public ResponseEntity<Stock> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Read all Stocks")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Stock>> readAll(Pageable pageable){
		Iterable<Stock> ss = service.readAll(pageable);
		return new ResponseEntity<>(ss, HttpStatus.OK);
	}
}
