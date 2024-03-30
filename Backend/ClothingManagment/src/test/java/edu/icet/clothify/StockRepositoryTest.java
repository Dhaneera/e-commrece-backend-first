package edu.icet.clothify;

import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StockRepositoryTest {
    @Autowired
    StockRepository stockRepository;

    public void StockRepository_SaveStock_ReturnStockObject(){
        //Given
//        Stock stock = Stock.builder().id(null).price(200).build()
        //When

        //Then


    }
}
