package edu.nyu.resizrweb.service;

import edu.nyu.resizrweb.entity.User;

public interface UserService {
    public void save(User user);
    public User findByUsername(String username);
}
