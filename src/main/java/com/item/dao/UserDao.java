package com.item.dao;

import com.item.model.User;

public interface UserDao {
    boolean insertUser(User user);
    User getUserByUsernameAndPassword(String username, String password);
    boolean isUsernameTaken(String username);
}
