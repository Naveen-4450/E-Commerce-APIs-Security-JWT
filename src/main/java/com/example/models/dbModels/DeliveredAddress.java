package com.example.models.dbModels;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DeliveredAddress
{
    @Id
    @SequenceGenerator(name = "seqGen10",sequenceName = "DeliverAddSeq", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen10")
    private Integer id;

    private String addLine;
    private String city;
    private int pincode;
    private String state;
    private String country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private Orders orderId;

}
