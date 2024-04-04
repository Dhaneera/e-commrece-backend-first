package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.BillingInfoDto;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.repository.BillingInfoRepository;
import edu.icet.clothify.service.BillingInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingInfoServiceImpl implements BillingInfoService {

    @Autowired
    ObjectMapper mapper;


    @Autowired
    private final BillingInfoRepository billingInfoRepository;

    @Autowired
    public BillingInfoServiceImpl(BillingInfoRepository billingInfoRepository) {
        this.billingInfoRepository = billingInfoRepository;
    }


    @Override
    public  Boolean createBillingInfo(BillingInfoDto billingInfoDto) {
        BillingInfo billingInfo = mapper.convertValue(billingInfoDto, BillingInfo.class);
        BillingInfo save = billingInfoRepository.save(billingInfo);
        return save.getId()!=null;
    }

    @Override
    public BillingInfo updateBillingInfo(Long id, BillingInfoDto billingInfoDto) {
       if (! billingInfoRepository.existsById(id)){
           throw new ResourceNotFoundException("bill information not found in this id: "+id);
       }
       BillingInfo existingbill= billingInfoRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException("bill information not found with this id: " + id));

        BeanUtils.copyProperties(billingInfoDto,existingbill,"id");
              return billingInfoRepository.save(existingbill);
    }

}
