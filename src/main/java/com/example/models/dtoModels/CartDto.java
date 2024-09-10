package com.example.models.dtoModels;

import com.example.enums.Stock;
import com.example.models.dbModels.Products;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CartDto
{
    private Integer cartId;
    private List<ProductDto> productQuantities;


    @Data
    public static class ProductDto {
        private Integer prodId;
        private String prodName;
        private String description;
        private Double price;
        private Integer discount;
        private Stock stockAvail;
        private List<String> prodImages;
        private Integer quantity; // extra added for changing CartDto Map to List
    }

}
