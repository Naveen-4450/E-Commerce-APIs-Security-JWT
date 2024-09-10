package com.example.models.dbModels;

import com.example.enums.Stock;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Products
{
    @Id
    @SequenceGenerator(name = "seqGen4",sequenceName = "ProdSeq", initialValue = 400, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen4")
    private Integer prodId;

    private String prodName;
    private String description;
    private Double price;
    private Integer discount;
    private Date createdAt;
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    private Stock stockAvail = Stock.AVAILABLE;
    private List<String> prodImages;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    @JsonIgnore
    private Categories productCategories;

    @OneToMany(mappedBy = "productId",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItems> orderItems; //here It's Not Required. bcz, we are not return order items details along with product details

}
















/*  @ManyToMany(mappedBy = "prodCart",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cart> cart;*/
  /*@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "WishlistProduct",
            joinColumns = @JoinColumn(name = "prodId", referencedColumnName = "prodId"),
            inverseJoinColumns =@JoinColumn(name = "wishlistId", referencedColumnName = "wishlistId")
    )
    private List<Wishlist> wishlists;*/