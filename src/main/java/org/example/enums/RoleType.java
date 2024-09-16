package org.example.enums;

import lombok.Getter;
import lombok.Setter;

public enum RoleType {

    ADMIN("admin"),CLIENT("client"),OWNER("owner");
    @Getter
    @Setter
    private String code;


    RoleType(String code) {
        this.code = code;
    }
}
