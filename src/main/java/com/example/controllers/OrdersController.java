package com.example.controllers;

import com.example.enums.OrderStatus;
import com.example.models.dbModels.Orders;
import com.example.models.dtoModels.FetchOrderDetailsDto;
import com.example.models.dtoModels.OrdersDto;
import com.example.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Orders Controller API's", description = "This API's are Managed to Customers Orders in Application")
public class OrdersController
{
    @Autowired
    private OrderService orderSer;


    @Operation(summary = "Creating/Placing Order", description = "Customer Placing a Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order Placed Successfully"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found/Address is not belond to Customer, Check the Customer Id")
    })
    @PostMapping("/ordered")
    public ResponseEntity<String> createOrder(@RequestBody OrdersDto oDto)
    {
        return orderSer.createOrder(oDto);
    }


    @Operation(summary = "Get one Order Details by orderId", description = "Get Order Details by using Order Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Details Retrieved"),
            @ApiResponse(responseCode = "404", description = "Order Not Found with that Order Id")
    })
    @GetMapping("/order/orderid/{orderId}")
    public ResponseEntity<FetchOrderDetailsDto> getOrderById(@PathVariable int orderId)
    {
        return orderSer.getOrderById(orderId);
    }


    @Operation(summary = "Get All Orders of a Customer by customer Id", description = "Get All Orders History of a Customer by customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ALl Order Details Retrieved"),
            @ApiResponse(responseCode = "404", description = "Customer Not Found/ Customer don't have any order History")
    })
    @GetMapping("/order/custid/{custId}")
    public ResponseEntity<List<FetchOrderDetailsDto>> getOrderByCustomerId(@PathVariable int custId)
    {
        return orderSer.getOrderByCustomerId(custId);
    }


    @Operation(summary = "Update Order Status by orderId", description = "Updating an Order status by order Id & Pass the Status value like(SHIPPED,DELIVERED,CANCELLED,ORDERED)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Status Updated Successfully"),
            @ApiResponse(responseCode = "404", description = "Order Not Found to Update Status (check-->orderId)"),
            @ApiResponse(responseCode = "406", description = "Invalid Status Value")
    })
    @PutMapping("/order/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable int orderId, @RequestParam String status)
    {
        try {
            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            return orderSer.updateOrderStatus(orderId, orderStatus);
        }catch(IllegalArgumentException ia){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid Status value");
        }

    }




}























/*    @PutMapping("/orderShipped/{orderId}")
    public ResponseEntity<String> orderStatusShipped(@PathVariable int orderId)
    {

            return orderSer.updateOrderStatus(orderId);
    }

    @PutMapping("/orderDelivered/{orderId}")
    public ResponseEntity<String> orderStatusDelivered(@PathVariable int orderId)
    {

        return orderSer.updateOrderStatus(orderId);
    }

    @PutMapping("/orderCancelled/{orderId}")
    public ResponseEntity<String> orderStatusCancelled(@PathVariable int orderId)
    {

        return orderSer.updateOrderStatus(orderId);
    }*/