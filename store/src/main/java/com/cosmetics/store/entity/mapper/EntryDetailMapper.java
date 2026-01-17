package com.cosmetics.store.entity.mapper;

import com.cosmetics.store.entity.EntryDetail;
import com.cosmetics.store.entity.request.EntryDetailRequestDto;
import com.cosmetics.store.entity.response.EntryDetailResponseDto;

public class EntryDetailMapper {
    public static void updateEntryDetail(EntryDetailRequestDto source, EntryDetail destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getPriceEntry() != null ) {
            destination.setPriceEntry( source.getPriceEntry() );
        }
        if ( source.getQuantity() != null ) {
            destination.setQuantity( source.getQuantity() );
        }
        if ( source.getEntryId() != null ) {
            destination.setEntryId( source.getEntryId() );
        }
        if ( source.getProductId() != null ) {
            destination.setProductId( source.getProductId() );
        }
    }

    public static void convertToResponseDto(EntryDetail source, EntryDetailResponseDto destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            destination.setId( source.getId() );
        }
        if ( source.getPriceEntry() != null ) {
            destination.setPriceEntry( source.getPriceEntry() );
        }
        if ( source.getQuantity() != null ) {
            destination.setQuantity( source.getQuantity() );
        }

        if ( source.getActive() != null ) {
            destination.setActive( source.getActive() );
        }

        if ( source.getEntryId() != null ) {
            destination.setEntryId( source.getEntryId() );
        }
        if ( source.getProductId() != null ) {
            destination.setProductId( source.getProductId() );
        }

        if ( source.getDateCreated() != null ) {
            destination.setDateCreated( source.getDateCreated() );
        }
        if ( source.getDateUpdated() != null ) {
            destination.setDateUpdated( source.getDateUpdated() );
        }
        if ( source.getDateDeleted() != null ) {
            destination.setDateDeleted( source.getDateDeleted() );
        }

        if ( source.getUsersCreated() != null ) {
            destination.setUsersCreated( UsersMapper.convertToResponseAssociatedDto(source.getUsersCreated()) );
        }
        if ( source.getUsersUpdated() != null ) {
            destination.setUsersUpdated( UsersMapper.convertToResponseAssociatedDto(source.getUsersUpdated()) );
        }
        if ( source.getUsersDeleted() != null ) {
            destination.setUsersDeleted( UsersMapper.convertToResponseAssociatedDto(source.getUsersDeleted()) );
        }
    }
}
