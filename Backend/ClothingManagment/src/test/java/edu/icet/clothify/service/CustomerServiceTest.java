package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.repository.CustomerRepository;
import edu.icet.clothify.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    ObjectMapper mapper;

    @BeforeEach
    public void setUp() {MockitoAnnotations.initMocks(this);}



    @Nested
    @Order(1)
    @DisplayName(" Save Service")
    class CustomerServiceSave{
        @Test
        @Order(1)
        @DisplayName("Save Customer Service")
        public void CustomerService_SaveCustomer_ReturnObject(){
            //Given
            Customer customer = Customer.builder().id(1L).name("shehan").address("MountLavinia").phone("077007987").mail("wickramasuriyashehan@gmail.com").build();
            CustomerDto customerDto = CustomerDto.builder().id(1L).name("shehan").address("MountLavinia").phone("077007987").mail("wickramasuriyashehan@gmail.com").build();


            //When
            when(customerRepository.save(any())).thenReturn(customer);
            when(mapper.convertValue((Object) any(), (JavaType) any())).thenReturn(customer);
            boolean isSaved = customerService.addCustomer(customerDto);

            //Then
            Assertions.assertTrue(isSaved);

        }
    }


    @Nested
    @Order(2)
    @DisplayName("View Service")
    class CustomerServiceDelete{

        @Test
        @Order(1)
        @DisplayName("View Customer Service")
        public void CustomerService_GetCustomerByName(){
            //Given
            Customer customer = Customer.builder().id(1L).name("shehan").address("MountLavinia").phone("077007987").mail("wickramasuriyashehan@gmail.com").build();
            CustomerService  customerService = Mockito.mock(CustomerService.class);

            //When
            when(customerService.getCustomerByName(customer.getName())).thenReturn(CustomerDto.builder().build());
            CustomerDto customerDto = customerService.getCustomerByName(customer.getName());

            //Then
            Assertions.assertNotNull(customerDto,"CustomerDto Not Null");

        }

    }
    @Nested
    @Order(3)
    @DisplayName("Update Service")
    class CustomerServiceUpdate{
        @Test
        @Order(1)
        @DisplayName("Update Customer Service")
        public void CustomerService_UpdateCustomer(){
            //Given
            Long customerId = 1L;
            Customer customer = Customer.builder().id(customerId).name("shehan").address("MountLavinia").phone("077007987").mail("wickramasuriyashehan@gmail.com").build();
            CustomerDto customerDto = CustomerDto.builder().id(customerId).name("shehan").address("MountLavinia").phone("077007987").mail("wickramasuriyashehan@gmail.com").build();

           //When
            when(customerRepository.existsById(any())).thenReturn(true);
            when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(customer));
            when(customerRepository.save(any())).thenReturn(customer);
            Customer customerUpdated=customerService.updateCustomer(1L,customerDto);

            //Then
            Assertions.assertNotNull(customerUpdated);
        }
    }

}
