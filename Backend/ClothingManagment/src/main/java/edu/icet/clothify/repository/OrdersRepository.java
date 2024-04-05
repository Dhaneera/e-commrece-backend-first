package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders,Long> {

    @Query("SELECT o FROM Orders o WHERE o.customer.id = :customerId")

    List<Orders> findByCustomerId(Long customerId);


}
