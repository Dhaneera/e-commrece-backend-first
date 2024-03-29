package edu.icet.clothify.repository;

import edu.icet.clothify.entity.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends CrudRepository<Collection,Long> {
    Collection getByName(String name);
}
