package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.PaymentDto;
import edu.icet.clothify.entity.Payment;
import edu.icet.clothify.repository.PaymentRepository;
import edu.icet.clothify.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ObjectMapper mapper;


    @Autowired
    private  final  PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Boolean addPayment(PaymentDto paymentDto) {
        Payment payment=mapper.convertValue(paymentDto,Payment.class);
        Payment saved = paymentRepository.save(payment);
        return saved.getId()!=null;
    }

    @Override
    public Payment upadatePayment(Long id, Payment payment) {
        if (!paymentRepository.existsById(id)) throw new ResourceNotFoundException("Payment not found with id: " + id);
        payment.setId(id);
        return paymentRepository.save(payment);
    }
}
