package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.CartRepository;
import edu.icet.clothify.service.impl.CartServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Cart Service Testing")
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;
    @Mock
    CartServiceImpl cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Nested
    @Order(1)
    @DisplayName("Save Service")
    class SaveStockService{
        @Test
        @Order(1)
        @DisplayName("Save Cart Service")
        public void CartService_SaveCart_ReturnObject() {
            // Given
            Cart cart = Cart.builder().id(1L).stockId(Stock.builder().build()).completed(false).productTot(10.00).qty(10).build();
            CartDto cartDto = CartDto.builder().id(1L).stockId(Stock.builder().build()).isCompleted(false).productTot(10.00).qty(10).build();

            // Mocking behavior
            when(cartRepository.save(any())).thenReturn(cart);

            // When
            boolean isSaved = cartService.addCart(cartDto);

            // Then
            assertTrue(!isSaved);
        }

    }
}
