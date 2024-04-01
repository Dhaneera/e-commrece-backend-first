package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product getByName(String name);
}
