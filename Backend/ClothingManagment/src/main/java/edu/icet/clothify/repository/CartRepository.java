package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

}
