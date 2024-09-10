package com.example.repositories;

import com.example.enums.Stock;
import com.example.models.dbModels.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository extends JpaRepository<Products,Integer>
{
    /*@Modifying
    @Query("UPDATE Products p SET p.stockAvail = :stockAvail WHERE p.prodId = :proId")
    void updateProductStatus(@Param("proId") int proId, @Param("stockAvail") Stock stockAvail);*/
}
