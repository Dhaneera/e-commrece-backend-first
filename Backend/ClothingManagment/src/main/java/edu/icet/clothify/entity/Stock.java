package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne(mappedBy = "stockId")
    @JsonIgnore
    private Cart cart;

    @Column(name = "Color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private int qty;

    public Stock(Long id) {
        this.id = id;
    }

}
