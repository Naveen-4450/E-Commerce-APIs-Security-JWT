package com.example.models.dbModels;

import com.example.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Payments
{
    @Id
    @SequenceGenerator(name = "seqGen9",sequenceName = "PaymentsSeq", initialValue = 900, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen9")
    private Integer paymentId;

    private Double amount;

    private Date paymentDate;
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders orderId;



}
