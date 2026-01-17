package com.cosmetics.store.filter;

import com.cosmetics.store.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersUpdateRequestDto {
    @NotBlank
    @Size(max = 80, message = "{error.user.name.max}")
    private String name;
    @NotBlank
    @Size(max = 80, message = "{error.user.lastname.max}")
    private String lastname;
    @NotBlank
    @Size(max = 80, message = "{error.user.email.max}")
    @Email(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$", message = "{error.customer.numdoc.max}")
    private String email;

    public Users toEntity() {
        Users users = new Users();
        users.setName(name);
        users.setLastname(lastname);
        users.setEmail(email);
        return users;
    }
}
