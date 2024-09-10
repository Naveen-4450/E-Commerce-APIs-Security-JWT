package com.example.models.dbModels;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class CustomerAddresses
{
    @Id
    @SequenceGenerator(name = "seqGen2",sequenceName = "CustAddSeq", initialValue = 200, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen2")
    private Integer addId;

    private String addLine;
    private String city;
    private int pincode;
    private String state;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId",referencedColumnName = "custId")
    private Customers customerId;












   /* it is not required bidirectional access
   @OneToMany(mappedBy = "custAddId",cascade = CascadeType.ALL,orphanRemoval = true)
    private Orders order;*/


}
