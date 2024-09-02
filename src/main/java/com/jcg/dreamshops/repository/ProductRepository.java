package com.jcg.dreamshops.repository;

import com.jcg.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
    List<Product> findByName(String name);
    List<Product> findByCategoryAndBrand(String category, String brand);
    List<Product> findByBrandAndName(String brand, String name);
    Long countByBrandAndName(String brand, String name);
}
