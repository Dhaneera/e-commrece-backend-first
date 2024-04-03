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



    @Override
    public Stock updateStock(Long id, StockDto stockDto) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with id: " + id);
        }
        Stock existingStock =stockRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Stock not found with id: " + id));
        BeanUtils.copyProperties(stockDto,existingStock,"id");
        return stockRepository.save(existingStock);
    }


    @Override
    public Boolean deleteStock(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("stock info not available for this id to delete: "+id);
        } else {
            stockRepository.deleteById(id);
            return true;
        }

    }


    @Override
    public StockDto getStockById(long id) {
        Stock stock = stockRepository.findById(id).get();
        if (stock.getId()==null){
            return null;
        }
        return mapper.convertValue(stock,StockDto.class);
    }




}
