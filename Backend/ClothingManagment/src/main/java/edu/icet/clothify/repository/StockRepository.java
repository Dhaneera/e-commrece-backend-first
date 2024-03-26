package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock,Long> {
}
