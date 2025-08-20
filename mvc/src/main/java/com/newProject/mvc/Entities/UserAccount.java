package com.newProject.mvc.Entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
@Entity
public class UserAccount {

    @Id @GeneratedValue
    Long id;
    String username;
    String password;
    //@ElementCollection → means this is a collection of simple values (not another entity with its own table).
    //fetch = FetchType.EAGER → tells JPA to always load authorities with the user immediately (important for
    // Spring Security, because roles are needed right away during authentication).
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserAccount(String user, String password, String roleUser) {
    }
    //authorities = list of roles/permissions assigned to the user.
    //List<GrantedAuthority> → GrantedAuthority is a Spring Security interface that represents an authority/role.
}
