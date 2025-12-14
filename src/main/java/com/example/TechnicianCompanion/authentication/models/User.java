package com.example.TechnicianCompanion.authentication.models;

import com.example.TechnicianCompanion.reports.models.Report;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "users")
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String login;
    private String password;
    private UserRole role;
    @ManyToMany(mappedBy = "technicians")
    @JsonManagedReference
    private Set<Report> reports = new HashSet<>();

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
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
    public String getUsername() {
        return login;
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
