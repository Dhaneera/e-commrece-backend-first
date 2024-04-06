package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.icet.clothify.util.converter.OrderStatusConverter;
import edu.icet.clothify.util.enums.OrderStatus;
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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @Column(name = "Address")
    private String address;

    @Column(name ="Contact")
    private String phone;

    @Column(name ="Tax")
    private Double tax;

    @Column(name ="Delivery Charge")
    private Double charge;

    @Column(name = "Land Code")
    private String zipCode;

    @Column(name = "Order Total")
    private Double tot;

    @Column(name = "City")
    private String city;

    @OneToOne(mappedBy = "orders")
    @JsonIgnore
    private BillingInfo billingInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @Convert(converter =  OrderStatusConverter.class)
    private OrderStatus status;


}
