package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.a.NativeUtils;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.dto.OrdersDto;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Orders;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.CartRepository;
import edu.icet.clothify.repository.CustomerRepository;
import edu.icet.clothify.repository.OrdersRepository;
import edu.icet.clothify.repository.StockRepository;
import edu.icet.clothify.service.impl.CartServiceImpl;
import edu.icet.clothify.service.impl.OrdersServiceImpl;
import edu.icet.clothify.service.impl.StockServiceImpl;
import edu.icet.clothify.util.enums.OrderStatus;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    OrdersRepository ordersRepository;

    @Mock
    ObjectMapper mapper;

    @InjectMocks
    OrdersServiceImpl ordersService;

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
    class SaveOrderService{
        @Test
        @Order(1)
        @DisplayName("Successfully adds order with existing customer and cart")
        public void addOrder_Success1() {
            // Given (common setup)
            // Given (common setup)
            long customerId = 1L;
            String customerName = "John Doe";
            long cartId = 2L;
            OrdersDto orderDto = createOrderDto(customerName, cartId);

            // Mock customer service behavior
            CustomerDto customerDto = new CustomerDto(customerId, customerName);
            when(cartRepository.findById(cartId)).thenReturn(Optional.of(new Cart(cartId)));
            when(customerService.getCustomerByName(customerName)).thenReturn(customerDto);


            // Mock cart service behavior (optional, not called directly in this test)
            // when(cartServiceMock.getCartById(cartId)).thenReturn(new CartDto(cartId));

            // Mock mapper behavior
            Orders expectedOrder = createOrders(customerId, cartId);
            when(mapper.convertValue(orderDto, Orders.class)).thenReturn(expectedOrder);

            // When
            boolean success = ordersService.addOrder(orderDto);

            // Then
            assertTrue(success); // Assert successful addition
            verify(customerService, times(1)).getCustomerByName(customerName); // Verify interactions
            // No verification for cartService.getCartById as it's not called here
            verify(ordersRepository, times(1)).save(expectedOrder); // Verify saved order
        }

        @Test
        @DisplayName("Successfully adds order with existing customer and cart")
        public void addOrder_Success() {
            // Given (common setup)
            long customerId = 1L;
            String customerName = "John Doe";
            long cartId = 2L;
            OrdersDto orderDto = createOrderDto(customerName, cartId);

            // Mock customer and cart services (consider using a mocking framework)
            CustomerDto customerDto = new CustomerDto(customerId, customerName);
            CartDto cartDto = new CartDto(cartId);
            when(customerService.getCustomerByName(customerName)).thenReturn(customerDto);
            when(cartRepository.findById(cartId)).thenReturn(Optional.of(new Cart(cartId)));

            when(cartService.getCartById(cartId)).thenReturn(cartDto);

            // Mock mapper behavior (consider mocking framework or manual mapping)
            Orders expectedOrder = createOrders(customerId, cartId);
            when(mapper.convertValue(orderDto, Orders.class)).thenReturn(expectedOrder);

            // When
            boolean success = ordersService.addOrder(orderDto);

            // Then
            assertTrue(success); // Assert successful addition
            verify(customerService, times(1)).getCustomerByName(customerName); // Verify interactions
            verify(cartService, times(1)).getCartById(cartId);
            verify(ordersRepository, times(1)).save(expectedOrder); // Verify saved order
        }
        // Helper methods to create OrderDto, Orders, etc.

        private OrdersDto createOrderDto(String customerName, long cartId) {
            OrdersDto orderDto = new OrdersDto();
            orderDto.setCustomer(new Customer(customerName)); // Assuming CustomerDto has a name field
            orderDto.setCart(new Cart(cartId)); // Assuming CartDto has an id field
            // Add other fields specific to OrdersDto if needed


            return orderDto;
        }

        private Orders createOrders(long customerId, long cartId) {
            Orders orders = new Orders();
            orders.setCustomer(Customer.builder().id(customerId).build()); // Assuming Customer has an ID
            orders.setCart(Cart.builder().id(cartId).build()); // Assuming Cart has an ID
            // Add other fields specific to Orders based on your domain model
            return orders;
        }

    }
}
