package de.oks.g52shop.service;

import de.oks.g52shop.domain.entity.Role;
import de.oks.g52shop.repository.RoleRepository;
import de.oks.g52shop.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleUser() {
        return repository.findByTitle("ROLE_USER").orElseThrow(
                () -> new RuntimeException("ROLE_USER doesn't exist in db")
        );
    }
}
