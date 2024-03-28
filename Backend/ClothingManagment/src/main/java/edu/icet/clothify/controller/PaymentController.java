package edu.icet.clothify.controller;

import edu.icet.clothify.dto.PaymentDto;
import edu.icet.clothify.entity.Payment;
import edu.icet.clothify.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @GetMapping("/add")
    public boolean addPayment(@RequestBody PaymentDto paymentDto){
        return paymentService.addPayment(paymentDto);
    }
    @PostMapping("/update/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody PaymentDto paymentDto){
        return paymentService.upadatePayment(id, paymentDto);
    }
}
