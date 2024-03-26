package edu.icet.clothify.dto;

import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Payment;
import edu.icet.clothify.util.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {
    @NotNull
    private Long id;
    private String customerId ;
    private Cart cart;
    private String address;
    private String phone;
    private Double tax;
    private String charge;
    private String zipCode;
    private Double Tot;
    private String city;
    private BillingInfo billingInfo;
    private Customer customer;
    private Payment payment;
    private OrderStatus status;
}
