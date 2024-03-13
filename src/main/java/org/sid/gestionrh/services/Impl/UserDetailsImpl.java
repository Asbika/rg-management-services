package org.sid.gestionrh.services.Impl;

import org.sid.gestionrh.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {


    private Long id;
    private String name;
    private String email;
    private String password;
    //CREATE ENTITY FOR ROLE
    private Collection<? extends GrantedAuthority> role;//ADMIN - RH

    public UserDetailsImpl(Long id, String name, String email, String password, Collection<? extends GrantedAuthority>  role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public static UserDetailsImpl builder(User user){
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole()));
        return new UserDetailsImpl(user.getId(), user.getName(),user.getEmail(),user.getPassword(),roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
