package com.store.cosmetics.entity.mapper;

import com.store.cosmetics.entity.UsersRoles;
import com.store.cosmetics.entity.request.UsersRolesRequestDto;
import com.store.cosmetics.entity.response.UsersRolesResponseDto;

public class UsersRolesMapper {
    public void updateUserRoles(UsersRolesRequestDto source, UsersRoles destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getUsersId() != null ) {
            destination.setUsersId( source.getUsersId() );
        }
        if ( source.getRoleId() != null ) {
            destination.setRoleId( source.getRoleId() );
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

    public static void convertToResponseDto(UsersRoles source, UsersRolesResponseDto destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            destination.setId( source.getId() );
        }
        if ( source.getUsersId() != null ) {
            destination.setUsersId( UsersMapper.convertToResponseDto(source.getUsersId()) );
        }
        if ( source.getRoleId() != null ) {
            destination.setRoleId( RolesMapper.convertToResponseDto(source.getRoleId()) );
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
