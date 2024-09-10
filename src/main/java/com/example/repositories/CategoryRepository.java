package com.example.repositories;

import com.example.models.dbModels.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Integer>
{
    Categories findByCategoryName(String categoryName);
}
