package edu.icet.clothify.dto;

import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    @NotNull
    private Long id;
    private String color;
    private String size;
    private String price;
    private String qty;

//    private Cart cart;
//    private Product product;
}
