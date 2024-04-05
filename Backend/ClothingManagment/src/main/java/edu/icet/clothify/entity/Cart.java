package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stock_id")
    @JsonIgnore
    private Stock stockId;

    @Column(name = "Quantity")
    private int qty;

    @Column(name = "Product Total")
    private Double productTot;

    @OneToOne(mappedBy = "cart")
    private Orders orders;

    private Boolean completed;

    public Cart(Long id) { // Add the missing constructor
        this.id = id;
    }


    public Cart(Long id, Stock stockId, int qty, boolean completed, Double productTot) {
        this.id = id;
        this.qty = qty;
        this.completed = completed;
        this.stockId = stockId;
        this.productTot = productTot;
    }

    public Cart(long id, boolean completed) {
        this.id=id;
        this.completed=completed;
    }
}
