package com.example.models.dtoModels;

import com.example.enums.Stock;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class ProductsDto
{
    private String prodName;
    private String description;
    private Double price;
    private Integer discount;
    private MultipartFile[] prodImages;     //here Datatype is MultipartFile and in Entity it's String


}
