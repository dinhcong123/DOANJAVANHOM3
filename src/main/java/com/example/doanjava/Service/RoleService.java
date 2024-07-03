package com.example.doanjava.Service;

import com.example.doanjava.Repository.IRoleRepository;
import com.example.doanjava.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
