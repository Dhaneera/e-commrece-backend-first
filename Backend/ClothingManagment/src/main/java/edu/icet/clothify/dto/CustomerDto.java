package edu.icet.clothify.dto;

import java.util.List;

public class CustomerDto {

    private Long id;
    private String mail;
    private String name;
    private String address;
    private String phone;
    private List<Long> orderIds;
    private List<Long> billingInfoIds;

    // private List<OrderDTO> orders;
    // private List<BillingInfoDTO> billingInfo;
}
