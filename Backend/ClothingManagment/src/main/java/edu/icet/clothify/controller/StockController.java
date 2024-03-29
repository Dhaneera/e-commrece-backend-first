package edu.icet.clothify.controller;
import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Stock;
import edu.icet.clothify.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/add")
    public boolean addStock(@RequestBody StockDto stock) {
        return stockService.addStock(stock);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<StockDto>> listStock(@PathVariable Long id) {
        List<StockDto> stockDTOList = stockService.listStock(id);
        return ResponseEntity.ok(stockDTOList);
    }
    @PutMapping ("/update/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody StockDto stockDto) {
        return stockService.updateStock(id, stockDto);
    }
    @DeleteMapping("/remove/{id}")
    public Boolean  deleteStock(@PathVariable Long id){
        return stockService.deleteStock(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> error(MethodArgumentNotValidException exception){
        Map <String, String> map=new HashMap<>();
        List<ObjectError> list=exception.getBindingResult().getAllErrors();
        for(ObjectError item:list) {
            FieldError fieldError=(FieldError) item;
            String fieldName= fieldError.getField();
            String message = item.getDefaultMessage();
            map.put(fieldName,message);
        }
        return map;
    }
}
