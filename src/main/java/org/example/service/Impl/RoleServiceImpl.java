package org.example.service.Impl;

import lombok.AllArgsConstructor;
import org.example.dao.Person;
import org.example.dao.Role;
import org.example.repository.RoleRepository;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role createRole(Role role) {
        Role existRole = roleRepository.findByRoleType(role.getRoleType());
        if(existRole!=null) {
            return existRole;
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        if(roleRepository.existsById(role.getId())) {
            return roleRepository.findById(role.getId()).get();
        }
        return roleRepository.save(role);
    }

    @Override
    public Role deleteRole(long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            roleRepository.delete(role);
            return role;
        }
        return null;
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepository.getRoleByDescription(roleName).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Person> getPersonsByRoleId(long id) {
        return roleRepository.findById(id).get().getPersons();
    }
}
