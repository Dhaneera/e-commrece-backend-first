package edu.icet.clothify.controller;
import edu.icet.clothify.dto.ProductDto;
import edu.icet.clothify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public boolean addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
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
