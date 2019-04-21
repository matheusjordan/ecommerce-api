package ecommerce.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.dto.client.CreateClientDto;
import ecommerce.dto.client.ReadClientDto;
import ecommerce.dto.client.UpdateClientDto;
import ecommerce.model.Client;
import ecommerce.model.Sell;
import ecommerce.repository.ClientRepository;

@Service
public class ClientService {
	private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	public void create(CreateClientDto createClient) {
		LOG.info("Creating client");
		
		String password = createClient.getPassword();
		password = passEncoder.encode(password);
		createClient.setPassword(password);
		
		Client c = this.CreateClientToClient(createClient);
		repository.save(c);
	}
	
	public ReadClientDto read(Long id) {
		LOG.info("Finding client");
		Client c = repository.findById(id).get();
		ReadClientDto getClient = this.ClientToGetClientDto(c);
		return getClient;
	}
	
	public void update(UpdateClientDto updateClient) {
		LOG.info("Updating client");
		Client c = this.UpdateClientToClient(updateClient);
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
	
	//Function to convert Client in ReadClient DTO
	protected ReadClientDto ClientToGetClientDto(Client c) {
		Long id = c.getId();
		System.out.println(id);
		String username = c.getUsername();
		List<Sell> sells = c.getSells();
		ReadClientDto getClient = new ReadClientDto(id, username, sells);
		return getClient;
	}
	
	//Function to convert CreateClient DTO in Client
	protected Client CreateClientToClient(CreateClientDto createClient) {
		String username = createClient.getUsername();
		String password = createClient.getPassword();
		Client c = new Client(username, password);
		return c;
	}
	
	//Function to convert UpdateClientDto in Client
	protected Client UpdateClientToClient(UpdateClientDto updateClient) {
		Long id = updateClient.getId();
		String password = updateClient.getPassword();
		Client c = repository.findById(id).get();
		
		//Criar anotação para validar isso - not implemented
		boolean passEmpty =  password.equals("");
		
		c.setPassword(passEmpty ? c.getPassword() : password);
		return c;
	}
	
	//Function to find Client By Id
	public Client readById(Long id) {
		return repository.findById(id).get();
	}
}
