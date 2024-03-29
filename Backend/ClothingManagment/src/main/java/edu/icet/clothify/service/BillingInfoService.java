package edu.icet.clothify.service;

import edu.icet.clothify.dto.BillingInfoDto;
import edu.icet.clothify.entity.BillingInfo;

public interface BillingInfoService {
    Boolean createBillingInfo(BillingInfoDto billingInfoDto);
    BillingInfo updateBillingInfo(Long id, BillingInfoDto billingInfoDto);
    Boolean deleteBillingInfo(Long id);
}
