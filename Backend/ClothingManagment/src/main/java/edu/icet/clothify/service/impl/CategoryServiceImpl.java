package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.repository.CategoryRepository;
import edu.icet.clothify.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  ObjectMapper objectMapper;

    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        Category category=objectMapper.convertValue(categoryDto, Category.class);
        Category savedCategory =categoryRepository.save(category);
        if (savedCategory.getId()!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
       Iterable<Category> iterableCategories=categoryRepository.findAll();
       Iterator<Category> iterateCategories=iterableCategories.iterator();
       List <CategoryDto> allCategoryDtos = new ArrayList<>();
       while (iterateCategories.hasNext()){
            Category category=iterateCategories.next();
            CategoryDto categoryDto=objectMapper.convertValue(category,CategoryDto.class);
            allCategoryDtos.add(categoryDto);
       }
       return allCategoryDtos;
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        Category category=categoryRepository.getByName(name);
        return objectMapper.convertValue(category,CategoryDto.class);
    }

    @Override
    public boolean deleteCategoryByName(String name) {
       CategoryDto category=getCategoryByName(name);
       categoryRepository.deleteById(category.getId());
       return true;
    }
}
