package edu.icet.clothify.controller;

import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public boolean addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/getAll")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/get/{name}")
    public CategoryDto getCategoryByName(@PathVariable String name){
       return categoryService.getCategoryByName(name);
    }

    @DeleteMapping("/delete/{name}")
    public Boolean deleteCategoryByName(@PathVariable String name){
        return categoryService.deleteCategoryByName(name);
    }
}
