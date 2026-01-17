package com.cosmetics.store.entity.mapper;

import com.cosmetics.store.entity.Product;
import com.cosmetics.store.entity.request.ProductRequestDto;
import com.cosmetics.store.entity.response.ProductResponseDto;

public class ProductMapper {
    public static void updateProduct(ProductRequestDto source, Product destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getName() != null ) {
            destination.setName( source.getName() );
        }
        if ( source.getDescription() != null ) {
            destination.setDescription( source.getDescription() );
        }
        if ( source.getPrice() != null ) {
            destination.setPrice( source.getPrice() );
        }
    }

    public static void convertToResponseDto(Product source, ProductResponseDto destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            destination.setId( source.getId() );
        }
        if ( source.getName() != null ) {
            destination.setName( source.getName() );
        }
        if ( source.getDescription() != null ) {
            destination.setDescription( source.getDescription() );
        }
        if ( source.getPrice() != null ) {
            destination.setPrice( source.getPrice() );
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
