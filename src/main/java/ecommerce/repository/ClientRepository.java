package ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>{
}
