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

    @Column(name = "CustomerID")
    private String customerId ;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "Address" , nullable = false)
    private String address;

    @Column(name ="Contact" , nullable = false)
    private String phone;

    @Column(name ="Tax" , nullable = false)
    private Double tax;

    @Column(name ="Delivery Charge" , nullable = false)
    private Double charge;

    @Column(name = "Land Code" , nullable = false)
    private String zipCode;

    @Column(name = "Order Total" , nullable = false)
    private Double Tot;

    @Column(name = "City" , nullable = false)
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
