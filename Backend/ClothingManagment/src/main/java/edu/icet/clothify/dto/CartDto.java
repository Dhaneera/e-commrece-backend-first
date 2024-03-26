package edu.icet.clothify.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    @NotNull
    private Long id;
//    private Stock stockId;
    private int qty;
    private int productTot;
}
