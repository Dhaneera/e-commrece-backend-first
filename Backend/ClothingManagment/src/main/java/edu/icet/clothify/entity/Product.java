package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "total_description")
    private  String desc;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Column(name = "sold_count")
    private int soldCount;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    private Sales sales;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @Lob
    private byte[] imageData;
}