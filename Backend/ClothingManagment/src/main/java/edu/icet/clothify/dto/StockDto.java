package edu.icet.clothify.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.entity.Cart;
import edu.icet.clothify.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StockDto {
    private Long id;
    private String color;
    private String size;
    private Double price;
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
