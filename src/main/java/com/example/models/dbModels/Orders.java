package com.example.models.dbModels;

import com.example.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.client.support.InterceptingHttpAccessor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Orders
{
    @Id
    @SequenceGenerator(name = "seqGen7",sequenceName = "OrderSeq", initialValue = 700, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen7")
    private Integer orderId;
    private Date OrderedDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId", referencedColumnName = "custId")
    private Customers customerId;        // Relationship to Customers


    @OneToMany(mappedBy = "orderId",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItems> orderItems;     // Relationship to OrderItems

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addId", referencedColumnName = "addId")
    private CustomerAddresses custAddId;        //Relationship to CustomerAddress


    @OneToOne(mappedBy = "orderId", cascade = CascadeType.ALL,orphanRemoval = true)
    private Payments payments;       // Relationship to Payments

    @OneToOne(mappedBy = "orderId",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private DeliveredAddress deliveredAddress;   // Relationship to DeliveredAddress

}
