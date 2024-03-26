package edu.icet.clothify.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;
    private String name;
    private  String description;
    private double price;
    private int soldCount;
    private Long categoryId;
    private Long subCategoryId;
    private Long collectionId;
    private SalesDto sales;
    private List<StockDto> stocks;
    private String image;
}
