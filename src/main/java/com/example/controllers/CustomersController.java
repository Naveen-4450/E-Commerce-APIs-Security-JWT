package com.example.controllers;

import com.example.models.dbModels.Customers;
import com.example.models.dtoModels.CustomersDto;
import com.example.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Customers Controller API's", description = "This API's are Managed to Customers in Application")
public class CustomersController
{
    @Autowired
    private CustomerService customerSer;

    @Operation(summary = "Add/Register a Customer", description = "Adding a new Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer Details Added successfully")
    })
    @PostMapping("/addCustomer")
    public ResponseEntity<Customers> addingCustomer(@RequestBody CustomersDto cDto)
    {
        return customerSer.addingCustomer(cDto);
    }


    @Operation(summary = "Get Customer by Id", description = "Get Customer Details by customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Customer Details Retrieved"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found to Retrieve")
    })
    @GetMapping("/getCustomer/{custId}")
    public ResponseEntity<Customers> getCustomer(@PathVariable int custId)
    {
        return customerSer.getCustomer(custId);
    }


    @Operation(summary = "Get all Customers", description = "Fetching all Customer Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All Customer Details Retrieved Successfully")
    })
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customers>> getAllCustomers()
    {
        return customerSer.getAllCustomers();
    }



    @Operation(summary = "Update one Customer Details by Id", description = "Updating an existing customer Details by Customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Details Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found to Update")
    })
    @PutMapping("/updateCustomer/{custId}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable int custId, @RequestBody CustomersDto cDto)
    {
        return customerSer.updateCustomer(custId, cDto);
    }



    @Operation(summary = "Delete a  Customer by Id", description = "Deleting the Customer Details by Using Customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Deleted Successfully"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found to Delete")
    })
    @DeleteMapping("/deleteCustomer/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int custId)
    {
        return customerSer.deleteCustomer(custId);
    }



}
