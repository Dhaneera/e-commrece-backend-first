package edu.icet.clothify.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.entity.Customer;
import edu.icet.clothify.entity.Stock;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CartDto {

    @Valid
    private Long id;
    private Stock stockId;
    private Customer customerId;
    @NotBlank(message = "Quantity  is can't be null")
    private int qty;
    private Double productTot;
    private boolean isCompleted;

    public CartDto(Long id) {
        this.id = id;
    }
}
