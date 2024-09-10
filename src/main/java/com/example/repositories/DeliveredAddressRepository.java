package com.example.repositories;

import com.example.models.dbModels.DeliveredAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveredAddressRepository extends JpaRepository<DeliveredAddress,Integer>
{
}
