package com.momentum.shoppe.springbootexercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    public Customer(Long id, String firstName, Long pointsTotal) {
        this.id = id;
        this.firstName = firstName;
        this.pointsTotal = pointsTotal;
    }

    @Column(name = "points_total")
    private Long pointsTotal;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Builder.Default
    private List<Product> productList = new ArrayList<>();

}
