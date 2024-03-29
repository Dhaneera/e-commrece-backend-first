package edu.icet.clothify.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BillingInfoDto {
    @Valid
    private Long id;
    private OrdersDto order;
    private CustomerDto customer;
    @NotBlank(message = "address is null")
    private String address;
    @NotBlank(message = "contact number is blank")
    @Size(min = 10,message = "contact missing digits")
    private String phone;
}