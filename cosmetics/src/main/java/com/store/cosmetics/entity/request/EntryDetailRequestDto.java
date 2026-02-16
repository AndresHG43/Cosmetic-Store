package com.store.cosmetics.entity.request;

import com.store.cosmetics.entity.Entry;
import com.store.cosmetics.entity.EntryDetail;
import com.store.cosmetics.entity.Product;
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
public class EntryDetailRequestDto {
    @NotBlank
    @PositiveOrZero(message = "{error.entry_detail.price_entry.positive}")
    private BigDecimal priceEntry;
    @NotBlank
    @Positive(message = "{error.entry_detail.quantity.positive}")
    private Integer quantity;

    @NotBlank
    @NotNull
    private Entry entryId;
    @NotBlank
    @NotNull
    private Product productId;

    public EntryDetail toEntity(){
        EntryDetail entryDetail = new EntryDetail();
        entryDetail.setPriceEntry(priceEntry);
        entryDetail.setQuantity(quantity);
        entryDetail.setEntryId(entryId);
        entryDetail.setProductId(productId);
        return entryDetail;
    }
}
