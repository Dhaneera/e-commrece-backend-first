package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.ProductDto;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Product;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.repository.StockRepository;
import edu.icet.clothify.service.ProductService;
import edu.icet.clothify.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
    public class  StockServiceImpl implements StockService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    StockRepository stockRepository;

    ProductService productService;

    @Override
    public Boolean addStock(StockDto stockDto) {
        Stock stock=mapper.convertValue(stockDto,Stock.class);
        Stock saved = stockRepository.save(stock);
        return saved.getId() != null;
    }

    public List<StockDto> getStock(Long id ,StockDto stockDto){
        return listStock(stockDto.getId());
    }

    @Override
    public Stock updateStock(Long id, StockDto stockDto) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with id: " + id);
        }
        Stock existingStock =stockRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Stock not found with id: " + id));

        BeanUtils.copyProperties(stockDto,existingStock,"id");
        return stockRepository.save(existingStock);
    }


    @Override
    public Boolean deleteStock(Long id) {
        if (stockRepository.existsById(id)){
            stockRepository.deleteById(id);
            return true;
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

    @Override
    public StockDto getStockById(long id) {
        Optional<Stock> byId = stockRepository.findById(id);
        if (byId.isPresent()){
            Stock stock = byId.get();
            Long  productId = stock.getProduct().getId();
            ProductDto productDto = productService.getProductById(productId);
            StockDto stockDto = convertStockToDTO(stock);
            stockDto.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
            return stockDto;
        }
        return null;
    }

    private StockDto    convertStockToDTO(Stock stock) {
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
