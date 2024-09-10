package com.example.models.dtoModels;

import com.example.models.dbModels.Customers;
import lombok.Data;

@Data
public class CustomerAddressDto
{
    private Integer addId; // for fetching
    private String addLine;
    private String city;
    private int pincode;
    private String state;
    private String country;

    private Integer customerId;
}
