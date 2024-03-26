package edu.icet.clothify.service;

import edu.icet.clothify.entity.BillingInfo;

public interface BillingInfoService {
    BillingInfo createBillingInfo(BillingInfo billingInfo);
    BillingInfo updateBillingInfo(Long id, BillingInfo billingInfo);
    void deleteBillingInfo(Long id);
}
