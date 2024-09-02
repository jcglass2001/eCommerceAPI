package com.jcg.dreamshops.service.product;

import com.jcg.dreamshops.dto.request.AddProductRequest;
import com.jcg.dreamshops.dto.request.UpdateProductRequest;
import com.jcg.dreamshops.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long productId);
    Long countProductsByBrandAndName(String brand, String name);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductByBrandAndName(String name, String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
}
