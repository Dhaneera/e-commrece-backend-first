package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.repository.BillingInfoRepository;
import edu.icet.clothify.service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingInfoServiceImpl implements BillingInfoService {

    @Autowired
    ObjectMapper mapper;


    @Autowired
    private final BillingInfoRepository billingInfoRepository;

    public BillingInfoServiceImpl(BillingInfoRepository billingInfoRepository) {
        this.billingInfoRepository = billingInfoRepository;
    }


    @Override
    public BillingInfo createBillingInfo(BillingInfo billingInfo) {
        return billingInfoRepository.save(billingInfo);
    }

    @Override
    public BillingInfo updateBillingInfo(Long id, BillingInfo billingInfo) {
        if (!billingInfoRepository.existsById(id)){
            throw new ResourceNotFoundException("billing info not available for this id: "+id);
        }
        billingInfo.setId(id);
        return billingInfoRepository.save(billingInfo);
    }

    @Override
    public void deleteBillingInfo(Long id) {
        if (billingInfoRepository.existsById(id)){
            billingInfoRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("billing info not available for this id to delete: "+id);
        }

    }
}
