package edu.icet.clothify.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.entity.BillingInfo;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Payment;
import edu.icet.clothify.util.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdersDto {
    @NotNull
    @Valid
    private Long id;
    private String customerId ;
    private Cart cart;
    @NotBlank(message = "address is null")
    private String address;
    @NotBlank(message = "contact is null")
    @Size(min = 10,message = "contact missing digits")
    private String phone;
    @NotBlank(message = "tax  is null")
    private Double tax;
    @NotBlank(message = "charging is null")
    private String charge;
    @NotBlank(message = "zipcode is null")
    private String zipCode;
    @NotBlank(message = "Total is null")
    private Double Tot;
    @NotBlank(message = "city is null")
    @Size(min = 5,message = "contact missing digits")
    private String city;
    private BillingInfo billingInfo;
    private Customer customer;
    private Payment payment;
    private OrderStatus status;
}
