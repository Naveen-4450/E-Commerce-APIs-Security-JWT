package com.example.models.dbModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Credentials
{
    @Id
    @SequenceGenerator(name = "seqGen44",sequenceName = "CredentialsSeq", initialValue = 1500, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen44")
    private Integer id;
    private String username;
    private String password;
    private String roles;

//    private Integer custId;
}
