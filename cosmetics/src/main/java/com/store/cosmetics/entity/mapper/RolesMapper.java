package com.store.cosmetics.entity.mapper;

import com.store.cosmetics.entity.Roles;
import com.store.cosmetics.entity.request.RolesRequestDto;
import com.store.cosmetics.entity.response.RolesResponseDto;

public class RolesMapper {
    public static void updateRoles(RolesRequestDto source, Roles destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getName() != null ) {
            destination.setName( source.getName() );
        }
        if ( source.getDescription() != null ) {
            destination.setDescription( source.getDescription() );
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

    public static RolesResponseDto convertToResponseDto(Roles rol) {
        if (rol == null) return null;

        return new RolesResponseDto(
                rol.getId(),
                rol.getName(),
                rol.getDescription(),
                rol.getActive(),
                rol.getDateCreated(),
                rol.getDateUpdated(),
                rol.getDateDeleted(),
                UsersMapper.convertToResponseAssociatedDto(rol.getUsersCreated()),
                UsersMapper.convertToResponseAssociatedDto(rol.getUsersUpdated()),
                UsersMapper.convertToResponseAssociatedDto(rol.getUsersDeleted())
        );
    }

    public static void convertToResponseDto(Roles source, RolesResponseDto destination) {
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
