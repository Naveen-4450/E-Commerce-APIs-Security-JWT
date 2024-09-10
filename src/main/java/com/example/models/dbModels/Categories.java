package com.example.models.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categories
{
    @Id
    @SequenceGenerator(name = "seqGen3",sequenceName = "ProdCateSeq", initialValue = 300, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen3")
    private Integer categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "productCategories", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Products> products;


}
