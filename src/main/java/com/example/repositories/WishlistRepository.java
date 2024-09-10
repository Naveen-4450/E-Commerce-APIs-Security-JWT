package com.example.repositories;

import com.example.models.dbModels.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer>
{
     // List<Wishlist> findByCustomerIdCustId(int custId);

      List<Wishlist> findByCustomerIdCustId(int custId);

}
