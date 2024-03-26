package edu.icet.clothify.controller;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @PostMapping("/add")
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }

}
