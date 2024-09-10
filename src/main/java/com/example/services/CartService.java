package com.example.services;

import com.example.exceptionhandling.CustomerNotFoundException;
import com.example.exceptionhandling.ResourceNotFoundException;
import com.example.models.dbModels.Cart;
import com.example.models.dbModels.Customers;
import com.example.models.dbModels.Products;
import com.example.models.dtoModels.CartDto;
import com.example.repositories.CartRepository;
import com.example.repositories.CustomersRepository;
import com.example.repositories.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CartService
{
    @Autowired
    private ProductsRepository prodRepo;

    @Autowired
    private CustomersRepository custRepo;

    @Autowired
    private CartRepository cartRepo;


    public ResponseEntity<String> addProductToCart(int prodId, int custId, int quantity)
    {
        log.info("Adding product with Id:{} to cart for customer with ID:{}",prodId,custId);
        Products product = prodRepo.findById(prodId).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
        Customers customer = custRepo.findById(custId).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        Optional<Cart> existingCart = cartRepo.findByCustCart(customer);

        Cart cart;
        if(existingCart.isPresent())
        {
            cart = existingCart.get();
            Map<Products,Integer> productQuantities = cart.getProductQuantities();

            productQuantities.put(product,productQuantities.getOrDefault(product,0) + quantity);
            cart.setUpdatedAt(new Date());
            log.info("Added product in cart for customer with ID:{}",custId);
        }else {
                //else, creating the new Cart for a Customer
            cart = new Cart();
            cart.getProductQuantities().put(product,quantity);
            cart.setCustCart(customer);
            cart.setCreatedAt(new Date());
            cart.setUpdatedAt(new Date());
            log.info("Created new cart & added product in cart for customer with ID: {}",custId);
        }
        Cart savedCart = cartRepo.save(cart);
        return new ResponseEntity<>("Product Successfully Added to your Cart", HttpStatus.OK);
    }



    public ResponseEntity<CartDto> viewCustomerCart(int custId)
    {
        log.info("Viewing customer cart with ID:{}",custId);
        Customers customer = custRepo.findById(custId).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        Cart cart = cartRepo.findByCustCart(customer).orElseThrow(()->new ResourceNotFoundException("Customer Doesn't have a Cart/Cart Items"));

        List<CartDto.ProductDto> productQuantities = new ArrayList<>();

        for(Map.Entry<Products,Integer> entry : cart.getProductQuantities().entrySet())
        {
            Products product = entry.getKey();
            Integer quantity = entry.getValue();

            CartDto.ProductDto productDto = new CartDto.ProductDto();
            productDto.setProdId(product.getProdId());
            productDto.setProdName(product.getProdName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setDiscount(product.getDiscount());
            productDto.setStockAvail(product.getStockAvail());
            productDto.setProdImages(product.getProdImages());
            productDto.setQuantity(quantity);

            productQuantities.add(productDto);
        }
            CartDto cartDto = new CartDto();
            cartDto.setCartId(cart.getCartId());
            cartDto.setProductQuantities(productQuantities);
        log.info("Retrieved cart details for customer with id {}", custId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


    public ResponseEntity<String> updateProductQuantity(int prodId, int custId, int quantity)
    {
        log.info("Updating product quantity for product ID:{} in cart for customer ID:{}", prodId, custId);
        Products product = prodRepo.findById(prodId).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
        Customers customer = custRepo.findById(custId).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found"));
        Cart cart = cartRepo.findByCustCart(customer).orElseThrow(()->new ResourceNotFoundException("Cart Not Found"));

        Map<Products,Integer> productQuantities = cart.getProductQuantities();
        if(productQuantities.containsKey(product))
        {
            productQuantities.put(product,quantity);
            cart.setUpdatedAt(new Date());

            cartRepo.save(cart);
            log.info("Updated quantity for product with id {} in cart for customer with id {}", prodId, custId);
            return new ResponseEntity<>("Product Quantity updated successfully",HttpStatus.OK);
        }else{
            log.warn("Product with id {} not found in cart for customer with id {}", prodId, custId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> removeProductFromCart(int prodId, int custId) {

        log.info("Removing product ID:{} from cart for customer with ID:{}",prodId,custId);
        Products product = prodRepo.findById(prodId).orElseThrow(()->new ResourceNotFoundException("Product Not Available in Customer Cart"));
        Customers customer = custRepo.findById(custId).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        Cart cart = cartRepo.findByCustCart(customer).orElseThrow(()->new ResourceNotFoundException("Cart Not Found"));

        Map<Products,Integer> productQuantities = cart.getProductQuantities();
        if(productQuantities.containsKey(product))
        {
            int currentQuantity = productQuantities.get(product);
            if(currentQuantity > 1) {
                productQuantities.put(product, currentQuantity - 1);
                log.info("Decremented quantity of product with id {} in cart for customer with id {}", prodId, custId);
            }else{
                productQuantities.remove(product);
                log.info("Removed product with id {} from cart for customer with id {}", prodId, custId);
            }
            cart.setUpdatedAt(new Date());
            cartRepo.save(cart);
            return new ResponseEntity<>("Product Removed From Cart",HttpStatus.OK);
        }else {
            log.warn("Product Id: {} is not found in cart for customer with ID:{}", prodId, custId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> clearCart(int custId) {

        log.info("Clearing Cart for customer with ID:{}",custId);
        Customers customer = custRepo.findById(custId).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        Cart cart = cartRepo.findByCustCart(customer).orElseThrow(()->new ResourceNotFoundException("Cart is Not Found"));

        cart.getProductQuantities().clear();
        cart.setUpdatedAt(new Date());
        cartRepo.save(cart);

        log.info("Cleared all products from cart for customer with id {}", custId);
        return new ResponseEntity<>(custId+" customer all products are deleted from cart",HttpStatus.OK);
    }
}
