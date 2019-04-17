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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.model.Client;
import ecommerce.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@PostMapping("/")
	public ResponseEntity<Client> create(Client c){
		service.create(c);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> read(@PathVariable(value = "id") Long id){
		Client c = service.read(id);
		return new ResponseEntity<>(c, HttpStatus.FOUND);
	}
	
	@PutMapping("/")
	public ResponseEntity<Client> update(Client c){
		service.update(c);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<Client>> readAll(Pageable pageable){
		Iterable<Client> cs = service.readAll(pageable);
		return new ResponseEntity<>(cs, HttpStatus.OK);
	}
}
