package edu.icet.clothify.entity;

import edu.icet.clothify.util.converter.PaymentStatusConverter;
import edu.icet.clothify.util.converter.PaymentTypeConverter;
import edu.icet.clothify.util.enums.PaymentStatus;
import edu.icet.clothify.util.enums.PaymentType;
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
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
    @Column(name = "Payment Total" , nullable = false)
    private String Tot;

    @Column(name = "Payment Status")
    @Convert(converter =  PaymentStatusConverter.class)
    private PaymentStatus status;

    @Column(name = "Payment Type")
    @Convert(converter =  PaymentTypeConverter.class)
    private PaymentType paymentType;

}
