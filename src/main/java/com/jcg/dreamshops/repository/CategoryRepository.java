package com.jcg.dreamshops.repository;

import com.jcg.dreamshops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String category);
}
