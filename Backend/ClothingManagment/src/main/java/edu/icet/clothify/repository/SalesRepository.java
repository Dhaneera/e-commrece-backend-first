package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends CrudRepository<Sales,Long> {
}
