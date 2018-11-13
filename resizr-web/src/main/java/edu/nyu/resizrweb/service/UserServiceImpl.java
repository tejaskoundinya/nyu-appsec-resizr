package edu.nyu.resizrweb.service;

import edu.nyu.resizrweb.entity.Role;
import edu.nyu.resizrweb.entity.User;
import edu.nyu.resizrweb.repository.RoleRepository;
import edu.nyu.resizrweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.getByAuthority("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setAuthorities(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}