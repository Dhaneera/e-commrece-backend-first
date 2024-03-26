package edu.icet.clothify.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDto {

    @NotNull
    private Long id;
    private  String price;
    private String qty;

//    private Product product;
}
