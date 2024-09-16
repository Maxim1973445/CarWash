package org.example.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


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
        return this.getCode();
    }
}
