package edu.icet.clothify.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.repository.CategoryRepository;
import edu.icet.clothify.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Category Service Testing")
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @Order(1)
    @DisplayName(" save Service")
    class CategoryServiceSave {
        @Test
        @Order(1)
        @DisplayName("Save Category Service")
        public void CategoryService_SaveCategory_ReturnObject(){
            //Given
            Category category = Category.builder().id(1L).name("Mens").build();
            CategoryDto categoryDto = CategoryDto.builder().id(1L).name("Mens").build();
            //When
            when(categoryRepository.save(any())).thenReturn(category);
            when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(category);
            boolean isSaved=categoryService.saveCategory(categoryDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName(" View Service")
    class CategoryServiceView{
        @Test
        @Order(1)
        @DisplayName("Category GetAll Service")
        public void CategoryService_ViewCategory_ReturnObject(){

            //Given
            List<Category> list= new ArrayList<>();

            Category category =  Category.builder().id(1L).name("Mens").build();
            Category category1 =  Category.builder().id(2L).name("womens").build();
            CategoryDto categoryDto=CategoryDto.builder().id(2L).name("womens").build();
            list.add(category1);
            list.add(category);

            //When
            when(categoryRepository.findAll()).thenReturn(list);
            when(objectMapper.convertValue(any(), (Class<Object>) any())).thenReturn(categoryDto);
            List<CategoryDto>categoriesGot=categoryService.getAllCategories();
            //then
            org.assertj.core.api.Assertions.assertThat(!categoriesGot.isEmpty());
        }
        @Test
        @Order(2)
        @DisplayName("Category GetByName Service")
        public void CategoryService_ViewCategoryByName_ReturnObject(){
            //Given
            Category category =  Category.builder().id(1L).name("Mens").build();
            CategoryService categoryService = Mockito.mock(CategoryService.class);


            //When
            when(categoryService.getCategoryByName(category.getName())).thenReturn(CategoryDto.builder().build());
            CategoryDto categoryDto = categoryService.getCategoryByName(category.getName());

            //Then
            assertNotNull(categoryDto, "category Dto should not be null ");
        }


    }
    @Nested
    @Order(3)
    @DisplayName(" Delete Service")
    class CategoryServiceDelete{
        @Test
        @Order(1)
        @DisplayName("Category DeleteById Service")
        public void CategoryService_DeleteCategoryById_ReturnVoid(){

        }
    }


}