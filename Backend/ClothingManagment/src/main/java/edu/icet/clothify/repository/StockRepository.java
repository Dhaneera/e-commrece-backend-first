package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface StockRepository extends CrudRepository<Stock,Long> {


}
