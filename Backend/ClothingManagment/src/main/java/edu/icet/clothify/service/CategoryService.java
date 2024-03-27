package edu.icet.clothify.service;
import edu.icet.clothify.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    public boolean saveCategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);

    boolean deleteCategoryByName(String name);
}
