package com.store.cosmetics.entity.response;

import com.store.cosmetics.entity.Product;
import com.store.cosmetics.entity.Sale;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailResponseDto {
    @NotBlank
    @PositiveOrZero(message = "{error.sale_detail.price_sale.positive}")
    private BigDecimal priceSale;
    @NotBlank
    @Positive(message = "{error.sale_detail.quantity.positive}")
    private Integer quantity;
    private Boolean active;

    @NotBlank
    @NotNull
    private Sale saleId;
    @NotBlank
    @NotNull
    private Product productId;

    private UsersAssociatedResponseDto usersCreated;
    private UsersAssociatedResponseDto usersUpdated;
    private UsersAssociatedResponseDto usersDeleted;
}
