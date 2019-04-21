package ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.Sell;

@Repository
public interface SellRepository extends PagingAndSortingRepository<Sell, Long>{

}
