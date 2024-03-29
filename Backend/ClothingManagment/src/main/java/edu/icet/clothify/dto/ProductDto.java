package edu.icet.clothify.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.entity.Category;
import edu.icet.clothify.entity.Collection;
import edu.icet.clothify.entity.SubCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "name is null")
    private String name;
    @NotBlank(message = "description is null")
    @Size(min = 10,message = "contact missing digits")
    private  String desc;
    @NotBlank(message = "price is null")
    private double price;
    @NotBlank(message = "Sold count is null")
    private int soldCount;
    @NotBlank(message = "image is null")
    private byte[] imageData;
    private Category category;
    private Collection collection;
    private SubCategory subCategory;
}
