package com.example.models.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "wishlist", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"custId","prodId"})
})
public class Wishlist
{
    @Id
    @SequenceGenerator(name = "seqGen5",sequenceName = "wishlistSeq", initialValue = 500, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen5")
    private Integer wishlistId;

    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId",referencedColumnName = "custId")
    private Customers customerId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodId",referencedColumnName = "prodId")
    private Products productId;



}

 /*@ManyToMany(mappedBy = "wishlists")
    private List<Customers> custWishlist;


    @ManyToMany(mappedBy = "wishlists")
    private List<Products> prodWishlist;*/