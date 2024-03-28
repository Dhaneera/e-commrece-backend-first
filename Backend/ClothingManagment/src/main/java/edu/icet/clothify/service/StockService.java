package edu.icet.clothify.service;

import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Stock;

import java.util.List;

public interface StockService {

    Boolean addStock(StockDto stockDto);

    public Stock updateStock(Long id, Stock stock);
     void deleteProduct(Long id);

    List<StockDto> listStock(Long id);

}
