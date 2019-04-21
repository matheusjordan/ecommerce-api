package ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.dto.sell.CreateSellDto;
import ecommerce.model.Sell;
import ecommerce.service.SellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sells")
@Api(value = "Sells managagement endpoints")
public class SellController {
	
	@Autowired
	private SellService service;
	
	@PostMapping("/")
	@ApiOperation(value = "Create Sell")
	public ResponseEntity<Sell> create(@RequestBody CreateSellDto createSell){
		service.create(createSell);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Read Sell")
	public ResponseEntity<Sell> read(@PathVariable(value = "id") Long id){
		Sell s = service.read(id);
		return new ResponseEntity<>(s, HttpStatus.FOUND);
	}
	/**
	 * For security reasons this function commented is.
	 * If you want to update a sell,
	 * Go to database and update yourself.
	 * @author Matheus Jordan
	 */
//	@ApiOperation(value = "Update Sell")
//	@PutMapping("/")
//	public ResponseEntity<Sell> update(@RequestBody Sell s){
//		service.update(s);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	/**
	 * For security reasons this function commented is.
	 * If you want to delete a sell,
	 * Go to database and delete yourself.
	 * @author Matheus Jordan
	 */
//	@ApiOperation(value = "Delete Sell")
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Sell> delete(@PathVariable(value = "id") Long id){
//		service.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	@ApiOperation(value = "Read all Sells")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Sell>> readAll(Pageable pageable){
		Iterable<Sell> ss = service.readAll(pageable);
		return new ResponseEntity<>(ss, HttpStatus.OK);
	}
}
