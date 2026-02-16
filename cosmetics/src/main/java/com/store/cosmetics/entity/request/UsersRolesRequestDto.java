package com.store.cosmetics.entity.request;

import com.store.cosmetics.entity.Roles;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.UsersRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRolesRequestDto {
    @NotBlank
    @NotNull
    private Users usersId;
    @NotBlank
    @NotNull
    private Roles roleId;

    private Users usersCreated;
    private Users usersUpdated;
    private Users usersDeleted;

    public UsersRoles toEntity(){
        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setUsersId(usersId);
        usersRoles.setRoleId(roleId);
        usersRoles.setUsersCreated(usersCreated);
        usersRoles.setUsersUpdated(usersUpdated);
        usersRoles.setUsersDeleted(usersDeleted);
        return usersRoles;
    }
}
