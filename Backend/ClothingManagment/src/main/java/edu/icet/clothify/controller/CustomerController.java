package edu.icet.clothify.controller;

import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public boolean addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }
    @GetMapping("/get/{name}")
    public CustomerDto getCustomerByName(@PathVariable String name){
        return customerService.getCustomerByName(name);
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

}
