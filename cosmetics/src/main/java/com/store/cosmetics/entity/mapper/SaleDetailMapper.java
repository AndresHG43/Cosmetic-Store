package com.store.cosmetics.entity.mapper;

import com.store.cosmetics.entity.SaleDetail;
import com.store.cosmetics.entity.request.SaleDetailRequestDto;

public class SaleDetailMapper {
    public void updateSaleDetail(SaleDetailRequestDto source, SaleDetail destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getPriceSale() != null ) {
            destination.setPriceSale( source.getPriceSale() );
        }
        if ( source.getQuantity() != null ) {
            destination.setQuantity( source.getQuantity() );
        }
        if ( source.getSaleId() != null ) {
            destination.setSaleId( source.getSaleId() );
        }
        if ( source.getProductId() != null ) {
            destination.setProductId( source.getProductId() );
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
