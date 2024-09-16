package org.example.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


public enum RoleType implements GrantedAuthority {

    ADMIN("admin"),CLIENT("client"),OWNER("owner");
    @Getter
    @Setter
    private String code;


    RoleType(String code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
