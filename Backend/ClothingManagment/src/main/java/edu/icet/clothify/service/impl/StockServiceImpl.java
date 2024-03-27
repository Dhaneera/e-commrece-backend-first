package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.StockRepository;
import edu.icet.clothify.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
    public class    StockServiceImpl implements StockService {
    @Autowired
    ObjectMapper mapper;

    final  StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository repository) {
        this.stockRepository = repository;
    }

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

//    @Override
//    public Boolean addStock(StockDto stockDto) {
//        Stock stock=mapper.convertValue(stockDto,Stock.class);
//        Stock saved = stockRepository.save(stock);
//        return saved.getId() != null;
//    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with id: " + id);
        }
        stock.setId(id);
        return stockRepository.save(stock);
    }

    @Override
    public void deleteProduct(Long id) {
        if (stockRepository.existsById(id)){
            stockRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("stock info not available for this id to delete: "+id);
        }
    }

    @Override
    public List<StockDto> listStock(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            List<StockDto> stockDTOList = Collections.singletonList(convertStockToDTO(stock));  // Convert to a single StockDTO
            return stockDTOList;
        } else {

            return Collections.emptyList();
        }
    }
    private StockDto convertStockToDTO(Stock stock) {
        // Implement logic to map Stock entity fields to StockDTO object
        StockDto stockDTO = new StockDto();
        stockDTO.setId(stock.getId());
        stockDTO.setColor(stock.getColor());
        stockDTO.setSize(stock.getSize());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setQty(stock.getQty());
        return stockDTO;
    }

}
