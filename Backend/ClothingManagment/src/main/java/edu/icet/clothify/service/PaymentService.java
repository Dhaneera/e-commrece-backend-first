package edu.icet.clothify.service;

import edu.icet.clothify.dto.PaymentDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Payment;

public interface PaymentService {
    public Payment addPayment(Payment payment);
    public Boolean addPayment(PaymentDto paymentDto);
    public Payment upadatePayment(Long id, Payment payment);

}
