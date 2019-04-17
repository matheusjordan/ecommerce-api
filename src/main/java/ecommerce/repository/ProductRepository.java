package ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ecommerce.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
}
