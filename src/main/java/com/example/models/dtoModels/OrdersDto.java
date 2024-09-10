package com.example.models.dtoModels;

import com.example.enums.OrderStatus;
import com.example.models.dbModels.Customers;
import com.example.models.dbModels.OrderItems;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrdersDto
{
    private int customerId;
    private int custAddId;
    private List<OrderItemsDto> orderItems;
    private PaymentDto payment;






}
