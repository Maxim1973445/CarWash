package org.example.service;

import org.example.dao.Person;
import org.example.dao.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Role role);
    Role deleteRole(long id);
    Role getRole(String roleName);
    List<Role> getAllRoles();
    List<Person> getPersonsByRoleId(long Id);

}
