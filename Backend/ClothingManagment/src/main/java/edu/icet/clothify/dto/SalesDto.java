package edu.icet.clothify.dto;

import edu.icet.clothify.entity.Product;
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
public class SalesDto {

    @NotNull
    @Valid
    private Long id;
    @NotBlank(message = "price is null")
    private  Double price;
    @NotBlank(message = "quantity is null")
    private String qty;
    private Product product;
}
