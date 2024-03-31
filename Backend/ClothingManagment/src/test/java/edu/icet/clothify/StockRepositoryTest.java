package edu.icet.clothify;

import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.StockRepository;
import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Stock Repository Testing")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StockRepositoryTest {
    @Autowired
    StockRepository stockRepository;

    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class SaveStock {

        @Test
        @Order(1)
        @DisplayName("Save stock Repository")
        public void StockRepository_SaveStock_ReturnStockObject() {
            //Given
            Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(null).build();
            //When
            Stock saved = stockRepository.save(stock);
            //Then
            Assertions.assertEquals(stock.getId(), saved.getId());

        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class UpdateStock{

        @Test
        @Order(1)
        @DisplayName("Update stock Repository")
        public void StockRepository_UpdateStock_ReturnStockObject(){
            //Given
            Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(null).build();

            //When
            Stock saved = stockRepository.save(stock);
            saved.setPrice(3200.00);
            saved.setQty(19);
            saved.setColor("Black");
            saved.setSize("M");
            //Then
            Assertions.assertEquals(saved.getPrice(),3200.00);
            Assertions.assertEquals(saved.getQty(),19);
            Assertions.assertEquals(saved.getColor(),"Black");
            Assertions.assertEquals(saved.getSize(),"M");
        }
    }

    @Nested
    @Order(3)
    @DisplayName("Delete Repository")
    class DeleteStock{

        @Test
        @Order(1)
        @DisplayName("Delete Stock Repository")
        public void StockRepository_DeleteStock_ReturnVoid() throws Exception{
            //Given
            Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(null).build();

            //When
            Stock saved = stockRepository.save(stock);
            stockRepository.deleteById(saved.getId());
            Optional<Stock> byId = stockRepository.findById(stock.getId());

            //Then
            then(Optional.empty())
        }
    }
}
