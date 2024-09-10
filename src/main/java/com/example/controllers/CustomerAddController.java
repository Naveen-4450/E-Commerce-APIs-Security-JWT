package com.example.controllers;

import com.example.models.dbModels.CustomerAddresses;
import com.example.models.dtoModels.CustomerAddressDto;
import com.example.services.CustomerAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "CustomerAddress Controller API's", description = "This API's are Managed to Customer Addresses in Application")
public class CustomerAddController
{
    @Autowired
    private CustomerAddressService custAddSer;

    @Operation(summary = "Add a Customer Address", description = "Adding a new CustomerAddress")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer Address Added successfully")
    })
    @PostMapping("/custAddress/{custId}")
    public ResponseEntity<CustomerAddresses> addingAddress(@PathVariable int custId, @RequestBody CustomerAddressDto cADto)
    {
        return custAddSer.addingAddress(custId,cADto);
    }


    @Operation(summary = "Get Customer Address by address id", description = "Get Customer Address by customerAddress Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Customer Address Retrieved"),
            @ApiResponse(responseCode = "404", description = "Customer Address Not Found to Retrieve")
    })
    @GetMapping("/oneAdd/{addId}")
    public ResponseEntity<CustomerAddressDto> getOneAddress(@PathVariable int addId)
    {
        return custAddSer.getOneAddress(addId);
    }


    @Operation(summary = "Get all Customer Addresses by customer Id", description = "Fetching all Customer Addresses by customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Customer Addresses Retrieved Successfully"),
            @ApiResponse(responseCode = "404", description = "Customer Doesn't add any Addresses to Retrieve")
    })
    @GetMapping("/allAdd/{custId}")
    public ResponseEntity<List<CustomerAddressDto>> getAllAddresses(@PathVariable int custId)
    {
        return custAddSer.getAllAddresses(custId);
    }


    @Operation(summary = "Update Customer Address Details by AddressId", description = "Updating an existing customer Address Details by Address Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Address Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Customer Address Not Found to Update with address id")
    })
    @PutMapping("/address/{addId}")
    public ResponseEntity<String> updateAddress(@PathVariable int addId,
                                                           @RequestBody CustomerAddressDto AddDto)
    {
        return custAddSer.updateAddress(addId,AddDto);
    }


    @Operation(summary = "Delete a Customer Address by AddId", description = "Deleting the Customer Address by Using Address Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Address Deleted Successfully"),
            @ApiResponse(responseCode = "404", description = "Customer Address Not Found to Delete")
    })
    @DeleteMapping("/address/{addId}")
    public ResponseEntity<String> deleteAddress(@PathVariable int addId)
    {
        return custAddSer.deleteAddress(addId);
    }




}
