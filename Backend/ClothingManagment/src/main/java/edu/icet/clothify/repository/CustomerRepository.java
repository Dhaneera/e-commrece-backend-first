package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer getByName(String name);

}
