package com.momentum.shoppe.springbootexercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "product_seq", sequenceName = "product_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_seq")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private Long productCode;

    @Column(name = "points_cost")
    private Long pointsCost;
}
