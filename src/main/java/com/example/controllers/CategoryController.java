package com.example.controllers;

import com.example.models.dbModels.Categories;
import com.example.models.dtoModels.CategoriesDto;
import com.example.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category Controller Api's", description = "This Api's are managed to the Categories of the Application")
public class CategoryController {
    @Autowired
    private CategoryService categorySer;


    @Operation(summary = "Add a new Category", description = "Adds a new Category in the Application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Category Added Successfully"),
           // @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    @PostMapping("/adding")
    public ResponseEntity<Categories> addingCategory(@RequestBody CategoriesDto cateDto) {
        return categorySer.addingCategory(cateDto);
    }


    @Operation(summary = "Fetch All Categories",description = "Fetch the all Categories available in Application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories Retrieved Successfully"),
            @ApiResponse(responseCode = "404", description = "Categories Not Available")
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<Categories>> getAllCategories() {
        return categorySer.getAllCategories();
    }


    @Operation(summary = "Get One Category by name", description = "Retrieves the Category by it's name from the Application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Category Found"),
            @ApiResponse(responseCode = "404", description = "Category not found with your name")
    })
    @GetMapping("/getOne")
    public ResponseEntity<Categories> getCategory(
            @Parameter(description = "Name of the Category to Retrieve") @RequestParam String categoryName)
    {
        return categorySer.getCategory(categoryName);
    }


    @Operation(summary = "Update a Category",description = "Updating an existing Category in the Application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Category Updated Successfully"),
            @ApiResponse(responseCode = "404",description = "Category Not Found"),
           // @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Categories> updatingCategory(@Parameter(description = "Enter the Id of Category to update")
                                                       @PathVariable int id,
                                                       @RequestBody CategoriesDto cDto)
    {
        return categorySer.updatingCategory(id,cDto);
    }

}

