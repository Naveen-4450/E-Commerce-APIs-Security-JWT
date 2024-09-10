package com.example.repositories;

import com.example.models.dbModels.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers,Integer>
{
    Customers findByCustId(int custId);
}
