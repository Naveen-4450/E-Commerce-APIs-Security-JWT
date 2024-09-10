package com.example.services;

import com.example.exceptionhandling.ResourceNotFoundException;
import com.example.models.dbModels.Categories;
import com.example.models.dtoModels.CategoriesDto;
import com.example.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepo;


    public ResponseEntity<Categories> addingCategory(CategoriesDto cDto)    //Adding Category
    {
        Categories ct = new Categories();
        ct.setCategoryName(cDto.getCategoryName());
        Categories savedCategory = categoryRepo.save(ct);

        log.info("New category '{}' created with ID:",cDto.getCategoryName(),savedCategory.getCategoryId());
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }


    public ResponseEntity<List<Categories>> getAllCategories()
    {
       List<Categories> list =  categoryRepo.findAll();
        if(list.isEmpty()) {
            log.warn("No categories found in the database.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            log.info("Retrieved {} categories from the database.", list.size());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }


    public ResponseEntity<Categories> getCategory(String categoryName)
    {
        Categories ct = categoryRepo.findByCategoryName(categoryName);
        if(ct != null) {
            log.info("Category '{}' found",categoryName);
            return new ResponseEntity<>(ct,HttpStatus.OK);
        }else{
            log.error("Category '{}' not found in the database.", categoryName);
            throw new ResourceNotFoundException("Category Not there with the given name");
        }
    }


    public ResponseEntity<Categories> updatingCategory(int id, CategoriesDto cDto) {

        Optional<Categories> opt = categoryRepo.findById(id);

        if (opt.isPresent()){
            Categories ct = opt.get();
            ct.setCategoryName(cDto.getCategoryName());
            Categories updatedCategory = categoryRepo.save(ct);

            log.info("Category with ID: {} updated to new name: '{}'",id, updatedCategory.getCategoryName());
            return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
        }else {
            log.error("Category with ID: {} not found for update.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
