package edu.icet.clothify.repository;

import edu.icet.clothify.config.StockServiceException;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Stock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Cart Repository Testing")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    StockRepository stockRepository;

    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class Cart_Repository_Save {

        @Test
        @Order(1)
        @DisplayName("Save cart Repository")
        public void CartRepository_SaveCart_ReturnStockObject() {
            //Given
            Stock stock = Stock.builder().id(null).build();
            Stock save = stockRepository.save(stock);
            //When
            Cart cart = Cart.builder().id(null).productTot(200.00).qty(20).stockId(Stock.builder().id(save.getId()).build()).build();
            Cart saved = cartRepository.save(cart);
            //Then
            Assertions.assertEquals(cart.getId(), saved.getId());
            Assertions.assertEquals(cart.getStockId().getId(), saved.getStockId().getId());


        }
    }

    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class CartRepositoryUpdate {

        @Test
        @Order(1)
        @DisplayName("Update cart Repository")
        public void CartRepository_UpdateCart_ReturnStockObject() {
            //Given
            Cart cart = Cart.builder().id(null).productTot(200.00).qty(20).stockId(null).build();

            //When
            Cart saved = cartRepository.save(cart);
            saved.setProductTot(450.00);
            saved.setQty(28);
            //Then
            Assertions.assertEquals(saved.getProductTot(), 450.00);
            Assertions.assertEquals(saved.getQty(), 28);
        }
    }

    @Nested
    @Order(3)
    @DisplayName("Delete Repository")
    class CartRepositoryDelete {

        @Test
        @Order(1)
        @DisplayName("Delete Cart Repository")
        public void CartRepository_DeleteCart_ReturnVoid() throws StockServiceException {
            //Given
            Cart cart = Cart.builder().id(null).productTot(200.00).qty(20).stockId(null).build();


            //When
            Cart saved = cartRepository.save(cart);
            cartRepository.deleteById(saved.getId());
            Optional<Cart> byId = cartRepository.findById(cart.getId());

            //Then
            assertNull(byId.orElse(null));
        }


    }

    @Nested
    @Order(4)
    @DisplayName("View Repository")
    class StockRepositoryView {

        @Test
        @Order(1)
        @DisplayName("View Cart Repository")
        public void CartRepository_GetById_ReturnVoid() {
            //Given
            Cart cart = Cart.builder().id(null).productTot(200.00).qty(20).stockId(null).build();
            Cart cart1 = Cart.builder().id(null).productTot(300.00).qty(10).stockId(null).build();
            ArrayList<Cart> list = new ArrayList<>();

            //When
            cartRepository.save(cart);
            cartRepository.save(cart1);
            Iterable<Cart> iterable = cartRepository.findAll();

            for (Cart next : iterable) list.add(next);

            //Then
            Assertions.assertEquals(list.size(), 2);
        }

    }
}