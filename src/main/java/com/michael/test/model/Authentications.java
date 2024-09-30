package com.michael.test.model;

import com.michael.test.controller.dto.request.SignupRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Table(name = "authentications")
@Getter
@Setter
@RequiredArgsConstructor
public class Authentications extends AuditTrail implements UserDetails {

    @Id
    @Column(nullable = false)
    private String id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Users idUser;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = true;
    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = true;
    @Column(name = "role", nullable = false)
    private String role = "ROLE_USER";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
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
        return isEnabled;
    }

    public Authentications(Users users, SignupRequest request, String password) {
        this.id = UUID.randomUUID().toString();
        this.idUser = users;
        this.username = request.getUsername();
        this.password = password;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
