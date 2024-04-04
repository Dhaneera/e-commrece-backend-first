package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.dto.StockDto;
import edu.icet.clothify.dto.SubCategoryDto;
import edu.icet.clothify.entity.SubCategory;
import edu.icet.clothify.repository.SubCategoryRepository;
import edu.icet.clothify.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Iterator;
import java.util.List;


@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public boolean saveSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory =objectMapper.convertValue(subCategoryDto, SubCategory.class);
        SubCategory savedSubcategory=subCategoryRepository.save(subCategory);
        return savedSubcategory.getId() != null;
    }

    @Override
    public List<SubCategoryDto> getAllSubCategories() {
       Iterable<SubCategory> subcategories =subCategoryRepository.findAll();
       List<SubCategoryDto> subcategoriesList= new ArrayList<>();
       Iterator<SubCategory> iteratorSubcategory =subcategories.iterator();
        while (iteratorSubcategory.hasNext()){
            SubCategory subCategory=iteratorSubcategory.next();
            SubCategoryDto subCategoryDto=objectMapper.convertValue(subCategory,SubCategoryDto.class);
            subcategoriesList.add(subCategoryDto);
        }
        return subcategoriesList;
    }

    @Override
    public SubCategoryDto getSubCategoryById(Long id) {
            SubCategory subCategory = subCategoryRepository.findById(id).get();
            return subCategory.getId()!=null ? objectMapper.convertValue(subCategory,SubCategoryDto.class):null;}

    @Override
    public boolean deleteSubCategoryById(Long id) {
        if (subCategoryRepository.existsById(id)){
            subCategoryRepository.deleteById(id);
            return true;
        }else {
            throw new ResourceNotFoundException("SubCategory  not available for this id to delete: "+id);

        }
    }

    @Override
    public SubCategoryDto getCategoryByName(String name) {
        SubCategory subCategory=subCategoryRepository.getByName(name);
        return subCategory.getId() != null ? objectMapper.convertValue(subCategory, SubCategoryDto.class) : null;}


}


