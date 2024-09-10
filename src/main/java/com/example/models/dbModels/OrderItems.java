package com.example.models.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderItems
{
    @Id
    @SequenceGenerator(name = "seqGen8",sequenceName = "OrderItemsSeq", initialValue = 800, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen8")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    @JsonIgnore
    private Orders orderId;      // Relationship to Orders

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodId", referencedColumnName = "prodId")
    private Products productId; // Relationship to Products

    private Integer quantity;
    private Double itemsPrice;
    private  Double finalAmount; // after dicount

}
