package com.example.repositories;

import com.example.models.dbModels.CustomerAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddresses,Integer>
{
        CustomerAddresses findByAddId(int addId);

        List<CustomerAddresses> findByCustomerIdCustId(int custId);
}
