package com.cosmetics.store.entity.request;

import com.cosmetics.store.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotNull
    @NotBlank
    @Size(max = 100, message = "{error.product.name.max}")
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 255, message = "{error.product.description.max}")
    private String description;
    @NotNull
    @PositiveOrZero(message = "{error.product.price.positive}")
    private BigDecimal price;

    public Product toEntity(){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
