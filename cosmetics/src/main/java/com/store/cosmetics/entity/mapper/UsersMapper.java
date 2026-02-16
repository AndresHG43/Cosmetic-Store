package com.store.cosmetics.entity.mapper;

import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.request.UsersRequestDto;
import com.store.cosmetics.entity.response.UsersAssociatedResponseDto;
import com.store.cosmetics.entity.response.UsersResponseDto;
import com.store.cosmetics.filter.UsersUpdateRequestDto;

public class UsersMapper {
    public static void updateUser(final UsersRequestDto source, Users destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getName() != null ) {
            destination.setName( source.getName() );
        }
        if ( source.getLastname() != null ) {
            destination.setLastname( source.getLastname() );
        }
        if ( source.getEmail() != null ) {
            destination.setEmail( source.getEmail() );
        }
    }

    public static void updateUser(final UsersUpdateRequestDto source, Users destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getName() != null ) {
            destination.setName( source.getName() );
        }
        if ( source.getLastname() != null ) {
            destination.setLastname( source.getLastname() );
        }
        if ( source.getEmail() != null ) {
            destination.setEmail( source.getEmail() );
        }
    }

    public static void convertToResponseDto(Users source, UsersResponseDto destination) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            destination.setId( source.getId() );
        }
        if ( source.getName() != null ) {
            destination.setName( source.getName() );
        }
        if ( source.getLastname() != null ) {
            destination.setLastname( source.getLastname() );
        }
        if ( source.getEmail() != null ) {
            destination.setEmail( source.getEmail() );
        }
        if ( source.getUsername() != null ) {
            destination.setUsername( source.getUsername() );
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
    }

    public static UsersResponseDto convertToResponseDto(Users user) {
        if (user == null) return null;

        return new UsersResponseDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getUsername(),
                user.getActive(),
                user.getDateCreated(),
                user.getDateUpdated(),
                user.getDateDeleted()
        );
    }

    public static UsersAssociatedResponseDto convertToResponseAssociatedDto(Users user) {
        if (user == null) return null;

        return new UsersAssociatedResponseDto(
                user.getId(),
                user.getName(),
                user.getLastname()
        );
    }


}
