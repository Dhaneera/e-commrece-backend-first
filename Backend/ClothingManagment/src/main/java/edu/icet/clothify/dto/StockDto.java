package edu.icet.clothify.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StockDto {
    @Valid
    private Long id;
    @NotBlank(message = "colour is null")
    private String color;
    @NotBlank(message = "size is null")
    private String size;
    @NotBlank(message = "price is null")
    private Double price;
    @NotBlank(message = "contact is null")
    @Size(min = 10,message = "Quantity missing digits")
    private int qty;
    private Cart cart;
    private Product product;


    public StockDto(Long id, String color, String size, Double price, int qty) {
        this.id=id;
        this.color=color;
        this.size=size;
        this.price=price;
        this.qty=qty;
    }
}
