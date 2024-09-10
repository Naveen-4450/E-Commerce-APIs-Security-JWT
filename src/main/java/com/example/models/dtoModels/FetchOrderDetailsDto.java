package com.example.models.dtoModels;

import com.example.enums.OrderStatus;
import com.example.enums.PaymentStatus;
import com.example.models.dbModels.CustomerAddresses;
import com.example.models.dbModels.Customers;
import com.example.models.dbModels.Payments;
import com.example.models.dbModels.Products;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FetchOrderDetailsDto
{
    //from orders entity
    private Integer orderId;
    private Date OrderedDate;
    private OrderStatus status;
    private Double totalAmount;

    private Integer customerId;
    private Integer addId;

    private String paymentMethod;
    private PaymentStatus PaymentStatus;

    private List<OrdItemDto> orderItems;

    @Data
    public static class OrdItemDto{
      private Integer id;
      private Integer quantity;
      private Double itemsPrice;
      private  Double finalAmount;
      private ProdDto product;
    }
    @Data
    public static class ProdDto
    {
      private Integer productId;
      private String prodName;
      private String description;
      private Double price;
      private List<String> prodImages;
    }



}

















































 /*@Data
    public static class CustDto{
        private Integer customerId;
        private String firstName;
        private String lastName;
        private Long mobile;
        private String email;
    }*/
   /* @Data
    public static class CustAddDto{
        private Integer addId;
        private String addLine;
        private String city;
        private int pincode;
        private String state;
        private String country;
    }*/

