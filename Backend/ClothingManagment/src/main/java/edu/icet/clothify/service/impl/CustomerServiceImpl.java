package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.repository.CustomerRepository;
import edu.icet.clothify.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;



    @Override
    public Boolean addCustomer(CustomerDto customerDto) {
        Customer customer = mapper.convertValue(customerDto, Customer.class);
        Customer save = customerRepository.save(customer);
        return save.getId() != null;
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)){
            throw  new ResourceNotFoundException("Customer not found in this id: "+id);
        }

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with this id: " + id));
        BeanUtils.copyProperties(customerDto, existingCustomer, "id");

        return customerRepository.save(existingCustomer);
    }



    @Override
    public CustomerDto getCustomerByName(String  name) {
        try {
            Customer customer = customerRepository.getByName(name);
            return mapper.convertValue(customer , CustomerDto.class);
        }catch (Exception exception){
            return null;

        }
    }


}
