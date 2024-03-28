package edu.icet.clothify.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class CustomerDto {

    private Long id;
    private String mail;
    private String name;
    private String address;
    private String phone;
    private List<Long> orderIds;
    private List<Long> billingInfoIds;
     private List<OrdersDto> orders;
     private List<BillingInfoDto> billingInfo;
}
