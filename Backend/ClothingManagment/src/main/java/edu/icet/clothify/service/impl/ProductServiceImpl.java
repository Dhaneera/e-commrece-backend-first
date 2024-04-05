package edu.icet.clothify.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.dto.CollectionDto;
import edu.icet.clothify.dto.ProductDto;
import edu.icet.clothify.dto.SubCategoryDto;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Collection;
import edu.icet.clothify.entity.Product;
import edu.icet.clothify.entity.SubCategory;
import edu.icet.clothify.repository.ProductRepository;
import edu.icet.clothify.service.CategoryService;
import edu.icet.clothify.service.CollectionService;
import edu.icet.clothify.service.ProductService;
import edu.icet.clothify.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private CollectionService collectionService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean addProduct(ProductDto productDto) {
        CategoryDto category=categoryService.getCategoryByName(productDto.getCategory().getName());
        if (category.getId()==null){
            return false;
        }
        SubCategoryDto subCategoryDto =subCategoryService.getCategoryByName(productDto.getSubCategory().getName());
        CollectionDto collection=collectionService.getCollectionByName(productDto.getCollection().getName());
        Long id=category.getId();

        Product product = Product.builder().
                name(productDto.getName())
                .id(productDto.getId())
                .desc(productDto.getDesc())
                .price(productDto.getPrice())
                .soldCount(productDto.getSoldCount())
                .category(Category.builder()
                        .id(id).name(category.getName())
                        .build())
                .subCategory(SubCategory.builder()
                        .id(subCategoryDto.getId())
                        .name(subCategoryDto.getName())
                        .build())
                .collection(Collection.builder()
                        .id(collection.getId())
                        .name(collection.getName())
                        .build())
                .build();

        Product product1=productRepository.save(product);
        if (product1.getId()!=null){
            return true;
        }
        return true;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Iterable<Product> iterableProducts=productRepository.findAll();
        Iterator<Product> iteratorProducts =iterableProducts.iterator();
        List<ProductDto> list = new ArrayList<>();
        while (iteratorProducts.hasNext()){
            Product product=iteratorProducts.next();
            ProductDto productDto=objectMapper.convertValue(product,ProductDto.class);
            productDto.setCategory
                    (Category.builder()
                            .id(product.getCategory().getId())
                            .name(product.getCategory().getName()).build());
            productDto.setSubCategory
                    (SubCategory.builder()
                            .id(product.getSubCategory().getId())
                            .name(product.getSubCategory().getName()).build());
            productDto.setCollection
                    (edu.icet.clothify.entity.Collection.builder()
                            .id(product.getCollection().getId())
                            .name(product.getCollection().getName()).build());
            list.add(productDto);
        }
        return list;
    }

    @Override
    public ProductDto getProductById(long id){
        Product product=productRepository.findById(id).get();
        if (product.getId()!=null){
            ProductDto productDto=objectMapper.convertValue(product,ProductDto.class);
            productDto.
                    setCategory(Category.builder()
                            .id(product.getCategory().getId())
                            .name(product.getCategory().getName()).build());
            productDto.setSubCategory
                    (SubCategory.builder()
                            .id(product.getSubCategory().getId())
                            .name(product.getSubCategory().getName()).build());
            productDto.setCollection
                    (edu.icet.clothify.entity.Collection.builder()
                            .id(product.getCollection().getId())
                            .name(product.getCollection().getName()).build());
            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductByCategory(String categoryName) {
        List<ProductDto> listOfALlProducts=getAllProducts();
        List<ProductDto> listOfSpecificProducts = new ArrayList<>();
        for (ProductDto productDto:listOfALlProducts){
            if (Objects.equals(productDto.getCategory().getName(), categoryName)){
                listOfSpecificProducts.add(productDto);
            }
        }
        return listOfSpecificProducts;
    }

    @Override
    public ProductDto getProductByName(String name) {
        Product product = productRepository.getByName(name);
        return objectMapper.convertValue(product,ProductDto.class);

    }
    //TODO IF NEEDED SUBCATEGORY OR COLLECTION BY GETTING PRODUCT CAN BE IMPLEMENT
}
