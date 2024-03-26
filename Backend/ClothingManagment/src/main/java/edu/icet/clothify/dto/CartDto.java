package edu.icet.clothify.dto;
import edu.icet.clothify.entity.Stock;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    @NotNull
    private Long id;
    private Stock stockId;
    private int qty;
    private int productTot;
}
