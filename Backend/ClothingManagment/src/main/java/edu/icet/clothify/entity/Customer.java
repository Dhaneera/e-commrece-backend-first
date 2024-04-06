package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Email")
    private String mail;

    @OneToOne(mappedBy = "customerId")
    @JsonIgnore
    private Cart cart;

    @Column(name = "UserName")
    private String name ;

    @Column(name = "Address")
    private String address;

    @Column(name ="Contact")
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    @OneToMany(mappedBy = "customer")
    private List<BillingInfo> billingInfo;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(String customerName) {
        this.name =customerName;
    }
}
