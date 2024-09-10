package com.example.repositories;

import com.example.models.dbModels.Orders;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer>
{
    Orders findByOrderId(int orderId);

    List<Orders> findByCustomerIdCustId(int custId);
}
