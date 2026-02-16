package com.store.cosmetics.entity.response;

import com.store.cosmetics.entity.Sale;
import com.store.cosmetics.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto {
    private Boolean active;
    private Users usersCreated;
    private Users usersUpdated;
    private Users usersDeleted;

    public Sale toEntity() {
        Sale sale = new Sale();
        sale.setUsersCreated(usersCreated);
        sale.setUsersUpdated(usersUpdated);
        sale.setUsersDeleted(usersDeleted);
        return sale;
    }
}
