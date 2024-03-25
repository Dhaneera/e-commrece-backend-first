package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.converter.OrderStatusConverter;
import org.example.util.enums.OrderStatus;


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
    private String tax;

    @Column(name ="Delivery Charge" , nullable = false)
    private String charge;

    @Column(name = "Land Code" , nullable = false)
    private String zipCode;

    @Column(name = "Order Total" , nullable = false)
    private Long Tot;

    @Column(name = "City" , nullable = false)
    private String city;

    @OneToOne(mappedBy = "orders")
    private BillingInfo billingInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "orders")
    private Payment payment;

    @Convert(converter =  OrderStatusConverter.class)
    private  OrderStatus status;

}
