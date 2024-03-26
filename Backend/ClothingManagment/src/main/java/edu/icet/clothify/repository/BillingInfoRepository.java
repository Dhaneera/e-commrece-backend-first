package edu.icet.clothify.repository;


import edu.icet.clothify.entity.BillingInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInfoRepository extends CrudRepository<BillingInfo,Long> {
}
