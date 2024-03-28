package edu.icet.clothify.service;

import edu.icet.clothify.dto.PaymentDto;
import edu.icet.clothify.entity.Payment;

public interface PaymentService {

    public Boolean addPayment(PaymentDto paymentDto);
    public Payment upadatePayment(Long id, PaymentDto paymentDto);

}
