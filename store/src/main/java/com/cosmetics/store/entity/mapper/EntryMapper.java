package com.cosmetics.store.entity.mapper;

import com.cosmetics.store.entity.Entry;
import com.cosmetics.store.entity.request.EntryRequestDto;
import com.cosmetics.store.entity.response.EntryResponseDto;

public class EntryMapper {
    public static void updateEntry(EntryRequestDto source, Entry destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getIsInitial() != null ) {
            destination.setIsInitial( source.getIsInitial() );
        }
    }

    public static void convertToResponseDto(Entry source, EntryResponseDto destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            destination.setId( source.getId() );
        }
        if ( source.getDate() != null ) {
            destination.setDate( source.getDate() );
        }
        if ( source.getTotal() != null ) {
            destination.setTotal( source.getTotal() );
        }
        if ( source.getIsInitial() != null ) {
            destination.setIsInitial( source.getIsInitial() );
        }
        if (source.getStatus() != null ) {
            destination.setStatus( EntryStatusMapper.convertToResponseAssociatedDto( source.getStatus()) );
        }

        if ( source.getActive() != null ) {
            destination.setActive( source.getActive() );
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
