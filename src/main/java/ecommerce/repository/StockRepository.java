package ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.Stock;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Long>{
}
