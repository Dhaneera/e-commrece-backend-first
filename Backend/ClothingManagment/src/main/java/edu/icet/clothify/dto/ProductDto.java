package edu.icet.clothify.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Collection;
import edu.icet.clothify.entity.SubCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductDto {
    @Valid

    private Long id;
    @NotBlank(message = "Product Name cannot be null")
    private String name;
    @NotBlank(message = "Description cannot be null")
    private  String desc;
    private double price;
    private int soldCount;
    private byte[] imageData;
    private Category category;
    private Collection collection;
    private SubCategory subCategory;
}
