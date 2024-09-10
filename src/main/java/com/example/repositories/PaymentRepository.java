package com.example.repositories;

import com.example.models.dbModels.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments,Integer> {
}
