package ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.ProductSell;

@Repository
public interface ProductSellRepository extends PagingAndSortingRepository<ProductSell, Long>{
}
