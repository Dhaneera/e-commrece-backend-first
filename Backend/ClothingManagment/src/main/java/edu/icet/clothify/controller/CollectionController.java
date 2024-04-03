package edu.icet.clothify.controller;

import edu.icet.clothify.dto.CollectionDto;
import edu.icet.clothify.service.CollectionService;
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
@RequestMapping("/collection")
@CrossOrigin
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @PostMapping("/add")
    public boolean addSubcategory(@Valid @RequestBody CollectionDto collectionDto){
        return collectionService.saveCollection(collectionDto);
    }

    @GetMapping("/getAll")
    public List<CollectionDto> getAllSubCategories(){
        return collectionService.getAllCollection();
    }

    @GetMapping("/get/{id}")
    public CollectionDto getCollectionById(@PathVariable Long id){
        return collectionService.getCollectionById(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCollectionById(@PathVariable Long id){
        return collectionService.deleteCollectionById(id);
    }
    @GetMapping("/get/name/{name}")
    public CollectionDto getCollectionByName(@PathVariable String name){
        return collectionService.getCollectionByName(name);
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
