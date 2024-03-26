package edu.icet.clothify.service;

import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Stock;

public interface StockService {
    public Stock addStock(Stock stock);
    public Boolean addStock(StockDto stockDto);
    public Stock updateStock(Long id, Stock stock);
     void deleteProduct(Long id);
}
