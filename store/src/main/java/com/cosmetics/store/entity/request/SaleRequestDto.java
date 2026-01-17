package com.cosmetics.store.entity.request;

import com.cosmetics.store.entity.Sale;
import com.cosmetics.store.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDto {
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
