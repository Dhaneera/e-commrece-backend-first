package edu.icet.clothify.controller;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @PostMapping("/add")
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StockDto>> listStock(@PathVariable Long id) {
        List<StockDto> stockDTOList = stockService.listStock(id);
        return ResponseEntity.ok(stockDTOList);
    }

}
