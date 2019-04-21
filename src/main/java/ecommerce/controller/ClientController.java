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

import ecommerce.dto.client.CreateClientDto;
import ecommerce.dto.client.ReadClientDto;
import ecommerce.dto.client.UpdateClientDto;
import ecommerce.model.Client;
import ecommerce.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clients")
@Api(value = "Clients management endpoints")
public class ClientController {

	@Autowired
	private ClientService service;

	@ApiOperation(value = "Create Client")
	@PostMapping("/signup")
	public ResponseEntity<Client> create(@RequestBody CreateClientDto createClient){
		service.create(createClient);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Read Client")
	@GetMapping("/{id}")
	public ResponseEntity<ReadClientDto> read(@PathVariable(value = "id") Long id){
		ReadClientDto getClient = service.read(id);
		return new ResponseEntity<>(getClient, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Update Client")
	@PutMapping("/")
	public ResponseEntity<Client> update(@RequestBody UpdateClientDto updateClient){
		service.update(updateClient);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete Client")
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Read all clients")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Client>> readAll(Pageable pageable){
		Iterable<Client> cs = service.readAll(pageable);
		return new ResponseEntity<>(cs, HttpStatus.OK);
	}
}
