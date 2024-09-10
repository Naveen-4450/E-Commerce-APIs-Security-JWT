package com.example.models.dbModels;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Cart
{
    @Id
    @SequenceGenerator(name = "seqGen6",sequenceName = "CartSeq", initialValue = 600, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen6")
    private Integer cartId;

    @ElementCollection
    @CollectionTable(name = "CartProducts", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Products, Integer> productQuantities = new HashMap<>();

    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId", referencedColumnName = "custId")
    @JsonBackReference
    private Customers custCart;// customerId


}
