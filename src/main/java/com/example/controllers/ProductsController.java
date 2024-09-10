package com.example.controllers;

import com.example.enums.Stock;
import com.example.models.dbModels.Products;
import com.example.models.dtoModels.ProductsDto;
import com.example.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Products Controller API's", description = "This API's are Managed to Products in Application")
public class ProductsController {
    @Autowired
    private ProductService productSer;


    @Operation(summary = "Add a new Product in Application", description = "Adds a new Product Details in to Application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Product Details are Entered Successfully")
    })
    @PostMapping(value = "/addproduct/{cateId}")
    public ResponseEntity<Products> addingProduct(@RequestParam String prodName,
                                                  @RequestParam String description,
                                                  @RequestParam Double price,
                                                  @RequestParam Integer discount,
                                                  @RequestParam MultipartFile[] prodImages,
                                                  @PathVariable int cateId) throws IOException {
        ProductsDto pDto = new ProductsDto();
        pDto.setProdName(prodName);
        pDto.setDescription(description);
        pDto.setPrice(price);
        pDto.setDiscount(discount);
        pDto.setProdImages(prodImages);
        return productSer.addingProduct(pDto, cateId);
    }


    @Operation(summary = "Delete Product Details by Id", description = "Deleting the Product Details by Using Product Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Deleted Successfully"),
            @ApiResponse(responseCode = "404", description = "Product Not Found to Delete")
    })
    @DeleteMapping("/deleteproduct/{prodId}")
    public ResponseEntity<String> deletingProduct(@PathVariable int prodId) {
        return productSer.deletingProduct(prodId);
    }


    @Operation(summary = "Status Update to ***Available*** ", description = "Status Updating of a product is Available or not")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "roduct Status Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Product is Not Found to update Status")
    })
    @PutMapping("/productAvail/{prodId}")
    public ResponseEntity<String> updateProdAvail(@PathVariable int prodId) {
        return productSer.updateProdAvail(prodId);
    }



    @Operation(summary = "Status Update to ***Not_Available***", description = "Status Updating of a product is Available or not")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Status Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Product is Not Found to update Status")
    })
    @PutMapping("/productNotAvail/{prodId}")
    public ResponseEntity<String> updateProdNotAvail(@PathVariable int prodId) {
        return productSer.updateProdNotAvail(prodId);
    }


    @Operation(summary = "Update Product Discount by Id", description = "Update the Existing Product Discount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Discount Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Product Not Found to update Discount")
    })
    @PutMapping("/productDiscount/{prodId}")
    public ResponseEntity<String> updateProductDiscount(@PathVariable int prodId, @RequestParam int discount)
    {
        return productSer.updateProductDiscount(prodId,discount);
    }



    @Operation(summary = "Update Product Price By Id", description = "Update the Existing Product Price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Price Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Product Not Found to update Price")
    })
    @PutMapping("/productPrice/{prodId}")
    public ResponseEntity<String> updateProductPrice(@PathVariable int prodId, @RequestParam  Double price)
    {
        return productSer.updateProductPrice(prodId, price);
    }


    @Operation(summary = "Fetch one Product by Id", description = "Get One Product Details by it's Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Product Details Retrieved"),
            @ApiResponse(responseCode = "404", description = "Product Not Found to Retrieve")
    })
    @GetMapping("/getoneproduct/{prodId}")
    public ResponseEntity<Products> getProduct(@PathVariable int prodId)
    {
        return productSer.getProduct(prodId);
    }


    @Operation(summary = "Fetch All Product Details", description = "Get All Product Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products Details are Retrieved")
    })
    @GetMapping("/getallproducts")
    public ResponseEntity<List<Products>> getAllProducts()
    {
        return productSer.getAllProducts();
    }




}















 /*
  @Operation(summary = "Add Multiple Products at a time", description = "Adds Multiple Product Details at a time in to Application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All Product Details are Entered Successfully")
    })
    @PostMapping("/addmultiproducts/{cateId}")
    public ResponseEntity<List<Products>> addingMultipleProducts(@RequestParam List<String> prodName,
                                                                 @RequestParam List<String> description,
                                                                 @RequestParam List<Double> price,
                                                                 @RequestParam List<Integer> discount,
                                                                 @RequestParam List<MultipartFile[]> prodImages,
                                                                 @PathVariable int cateId) throws IOException {
        List<ProductsDto> productsDtos = new ArrayList<>();
        for (int i = 0; i < prodName.size(); i++) {
            ProductsDto pDto = new ProductsDto();

            pDto.setProdName(prodName.get(i));
            pDto.setDescription(description.get(i));
            pDto.setPrice(price.get(i));
            pDto.setDiscount(discount.get(i));
            pDto.setProdImages(prodImages.get(i)); // Multiple images not adding for each product
            productsDtos.add(pDto);
        }
        return productSer.addingMultipleProducts(productsDtos, cateId);
    }

*/