package ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ecommerce.model.Client;
import ecommerce.repository.ClientRepository;

@Service
public class ClientService {
	private final Logger LOG = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private ClientRepository repository;
	
	public void create(Client c) {
		LOG.info("Creating client");
		repository.save(c);
	}
	
	public Client read(Long id) {
		LOG.info("Finding client");
		return repository.findById(id).get();
	}
	
	public void update(Client c) {
		LOG.info("Updating client");
		repository.save(c);
	}
	
	public void delete(Long id) {
		LOG.info("Deleting client");
		repository.deleteById(id);
	}
	
	public Iterable<Client> readAll(Pageable pageable){
		LOG.info("Reading all clients");
		return repository.findAll(pageable);
	}
}
