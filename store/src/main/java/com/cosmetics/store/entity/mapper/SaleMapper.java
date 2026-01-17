package com.cosmetics.store.entity.mapper;

import com.cosmetics.store.entity.Sale;
import com.cosmetics.store.entity.request.SaleRequestDto;

public class SaleMapper {
    public void updateSale(SaleRequestDto source, Sale destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getUsersCreated() != null ) {
            destination.setUsersCreated( source.getUsersCreated() );
        }
        if ( source.getUsersUpdated() != null ) {
            destination.setUsersUpdated( source.getUsersUpdated() );
        }
        if ( source.getUsersDeleted() != null ) {
            destination.setUsersDeleted( source.getUsersDeleted() );
        }
    }
}
