package com.example.TechnicianCompanion.authentication.security;

import com.example.TechnicianCompanion.authentication.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConfigurationImp implements UserDetails {

    private User user;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (user.getRole()) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_TECHNICIAN"));

            case SUPERVISOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR"),
                    new SimpleGrantedAuthority("ROLE_TECHNICIAN"));

            case TECHNICIAN -> List.of(
                    new SimpleGrantedAuthority("ROLE_TECHNICIAN"));

            default -> List.of();
        };
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
