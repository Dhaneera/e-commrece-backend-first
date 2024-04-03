package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Product;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.StockRepository;
import edu.icet.clothify.service.impl.StockServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Stock Service Testing")
public class StockServiceTest {

    @Mock
    StockRepository stockRepository;

    @Mock
    ObjectMapper mapper;

    @InjectMocks
    StockServiceImpl stockService;



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
        @DisplayName("Save  Stock Service")
        public void StockService_SaveStock_ReturnObject(){
            //Given
            Stock stock = Stock.builder().id(1L).size("M").qty(20).price(2000.00).color("Red").product(null).cart(null).build();
            StockDto stockDto = StockDto.builder().id(1L).size("M").qty(20).price(2000.00).color("Red").product(null).build();

            //When
            when(stockRepository.save(any())).thenReturn(stock);
            when(mapper.convertValue(any(), (JavaType) any())).thenReturn(stock);
            boolean isSaved=stockService.addStock(stockDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update Service")
    class UpdateStockService{

        @Test
        @Order(1)
        @DisplayName("Update Stock Service")
        public void StockService_UpdateStock(){
            //Given
            Long stockId = 1L;
            StockDto updatedDto = createStockDto(stockId);
            Stock existingStock = createStock(stockId);
            Stock expectedUpdatedStock = createStock(stockId);


            // When
            Mockito.when(stockRepository.existsById(stockId)).thenReturn(true);
            Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(existingStock));
            Mockito.when(stockRepository.save(existingStock)).thenReturn(expectedUpdatedStock);

            Stock updatedCustomer = stockService.updateStock(stockId, updatedDto);

            // Then

            verify(stockRepository).existsById(stockId);
            verify(stockRepository).findById(stockId);
            verify(stockRepository).save(existingStock);

            assertEquals(expectedUpdatedStock, updatedCustomer);
        }

        private StockDto createStockDto(Long id) {
            StockDto dto = new StockDto();
            dto.setId(id);
            return dto;
        }

        private Stock createStock(Long id) {
            Stock customer = new Stock();
            customer.setId(id);
            return customer;
        }
        @Test
        @Order(2)
        @DisplayName("Service Update Stock - Stock Not Found")
        public void StockService_UpdateStock_StockNotFound() {
            // Given
            Long nonExistentId = 10L;
            StockDto anyDto = new StockDto();

            Mockito.when(stockRepository.existsById(nonExistentId)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () ->
                    stockService.updateStock(nonExistentId, anyDto));
        }
        
        

    }
    @Nested
    @Order(3)
    @DisplayName("Delete Service")
    class DeleteStockService{

        @Test
        @Order(1)
        @DisplayName("Service Delete Stock - Successful Deletion")
        public void StockService_DeleteStock_Success() {
            // Given
            Long existingStockId = 1L;

            // Mock repository behavior
            Mockito.when(stockRepository.existsById(existingStockId)).thenReturn(true);

            // When
            boolean deletionResult = stockService.deleteStock(existingStockId);

            // Then
            // Verify repository calls
            verify(stockRepository).existsById(existingStockId);
            verify(stockRepository).deleteById(existingStockId);
            // Assert successful deletion (true returned)
            assertTrue(deletionResult);
        }

        @Test
        @Order(2)
        @DisplayName("Service Delete Stock - Stock Not Found")
        public void StockService_DeleteStock_NotFound() {
            // Given
            Long nonExistentStockId = 10L;

            Mockito.when(stockRepository.existsById(nonExistentStockId)).thenReturn(false);

            //When
            // Assuming your service throws ResourceNotFoundException when stock is not found
            assertThrows(ResourceNotFoundException.class, () -> stockService.deleteStock(nonExistentStockId));

            // Then
            // Verify repository call
            verify(stockRepository).existsById(nonExistentStockId);

        }
    }
    @Nested
    @Order(4)
    @DisplayName("View Service")
    class ViewStockService{







    }
}
