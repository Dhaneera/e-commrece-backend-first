package edu.icet.clothify.dto;

import edu.icet.clothify.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesDto {

    @NotNull
    private Long id;
    private  String price;
    private String qty;
    private Product product;
}
