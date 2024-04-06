package edu.icet.clothify.service;

import edu.icet.clothify.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public Boolean addProduct(ProductDto productDto);

    public List<ProductDto> getAllProducts();

    ProductDto getProductById(long id);

    List<ProductDto> getProductByCategory(String categoryName);

    ProductDto getProductByName(String name);

    List<ProductDto> getProductByStock(Long StockId);
    public Boolean deleteProduct(Long id);
}
