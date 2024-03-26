package edu.icet.clothify.service.impl;

import edu.icet.clothify.config.ResourceNotFoundException;
import edu.icet.clothify.entity.Product;
import edu.icet.clothify.repository.ProductRepository;
import edu.icet.clothify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found : "+id);
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);

        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("product info not available for this id to delete: "+id);
        }
    }
}
