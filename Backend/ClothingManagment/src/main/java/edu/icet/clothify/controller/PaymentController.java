package edu.icet.clothify.controller;

import edu.icet.clothify.dto.PaymentDto;
import edu.icet.clothify.entity.Payment;
import edu.icet.clothify.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> error(MethodArgumentNotValidException exception){
        Map <String, String> map=new HashMap<>();
        List<ObjectError> list=exception.getBindingResult().getAllErrors();
        for(ObjectError item:list) {
            FieldError fieldError=(FieldError) item;
            String fieldName= fieldError.getField();
            String message = item.getDefaultMessage();
            map.put(fieldName,message);
        }
        return map;
    }
}
