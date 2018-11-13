package edu.nyu.resizrweb.repository;

import edu.nyu.resizrweb.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    public Role getByRole(String role);
}
