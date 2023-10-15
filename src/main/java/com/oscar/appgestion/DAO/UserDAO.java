package com.oscar.appgestion.DAO;

import com.oscar.appgestion.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void deleteUser(Long id);

    void registerUser(User user);

    User getUser(User user);
}
