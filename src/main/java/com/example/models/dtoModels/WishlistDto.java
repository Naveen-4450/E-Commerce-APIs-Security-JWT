package com.example.models.dtoModels;

import com.example.enums.Stock;
import com.example.models.dbModels.Customers;
import com.example.models.dbModels.Products;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class WishlistDto  //Dto created for getting the data based on CustId. without this lazyLoading Exceptions coming
{
    private Integer wishlistId;
    private Date addedDate;

    //product details
    private String prodName;
    private String description;
    private Double price;
    private Stock stockAvail;
    private List<String> prodImages;

    //customer Details
    private Integer custId;
    /* these details are not required
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Long mobile;
    private String email;*/
}
