package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfoDto {
    private Long id;
    private OrdersDto order;
    private CustomerDto customer;
    private String address;
    private String phone;

}
