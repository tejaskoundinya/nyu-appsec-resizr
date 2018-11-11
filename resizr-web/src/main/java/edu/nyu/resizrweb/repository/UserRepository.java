package edu.nyu.resizrweb.repository;

import edu.nyu.resizrweb.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
