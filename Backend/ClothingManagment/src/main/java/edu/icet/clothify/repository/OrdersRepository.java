package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders,Long> {

}
