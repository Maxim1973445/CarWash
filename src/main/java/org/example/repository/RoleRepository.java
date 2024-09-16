package org.example.repository;

import org.example.dao.Role;
import org.example.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> getRoleByDescription(String roleName);

    Role findByRoleType(RoleType roleType);
}
