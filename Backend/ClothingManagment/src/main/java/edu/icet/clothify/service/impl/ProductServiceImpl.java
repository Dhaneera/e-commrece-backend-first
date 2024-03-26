package edu.icet.clothify.service.impl;
import edu.icet.clothify.dto.CategoryDto;
import edu.icet.clothify.dto.ProductDto;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Product;
import edu.icet.clothify.repository.ProductRepository;
import edu.icet.clothify.service.CategoryService;
import edu.icet.clothify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean addProduct(ProductDto productDto) {
        CategoryDto category=categoryService.getCategoryByName(productDto.getCategory().getName());
        Long id=category.getId();
        Product product = Product.builder().name(productDto.getName()).id(productDto.getId()).desc(productDto.getDesc()).price(productDto.getPrice()).soldCount(productDto.getSoldCount()).category(Category.builder().id(id).name(productDto.getCategory().getName()).build()).build();
        Product product1=productRepository.save(product);
        if (product1.getId()!=null){
            return true;
        }
        return true;
    }
}
