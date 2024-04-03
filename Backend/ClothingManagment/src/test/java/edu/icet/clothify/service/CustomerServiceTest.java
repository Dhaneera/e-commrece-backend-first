package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Customer Service Testing")
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
            Long id =1L;
            String customerName = "Shehan";
            String customerAddress = "MountLavinia";
            String customerPhone = "077007987";
            String customerMail = "wickramasuriyashehan@gmail.com";

            Customer customer = Customer.builder().id(id).name(customerName).address(customerAddress).phone(customerPhone).mail(customerMail).build();
            CustomerDto customerDto = CustomerDto.builder().id(id).name(customerName).address(customerAddress).phone(customerPhone).mail(customerMail).build();


            //When
            when(customerRepository.getByName(customerName)).thenReturn(customer);
           when(customerService.getCustomerByName(customerName)).thenReturn(customerDto);

            //Then
            verify(customerRepository).getByName(customerName);

        }
        @Test
        @Order(2)
        @DisplayName("Service Get Customer by Name - Customer Not Found")
        public void CustomerService_GetCustomerByName_CustomerNotFound() {
            // Given
            String nonExistentName = "Shehan";
            // Mock repository to return null (no customer found)
            Mockito.when(customerRepository.getByName(nonExistentName)).thenReturn(null);

            // When
            CustomerDto customerDto = customerService.getCustomerByName(nonExistentName);

            // Then
            // Verify repository call
            verify(customerRepository).getByName(nonExistentName);
            // Assert null result
            assertNull(customerDto);
        }
        @Test
        @Order(3)
        @DisplayName("Service Get Customer by Name - Exception Handled")
        public void CustomerService_GetCustomerByName_HandlesException() {
            // Given
            String nameCausingException = "Error";
            // Mock repository to throw an exception
            Mockito.when(customerRepository.getByName(nameCausingException)).thenThrow(new RuntimeException("Unexpected error"));

            // When
            CustomerDto customerDto = customerService.getCustomerByName(nameCausingException);

            // Then
            // Verify repository call
            verify(customerRepository).getByName(nameCausingException);

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
            CustomerDto updatedDto = createCustomerDto(customerId, "Updated Name");
            Customer existingCustomer = createCustomer(customerId, "Original Name");
            Customer expectedUpdatedCustomer = createCustomer(customerId, "Updated Name");


            // When
            Mockito.when(customerRepository.existsById(customerId)).thenReturn(true);
            Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
            Mockito.when(customerRepository.save(existingCustomer)).thenReturn(expectedUpdatedCustomer);

            Customer updatedCustomer = customerService.updateCustomer(customerId, updatedDto);

            // Then

            verify(customerRepository).existsById(customerId);
            verify(customerRepository).findById(customerId);
            verify(customerRepository).save(existingCustomer);

            assertEquals(expectedUpdatedCustomer, updatedCustomer);
        }

        private CustomerDto createCustomerDto(Long id, String name) {
            CustomerDto dto = new CustomerDto();
            dto.setId(id);
            dto.setName(name);
            return dto;
        }

        private Customer createCustomer(Long id, String name) {
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            return customer;
        }

        @Test
        @Order(2)
        @DisplayName("Service Update Customer - Customer Not Found")
        public void CustomerService_UpdateCustomer_CustomerNotFound() {
            // Given
            Long nonExistentId = 10L;
            CustomerDto anyDto = new CustomerDto();

            Mockito.when(customerRepository.existsById(nonExistentId)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () ->
                    customerService.updateCustomer(nonExistentId, anyDto));
        }




    }

}
