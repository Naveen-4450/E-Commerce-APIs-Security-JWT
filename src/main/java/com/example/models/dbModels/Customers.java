package com.example.models.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Customers
{
    @Id
    @SequenceGenerator(name = "seqGen1",sequenceName = "CustSeq", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen1")
    private Integer custId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Long mobile;
    private String email;
    private Date createdAt;
    private Date UpdatedAt;

    @OneToMany(mappedBy = "customerId",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CustomerAddresses> customerAddresses;


    @OneToMany(mappedBy = "customerId",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Wishlist> wishlists;


    @OneToMany(mappedBy = "custCart",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Cart> Cart;


    @OneToMany(mappedBy = "customerId",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Orders> orders;

}


/*@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "CustomerWishlist",
            joinColumns =@JoinColumn(name = "customerId",referencedColumnName = "custId"),
            inverseJoinColumns = @JoinColumn(name = "wishlistId",referencedColumnName = "wishlistId")
    )
    private List<Wishlist> wishlists;
*/



/*
in my application i want to implement many to many association mapping between customer and Product and wishlist tables
 */


