package com.example.Practice.domain.dto;

import com.example.Practice.domain.enums.RoleType;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record AuthInfoDTO(
        Long id,
        String email,
        String password,
        String username,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    @Builder
    public AuthInfoDTO {
        if (Objects.isNull(authorities)) {
            authorities = Set.of(RoleType.USER).stream()
                    .map(RoleType::getDescription)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toUnmodifiableSet());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
