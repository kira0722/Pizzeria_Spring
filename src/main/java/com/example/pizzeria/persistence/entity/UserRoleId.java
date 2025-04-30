package com.example.pizzeria.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleId {

    private String username;
    private String role;


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof UserRoleId that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(username, role);
    }


}
