package com.example.controllers;

import com.example.models.dbModels.Cart;
import com.example.models.dtoModels.CartDto;
import com.example.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Cart Controller API's", description = "This API's are Managed to Customer Cart's in Application")
public class CartController
{
    @Autowired
    private CartService cartSer;

    @Operation(summary = "Add Product to cart", description = "Adding a Product to a Customer cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Added to Cart successfully")
    })
    @PostMapping("/cart")
    public ResponseEntity<String> addProductToCart(@RequestParam("prodId")int prodId,
                                                 @RequestParam("custId")int custId,
                                                 @RequestParam("quantity")int quantity)
    {
       return cartSer.addProductToCart(prodId,custId,quantity);
    }


    @Operation(summary = "View the Customer Cart by customer id", description = "Viewing the Customer Added products in cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart Products Retrieved successfully")
    })
    @GetMapping("/cart")
    public ResponseEntity<CartDto> viewCustomerCart(@RequestParam("custId")int custId)//view the cart items for a customer
    {
        return cartSer.viewCustomerCart(custId);
    }



    @Operation(summary = "Update Product Quantity in Cart", description = "Updating an existing Product Quantity in Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart Product Quantity Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Your Passing id of Product Not available in Cart ")
    })
    @PutMapping("/cart")
    public ResponseEntity<String> updateProductQuantity(@RequestParam int prodId,
                                                      @RequestParam int custId,
                                                      @RequestParam int quantity)
    {
        return cartSer.updateProductQuantity(prodId,custId,quantity);
    }


    @Operation(summary = "Remove a Product in customer Cart by ProductId", description = "Removing a Product From a customer Cart by ProductId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Removed From a Customer Cart"),
            @ApiResponse(responseCode = "404", description = "Product Not Available in Cart(Product Id)")
    })
    @DeleteMapping("/removeProdCart")
    public ResponseEntity<String> removeProductFromCart(@RequestParam int prodId,
                                                      @RequestParam int custId)
    {
        return cartSer.removeProductFromCart(prodId,custId);
    }



    @Operation(summary = "Clear Customer Cart by customer Id", description = "Deleting all the Products from a customer cart by Using Customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart Cleared Successfully")
    })
    @DeleteMapping("/clearCart/{custId}")
    public ResponseEntity<String> clearCart(@PathVariable int custId)
    {
        return cartSer.clearCart(custId);
    }


}
