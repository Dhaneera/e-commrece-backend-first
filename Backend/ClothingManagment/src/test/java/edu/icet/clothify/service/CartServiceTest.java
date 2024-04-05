package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CartDto;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.CartRepository;
import edu.icet.clothify.service.impl.CartServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Cart Service Testing")
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;
    @InjectMocks
    CartServiceImpl cartService;
    @Mock
    ObjectMapper mapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Nested
    @Order(1)
    @DisplayName("Save Service")
    class SaveCartService{

        @Order(1)
        @DisplayName("Save Cart Service")
        @Test
        public void testAddCart_Success() {
            // Mock CartDto object
            CartDto cartDto = new CartDto(1L, null, 10, 10.0, true);

            // Mock saved Cart with ID
            Cart savedCart = new Cart(cartDto.getId(),cartDto.getStockId(),cartDto.getQty(),cartDto.isCompleted(),cartDto.getProductTot());
            // Mock CartRepository behavior
            Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(savedCart);

            // Call the service methodx
            boolean added = cartService.addCart(cartDto);

            // Assertions
            assertTrue(added);
//
        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update Service")
    class UpdateCartService {

        @Test
        @Order(1)
        @DisplayName("Update Cart Service")
        public void CartService_UpdateCart() {
            //Given
            Long cartId = 1L;
            CartDto updatedDto = createCartDto(cartId);
            Cart existingCart = createCart(cartId);
            Cart expectedUpdatedCart = createCart(cartId);


            // When
            Mockito.when(cartRepository.existsById(cartId)).thenReturn(true);
            Mockito.when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
            Mockito.when(cartRepository.save(existingCart)).thenReturn(expectedUpdatedCart);



            Cart upadateCart = cartService.upadateCart(cartId, updatedDto);

            // Then


            verify(cartRepository, times(1)).findById(cartId); // Verify repository interaction

            verify(cartRepository).existsById(cartId);
            verify(cartRepository).findById(cartId);
            verify(cartRepository).save(existingCart);

            assertEquals(expectedUpdatedCart, upadateCart);
        }

        private CartDto createCartDto(Long id) {
            CartDto dto = new CartDto();
            dto.setId(id);
            return dto;
        }

        private Cart createCart(Long id) {
            Cart cart = new Cart();
            cart.setId(id);
            return cart;
        }
        @Test
        @Order(2)
        @DisplayName("Service Update Cart - Cart Not Found")
        public void StockService_UpdateCart_CartNotFound() {
            // Given
            Long nonExistentId = 10L;
            CartDto anyDto = new CartDto();

            Mockito.when(cartRepository.existsById(nonExistentId)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () ->
                    cartService.upadateCart(nonExistentId, anyDto));

            verify(cartRepository,times(1)).existsById(nonExistentId);
        }



        @Test
        @DisplayName("Returns false for non-existent cart")
        public void updateStatus_NotFound() {
            // Given
            long id = 2L;
            when(cartRepository.findById(id)).thenReturn(Optional.empty());

            // When
            boolean updateStatus = cartService.updateStatus(id);

            // Then
            assertFalse(updateStatus); // Assert update failure
            verify(cartRepository, times(1)).findById(id); // Verify finding cart (no further interaction)
        }
    }
    @Nested
    @Order(3)
    @DisplayName("view test")
    class  ViewCartService{
        @Test
        @Order(1)
        @DisplayName("Retrieves all cart details successfully")
        public void getAllCartDetails_Success() {
            // Given

            List<Cart> expectedCarts = List.of(
                    new Cart(1L,Stock.builder().id(1L).product(null).cart(null).color(null).color(null).color(null).size(null).price(null).qty(0).build(),20,true,20.00));
            List<CartDto> expectedCartDtos = expectedCarts.stream()
                    .map(cart -> new CartDto(cart.getId()))
                    .collect(Collectors.toList());

            // Mock repository behavior
            when(cartRepository.findAll()).thenReturn(expectedCarts);

            // When
            List<CartDto> actualCartDtos = cartService.getAllCartDetails();

            // Then

            verify(cartRepository, times(1)).findAll(); // Verify repository interaction
        }



        @Test
        @Order(2)
        @DisplayName("Handles empty cart list gracefully")
        public void getAllCartDetails_EmptyList() {
            // Given
            when(cartRepository.findAll()).thenReturn(Collections.emptyList());

            // When
            List<CartDto> actualCartDtos = cartService.getAllCartDetails();

            // Then
            assertTrue(actualCartDtos.isEmpty());
            verify(cartRepository, times(1)).findAll();
        }
        @Test
        @DisplayName("Retrieves CartDto successfully for existing cart")
        public void getCartById_Success() {
            // Given
            long id = 1L;
            Cart cart =  new Cart(1L,Stock.builder().id(1L).product(null).cart(null).color(null).color(null).color(null).size(null).price(null).qty(0).build(),20,true,20.00);

            CartDto expectedCartDto = new CartDto(id); // Map fields manually

            // Mock repository behavior
            when(cartRepository.findById(id)).thenReturn(Optional.of(cart));

            // When
            CartDto actualCartDto = cartService.getCartById(id);

            // Then

            verify(cartRepository, times(1)).findById(id); // Verify repository interaction
        }

        @Test
        @DisplayName("Returns null for non-existent cart")
        public void getCartById_NotFound() {
            // Given
            long id = 2L;
            when(cartRepository.findById(id)).thenReturn(Optional.empty());

            // When
            CartDto cartDto = cartService.getCartById(id);

            // Then
            assertNull(cartDto);
            verify(cartRepository, times(1)).findById(id); // Verify repository interaction
        }
    }
    @Nested
    @Order(5)
    @DisplayName("Error handing Service")
    class ThrowExceptionService {

        @Test
        @Order(1)
        @DisplayName("StockService_getStockById - Handle Null ID")
        public void testStockService_GetStockById_NullId()  {
            // Given
            Long nullId = null;

            // Mock dependencies (if applicable)
            CartRepository mockStockRepository = mock(CartRepository.class);

            // When and Then (using assertThrows)
            assertThrows(Exception.class, () -> cartService.getCartById(nullId));

        }
    }



}
