package com.jcg.dreamshops.service.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcg.dreamshops.dto.request.AddProductRequest;
import com.jcg.dreamshops.dto.request.UpdateProductRequest;
import com.jcg.dreamshops.exception.ProductNotFoundException;
import com.jcg.dreamshops.model.Category;
import com.jcg.dreamshops.model.Product;
import com.jcg.dreamshops.repository.CategoryRepository;
import com.jcg.dreamshops.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    @Override
    public Product addProduct(AddProductRequest request) {
        // Find or create the category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(
                        Category.builder()
                                .name(request.getCategory().getName())
                                .build()
                ));

        // Create and save the product
        return productRepository.save(
                Product.builder()
                        .name(request.getName())
                        .brand(request.getBrand())
                        .description(request.getDescription())
                        .price(request.getPrice())
                        .category(category)
                        .build()
        );
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Unable to find product.")); //
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> {throw new ProductNotFoundException("Unable to find product.");}
                        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Unable to find product."));
        mapper.map(request, product);
        return productRepository.save(product);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String name, String brand) {
        return productRepository.findByBrandAndName(name, brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category, brand);
    }
}
