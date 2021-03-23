package com.softwork.softwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;



//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<UserPrivilege> privileges;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<User> users = new HashSet<>();
/*    public Role() {
        this(USER.name(), Sets.newHashSet());
    }*/

 /*   public Role(String name, Set<UserPrivilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }


    public Set<UserPrivilege> getPrivileges(){
        return privileges;
    }*/

/*
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPrivileges()
                      .stream()
                      .map(userPrivilege -> new SimpleGrantedAuthority(userPrivilege.getPermission()))
                      .collect(Collectors.toSet());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(name)));

        return grantedAuthorities;

    }*/


}
