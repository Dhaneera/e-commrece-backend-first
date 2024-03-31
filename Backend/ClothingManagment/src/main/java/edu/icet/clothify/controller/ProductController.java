package edu.icet.clothify.controller;

import edu.icet.clothify.dto.ProductDto;
import edu.icet.clothify.service.ProductService;
import jakarta.validation.Valid;
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
    @Valid
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String addProduct(@Valid @RequestBody ProductDto productDto){
        boolean isSaved=productService.addProduct(productDto);
        if (!isSaved){
            return "unsuccessful due to category,subcategory or collection is empty";
        }
        return "Product successfully saved";
    }

    @GetMapping("/get/all")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public ProductDto getProductById(@Valid @PathVariable Long id){
       return productService.getProductById(id);
    }

    @GetMapping("/get/category/{category}")
    public List getProductByCategory(@PathVariable String category){
        return productService.getProductByCategory(category);
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