package com.cosmetics.store.entity.request;

import com.cosmetics.store.entity.Product;
import com.cosmetics.store.entity.Sale;
import com.cosmetics.store.entity.SaleDetail;
import com.cosmetics.store.entity.Users;
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
public class SaleDetailRequestDto {
    @NotBlank
    @PositiveOrZero(message = "{error.sale_detail.price_sale.positive}")
    private BigDecimal priceSale;
    @NotBlank
    @Positive(message = "{error.sale_detail.quantity.positive}")
    private Integer quantity;

    @NotBlank
    @NotNull
    private Sale saleId;
    @NotBlank
    @NotNull
    private Product productId;

    private Users usersCreated;
    private Users usersUpdated;
    private Users usersDeleted;

    public SaleDetail toEntity(){
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setPriceSale(priceSale);
        saleDetail.setQuantity(quantity);
        saleDetail.setSaleId(saleId);
        saleDetail.setProductId(productId);
        saleDetail.setUsersCreated(usersCreated);
        saleDetail.setUsersUpdated(usersUpdated);
        saleDetail.setUsersDeleted(usersDeleted);
        return saleDetail;
    }
}
