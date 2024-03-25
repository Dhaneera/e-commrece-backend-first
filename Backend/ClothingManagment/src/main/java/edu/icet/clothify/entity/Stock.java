package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Table(name = "stock")
//public class Stock {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @OneToOne(mappedBy = "StockId")
//    private Cart cart;
//
//    @Column(name = "Color")
//    private String color;
//
//    @Column(name = "size")
//    private String size;
//
//    @Column(name = "price")
//    private String price;
//
//    @Column(name = "quantity")
//    private String qty;
//
//}
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

    @OneToOne(mappedBy = "stockId") // Update mappedBy to match the property name in the Cart entity
    private Cart cart;

    @Column(name = "Color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private String qty;

}
