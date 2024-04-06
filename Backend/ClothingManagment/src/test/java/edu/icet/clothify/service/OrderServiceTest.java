package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.repository.CartRepository;
import edu.icet.clothify.repository.CustomerRepository;
import edu.icet.clothify.repository.OrdersRepository;
import edu.icet.clothify.service.impl.CartServiceImpl;
import edu.icet.clothify.service.impl.OrdersServiceImpl;
import edu.icet.clothify.util.enums.OrderStatus;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class OrderServiceTest {



    @Mock
    OrdersRepository ordersRepository;

    @Mock
    ObjectMapper mapper;

    @InjectMocks
    OrdersServiceImpl ordersService1;

    @InjectMocks
    OrdersService ordersService;

    @InjectMocks
    CartServiceImpl cartService;

    @Mock
    CustomerService customerService;

    @Mock
    CartRepository cartRepository;
    @Mock
    CustomerRepository customerRepository;




    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }




    @Nested
    @Order(1)
    @DisplayName("Save Service")
    class SaveOrderService {

    }
}
